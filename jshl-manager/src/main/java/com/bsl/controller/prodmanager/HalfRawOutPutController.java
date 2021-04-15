package com.bsl.controller.prodmanager;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslProductInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.prodmanager.HalfProdOutPutService;

/**
 * @author 杜康
 * 半成品出库管理Controller
 */
@Controller
@RequestMapping("/halfRawOutput")
public class HalfRawOutPutController {
	
	@Autowired
	private HalfProdOutPutService halfProdOutPutService;
		
	/**
	 * 获取符合条件的生产计划指令信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getHalfProdPlanInfo(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getPlanJz())) {
			result =  BSLResult.build(400, "生产机组不能为空");
		}else{
			try{
				result = halfProdOutPutService.getHalfProdPlanService(queryCriteria);
			}catch (Exception e) {
				DictItemOperation.log.info("===========异常:"+e.getMessage());
				return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
			}
		}
		return result;
	}
	
	/**
	 * 半成品确认出库
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/output")
	@ResponseBody
	public BSLResult outputProdPlan(String prodId,String planJz,String prodInputuser){
		try {
			if(StringUtils.isBlank(prodId)) {
				return  BSLResult.build(400, "盘号不能为空");
			}
			if(StringUtils.isBlank(planJz)) {
				return  BSLResult.build(400, "生产机组不能为空");
			}
			return halfProdOutPutService.addHalfProdPlanInfo(prodId,planJz,prodInputuser,1);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 半成品未用退回
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/reInsert")
	@ResponseBody
	public BSLResult reInsertProdPlan(String prodId,String prodInputuser){
		try {
			if(StringUtils.isBlank(prodId)) {
				return  BSLResult.build(400, "盘号不能为空");
			}
			return halfProdOutPutService.addHalfProdPlanInfo(prodId,null,prodInputuser,2);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 半成品完成
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/finish")
	@ResponseBody
	public BSLResult finishProdPlan(String prodId,String prodInputuser){
		try {
			if(StringUtils.isBlank(prodId)) {
				return  BSLResult.build(400, "盘号不能为空");
			}
			return halfProdOutPutService.addHalfProdPlanInfo(prodId,null,prodInputuser,3);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 跟住盘号查询半成品
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public BSLResult prodGetById(String prodId){
		try {
			if(StringUtils.isBlank(prodId)) {
				return  BSLResult.build(400, "盘号不能为空");
			}
			BslProductInfo bslProductInfo = halfProdOutPutService.getHalfProdPlanByPK(prodId);
			if(bslProductInfo == null){
				return  BSLResult.build(400, "根据盘号查询内容为空");
			}
			return BSLResult.ok(bslProductInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	
	@RequestMapping("/{page}")
	public String showUserPage(@PathVariable String page, HttpServletRequest request) {
		return page;
	}
	
}
