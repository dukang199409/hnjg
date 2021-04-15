package com.bsl.service.dclprod;

import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslProductInfo;
import com.bsl.select.QueryCriteria;

/**
 * 待处理品入库
 * duk-20190320
 */
public interface DclProdService {
	
	//根据条件查询所有待处理品信息
	BSLResult getDclProdServiceByCriteria(QueryCriteria queryCriteria);
	//根据id查询待处理品信息
	BSLResult getDclProdServiceById(String prodId);
	//待处理品入库
	BSLResult addDclProdinfo(BslProductInfo bslProductInfo,int sumNum);
	//待处理品补录入库
	BSLResult addDclProdinfoB(BslProductInfo bslProductInfo,int sumNum);
	//待处理品信息修改
	BSLResult updateDclProdPlan(BslProductInfo bslProductInfo);
	//待处理品信息删除
	BSLResult delete(String prodId,String user);
	
}
