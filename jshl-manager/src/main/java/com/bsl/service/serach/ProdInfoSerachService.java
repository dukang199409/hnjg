package com.bsl.service.serach;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.select.QueryCriteria;

/**
 * 产品信息查询接口
 * duk-20190320
 */
public interface ProdInfoSerachService {
	
	//初始化查询所有信息
	EasyUIDataGridResult getAllInfoService(Integer page, Integer rows);
	//根据条件查询所有当前产品信息
	BSLResult getInfoByCriteriaService(QueryCriteria queryCriteria);
	//根据条件查询所有历史产品信息
	BSLResult getProdInfoHList(QueryCriteria queryCriteria);
	//初始化查询所有在库信息
	EasyUIDataGridResult getAllInInfoService(Integer page, Integer rows);
	//根据条件查询所有在库产品信息
	BSLResult getInInfoByCriteriaService(QueryCriteria queryCriteria);
	
	
}
