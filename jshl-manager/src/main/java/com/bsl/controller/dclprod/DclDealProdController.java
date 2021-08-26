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
import com.bsl.service.dclprod.WxDclProdService;

/**
 * @author 杜康
 * 待处理品管理Controller
 */
@Controller
@RequestMapping("/dclDeal")
public class DclDealProdController {
	
	@Autowired
	private WxDclProdService wxDclProdService;
	
	/**
	 *根据条件查询待处理品信息 
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getDclWxProdServiceByCriteria(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = wxDclProdService.getDclWxProdServiceByCriteria(queryCriteria);
		}
		return result;
	}
	
	/**
	 *单个待处理品处理完成 
	 */
	@RequestMapping("/dealFinO")
	@ResponseBody
	public BSLResult updateFinishStatus(String prodId,String user){
		BSLResult result = null;
		if(StringUtils.isBlank(prodId)) {
			result =  BSLResult.build(400, "待处理品编号不能为空");
		}else{
			result = wxDclProdService.updateFinishStatus(prodId,user);
		}
		return result;
	}
	
	/**
	 *待处理品批量处理完成 
	 */
	@RequestMapping("/dealFinAll")
	@ResponseBody
	public BSLResult updateFinishAllStatus(String[] arrays,String user){
		try {
			return  wxDclProdService.updateFinishAllStatus(arrays,user);
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
