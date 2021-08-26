package com.bsl.controller.plan;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslMakePlanInfo;
import com.bsl.pojo.BslMakePlanInfoDetail;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryExample;
import com.bsl.service.plan.MakePlanService;

/**
 * 半成品指令执行
 * @author duk
 * @date 2019年3月28日  
 *
 */
@Controller
@RequestMapping("/doPlan")
public class DoPlanController {
	
	@Autowired
	private MakePlanService makePlanService;
	
	@RequestMapping("/{page}")
	public String showPlanPage(@PathVariable String page, HttpServletRequest request) {
		return page;
	}
	
	/**
	 * 初始化查询所有纵剪带生产指令
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getMakePlanInfoList(Integer page, Integer rows) {
		return makePlanService.getMakePlanInfoList(page, rows);
	}
	
	/**
	 * 根据条件查询纵剪带生产指令
	 */
	@RequestMapping("/query")
	@ResponseBody
	public BSLResult queryMakePlanInfoList(QueryExample queryCriteria) {
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = makePlanService.queryMakePlanInfoList(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 纵剪带生产指令详情
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public BSLResult getMakePlanDetailInfo(String planId) {
		if(StringUtils.isBlank(planId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "纵剪带指令号不能为空");
		}
		try {
			List<BslMakePlanInfoDetail> lists = makePlanService.getMakePlanInfoDetail(planId);
			return BSLResult.ok(lists);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 执行半成品生产指令信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/execute")
	@ResponseBody
	public BSLResult executeProdPlan(BslMakePlanInfo bslMakePlanInfo){
		try {
			return makePlanService.updateProdPlanInfoStatus(bslMakePlanInfo,1);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 暂停半成品生产指令信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/pause")
	@ResponseBody
	public BSLResult pauseProdPlan(BslMakePlanInfo bslMakePlanInfo){
		try {
			return makePlanService.updateProdPlanInfoStatus(bslMakePlanInfo,2);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 完成半成品生产指令信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/finish")
	@ResponseBody
	public BSLResult finishProdPlan(BslMakePlanInfo bslMakePlanInfo){
		try {
			return makePlanService.updateProdPlanInfoStatus(bslMakePlanInfo,3);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 强制终止半成品生产指令信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/stop")
	@ResponseBody
	public BSLResult stopProdPlan(BslMakePlanInfo bslMakePlanInfo){
		try {
			return makePlanService.updateProdPlanInfoStatus(bslMakePlanInfo,4);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 撤销执行半成品生产指令信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/redo")
	@ResponseBody
	public BSLResult redoProdPlan(BslMakePlanInfo bslMakePlanInfo){
		try {
			return makePlanService.updateProdPlanInfoStatus(bslMakePlanInfo,5);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
}
