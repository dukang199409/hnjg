package com.bsl.controller.serach;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.select.QueryCriteria;
import com.bsl.service.serach.ChangeInfoSerachService;

/**
 * @author 杜康
 * 库存详细信息查询Controller
 */
@Controller
@RequestMapping("/changeInfo")
public class ChangeInfoController {
	
	@Autowired
	private ChangeInfoSerachService changeInfoSerachService;
	
	/**
	 * 查询库存变动信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getProdInfoList(Integer page,Integer rows){		
		return changeInfoSerachService.getAllInfoService(page, rows);
	}
		
	/**
	 * 根据条件查询库存变动信息
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
			result =changeInfoSerachService.getInfoByCriteriaService(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 根据条件查询库存变动历史信息
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
			result =changeInfoSerachService.getInfoByCriteriaHService(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 查询状态变动信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/listM7003")
	@ResponseBody
	public EasyUIDataGridResult getAllStatusChangeInfoService(Integer page,Integer rows){		
		return changeInfoSerachService.getAllStatusChangeInfoService(page, rows);
	}
		
	/**
	 * 根据条件查询库存变动信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteriaM7003")
	@ResponseBody
	public BSLResult getStatusChangeInfoService(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result =changeInfoSerachService.getStatusChangeInfoService(queryCriteria);
		}
		return result;
	}
	
}
