package com.bsl.controller.user;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslUsertypeInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.user.UserTypeService;

@Controller
@RequestMapping("/userType")
public class UserTypeController {
	
	@Autowired
	private UserTypeService userTypeService;
	
	@RequestMapping("/{page}")
	public String showPlanPage(@PathVariable String page, HttpServletRequest request) {
		return page;
	}
	
	/**
	 * 初始化查询所有人员角色信息
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getMakePlanInfoList(Integer page, Integer rows) {
		return userTypeService.getAllUserTypeInfoList(page, rows);
	}
	
	/**
	 * 根据条件查询所有人员角色信息
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult queryMakePlanInfoList(QueryCriteria queryCriteria) {
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = userTypeService.getUserTypeInfoList(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 新增人员角色信息
	 */
	@RequestMapping("/add")
	@ResponseBody
	public BSLResult addUserTypeInfo(BslUsertypeInfo bslUsertypeInfo) {
		if(StringUtils.isBlank(bslUsertypeInfo.getUserId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "员工编号不能为空");
		}
		if(StringUtils.isBlank(bslUsertypeInfo.getUserType())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "角色类型不能为空");
		}
		try {
			return userTypeService.addUserTypeInfo(bslUsertypeInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 删除人员角色信息
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public BSLResult deleteUserTypeInfo(BslUsertypeInfo bslUsertypeInfo) {
		if(StringUtils.isBlank(bslUsertypeInfo.getUserId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "员工编号不能为空");
		}
		if(StringUtils.isBlank(bslUsertypeInfo.getUserType())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "角色类型不能为空");
		}
		try {
			return userTypeService.deleteUserTypeInfo(bslUsertypeInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
}
