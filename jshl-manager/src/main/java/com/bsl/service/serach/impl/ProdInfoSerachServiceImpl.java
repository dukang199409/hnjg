package com.bsl.service.serach.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.mapper.BslProductInfoHMapper;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslProductInfoExample.Criteria;
import com.bsl.pojo.BslProductInfoH;
import com.bsl.pojo.BslProductInfoHExample;
import com.bsl.select.DictItemOperation;
import com.bsl.select.QueryCriteria;
import com.bsl.service.serach.ProdInfoSerachService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 产品查询实现类
 * duk-20190319
 */
@Service
public class ProdInfoSerachServiceImpl implements ProdInfoSerachService {

	@Autowired	 
	BslProductInfoMapper bslProductInfoMapper;
	
	@Autowired	 
	BslProductInfoHMapper bslProductInfoHMapper;

	/**
	 * 初始化查询所有产品信息 
	 */
	@Override
	public EasyUIDataGridResult getAllInfoService(Integer page, Integer rows) {
		//查询条件状态 
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		//分页处理
		PageHelper.startPage(page,rows);
		//调用sql查询
		bslProductInfoExample.setOrderByClause("`crt_date` desc,`prod_type` asc,`prod_id` desc,`prod_plan_no` desc");
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(bslProductInfos);
		//取记录总条数
		PageInfo<BslProductInfo> pageInfo = new PageInfo<>(bslProductInfos);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("prodInfoSerachServiceImpl");
		result.setMethodName("getInfoByCriteriaService");
		return result;
	}

	/**
	 *根据条件查询产品信息 
	 */
	@Override
	public BSLResult getInfoByCriteriaService(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslProductInfoExample.createCriteria();
		
		if(!StringUtils.isBlank(queryCriteria.getProdPlanNo())){
			criteria.andProdPlanNoLike("%"+ queryCriteria.getProdPlanNo()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdLuno())){
			criteria.andProdLunoLike("%"+ queryCriteria.getProdLuno()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdOutPlan())){
			criteria.andProdOutPlanLike("%"+ queryCriteria.getProdOutPlan()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdId())){
			criteria.andProdIdLike("%"+ queryCriteria.getProdId()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdType())){
			criteria.andProdTypeEqualTo(queryCriteria.getProdType());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdParentNo())){
			criteria.andProdParentNoLike("%"+queryCriteria.getProdParentNo()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdNorm())){
			criteria.andProdNormLike("%"+queryCriteria.getProdNorm()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdStatus())){
			criteria.andProdStatusEqualTo(queryCriteria.getProdStatus());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdMaterial())){
			criteria.andProdMaterialEqualTo(queryCriteria.getProdMaterial());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdSource())){
			criteria.andProdSourceEqualTo(queryCriteria.getProdSource());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdMakeJz())){
			criteria.andProdMakeJzEqualTo(queryCriteria.getProdMakeJz());
		}
		//是否待处理品入库
		if (!StringUtils.isBlank(queryCriteria.getProdDclFlag())) {
			criteria.andProdDclFlagEqualTo(queryCriteria.getProdDclFlag());
		}
		//开始日期结束日期
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
			bslProductInfoExample.setOrderByClause("`crt_date` desc,`prod_type` asc,`prod_id` desc,`prod_plan_no` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){
				bslProductInfoExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		PageInfo<BslProductInfo> pageInfo = new PageInfo<BslProductInfo>(bslProductInfos);
		return BSLResult.ok(bslProductInfos,"prodInfoSerachServiceImpl","getInfoByCriteriaService",pageInfo.getTotal(),bslProductInfos);
	}

	/**
	 * 初始化查询所有在库产品信息 
	 */
	@Override
	public EasyUIDataGridResult getAllInInfoService(Integer page, Integer rows) {
		//查询条件状态 
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		Criteria createCriteria = bslProductInfoExample.createCriteria();
		createCriteria.andProdStatusEqualTo(DictItemOperation.产品状态_已入库);
		//分页处理
		PageHelper.startPage(page,rows);
		//调用sql查询
		bslProductInfoExample.setOrderByClause("`crt_date` desc,`prod_type` asc,`prod_id` desc,`prod_plan_no` desc");
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(bslProductInfos);
		//取记录总条数
		PageInfo<BslProductInfo> pageInfo = new PageInfo<>(bslProductInfos);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("prodInfoSerachServiceImpl");
		result.setMethodName("getInInfoByCriteriaService");
		return result;
	}

	/**
	 * 根据条件查询所有在库产品信息 
	 */
	@Override
	public BSLResult getInInfoByCriteriaService(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdStatusEqualTo(DictItemOperation.产品状态_已入库);
		if(!StringUtils.isBlank(queryCriteria.getProdPlanNo())){
			criteria.andProdPlanNoLike("%"+ queryCriteria.getProdPlanNo()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdLuno())){
			criteria.andProdLunoLike("%"+ queryCriteria.getProdLuno()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdId())){
			criteria.andProdIdLike("%"+ queryCriteria.getProdId()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdType())){
			criteria.andProdTypeEqualTo(queryCriteria.getProdType());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdParentNo())){
			criteria.andProdParentNoLike("%"+queryCriteria.getProdParentNo()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdNorm())){
			criteria.andProdNormLike("%"+queryCriteria.getProdNorm()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdDclFlag())){
			criteria.andProdDclFlagEqualTo(queryCriteria.getProdDclFlag());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdMaterial())){
			criteria.andProdMaterialEqualTo(queryCriteria.getProdMaterial());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdSource())){
			criteria.andProdSourceEqualTo(queryCriteria.getProdSource());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdMakeJz())){
			criteria.andProdMakeJzEqualTo(queryCriteria.getProdMakeJz());
		}
		//开始日期结束日期
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
			bslProductInfoExample.setOrderByClause("`crt_date` desc,`prod_type` asc,`prod_id` desc,`prod_plan_no` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){
				bslProductInfoExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		PageInfo<BslProductInfo> pageInfo = new PageInfo<BslProductInfo>(bslProductInfos);
		return BSLResult.ok(bslProductInfos,"prodInfoSerachServiceImpl","getInInfoByCriteriaService",pageInfo.getTotal(),bslProductInfos);
	}

	/**
	 * 根据条件查询历史产品信息
	 */
	@Override
	public BSLResult getProdInfoHList(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslProductInfoHExample bslProductInfoHExample = new BslProductInfoHExample();
		com.bsl.pojo.BslProductInfoHExample.Criteria criteria = bslProductInfoHExample.createCriteria();
		
		if(!StringUtils.isBlank(queryCriteria.getProdPlanNo())){
			criteria.andProdPlanNoLike("%"+ queryCriteria.getProdPlanNo()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdLuno())){
			criteria.andProdLunoLike("%"+ queryCriteria.getProdLuno()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdOutPlan())){
			criteria.andProdOutPlanLike("%"+ queryCriteria.getProdOutPlan()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdId())){
			criteria.andProdIdLike("%"+ queryCriteria.getProdId()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdType())){
			criteria.andProdTypeEqualTo(queryCriteria.getProdType());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdParentNo())){
			criteria.andProdParentNoLike("%"+queryCriteria.getProdParentNo()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdNorm())){
			criteria.andProdNormLike("%"+queryCriteria.getProdNorm()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdStatus())){
			criteria.andProdStatusEqualTo(queryCriteria.getProdStatus());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdMaterial())){
			criteria.andProdMaterialEqualTo(queryCriteria.getProdMaterial());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdSource())){
			criteria.andProdSourceEqualTo(queryCriteria.getProdSource());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdMakeJz())){
			criteria.andProdMakeJzEqualTo(queryCriteria.getProdMakeJz());
		}
		//是否待处理品入库
		if (!StringUtils.isBlank(queryCriteria.getProdDclFlag())) {
			criteria.andProdDclFlagEqualTo(queryCriteria.getProdDclFlag());
		}
		//开始日期结束日期
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
			bslProductInfoHExample.setOrderByClause("`crt_date` desc,`prod_type` asc,`prod_id` desc,`prod_plan_no` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){
				bslProductInfoHExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslProductInfoH> bslProductInfos = bslProductInfoHMapper.selectByExample(bslProductInfoHExample);
		PageInfo<BslProductInfoH> pageInfo = new PageInfo<BslProductInfoH>(bslProductInfos);
		return BSLResult.ok(bslProductInfos,"prodInfoSerachServiceImpl","getInfoByCriteriaService",pageInfo.getTotal(),bslProductInfos);
	}

}