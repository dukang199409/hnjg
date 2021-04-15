package com.bsl.service.prodmanager;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslMakePlanInfoDetail;
import com.bsl.pojo.BslMakePlanInfoDetailExample;
import com.bsl.select.QueryCriteria;

/**
 * 生产计划指令详细调度信息管理
 * duk-20190320
 */
public interface ProdPlanDetailService {
	
	//初始化查询所有生产计划指令详细调度信息管理
	EasyUIDataGridResult getProdPlanDetailService(Integer page, Integer rows);
	//根据条件查询所有生产计划指令详细调度信息管理
	BSLResult getProdPlanDetailService(QueryCriteria queryCriteria);
	//根据实体类条件查询生产计划指令详细调度信息管理
	BSLResult getProdPlanByMakePlanInfoDetailService(BslMakePlanInfoDetailExample bslMakePlanInfoDetailExample);
	//新增生产计划指令详细调度信息管理
	BSLResult addProdPlanInfoDetail(BslMakePlanInfoDetail bslMakePlanInfoDetail);
	//修改生产计划指令详细调度信息管理
	BSLResult updateProdPlanInfoDetail(BslMakePlanInfoDetail bslMakePlanInfoDetail);
	//删除生产计划指令详细调度信息管理
	BSLResult deleteProdPlanInfoDetail(String planInfoDetailId);
	//根据条件查询所有生产计划指令详细调度信息管理
	BSLResult selectMakePlanDetailInfoJoinPlan(QueryCriteria queryCriteria);
	
}
