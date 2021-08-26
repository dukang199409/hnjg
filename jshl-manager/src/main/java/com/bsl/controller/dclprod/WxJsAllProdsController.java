package com.bsl.controller.dclprod;


import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.utils.BSLResult;
import com.bsl.select.QueryCriteria;
import com.bsl.service.dclprod.WxJsAllProdsService;

/**
 * @author 杜康
 * 外协厂接受产品/待处理品管理Controller
 */
@Controller
@RequestMapping("/wxJsProdsManager")
public class WxJsAllProdsController {
	
	@Autowired
	private WxJsAllProdsService wxJsAllProdsService;
	
	/**
	 *根据条件查询外协厂接受产品/待处理品
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getProdServiceByCriteria(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = wxJsAllProdsService.getWxJsAllProds(queryCriteria);
		}
		return result;
	}
	
	
	@RequestMapping("/{page}")
	public String showUserPage(@PathVariable String page, HttpServletRequest request) {
		return page;
	}
	
}
