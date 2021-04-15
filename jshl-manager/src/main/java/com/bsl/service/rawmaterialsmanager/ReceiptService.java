package com.bsl.service.rawmaterialsmanager;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslBsPlanInfo;
import com.bsl.pojo.BslBsPlanInfoExample;
import com.bsl.select.QueryCriteria;

/**
 * 原料入库通知单管理接口
 * duk-20190319
 */
public interface ReceiptService {
	
	//初始化查询所有原料入库通知单信息
	EasyUIDataGridResult getReceiptService(Integer page, Integer rows);
	//根据条件查询所有原料入库通知单信息
	BSLResult getReceiptService(QueryCriteria queryCriteria);
	//根据实体类条件查询所有原料入库通知单信息
	BSLResult getReceiptServiceByBsPlanInfo(BslBsPlanInfoExample bslBsPlanInfoExample);
	//根据单号id查询原料入库通知单信息
	BSLResult getReceiptByBsBsId(String bsId);
	//新增原料入库通知单信息
	BSLResult addReceiptInfo(BslBsPlanInfo bslBsPlanInfo);
	//修改原料入库通知单信息
	BSLResult updateReceiptInfo(BslBsPlanInfo bslBsPlanInfo);
	//删除原料入库通知单信息
	BSLResult deleteReceiptInfo(BslBsPlanInfo bslBsPlanInfo);
	//根据单号查询卷板信息
	BSLResult getMakePlanInfoDetail(String bsId);
	//根据卷板信息刷新通知单状态
	BSLResult updateBsInfoStatus(String bsId);
	
}
