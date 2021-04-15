package com.bsl.service.rawmaterialsmanager;

import com.bsl.common.utils.BSLResult;
import com.bsl.select.QueryCriteria;

/**
 * 重量比对
 * duk-20190320
 */
public interface RawCompareService {
	
	//根据条件查询所有入库单(卷板)信息
	BSLResult getProdInfoService(QueryCriteria queryCriteria);
	
}
