package com.bsl.controller.dclprod;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.utils.BSLResult;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.dclprod.WxProdJsService;

/**
 * @author 杜康
 * 外协厂接收Controller
 */
@Controller
@RequestMapping("/prodWxJs")
public class WxProdJsController {
	
	@Autowired
	private WxProdJsService wxProdJsService;
	
	/**
	 *根据条件查询通知单信息 （7,8,9）
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getProdServiceByCriteria(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getProdOutPlan())){
			result = wxProdJsService.getWxTzdJsServiceByCriteria(queryCriteria);
		}
		return result;
	}
	
	/**
	 *根据通知单号查询废品详细流水信息
	 */
	@RequestMapping("/wasteByBsId")
	@ResponseBody
	public BSLResult wasteByBsId(String salePlanId){
		BSLResult result = wxProdJsService.getWasteSaleInfoByBsId(salePlanId);
		return result;
	}
	
	/**
	 *废品接收
	 */
	@RequestMapping("/jsWaste")
	@ResponseBody
	public BSLResult jsWaste(String saleSerno,Float prodOutWeight,String inputUser){
		if(StringUtils.isBlank(saleSerno)) {
			return BSLResult.build(400, "出库详细流水不能为空");
		}
		if(prodOutWeight == null || prodOutWeight == 0f) {
			return BSLResult.build(400, "本次接收重量不能为空");
		}
		try {
			return wxProdJsService.updateJsWaste(saleSerno,prodOutWeight,inputUser);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 *废品接收退回
	 */
	@RequestMapping("/rebackWaste")
	@ResponseBody
	public BSLResult rebackWaste(String saleSerno,Float prodOutWeight,String inputUser){
		if(StringUtils.isBlank(saleSerno)) {
			return BSLResult.build(400, "出库详细流水不能为空");
		}
		if(prodOutWeight == null || prodOutWeight == 0f) {
			return BSLResult.build(400, "本次退回重量不能为空");
		}
		try {
			return wxProdJsService.updateRebackWaste(saleSerno,prodOutWeight,inputUser);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 *可以接收入库的产品查询
	 */
	@RequestMapping("/canJsProds")
	@ResponseBody
	public BSLResult canJsProds(QueryCriteria queryCriteria){
		if(StringUtils.isBlank(queryCriteria.getProdOutPlan())) {
			return BSLResult.build(400, "出库通知单号不能为空");
		}
		try {
			return wxProdJsService.getWxCanJsProds(queryCriteria);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 *已经接收入库的产品查询
	 */
	@RequestMapping("/alreadyJsProds")
	@ResponseBody
	public BSLResult alreadyJsProds(QueryCriteria queryCriteria){
		if(StringUtils.isBlank(queryCriteria.getProdOutPlan())) {
			return BSLResult.build(400, "出库通知单号不能为空");
		}
		try {
			return wxProdJsService.getWxAlreadyJsProds(queryCriteria);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 转场入库接收
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/prodJsPl")
	@ResponseBody
	public BSLResult prodJsPl(String prodOutPlan,String prodCheckuser,String[] arrays){
		if(StringUtils.isBlank(prodOutPlan)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "出库通知单号不能为空");
		}
		try {
			return wxProdJsService.updateWxJsProdsBSLResult(prodOutPlan,prodCheckuser,arrays);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 转场入库接收退回
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/reBack")
	@ResponseBody
	public BSLResult reBack(String prodId,String checkuser){
		if(StringUtils.isBlank(prodId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "退回产品编号不能为空");
		}
		try {
			return wxProdJsService.updateWxRebackProdsBSLResult(prodId,checkuser);
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
