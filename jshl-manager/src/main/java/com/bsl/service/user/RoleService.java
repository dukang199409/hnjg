package com.bsl.service.user;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.select.QueryCriteria;

/**
 * 人员权限管理接口
 * duk-20190319
 */
public interface RoleService {
	
	//初始化查询所有人员权限信息
	EasyUIDataGridResult getRoleService(Integer page, Integer rows);
	//根据条件查询所有人员权限信息
	BSLResult getRoleService(QueryCriteria queryCriteria);
	//新增人员权限信息
	BSLResult addRoleInfoById(String[] arrays);
	//删除人员权限信息
	BSLResult deleteSaleDetailInfo(String[] arrays);
	//获取可限制交易列表
	BSLResult getMenuByUserType(String userType);
	
	
}
