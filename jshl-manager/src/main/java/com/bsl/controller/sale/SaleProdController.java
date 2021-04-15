package com.bsl.controller.sale;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslProductInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.rawmaterialsmanager.RawService;
import com.bsl.service.rawmaterialsmanager.ReceiptService;
import com.bsl.service.sale.SaleProdService;

/**
 * @author 杜康
 * 销售产品出库管理Controller
 */
@Controller
@RequestMapping("/saleProd")
public class SaleProdController {
	
	@Autowired
	SaleProdService saleProdService;
	
	@Autowired
	ReceiptService receiptService;
	
	@Autowired
	RawService rawService;
	
	/**
	 * 根据条件查询可以出库的产品信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("canOutProds")
	@ResponseBody
	public BSLResult getCanOutProds(QueryCriteria criteria){
		if(StringUtils.isBlank(criteria.getPage())) {
			return BSLResult.build(400, "页码不能为空");
		}
		if(StringUtils.isBlank(criteria.getRows())) {
			return BSLResult.build(400, "每页记录数不能为空");
		}
		if(StringUtils.isBlank(criteria.getSalePlanId()) 
			&& StringUtils.isBlank(criteria.getSaleSerno())
				&& StringUtils.isBlank(criteria.getProdId())){
			return BSLResult.build(400, "除产品状态外的查询条件不能同时为空");
		}
		return saleProdService.getCanOutProdsService(criteria);
	}
	
	/**
	 * 根据条件查询已经出库的产品信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("outProds")
	@ResponseBody
	public BSLResult getOutProds(QueryCriteria criteria){
		if(StringUtils.isBlank(criteria.getPage())) {
			return BSLResult.build(400, "页码不能为空");
		}
		if(StringUtils.isBlank(criteria.getRows())) {
			return BSLResult.build(400, "每页记录数不能为空");
		}
		return saleProdService.getOutProdsService(criteria);
	}
	
	/**
	 * 发货出库
	 */
	@RequestMapping(value="/saleOut", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult saleOut(String prodId,String salePlanId,String saleSerno,String prodCheckuser,Float prodOutWeight) {
		if(StringUtils.isBlank(prodId)) {
			return  BSLResult.build(400, "销售产品编号不能为空");
		}
		if(StringUtils.isBlank(salePlanId) && StringUtils.isBlank(saleSerno)) {
			return BSLResult.build(400, "销售单号与详细计划不能同时为空");
		}
		try {
			return saleProdService.updateSaleProdOutPut(prodId,salePlanId,saleSerno,prodCheckuser,prodOutWeight);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 批量发货出库
	 */
	@RequestMapping(value="/prodOutPl", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult prodOutPl(String salePlanId,String saleSerno,String prodCheckuser,String[] arrays) {
		if(StringUtils.isBlank(salePlanId) && StringUtils.isBlank(saleSerno)) {
			return BSLResult.build(400, "销售单号与详细计划不能同时为空");
		}
		try {
			return saleProdService.prodOutPl(salePlanId,saleSerno,prodCheckuser,arrays);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 产品复磅
	 */
	@RequestMapping(value="/saleFb", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult saleFb(String prodId,Float prodOutWeight) {
		if(StringUtils.isBlank(prodId)) {
			return  BSLResult.build(400, "销售产品编号不能为空");
		}
		if(prodOutWeight == null || prodOutWeight == 0) {
			return BSLResult.build(400, "销售出库重量不能为空");
		}
		try {
			return saleProdService.saleProdFb(prodId,prodOutWeight);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 根据编号数组获取产品信息
	 */
	@RequestMapping(value="/getByProdIds", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult getByProdIds(String[] arrays) {
		if(arrays.length <= 0) {
			return  BSLResult.build(400, "没有要出库的列表记录!");
		}
		try {
			return saleProdService.getByProdIds(arrays);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 误发退回
	 * @return
	 */
	@RequestMapping(value="/reBack", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult reBackSaleProd(String prodSaleSerno,String prodId,String checkuser) {
		BSLResult result = null;
		if(StringUtils.isBlank(prodId)) {
			return  BSLResult.build(400, "产品编号不能为空");
		}
		try {
			result  = saleProdService.updateReBackSaleProd(prodSaleSerno, prodId, checkuser);
		}catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
		return result;
	}
	
	/**
	 * 扫码获取出库详细信息
	 * @return
	 */
	@RequestMapping(value="/getByProdId", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult reBackSaleProd(String prodId) {
		if(StringUtils.isBlank(prodId)) {
			return  BSLResult.build(400, "产品编号不能为空");
		}
		BslProductInfo bslProductInfo  = rawService.queryByPrimaryKey(prodId);
		if(bslProductInfo == null){
			return  BSLResult.build(ErrorCodeInfo.错误类型_查询无记录, "根据产品编号查询记录为空");
		}
		return BSLResult.ok(bslProductInfo);
	}
	
	@RequestMapping("/{page}")
	public String showUserPage(@PathVariable String page, HttpServletRequest request) {
		return page;
	}
	
}
