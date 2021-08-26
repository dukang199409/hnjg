package com.bsl.controller.waste;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslWasteWeightInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.waste.WasteService;

/**
 * @author 杜康
 * 废品管理Controller
 */
@Controller
@RequestMapping("/waste")
public class WasteController {
	
	@Autowired
	private WasteService wasteService;
	
	/**
	 *根据条件查询废品信息
	 */
	@RequestMapping("/listByCriteria")
	@ResponseBody
	public BSLResult getWasteServiceByCriteria(QueryCriteria queryCriteria){
		BSLResult result = wasteService.getWasteService(queryCriteria);
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
	public BSLResult addWasteinfo(BslWasteWeightInfo bslWasteWeightInfo,String inputUser){
		if(StringUtils.isBlank(bslWasteWeightInfo.getWasteType())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "废品类型不能为空");
		}
		if(bslWasteWeightInfo.getWasteWeight() == null ||bslWasteWeightInfo.getWasteWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "入库重量不能为空");
		}
		try {
			return wasteService.updateWasteIn(bslWasteWeightInfo, inputUser);
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
	public BSLResult checkWasteInfo(BslWasteWeightInfo bslWasteWeightInfo,String inputUser){
		if(StringUtils.isBlank(bslWasteWeightInfo.getWasteType())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "废品类型不能为空");
		}
		/*if(bslWasteWeightInfo.getWasteWeight() == null ||bslWasteWeightInfo.getWasteWeight() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "实际重量不能为空");
		}*/
		try {
			return wasteService.updateWasteCheck(bslWasteWeightInfo, inputUser);
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
