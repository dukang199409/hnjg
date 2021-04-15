package com.bsl.controller.rawmaterialsmanager;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslSendCheckInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.rawmaterialsmanager.SendService;

/**
 * @author 杜康
 * 送检标志Controller
 */
@Controller
@RequestMapping("/sendFlag")
public class SendController {
	
	@Autowired
	private SendService sendService;
	
	/**
	 * 根据条件查询送检信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getSendList(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = sendService.getSendService(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 新增送检信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public BSLResult addSendInfo(BslSendCheckInfo bslSendCheckInfo){
		if(StringUtils.isBlank(bslSendCheckInfo.getLuId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "炉号不能为空");
		}
		try {
			return sendService.addSendInfo(bslSendCheckInfo);
		} catch (Exception e) {
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 修改送检信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public BSLResult updateSendInfo(BslSendCheckInfo bslSendCheckInfo){
		if(StringUtils.isBlank(bslSendCheckInfo.getLuId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "炉号不能为空");
		}
		try {
			return sendService.updateSendInfo(bslSendCheckInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 删除送检信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public BSLResult deleteSendInfo(String luId){
		if(StringUtils.isBlank(luId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "炉号不能为空");
		}
		try {
			return sendService.deleteSendInfo(luId);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	@RequestMapping("/{page}")
	public String showUserPage(@PathVariable String page, HttpServletRequest request) {
		return page;
	}
	
}
