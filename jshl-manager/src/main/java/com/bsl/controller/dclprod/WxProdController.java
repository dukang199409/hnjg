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
import com.bsl.pojo.BslProductInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.dclprod.WxDclProdService;
import com.bsl.service.prodmanager.ProdService;

/**
 * @author 杜康
 * 外协厂产品管理Controller
 */
@Controller
@RequestMapping("/prodWxManager")
public class WxProdController {
	
	@Autowired
	private ProdService prodService;
	@Autowired
	private WxDclProdService wxDclProdService;
	
	/**
	 *根据条件查询2-产品信息,标志待处理品入库
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
			result = prodService.getWxProdService(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 补录入库
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/addProdB")
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
			return prodService.addProdWxinfoB(bslProductInfo,sumNumInt);
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
	public BSLResult editProdWxInfo(BslProductInfo bslProductInfo){
		if(StringUtils.isBlank(bslProductInfo.getProdId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "件号不能为空");
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
			return prodService.updateProdWxInfo(bslProductInfo);
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
	public BSLResult deleteWxProd(String prodId,String user) {
		if(StringUtils.isBlank(prodId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品编号不能为空");
		}
		try {
			return prodService.deleteWxProd(prodId,user);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	@RequestMapping("/{page}")
	public String showUserPage(@PathVariable String page, HttpServletRequest request) {
		//新增的时候，查询可以处理的待处理品
		if("M3103-addb".equals(page)) {
			 List<BslProductInfo> bslProductInfos = wxDclProdService.getDclInfoDeal();
			 if(bslProductInfos != null){
				 request.setAttribute("bslProductInfos", bslProductInfos);
			 }
		}
		return page;
	}
	
}
