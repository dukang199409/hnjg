package com.bsl.controller.dclprod;

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
import com.bsl.select.QueryCriteria;
import com.bsl.service.dclprod.DclProdService;
import com.bsl.service.prodmanager.HalfProdOutPutService;
import com.bsl.service.prodmanager.ProdPlanService;

/**
 * @author 杜康
 * 待处理品管理Controller
 */
@Controller
@RequestMapping("/dclProd")
public class DclProdController {
	
	@Autowired
	private DclProdService dclProdService;
	@Autowired
	private ProdPlanService prodPlanService;
	@Autowired
	private HalfProdOutPutService halfProdOutPutService;
	
	/**
	 *根据条件查询待处理品信息 
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getDclProdServiceByCriteria(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = dclProdService.getDclProdServiceByCriteria(queryCriteria);
		}
		return result;
	}
	
	/**
	 *根据条件查询待处理品信息 
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public BSLResult getDclProdServiceById(String prodId){
		BSLResult result = dclProdService.getDclProdServiceById(prodId);
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
	public BSLResult addDclProdinfo(BslProductInfo bslProductInfo,String sumNum){
		int sumNumInt = Integer.parseInt(sumNum);
		if(StringUtils.isBlank(bslProductInfo.getProdPlanNo())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品生产指令不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdName())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品名称不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdNorm())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品规格不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdMaterial())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品钢种不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdParentNo())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品父级盘号不能为空");
		}
		/*if(bslProductInfo.getProdLength() == null || bslProductInfo.getProdLength() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品定尺不能为空");
		}*/
		if(bslProductInfo.getProdRelWeight() == null || bslProductInfo.getProdRelWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品实际重量不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdBc())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "生产班次不能为空");
		}
		try {
			return dclProdService.addDclProdinfo(bslProductInfo,sumNumInt);
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
	public BSLResult addDclProdinfoB(BslProductInfo bslProductInfo,String sumNum){
		int sumNumInt = Integer.parseInt(sumNum);
		if(StringUtils.isBlank(bslProductInfo.getProdPlanNo())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品生产指令不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdName())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品名称不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdNorm())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品规格不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdMaterial())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品钢种不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdParentNo())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品父级盘号不能为空");
		}
		/*if(bslProductInfo.getProdLength() == null || bslProductInfo.getProdLength() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品定尺不能为空");
		}*/
		if(bslProductInfo.getProdRelWeight() == null || bslProductInfo.getProdRelWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品实际重量不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdBc())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "生产班次不能为空");
		}
		try {
			return dclProdService.addDclProdinfoB(bslProductInfo,sumNumInt);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 修改待处理品信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/editProd")
	@ResponseBody
	public BSLResult editDclProdPlan(BslProductInfo bslProductInfo){
		if(StringUtils.isBlank(bslProductInfo.getProdId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品编号不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdPlanNo())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品生产指令不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdName())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品名称不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdNorm())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品规格不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdMaterial())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品钢种不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdParentNo())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品父级盘号不能为空");
		}
		/*if(bslProductInfo.getProdLength() == null || bslProductInfo.getProdLength() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品定尺不能为空");
		}*/
		if(bslProductInfo.getProdRelWeight() == null || bslProductInfo.getProdRelWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品实际重量不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdBc())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "生产班次不能为空");
		}
		try {
			return dclProdService.updateDclProdPlan(bslProductInfo);
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
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "待处理品编号不能为空");
		}
		try {
			return dclProdService.delete(prodId,user);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	@RequestMapping("/{page}")
	public String showUserPage(@PathVariable String page, HttpServletRequest request) {
		//新增的时候，查询该指令下正在执行的盘号
		/*if("M3101-add".equals(page)) {
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
