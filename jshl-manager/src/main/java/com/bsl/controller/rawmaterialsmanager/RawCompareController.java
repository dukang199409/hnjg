package com.bsl.controller.rawmaterialsmanager;

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
import com.bsl.service.rawmaterialsmanager.RawCompareService;

/**
 * @author 杜康
 * 卷板重量比对Controller
 */
@Controller
@RequestMapping("/rawcompare")
public class RawCompareController {
	
	@Autowired
	private RawCompareService rawCompareService;
	
	/**
	 * 根据条件查询入库单信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listDetail")
	@ResponseBody
	public BSLResult getRawList(QueryCriteria queryCriteria){
		BSLResult result = null;
		try {
			result = rawCompareService.getProdInfoService(queryCriteria);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/{page}")
	public String showUserPage(@PathVariable String page, HttpServletRequest request) {
		return page;
	}
	
}
