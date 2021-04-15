package com.bsl.service.rawmaterialsmanager.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bsl.common.pojo.BSLException;
import com.bsl.common.utils.BSLResult;
import com.bsl.mapper.BslSendCheckInfoMapper;
import com.bsl.pojo.BslSendCheckInfo;
import com.bsl.pojo.BslSendCheckInfoExample;
import com.bsl.pojo.BslSendCheckInfoExample.Criteria;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.rawmaterialsmanager.SendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 送检信息管理实现类
 * duk-20190319
 */
@Service
public class SendServiceImpl implements SendService{
	
	 @Autowired	 
	 BslSendCheckInfoMapper bslSendCheckInfoMapper;
	 
	/**
	 * 获取符合条件的送检信息
	 */
	@Override
	public BSLResult getSendService(QueryCriteria queryCriteria) {
		 
		//创建查询的实例，并赋值
		BslSendCheckInfoExample sendCheckInfoExample = new BslSendCheckInfoExample();
		Criteria criteria = sendCheckInfoExample.createCriteria();
		
		if(!StringUtils.isBlank(queryCriteria.getLuId())){
			criteria.andLuIdLike("%"+queryCriteria.getLuId()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getSendFlag())){
			criteria.andSendFlagEqualTo(queryCriteria.getSendFlag());
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
		criteria.andSendDateBetween(dateStart,dateEnd);
		
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		if(StringUtils.isBlank(queryCriteria.getSort()) || StringUtils.isBlank(queryCriteria.getOrder())){
			sendCheckInfoExample.setOrderByClause("`send_date` desc,`lu_id` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){
				sendCheckInfoExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslSendCheckInfo> bslBsPlanInfoList = bslSendCheckInfoMapper.selectByExample(sendCheckInfoExample);
		PageInfo<BslSendCheckInfo> pageInfo = new PageInfo<BslSendCheckInfo>(bslBsPlanInfoList);
		return BSLResult.ok(bslBsPlanInfoList,"sendServiceImpl","getSendService",pageInfo.getTotal(),bslBsPlanInfoList);
	}

	/**
	 * 新增送检信息
	 */
	@Override
	public BSLResult addSendInfo(BslSendCheckInfo bslSendCheckInfo) {
		BslSendCheckInfo sendCheckInfo = bslSendCheckInfoMapper.selectByPrimaryKey(bslSendCheckInfo.getLuId());
		if(sendCheckInfo != null){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"已存在该炉号的送检信息，无需新增！");
		}
		bslSendCheckInfo.setSendDate(new Date());//创建日期当天
		int result = bslSendCheckInfoMapper.insert(bslSendCheckInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增失败");
		}
		return BSLResult.ok(bslSendCheckInfo.getLuId());
	}
	
	/**
	 * 修改质量信息
	 */
	@Override
	public BSLResult updateSendInfo(BslSendCheckInfo bslSendCheckInfo) {
		//修改之前先查询原信息
		BslSendCheckInfo sendCheckInfoOld = bslSendCheckInfoMapper.selectByPrimaryKey(bslSendCheckInfo.getLuId());
		if(sendCheckInfoOld == null){
			bslSendCheckInfo.setSendDate(new Date());//创建日期当天
			int result = bslSendCheckInfoMapper.insert(bslSendCheckInfo);
			if(result<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(result==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增失败");
			}
		}else{
			bslSendCheckInfo.setSendDate(sendCheckInfoOld.getSendDate());
			bslSendCheckInfo.setUpdDate(new Date());//修改日期当天
			int result = bslSendCheckInfoMapper.updateByPrimaryKey(bslSendCheckInfo);
			if(result<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(result==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的修改记录");
			}	
		}
		return BSLResult.ok(bslSendCheckInfo.getLuId());
	}
	
	/**
	 * 删除送检信息
	 */
	@Override
	public BSLResult deleteSendInfo(String luId) {
		//修改之前先查询原信息
		BslSendCheckInfo sendCheckInfoOld = bslSendCheckInfoMapper.selectByPrimaryKey(luId);
		if(sendCheckInfoOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"根据炉号未查到送检信息！");
		}
		int result = bslSendCheckInfoMapper.deleteByPrimaryKey(luId);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的删除记录");
		}		
		return BSLResult.ok(luId);
	}

}
