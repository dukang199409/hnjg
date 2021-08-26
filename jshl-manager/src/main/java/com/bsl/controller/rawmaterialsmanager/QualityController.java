package com.bsl.controller.rawmaterialsmanager;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslLunoQuality;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.rawmaterialsmanager.QualityService;
import com.bsl.service.rawmaterialsmanager.RawImportExcelService;

/**
 * @author 杜康
 * 质量信息Controller
 */
@Controller
@RequestMapping("/quality")
public class QualityController {
	
	@Autowired
	private QualityService qualityService;
	
	@Autowired
	private RawImportExcelService rawImportExcelService;
	
	/**
	 * 查询质量信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getAddQualityList(Integer page,Integer rows){		
		return qualityService.getQualityService(page, rows);
	}
		
	/**
	 * 根据条件查询质量信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getQualityList(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = qualityService.getQualityService(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 新增质量信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public BSLResult addQualityInfo(BslLunoQuality bslLunoQuality){
		if(StringUtils.isBlank(bslLunoQuality.getLuId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "炉号不能为空");
		}
		try {
			return qualityService.addQualityInfo(bslLunoQuality);
		} catch (Exception e) {
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 修改质量信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public BSLResult updateQualityInfo(BslLunoQuality bslLunoQuality){
		if(StringUtils.isBlank(bslLunoQuality.getLuId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "炉号不能为空");
		}
		try {
			return qualityService.updateQualityInfo(bslLunoQuality);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 删除质量信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public BSLResult deleteQualityInfo(String luId){
		if(StringUtils.isBlank(luId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "炉号不能为空");
		}
		try {
			return qualityService.deleteQualityInfo(luId);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 质量信息批量导入
	 * @param 
	 * @param 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/import",method = RequestMethod.POST)
	@ResponseBody
	public BSLResult updateProdLuQuality(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		BSLResult bslResult = new BSLResult();
		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
		//MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("importExcel");
		response.reset();//非常重要
        response.setContentType("multipart/form-data");
		try {
			multipartRequest.setAttribute("upload", "1");
			multipartRequest.setAttribute("message", bslResult.getMsg());
			bslResult = rawImportExcelService.updateProdLuQuality(file);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			multipartRequest.setAttribute("upload", "0");
			multipartRequest.setAttribute("message", e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
		
		return bslResult;
	}
	
	@RequestMapping("/{page}")
	public String showUserPage(@PathVariable String page, HttpServletRequest request) {
		return page;
	}
	
}
