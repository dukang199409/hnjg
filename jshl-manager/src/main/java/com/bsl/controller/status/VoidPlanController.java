package com.bsl.controller.status;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.utils.BSLResult;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.status.VoidPlanService;

/**
 * 产品状态维护
 * @author 杜康
 *
 */
@Controller
@RequestMapping("/voidPlan")
public class VoidPlanController {
	
	@Autowired
	private VoidPlanService voidPlanService;
	
	/**
	 *查询该指令出库的产品
	 */
	@RequestMapping("/outProds")
	@ResponseBody
	public BSLResult outProds(QueryCriteria queryCriteria) {
		if(StringUtils.isBlank(queryCriteria.getPlanId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "生产指令号不能为空");
		}
		try {
			return voidPlanService.getOutProds(queryCriteria);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 查询该指令制造的产品
	 */
	@RequestMapping("/makeProds")
	@ResponseBody
	public BSLResult makeProds(QueryCriteria queryCriteria) {
		if(StringUtils.isBlank(queryCriteria.getPlanId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "生产指令号不能为空");
		}
		try {
			return voidPlanService.getMakeProds(queryCriteria);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 指令作废 还原出库的产品 删除制造的产品
	 */
	@RequestMapping("/void")
	@ResponseBody
	public BSLResult voidPlan(String planId,String inputuser) {
		if(StringUtils.isBlank(planId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "生产指令号不能为空");
		}
		try {
			return voidPlanService.voidPlan(planId,inputuser);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
}
