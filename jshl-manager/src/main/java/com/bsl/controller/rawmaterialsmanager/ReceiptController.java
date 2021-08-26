package com.bsl.controller.rawmaterialsmanager;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsl.common.SpringContextUtils;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.controller.common.AutoEnumUtil;
import com.bsl.pojo.BslBsPlanInfo;
import com.bsl.pojo.BslMakePlanInfoDetail;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.rawmaterialsmanager.ReceiptService;

/**
 * @author 杜康
 * 原料入库通知单管理Controller
 */
@Controller
@RequestMapping("/receipt")
public class ReceiptController {
	
	@Autowired
	private ReceiptService receiptService;
	
	
	/**
	 * 查询原料入库通知单信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getReceiptList(Integer page, Integer rows){
		return receiptService.getReceiptService(page,rows);
	}
	
	/**
	 * 根据条件查询原料入库通知单信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getReceiptList(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = receiptService.getReceiptService(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 原料入库通知单详情
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public BSLResult getMakePlanDetailInfo(String bsId) {
		if(StringUtils.isBlank(bsId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "原料入库通知单号不能为空");
		}
		try {
			return receiptService.getMakePlanInfoDetail(bsId);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 新增入库通知单
	 * @param BslBsPlanInfo
	 * @return
	 */
	@RequestMapping(value="/addReceipt", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult addReceiptInfo(BslBsPlanInfo bslBsPlanInfo,String bsArrdateAdd) {
		if(!StringUtils.isBlank(bsArrdateAdd)){
			try {
				bslBsPlanInfo.setBsArrdate(DictItemOperation.日期转换实例.parse(bsArrdateAdd));
			} catch (ParseException e) {
				DictItemOperation.log.info("===========异常:"+e.getMessage());
				e.printStackTrace();
			}
		}
		if(StringUtils.isBlank(bslBsPlanInfo.getBsFlag())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "入库卷板类别不能为空");
		}
		if(bslBsPlanInfo.getBsWeight()== null || bslBsPlanInfo.getBsWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "单号记载重量不能为空");
		}
		if(bslBsPlanInfo.getBsAmt()== null || bslBsPlanInfo.getBsAmt() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "单号卷数不能为空");
		}
		try {
			return receiptService.addReceiptInfo(bslBsPlanInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 修改入库通知单
	 * @param BslBsPlanInfo
	 * @return
	 */
	@RequestMapping(value="/editReceipt", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult editReceiptInfo(BslBsPlanInfo bslBsPlanInfo,String crtDateM1001EditText,String bsArrdateEditText) {
		if(!StringUtils.isBlank(crtDateM1001EditText)){
			try {
				bslBsPlanInfo.setCrtDate(DictItemOperation.日期转换实例.parse(crtDateM1001EditText));
			} catch (ParseException e) {
				DictItemOperation.log.info("===========异常:"+e.getMessage());
				e.printStackTrace();
			}
		}
		if(!StringUtils.isBlank(bsArrdateEditText)){
			try {
				bslBsPlanInfo.setBsArrdate(DictItemOperation.日期转换实例.parse(bsArrdateEditText));
			} catch (ParseException e) {
				DictItemOperation.log.info("===========异常:"+e.getMessage());
				e.printStackTrace();
			}
		}
		if(StringUtils.isBlank(bslBsPlanInfo.getBsFlag())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "入库卷板类别不能为空");
		}
		if(StringUtils.isBlank(bslBsPlanInfo.getBsId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "原料入库通知单号不能为空");
		}
		if(bslBsPlanInfo.getBsWeight()== null || bslBsPlanInfo.getBsWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "单号记载重量不能为空");
		}
		if(bslBsPlanInfo.getBsAmt()== null || bslBsPlanInfo.getBsAmt() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "单号卷数不能为空");
		}
		try {
			return receiptService.updateReceiptInfo(bslBsPlanInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 删除入库通知单
	 * @param BslBsPlanInfo
	 * @return
	 */
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult deleteReceiptInfo(BslBsPlanInfo bslBsPlanInfo) {
		try {
			return receiptService.deleteReceiptInfo(bslBsPlanInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	@RequestMapping("/{page}")
	public String showUserPage(@PathVariable String page) {
		return page;
	}
	
}
