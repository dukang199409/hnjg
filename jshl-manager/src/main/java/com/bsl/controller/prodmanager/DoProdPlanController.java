package com.bsl.controller.prodmanager;

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
import com.bsl.reportbean.BslMakePlanInfoDetailPro;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.prodmanager.ProdPlanService;

/**
 * @author 杜康
 * 生产计划指令执行管理Controller
 */
@Controller
@RequestMapping("/doProdPlan")
public class DoProdPlanController {
	
	@Autowired
	private ProdPlanService prodPlanService;
	
	/**
	 * 查询生产计划指令信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getProdPlanList(Integer page,Integer rows){		
		return prodPlanService.getProdPlanService(page, rows);
	}
		
	/**
	 * 获取符合条件的生产计划指令信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getProdPlanList(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = prodPlanService.getProdPlanService(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 执行生产计划指令信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/execute")
	@ResponseBody
	public BSLResult executeProdPlan(BslMakePlanInfo bslMakePlanInfo){
		try {
			return prodPlanService.updateProdPlanInfoStatus(bslMakePlanInfo,1);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 暂停生产计划指令信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/pause")
	@ResponseBody
	public BSLResult pauseProdPlan(BslMakePlanInfo bslMakePlanInfo){
		try {
			return prodPlanService.updateProdPlanInfoStatus(bslMakePlanInfo,2);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 产成品生产指令详情
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public BSLResult getMakePlanDetailInfo(String planId) {
		if(StringUtils.isBlank(planId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产成品生产指令号不能为空");
		}
		try {
			List<BslMakePlanInfoDetailPro> lists = prodPlanService.getProdPlanInfoDetailPro(planId);
			return BSLResult.ok(lists);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 完成生产计划指令信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/finish")
	@ResponseBody
	public BSLResult finishProdPlan(BslMakePlanInfo bslMakePlanInfo){
		try {
			return prodPlanService.updateProdPlanInfoStatus(bslMakePlanInfo,3);
		} catch (Exception e) {
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 强制终止生产计划指令信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/stop")
	@ResponseBody
	public BSLResult stopProdPlan(BslMakePlanInfo bslMakePlanInfo){
		try {
			return prodPlanService.updateProdPlanInfoStatus(bslMakePlanInfo,4);
		} catch (Exception e) {
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 撤销执行成品生产指令信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/redo")
	@ResponseBody
	public BSLResult redoProdPlan(BslMakePlanInfo bslMakePlanInfo){
		try {
			return prodPlanService.updateProdPlanInfoStatus(bslMakePlanInfo,5);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	@RequestMapping("/{page}")
	public String showUserPage(@PathVariable String page, HttpServletRequest request) {
		return page;
	}
	
}
