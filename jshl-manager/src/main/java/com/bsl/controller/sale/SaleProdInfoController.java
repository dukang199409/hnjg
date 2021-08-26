package com.bsl.controller.sale;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.sale.SaleDetailService;
import com.bsl.service.sale.SaleProdInfoService;

/**
 * @author 杜康
 * 销售出库产品信息查询管理Controller
 */
@Controller
@RequestMapping("/saleProdByPlan")
public class SaleProdInfoController {
	
	@Autowired
	SaleProdInfoService saleProdInfoService;
	@Autowired
	SaleDetailService saleDetailService;
	
	/**
	 * 根据条件查询销售单出库的产品信息
	 */
	@RequestMapping("outProds")
	@ResponseBody
	public BSLResult getCanOutProds(QueryCriteria criteria){
		if(StringUtils.isBlank(criteria.getPage())) {
			return BSLResult.build(400, "页码不能为空");
		}
		if(StringUtils.isBlank(criteria.getRows())) {
			return BSLResult.build(400, "每页记录数不能为空");
		}
		if(StringUtils.isBlank(criteria.getProdOutPlan()) 
			&& StringUtils.isBlank(criteria.getProdSaleSerno())){
			return BSLResult.build(400, "查询条件不能同时为空");
		}
		return saleProdInfoService.getOutProdsService(criteria);
	}
	
	/**
	 * 根据条件查询销售单出库的产品信息
	 */
	@RequestMapping("outProdGroups")
	@ResponseBody
	public BSLResult getOutProdsGroupService(QueryCriteria criteria){
		if(StringUtils.isBlank(criteria.getPage())) {
			return BSLResult.build(400, "页码不能为空");
		}
		if(StringUtils.isBlank(criteria.getRows())) {
			return BSLResult.build(400, "每页记录数不能为空");
		}
		try {
			return saleProdInfoService.getOutProdsGroupService(criteria);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 根据条件查询外协厂销售单出库的产品信息
	 */
	@RequestMapping("outWxProdGroups")
	@ResponseBody
	public BSLResult getWxOutProdsGroupService(QueryCriteria criteria){
		if(StringUtils.isBlank(criteria.getPage())) {
			return BSLResult.build(400, "页码不能为空");
		}
		if(StringUtils.isBlank(criteria.getRows())) {
			return BSLResult.build(400, "每页记录数不能为空");
		}
		try {
			return saleProdInfoService.getWxOutProdsGroupService(criteria);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 根据条件查询销售单出库的产品信息
	 */
	@RequestMapping("waitSaleProdGroups")
	@ResponseBody
	public BSLResult waitSaleProdGroups(QueryCriteria criteria){
		if(StringUtils.isBlank(criteria.getPage())) {
			return BSLResult.build(400, "页码不能为空");
		}
		if(StringUtils.isBlank(criteria.getRows())) {
			return BSLResult.build(400, "每页记录数不能为空");
		}
		try {
			criteria.setProdStatus(DictItemOperation.产品状态_出库待发货);
			return saleProdInfoService.getOutProdsService(criteria);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 根据条件查询销售单出库的产品信息
	 */
	@RequestMapping("waitSaleWassteGroups")
	@ResponseBody
	public BSLResult waitSaleWassteGroups(String salePlanId){
		try {
			return saleDetailService.getWaitSaleWassteGroups(salePlanId);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 查询销售单出库的产品信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getOutProdsGroupService(Integer page, Integer rows){
		return saleProdInfoService.getOutProdsGroupService(page, rows);
	}
	
	@RequestMapping("/{page}")
	public String showUserPage(@PathVariable String page, HttpServletRequest request) {
		return page;
	}
	
}
