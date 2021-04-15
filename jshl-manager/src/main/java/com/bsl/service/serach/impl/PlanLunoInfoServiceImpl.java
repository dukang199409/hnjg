package com.bsl.service.serach.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsl.common.pojo.BSLException;
import com.bsl.common.utils.BSLResult;
import com.bsl.mapper.BslMakePlanInfoMapper;
import com.bsl.mapper.BslPlanLuInfoMapper;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.pojo.BslMakePlanInfo;
import com.bsl.pojo.BslMakePlanInfoExample;
import com.bsl.pojo.BslPlanLuInfo;
import com.bsl.pojo.BslPlanLuInfoExample;
import com.bsl.pojo.BslPlanLuInfoExample.Criteria;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.serach.PlanLunoInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 变动查询实现类
 * duk-20190319
 */
@Service
public class PlanLunoInfoServiceImpl implements PlanLunoInfoService {

	@Autowired	 
	BslPlanLuInfoMapper bslPlanLuInfoMapper;
	
	@Autowired	 
	BslMakePlanInfoMapper bslMakePlanInfoMapper;
	
	@Autowired	 
	BslProductInfoMapper bslProductInfoMapper;
	

	/**
	 * 获取该单号该炉号出库产品数量
	 */
	@Override
	public int getPlanLuno(String planId, String luno) {

		//根据指令号获取原指令信息
		BslMakePlanInfo bslBsPlanInfoOld = bslMakePlanInfoMapper.selectByPrimaryKey(planId);
		if(bslBsPlanInfoOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据指令号查询记录为空");
		}
		//查询是否有此炉号
		BslPlanLuInfoExample bslPlanLuInfoExample = new BslPlanLuInfoExample();
		Criteria criteriaLu = bslPlanLuInfoExample.createCriteria();
		criteriaLu.andPlanIdEqualTo(planId);
		criteriaLu.andPlanLunoEqualTo(luno);
		List<BslPlanLuInfo> bslPlanLuInfos = bslPlanLuInfoMapper.selectByExample(bslPlanLuInfoExample);
		if(bslPlanLuInfos == null || bslPlanLuInfos.size()<=0){
			return 0;
		}
		//查询该指令出库的该炉号的产品数量
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		com.bsl.pojo.BslProductInfoExample.Criteria criteriaProd = bslProductInfoExample.createCriteria();	
		criteriaProd.andProdLunoEqualTo(luno);
		criteriaProd.andProdOutPlanEqualTo(planId);
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		if(bslProductInfos == null || bslProductInfos.size()<=0){
			return 0;
		}else{
			return bslProductInfos.size();
		}
		
			
	}

	/**
	 * 登记生产计划炉号表
	 */
	@Override
	public BSLResult insertPlanLuno(String planId, String luno) {
		BslPlanLuInfo bslPlanLuInfo = new BslPlanLuInfo();
		bslPlanLuInfo.setPlanId(planId);
		bslPlanLuInfo.setPlanLuno(luno);
		int insert = bslPlanLuInfoMapper.insert(bslPlanLuInfo);
		if(insert<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(insert==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增生产计划炉号对应表失败");
		}
		return BSLResult.ok();
	}
	
	/**
	 * 删除生产计划炉号表
	 */
	@Override
	public BSLResult deletePlanLuno(String planId, String luno) {
		BslPlanLuInfoExample bslPlanLuInfoExample = new BslPlanLuInfoExample();
		Criteria criteriaLu = bslPlanLuInfoExample.createCriteria();
		criteriaLu.andPlanIdEqualTo(planId);
		criteriaLu.andPlanLunoEqualTo(luno);
		int insert = bslPlanLuInfoMapper.deleteByExample(bslPlanLuInfoExample);
		if(insert<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}
		return BSLResult.ok();
	}
	
	/**
	 * 根据单号获取炉号
	 */
	@Override
	public BSLResult getLunoByPlan(String planId) {
		BslPlanLuInfoExample bslPlanLuInfoExample = new BslPlanLuInfoExample();
		Criteria criteriaLu = bslPlanLuInfoExample.createCriteria();
		criteriaLu.andPlanIdEqualTo(planId);
		List<BslPlanLuInfo> selectByExample = bslPlanLuInfoMapper.selectByExample(bslPlanLuInfoExample);
		return BSLResult.ok(selectByExample);
	}
	
	/**
	 * 根据单号获取炉号分页
	 */
	@Override
	public BSLResult getLunoByPlanFY(QueryCriteria queryCriteria) {
		BslPlanLuInfoExample bslPlanLuInfoExample = new BslPlanLuInfoExample();
		Criteria criteriaLu = bslPlanLuInfoExample.createCriteria();
		if(!StringUtils.isBlank(queryCriteria.getPlanId())){
			criteriaLu.andPlanIdEqualTo(queryCriteria.getPlanId());
		}
		if(!StringUtils.isBlank(queryCriteria.getPlanLuno())){
			criteriaLu.andPlanLunoEqualTo( queryCriteria.getPlanLuno());
		}
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		bslPlanLuInfoExample.setOrderByClause("`plan_id` asc,`plan_luno` asc");
		List<BslPlanLuInfo> list = bslPlanLuInfoMapper.selectByExample(bslPlanLuInfoExample);
		PageInfo<BslPlanLuInfo> pageInfo = new PageInfo<BslPlanLuInfo>(list);
		return BSLResult.ok(list, "planLunoInfoServiceImpl", "getLunoByPlanFY",pageInfo.getTotal(),list);
	}

	/**
	 * 此方法用于将生产上的现有炉号字段刷新，插入新表
	 */
	@Override
	public BSLResult updateLuaoOnce() {
		//1 获取所有的生产指令
		BslMakePlanInfoExample example = new BslMakePlanInfoExample();
		List<BslMakePlanInfo> bslMakePlanInfos = bslMakePlanInfoMapper.selectByExample(example);
		BslPlanLuInfo bslPlanLuInfo = new BslPlanLuInfo();
		String planId = "";
		String planLunos = "";
		for (BslMakePlanInfo bslMakePlanInfo : bslMakePlanInfos) {
			planId = bslMakePlanInfo.getPlanId();
			planLunos = bslMakePlanInfo.getPlanLuno();
			if(!StringUtils.isBlank(planLunos)){
				String[] luStrings = planLunos.split(";");
				for (String luno : luStrings) {
					if(!StringUtils.isBlank(luno)){
						bslPlanLuInfo.setPlanId(planId);
						bslPlanLuInfo.setPlanLuno(luno);
						bslPlanLuInfoMapper.insert(bslPlanLuInfo);
					}
				}
			}
			
		}
		
		return null;
	}



}