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
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.sale.SaleWasteService;

/**
 * @author 杜康
 * 废品销售出库管理Controller
 */
@Controller
@RequestMapping("/saleWaste")
public class SaleWasteController {
	
	@Autowired
	SaleWasteService saleWasteService;
	
	/**
	 * 根据条件查询废品出库计划
	 */
	@RequestMapping("listByCriteria")
	@ResponseBody
	public BSLResult getOutProds(QueryCriteria criteria){
		if(StringUtils.isBlank(criteria.getPage())) {
			return BSLResult.build(400, "页码不能为空");
		}
		if(StringUtils.isBlank(criteria.getRows())) {
			return BSLResult.build(400, "每页记录数不能为空");
		}
		return saleWasteService.getOutWastesService(criteria);
	}
	
	/**
	 * 废品销售出库
	 */
	@RequestMapping(value="/out", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult saleWasteOutPut(String saleSerno,Float prodOutWeight,String inputUser) {
		if(StringUtils.isBlank(saleSerno)) {
			return  BSLResult.build(400, "详细计划编号不能为空");
		}
		if(prodOutWeight == null || prodOutWeight == 0) {
			return BSLResult.build(400, "废品销售出库重量不能为空");
		}
		try {
			return saleWasteService.updateSaleWasteOutPut(saleSerno, prodOutWeight, inputUser);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 废品销售出库
	 */
	@RequestMapping(value="/reback", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult reBackSaleWaste(String saleSerno,Float prodRebackWeight,String inputUser) {
		if(StringUtils.isBlank(saleSerno)) {
			return  BSLResult.build(400, "详细计划编号不能为空");
		}
		if(prodRebackWeight == null || prodRebackWeight == 0) {
			return BSLResult.build(400, "废品退回重量不能为空");
		}
		try {
			return saleWasteService.updateReBackSaleWaste(saleSerno, prodRebackWeight, inputUser);
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
