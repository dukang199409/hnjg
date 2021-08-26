package com.bsl.service.plan;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslMakePlanInfoDetail;
import com.bsl.select.QueryExample;

/**
 * 纵剪带详细计划管理接口
 */
public interface MakePlanDetailService {
	//新增纵剪带指令详细计划
	BSLResult add(BslMakePlanInfoDetail bslMakePlanInfo);
	//修改纵剪带指令详细计划
	BSLResult update(BslMakePlanInfoDetail bslMakePlanInfo);
	//删除纵剪带指令详细计划
	BSLResult delete(String planInfoDetailId);
	//查询所有纵剪带指令详细计划
	EasyUIDataGridResult getMakePlanInfoDetailList(int page,int rows);
	//根据条件查询所有纵剪带指令详细计划
	BSLResult queryMakePlanInfoDetailList(QueryExample queryCriteria);
}
