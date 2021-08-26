package com.bsl.controller.report;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.select.QueryCriteria;
import com.bsl.service.report.ReportService;

/**
 * @author 杜康
 * 报表查询Controller
 */
@Controller
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private ReportService reportService;

	/**
	 * 查询库存日照信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/listM8001")
	@ResponseBody
	public EasyUIDataGridResult getProdInfoList(Integer page,Integer rows){		
		return reportService.getAllProductPhotoService(page, rows);
	}
		
	/**
	 * 根据条件查询库存日照信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteriaM8001")
	@ResponseBody
	public BSLResult getProdInfoList(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = reportService.getProductPhotoService(queryCriteria);
		}
		return result;
	} 
	
	/**
	 * 查询库存变动日汇总信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/listM8002")
	@ResponseBody
	public EasyUIDataGridResult getStockChangeInfoList(Integer page,Integer rows){		
		return reportService.getAllStockChangeInfoService(page, rows);
	}
		
	/**
	 * 根据条件查询库存变动日汇总信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteriaM8002")
	@ResponseBody
	public BSLResult getStockChangeInfoList(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = reportService.getStockChangeInfoService(queryCriteria);
		}
		return result;
	} 
	
	/**
	 * 查询库存变动汇总信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/listM8003")
	@ResponseBody
	public EasyUIDataGridResult getStockChangeSumInfoList(Integer page,Integer rows){		
		return reportService.getAllStockChangeSumService(page, rows);
	}
		
	/**
	 * 根据条件查询库存变动汇总信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteriaM8003")
	@ResponseBody
	public BSLResult getStockChangeSumInfoList(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = reportService.getStockChangeSumService(queryCriteria);
		}
		return result;
	} 
	
	/**
	 * 查询单炉报表信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/listM8004")
	@ResponseBody
	public EasyUIDataGridResult getLuStockInfoList(Integer page,Integer rows){		
		return reportService.getAllLuStockInfoService(page, rows);
	}
		
	/**
	 * 根据条件查询单炉报表信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteriaM8004")
	@ResponseBody
	public BSLResult getLuStockInfoList(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = reportService.getLuStockInfoService(queryCriteria);
		}
		return result;
	}
	
}
