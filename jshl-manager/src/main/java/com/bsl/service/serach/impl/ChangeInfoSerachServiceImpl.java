package com.bsl.service.serach.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.mapper.BslChangeStatusRecordMapper;
import com.bsl.mapper.BslStockChangeDetailHMapper;
import com.bsl.mapper.BslStockChangeDetailMapper;
import com.bsl.pojo.BslChangeStatusRecord;
import com.bsl.pojo.BslChangeStatusRecordExample;
import com.bsl.pojo.BslStockChangeDetail;
import com.bsl.pojo.BslStockChangeDetailExample;
import com.bsl.pojo.BslStockChangeDetailExample.Criteria;
import com.bsl.pojo.BslStockChangeDetailH;
import com.bsl.pojo.BslStockChangeDetailHExample;
import com.bsl.select.DictItemOperation;
import com.bsl.select.QueryCriteria;
import com.bsl.service.serach.ChangeInfoSerachService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 变动查询实现类
 * duk-20190319
 */
@Service
public class ChangeInfoSerachServiceImpl implements ChangeInfoSerachService {

	@Autowired	 
	BslStockChangeDetailMapper bslStockChangeDetailMapper;
	
	@Autowired	 
	BslStockChangeDetailHMapper bslStockChangeDetailHMapper;
	
	@Autowired	 
	BslChangeStatusRecordMapper bslChangeStatusRecordMapper;

	/**
	 * 初始化查询所有库存变动信息 
	 */
	@Override
	public EasyUIDataGridResult getAllInfoService(Integer page, Integer rows) {
		//查询条件状态 
		BslStockChangeDetailExample bslStockChangeDetailExample  = new BslStockChangeDetailExample();
		//分页处理
		PageHelper.startPage(page,rows);
		//调用sql查询
		bslStockChangeDetailExample.setOrderByClause("`trans_serno` desc,`prod_id` desc,`plan_serno` desc");
		List<BslStockChangeDetail> bslStockChangeDetails = bslStockChangeDetailMapper.selectByExample(bslStockChangeDetailExample);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(bslStockChangeDetails);
		//取记录总条数
		PageInfo<BslStockChangeDetail> pageInfo = new PageInfo<>(bslStockChangeDetails);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("changeInfoSerachServiceImpl");
		result.setMethodName("getInfoByCriteriaService");
		return result;
	}

	/**
	 *根据条件查询库存变动信息 
	 */
	@Override
	public BSLResult getInfoByCriteriaService(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslStockChangeDetailExample bslStockChangeDetailExample  = new BslStockChangeDetailExample();
		Criteria criteria = bslStockChangeDetailExample.createCriteria();
		
		if(!StringUtils.isBlank(queryCriteria.getTransSerno())){
			criteria.andTransSernoLike("%"+ queryCriteria.getTransSerno()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdId())){
			criteria.andProdIdLike("%"+ queryCriteria.getProdId()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdType())){
			criteria.andProdTypeEqualTo(queryCriteria.getProdType());
		}
		if(!StringUtils.isBlank(queryCriteria.getPlanSerno())){
			criteria.andPlanSernoLike("%"+queryCriteria.getPlanSerno()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdOriId())){
			criteria.andProdOriIdLike("%"+queryCriteria.getProdOriId()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getTransCode())){
			criteria.andTransCodeEqualTo(queryCriteria.getTransCode());
		}
		if(!StringUtils.isBlank(queryCriteria.getRubbishType())){
			criteria.andRubbishTypeEqualTo(queryCriteria.getRubbishType());
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
			bslStockChangeDetailExample.setOrderByClause("`trans_serno` desc,`prod_id` desc,`plan_serno` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){
				bslStockChangeDetailExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslStockChangeDetail> bslStockChangeDetails = bslStockChangeDetailMapper.selectByExample(bslStockChangeDetailExample);
		PageInfo<BslStockChangeDetail> pageInfo = new PageInfo<BslStockChangeDetail>(bslStockChangeDetails);
		return BSLResult.ok(bslStockChangeDetails,"changeInfoSerachServiceImpl","getInfoByCriteriaService",pageInfo.getTotal(),bslStockChangeDetails);
	}
	
	/**
	 *根据条件查询库存变动历史信息 
	 */
	@Override
	public BSLResult getInfoByCriteriaHService(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslStockChangeDetailHExample bslStockChangeDetailExample  = new BslStockChangeDetailHExample();
		com.bsl.pojo.BslStockChangeDetailHExample.Criteria criteria = bslStockChangeDetailExample.createCriteria();
		
		if(!StringUtils.isBlank(queryCriteria.getTransSerno())){
			criteria.andTransSernoLike("%"+ queryCriteria.getTransSerno()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdId())){
			criteria.andProdIdLike("%"+ queryCriteria.getProdId()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdType())){
			criteria.andProdTypeEqualTo(queryCriteria.getProdType());
		}
		if(!StringUtils.isBlank(queryCriteria.getPlanSerno())){
			criteria.andPlanSernoLike("%"+queryCriteria.getPlanSerno()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdOriId())){
			criteria.andProdOriIdLike("%"+queryCriteria.getProdOriId()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getTransCode())){
			criteria.andTransCodeEqualTo(queryCriteria.getTransCode());
		}
		if(!StringUtils.isBlank(queryCriteria.getRubbishType())){
			criteria.andRubbishTypeEqualTo(queryCriteria.getRubbishType());
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
			bslStockChangeDetailExample.setOrderByClause("`trans_serno` desc,`prod_id` desc,`plan_serno` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){
				bslStockChangeDetailExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslStockChangeDetailH> bslStockChangeDetails = bslStockChangeDetailHMapper.selectByExample(bslStockChangeDetailExample);
		PageInfo<BslStockChangeDetailH> pageInfo = new PageInfo<BslStockChangeDetailH>(bslStockChangeDetails);
		return BSLResult.ok(bslStockChangeDetails,"changeInfoSerachServiceImpl","getInfoByCriteriaHService",pageInfo.getTotal(),bslStockChangeDetails);
	}


	/**
	 * 初始化查询所有状态变动信息 
	 */
	@Override
	public EasyUIDataGridResult getAllStatusChangeInfoService(Integer page, Integer rows) {
		//查询条件状态 
		BslChangeStatusRecordExample bslChangeStatusRecordExample = new BslChangeStatusRecordExample();
		//分页处理
		PageHelper.startPage(page,rows);
		//调用sql查询
		bslChangeStatusRecordExample.setOrderByClause("`change_serno` desc");
		List<BslChangeStatusRecord> bslChangeStatusRecords = bslChangeStatusRecordMapper.selectByExample(bslChangeStatusRecordExample);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(bslChangeStatusRecords);
		//取记录总条数
		PageInfo<BslChangeStatusRecord> pageInfo = new PageInfo<>(bslChangeStatusRecords);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("changeInfoSerachServiceImpl");
		result.setMethodName("getStatusChangeInfoService");
		return result;
	}

	/**
	 *根据条件查询状态变动信息 
	 */
	@Override
	public BSLResult getStatusChangeInfoService(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslChangeStatusRecordExample bslChangeStatusRecordExample = new BslChangeStatusRecordExample();
		com.bsl.pojo.BslChangeStatusRecordExample.Criteria criteria = bslChangeStatusRecordExample.createCriteria();
		
		if(!StringUtils.isBlank(queryCriteria.getChangeSerno())){
			criteria.andChangeSernoLike("%"+ queryCriteria.getChangeSerno()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getChangeProdId())){
			criteria.andChangeProdIdLike("%"+ queryCriteria.getChangeProdId()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getChangeType())){
			criteria.andChangeTypeEqualTo(queryCriteria.getChangeType());
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
			bslChangeStatusRecordExample.setOrderByClause("`change_serno` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){
				bslChangeStatusRecordExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslChangeStatusRecord> bslChangeStatusRecords = bslChangeStatusRecordMapper.selectByExample(bslChangeStatusRecordExample);
		PageInfo<BslChangeStatusRecord> pageInfo = new PageInfo<BslChangeStatusRecord>(bslChangeStatusRecords);
		return BSLResult.ok(bslChangeStatusRecords,"changeInfoSerachServiceImpl","getStatusChangeInfoService",pageInfo.getTotal(),bslChangeStatusRecords);
	}

}