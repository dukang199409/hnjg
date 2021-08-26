package com.bsl.controller.sale;

import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslBsPlanInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.sale.SalePlanService;

/**
 * @author 杜康
 * 销售出库通知单制定管理Controller
 */
@Controller
@RequestMapping("/salePlan")
public class SalePlanController {
	
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
			result = salePlanService.getSalePlanService(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 新增销售出库通知单
	 * @param BslBsPlanInfo
	 * @return
	 */
	@RequestMapping(value="/addsalePlan", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult addSalePlanInfo(BslBsPlanInfo bslBsPlanInfo,String bsArrdateAddM5001) {
		if(!StringUtils.isBlank(bsArrdateAddM5001)){
			try {
				bslBsPlanInfo.setBsArrdate(DictItemOperation.日期转换实例.parse(bsArrdateAddM5001));
			} catch (ParseException e) {
				DictItemOperation.log.info("===========异常:"+e.getMessage());
				e.printStackTrace();
			}
		}
		if(StringUtils.isBlank(bslBsPlanInfo.getBsFlag())) {
			return BSLResult.build(400, "销售出库通知单产品类别不能为空");
		}
		try {
			return salePlanService.addSalePlanInfo(bslBsPlanInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 销售通知调度指令详情
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public BSLResult getSalePlanDetailByBsBsId(String bsId) {
		if(StringUtils.isBlank(bsId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "销售通知单号不能为空");
		}
		try {
			 return salePlanService.getSalePlanDetailByBsBsId(bsId);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 刷新出库数量和重量
	 */
	@RequestMapping("/reload")
	@ResponseBody
	public BSLResult updateReloadSalePlanDetail(String bsId) {
		if(StringUtils.isBlank(bsId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "销售通知单号不能为空");
		}
		try {
			 return salePlanService.updateReloadSalePlanDetail(bsId);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	
	/**
	 * 修改销售出库通知单
	 * @param BslBsPlanInfo
	 * @return
	 */
	@RequestMapping(value="/editsalePlan", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult editSalePlanInfo(BslBsPlanInfo bslBsPlanInfo,String crtDateM5001EditText,String bsArrdateEditTextM5001) {
		if(!StringUtils.isBlank(crtDateM5001EditText)){
			try {
				bslBsPlanInfo.setCrtDate(DictItemOperation.日期转换实例.parse(crtDateM5001EditText));
			} catch (ParseException e) {
				DictItemOperation.log.info("===========异常:"+e.getMessage());
				e.printStackTrace();
			}
		}
		if(!StringUtils.isBlank(bsArrdateEditTextM5001)){
			try {
				bslBsPlanInfo.setBsArrdate(DictItemOperation.日期转换实例.parse(bsArrdateEditTextM5001));
			} catch (ParseException e) {
				DictItemOperation.log.info("===========异常:"+e.getMessage());
				e.printStackTrace();
			}
		}
		if(StringUtils.isBlank(bslBsPlanInfo.getBsId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "销售出库通知单号不能为空");
		}
		if(StringUtils.isBlank(bslBsPlanInfo.getBsFlag())) {
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "销售出库通知单产品类别不能为空");
		}
		try {
			return salePlanService.updateSalePlanInfo(bslBsPlanInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 删除销售出库通知单
	 * @param BslBsPlanInfo
	 * @return
	 */
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult deleteSalePlanInfo(BslBsPlanInfo bslBsPlanInfo) {
		if(StringUtils.isBlank(bslBsPlanInfo.getBsId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "销售出库通知单号不能为空");
		}
		try {
			return salePlanService.deleteSalePlanInfo(bslBsPlanInfo);
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
