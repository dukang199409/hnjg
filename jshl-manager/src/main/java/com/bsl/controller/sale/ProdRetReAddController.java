package com.bsl.controller.sale;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslProductInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.service.sale.ProdRetReAddService;

/**
 * @author 杜康
 * 退货重新入库管理Controller
 */
@Controller
@RequestMapping("/prodRetRe")
public class ProdRetReAddController {
	
	@Autowired
	ProdRetReAddService prodRetReAddService;
	
	/**
	 * 退货重新入库
	 * @param BslBsPlanInfo
	 * @return
	 */
	@RequestMapping(value="/reAdd", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult updateProdReturn(BslProductInfo bslProductInfo) {
		if(StringUtils.isBlank(bslProductInfo.getProdType())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品类型不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdName())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品名称不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdNorm())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品规格不能为空");
		}
		if(StringUtils.isBlank(bslProductInfo.getProdMaterial())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品钢种不能为空");
		}
		if(bslProductInfo.getProdNum() == null || bslProductInfo.getProdNum() == 0){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品件数不能为空");
		}
		if(bslProductInfo.getProdRelWeight() == null || bslProductInfo.getProdRelWeight() == 0) {
			return  BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品重量不能为空");
		}
		if(DictItemOperation.产品类型_成品.equals(bslProductInfo.getProdType())){
			if(bslProductInfo.getProdLength() == null || bslProductInfo.getProdLength() == 0) {
				return  BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "产品类型为产品时定尺不能为空");
			}
		}
		try {
			return prodRetReAddService.addRetProd(bslProductInfo);
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
