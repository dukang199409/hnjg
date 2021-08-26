package com.bsl.controller.plan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.bsl.pojo.BslMakePlanInfoExample;
import com.bsl.pojo.BslMakePlanInfoExample.Criteria;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryExample;
import com.bsl.service.plan.MakePlanDetailService;
import com.bsl.service.plan.MakePlanService;

/**
 * 半成品指令管理
 * @author huangyl
 * @date 2019年3月28日  
 *
 */
@Controller
@RequestMapping("/planDetail")
public class MakePlanDetailController {
	
	@Autowired
	private MakePlanDetailService makePlanDetailService;
	
	@RequestMapping("/{page}")
	public String showPlanDetailPage(@PathVariable String page, HttpServletRequest request) {
		return page;
	}
	
	/**
	 * 查询所有调度计划信息
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getMakePlanInfoDetailList(Integer page, Integer rows) {
		return makePlanDetailService.getMakePlanInfoDetailList(page, rows);
	}
	
	/**
	 * 根据条件查询调度计划信息
	 */
	@RequestMapping("/query")
	@ResponseBody
	public BSLResult queryMakePlanInfoDetailList(QueryExample queryCriteria) {
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = makePlanDetailService.queryMakePlanInfoDetailList(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 新增调度计划信息
	 */
	@RequestMapping("/add")
	@ResponseBody
	public BSLResult addMakePlanInfoDetail(BslMakePlanInfoDetail bslMakePlanInfoDetail,String planFinistDateM2002) {
		bslMakePlanInfoDetail.setPlanFinistDate(formatDate(planFinistDateM2002));
		if(StringUtils.isBlank(bslMakePlanInfoDetail.getPlanId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "对应指令号不能为空");
		}
		if(StringUtils.isBlank(bslMakePlanInfoDetail.getProdNorm())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "规格不能为空");
		}
		try {
			return makePlanDetailService.add(bslMakePlanInfoDetail);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 格式化日期
	 */
	private Date formatDate(String planFinistDateM2002) {
		Date parse = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			parse = sdf.parse(planFinistDateM2002);
		} catch (ParseException e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			e.printStackTrace();
		}
		return parse;
	}
	
	/**
	 * 修改调度计划信息
	 */
	@RequestMapping("/update")
	@ResponseBody
	public BSLResult updateMakePlanInfoDetail(BslMakePlanInfoDetail bslMakePlanInfoDetail,String planFinistDateM2002) {
		bslMakePlanInfoDetail.setPlanFinistDate(formatDate(planFinistDateM2002));
		if(StringUtils.isBlank(bslMakePlanInfoDetail.getPlanId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "对应指令号不能为空");
		}
		if(StringUtils.isBlank(bslMakePlanInfoDetail.getProdNorm())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "规格不能为空");
		}
		try {
			return makePlanDetailService.update(bslMakePlanInfoDetail);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 删除调度计划信息
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public BSLResult deleteMakePlanInfoDetail(String planInfoDetailId) {
		if(StringUtils.isBlank(planInfoDetailId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "对应指令详细调度编号不能为空");
		}
		try {
			return makePlanDetailService.delete(planInfoDetailId);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
}
