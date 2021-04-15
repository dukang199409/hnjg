package com.bsl.service.enums;

import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslParamInfo;
import com.bsl.select.QueryCriteria;

public interface ParamService {
	
	//根据条件查询所有枚举信息
	BSLResult getParamListByCriteria(QueryCriteria queryCriteria);
	//根据key查询值
	String getValueByParamKey(String paramId);
	//修改
	BSLResult editParam(BslParamInfo bslParamInfo);
}
