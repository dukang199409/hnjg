package com.bsl.controller.prodmanager;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslMakePlanInfo;
import com.bsl.pojo.BslMakePlanInfoDetail;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.prodmanager.ProdPlanService;
import com.bsl.service.rawmaterialsmanager.RawService;

/**
 * @author 杜康
 * 生产计划指令制定管理Controller
 */
@Controller
@RequestMapping("/prodPlan")
public class ProdPlanController {
	
	@Autowired
	private ProdPlanService prodPlanService;
	@Autowired
	private RawService rawService;
	
	/**
	 * 查询生产计划指令信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getProdPlanList(Integer page,Integer rows){		
		return prodPlanService.getProdPlanService(page, rows);
	}
		
	/**
	 * 获取符合条件的生产计划指令信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getProdPlanList(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = prodPlanService.getProdPlanService(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 产成品生产指令详情
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public BSLResult getMakePlanDetailInfo(String planId) {
		if(StringUtils.isBlank(planId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产成品生产指令号不能为空");
		}
		try {
			List<BslMakePlanInfoDetail> lists = prodPlanService.getProdPlanInfoDetail(planId);
			return BSLResult.ok(lists);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 新增生产计划指令信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public BSLResult addProdPlan(BslMakePlanInfo bslMakePlanInfo){
//		if(StringUtils.isBlank(bslMakePlanInfo.getPlanLuno())){
//			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "炉号不能为空");
//		}
		if(StringUtils.isBlank(bslMakePlanInfo.getProdNorm())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "用料规格不能为空");
		}
		if(StringUtils.isBlank(bslMakePlanInfo.getProdMaterial())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "用料钢种不能为空");
		}
		if(StringUtils.isBlank(bslMakePlanInfo.getMakeName())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "制造产品名称不能为空");
		}
		if(StringUtils.isBlank(bslMakePlanInfo.getMakeProdNorm())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "制造产品规格不能为空");
		}
		if(StringUtils.isBlank(bslMakePlanInfo.getPlanJz())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "制造机组不能为空");
		}
		try {
			return prodPlanService.addProdPlanInfo(bslMakePlanInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 修改生产计划指令信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public BSLResult editProdPlan(BslMakePlanInfo bslMakePlanInfo){
		if(StringUtils.isBlank(bslMakePlanInfo.getPlanId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "生产调度指令号不能为空");
		}
//		if(StringUtils.isBlank(bslMakePlanInfo.getPlanLuno())){
//			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "炉号不能为空");
//		}
		if(StringUtils.isBlank(bslMakePlanInfo.getProdNorm())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "用料规格不能为空");
		}
		if(StringUtils.isBlank(bslMakePlanInfo.getProdMaterial())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "用料钢种不能为空");
		}
		if(StringUtils.isBlank(bslMakePlanInfo.getMakeName())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "制造产品名称不能为空");
		}
		if(StringUtils.isBlank(bslMakePlanInfo.getMakeProdNorm())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "制造产品规格不能为空");
		}
		if(StringUtils.isBlank(bslMakePlanInfo.getPlanJz())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "制造机组不能为空");
		}
		try {
			return prodPlanService.updateProdPlanInfo(bslMakePlanInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 删除生产计划指令信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public BSLResult deleteProdPlan(BslMakePlanInfo bslMakePlanInfo){
		if(StringUtils.isBlank(bslMakePlanInfo.getPlanId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "生产调度指令号不能为空");
		}
		try {
			return prodPlanService.deleteProdPlanInfo(bslMakePlanInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	@RequestMapping("/{page}")
	public String showUserPage(@PathVariable String page, HttpServletRequest request) {
		/*if("M3001-add".equals(page) || "M3001-edit".equals(page)) {
			BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
			bslProductInfoExample.setOrderByClause("`prod_id` desc");
			BSLResult bslResult = rawService.getRawServiceByProductInfo(bslProductInfoExample);
			if(ErrorCodeInfo.交易成功 == bslResult.getStatus()) {
				List<BslProductInfo> lists = (List<BslProductInfo>)bslResult.getData();
				List<String> listStrings = new ArrayList<String>();
				for (BslProductInfo bslProductInfo : lists) {
					if(!listStrings.contains(bslProductInfo.getProdLuno())){
						listStrings.add(bslProductInfo.getProdLuno());
					}
				}
				request.setAttribute("prodLunoLists", listStrings);
			}
		}*/
		return page;
	}
	
}
