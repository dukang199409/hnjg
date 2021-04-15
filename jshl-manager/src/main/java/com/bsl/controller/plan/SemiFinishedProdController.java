package com.bsl.controller.plan;

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
import com.bsl.pojo.BslProductInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryExample;
import com.bsl.service.plan.MakePlanService;
import com.bsl.service.plan.RawOutputService;
import com.bsl.service.plan.SemiFinishedProdService;

@Controller
@RequestMapping("/semi")
public class SemiFinishedProdController {
	
	@Autowired
	private SemiFinishedProdService semiFinishedProdService;
	@Autowired
	private MakePlanService makePlanService;
	@Autowired
	private RawOutputService rawOutputService;
	
	@RequestMapping("/{page}")
	public String showPlanDetailPage(@PathVariable String page, HttpServletRequest httpServletRequest) {
		return page;
	}

	/**
	 * 初始化查询所有半成品信息
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getRawOutputList(Integer page, Integer rows) {
		return semiFinishedProdService.getBslProductInfoList(page, rows);
	}
	
	/**
	 * 根据条件查询所有半成品信息
	 */
	@RequestMapping("/query")
	@ResponseBody
	public BSLResult queryRawOutputList(QueryExample queryCriteria) {
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = semiFinishedProdService.queryBslProductInfoList(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 根据生产出库单查询出库的半成品信息
	 */
	@RequestMapping("/queryMakeInfo")
	@ResponseBody
	public BSLResult queryMakeInfo(QueryExample queryCriteria) {
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getProdOutPlan())) {
			return null;
		}
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = semiFinishedProdService.queryMakeInfoList(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 获取正在执行的纵剪带指令信息
	 */
	@RequestMapping("/getHalfPlanExeInfo")
	@ResponseBody
	public BSLResult getHalfPlanExeInfo(String planJz) {
		 BslMakePlanInfo makePlanInfoExe = makePlanService.getMakePlanInfoExe(planJz);
		 if(makePlanInfoExe == null){
			 return BSLResult.build(ErrorCodeInfo.错误类型_查询无记录, "没有查询到正在执行的纵剪带指令信息，无法入库");
		 }
		 return BSLResult.ok(makePlanInfoExe);
	}
	
	/**
	 * 获取该指令正在执行的卷板信息
	 */
	@RequestMapping("/getRawExeInfo")
	@ResponseBody
	public BSLResult getRawExeInfo(String exePlanId,String planJz) {
		 if(StringUtils.isBlank(exePlanId)){
			 BslMakePlanInfo makePlanInfoExe = makePlanService.getMakePlanInfoExe(planJz);
			 exePlanId = makePlanInfoExe.getPlanId();
		 }
		 BslProductInfo rawExe = rawOutputService.getRawExe(exePlanId);
		 if(rawExe == null){
			 return BSLResult.build(ErrorCodeInfo.错误类型_查询无记录, "没有查询到该指令下制造出库的卷板信息，无法入库");
		 }
		 return BSLResult.ok(rawExe);
	}
	
	/**
	 * 入库
	 */
	@RequestMapping("/add")
	@ResponseBody
	public BSLResult add(BslProductInfo bslProductInfo,String sumNum) {
		int sumNumInt = Integer.parseInt(sumNum);
		if(StringUtils.isBlank(bslProductInfo.getProdPlanNo())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "纵剪带生产指令不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdParentNo())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "父级钢卷号不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdName())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "物料名称不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdNorm())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品规格不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdMaterial())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品钢种不能为空");
		}
//		if(StringUtils.isBlank(bslProductInfo.getProdLuno())){
//			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品炉号不能为空");
//		}
		if(bslProductInfo.getProdRelWeight() == null || bslProductInfo.getProdRelWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品实际重量不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdBc())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "生产班次不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdMakeJz())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "生产机组不能为空");
		}
		try {
			return semiFinishedProdService.add(bslProductInfo,sumNumInt);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	public BSLResult update(BslProductInfo bslProductInfo) {
		if(StringUtils.isBlank(bslProductInfo.getProdId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "盘号不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdPlanNo())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "纵剪带生产指令不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdParentNo())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "父级钢卷号不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdName())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "物料名称不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdNorm())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品规格不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdMaterial())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品钢种不能为空");
		}
//		if(StringUtils.isBlank(bslProductInfo.getProdLuno())){
//			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品炉号不能为空");
//		}
		if(bslProductInfo.getProdRelWeight() == null || bslProductInfo.getProdRelWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品实际重量不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdBc())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "生产班次不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdMakeJz())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "生产机组不能为空");
		}
		try {
			return semiFinishedProdService.update(bslProductInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public BSLResult delete(String prodId,String user) {
		if(StringUtils.isBlank(prodId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品编号不能为空");
		}
		try {
			return semiFinishedProdService.delete(prodId,user);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 剩余重新入库
	 */
	@RequestMapping("/readd")
	@ResponseBody
	public BSLResult readd(BslProductInfo bslProductInfo) {
		if(StringUtils.isBlank(bslProductInfo.getProdOriId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "剩余入库盘号不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdNorm())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品规格不能为空");
		}
		if(bslProductInfo.getProdRelWeight() == null || bslProductInfo.getProdRelWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品实际重量不能为空");
		}
		try {
			return semiFinishedProdService.addRe(bslProductInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	
	
}
