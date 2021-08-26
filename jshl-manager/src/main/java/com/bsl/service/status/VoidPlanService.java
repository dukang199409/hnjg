package com.bsl.service.status;

import com.bsl.common.utils.BSLResult;
import com.bsl.select.QueryCriteria;

/**
 * 指令作废
 * duk-20210711
 */
public interface VoidPlanService {
	
	//查询该指令出库的产品
	BSLResult getOutProds(QueryCriteria queryCriteria);
	//查询该指令制造的产品
	BSLResult getMakeProds(QueryCriteria queryCriteria);
	//指令作废 还原出库的产品 删除制造的产品
	BSLResult voidPlan(String planId,String inputuser);
	
}
