package com.bsl.service.sale;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.select.QueryCriteria;

/**
 * 销售出库产品管理接口
 * duk-20190319
 */
public interface SaleProdInfoService {
	
	//根据条件查询已经出库的产品信息
	BSLResult getOutProdsService(QueryCriteria queryCriteria);
	
	//根据条件查询已经出库的产品汇总信息
	BSLResult getOutProdsGroupService(QueryCriteria queryCriteria);
	
	//根据条件查询外协厂已经出库的产品汇总信息
	BSLResult getWxOutProdsGroupService(QueryCriteria queryCriteria);
	
	//初始化查询所有销售出库通知单信息
	EasyUIDataGridResult getOutProdsGroupService(Integer page, Integer rows);
	
}
