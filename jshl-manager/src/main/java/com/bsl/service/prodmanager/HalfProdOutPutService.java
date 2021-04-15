package com.bsl.service.prodmanager;

import java.util.List;

import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.select.QueryCriteria;

/**
 * 半成品出库管理
 * duk-20190320
 */
public interface HalfProdOutPutService {

	//根据条件查询所有可以出库的半成品库存信息
	BSLResult getHalfProdPlanService(QueryCriteria queryCriteria);
	//根据实体类条件查询所有半成品库存信息
	BSLResult getHalfProdPlanByMakePlanInfoService(BslProductInfoExample bslProductInfoExample);
	//半成品库存状态变更
	BSLResult addHalfProdPlanInfo(String prodId,String planJz,String prodInputuser,Integer flag);
	//根据盘号查询半成品信息
	BslProductInfo getHalfProdPlanByPK(String prodId);
	//查询所有因为某指令制造出库中的半成品信息
	List<BslProductInfo> getHalfProdExe(String exePlanId);
	//判断半成品状态
	int updateHalfProdStatus(String prodId,Float inWeightThis);
	
}
