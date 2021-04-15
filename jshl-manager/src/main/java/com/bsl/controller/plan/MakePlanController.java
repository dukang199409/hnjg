package com.bsl.controller.plan;

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
import com.bsl.select.QueryExample;
import com.bsl.service.plan.MakePlanService;
import com.bsl.service.rawmaterialsmanager.RawService;

/**
 * 半成品指令制定管理
 * @author huangyl
 * @date 2019年3月28日  
 *
 */
@Controller
@RequestMapping("/plan")
public class MakePlanController {
	
	@Autowired
	private MakePlanService makePlanService;
	
	@Autowired
	private RawService rawService;
	
	@RequestMapping("/{page}")
	public String showPlanPage(@PathVariable String page, HttpServletRequest request) {
		/*if(!"M2001".equals(page)) {
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
	
	/**
	 * 初始化查询所有纵剪带生产指令
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getMakePlanInfoList(Integer page, Integer rows) {
		return makePlanService.getMakePlanInfoList(page, rows);
	}
	
	/**
	 * 纵剪带生产指令详情
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public BSLResult getMakePlanDetailInfo(String planId) {
		if(StringUtils.isBlank(planId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "纵剪带指令号不能为空");
		}
		try {
			List<BslMakePlanInfoDetail> lists = makePlanService.getMakePlanInfoDetail(planId);
			return BSLResult.ok(lists);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 根据条件查询纵剪带生产指令
	 */
	@RequestMapping("/query")
	@ResponseBody
	public BSLResult queryMakePlanInfoList(QueryExample queryCriteria) {
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = makePlanService.queryMakePlanInfoList(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 新增纵剪带生产指令
	 */
	@RequestMapping("/add")
	@ResponseBody
	public BSLResult addMakePlanInfo(BslMakePlanInfo bslMakePlanInfo) {
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
		if(StringUtils.isBlank(bslMakePlanInfo.getMakeType())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "制造纵剪带用途不能为空");
		}
		if(StringUtils.isBlank(bslMakePlanInfo.getPlanJz())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "制造机组不能为空");
		}
		try {
			return makePlanService.add(bslMakePlanInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 修改纵剪带生产指令
	 */
	@RequestMapping("/update")
	@ResponseBody
	public BSLResult updateMakePlanInfo(BslMakePlanInfo bslMakePlanInfo) {
		if(StringUtils.isBlank(bslMakePlanInfo.getPlanId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "纵剪带指令号不能为空");
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
		if(StringUtils.isBlank(bslMakePlanInfo.getMakeType())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "制造纵剪带用途不能为空");
		}
		if(StringUtils.isBlank(bslMakePlanInfo.getPlanJz())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "制造机组不能为空");
		}
		try {
			return makePlanService.update(bslMakePlanInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 删除纵剪带生产指令
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public BSLResult deleteMakePlanInfo(String planId) {
		if(StringUtils.isBlank(planId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "纵剪带指令号不能为空");
		}
		try {
			return makePlanService.delete(planId);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
}
