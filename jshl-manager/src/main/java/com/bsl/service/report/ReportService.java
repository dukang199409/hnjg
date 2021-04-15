package com.bsl.service.report;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.select.QueryCriteria;

/**
 * 报表查询接口
 * duk-20190320
 */
public interface ReportService {
	
	//初始化查询所有单炉库存重量统计信息
	EasyUIDataGridResult getAllLuStockInfoService(Integer page, Integer rows);
	//根据条件查询单炉库存重量统计信息
	BSLResult getLuStockInfoService(QueryCriteria queryCriteria);
	
	//初始化查询库存变动日汇总信息
	EasyUIDataGridResult getAllStockChangeInfoService(Integer page, Integer rows);
	//根据条件查询库存变动日汇总信息
	BSLResult getStockChangeInfoService(QueryCriteria queryCriteria);
	
	//初始化查询所有库存变动汇总信息
	EasyUIDataGridResult getAllStockChangeSumService(Integer page, Integer rows);
	//根据条件查询库存变动汇总信息
	BSLResult getStockChangeSumService(QueryCriteria queryCriteria);
	
	//初始化查询所有库存日照报表信息
	EasyUIDataGridResult getAllProductPhotoService(Integer page, Integer rows);
	//根据条件查询库存日照报表信息
	BSLResult getProductPhotoService(QueryCriteria queryCriteria);
	
	
}
