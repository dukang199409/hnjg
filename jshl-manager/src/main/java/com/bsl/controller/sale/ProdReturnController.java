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
import com.bsl.service.sale.ProdReturnService;

/**
 * @author 杜康
 * 磅差处理/退货管理Controller
 */
@Controller
@RequestMapping("/prodReturn")
public class ProdReturnController {
	
	@Autowired
	ProdReturnService prodReturnService;
	
	/**
	 * 新增指定类型销售出库通知详细
	 * @param BslBsPlanInfo
	 * @return
	 */
	@RequestMapping(value="/deal", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult updateProdReturn(BslProductInfo bslProductInfo,String dealType,Float prodPrice) {
		if(StringUtils.isBlank(dealType)) {
			return  BSLResult.build(400, "处理类型不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdOutPlan())) {
			return  BSLResult.build(400, "销售通知单号不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdOutCarno())) {
			return  BSLResult.build(400, "出库车次流水不能为空");
		}
		if(bslProductInfo.getProdRelWeight() == null || bslProductInfo.getProdRelWeight() == 0) {
			return  BSLResult.build(400, "磅差处理重量/退货重量不能为空");
		}
		try {
			return prodReturnService.updateProdReturn(bslProductInfo, dealType,prodPrice);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 根据出库单号获取流水信息
	 * @param prodOutPlan
	 * @return
	 */
	@RequestMapping(value="/carInfo", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult getCarInfo(String prodOutPlan) {
		if(StringUtils.isBlank(prodOutPlan)) {
			return  BSLResult.build(400, "销售通知单号不能为空");
		}
		try {
			return prodReturnService.getCarInfo(prodOutPlan);
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
