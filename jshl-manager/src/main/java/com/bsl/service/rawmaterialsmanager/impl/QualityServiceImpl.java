package com.bsl.service.rawmaterialsmanager.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bsl.common.pojo.BSLException;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.mapper.BslLunoQualityMapper;
import com.bsl.pojo.BslLunoQuality;
import com.bsl.pojo.BslLunoQualityExample;
import com.bsl.pojo.BslLunoQualityExample.Criteria;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.rawmaterialsmanager.QualityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 质量信息管理实现类
 * duk-20190319
 */
@Service
public class QualityServiceImpl implements QualityService{
	
	 @Autowired	 
	 BslLunoQualityMapper bslLunoQualityMapper;
	 
	 /**
	 * 初始化查询所有质量信息
	 */
	@Override
	public EasyUIDataGridResult getQualityService(Integer page, Integer rows) {
		BslLunoQualityExample bslLunoQualityExample = new BslLunoQualityExample();
		// 分页处理
		PageHelper.startPage(page, rows);
		bslLunoQualityExample.setOrderByClause("`lu_id` desc");
		List<BslLunoQuality> bslBsPlanInfoList = bslLunoQualityMapper.selectByExample(bslLunoQualityExample);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(bslBsPlanInfoList);
		// 取记录总条数
		PageInfo<BslLunoQuality> pageInfo = new PageInfo<BslLunoQuality>(bslBsPlanInfoList);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("qualityServiceImpl");//类名
		result.setMethodName("getQualityService");
		return result;
	}
	 
	/**
	 * 获取符合条件的质量信息
	 */
	@Override
	public BSLResult getQualityService(QueryCriteria queryCriteria) {
		 
		//创建查询的实例，并赋值
		BslLunoQualityExample bslLunoQualityExample = new BslLunoQualityExample();
		Criteria criteria = bslLunoQualityExample.createCriteria();
		
		if(!StringUtils.isBlank(queryCriteria.getLuId())){
			criteria.andLuIdLike("%"+queryCriteria.getLuId()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getLuCompany())){
			criteria.andLuCompanyLike("%"+queryCriteria.getLuCompany()+"%");
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
			bslLunoQualityExample.setOrderByClause("`lu_id` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){
				bslLunoQualityExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslLunoQuality> bslBsPlanInfoList = bslLunoQualityMapper.selectByExample(bslLunoQualityExample);
		PageInfo<BslLunoQuality> pageInfo = new PageInfo<BslLunoQuality>(bslBsPlanInfoList);
		return BSLResult.ok(bslBsPlanInfoList,"qualityServiceImpl","getQualityService",pageInfo.getTotal(),bslBsPlanInfoList);
	}

	/**
	 * 新增质量信息
	 */
	@Override
	public BSLResult addQualityInfo(BslLunoQuality bslLunoQuality) {
		BslLunoQuality bslLunoQualityOld = bslLunoQualityMapper.selectByPrimaryKey(bslLunoQuality.getLuId());
		if(bslLunoQualityOld != null){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"已经有该炉号的质量信息，无需新增！");
		}
		bslLunoQuality.setCrtDate(new Date());//创建日期当天
		int result = bslLunoQualityMapper.insert(bslLunoQuality);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增失败");
		}
		return BSLResult.ok(bslLunoQuality.getLuId());
	}
	
	/**
	 * 修改质量信息
	 */
	@Override
	public BSLResult updateQualityInfo(BslLunoQuality bslLunoQuality) {
		//修改之前先查询原信息
		BslLunoQuality bslLunoQualityOld = bslLunoQualityMapper.selectByPrimaryKey(bslLunoQuality.getLuId());
		if(bslLunoQualityOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"根据炉号未查到质量信息！");
		}
		bslLunoQuality.setCrtDate(bslLunoQualityOld.getCrtDate());
		bslLunoQuality.setUpdDate(new Date());//修改日期当天
		int result = bslLunoQualityMapper.updateByPrimaryKey(bslLunoQuality);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的修改记录");
		}		
		return BSLResult.ok(bslLunoQuality.getLuId());
	}

	/**
	 * 删除质量信息
	 */
	@Override
	public BSLResult deleteQualityInfo(String luId) {
		//删除之前先校验状态以及是不是存在
		BslLunoQuality bslLunoQualityOld = bslLunoQualityMapper.selectByPrimaryKey(luId);
		if(bslLunoQualityOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"根据炉号未查到质量信息！");
		}
		
		//删除操作
		int result = bslLunoQualityMapper.deleteByPrimaryKey(luId);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的删除记录");
		}		
		return BSLResult.ok(luId);
	}

}
