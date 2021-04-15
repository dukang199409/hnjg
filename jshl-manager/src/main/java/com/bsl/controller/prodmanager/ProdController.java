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
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslMakePlanInfo;
import com.bsl.pojo.BslProductInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.ProdLeftInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.prodmanager.HalfProdOutPutService;
import com.bsl.service.prodmanager.ProdPlanService;
import com.bsl.service.prodmanager.ProdService;

/**
 * @author 杜康
 * 产品管理Controller
 */
@Controller
@RequestMapping("/prodManager")
public class ProdController {
	
	@Autowired
	private ProdService prodService;
	@Autowired
	private ProdPlanService prodPlanService;
	@Autowired
	private HalfProdOutPutService halfProdOutPutService;
	
	/**
	 * 获取正在执行的产品生产指令信息
	 */
	@RequestMapping("/getPlanExeInfo")
	@ResponseBody
	public BSLResult getPlanExeInfo(String planJz) {
		 BslMakePlanInfo makePlanInfoExe = prodPlanService.getProdPlanInfoExe(planJz);
		 if(makePlanInfoExe == null){
			 return BSLResult.build(ErrorCodeInfo.错误类型_查询无记录, "没有查询到正在执行的生产指令信息，无法入库");
		 }
		 return BSLResult.ok(makePlanInfoExe);
	}
	
	/**
	 * 获取产品生产指令信息
	 */
	@RequestMapping("/getPlanById")
	@ResponseBody
	public BSLResult getPlanById(String prodPlanNo) {
		 BslMakePlanInfo makePlanInfo = prodPlanService.getProdPlanInfo(prodPlanNo);
		 if(makePlanInfo == null){
			 return BSLResult.build(ErrorCodeInfo.错误类型_查询无记录, "根据指令号没有查询生产指令信息，无法入库");
		 }
		 return BSLResult.ok(makePlanInfo);
	}
	
	/**
	 * 获取该指令下出库的纵剪带信息
	 */
	@RequestMapping("/getParentIds")
	@ResponseBody
	public BSLResult getParentIds(String prodPlanNo) {
		return prodPlanService.getHalfProdsByPlanId(prodPlanNo);
	}
	
	/**
	 * 根据盘号获取该盘已经入库的包数
	 */
	@RequestMapping("/getProdRuNums")
	@ResponseBody
	public BSLResult getProdRuNums(String prodId) {
		return prodService.getProdRuNums(prodId);
	}
	
	/**
	 * 获取该指令下的详细信息
	 */
	@RequestMapping("/getProdLengthIds")
	@ResponseBody
	public BSLResult getProdLengthIds(String prodPlanNo) {
		return BSLResult.ok(prodPlanService.getProdPlanInfoDetail(prodPlanNo));
	}
	
	/**
	 * 获取正在执行的半成品信息
	 */
	@RequestMapping("/getHalfProdExeInfo")
	@ResponseBody
	public BSLResult getHalfProdExeInfo(String exePlanId,String planJz) {
		 if(StringUtils.isBlank(exePlanId)){
			 BslMakePlanInfo makePlanInfoExe = prodPlanService.getProdPlanInfoExe(planJz);
			 exePlanId = makePlanInfoExe.getPlanId();
		 }
		 List<BslProductInfo> halfProdExes = halfProdOutPutService.getHalfProdExe(exePlanId);
		 StringBuilder sBuilder = new StringBuilder("");
		 for (BslProductInfo bslProductInfo : halfProdExes) {
			 sBuilder.append(bslProductInfo.getProdId());
			 sBuilder.append(";");
		 }
		 return BSLResult.ok(sBuilder.toString());
	}
		
	/**
	 *根据条件查询2-产品信息 
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getProdServiceByCriteria(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = prodService.getProdService(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 入库
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/addProd")
	@ResponseBody
	public BSLResult addProdinfo(BslProductInfo bslProductInfo,String sumNum){
		int sumNumInt = Integer.parseInt(sumNum);
		if(StringUtils.isBlank(bslProductInfo.getProdPlanNo())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品生产指令不能为空");
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
		if(StringUtils.isBlank(bslProductInfo.getProdParentNo())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品父级盘号不能为空");
		}
//		if(StringUtils.isBlank(bslProductInfo.getProdLuno())){
//			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品炉号不能为空");
//		}
		if(StringUtils.isBlank(bslProductInfo.getProdRuc())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "入库仓库不能为空");
		}
		if(bslProductInfo.getProdLength() == null || bslProductInfo.getProdLength() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品定尺不能为空");
		}
		if(bslProductInfo.getProdRelWeight() == null || bslProductInfo.getProdRelWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品实际重量不能为空");
		}
		if(bslProductInfo.getProdNum() == null || bslProductInfo.getProdNum() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品件数不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdBc())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "生产班次不能为空");
		}
		try {
			return prodService.addCfmProdInfo(bslProductInfo,sumNumInt);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 补录入库
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/addProdB")
	@ResponseBody
	public BSLResult addProdinfoB(BslProductInfo bslProductInfo,String sumNum){
		int sumNumInt = Integer.parseInt(sumNum);
		if(StringUtils.isBlank(bslProductInfo.getProdPlanNo())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品生产指令不能为空");
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
		if(StringUtils.isBlank(bslProductInfo.getProdParentNo())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品父级盘号不能为空");
		}
//		if(StringUtils.isBlank(bslProductInfo.getProdLuno())){
//			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品炉号不能为空");
//		}
		if(StringUtils.isBlank(bslProductInfo.getProdRuc())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "入库仓库不能为空");
		}
		if(bslProductInfo.getProdLength() == null || bslProductInfo.getProdLength() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品定尺不能为空");
		}
		if(bslProductInfo.getProdRelWeight() == null || bslProductInfo.getProdRelWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品实际重量不能为空");
		}
		if(bslProductInfo.getProdNum() == null || bslProductInfo.getProdNum() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品件数不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdBc())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "生产班次不能为空");
		}
		try {
			return prodService.addCfmProdInfoBuLu(bslProductInfo,sumNumInt);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 待处理品处理入库
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/addProdByDCL")
	@ResponseBody
	public BSLResult addProdWxinfoB(BslProductInfo bslProductInfo,String sumNum){
		int sumNumInt = Integer.parseInt(sumNum);
		if(StringUtils.isBlank(bslProductInfo.getProdName())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "物料名称不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdNorm())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品规格不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdMaterial())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品钢种不能为空");
		}
//		if(StringUtils.isBlank(bslProductInfo.getProdOriId())){
//			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品父级待处理品编号不能为空");
//		}
		if(bslProductInfo.getProdLength() == null || bslProductInfo.getProdLength() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品定尺不能为空");
		}
		if(bslProductInfo.getProdRelWeight() == null || bslProductInfo.getProdRelWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品实际重量不能为空");
		}
		if(bslProductInfo.getProdNum() == null || bslProductInfo.getProdNum() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品件数不能为空");
		}
		try {
			return prodService.addProdFromDclinfo(bslProductInfo,sumNumInt);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 修改产品信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/editProd")
	@ResponseBody
	public BSLResult editProdPlan(BslProductInfo bslProductInfo){
		if(StringUtils.isBlank(bslProductInfo.getProdId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "件号不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdPlanNo())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品生产指令不能为空");
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
		if(StringUtils.isBlank(bslProductInfo.getProdParentNo())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品父级盘号不能为空");
		}
//		if(StringUtils.isBlank(bslProductInfo.getProdLuno())){
//			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品炉号不能为空");
//		}
		if(StringUtils.isBlank(bslProductInfo.getProdRuc())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "入库仓库不能为空");
		}
		if(bslProductInfo.getProdLength() == null || bslProductInfo.getProdLength() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品定尺不能为空");
		}
		if(bslProductInfo.getProdRelWeight() == null || bslProductInfo.getProdRelWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品实际重量不能为空");
		}
		if(bslProductInfo.getProdNum() == null || bslProductInfo.getProdNum() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品件数不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdBc())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "生产班次不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdMakeJz())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "生产机组不能为空");
		}
		try {
			return prodService.updateProdInfo(bslProductInfo);
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
			return prodService.delete(prodId,user);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	@RequestMapping("/getParentPh")
	@ResponseBody
	public BSLResult getParentPh(String planId) {
		//新增的时候，查询该指令下正在执行的盘号
		 List<BslProductInfo> halfProdExes = halfProdOutPutService.getHalfProdExe(planId);
		 List<String> liStrings = new ArrayList<String>();
		 for (BslProductInfo bslProductInfo : halfProdExes) {
			 liStrings.add(bslProductInfo.getProdId());
		 }
		 return BSLResult.ok(liStrings);
	}
	
	@RequestMapping("/{page}")
	public String showUserPage(@PathVariable String page, HttpServletRequest request) {
		/*//新增的时候，查询该指令下正在执行的盘号
		if("M3005-add".equals(page)) {
			 BslMakePlanInfo makePlanInfoExe = prodPlanService.getProdPlanInfoExe();
			 if(makePlanInfoExe != null){
				 String exePlanId = makePlanInfoExe.getPlanId();
				 List<BslProductInfo> halfProdExes = halfProdOutPutService.getHalfProdExe(exePlanId);
				 request.setAttribute("prodParentNoLists", halfProdExes);
			 }
		}*/
		return page;
	}
	
}
