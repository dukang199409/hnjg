package com.bsl.controller.sale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.sale.SalePlanService;

/**
 * @author 杜康
 * 销售出库通知单执行管理Controller
 */
@Controller
@RequestMapping("/doSalePlan")
public class DoSalePlanController {
	
	@Autowired
	SalePlanService salePlanService;
	
	
	/**
	 * 查询销售出库通知单信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getsalePlanList(Integer page, Integer rows){
		return salePlanService.getSalePlanService(page, rows);
	}
	
	/**
	 * 根据条件查询销售出库通知单信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getsalePlanList(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = salePlanService.getSalePlanServiceNotWx(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 根据条件查询外协厂销售出库通知单信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteriaWx")
	@ResponseBody
	public BSLResult getsalePlanListWx(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = salePlanService.getSalePlanServiceWx(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 销售通知调度指令详情
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public BSLResult getSalePlanDetailByBsBsId(String bsId) {
		if(StringUtils.isBlank(bsId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "销售通知号不能为空");
		}
		try {
			 return salePlanService.getSalePlanDetailByBsBsId(bsId);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 销售出库通知单发货
	 * @param BslBsPlanInfo
	 * @return
	 */
	@RequestMapping(value="/sale", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult salePlanInfo(String[] arrays,String prodOutPlan,String checkUser) {
		if(StringUtils.isBlank(prodOutPlan)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "销售出库通知单号不能为空");
		}
		try {
			return salePlanService.updateFinishSalePlanInfo(arrays,prodOutPlan, checkUser);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 销售出库通知单强制完成
	 * @param BslBsPlanInfo
	 * @return
	 */
	@RequestMapping(value="/finish", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult finishSalePlanInfo(String bsId,String checkUser) {
		if(StringUtils.isBlank(bsId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "销售出库通知单号不能为空");
		}
		try {
			return salePlanService.updateQZFinishSalePlanInfo(bsId, checkUser);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 销售出库通知单获取已经出库总重
	 * @param bsId
	 * @return
	 */
	@RequestMapping(value="/getOutWeight", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult getOutWeight(String bsId) {
		if(StringUtils.isBlank(bsId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "销售出库通知单号不能为空");
		}
		try {
			return salePlanService.getOutWeight(bsId);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 销售出库通知单复磅
	 * @param bsId
	 * @return
	 */
	@RequestMapping(value="/saleFbAll", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult saleFbAll(String bsId,Float bsFbweight) {
		if(StringUtils.isBlank(bsId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "销售出库通知单号不能为空");
		}
		if(bsFbweight == null || bsFbweight == 0f){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "复磅重量不能为空");
		}
		try {
			return salePlanService.saleFbAll(bsId,bsFbweight);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	@RequestMapping("/{page}")
	public String showUserPage(@PathVariable String page) {
		return page;
	}
	
}
