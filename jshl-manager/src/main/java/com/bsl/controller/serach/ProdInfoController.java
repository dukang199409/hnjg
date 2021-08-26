package com.bsl.controller.serach;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.select.QueryCriteria;
import com.bsl.service.serach.ProdInfoSerachService;

/**
 * @author 杜康
 * 产品信息查询Controller
 */
@Controller
@RequestMapping("/prodInfo")
public class ProdInfoController {
	
	@Autowired
	private ProdInfoSerachService prodInfoSerachService;
	
	/**
	 * 查询信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getProdInfoList(Integer page,Integer rows){		
		return prodInfoSerachService.getAllInfoService(page, rows);
	}
		
	/**
	 * 根据条件查询信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getProdInfoList(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result =prodInfoSerachService.getInfoByCriteriaService(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 根据条件查询历史产品信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listH")
	@ResponseBody
	public BSLResult getProdInfoHList(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result =prodInfoSerachService.getProdInfoHList(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 查询信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/listM7004")
	@ResponseBody
	public EasyUIDataGridResult getProdInInfoList(Integer page,Integer rows){		
		return prodInfoSerachService.getAllInInfoService(page, rows);
	}
		
	/**
	 * 根据条件查询信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteriaM7004")
	@ResponseBody
	public BSLResult getProdInInfoList(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result =prodInfoSerachService.getInInfoByCriteriaService(queryCriteria);
		}
		return result;
	}
	
}
