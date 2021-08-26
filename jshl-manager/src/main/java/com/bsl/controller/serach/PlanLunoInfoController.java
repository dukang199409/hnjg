package com.bsl.controller.serach;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.utils.BSLResult;
import com.bsl.select.QueryCriteria;
import com.bsl.service.serach.PlanLunoInfoService;

/**
 * @author 杜康
 * 产品信息查询Controller
 */
@Controller
@RequestMapping("/planLuInfo")
public class PlanLunoInfoController {
	
	@Autowired
	private PlanLunoInfoService planLunoInfoService;
	
	/**
	 * 刷新炉号信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/updateAllLu")
	@ResponseBody
	public BSLResult updateLuaoOnce(){
			return planLunoInfoService.updateLuaoOnce();
	}
		
	/**
	 * 根据条件查询信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getLunoByPlanFY(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPlanId()) && StringUtils.isBlank(queryCriteria.getPlanLuno())) {
			return BSLResult.build(400, "查询条件不能同时为空");
		}
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result =planLunoInfoService.getLunoByPlanFY(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 根据条件查询信息不分页
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public BSLResult getLunoByPlan(String planId){
		BSLResult result  = planLunoInfoService.getLunoByPlan(planId);
		return result;
	}
	
}
