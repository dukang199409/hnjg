package com.bsl.service.serach;

import com.bsl.common.utils.BSLResult;
import com.bsl.select.QueryCriteria;

/**
 *  获取该单号该炉号出库产品数量
 * duk-20190320
 */
public interface PlanLunoInfoService {
	
	//根据条件查询所有满足条件的在库产品信息
	int getPlanLuno(String planId,String luno);
	
	//登记生产计划炉号表
	BSLResult insertPlanLuno(String planId,String luno);
	
	//删除生产计划炉号表
	BSLResult deletePlanLuno(String planId, String luno);
	
	//根据单号查询所用炉号
	BSLResult getLunoByPlan(String  planId);
	
	//根据单号查询所用炉号分页
	BSLResult getLunoByPlanFY(QueryCriteria  criteria);
	
	//执行一次，刷新生产上的炉号，插入新表
	BSLResult updateLuaoOnce();
	
}
