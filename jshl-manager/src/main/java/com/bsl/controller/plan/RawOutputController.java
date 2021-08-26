package com.bsl.controller.plan;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslProductInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryExample;
import com.bsl.service.plan.RawOutputService;

/**
 * 
 * @author huangyl
 * @date 2019年5月2日  
 *
 */
@Controller
@RequestMapping("/rawOutput")
public class RawOutputController {
	
	@Autowired
	private RawOutputService rawOutputService;
	
	@RequestMapping("/{page}")
	public String showPlanDetailPage(@PathVariable String page) {
		return page;
	}
	
	/**
	 * 卷板出库
	 */
	@RequestMapping("/out")
	@ResponseBody
	public BSLResult outPut(String prodId,String planJz,String prodInputuser) {
		try {
			if(StringUtils.isBlank(prodId)) {
				return  BSLResult.build(400, "原料物料编码不能为空");
			}
			if(StringUtils.isBlank(planJz)) {
				return  BSLResult.build(400, "生产机组不能为空");
			}
			return rawOutputService.updateRawStatusService(prodId,planJz,prodInputuser,1);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 卷板未用退回
	 */
	@RequestMapping("/reInt")
	@ResponseBody
	public BSLResult reInt(String prodId,String prodInputuser) {
		try {
			if(StringUtils.isBlank(prodId)) {
				return  BSLResult.build(400, "原料物料编码不能为空");
			}
			return rawOutputService.updateRawStatusService(prodId,null,prodInputuser,2);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 卷板完成
	 */
	@RequestMapping("/finish")
	@ResponseBody
	public BSLResult finish(String prodId,String prodInputuser) {
		try {
			if(StringUtils.isBlank(prodId)) {
				return  BSLResult.build(400, "原料物料编码不能为空");
			}
			return rawOutputService.updateRawStatusService(prodId,null,prodInputuser,3);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 获取符合条件的卷板库存信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public BSLResult queryRawOutputList(QueryExample queryCriteria) {
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getPlanJz())) {
			result =  BSLResult.build(400, "生产机组不能为空");
		}else{
			try{
				result = rawOutputService.queryBslProductInfoList(queryCriteria);
			}catch (Exception e) {
				DictItemOperation.log.info("===========异常:"+e.getMessage());
				return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
			}
		}
		return result;
	}
	
	/**
	 * 根据原料物料编码查询卷板信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public BSLResult prodGetById(String prodId){
		try {
			if(StringUtils.isBlank(prodId)) {
				return  BSLResult.build(400, "原料物料编码不能为空");
			}
			BslProductInfo bslProductInfo = rawOutputService.getProdPlanByPK(prodId);
			if(bslProductInfo == null){
				return  BSLResult.build(400, "根据原料物料编码查询内容为空");
			}
			return BSLResult.ok(bslProductInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
}
