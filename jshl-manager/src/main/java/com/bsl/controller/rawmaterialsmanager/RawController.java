package com.bsl.controller.rawmaterialsmanager;

import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.pojo.BSLException;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslBsPlanInfo;
import com.bsl.pojo.BslBsPlanInfoExample;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslProductInfoExample.Criteria;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.rawmaterialsmanager.RawService;
import com.bsl.service.rawmaterialsmanager.ReceiptService;

/**
 * @author 杜康
 * 入库单预录入Controller
 */
@Controller
@RequestMapping("/raw")
public class RawController {
	
	@Autowired
	private RawService rawService;
	@Autowired
	private ReceiptService receiptService;
	
	/**
	 * 查询预录入入库单信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getAddRawList(Integer page,Integer rows){		
		return rawService.getRawService(page, rows);
	}
		
	/**
	 * 根据条件查询预录入入库单信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getRawList(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = rawService.getRawService(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 扫码批量新增数据处理
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/rawAddSMDeal")
	@ResponseBody
	public BSLResult rawAddSMDeal(String rawInfo) {
		try {
			return rawService.rawAddSMDeal(rawInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 入库
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/cfmRaw")
	@ResponseBody
	public BSLResult cfmRaw(BslProductInfo bslProductInfo,String crtdateM1003){
		if(!StringUtils.isBlank(crtdateM1003)){
			try {
				bslProductInfo.setCrtDate(DictItemOperation.日期转换实例.parse(crtdateM1003));
			} catch (ParseException e) {
				DictItemOperation.log.info("===========异常:"+e.getMessage());
				e.printStackTrace();
			}
		}
		if(StringUtils.isBlank(bslProductInfo.getProdNorm())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品规格不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdMaterial())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品钢种不能为空");
		}
		if(bslProductInfo.getProdRelWeight() == null || bslProductInfo.getProdRelWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品实际重量不能为空");
		}
		if(bslProductInfo.getProdPrintWeight() == null || bslProductInfo.getProdPrintWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品打印重量不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdLuno()) && StringUtils.isBlank(bslProductInfo.getProdOriId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "炉号和厂家钢卷号不能同时为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdCheckuser())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "录入人不能为空");
		}
		try {
			return rawService.addCfmRawInfo(bslProductInfo);
		} catch (Exception e) {
			if(e instanceof BSLException) {
				BSLException bslException = (BSLException) e;
				return BSLResult.build(bslException.getErrorCode(), bslException.getMessage());
			}else {
				DictItemOperation.log.info("===========异常:"+e.getMessage());
				return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
			}
		}
	}
	
	/**
	 * 入库后修改
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public BSLResult editRaw(BslProductInfo bslProductInfo,String crtDateM1003EditHidden){
		if(!StringUtils.isBlank(crtDateM1003EditHidden)){
			try {
				bslProductInfo.setCrtDate(DictItemOperation.日期转换实例.parse(crtDateM1003EditHidden));
			} catch (ParseException e) {
				DictItemOperation.log.info("===========异常:"+e.getMessage());
				e.printStackTrace();
			}
		}
		if(StringUtils.isBlank(bslProductInfo.getProdNorm())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品规格不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdMaterial())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品钢种不能为空");
		}
		if(bslProductInfo.getProdRelWeight() == null || bslProductInfo.getProdRelWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品实际重量不能为空");
		}
		if(bslProductInfo.getProdPrintWeight() == null || bslProductInfo.getProdPrintWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品打印重量不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdLuno()) && StringUtils.isBlank(bslProductInfo.getProdOriId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "炉号和厂家钢卷号不能同时为空");
		}
		try {
			return rawService.updateRawInfo(bslProductInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 剩余原料重新入库
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/readd")
	@ResponseBody
	public BSLResult reAddRaw(BslProductInfo bslProductInfo){
		if(StringUtils.isBlank(bslProductInfo.getProdOriId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "原出库物料编码不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdNorm())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品规格不能为空");
		}
		if(bslProductInfo.getProdRelWeight() == null || bslProductInfo.getProdRelWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品实际重量不能为空");
		}
		try {
			return rawService.addReRawInfo(bslProductInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	/**
	 * 已入库的删除
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public BSLResult deleteRaw(String prodId){
		if(StringUtils.isBlank(prodId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "物料编码不能为空");
		}
		try {
			return rawService.deleteRaw(prodId);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常, e.getMessage());
		}
	}
	
	
	@RequestMapping("/{page}")
	public String showUserPage(@PathVariable String page, HttpServletRequest request) {
		if("M1003-readd".equals(page)) {
			BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
			Criteria criteria = bslProductInfoExample.createCriteria();
			criteria.andProdTypeEqualTo(DictItemOperation.产品类型_卷板);
			criteria.andProdStatusEqualTo(DictItemOperation.产品状态_已出库);
			bslProductInfoExample.setOrderByClause("`prod_id` desc");
			BSLResult bslResult = rawService.getRawServiceByProductInfo(bslProductInfoExample);
			if(ErrorCodeInfo.交易成功 == bslResult.getStatus()) {
				List<BslProductInfo> list = (List<BslProductInfo>)bslResult.getData();
				request.setAttribute("bslBsProdOriList", list);
			}
		}else if("M1003-add".equals(page) || "M1003-edit".equals(page)) {
			BslBsPlanInfoExample bslBsPlanInfoExample = new BslBsPlanInfoExample();
			com.bsl.pojo.BslBsPlanInfoExample.Criteria criteria = bslBsPlanInfoExample.createCriteria();
			criteria.andBsTypeEqualTo(DictItemOperation.收发标志_原料入库通知单);
			criteria.andBsStatusNotEqualTo(DictItemOperation.通知单状态_已完成);
			bslBsPlanInfoExample.setOrderByClause("`bs_id` desc");
			BSLResult bslResult = receiptService.getReceiptServiceByBsPlanInfo(bslBsPlanInfoExample);
			if(ErrorCodeInfo.交易成功 == bslResult.getStatus()) {
				List<BslBsPlanInfo> list = (List<BslBsPlanInfo>)bslResult.getData();
				request.setAttribute("bslBsPlanInfoList", list);
			}
		}
		return page;
	}
	
}
