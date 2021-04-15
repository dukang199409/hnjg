package com.bsl.service.sale.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.bsl.common.pojo.BSLException;
import com.bsl.common.utils.BSLResult;
import com.bsl.dao.JedisClient;
import com.bsl.mapper.BslBsPlanInfoMapper;
import com.bsl.mapper.BslCarRetchaInfoMapper;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.mapper.BslSaleInfoDetailMapper;
import com.bsl.mapper.BslStockChangeDetailMapper;
import com.bsl.pojo.BslBsPlanInfo;
import com.bsl.pojo.BslCarRetchaInfo;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslStockChangeDetail;
import com.bsl.pojo.BslProductInfoExample.Criteria;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.service.sale.ProdReturnService;
import com.bsl.service.sale.SalePlanService;

/**
 * 销售退回/磅差处理接口管理实现类 duk-20190319
 */
@Service
public class ProdReturnServiceImpl implements ProdReturnService {

	@Autowired
	BslBsPlanInfoMapper bslBsPlanInfoMapper;
	@Autowired
	BslSaleInfoDetailMapper bslSaleInfoDetailMapper;
	@Autowired
	BslProductInfoMapper bslProductInfoMapper;
	@Autowired
	BslStockChangeDetailMapper bslStockChangeDetailMapper;
	@Autowired
	BslCarRetchaInfoMapper bslCarRetchaInfoMapper;
	@Autowired	 
	SalePlanService salePlanService;

	@Autowired
	private JedisClient jedisClient;

	@Value("${REDIS_NEXT_STOCKCHANGE_ID}")
	private String REDIS_NEXT_STOCKCHANGE_ID;
	
	@Value("REDIS_NEXT_PROD_ID")
	private String REDIS_NEXT_PROD_ID;
	
	/**
	 * 库存变动流水自动生成编号 CH+日期+4位序号
	 * @return
	 */
	public String createStockChangeId() {
		long incr = jedisClient.incr(REDIS_NEXT_STOCKCHANGE_ID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String rawId = String.format("CH%s%04d", sdf.format(new Date()), incr);
		return rawId;
	}
	
	/**
	 * 销售退回/磅差处理
	 * @return
	 */
	@Override
	public BSLResult updateProdReturn(BslProductInfo bslProductInfo, String dealType,Float prodPrice) {
		String prodOutPlan = bslProductInfo.getProdOutPlan();
		if(StringUtils.isBlank(prodOutPlan)){
			throw new BSLException(ErrorCodeInfo.错误类型_参数为空,"销售通知单号不能为空！");
		}
		BslBsPlanInfo bslBsPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(prodOutPlan);
		if(bslBsPlanInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_参数为空,"根据销售通知单号未查询到记录！");
		}
		//记录车次信息与磅差退货信息
		BslCarRetchaInfo bslCarRetchaInfo = new BslCarRetchaInfo();
		bslCarRetchaInfo.setCarNo(bslProductInfo.getProdOutCarno());
		bslCarRetchaInfo.setChaWeight(0f);
		bslCarRetchaInfo.setRetWeight(0f);
		String trans_code = "";
		String remake = "";
		if(DictItemOperation.处理类型_磅差处理.equals(dealType)){
			bslCarRetchaInfo.setChaWeight(bslProductInfo.getProdRelWeight());
			if(bslBsPlanInfo.getBsChaweight() == null){
				bslBsPlanInfo.setBsChaweight(bslProductInfo.getProdRelWeight());
			}else{
				bslBsPlanInfo.setBsChaweight(bslProductInfo.getProdRelWeight()+bslBsPlanInfo.getBsChaweight());
			}
			trans_code = DictItemOperation.库存变动交易码_磅差处理;
			remake = "磅差处理";
		}else if(DictItemOperation.处理类型_退货处理.equals(dealType)){
			bslCarRetchaInfo.setRetWeight(bslProductInfo.getProdRelWeight());
			if(bslBsPlanInfo.getBsRetweight() == null){
				bslBsPlanInfo.setBsRetweight(bslProductInfo.getProdRelWeight());
			}else{
				bslBsPlanInfo.setBsRetweight(bslProductInfo.getProdRelWeight()+bslBsPlanInfo.getBsRetweight());
			}
			trans_code = DictItemOperation.库存变动交易码_退货;
			remake = "退货";
		}else{
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"处理类型不存在");
		}
		String prodType = bslProductInfo.getProdType();
		if(StringUtils.isBlank(prodType)){
			if(DictItemOperation.收发类别_卷板退货.equals(bslBsPlanInfo.getBsFlag()) || DictItemOperation.收发类别_卷板销售发货.equals(bslBsPlanInfo.getBsFlag())){
				prodType = DictItemOperation.产品类型_卷板;
			}else if(DictItemOperation.收发类别_半成品发货.equals(bslBsPlanInfo.getBsFlag())){
				prodType = DictItemOperation.产品类型_半成品;
			}else if(DictItemOperation.收发类别_成品发货.equals(bslBsPlanInfo.getBsFlag()) ||
					DictItemOperation.收发类别_委外仓成品发货.equals(bslBsPlanInfo.getBsFlag())||
					DictItemOperation.收发类别_成品转场发货.equals(bslBsPlanInfo.getBsFlag())){
				prodType = DictItemOperation.产品类型_成品;
			}else{
				prodType = "未知";
			}
		}
		
		//1-修改记录
		int result = bslBsPlanInfoMapper.updateByPrimaryKeySelective(bslBsPlanInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改销售通知单失败");
		}
		
		//2-记录库存变动流水
		BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
		bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
		bslStockChangeDetail.setProdId(bslProductInfo.getProdId());//产品编号
		bslStockChangeDetail.setPlanSerno(prodOutPlan);//对应的生产指令单号
		bslStockChangeDetail.setTransCode(trans_code);//交易码
		bslStockChangeDetail.setProdType(prodType);//产品类型
		bslStockChangeDetail.setRubbishWeight(bslProductInfo.getProdRelWeight());//重量
		bslStockChangeDetail.setInputuser(bslProductInfo.getProdInputuser());//录入人
		bslStockChangeDetail.setPrice(prodPrice);//价格
		bslStockChangeDetail.setCrtDate(new Date());
		bslStockChangeDetail.setRemark(remake);
		int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
		if(resultStock<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStock==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
		}
		
		//3-记录磅差退货车次信息
		BslCarRetchaInfo bslCarRetchaInfoOld = bslCarRetchaInfoMapper.selectByPrimaryKey(bslProductInfo.getProdOutCarno());
		if(bslCarRetchaInfoOld == null){
			int insertResult = bslCarRetchaInfoMapper.insert(bslCarRetchaInfo);
			if(insertResult<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(insertResult==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增磅差退货车次信息失败");
			}
		}else{
			if(bslCarRetchaInfoOld.getChaWeight() == null){
				bslCarRetchaInfoOld.setChaWeight(bslCarRetchaInfo.getChaWeight());
			}else{
				bslCarRetchaInfoOld.setChaWeight(bslCarRetchaInfoOld.getChaWeight()+bslCarRetchaInfo.getChaWeight());
			}
			if(bslCarRetchaInfoOld.getRetWeight() == null){
				bslCarRetchaInfoOld.setRetWeight(bslCarRetchaInfo.getRetWeight());
			}else{
				bslCarRetchaInfoOld.setRetWeight(bslCarRetchaInfoOld.getRetWeight()+bslCarRetchaInfo.getRetWeight());
			}
			
			int resultResult = bslCarRetchaInfoMapper.updateByPrimaryKey(bslCarRetchaInfoOld);
			if(resultResult<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(resultResult==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改磅差退货车次信息失败");
			}
			
		}
		
		return BSLResult.ok();
	}

	/**
	 * 根据出库单号获取流水信息
	 */
	@Override
	public BSLResult getCarInfo(String prodOutPlan) {
		BslProductInfoExample selectExample = new BslProductInfoExample();
		Criteria criteria = selectExample.createCriteria();
		criteria.andProdOutPlanEqualTo(prodOutPlan);
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(selectExample);
		List<String> carList = new ArrayList<String>();
		if(bslProductInfos != null && bslProductInfos.size()>0 ){
			for (BslProductInfo bslProductInfo : bslProductInfos) {
				if(!carList.contains(bslProductInfo.getProdOutCarno())){
					carList.add(bslProductInfo.getProdOutCarno());
				}
			}
		}
		return BSLResult.ok(carList);
	}

}
