package com.bsl.controller.param;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslParamInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.enums.ParamService;

/**
 * 系统参数信息
 * @author duk
 * @date 2019年3月28日  
 *
 */
@Controller
@RequestMapping("/param")
public class ParamController {
	
	@Autowired
	private ParamService paramService;
	
	@RequestMapping("/{page}")
	public String showPlanPage(@PathVariable String page, HttpServletRequest request) {
		return page;
	}
	
	/**
	 * 根据条件查询纵剪带生产指令
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getParamListByCriteria(QueryCriteria queryCriteria) {
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = paramService.getParamListByCriteria(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 修改参数信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/editParam")
	@ResponseBody
	public BSLResult editPraramInfo(BslParamInfo bslParamInfo){
		if(StringUtils.isBlank(bslParamInfo.getParamId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "参数编码不能为空");
		}
		if(StringUtils.isBlank(bslParamInfo.getParamName())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "参数名称不能为空");
		}
		if(StringUtils.isBlank(bslParamInfo.getParamValue())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "参数值不能为空");
		}
		try {
			return paramService.editParam(bslParamInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}

	
	
}
