package com.bsl.service.plan;

import java.util.List;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslMakePlanInfo;
import com.bsl.pojo.BslMakePlanInfoDetail;
import com.bsl.pojo.BslMakePlanInfoExample;
import com.bsl.select.QueryExample;

/**
 * 纵剪带指令管理接口
 */
public interface MakePlanService {
	//新增纵剪带指令
	BSLResult add(BslMakePlanInfo bslMakePlanInfo);
	//修改纵剪带指令
	BSLResult update(BslMakePlanInfo bslMakePlanInfo);
	//删除纵剪带指令
	BSLResult delete(String planId);
	//查询所有纵剪带指令
	EasyUIDataGridResult getMakePlanInfoList(int page,int rows);
	//根据条件查询所有纵剪带指令
	BSLResult queryMakePlanInfoList(QueryExample queryCriteria);
	//修改状态
	public BSLResult updateProdPlanInfoStatus(BslMakePlanInfo bslMakePlanInfo, Integer statusInt);
	//根据实体类查询所有纵剪带指令
	List<BslMakePlanInfo> query(BslMakePlanInfoExample example);
	//查询正在执行的纵剪带指令
	BslMakePlanInfo getMakePlanInfoExe(String jz);
	//根据id查询所有纵剪带指令
	BslMakePlanInfo getMakePlanInfoById(String planId);
	//根据id查询所有纵剪带指令详细计划
	List<BslMakePlanInfoDetail> getMakePlanInfoDetail(String planId);
	//根据实体类查询所有纵剪带指令
	BSLResult getDetailByMakePlanInfoService(BslMakePlanInfoExample example);
	//根据半产品生产指令号更新该指令已经入库的包数和总重
	BSLResult updateProdRuNumAndSums(String planId);
}
