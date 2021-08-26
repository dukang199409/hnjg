package com.bsl.service.sale.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.bsl.common.pojo.BSLException;
import com.bsl.common.utils.BSLResult;
import com.bsl.dao.JedisClient;
import com.bsl.mapper.BslBsPlanInfoMapper;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.mapper.BslStockChangeDetailMapper;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslStockChangeDetail;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.service.sale.ProdRetReAddService;
import com.bsl.service.sale.SalePlanService;

/**
 * 销售退回重新入库接口管理实现类 duk-20190319
 */
@Service
public class ProdRetReAddServiceImpl implements ProdRetReAddService {

	@Autowired
	BslBsPlanInfoMapper bslBsPlanInfoMapper;
	@Autowired
	BslProductInfoMapper bslProductInfoMapper;
	@Autowired
	BslStockChangeDetailMapper bslStockChangeDetailMapper;
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
	 * 生成产品代码
	 * @return
	 */
	public String createProdId(String startString) {
		long incr = jedisClient.incr(REDIS_NEXT_PROD_ID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String prodId = String.format("%s%s%02dR",startString,sdf.format(new Date()), incr);
		return prodId;
	}
	
	/**
	 * 销售退回重新入库
	 * @return
	 */
	@Override
	public BSLResult addRetProd(BslProductInfo bslProductInfo) {
		String prodId = bslProductInfo.getProdId();
		String prodType = bslProductInfo.getProdType();
		if(StringUtils.isBlank(prodType)){
			throw new BSLException(ErrorCodeInfo.错误类型_参数为空,"产品类型不能为空！");
		}
		//设置产品信息
		BslProductInfo prod = new BslProductInfo();
		prod.setProdName(bslProductInfo.getProdName());
		prod.setProdType(prodType);
		prod.setProdRelWeight(bslProductInfo.getProdRelWeight());
		prod.setProdPrintWeight(bslProductInfo.getProdRelWeight());
		prod.setProdStatus(DictItemOperation.产品状态_已入库);
		prod.setProdSource(DictItemOperation.产品来源_退货入库);
		prod.setProdInputuser(bslProductInfo.getProdInputuser());
		prod.setCrtDate(new Date());
		prod.setRemark("退货");
		
		if(!StringUtils.isBlank(prodId)){
			BslProductInfo reAddProd = bslProductInfoMapper.selectByPrimaryKey(prodId);
			prod.setProdNorm(reAddProd.getProdNorm());
			prod.setProdMaterial(reAddProd.getProdMaterial());
			prod.setProdLevel(reAddProd.getProdLevel());
			prod.setProdSourceCompany(reAddProd.getProdSourceCompany());
			prod.setProdLuno(reAddProd.getProdLuno());
			prod.setProdOriId(prodId);
			prod.setProdCompany(reAddProd.getProdCompany());
			if(DictItemOperation.产品类型_成品.equals(prodType)){
				prod.setProdLength(reAddProd.getProdLength());
				prod.setProdNum(bslProductInfo.getProdNum());
				prod.setProdDclFlag(DictItemOperation.产品外协厂标志_本厂);
				prod.setProdId(createProdId("XR"));
			}else if(DictItemOperation.产品类型_半成品.equals(prodType)){
				prod.setProdNum(1);
				prod.setProdUserType(DictItemOperation.纵剪带用途_公用);
				prod.setProdId(createProdId("ZR"));
			}else if(DictItemOperation.产品类型_卷板.equals(prodType)){
				prod.setProdNum(1);
				prod.setProdId(createProdId("YLR"));
			}
		}else{
			prod.setProdNorm(bslProductInfo.getProdNorm());
			prod.setProdMaterial(bslProductInfo.getProdMaterial());
			if(DictItemOperation.产品类型_成品.equals(prodType)){
				prod.setProdLength(bslProductInfo.getProdLength());
				prod.setProdNum(bslProductInfo.getProdNum());
				prod.setProdDclFlag(DictItemOperation.产品外协厂标志_本厂);
				prod.setProdId(createProdId("X"));
			}else if(DictItemOperation.产品类型_半成品.equals(prodType)){
				prod.setProdNum(1);
				prod.setProdUserType(DictItemOperation.纵剪带用途_公用);
				prod.setProdId(createProdId("Z"));
			}else if(DictItemOperation.产品类型_卷板.equals(prodType)){
				prod.setProdNum(1);
				prod.setProdId(createProdId("YL"));
			}
			
		}
		
		//1-新增记录
		int result = bslProductInfoMapper.insert(prod);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"退货产品入库失败");
		}
		
		//2-记录库存变动流水
		BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
		bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
		bslStockChangeDetail.setProdId(prod.getProdId());//产品编号
		bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_入库);//交易码
		bslStockChangeDetail.setProdType(prodType);//产品类型
		bslStockChangeDetail.setRubbishWeight(bslProductInfo.getProdRelWeight());//重量
		bslStockChangeDetail.setInputuser(bslProductInfo.getProdInputuser());//录入人
		bslStockChangeDetail.setCrtDate(new Date());
		bslStockChangeDetail.setRemark("退货重新入库");
		int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
		if(resultStock<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStock==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
		}
		
		return BSLResult.ok(prod.getProdId());
	}

}
