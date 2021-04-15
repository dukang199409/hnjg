package com.bsl.service.user;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslUsertypeInfo;
import com.bsl.select.QueryCriteria;

/**
 * 销售出库通知单管理接口
 * duk-20190319
 */
public interface UserTypeService {
	
	//初始化查询所有人员角色信息
	EasyUIDataGridResult getAllUserTypeInfoList(Integer page, Integer rows);
	//根据条件查询所有人员角色信息
	BSLResult getUserTypeInfoList(QueryCriteria queryCriteria);
	//新增人员角色信息
	BSLResult addUserTypeInfo(BslUsertypeInfo bslUsertypeInfo);
	//新增人员角色信息
	BSLResult deleteUserTypeInfo(BslUsertypeInfo bslUsertypeInfo);
	
}
