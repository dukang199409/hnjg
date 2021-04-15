package com.bsl.controller.user;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsl.common.SpringContextUtils;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.controller.common.AutoEnumUtil;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.user.RoleService;

/**
 * 人员权限管理
 * @author huangyl
 * @date 2019年5月27日  
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/{page}")
	public String showPlanPage(@PathVariable String page) {
		return page;
	}
	
	/**
	 * 初始化查询所有权限信息
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getRoleInfoList(Integer page, Integer rows) {
		return roleService.getRoleService(page, rows);
	}
	
	/**
	 * 根据条件查询权限信息
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getRoleInfoList(QueryCriteria queryCriteria) {
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = roleService.getRoleService(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 新增权限信息
	 */
	@RequestMapping("/addRole")
	@ResponseBody
	public BSLResult addMakePlanInfo(String[] arrays) {
		try {
			return roleService.addRoleInfoById(arrays);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
		
	/**
	 * 删除纵剪带生产指令
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public BSLResult deleteRoleInfo(String[] arrays) {
		try {
			return roleService.deleteSaleDetailInfo(arrays);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 获取可出库交易列表
	 */
	@RequestMapping("/getMenu")
	@ResponseBody
	public BSLResult getMenuByUserType(String userType) {
		if(StringUtils.isBlank(userType)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "人员角色不能为空");
		}
		try {
			return roleService.getMenuByUserType(userType);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
}
