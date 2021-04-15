package com.bsl.controller.waste;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslWxWasteWeightInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.waste.WasteWxService;

/**
 * @author 杜康
 * 废品管理Controller
 */
@Controller
@RequestMapping("/wasteWx")
public class WasteWxController {
	
	@Autowired
	private WasteWxService wasteWxService;
	
	/**
	 *根据条件查询废品信息
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getWasteServiceByCriteria(QueryCriteria queryCriteria){
		BSLResult result = wasteWxService.getWasteService(queryCriteria);
		return result;
	}
	
	/**
	 * 入库
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/in")
	@ResponseBody
	public BSLResult addWasteinfo(BslWxWasteWeightInfo bslWxWasteWeightInfo,String inputUser){
		if(StringUtils.isBlank(bslWxWasteWeightInfo.getWasteType())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "废品类型不能为空");
		}
		if(bslWxWasteWeightInfo.getWasteWeight() == null ||bslWxWasteWeightInfo.getWasteWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "入库重量不能为空");
		}
		try {
			return wasteWxService.updateWasteIn(bslWxWasteWeightInfo, inputUser);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
	}
	
	/**
	 * 质量校验修改
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/check")
	@ResponseBody
	public BSLResult checkWasteInfo(BslWxWasteWeightInfo bslWxWasteWeightInfo,String inputUser){
		if(StringUtils.isBlank(bslWxWasteWeightInfo.getWasteType())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "废品类型不能为空");
		}
		/*if(bslWxWasteWeightInfo.getWasteWeight() == null ||bslWxWasteWeightInfo.getWasteWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "实际重量不能为空");
		}*/
		try {
			return wasteWxService.updateWasteCheck(bslWxWasteWeightInfo, inputUser);
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
