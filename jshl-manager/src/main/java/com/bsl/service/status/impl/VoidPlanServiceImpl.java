package com.bsl.service.status.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsl.common.pojo.BSLException;
import com.bsl.common.utils.BSLResult;
import com.bsl.mapper.BslMakePlanInfoDetailMapper;
import com.bsl.mapper.BslMakePlanInfoMapper;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.pojo.BslMakePlanInfo;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslProductInfoExample.Criteria;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.status.VoidPlanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 指令作废
 * duk-20210711
 */
@Service
public class VoidPlanServiceImpl implements VoidPlanService {

	@Autowired	 
	BslProductInfoMapper bslProductInfoMapper;
	
	@Autowired	 
	BslMakePlanInfoMapper bslMakePlanInfoMapper;
	
	@Autowired	 
	BslMakePlanInfoDetailMapper bslMakePlanInfoDetailMapper;

	/**
	 * 查询该指令出库的产品
	 */
	@Override
	public BSLResult getOutProds(QueryCriteria queryCriteria) {
		//根据单号获取指令信息
		BslMakePlanInfo bslMakePlanInfo = bslMakePlanInfoMapper.selectByPrimaryKey(queryCriteria.getPlanId());
		if(bslMakePlanInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"未查询到生产指令信息");
		}
		if(!DictItemOperation.指令状态_已完成.equals(bslMakePlanInfo.getPlanStatus()) &&
				!DictItemOperation.指令状态_强制终止.equals(bslMakePlanInfo.getPlanStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"仅允许还原已完成/强制终止的指令信息");
		}
		//创建查询的实例，并赋值
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdOutPlanEqualTo(queryCriteria.getPlanId());
		bslProductInfoExample.setOrderByClause("`prod_id` desc");
		
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		PageInfo<BslProductInfo> pageInfo = new PageInfo<BslProductInfo>(bslProductInfos);
		return BSLResult.ok(bslProductInfos,"voidPlanServiceImpl","getOutProds",pageInfo.getTotal(),bslProductInfos);
		
	}

	/**
	 * 查询该指令制造的产品
	 */
	@Override
	public BSLResult getMakeProds(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdPlanNoEqualTo(queryCriteria.getPlanId());
		bslProductInfoExample.setOrderByClause("`prod_id` desc");
		
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage1()), Integer.parseInt(queryCriteria.getRows1()));
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		PageInfo<BslProductInfo> pageInfo = new PageInfo<BslProductInfo>(bslProductInfos);
		return BSLResult.ok(bslProductInfos,"voidPlanServiceImpl","getMakeProds",pageInfo.getTotal(),bslProductInfos);
	}

	/**
	 * 指令作废 还原出库的产品 删除制造的产品
	 */
	@Override
	public BSLResult voidPlan(String planId,String inputuser) {
		if(!DictItemOperation.管理员.equals(inputuser)){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"仅允许超级管理员还原指令信息");
		}
		//根据单号获取指令信息
		BslMakePlanInfo bslMakePlanInfo = bslMakePlanInfoMapper.selectByPrimaryKey(planId);
		if(!DictItemOperation.指令状态_已完成.equals(bslMakePlanInfo.getPlanStatus()) &&
				!DictItemOperation.指令状态_强制终止.equals(bslMakePlanInfo.getPlanStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"仅允许还原已完成/强制终止的指令信息");
		}
		//1.还原出库产品信息
		bslProductInfoMapper.updateOutProdsByPlanId(planId);
		//2.删除制造产品信息
		bslProductInfoMapper.deleteMakeProdsByPlanId(planId);
		//3.删除指令信息
		bslMakePlanInfoMapper.deleteByPrimaryKey(planId);
		//4.删除指令详情信息
		bslMakePlanInfoDetailMapper.deleteDetailByPlanId(planId);
		
		return BSLResult.ok();
	}
	
	
	
	
}
