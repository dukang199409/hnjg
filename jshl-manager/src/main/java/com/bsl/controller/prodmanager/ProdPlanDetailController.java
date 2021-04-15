package com.bsl.controller.prodmanager;

import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslMakePlanInfoDetail;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.prodmanager.ProdPlanDetailService;

/**
 * @author 杜康
 * 生产计划指令详细调度信息管理Controller
 */
@Controller
@RequestMapping("/prodPlanDetail")
public class ProdPlanDetailController {
	
	@Autowired
	private ProdPlanDetailService prodPlanDetailService;
	
	/**
	 * 查询生产计划指令详细调度信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getProdPlanDetailList(Integer page,Integer rows){		
		return prodPlanDetailService.getProdPlanDetailService(page, rows);
	}
		
	/**
	 * 获取符合条件的生产计划指令详细调度信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getProdPlanDetailList(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = prodPlanDetailService.getProdPlanDetailService(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 获取符合条件的生产计划指令详细调度信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listInfo")
	@ResponseBody
	public BSLResult selectMakePlanDetailInfoJoinPlan(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = prodPlanDetailService.selectMakePlanDetailInfoJoinPlan(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 新增生产计划指令详细调度信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public BSLResult addProdDetailPlanDetail(BslMakePlanInfoDetail bslMakePlanInfoDetail,String planFinistDateM3002Add){
		if(!StringUtils.isBlank(planFinistDateM3002Add)){
			try {
				bslMakePlanInfoDetail.setPlanFinistDate(DictItemOperation.日期转换实例.parse(planFinistDateM3002Add));
			} catch (ParseException e) {
				DictItemOperation.log.info("===========异常:"+e.getMessage());
				e.printStackTrace();
			}
		}
		if(StringUtils.isBlank(bslMakePlanInfoDetail.getPlanId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "对应指令号不能为空");
		}
		try {
			return prodPlanDetailService.addProdPlanInfoDetail(bslMakePlanInfoDetail);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 修改生产计划指令详细调度信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public BSLResult editProdPlanDetail(BslMakePlanInfoDetail bslMakePlanInfoDetail,String planFinistDateM3002Edit){
		if(!StringUtils.isBlank(planFinistDateM3002Edit)){
			try {
				bslMakePlanInfoDetail.setPlanFinistDate(DictItemOperation.日期转换实例.parse(planFinistDateM3002Edit));
			} catch (ParseException e) {
				DictItemOperation.log.info("===========异常:"+e.getMessage());
				e.printStackTrace();
			}
		}
		if(StringUtils.isBlank(bslMakePlanInfoDetail.getPlanId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "对应指令号不能为空");
		}
		try {
			return prodPlanDetailService.updateProdPlanInfoDetail(bslMakePlanInfoDetail);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 删除生产计划指令详细调度信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public BSLResult deleteProdPlanDetail(String planInfoDetailId){
		if(StringUtils.isBlank(planInfoDetailId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "对应指令详细调度编号不能为空");
		}
		try {
			return prodPlanDetailService.deleteProdPlanInfoDetail(planInfoDetailId);
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
