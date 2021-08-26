package com.bsl.controller.enums;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.dao.JedisClient;
import com.bsl.pojo.BslEnumInfo;
import com.bsl.pojo.BslEnumInfoExample;
import com.bsl.pojo.BslMakePlanInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.select.QueryExample;
import com.bsl.service.enums.EnumService;

@Controller
public class EnumController {
	
	@Autowired
	private EnumService enumService;
	
	@Autowired
	private JedisClient jedisClient;
	
	/**
	 * 测试使用，暂未使用
	 * @param enumEngName
	 * @return
	 */
	@RequestMapping("/enum/queryKey/{enumEngName}")
	@ResponseBody
	public BSLResult getBslEnumInfoByEngName(@PathVariable String enumEngName){
		return BSLResult.ok(enumService.getBslEnumInfoByEngName(enumEngName));
	}
	
	/**
	 * 前台表格formatter枚举转换，动态获取redis中存储的枚举
	 * @param enumEngName
	 * @param enumKey
	 * @return
	 */
	@RequestMapping(value="/enum/queryValue/{enumEngName}/{enumKey}", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getBslEnumInfoByEngNameForRedis(@PathVariable String enumEngName,@PathVariable String enumKey){
		String enumValue = jedisClient.get(enumEngName+":"+enumKey);
		return StringUtils.isBlank(enumValue) ? enumKey : enumValue;
	}
	
	/**
	 * 查询数据字典信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/enum/list")
	@ResponseBody
	public EasyUIDataGridResult getEnumList(Integer page, Integer rows){
		return enumService.getEnumList(page,rows);
	}
	
	/**
	 * 根据条件查询数据字典信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/enum/listByCriteria")
	@ResponseBody
	public BSLResult getEnumList(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = enumService.getEnumListByCriteria(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 新增数据字典子项信息
	 */
	@RequestMapping("/enum/add")
	@ResponseBody
	public BSLResult addEnumInfo(BslEnumInfo bslEnumInfo) {
		if(StringUtils.isBlank(bslEnumInfo.getEnumEnglishName())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "数组字典英文名称不能为空");
		}
		if(StringUtils.isBlank(bslEnumInfo.getEnumChineseName())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "数组字典中文名称不能为空");
		}
		if(StringUtils.isBlank(bslEnumInfo.getEnumKey())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "数组字典key值不能为空");
		}
		if(StringUtils.isBlank(bslEnumInfo.getEnumValue())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "数组字典value值不能为空");
		}
		try {
			return enumService.addEnum(bslEnumInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 修改数据字典子项信息
	 */
	@RequestMapping("/enum/edit")
	@ResponseBody
	public BSLResult editEnumInfo(BslEnumInfo bslEnumInfo) {
		if(StringUtils.isBlank(bslEnumInfo.getEnumEnglishName())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "数组字典英文名称不能为空");
		}
		if(StringUtils.isBlank(bslEnumInfo.getEnumChineseName())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "数组字典中文名称不能为空");
		}
		if(StringUtils.isBlank(bslEnumInfo.getEnumKey())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "数组字典key值不能为空");
		}
		if(StringUtils.isBlank(bslEnumInfo.getEnumValue())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "数组字典value值不能为空");
		}
		try {
			return enumService.editEnum(bslEnumInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 删除数据字典子项信息
	 */
	@RequestMapping("/enum/delete")
	@ResponseBody
	public BSLResult deleteMakePlanInfo(String[] arrays) {
		try {
			return enumService.deleteEnum(arrays);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 一键同步
	 * @return
	 */
	@RequestMapping("/enum/synchData")
	@ResponseBody
	public BSLResult synchData() {
		try {
			return enumService.synchData();
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
}
