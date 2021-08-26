package com.bsl.service.dclprod;

import com.bsl.common.utils.BSLResult;
import com.bsl.select.QueryCriteria;

/**
 * 查询所有外协厂转场接收的产品/待处理品信息
 * duk-20190320
 */
public interface WxJsAllProdsService {
	
	//查询所有外协厂转场接收的产品/待处理品信息
	BSLResult getWxJsAllProds(QueryCriteria queryCriteria);
	
	//查询所有外协厂转场接收的废品信息
	BSLResult getWxJsAllWastes(QueryCriteria queryCriteria);
	
	
}
