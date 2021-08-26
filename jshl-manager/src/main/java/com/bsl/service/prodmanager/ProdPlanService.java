package com.bsl.service.prodmanager;

import java.util.List;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslMakePlanInfo;
import com.bsl.pojo.BslMakePlanInfoDetail;
import com.bsl.pojo.BslMakePlanInfoExample;
import com.bsl.reportbean.BslMakePlanInfoDetailPro;
import com.bsl.select.QueryCriteria;

/**
 * 生产计划指令管理
 * duk-20190320
 */
public interface ProdPlanService {
	
	//初始化查询所有生产计划指令信息
	EasyUIDataGridResult getProdPlanService(Integer page, Integer rows);
	//根据条件查询所有生产计划指令信息
	BSLResult getProdPlanService(QueryCriteria queryCriteria);
	//根据实体类条件查询所有生产计划指令信息
	BSLResult getProdPlanByMakePlanInfoService(BslMakePlanInfoExample bslMakePlanInfoExample);
	//新增生产计划指令信息
	BSLResult addProdPlanInfo(BslMakePlanInfo bslMakePlanInfo);
	//修改生产计划指令信息
	BSLResult updateProdPlanInfo(BslMakePlanInfo bslMakePlanInfo);
	//删除生产计划指令信息
	BSLResult deleteProdPlanInfo(BslMakePlanInfo bslMakePlanInfo);
	//修改生产计划指令信息状态
	BSLResult updateProdPlanInfoStatus(BslMakePlanInfo bslMakePlanInfo,Integer statusInt);
	//获取正在执行中的生产指令信息
	BslMakePlanInfo getProdPlanInfoExe(String jz);
	//根据生产指令id获取详细调度信息
	List<BslMakePlanInfoDetailPro> getProdPlanInfoDetailPro(String planId);
	//根据生产指令id获取详细调度信息
	List<BslMakePlanInfoDetail> getProdPlanInfoDetail(String planId);
	//根据生产指令id获取产品生产指令信息
	BslMakePlanInfo getProdPlanInfo(String planId);
	//查询指令出库的纵剪带
	BSLResult getHalfProdsByPlanId(String planId);
	//根据产品生产指令号更新该指令已经入库的包数和总重
	BSLResult updateProdRuNumAndSums(String planId);
	
	
}
