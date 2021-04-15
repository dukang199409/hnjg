package com.bsl.controller.status;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslChangeStatusRecord;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.service.status.ChangeStatusService;

/**
 * 产品状态维护
 * @author 杜康
 *
 */
@Controller
@RequestMapping("/changeStatus")
public class ChangeStatusController {
	
	@Autowired
	private ChangeStatusService changeStatusService;
	
	/**
	 * 根据编号获取产品信息
	 */
	@RequestMapping("/getProdInfo")
	@ResponseBody
	public BSLResult getProdInfoByPk(String prodId) {
		if(StringUtils.isBlank(prodId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品编号不能为空");
		}
		try {
			return changeStatusService.getProdInfoByPk(prodId);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 实物状态维护
	 */
	@RequestMapping("/change")
	@ResponseBody
	public BSLResult addUserTypeInfo(BslChangeStatusRecord bslChangeStatusRecord) {
		if(StringUtils.isBlank(bslChangeStatusRecord.getChangeProdId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品编号不能为空");
		}
		if(StringUtils.isBlank(bslChangeStatusRecord.getBeforeStatus())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "维护前状态不能为空");
		}
		if(StringUtils.isBlank(bslChangeStatusRecord.getAfterStatus())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "维护后状态不能为空");
		}
		try {
			return changeStatusService.changeProdStatus(bslChangeStatusRecord);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
}
