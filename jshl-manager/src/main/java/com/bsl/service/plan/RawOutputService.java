package com.bsl.service.plan;

import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslProductInfo;
import com.bsl.select.QueryExample;

/**
 * 卷板出库接口
 */
public interface RawOutputService {
	//卷板状态变更
	BSLResult updateRawStatusService(String prodId,String planJz,String inputUser,Integer status);
	//根据条件查询所有卷板信息
	BSLResult queryBslProductInfoList(QueryExample queryCriteria);
	//根据id查询所有卷板信息
	BslProductInfo getProdPlanByPK(String prodId);
	//获取某指令正在进行中的卷板信息
	BslProductInfo getRawExe(String exePlanId);
}
