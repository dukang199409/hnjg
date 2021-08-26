package com.bsl.service.dclprod.impl;

import java.text.ParseException;
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
import com.bsl.common.utils.StringUtil;
import com.bsl.dao.JedisClient;
import com.bsl.mapper.BslBsPlanInfoMapper;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.mapper.BslSaleInfoDetailMapper;
import com.bsl.mapper.BslStockChangeDetailMapper;
import com.bsl.mapper.BslWxWasteWeightInfoMapper;
import com.bsl.pojo.BslBsPlanInfo;
import com.bsl.pojo.BslBsPlanInfoExample;
import com.bsl.pojo.BslBsPlanInfoExample.Criteria;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslSaleInfoDetail;
import com.bsl.pojo.BslSaleInfoDetailExample;
import com.bsl.pojo.BslStockChangeDetail;
import com.bsl.pojo.BslWxWasteWeightInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.dclprod.WxProdJsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 外协厂产品接收入库实现类
 * duk-20190319
 */
@Service
public class WxProdJsServiceImpl implements WxProdJsService {

	 @Autowired
	 private BslProductInfoMapper bslProductInfoMapper;
	 @Autowired	 
	 private BslBsPlanInfoMapper bslBsPlanInfoMapper;
	 @Autowired
	 private BslSaleInfoDetailMapper bslSaleInfoDetailMapper;
	 @Autowired
	 private BslWxWasteWeightInfoMapper bslWxWasteWeightInfoMapper;
	 @Autowired
	 private BslStockChangeDetailMapper bslStockChangeDetailMapper;
	 
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
	 * 初始化根据出库单号查询产品信息
	 */
	@Override
	public BSLResult getWxTzdJsServiceByCriteria(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslBsPlanInfoExample bslBsPlanInfoExample = new BslBsPlanInfoExample();
		Criteria criteria = bslBsPlanInfoExample.createCriteria();
		//查询外协厂可以接收的出库通知单
		criteria.andBsTypeEqualTo(DictItemOperation.收发标志_销售出库通知单);
		List<String> lists = new ArrayList<String>();
		lists.add(DictItemOperation.收发类别_待处理品处理发货);
		lists.add(DictItemOperation.收发类别_成品转场发货);
		lists.add(DictItemOperation.收发类别_废品转场发货);
		criteria.andBsFlagIn(lists);
		if(!StringUtils.isBlank(queryCriteria.getBsId())){
			criteria.andBsIdLike("%"+queryCriteria.getBsId()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getBsFlag())){
			criteria.andBsFlagEqualTo(queryCriteria.getBsFlag());
		}
		if(!StringUtils.isBlank(queryCriteria.getBsStatus())){
			criteria.andBsStatusEqualTo(queryCriteria.getBsStatus());
		}
		Date dateStart = new Date();
		Date dateEnd = new Date();
		if(!StringUtils.isBlank(queryCriteria.getStartDate())){
			try {
				dateStart = DictItemOperation.日期转换实例.parse(queryCriteria.getStartDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			try {
				dateStart = DictItemOperation.日期转换实例.parse("2018-01-01");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(!StringUtils.isBlank(queryCriteria.getEndDate())){
			try {
				dateEnd = DictItemOperation.日期转换实例.parse(queryCriteria.getEndDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			dateEnd = new Date();
		}
		criteria.andCrtDateBetween(dateStart,dateEnd);
		
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		if(StringUtils.isBlank(queryCriteria.getSort()) || StringUtils.isBlank(queryCriteria.getOrder())){
			bslBsPlanInfoExample.setOrderByClause("`bs_id` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){	
				bslBsPlanInfoExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslBsPlanInfo> bslBsPlanInfoList = bslBsPlanInfoMapper.selectByExample(bslBsPlanInfoExample);
		PageInfo<BslBsPlanInfo> pageInfo = new PageInfo<BslBsPlanInfo>(bslBsPlanInfoList);
		return BSLResult.ok(bslBsPlanInfoList,"wxProdJsServiceImpl","getWxTzdJsServiceByCriteria",pageInfo.getTotal(),bslBsPlanInfoList);
	}

	/**
	 * 根据出库单号查询废品出库详细流水信息
	 */
	@Override
	public BSLResult getWasteSaleInfoByBsId(String bsId) {
		BslSaleInfoDetailExample bslSaleInfoDetailExample = new BslSaleInfoDetailExample();
		com.bsl.pojo.BslSaleInfoDetailExample.Criteria criteria = bslSaleInfoDetailExample.createCriteria();
		criteria.andSalePlanIdEqualTo(bsId);
		criteria.andSaleStatusEqualTo(DictItemOperation.销售单出库标志_已发货);
		List<BslSaleInfoDetail> bslSaleInfoDetails = bslSaleInfoDetailMapper.selectByExample(bslSaleInfoDetailExample);
		PageInfo<BslSaleInfoDetail> pageInfo = new PageInfo<BslSaleInfoDetail>(bslSaleInfoDetails);
		return BSLResult.ok(bslSaleInfoDetails,"wxProdJsServiceImpl","getWasteSaleInfoByBsId",pageInfo.getTotal(),bslSaleInfoDetails);
	}

	/**
	 * 废品接收
	 */
	@Override
	public BSLResult updateJsWaste(String saleSerno, Float prodOutWeight, String inputUser) {
		BslSaleInfoDetail bslSaleInfoDetail = bslSaleInfoDetailMapper.selectByPrimaryKey(saleSerno);
		if(bslSaleInfoDetail == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "没有查询到该出库详细流水信息");
		}
		if(!DictItemOperation.销售单出库标志_已发货.equals(bslSaleInfoDetail.getSaleStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "该出库流水详情不是已发货无法入库");
		}
		//实际出库重量
		Float wasteOutWeight = bslSaleInfoDetail.getProdSumweight();
		//已接收重量
		Float wasteJsWeight = bslSaleInfoDetail.getProdJsweight();
		if(wasteJsWeight == null){
			wasteJsWeight = 0f;
		}
		//判断本次接收重量是否足够
		if(prodOutWeight + wasteJsWeight > wasteOutWeight){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "剩余可接收数量不足");
		}
		//校验完成开始接收
		
		//1-增加该废品库存重量
		//获取废品编号
		String prodId = bslSaleInfoDetail.getProdId();
		//查询废品信息
		BslWxWasteWeightInfo bslWxWasteWeightInfo = bslWxWasteWeightInfoMapper.selectByPrimaryKey(prodId);
		if(bslWxWasteWeightInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "根据废品类型未查询到废品信息！");
		}
		//增加废品库存
		bslWxWasteWeightInfo.setWasteWeight(bslWxWasteWeightInfo.getWasteWeight() + prodOutWeight);
		bslWxWasteWeightInfo.setWasteInWeight(bslWxWasteWeightInfo.getWasteInWeight() + prodOutWeight);
		int updateByPrimaryKeySelective = bslWxWasteWeightInfoMapper.updateByPrimaryKeySelective(bslWxWasteWeightInfo);
		if(updateByPrimaryKeySelective<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(updateByPrimaryKeySelective==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改废品库存信息失败");
		}
		
		//2-修改原流水已接收库存
		BslSaleInfoDetail bslSaleInfoDetailNew = bslSaleInfoDetail;
		bslSaleInfoDetailNew.setProdJsweight(prodOutWeight + wasteJsWeight);
		bslSaleInfoDetailNew.setInputuser(inputUser);
		bslSaleInfoDetailNew.setUpdDate(new Date());
		int updateSerno = bslSaleInfoDetailMapper.updateByPrimaryKeySelective(bslSaleInfoDetailNew);
		if(updateSerno<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(updateSerno==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改原流水已接收库存失败");
		}
		
		//3-修改原单号已接收库存
		BslBsPlanInfo bslBsPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(bslSaleInfoDetail.getSalePlanId());
		if(bslBsPlanInfo.getBsJsweight() == null){
			bslBsPlanInfo.setBsJsweight(0f);
		}
		bslBsPlanInfo.setBsJsweight(bslBsPlanInfo.getBsJsweight() + prodOutWeight);
		bslSaleInfoDetailNew.setInputuser(inputUser);
		bslSaleInfoDetailNew.setUpdDate(new Date());
		int updateBsId = bslBsPlanInfoMapper.updateByPrimaryKeySelective(bslBsPlanInfo);
		if(updateBsId<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(updateBsId==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改原单号已接收库存失败");
		}
		
		//4-记录库存变动信息
		BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
		bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
		bslStockChangeDetail.setProdId(prodId);//产品编号
		bslStockChangeDetail.setPlanSerno(bslSaleInfoDetail.getSalePlanId());//对应的生产指令单号
		bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_接收入库);//交易码
		bslStockChangeDetail.setProdType(DictItemOperation.产品类型_废品);//产品类型
		bslStockChangeDetail.setRubbishType(prodId);
		bslStockChangeDetail.setRubbishWeight(prodOutWeight);//重量
		bslStockChangeDetail.setInputuser(inputUser);//录入人
		bslStockChangeDetail.setPrice(bslSaleInfoDetail.getSalePrice());//价格
		bslStockChangeDetail.setCrtDate(new Date());
		bslStockChangeDetail.setRemark("废品接收入库");
		int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
		if(resultStock<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStock==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
		}
		
		return BSLResult.ok(bslSaleInfoDetail.getSalePlanId());
	}
	
	/**
	 * 废品接收退回
	 */
	@Override
	public BSLResult updateRebackWaste(String saleSerno, Float prodOutWeight, String inputUser) {
		BslSaleInfoDetail bslSaleInfoDetail = bslSaleInfoDetailMapper.selectByPrimaryKey(saleSerno);
		if(bslSaleInfoDetail == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "没有查询到该出库详细流水信息");
		}
		if(!DictItemOperation.销售单出库标志_已发货.equals(bslSaleInfoDetail.getSaleStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "该出库流水详情不是已发货无法退回");
		}
		//已接收重量
		Float wasteJsWeight = bslSaleInfoDetail.getProdJsweight();
		if(wasteJsWeight == null){
			wasteJsWeight = 0f;
		}
		//判断本次退回重量是否足够
		if(prodOutWeight > wasteJsWeight){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "退回重量不能大于已接收重量");
		}
		//校验完成开始接收
		
		//1-减少该废品库存重量
		//获取废品编号
		String prodId = bslSaleInfoDetail.getProdId();
		//查询废品信息
		BslWxWasteWeightInfo bslWxWasteWeightInfo = bslWxWasteWeightInfoMapper.selectByPrimaryKey(prodId);
		if(bslWxWasteWeightInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "根据废品类型未查询到废品信息！");
		}
		//判断库存废品重量是否足够退回
		if(prodOutWeight > bslWxWasteWeightInfo.getWasteWeight()){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "库存废品重量不足，无法退回！");
		}
		//减少废品库存
		bslWxWasteWeightInfo.setWasteWeight(bslWxWasteWeightInfo.getWasteWeight() - prodOutWeight);
		bslWxWasteWeightInfo.setWasteInWeight(bslWxWasteWeightInfo.getWasteInWeight() - prodOutWeight);
		int updateByPrimaryKeySelective = bslWxWasteWeightInfoMapper.updateByPrimaryKeySelective(bslWxWasteWeightInfo);
		if(updateByPrimaryKeySelective<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(updateByPrimaryKeySelective==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改废品库存信息失败");
		}
		
		//2-修改原流水已接收库存
		BslSaleInfoDetail bslSaleInfoDetailNew = bslSaleInfoDetail;
		bslSaleInfoDetailNew.setProdJsweight(wasteJsWeight - prodOutWeight);
		bslSaleInfoDetailNew.setInputuser(inputUser);
		bslSaleInfoDetailNew.setUpdDate(new Date());
		int updateSerno = bslSaleInfoDetailMapper.updateByPrimaryKeySelective(bslSaleInfoDetailNew);
		if(updateSerno<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(updateSerno==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改原流水已接收库存失败");
		}
		
		//3-修改原单号已接收库存
		BslBsPlanInfo bslBsPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(bslSaleInfoDetail.getSalePlanId());
		if(bslBsPlanInfo.getBsJsweight() == null){
			bslBsPlanInfo.setBsJsweight(0f);
		}
		bslBsPlanInfo.setBsJsweight(bslBsPlanInfo.getBsJsweight() - prodOutWeight);
		bslSaleInfoDetailNew.setInputuser(inputUser);
		bslSaleInfoDetailNew.setUpdDate(new Date());
		int updateBsId = bslBsPlanInfoMapper.updateByPrimaryKeySelective(bslBsPlanInfo);
		if(updateBsId<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(updateBsId==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改原单号已接收库存失败");
		}
		
		//4-记录库存变动信息
		BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
		bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
		bslStockChangeDetail.setProdId(prodId);//产品编号
		bslStockChangeDetail.setPlanSerno(bslSaleInfoDetail.getSalePlanId());//对应的生产指令单号
		bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_接收入库退回);//交易码
		bslStockChangeDetail.setProdType(DictItemOperation.产品类型_废品);//产品类型
		bslStockChangeDetail.setRubbishType(prodId);
		bslStockChangeDetail.setRubbishWeight(prodOutWeight);//重量
		bslStockChangeDetail.setInputuser(inputUser);//录入人
		bslStockChangeDetail.setPrice(bslSaleInfoDetail.getSalePrice());//价格
		bslStockChangeDetail.setCrtDate(new Date());
		bslStockChangeDetail.setRemark("废品接收入库退回");
		int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
		if(resultStock<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStock==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
		}
		
		return BSLResult.ok(bslSaleInfoDetail.getSalePlanId());
	}

	/**
	 * 根据单号等信息查询可以接收的产品/待处理品信息
	 */
	@Override
	public BSLResult getWxCanJsProds(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdStatusEqualTo(DictItemOperation.产品状态_已发货);
		//出库指令号
		if (!StringUtils.isBlank(queryCriteria.getProdOutPlan())) {
			criteria.andProdOutPlanLike(StringUtil.likeStr(queryCriteria.getProdOutPlan()));
		}
		//出库计划流水
		if (!StringUtils.isBlank(queryCriteria.getProdSaleSerno())) {
			criteria.andProdSaleSernoLike(StringUtil.likeStr(queryCriteria.getProdSaleSerno()));
		}
		//产品编号
		if (!StringUtils.isBlank(queryCriteria.getProdId())) {
			criteria.andProdIdLike(StringUtil.likeStr(queryCriteria.getProdId()));
		}
		//产品规格
		if(!StringUtils.isBlank(queryCriteria.getProdNorm())){
			criteria.andProdNormLike("%"+queryCriteria.getProdNorm()+"%");
		}
		//钢种
		if (!StringUtils.isBlank(queryCriteria.getProdMaterial())) {
			criteria.andProdMaterialEqualTo(queryCriteria.getProdMaterial());
		}
		//产品车次
		if (!StringUtils.isBlank(queryCriteria.getProdOutCarno())) {
			criteria.andProdOutCarnoLike(StringUtil.likeStr(queryCriteria.getProdOutCarno()));
		}
		
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		if(StringUtils.isBlank(queryCriteria.getSort()) || StringUtils.isBlank(queryCriteria.getOrder())){
			bslProductInfoExample.setOrderByClause("`prod_id` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){	
				bslProductInfoExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		PageInfo<BslProductInfo> pageInfo = new PageInfo<BslProductInfo>(bslProductInfos);
		return BSLResult.ok(bslProductInfos,"wxProdJsServiceImpl","getWxCanJsProds",pageInfo.getTotal(),bslProductInfos);
	}

	/**
	 * 根据单号等信息已经接收的产品信息
	 */
	@Override
	public BSLResult getWxAlreadyJsProds(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdStatusEqualTo(DictItemOperation.产品状态_已转场);
		//出库指令号
		if (!StringUtils.isBlank(queryCriteria.getProdOutPlan())) {
			criteria.andProdOutPlanLike(StringUtil.likeStr(queryCriteria.getProdOutPlan()));
		}
		//出库计划流水
		if (!StringUtils.isBlank(queryCriteria.getProdSaleSerno())) {
			criteria.andProdSaleSernoLike(StringUtil.likeStr(queryCriteria.getProdSaleSerno()));
		}
		//产品编号
		if (!StringUtils.isBlank(queryCriteria.getProdId())) {
			criteria.andProdIdLike(StringUtil.likeStr(queryCriteria.getProdId()));
		}
		//产品规格
		if(!StringUtils.isBlank(queryCriteria.getProdNorm())){
			criteria.andProdNormLike("%"+queryCriteria.getProdNorm()+"%");
		}
		//钢种
		if (!StringUtils.isBlank(queryCriteria.getProdMaterial())) {
			criteria.andProdMaterialEqualTo(queryCriteria.getProdMaterial());
		}
		//产品车次
		if (!StringUtils.isBlank(queryCriteria.getProdOutCarno())) {
			criteria.andProdOutCarnoLike(StringUtil.likeStr(queryCriteria.getProdOutCarno()));
		}
		
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		if(StringUtils.isBlank(queryCriteria.getSort()) || StringUtils.isBlank(queryCriteria.getOrder())){
			bslProductInfoExample.setOrderByClause("`prod_id` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){	
				bslProductInfoExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		PageInfo<BslProductInfo> pageInfo = new PageInfo<BslProductInfo>(bslProductInfos);
		return BSLResult.ok(bslProductInfos,"wxProdJsServiceImpl","getWxAlreadyJsProds",pageInfo.getTotal(),bslProductInfos);
	}

	/**
	 * 批量接收转场产品
	 */
	@Override
	public BSLResult updateWxJsProdsBSLResult(String prodOutPlan, String prodCheckuser, String[] arrays) {
		//校验产品数量
		if(arrays.length<=0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"没有需要出库的产品信息！");
		}
		//校验通知单属性
		BslBsPlanInfo bslBsPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(prodOutPlan);
		if(!DictItemOperation.收发类别_成品转场发货.equals(bslBsPlanInfo.getBsFlag())
				&& !DictItemOperation.收发类别_待处理品处理发货.equals(bslBsPlanInfo.getBsFlag())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "非成品转场通知单或待处理品处理通知单无法接收");
		}
		BslProductInfo bslProductInfo;
		BslProductInfo wxBslProductInfo;
		int jsNum = 0;
		Float jsWeight = 0f;
		for (String prodIdTmp : arrays) {
			//开始接收
			//获取产品信息
			bslProductInfo = bslProductInfoMapper.selectByPrimaryKey(prodIdTmp);
			if(bslProductInfo == null){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"没有查询到该产品信息:"+prodIdTmp);
			}
			if(!DictItemOperation.产品状态_已发货.equals(bslProductInfo.getProdStatus())){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"非已发货的产品无法接收:"+prodIdTmp);
			}
			//开始转场
			wxBslProductInfo = new BslProductInfo();
			//1-原已发货状态的产品信息状态更新为已转场
			bslProductInfo.setProdStatus(DictItemOperation.产品状态_已转场);
			bslProductInfo.setUpdDate(new Date());
			bslProductInfoMapper.updateByPrimaryKey(bslProductInfo);
			//2-创建一个转场入库的新产品
			wxBslProductInfo = bslProductInfo;
			wxBslProductInfo.setProdId("ZC"+bslProductInfo.getProdId());
			wxBslProductInfo.setProdSourceCompany(null);
			wxBslProductInfo.setProdStatus(DictItemOperation.产品状态_已入库);
			wxBslProductInfo.setProdDclFlag(DictItemOperation.产品外协厂标志_转场);
			wxBslProductInfo.setProdOutPlan(null);
			wxBslProductInfo.setProdSaleSerno(null);
			wxBslProductInfo.setProdOutCarno(null);
			wxBslProductInfo.setProdOutDate(null);
			wxBslProductInfo.setProdFhck(prodOutPlan);
			wxBslProductInfo.setProdInputuser(prodCheckuser);
			wxBslProductInfo.setProdCheckuser(null);
			wxBslProductInfo.setProdRuc(DictItemOperation.入库仓库_委外仓);
			wxBslProductInfo.setUpdDate(null);
			wxBslProductInfo.setCrtDate(new Date());
			wxBslProductInfo.setRemark("");
			bslProductInfoMapper.insert(wxBslProductInfo);
			//3-增加js数量和重量
			jsNum ++;
			jsWeight += bslProductInfo.getProdRelWeight();
		}
		//接收完毕记录总接收数量和重量
		if(bslBsPlanInfo.getBsJsnum() == null){
			bslBsPlanInfo.setBsJsnum(jsNum);
		}else{
			bslBsPlanInfo.setBsJsnum(jsNum + bslBsPlanInfo.getBsJsnum());
		}
		if(bslBsPlanInfo.getBsJsweight() == null){
			bslBsPlanInfo.setBsJsweight(jsWeight);
		}else{
			bslBsPlanInfo.setBsJsweight(jsWeight + bslBsPlanInfo.getBsJsweight());
		}
		bslBsPlanInfo.setUpdDate(new Date());
		int updateBsId = bslBsPlanInfoMapper.updateByPrimaryKeySelective(bslBsPlanInfo);
		if(updateBsId<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(updateBsId==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改原单号已接收库存失败");
		}
		
		return BSLResult.ok(prodOutPlan);
	}

	/**
	 * 退回接收产品
	 */
	@Override
	public BSLResult updateWxRebackProdsBSLResult(String prodId, String checkuser) {
		String zcProdId = "ZC"+prodId;
		//获取产品信息
		BslProductInfo bslProductInfo = bslProductInfoMapper.selectByPrimaryKey(prodId);
		if(bslProductInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"没有查询到该产品信息:"+prodId);
		}
		if(!DictItemOperation.产品状态_已转场.equals(bslProductInfo.getProdStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"非已转场的产品无法退回:"+prodId);
		}
		//校验通知单属性
		BslBsPlanInfo bslBsPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(bslProductInfo.getProdOutPlan());
		//校验接收的产品信息
		BslProductInfo bslProductInfoZc = bslProductInfoMapper.selectByPrimaryKey(zcProdId);
		if(bslProductInfoZc == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"没有查询到转场后的产品信息:"+zcProdId);
		}
		if(!DictItemOperation.产品状态_已入库.equals(bslProductInfoZc.getProdStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"已接收的产品不在库，无法退回:"+zcProdId);
		}
		//校验完毕开始退回
		//1-原已转场状态的产品信息状态更新为已发货
		bslProductInfo.setProdStatus(DictItemOperation.产品状态_已发货);
		bslProductInfo.setUpdDate(new Date());
		bslProductInfoMapper.updateByPrimaryKey(bslProductInfo);
		//2-删除接收的转场产品信息
		bslProductInfoMapper.deleteByPrimaryKey(zcProdId);
		//3-记录库存变动
		BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
		bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
		bslStockChangeDetail.setProdId(zcProdId);//产品编号
		bslStockChangeDetail.setPlanSerno(bslProductInfo.getProdOutPlan());//对应的生产指令单号
		bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_删除);//交易码
		bslStockChangeDetail.setProdType(bslProductInfo.getProdType());//产品类型
		bslStockChangeDetail.setRubbishWeight(bslProductInfo.getProdRelWeight());//重量
		bslStockChangeDetail.setInputuser(checkuser);//录入人
		bslStockChangeDetail.setPrice(0f);//价格
		bslStockChangeDetail.setCrtDate(new Date());
		bslStockChangeDetail.setRemark("接收入库退回删除");
		int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
		if(resultStock<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStock==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
		}
		//4-减少已接收数量和重量
		bslBsPlanInfo.setBsJsnum(bslBsPlanInfo.getBsJsnum()-1);
		bslBsPlanInfo.setBsJsweight(bslBsPlanInfo.getBsJsweight()-bslProductInfo.getProdRelWeight());
		bslBsPlanInfo.setUpdDate(new Date());
		int updateBsId = bslBsPlanInfoMapper.updateByPrimaryKeySelective(bslBsPlanInfo);
		if(updateBsId<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(updateBsId==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改原单号已接收库存失败");
		}
		
		return BSLResult.ok(zcProdId);
	}

	
	
}
