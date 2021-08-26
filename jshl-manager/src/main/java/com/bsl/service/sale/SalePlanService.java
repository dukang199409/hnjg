package com.bsl.service.sale;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslBsPlanInfo;
import com.bsl.pojo.BslBsPlanInfoExample;
import com.bsl.select.QueryCriteria;

/**
 * 销售出库通知单管理接口
 * duk-20190319
 */
public interface SalePlanService {
	
	//初始化查询所有销售出库通知单信息
	EasyUIDataGridResult getSalePlanService(Integer page, Integer rows);
	//根据条件查询所有销售出库通知单信息
	BSLResult getSalePlanService(QueryCriteria queryCriteria);
	//根据条件查询所有外协厂销售出库通知单信息
	BSLResult getSalePlanServiceWx(QueryCriteria queryCriteria);
	//根据条件查询所有非外协厂销售出库通知单信息
	BSLResult getSalePlanServiceNotWx(QueryCriteria queryCriteria);
	//根据实体类条件查询所有销售出库通知单信息
	BSLResult getSalePlanServiceByBsPlanInfo(BslBsPlanInfoExample bslBsPlanInfoExample);
	//根据单号id查询销售出库通知单信息
	BSLResult getSalePlanByBsBsId(String bsId);
	//新增销售出库通知单信息
	BSLResult addSalePlanInfo(BslBsPlanInfo bslBsPlanInfo);
	//修改销售出库通知单信息
	BSLResult updateSalePlanInfo(BslBsPlanInfo bslBsPlanInfo);
	//删除销售出库通知单信息
	BSLResult deleteSalePlanInfo(BslBsPlanInfo bslBsPlanInfo);
	//销售出库通知单发货
	BSLResult updateFinishSalePlanInfo(String[] arrays,String prodOutPlan,String checkUser);
	//根据出库通知单号刷新其状态
	BSLResult updateRefreshSalePlanStatus(String bsId);
	//根据单号id查询销售出库通知单详细信息
	BSLResult getSalePlanDetailByBsBsId(String bsId);
	//根据单号记录复磅重量
	BSLResult saleFbAll(String bsId,Float bsFbweight);
	//根据单号查询已经出库重量
	BSLResult getOutWeight(String bsId);
	//销售出库通知单强制完成
	BSLResult updateQZFinishSalePlanInfo(String bsId,String checkUser);
	//根据产品车号获取销售出库单信息
	BslBsPlanInfo getSalePlanByCarno(String carno);
	//刷新出库数量和重量
	BSLResult updateReloadSalePlanDetail(String bsId);
	
}
