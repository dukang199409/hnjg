package com.bsl.controller.sale;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslSaleInfoDetail;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.rawmaterialsmanager.ReceiptService;
import com.bsl.service.sale.SaleDetailService;

/**
 * @author 杜康
 * 销售出库详细信息管理Controller
 */
@Controller
@RequestMapping("/saleDetail")
public class SaleDetailController {
	
	@Autowired
	SaleDetailService saleDetailService;
	
	@Autowired
	ReceiptService receiptService;
	
	/**
	 * 查询销售出库通知单信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getsaleDetailList(Integer page, Integer rows){
		return saleDetailService.getSaleDetailService(page, rows);
	}
	
	/**
	 * 根据条件查询销售出库通知详细信息
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getSaleDetailList(QueryCriteria queryCriteria){
		BSLResult result = null;
		if(StringUtils.isBlank(queryCriteria.getPage())) {
			result =  BSLResult.build(400, "页码不能为空");
		}else if(StringUtils.isBlank(queryCriteria.getRows())) {
			result =  BSLResult.build(400, "每页记录数不能为空");
		}else{
			result = saleDetailService.getSaleDetailService(queryCriteria);
		}
		return result;
	}
	
	/**
	 * 新增指定类型销售出库通知详细
	 * @param BslBsPlanInfo
	 * @return
	 */
	@RequestMapping(value="/addSaleDetail", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult addSaleDetail(BslSaleInfoDetail bslSaleInfoDetail,String bsFlag) {
		if(StringUtils.isBlank(bsFlag)) {
			return  BSLResult.build(400, "通知单类别不能为空");
		}
		if(StringUtils.isBlank(bslSaleInfoDetail.getSalePlanId())) {
			return  BSLResult.build(400, "生产销售通知单号不能为空");
		}
		if(StringUtils.isBlank(bslSaleInfoDetail.getSaleFlag())) {
			return  BSLResult.build(400, "生产销售出库标准不能为空");
		}
		if(DictItemOperation.销售单出库标准_按重量销售出库.equals(bslSaleInfoDetail.getSaleFlag())){
			if(bslSaleInfoDetail.getSaleWeight() == null || bslSaleInfoDetail.getSaleWeight() == 0){
				return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "按重量销售出库时，计划出库重量不能为空");
			}
		}else if(DictItemOperation.销售单出库标准_按数量销售出库.equals(bslSaleInfoDetail.getSaleFlag())){
			if(bslSaleInfoDetail.getSaleNum() == null || bslSaleInfoDetail.getSaleNum() == 0){
				return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "按数量销售出库时，计划出库数量不能为空");
			}
		}
		if(StringUtils.isBlank(bslSaleInfoDetail.getProdMaterial())) {
			return  BSLResult.build(400, "生产销售计划出库钢种不能为空");
		}
		if(StringUtils.isBlank(bslSaleInfoDetail.getProdNorm())) {
			return  BSLResult.build(400, "生产销售计划出库规格不能为空");
		}
		//定尺只有在成品发货的时候不能为空,其他时候必须为空
		if(DictItemOperation.收发类别_成品发货.equals(bsFlag) 
				|| DictItemOperation.收发类别_委外仓成品发货.equals(bsFlag)
				 	|| DictItemOperation.收发类别_成品转场发货.equals(bsFlag) ){
			if(bslSaleInfoDetail.getProdLength() == null || bslSaleInfoDetail.getProdLength() == 0) {
				return  BSLResult.build(400, "成品发货，生产销售计划出库定尺不能为空");
			}
		}else{
			if(bslSaleInfoDetail.getProdLength() != null && bslSaleInfoDetail.getProdLength() > 0) {
				return  BSLResult.build(400, "非成品发货，生产销售计划出库定尺必须为空");
			}
		}
		try {
			return saleDetailService.addSaleDetailInfo(bslSaleInfoDetail);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 修改指定类型销售出库通知详细
	 * @param BslBsPlanInfo
	 * @return
	 */
	@RequestMapping(value="/editSaleDetail", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult editSaleDetail(BslSaleInfoDetail bslSaleInfoDetail,String bsFlag) {
		if(StringUtils.isBlank(bsFlag)) {
			return  BSLResult.build(400, "通知单类别不能为空");
		}
		if(StringUtils.isBlank(bslSaleInfoDetail.getSaleSerno())) {
			return  BSLResult.build(400, "单笔销售计划流水不能为空");
		}
		if(StringUtils.isBlank(bslSaleInfoDetail.getSalePlanId())) {
			return  BSLResult.build(400, "生产销售通知单号不能为空");
		}
		if(StringUtils.isBlank(bslSaleInfoDetail.getSaleFlag())) {
			return  BSLResult.build(400, "生产销售出库标准不能为空");
		}
		if(DictItemOperation.销售单出库标准_按重量销售出库.equals(bslSaleInfoDetail.getSaleFlag())){
			if(bslSaleInfoDetail.getSaleWeight() == null || bslSaleInfoDetail.getSaleWeight() == 0){
				return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "按重量销售出库时，计划出库重量不能为空");
			}
		}else if(DictItemOperation.销售单出库标准_按数量销售出库.equals(bslSaleInfoDetail.getSaleFlag())){
			if(bslSaleInfoDetail.getSaleNum() == null || bslSaleInfoDetail.getSaleNum() == 0){
				return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "按数量销售出库时，计划出库数量不能为空");
			}
		}
		if(StringUtils.isBlank(bslSaleInfoDetail.getProdMaterial())) {
			return  BSLResult.build(400, "生产销售计划出库钢种不能为空");
		}
		if(StringUtils.isBlank(bslSaleInfoDetail.getProdNorm())) {
			return  BSLResult.build(400, "生产销售计划出库规格不能为空");
		}
		//定尺只有在成品发货的时候不能为空,其他时候必须为空
		if(DictItemOperation.收发类别_成品发货.equals(bsFlag) 
				|| DictItemOperation.收发类别_委外仓成品发货.equals(bsFlag)
						|| DictItemOperation.收发类别_成品转场发货.equals(bsFlag) ){
			if(bslSaleInfoDetail.getProdLength() == null || bslSaleInfoDetail.getProdLength() == 0) {
				return  BSLResult.build(400, "成品发货，生产销售计划出库定尺不能为空");
			}
		}else{
			if(bslSaleInfoDetail.getProdLength() != null && bslSaleInfoDetail.getProdLength() > 0) {
				return  BSLResult.build(400, "非成品发货，生产销售计划出库定尺必须为空");
			}
		}
		try {
			return saleDetailService.updateSaleDetailInfo(bslSaleInfoDetail);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 根据销售出库通知单查询可出库的产品编号
	 * @param BslBsPlanInfo
	 * @return
	 */
	@RequestMapping(value="/getBsFlag", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult getBsFlag(String salePlanId) {
		if(StringUtils.isBlank(salePlanId)) {
			return  BSLResult.build(400, "生产销售通知单号不能为空");
		}
		try {
			return saleDetailService.getBsFlagAndProdInfoService(salePlanId);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 新增指定产品编号销售出库通知详细
	 * @param BslBsPlanInfo
	 * @return
	 */
	@RequestMapping(value="/addSaleDetailById", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult addsaleDetailInfo(String inputUser,String salePlanId,String[] arrays) {
		if(StringUtils.isBlank(salePlanId)) {
			return  BSLResult.build(400, "生产销售通知单号不能为空");
		}
		try {
			return saleDetailService.addSaleDetailInfoById(inputUser,salePlanId, arrays);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 修改指定产品的销售出库详细信息
	 */
	@RequestMapping(value="/editSaleDetailById", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult editsaleDetailInfo(BslSaleInfoDetail bslSaleInfoDetail) {
		if(StringUtils.isBlank(bslSaleInfoDetail.getSaleSerno())) {
			return  BSLResult.build(400, "生产销售出库详细流水号不能为空");
		}
		if(bslSaleInfoDetail.getSaleWeight() == null || bslSaleInfoDetail.getSaleWeight() == 0f) {
			return  BSLResult.build(400, "计划出库重量不能为空");
		}
		if(bslSaleInfoDetail.getSalePrice() == null || bslSaleInfoDetail.getSalePrice() == 0f) {
			return  BSLResult.build(400, "销售价格不能为空");
		}
		try {
			return saleDetailService.updateSaleDetailInfoById(bslSaleInfoDetail);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 删除销售出库详细
	 * @param BslBsPlanInfo
	 * @return
	 */
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult deleteSaleDetailInfo(BslSaleInfoDetail bslSaleInfoDetail) {
		if(StringUtils.isBlank(bslSaleInfoDetail.getSaleSerno())) {
			return BSLResult.build(400, "生产销售通知详细流水不能为空");
		}
		try {
			return saleDetailService.deleteSaleDetailInfo(bslSaleInfoDetail);
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
