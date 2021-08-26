package com.bsl.controller.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bsl.common.PDFAndQRCodeUtil;
import com.bsl.common.SpringContextUtils;
import com.bsl.common.utils.DateUtil;
import com.bsl.common.utils.StringUtil;
import com.bsl.dao.JedisClient;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.pojo.BslMakePlanInfo;
import com.bsl.pojo.BslProductInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.QueryExample;
import com.bsl.service.plan.MakePlanService;
import com.bsl.service.prodmanager.ProdPlanService;
import com.bsl.service.rawmaterialsmanager.RawService;
import org.apache.commons.lang3.StringUtils;

@Controller
@RequestMapping("/import")
public class QRCodeAndPDFControlller {

	@Autowired
	private RawService rawService;
	
	@Autowired
	private BslProductInfoMapper bslProductInfoMapper;
	
	@Autowired
	private ProdPlanService prodPlanService;
	
	@Autowired
	private MakePlanService makePlanService;

	@RequestMapping(value = "/importPdf", method = RequestMethod.POST)
	public String importPdf(HttpServletRequest request, HttpServletResponse response, QueryExample example) {
		JedisClient jedisClient = (JedisClient)SpringContextUtils.getBean("jedisClient");
		try {
			switch (example.getTradeType()) {
				case "M1003":
					//原料入库
					return materialIn(request, jedisClient, example.getProdId());
				case "M2005":
					//半产品入库
					return semiProdIn(request, jedisClient, example.getProdId());
				case "M3005":
					//产品入库
					return prodIn(request, jedisClient, example.getProdId());
				case "M3101":
					//待处理品入库
					return prodDclIn(request, jedisClient, example.getProdId());
				default:
					break;
			}
		} finally {
			jedisClient.close();
		}
		return "error";
	}
	
	/**
	 * 纵剪带标签.pdf
	 * @param request
	 * @param prodId
	 * @return
	 */
	private String semiProdIn(HttpServletRequest request, JedisClient jedisClient, String prodId) {
		try {
			BslProductInfo productInfo = rawService.queryByPrimaryKey(prodId);
			String prodCompany = "";
			if(!StringUtils.isBlank(productInfo.getProdCompany())){
				prodCompany = productInfo.getProdCompany()+"-";
			}
			if(!StringUtils.isBlank(productInfo.getProdCustomer())){
				prodCompany += productInfo.getProdCustomer();
			}
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("prodName", StringUtil.castToString(productInfo.getProdName()));
			paramMap.put("prodNorm", productInfo.getProdNorm());
			paramMap.put("prodMaterial", jedisClient.get("prodMaterial:"+productInfo.getProdMaterial()));
			paramMap.put("prodCompany", prodCompany);
			paramMap.put("prodPrintWeight", StringUtil.castToString(productInfo.getProdPrintWeight())+"吨");
			paramMap.put("prodPlanNo", StringUtil.castToString(productInfo.getProdPlanNo()));
			paramMap.put("prodId", StringUtil.castToString(productInfo.getProdId()));
			paramMap.put("prodLevel", jedisClient.get("prodLevel:"+productInfo.getProdLevel()));
			paramMap.put("prodLuno", StringUtil.castToString(productInfo.getProdLuno()));
			BslMakePlanInfo bslMakePlanInfo = makePlanService.getMakePlanInfoById(productInfo.getProdPlanNo());
			paramMap.put("planDepartment", jedisClient.get("planDepartment:"+bslMakePlanInfo.getPlanDepartment()));// 生产班组
			paramMap.put("bsCompany",  StringUtil.castToString(productInfo.getProdCompany()));// 来料顾客
			paramMap.put("bsCarno", StringUtil.castToString(productInfo.getRemark()));// 运输
			paramMap.put("crtDate", DateUtil.getFormatText(productInfo.getCrtDate(), "yyyy-MM-dd HH:mm"));// DateUtil.getFormatText(productInfo.getCrtDate(),"yyyy/MM/dd")
			paramMap.put("prodInputuser", StringUtil.castToString(productInfo.getProdInputuser()));
	
			Map<String, String> fileNameMap = new HashMap<>();
			fileNameMap.put("qrCodeName", "qrcode_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".png");// 二维码名称DateUtil.getFormatText(new
																															// Date(),"yyyyMMddHHmmss")
			// productInfo转成json字符串
//			Map<String, Object> map = new HashMap<>();
//			map.put("prodId", productInfo.getProdId());
			fileNameMap.put("qrData", productInfo.getProdId());
			fileNameMap.put("pdfTemplateName", "纵剪带标签.pdf");// pdf模版名称
			String pdfFileName = "纵剪带标签" + productInfo.getProdId() + ".pdf";
			fileNameMap.put("newPDFName", pdfFileName);// 生成的pdf标签名称
			//创建pdf文件
			PDFAndQRCodeUtil.createQRCodeToPDFFile(paramMap, fileNameMap);
			
			request.setCharacterEncoding("utf-8");
			request.setAttribute("pdfName", pdfFileName);
			request.setAttribute("pdfname", "纵剪带标签");
			return "pdf";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

	/**
	 * 产品入库
	 * 冷弯型钢产品标签pdf
	 * @param prodId
	 * @return
	 */
	private String prodIn(HttpServletRequest request, JedisClient jedisClient, String prodId) {
		try {
			BslProductInfo productInfo = rawService.queryByPrimaryKey(prodId);
			String plan_department = "";
			if(DictItemOperation.是否标志_否.equals(productInfo.getProdDclFlag())){
				BslMakePlanInfo prodPlanInfo = prodPlanService.getProdPlanInfo(productInfo.getProdPlanNo());
				if(prodPlanInfo != null){
					plan_department = jedisClient.get("planDepartment:"+prodPlanInfo.getPlanDepartment());
				}
			}
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("prod_name", productInfo.getProdName());
			paramMap.put("prod_id", productInfo.getProdId());
			paramMap.put("prod_norm", productInfo.getProdNorm());
			paramMap.put("prod_material", jedisClient.get("prodMaterial:"+productInfo.getProdMaterial()));
			paramMap.put("prod_rel_weight", StringUtil.castToString(productInfo.getProdRelWeight())+"吨");
			paramMap.put("prod_length", StringUtil.castToString(productInfo.getProdLength())+"米");
			paramMap.put("prod_plan_no", StringUtil.castToString(productInfo.getProdPlanNo()));//生产批号 
			paramMap.put("prod_level", jedisClient.get("prodLevel:"+productInfo.getProdLevel()));
			paramMap.put("prod_source_company", "湖南巨工机械制造有限公司");// 制造厂商
			paramMap.put("telphone", "13907481234");// 电话
			paramMap.put("crtDate", DateUtil.getFormatText(productInfo.getCrtDate(), "yyyy-MM-dd HH:mm"));// DateUtil.getFormatText(productInfo.getCrtDate(),"yyyy/MM/dd")
			paramMap.put("prod_check_user", StringUtil.castToString(productInfo.getProdCheckuser()));
			paramMap.put("prod_num", StringUtil.castToString(productInfo.getProdNum()));
			paramMap.put("plan_department", plan_department );//生产班组
			paramMap.put("plan_luno", StringUtil.castToString(productInfo.getProdLuno()));//炉号
	
			Map<String, String> fileNameMap = new HashMap<>();
			fileNameMap.put("qrCodeName", "qrcode_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".png");// 二维码名称DateUtil.getFormatText(new
																															// Date(),"yyyyMMddHHmmss")
			// productInfo转成json字符串
//			Map<String, Object> map = new HashMap<>();
//			map.put("prodId", productInfo.getProdId());
//			map.put("prodPlanNo", productInfo.getProdPlanNo());
			fileNameMap.put("qrData", productInfo.getProdId());
			fileNameMap.put("pdfTemplateName", "冷弯型钢产品标签.pdf");// pdf模版名称
			String pdfFileName = "成品入库" + productInfo.getProdId() + ".pdf";
			fileNameMap.put("newPDFName", pdfFileName);// 生成的pdf标签名称
			//创建pdf文件
			PDFAndQRCodeUtil.createQRCodeToPDFFile(paramMap, fileNameMap);
			
			request.setAttribute("pdfName", pdfFileName);
			request.setAttribute("pdfname", "冷弯型钢产品标签");
			return "pdf";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}
	
	/**
	 * 待处理品入库
	 * 冷弯型钢产品标签pdf
	 * @param prodId
	 * @return
	 */
	private String prodDclIn(HttpServletRequest request, JedisClient jedisClient, String prodId) {
		try {
			BslProductInfo productInfo = bslProductInfoMapper.selectByPrimaryKey(prodId);
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("prod_name", productInfo.getProdName());
			paramMap.put("prod_id", productInfo.getProdId());
			paramMap.put("prod_norm", productInfo.getProdNorm());
			paramMap.put("prod_material", jedisClient.get("prodMaterial:"+productInfo.getProdMaterial()));
			paramMap.put("prod_rel_weight", StringUtil.castToString(productInfo.getProdRelWeight())+"吨");
			paramMap.put("prod_length", StringUtil.castToString(productInfo.getProdLength())+"米");
			paramMap.put("prod_plan_no", StringUtil.castToString(productInfo.getProdPlanNo()));//生产批号 
			paramMap.put("prod_level", jedisClient.get("prodLevel:"+productInfo.getProdLevel()));
			paramMap.put("prod_source_company", "湖南巨工机械制造有限公司");// 制造厂商
			paramMap.put("telphone", "13907481234");// 电话
			paramMap.put("crtDate", DateUtil.getFormatText(productInfo.getCrtDate(), "yyyy-MM-dd HH:mm"));// DateUtil.getFormatText(productInfo.getCrtDate(),"yyyy/MM/dd")
			paramMap.put("prod_check_user", StringUtil.castToString(productInfo.getProdCheckuser()));
			BslMakePlanInfo prodPlanInfo = prodPlanService.getProdPlanInfo(productInfo.getProdPlanNo());
			paramMap.put("prod_num", StringUtil.castToString(productInfo.getProdNum()));
			paramMap.put("plan_department",  jedisClient.get("planDepartment:"+prodPlanInfo.getPlanDepartment()));//生产班组
			paramMap.put("plan_luno", StringUtil.castToString(productInfo.getProdLuno()));//炉号
	
			Map<String, String> fileNameMap = new HashMap<>();
			fileNameMap.put("qrCodeName", "qrcode_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".png");// 二维码名称DateUtil.getFormatText(new
																															// Date(),"yyyyMMddHHmmss")
			// productInfo转成json字符串
//			Map<String, Object> map = new HashMap<>();
//			map.put("prodId", productInfo.getProdId());
//			map.put("prodPlanNo", productInfo.getProdPlanNo());
			fileNameMap.put("qrData", productInfo.getProdId());
			fileNameMap.put("pdfTemplateName", "冷弯型钢待处理品标签.pdf");// pdf模版名称
			String pdfFileName = "待处理品入库" + productInfo.getProdId() + ".pdf";
			fileNameMap.put("newPDFName", pdfFileName);// 生成的pdf标签名称
			//创建pdf文件
			PDFAndQRCodeUtil.createQRCodeToPDFFile(paramMap, fileNameMap);
			
			request.setAttribute("pdfName", pdfFileName);
			request.setAttribute("pdfname", "冷弯型钢待处理品标签");
			return "pdf";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

	/**
	 * 物料入库标签
	 * @param response
	 * @param prodId
	 */
	private String materialIn(HttpServletRequest request, JedisClient jedisClient, String prodId) {
		try {
			BslProductInfo productInfo = rawService.queryByPrimaryKey(prodId);
			String prodCompany = "";
			if(!StringUtils.isBlank(productInfo.getProdCompany())){
				prodCompany = productInfo.getProdCompany()+"-";
			}
			if(!StringUtils.isBlank(productInfo.getProdCustomer())){
				prodCompany += productInfo.getProdCustomer();
			}
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("prodName", StringUtil.castToString(productInfo.getProdName()));
			paramMap.put("prodNorm", productInfo.getProdNorm());
			paramMap.put("prodMaterial", jedisClient.get("prodMaterial:"+productInfo.getProdMaterial()));
			paramMap.put("prodRecordWeight", StringUtil.castToString(productInfo.getProdRecordWeight())+"吨");
			paramMap.put("prodPrintWeight", StringUtil.castToString(productInfo.getProdPrintWeight())+"吨");
			paramMap.put("prodPlanNo", StringUtil.castToString(productInfo.getProdLuno()));
			paramMap.put("prodId", StringUtil.castToString(productInfo.getProdId()));
			paramMap.put("prodOriId", StringUtil.castToString(productInfo.getProdOrirawid()));
			paramMap.put("prodLevel", jedisClient.get("prodLevel:"+productInfo.getProdLevel()));
			paramMap.put("makeCompany", "湖南巨工");// 制造厂商
			paramMap.put("bsCompany",  prodCompany);// 来料顾客
			paramMap.put("bsCarno", StringUtil.castToString(productInfo.getRemark()));// 运输
			paramMap.put("crtDate", DateUtil.getFormatText(productInfo.getCrtDate(), "MM-dd HH:mm"));// DateUtil.getFormatText(productInfo.getCrtDate(),"yyyy/MM/dd")
			paramMap.put("prodInputuser", StringUtil.castToString(productInfo.getProdInputuser()));
			paramMap.put("prodCompany", jedisClient.get("prodRuc:"+productInfo.getProdRuc()));
	
			Map<String, String> fileNameMap = new HashMap<>();
			fileNameMap.put("qrCodeName", "qrcode_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".png");// 二维码名称DateUtil.getFormatText(new
																															// Date(),"yyyyMMddHHmmss")
			// productInfo转成json字符串
//			Map<String, Object> map = new HashMap<>();
//			map.put("prodId", productInfo.getProdId());
			fileNameMap.put("qrData", productInfo.getProdId());
			fileNameMap.put("pdfTemplateName", "物料入库标签.pdf");// pdf模版名称
			String pdfFileName = "物料入库" + productInfo.getProdId() + ".pdf";
			fileNameMap.put("newPDFName", pdfFileName);// 生成的pdf标签名称
			//创建pdf文件
			PDFAndQRCodeUtil.createQRCodeToPDFFile(paramMap, fileNameMap);
			
			request.setCharacterEncoding("utf-8");
			request.setAttribute("pdfName", pdfFileName);
			request.setAttribute("pdfname", "物料入库标签");
			return "pdf";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

}
