package com.bsl.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslProductInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.service.serach.ProdSerachService;

/**
 * 半成品指令制定管理
 * @author duk
 * @date 2019年3月28日  
 *
 */
@Controller
@RequestMapping("/leftInfo")
public class GetCountAndWeight {
	
	@Autowired
	private ProdSerachService prodSerachService;
	
	/**
	 * 纵剪带制单校验
	 */
	@RequestMapping("/rawInfo")
	@ResponseBody
	public BSLResult leftRawInfo(BslProductInfo bslProductInfo) {
		try {
			return prodSerachService.getLeftInfo(bslProductInfo, "1");
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 产品制单校验
	 */
	@RequestMapping("/haflProdInfo")
	@ResponseBody
	public BSLResult leftHaflProdInfo(BslProductInfo bslProductInfo) {
		try {
			return prodSerachService.getLeftInfo(bslProductInfo, "2");
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
}
