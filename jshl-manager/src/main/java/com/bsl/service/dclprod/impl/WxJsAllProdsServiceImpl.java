package com.bsl.service.dclprod.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bsl.common.utils.BSLResult;
import com.bsl.common.utils.StringUtil;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.mapper.BslSaleInfoDetailMapper;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslProductInfoExample.Criteria;
import com.bsl.pojo.BslSaleInfoDetail;
import com.bsl.select.DictItemOperation;
import com.bsl.select.QueryCriteria;
import com.bsl.service.dclprod.WxJsAllProdsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 查询所有外协厂转场接收的产品/待处理品信息
 * duk-20190319
 */
@Service
public class WxJsAllProdsServiceImpl implements WxJsAllProdsService {

	 @Autowired
	 private BslProductInfoMapper bslProductInfoMapper;
	 @Autowired
	 private BslSaleInfoDetailMapper bslSaleInfoDetailMapper;

	/**
	 * 查询所有外协厂转场接收的产品/待处理品信息
	 */
	@Override
	public BSLResult getWxJsAllProds(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslProductInfoExample bslDclProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslDclProductInfoExample.createCriteria();
		criteria.andProdDclFlagEqualTo(DictItemOperation.产品外协厂标志_转场);
		//产品编号
		if (!StringUtils.isBlank(queryCriteria.getProdId())) {
			criteria.andProdIdLike(StringUtil.likeStr(queryCriteria.getProdId()));
		}
		//炉号
		if (!StringUtils.isBlank(queryCriteria.getProdLuno())) {
			criteria.andProdLunoLike(StringUtil.likeStr(queryCriteria.getProdLuno()));
		}
		//产品类型
		if(!StringUtils.isBlank(queryCriteria.getProdType())){
			criteria.andProdTypeEqualTo(queryCriteria.getProdType());
		}
		//产品状态
		if (!StringUtils.isBlank(queryCriteria.getProdStatus())) {
			criteria.andProdStatusEqualTo(queryCriteria.getProdStatus());
		}
		//钢种
		if (!StringUtils.isBlank(queryCriteria.getProdMaterial())) {
			criteria.andProdMaterialEqualTo(queryCriteria.getProdMaterial());
		}
		//规格
		if(!StringUtils.isBlank(queryCriteria.getProdNorm())){
			criteria.andProdNormLike("%"+queryCriteria.getProdNorm()+"%");
		}
		//接收编号
		if (!StringUtils.isBlank(queryCriteria.getProdFhck())) {
			criteria.andProdFhckLike(StringUtil.likeStr(queryCriteria.getProdFhck()));
		}
		//出库编号
		if(!StringUtils.isBlank(queryCriteria.getProdOutPlan())){
			criteria.andProdOutPlanLike("%"+ queryCriteria.getProdOutPlan()+"%");
		}
		
		//开始日期结束日期
		//起始日期 结束日期
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
			bslDclProductInfoExample.setOrderByClause("`crt_date` desc,`prod_fhck` desc,`prod_id` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){	
				bslDclProductInfoExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslDclProductInfoExample);
		PageInfo<BslProductInfo> pageInfo = new PageInfo<BslProductInfo>(bslProductInfos);
		return BSLResult.ok(bslProductInfos,"wxJsAllProdsServiceImpl","getWxJsAllProds",pageInfo.getTotal(),bslProductInfos);
	}

	/**
	 * 查询所有外协厂转场接收的废品信息
	 */
	@Override
	public BSLResult getWxJsAllWastes(QueryCriteria queryCriteria) {
		if (!StringUtils.isBlank(queryCriteria.getSalePlanId())) {
			queryCriteria.setSalePlanId(StringUtil.likeStr(queryCriteria.getSalePlanId()));
		}else{
			queryCriteria.setSalePlanId(null);
		}
		if(!StringUtils.isBlank(queryCriteria.getSaleSerno())){
			queryCriteria.setSaleSerno(StringUtil.likeStr(queryCriteria.getSaleSerno()));
		}else{
			queryCriteria.setSaleSerno(null);
		}
		if(!StringUtils.isBlank(queryCriteria.getWasteType())){
			queryCriteria.setProdId(queryCriteria.getWasteType());
		}else{
			queryCriteria.setProdId(null);
		}
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
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		if(StringUtils.isBlank(queryCriteria.getSort()) || StringUtils.isBlank(queryCriteria.getOrder())){
			queryCriteria.setOrderByClause("`crt_date` asc,`sale_plan_id` desc,`saleSerno` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){
				queryCriteria.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslSaleInfoDetail> bslSaleInfoDetails = bslSaleInfoDetailMapper.getAllJsWastes(queryCriteria);
		PageInfo<BslSaleInfoDetail> pageInfo = new PageInfo<BslSaleInfoDetail>(bslSaleInfoDetails);
		return BSLResult.ok(bslSaleInfoDetails,"wxJsAllProdsServiceImpl","getWxJsAllWastes",pageInfo.getTotal(),bslSaleInfoDetails);
		
	}

	
}
