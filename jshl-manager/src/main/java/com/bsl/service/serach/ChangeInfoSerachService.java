package com.bsl.service.serach;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.select.QueryCriteria;

/**
 * 变动信息查询接口
 * duk-20190320
 */
public interface ChangeInfoSerachService {
	
	//初始化查询所有库存变动信息
	EasyUIDataGridResult getAllInfoService(Integer page, Integer rows);
	//根据条件查询所有库存变动信息
	BSLResult getInfoByCriteriaService(QueryCriteria queryCriteria);
	//根据条件查询所有库存变动历史信息
	BSLResult getInfoByCriteriaHService(QueryCriteria queryCriteria);
	
	//初始化查询所有状态变动信息
	EasyUIDataGridResult getAllStatusChangeInfoService(Integer page, Integer rows);
	//根据条件查询所有状态变动信息
	BSLResult getStatusChangeInfoService(QueryCriteria queryCriteria);
	
	
}
