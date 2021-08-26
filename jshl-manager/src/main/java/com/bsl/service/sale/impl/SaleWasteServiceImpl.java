package com.bsl.service.sale.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.bsl.mapper.BslSaleInfoDetailMapper;
import com.bsl.mapper.BslStockChangeDetailMapper;
import com.bsl.mapper.BslWasteWeightInfoMapper;
import com.bsl.pojo.BslSaleInfoDetail;
import com.bsl.reportbean.BslWasteSaleDetailInfo;
import com.bsl.pojo.BslStockChangeDetail;
import com.bsl.pojo.BslWasteWeightInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.sale.SalePlanService;
import com.bsl.service.sale.SaleWasteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 废品销售出库管理实现类 duk-20190319
 */
@Service
public class SaleWasteServiceImpl implements SaleWasteService {

	@Autowired
	BslBsPlanInfoMapper bslBsPlanInfoMapper;
	@Autowired
	BslSaleInfoDetailMapper bslSaleInfoDetailMapper;
	@Autowired
	BslWasteWeightInfoMapper bslWasteWeightInfoMapper;
	@Autowired
	BslStockChangeDetailMapper bslStockChangeDetailMapper;
	@Autowired	 
	SalePlanService salePlanService;

	@Autowired
	private JedisClient jedisClient;

	@Value("${REDIS_NEXT_STOCKCHANGE_ID}")
	private String REDIS_NEXT_STOCKCHANGE_ID;

	/**
	 * 库存变动流水自动生成编号 CH+日期+4位序号
	 * 
	 * @return
	 */
	public String createStockChangeId() {
		long incr = jedisClient.incr(REDIS_NEXT_STOCKCHANGE_ID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String rawId = String.format("CH%s%04d", sdf.format(new Date()), incr);
		return rawId;
	}

	/**
	 * 根据条件查询废品计划出库信息
	 */
	@Override
	public BSLResult getOutWastesService(QueryCriteria queryCriteria) {
		if(StringUtils.isBlank(queryCriteria.getSalePlanId())){
			queryCriteria.setSalePlanId(null);
		}else{
			queryCriteria.setSalePlanId("%"+queryCriteria.getSalePlanId()+"%");
		}
		if(StringUtils.isBlank(queryCriteria.getSaleSerno())){
			queryCriteria.setSaleSerno(null);
		}else{
			queryCriteria.setSaleSerno("%"+queryCriteria.getSaleSerno()+"%");
		}
		if(StringUtils.isBlank(queryCriteria.getSaleStatus())){
			queryCriteria.setSaleStatus(null);
		}
		if(StringUtils.isBlank(queryCriteria.getProdId())){
			queryCriteria.setProdId(null);
		}
		queryCriteria.setWxFlag("1");
		//起始日期 结束日期
		Date startDate = new Date();
		if(!StringUtils.isBlank(queryCriteria.getStartDate())){
			try {
				startDate = DictItemOperation.日期转换实例.parse(queryCriteria.getStartDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			try {
				startDate = DictItemOperation.日期转换实例.parse("2018-01-01");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Date endDate = new Date();
		if(!StringUtils.isBlank(queryCriteria.getEndDate())){
			try {
				endDate = DictItemOperation.日期转换实例.parse(queryCriteria.getEndDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		queryCriteria.setDateStart(startDate);
		queryCriteria.setDateEnd(endDate);
		
		if(StringUtils.isBlank(queryCriteria.getSort()) || StringUtils.isBlank(queryCriteria.getOrder())){
			queryCriteria.setOrderByClause("`sale_serno` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){	
				queryCriteria.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()),Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		List<BslWasteSaleDetailInfo> bslSaleInfoDetails = bslSaleInfoDetailMapper.getAllWasteSaleInfo(queryCriteria);
		PageInfo<BslWasteSaleDetailInfo> pageInfo = new PageInfo<BslWasteSaleDetailInfo>(bslSaleInfoDetails);
		return BSLResult.ok(bslSaleInfoDetails,"saleWasteServiceImpl","getOutWastesService",pageInfo.getTotal(),bslSaleInfoDetails);
	}

	/**
	 * 废品销售出库
	 */
	@Override
	public BSLResult updateSaleWasteOutPut(String saleSerno, Float prodOutWeight, String inputUser) {
		// 获取原详细计划信息
		BslSaleInfoDetail bslSaleInfoDetail = bslSaleInfoDetailMapper.selectByPrimaryKey(saleSerno);
		if (bslSaleInfoDetail == null) {
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据流水号未查询到销售出库详细信息");
		}
		// 出库待发货的才能退回
		if (DictItemOperation.销售单出库标志_已发货.equals(bslSaleInfoDetail.getSaleStatus())) {
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "已发货状态的销售出库详细信息不允许继续出库！");
		}
		//获取废品编号
		String prodId = bslSaleInfoDetail.getProdId();
		//查询废品信息
		BslWasteWeightInfo bslWasteWeightInfo = bslWasteWeightInfoMapper.selectByPrimaryKey(prodId);
		if(bslWasteWeightInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "根据废品类型未查询到废品信息！");
		}
		//校验废品库存是否足够
		if(bslWasteWeightInfo.getWasteWeight()<prodOutWeight){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "该类型废品不足出库重量，无法出库！");
		}
		//检查出库后是否超出标准
		Float sumWeight = bslSaleInfoDetail.getProdSumweight() + prodOutWeight;
		Float saleWeightH = bslSaleInfoDetail.getSaleWeight()*1.05f;
		Float saleWeightL = bslSaleInfoDetail.getSaleWeight()*0.95f;
		if(sumWeight > saleWeightH){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "出库之后大于该计划限定重量，无法出库！");
		}
		
		//足够的情况下开始出库
		
		//1-减去废品库存
		bslWasteWeightInfo.setWasteWeight(bslWasteWeightInfo.getWasteWeight() - prodOutWeight);
		bslWasteWeightInfo.setWasteOutWeight(bslWasteWeightInfo.getWasteOutWeight() + prodOutWeight);
		int updateByPrimaryKeySelective = bslWasteWeightInfoMapper.updateByPrimaryKeySelective(bslWasteWeightInfo);
		if(updateByPrimaryKeySelective<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(updateByPrimaryKeySelective==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改废品库存信息失败");
		}
		
		//2-记录废品库存变动流水
		BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
		bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
		bslStockChangeDetail.setProdId(prodId);//产品编号
		bslStockChangeDetail.setPlanSerno(bslSaleInfoDetail.getSalePlanId());//对应的生产指令单号
		bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_出售出库);//交易码
		bslStockChangeDetail.setProdType(DictItemOperation.产品类型_废品);//产品类型
		bslStockChangeDetail.setRubbishType(prodId);
		bslStockChangeDetail.setRubbishWeight(prodOutWeight);//重量
		bslStockChangeDetail.setInputuser(inputUser);//录入人
		bslStockChangeDetail.setPrice(bslSaleInfoDetail.getSalePrice());//价格
		bslStockChangeDetail.setCrtDate(new Date());
		bslStockChangeDetail.setRemark("废品销售出库");
		int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
		if(resultStock<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStock==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
		}
		
		//3-详细计划更新已出库重量
		bslSaleInfoDetail.setProdSumweight(sumWeight);//出库质量+产品入库质量
		if(sumWeight>=saleWeightL && sumWeight<=saleWeightH){
			bslSaleInfoDetail.setSaleStatus(DictItemOperation.销售单出库标志_已达标准);
		}else{
			bslSaleInfoDetail.setSaleStatus(DictItemOperation.销售单出库标志_未达标准);
		}
		int resultDetail = bslSaleInfoDetailMapper.updateByPrimaryKeySelective(bslSaleInfoDetail);
		if(resultDetail<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultDetail==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改详细计划失败");
		}
		
		//4-刷新通知单状态
		salePlanService.updateRefreshSalePlanStatus(bslSaleInfoDetail.getSalePlanId());
		
		return BSLResult.ok(saleSerno);
	}

	/**
	 * 废品销售退回
	 */
	@Override
	public BSLResult updateReBackSaleWaste(String saleSerno, Float prodOutWeight, String inputUser) {
		// 获取原详细计划信息
		BslSaleInfoDetail bslSaleInfoDetail = bslSaleInfoDetailMapper.selectByPrimaryKey(saleSerno);
		if (bslSaleInfoDetail == null) {
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据流水号未查询到销售出库详细信息");
		}
		// 出库待发货的才能退回
		if (DictItemOperation.销售单出库标志_已发货.equals(bslSaleInfoDetail.getSaleStatus())) {
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "已发货状态的销售出库详细信息不允许退回！");
		}
		//获取原出库重量
		Float outWeight = bslSaleInfoDetail.getProdSumweight();
		if(outWeight < prodOutWeight){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "已出库废品质量不足！");
		}
		//获取废品编号
		String prodId = bslSaleInfoDetail.getProdId();
		//查询废品信息
		BslWasteWeightInfo bslWasteWeightInfo = bslWasteWeightInfoMapper.selectByPrimaryKey(prodId);
		if(bslWasteWeightInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "根据废品类型未查询到废品信息！");
		}
		//获取退回后重量
		Float sumWeight = bslSaleInfoDetail.getProdSumweight() - prodOutWeight;
		Float saleWeightH = bslSaleInfoDetail.getSaleWeight()*1.05f;
		Float saleWeightL = bslSaleInfoDetail.getSaleWeight()*0.95f;
		
		//开始出库
		//1-加上废品库存
		bslWasteWeightInfo.setWasteWeight(bslWasteWeightInfo.getWasteWeight() + prodOutWeight);
		bslWasteWeightInfo.setWasteOutWeight(bslWasteWeightInfo.getWasteOutWeight() - prodOutWeight);
		int updateByPrimaryKeySelective = bslWasteWeightInfoMapper.updateByPrimaryKeySelective(bslWasteWeightInfo);
		if(updateByPrimaryKeySelective<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(updateByPrimaryKeySelective==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改废品库存信息失败");
		}
		
		//2-记录废品库存变动流水
		BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
		bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
		bslStockChangeDetail.setProdId(prodId);//产品编号
		bslStockChangeDetail.setPlanSerno(bslSaleInfoDetail.getSalePlanId());//对应的生产指令单号
		bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_未用退回);//交易码
		bslStockChangeDetail.setProdType(DictItemOperation.产品类型_废品);//产品类型
		bslStockChangeDetail.setRubbishType(prodId);
		bslStockChangeDetail.setRubbishWeight(prodOutWeight);//重量
		bslStockChangeDetail.setInputuser(inputUser);//录入人
		bslStockChangeDetail.setPrice(bslSaleInfoDetail.getSalePrice());//价格
		bslStockChangeDetail.setCrtDate(new Date());
		bslStockChangeDetail.setRemark("废品销售退回");
		int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
		if(resultStock<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStock==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
		}
		
		//3-详细计划更新已出库重量
		bslSaleInfoDetail.setProdSumweight(sumWeight);//出库质量-产品退回质量
		if(sumWeight>=saleWeightL && sumWeight<=saleWeightH){
			bslSaleInfoDetail.setSaleStatus(DictItemOperation.销售单出库标志_已达标准);
		}else{
			bslSaleInfoDetail.setSaleStatus(DictItemOperation.销售单出库标志_未达标准);
		}
		int resultDetail = bslSaleInfoDetailMapper.updateByPrimaryKeySelective(bslSaleInfoDetail);
		if(resultDetail<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultDetail==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改详细计划失败");
		}
		
		//4-刷新通知单状态
		salePlanService.updateRefreshSalePlanStatus(bslSaleInfoDetail.getSalePlanId());
		
		return BSLResult.ok(saleSerno);
	}

}
