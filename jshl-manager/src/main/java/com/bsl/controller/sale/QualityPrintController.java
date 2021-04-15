package com.bsl.controller.sale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslCarRetchaInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.sale.QualityPrintService;

/**
 * @author 杜康
 * 质量通知单打印管理Controller
 */
@Controller
@RequestMapping("/qualityPrint")
public class QualityPrintController {
	
	@Autowired
	QualityPrintService qualityPrintService;
	
	/**
	 * 初始化查询车次流水信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getCarSerno(Integer page, Integer rows){
		return qualityPrintService.getCarSernoService(page, rows);
	}
	
	/**
	 * 根据条件查询销售出库通知单信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getCarSernoService(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = qualityPrintService.getCarSernoServiceByCriteria(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 查询某一车次发货详情
	 * @param BslBsPlanInfo
	 * @return
	 */
	@RequestMapping(value="/detail", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult getCarDeatilInfo(String prodOutCarno) {
		if(StringUtils.isBlank(prodOutCarno)){
			return BSLResult.build(400, "出库车次流水不能为空");
		}
		try {
			return qualityPrintService.getCarDeatilInfo(prodOutCarno);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 修改车次信息
	 * @param BslBsPlanInfo
	 * @return
	 */
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult updateCarDeatilInfo(BslCarRetchaInfo bslCarRetchaInfo) {
		if(StringUtils.isBlank(bslCarRetchaInfo.getCarNo())){
			return BSLResult.build(400, "出库车次流水不能为空");
		}
		if(StringUtils.isBlank(bslCarRetchaInfo.getBsGettype())){
			return BSLResult.build(400, "运送方式不能为空");
		}
		try {
			return qualityPrintService.updateCarDeatilInfo(bslCarRetchaInfo);
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
