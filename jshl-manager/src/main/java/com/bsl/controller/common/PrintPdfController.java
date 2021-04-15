package com.bsl.controller.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bsl.common.SpringContextUtils;
import com.bsl.common.pojo.BslMakePlanInfoDetailExtra;
import com.bsl.common.pojo.PDFCell;
import com.bsl.common.utils.BSLResult;
import com.bsl.common.utils.CreatePdfUtil;
import com.bsl.common.utils.DateUtil;
import com.bsl.common.utils.StringUtil;
import com.bsl.dao.JedisClient;
import com.bsl.pojo.BslBsPlanInfo;
import com.bsl.pojo.BslMakePlanInfo;
import com.bsl.pojo.BslMakePlanInfoDetail;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslSaleInfoDetail;
import com.bsl.pojo.BslProductInfoExample.Criteria;
import com.bsl.reportbean.BslProductInfoCollect;
import com.bsl.reportbean.BslProductQualityInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryExample;
import com.bsl.service.plan.MakePlanDetailService;
import com.bsl.service.plan.MakePlanService;
import com.bsl.service.prodmanager.ProdPlanService;
import com.bsl.service.prodmanager.ProdService;
import com.bsl.service.rawmaterialsmanager.ReceiptService;
import com.bsl.service.sale.QualityPrintService;
import com.bsl.service.sale.SaleDetailService;
import com.bsl.service.sale.SalePlanService;
import com.itextpdf.text.Element;

/**
 * 打印
 * @author huangyl
 * @date 2019年5月13日  
 *
 */
@Controller
@RequestMapping("/print")
public class PrintPdfController {
	
	@Autowired
	private MakePlanService makePlanService;
	
	@Autowired
	private MakePlanDetailService makePlanDetailService;
	
	@Autowired
	private ProdPlanService prodPlanService;
	
	@Autowired
	private ReceiptService receiptService;
	
	@Autowired
	private ProdService prodService;
	
	@Autowired
	private SalePlanService salePlanService;
	
	@Autowired
	private SaleDetailService saleDetailService;
	
	@Autowired
	private QualityPrintService qualityPrintService;
	
	@Value("${REDIS_NEXT_QUALITY_ID}")
	private String REDIS_NEXT_QUALITY_ID;
	
	@RequestMapping(value = "/exportPdf", method = RequestMethod.POST)
	public String exportPdf(QueryExample example,HttpServletRequest request, HttpServletResponse response) throws Exception {		
		switch (example.getTradeType()) {
			case "M1001":
				return receipt(example, request, response);
			case "M2001":
				return semiFinishProd(example, request, response);
			case "M3001":
				return finishedProd(example, request, response);
			//销售出库单详单
			case "M5001":
				return saleProd(example, request, response);
			//销售出库通知单
			case "M5001o":
				return saleProdO(example, request, response);
			//销售出库通知单选择打印（根据产品）
			case "M5001Prods":
				return saleProdByIds(example, request, response);
			case "M5001Quality":
				return quality(example, request, response);
			case "M5001Waste":
				return saleWasteByIds(example, request, response);
			case "M5001ProdsByCar":
				return saleProdByCarIds(example, request, response);
			default:
				break;
		}
		return "error";
	}
	
	/**
	 * 质量通知书
	 * @param example
	 * @param request
	 * @param response
	 * @return
	 */
	private String quality(QueryExample example, HttpServletRequest request, HttpServletResponse response) {
		//先根据车号获取产品汇总信息以及路号信息
		String[] prodOutCarnos = example.getProdOutCarnos();
		List<String> prodOutCarnosList = new ArrayList<String>();
		if(prodOutCarnos!= null && prodOutCarnos.length>0){
			for (String prodOutCarno : prodOutCarnos) {
				prodOutCarnosList.add(prodOutCarno);
				//先记录打印次数
				qualityPrintService.updatePrintNum(prodOutCarno);
			}
		}
		List<BslProductQualityInfo> bslProductQualityInfoLists = qualityPrintService.getCarDetailByList(prodOutCarnosList);
		
		//获取客户信息
		BslBsPlanInfo bslBsPlanInfo = salePlanService.getSalePlanByCarno(prodOutCarnos[0]);
		
		JedisClient jedisClient = (JedisClient)SpringContextUtils.getBean("jedisClient");
		
		String outDateString;
		if(prodOutCarnos[0].length()>=9){
			outDateString = prodOutCarnos[0].substring(1,5) + "-" + prodOutCarnos[0].substring(5,7)
					+ "-" + prodOutCarnos[0].substring(7,9);
		}else{
			outDateString = DateUtil.getFormatText(new Date(),"yyyy-MM-dd");
		}
		
		long incr = jedisClient.incr(REDIS_NEXT_QUALITY_ID);
		try {
			if(bslBsPlanInfo != null) {
				
				String [] fields = {"prodPlanNo","prodLuno","prodNorm","prodMaterial","prodLength","sumProdNum","sumProdWeight","chemicalC","chemicalSi","chemicalMn","chemicalP","chemicalS", "chemicalTi","chemicalNi","chemicalCr","chemicalCu","mechanicalS","mechanicalB","mechanicalL","bendWC","bendYB","impactT","impactN1","impactN2","impactN3"};
				String [] titles = {"生产批号\nPRODPLANNO","炉(批)号\nHEAT NO","规格\nDESCRIPTION","材质\nMATERIAL","定尺(m)\nLENGTH","支数\nPIECES", "重量(t)\nN.W.","×100","×100","×100","×1000","×1000","×100","×100","×100","×100","MPa","MPa","%","BEND180ºd=a","FLATTEN","℃","1","2","3"};
				List<BslProductQualityInfo> inList = new ArrayList<BslProductQualityInfo>();
				
				Integer prodNumSum = 0;
				Float prodWeightTotal = 0f;
				String prodLevel = "";
				String prodRuc = "";
				List<String> prodLevels = new ArrayList<String>();
				List<String> prodRucs = new ArrayList<String>();
				for (BslProductQualityInfo bslProductQualityInfo : bslProductQualityInfoLists) {
					inList.add(bslProductQualityInfo);
					prodNumSum += bslProductQualityInfo.getSumProdNum();
					prodWeightTotal += bslProductQualityInfo.getSumProdWeight();
					if(!StringUtils.isBlank(bslProductQualityInfo.getProdLevel()) 
							&& !prodLevels.contains(bslProductQualityInfo.getProdLevel())){
						prodLevel += bslProductQualityInfo.getProdLevel() + "\n";
						prodLevels.add(bslProductQualityInfo.getProdLevel());
					}
					if(!StringUtils.isBlank(bslProductQualityInfo.getProdRuc()) 
							&& !prodRucs.contains(bslProductQualityInfo.getProdRuc())){
						prodRuc += jedisClient.get("prodRuc:"+bslProductQualityInfo.getProdRuc()) + "\n";
						prodRucs.add(bslProductQualityInfo.getProdRuc());
					}
				}
				prodWeightTotal = ((float)Math.round(prodWeightTotal*1000))/1000;
				
				String prodName = "方管";
				if("2".equals(bslBsPlanInfo.getBsFlag())){
					prodName = "纵剪带";
				}else if ("3".equals(bslBsPlanInfo.getBsFlag())) {
					prodName = "方管";
				}else if ("5".equals(bslBsPlanInfo.getBsFlag()) || "6".equals(bslBsPlanInfo.getBsFlag())) {
					prodName = "卷板";
				}
				
				//表头
				List<PDFCell> headList = new ArrayList<>();

				headList.add(new PDFCell("湖南巨工机械制造有限公司\nHunan Precious Sunlion Roll \nForming Technology Co., Ltd.", CreatePdfUtil.keyfont1, Element.ALIGN_LEFT, 9, false));
				headList.add(new PDFCell("产品质量证明书\nINSPECTION CERTIFICATE", CreatePdfUtil.headfont, Element.ALIGN_CENTER, 16, false));
				headList.add(new PDFCell("湖南省常德市桃源县陬市镇东新路\nDongxin Road, Zoushi Town,Taoyuan County, \nChangde City,Hunan Province.", CreatePdfUtil.keyfont1, Element.ALIGN_RIGHT, 9, false));
				headList.add(new PDFCell("本证明书随货同行，等同销售出库单 THIS CERTIFICATE IS ACCOMPANIED BY THE GOODS AND IS EQUIVALENT TO THE SALE OF THE TREASURY BILL", 
						CreatePdfUtil.keyfont1, Element.ALIGN_LEFT, 34, false));
				
				headList.add(new PDFCell("订货单位\nPUROHASER", CreatePdfUtil.keyfont1, Element.ALIGN_CENTER, 4, true));
				headList.add(new PDFCell(bslBsPlanInfo.getBsCustomer(), CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 12, true));
				headList.add(new PDFCell("产品名称\nCOMMODITY", CreatePdfUtil.keyfont1, Element.ALIGN_CENTER, 5, true));
				headList.add(new PDFCell(prodName, CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 4, true));
				headList.add(new PDFCell("签发日期\nDATE OF ISSUE", CreatePdfUtil.keyfont1, Element.ALIGN_CENTER, 5, true));
				headList.add(new PDFCell(DateUtil.getFormatText(new Date(),"yyyy-MM-dd HH:mm:ss"), CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 4, true));
				
				headList.add(new PDFCell("收货单位\nDELIVERY TO", CreatePdfUtil.keyfont1, Element.ALIGN_CENTER, 4, true));
				headList.add(new PDFCell(bslBsPlanInfo.getBsCustomer(), CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 12, true));
				headList.add(new PDFCell("订单编号\nORDER NO", CreatePdfUtil.keyfont1, Element.ALIGN_CENTER, 5, true));
				headList.add(new PDFCell(bslBsPlanInfo.getBsOrderNo(), CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 4, true));
				headList.add(new PDFCell("发货日期\nDATE OF DELIVERY", CreatePdfUtil.keyfont1, Element.ALIGN_CENTER, 5, true));
				headList.add(new PDFCell(outDateString, CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 4, true));
				
				headList.add(new PDFCell("收货地址\nADDRESS", CreatePdfUtil.keyfont1, Element.ALIGN_CENTER, 4, true));
				headList.add(new PDFCell("", CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 12, true));
				headList.add(new PDFCell("合同号\nMILL'S", CreatePdfUtil.keyfont1, Element.ALIGN_CENTER, 5, true));
				headList.add(new PDFCell("", CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 4, true));
				headList.add(new PDFCell("证明书号\nCERTIFICATE NO", CreatePdfUtil.keyfont1, Element.ALIGN_CENTER, 5, true));
				headList.add(new PDFCell(DateUtil.getFormatText(new Date(),"yyyyMMdd")+"-"+incr, CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 4, true));
				
				headList.add(new PDFCell("标准\nSPECIFICATION", CreatePdfUtil.keyfont1, Element.ALIGN_CENTER, 4, true));
				headList.add(new PDFCell(prodLevel, CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 4, true));
				headList.add(new PDFCell("发货仓库\nDELIVERY WAREHOUSE", CreatePdfUtil.keyfont1, Element.ALIGN_CENTER, 4, true));
				headList.add(new PDFCell(prodRuc, CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 4, true));
				headList.add(new PDFCell("许可证号\nLIOCENSE NO", CreatePdfUtil.keyfont1, Element.ALIGN_CENTER, 5, true));
				headList.add(new PDFCell("", CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 4, true));
				headList.add(new PDFCell("交货状态\nMETHOD OF MANUF ACTURING", CreatePdfUtil.keyfont1, Element.ALIGN_CENTER, 5, true));
				headList.add(new PDFCell("冷加工", CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 4, true));
				
				headList.add(new PDFCell("产品信息\nPRODUCT INFORMATION", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 16, true));
				headList.add(new PDFCell("化学成分\nCHEMICAL COMPOSITION %", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 9, true));
				headList.add(new PDFCell("拉伸试验\nTENSILE TEST", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell("冷弯试验", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell("冲击试验\nIMPACT TEST", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 5, true));
				
				headList.add(new PDFCell("销售出库单\nTHE SALE OF THE TREASURY BILL", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 16, true));
				headList.add(new PDFCell("C", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 1, true));
				headList.add(new PDFCell("Si", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 1, true));
				headList.add(new PDFCell("Mn", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 1, true));
				headList.add(new PDFCell("P", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 1, true));
				headList.add(new PDFCell("S", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 1, true));
				headList.add(new PDFCell("Ti", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 1, true));
				headList.add(new PDFCell("Ni", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 1, true));
				headList.add(new PDFCell("Cr", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 1, true));
				headList.add(new PDFCell("Cu", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 1, true));
				headList.add(new PDFCell("屈服", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 1, true));
				headList.add(new PDFCell("抗拉", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 1, true));
				headList.add(new PDFCell("伸长", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 1, true));
				headList.add(new PDFCell("弯曲", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 1, true));
				headList.add(new PDFCell("压扁", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 1, true));
				headList.add(new PDFCell("温度\nTEMP", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 1, true));
				headList.add(new PDFCell("冲击功（焦耳）\nIMPACT ENERGY", CreatePdfUtil.keyfont2, Element.ALIGN_CENTER, 3, true));

				List<PDFCell> footList = new ArrayList<>();
				footList.add(new PDFCell("合计\nTOTAL", CreatePdfUtil.keyfont1, Element.ALIGN_CENTER, 7, true));
				footList.add(new PDFCell("数量QTY："+StringUtil.castToString(prodNumSum), CreatePdfUtil.keyfont1, Element.ALIGN_CENTER, 6, true));
				footList.add(new PDFCell("重量WEIGHT："+StringUtil.castToString(prodWeightTotal), CreatePdfUtil.keyfont1, Element.ALIGN_CENTER, 6, true));
				footList.add(new PDFCell("无损检测", CreatePdfUtil.keyfont1, Element.ALIGN_CENTER, 5, true));
				footList.add(new PDFCell("合格", CreatePdfUtil.keyfont1, Element.ALIGN_CENTER, 10, true));
				
				footList.add(new PDFCell("注  释\nNOTES", CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 7, true));
				footList.add(new PDFCell("N.W.=NET WEIGHT  Y.S.=YIELD STRENGTH  T.S.=TENSILE STRENGTH  EL=ELONGATION", CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 17, true));
				footList.add(new PDFCell("制表人\nLISTER", CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 5, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 5, true));
				
				footList.add(new PDFCell("会签者\n\nSURVEYOR TO", CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 7, true));
				footList.add(new PDFCell("本产品已按上述要求进行制造和检验其结果符合要求，特此证明。（复印件无效）\n"
						+ "WE HEREBY CERTIFY THAT MATERIAL DESCRIBED HEREIN HAS MANUFACTURED\n"
						+ "AND TESTED WITH SATISFACTORY RESULTS IN ACCORDANCE WITH THE REQUIRENTS\n"
						+ "OF THE ABOVE MATERIAL SPECIFICATION.", CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 17, true));
				footList.add(new PDFCell("质量检验中心主任（章）\n\nDIRECTOR OF THETECHNICAL DEPARTMENT", CreatePdfUtil.textfont2, Element.ALIGN_CENTER, 10, true));
				
				String pdfName = "产品质量证明书.pdf";
				String savePath = Thread.currentThread().getContextClassLoader().getResource("pdf/").getPath() + pdfName;
				new CreatePdfUtil().generatePDFs2(headList, fields, titles, inList, footList, savePath);
				request.setAttribute("pdfName", pdfName);
				request.setAttribute("pdfname", "产品质量证明书");
				return "pdf";
			}
		}catch (Exception e) {
			e.printStackTrace();
			DictItemOperation.log.error("打印pdf出现异常", e);
		} finally {
			jedisClient.close();
		}
		return "error";
	}

	/**
	 * 销售出库通知单选择打印
	 * @param example
	 * @param request
	 * @param response
	 * @return
	 */
	private String saleProdByIds(QueryExample queryExample, HttpServletRequest request, HttpServletResponse response) {
		BSLResult result = receiptService.getReceiptByBsBsId(queryExample.getPlanId());
		String[] prodIds =queryExample.getProdIds();
		List<String> prodIdsList = new ArrayList<String>();
		if(prodIds!= null && prodIds.length>0){
			for (String string : prodIds) {
				prodIdsList.add(string);
			}
			queryExample.setProdIdsList(prodIdsList);
		}
		//先更新这些产品的车号信息
		String prodOutCarno = queryExample.getProdOutCarno();
		if(StringUtils.isBlank(prodOutCarno)){
			prodOutCarno = "BSL";
		}
		String prodOutCarnoNew = "";
		if(!StringUtils.isBlank(prodOutCarno)){
			String wxFlag = queryExample.getWxFlag();
			BSLResult resInt = prodService.updateProdCarNo(prodIdsList, prodOutCarno);
			String bsGettype = queryExample.getBsGettype();
			if(resInt.getStatus() != 200){
				return "error";
			}
			prodOutCarnoNew = resInt.getData().toString();
			//更新完毕插入车次信息
			BSLResult resIntCC = qualityPrintService.insertCCInfo(prodOutCarnoNew, wxFlag,bsGettype);
			if(resIntCC.getStatus() != 200){
				return "error";
			}
		}
		//获取出库仓库
		JedisClient jedisClient = (JedisClient)SpringContextUtils.getBean("jedisClient");
		String prodRuc = "";
		String[] prodRucs =queryExample.getProdRucs();
		List<String> listProdRucs = new ArrayList<String>();
		if(prodRucs!= null && prodRucs.length>0){
			for (String string : prodRucs) {
				if(!listProdRucs.contains(string)){
					prodRuc += jedisClient.get("prodRuc:"+string) + "\t";
					listProdRucs.add(string);
				}
			}
		}
		
		BslBsPlanInfo bslBsPlanInfo = null;
		if(result != null && result.getStatus() == ErrorCodeInfo.交易成功) {
			bslBsPlanInfo = (BslBsPlanInfo)result.getData();
		}
		try {
			if(bslBsPlanInfo != null) {
				List<BslProductInfoCollect> bslSaleInfoDetails = prodService.querySaleOutByProds(queryExample);
				if(prodIdsList == null || prodIdsList.size()<=0){
					bslSaleInfoDetails = new ArrayList<BslProductInfoCollect>();
				}
				//表头
				List<PDFCell> headList = new ArrayList<>();
				headList.add(new PDFCell("销售出库单", CreatePdfUtil.headfont, Element.ALIGN_CENTER, 11, true));
				headList.add(new PDFCell("制单日期："+DateUtil.getFormatText(new Date(),"yyyy-MM-dd HH:mm:ss"), CreatePdfUtil.keyfont, Element.ALIGN_RIGHT, 11, true));
				headList.add(new PDFCell("供应商：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell("湖南巨工机械制造有限公司", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 8, true));
				headList.add(new PDFCell("订单客户：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell(bslBsPlanInfo.getBsCustomer(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				headList.add(new PDFCell("出库车次：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(prodOutCarnoNew, CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				headList.add(new PDFCell("出库单号：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell(!StringUtils.isBlank(queryExample.getPlanDetailId()) ? StringUtil.castToString(bslBsPlanInfo.getBsId())+"/"+queryExample.getPlanDetailId() : StringUtil.castToString(bslBsPlanInfo.getBsId()), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				Date updateDate = bslBsPlanInfo.getUpdDate();
				if(!StringUtils.isBlank(queryExample.getPlanDetailId())) {
					BslSaleInfoDetail bslSaleInfoDetail = saleDetailService.getBslSaleInfoDetailById(queryExample.getPlanDetailId());
					updateDate = bslSaleInfoDetail.getUpdDate();
				}
				headList.add(new PDFCell("出库日期：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(DateUtil.getFormatText(updateDate,"yyyy-MM-dd"), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				headList.add(new PDFCell("出库仓库：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell(prodRuc, CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				headList.add(new PDFCell("出库类别：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(jedisClient.get("bsFlagS:"+bslBsPlanInfo.getBsFlag()), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				
				String [] fields = {"makeName","prodNorm","prodOrirawid","prodMaterial","prodLevel","prodNum","unit","planOutputVolume","prodWeight","planFinistDate"};
				String [] titles = {"生产批号","炉号","厂家卷号","物料名称","规格", "钢种", "定尺(米)","数量(卷/支)", "重量(吨)","质量等级"};
				List inList = new ArrayList<>();
				
				Integer prodNumSum = 0;
				Float prodWeightTotal = 0f;
				for (BslProductInfoCollect bslProductInfo : bslSaleInfoDetails) {
					inList.add(
							new BslMakePlanInfoDetailExtra(
									bslProductInfo.getProdPlanNo(),
									bslProductInfo.getProdLuno(),
									bslProductInfo.getProdOrirawid(),
									bslProductInfo.getProdName(),
									bslProductInfo.getProdNorm(),
									jedisClient.get("prodMaterial:"+bslProductInfo.getProdMaterial()),
									StringUtil.castToString(bslProductInfo.getProdLength()),
									StringUtil.castToString(bslProductInfo.getProdNumCount()),
									StringUtil.castToString(bslProductInfo.getProdWeightTotal()),
									jedisClient.get("prodLevel:"+bslProductInfo.getProdLevel())
									)
							);
					prodNumSum += bslProductInfo.getProdNumCount();
					prodWeightTotal += bslProductInfo.getProdWeightTotal();
				}
				prodWeightTotal = ((float)Math.round(prodWeightTotal*1000))/1000;
				List<PDFCell> footList = new ArrayList<>();
				footList.add(new PDFCell("合计", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				if(prodNumSum == 0 && prodWeightTotal == 0f) {
					for (int i = 0; i < 10; i++) {
						footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
					}
				} else {
					for (int i = 0; i < 7; i++) {
						footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
					}
					footList.add(new PDFCell(StringUtil.castToString(prodNumSum), CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
					footList.add(new PDFCell(StringUtil.castToString(prodWeightTotal), CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
					footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
				}
				footList.add(new PDFCell("备   注：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 8, true));
				footList.add(new PDFCell("收货签名：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				footList.add(new PDFCell("签收日期：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, false));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				footList.add(new PDFCell("第一联：留底联（白色）   第二联：客户联（粉红）    第三联：回单联    第四联：财务联    第五联：门卫联", CreatePdfUtil.keyfont, Element.ALIGN_LEFT, 11, true));
				footList.add(new PDFCell("制单：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell(queryExample.getLoginUserId(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 2, true));
				footList.add(new PDFCell("复核：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 2, true));
				footList.add(new PDFCell("审批：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				String pdfName = "销售调度通知单.pdf";
				String savePath = Thread.currentThread().getContextClassLoader().getResource("pdf/").getPath() + pdfName;
				new CreatePdfUtil().generatePDFs(headList, fields, titles, inList, footList, savePath);
				request.setAttribute("pdfName", pdfName);
				request.setAttribute("pdfname", "销售调度通知单");
				return "pdf";
			}
		}catch (Exception e) {
			e.printStackTrace();
			DictItemOperation.log.error("打印pdf出现异常", e);
		} finally {
			jedisClient.close();
		}
		return "error";
	}
	
	/**
	 * 销售出库通知单
	 * @param example
	 * @param request
	 * @param response
	 * @return
	 */
	private String saleProdO(QueryExample queryExample, HttpServletRequest request, HttpServletResponse response) {
		BSLResult result = receiptService.getReceiptByBsBsId(queryExample.getPlanId());
		BslBsPlanInfo bslBsPlanInfo = null;
		if(result != null && result.getStatus() == ErrorCodeInfo.交易成功) {
			bslBsPlanInfo = (BslBsPlanInfo)result.getData();
		}
		JedisClient jedisClient = (JedisClient)SpringContextUtils.getBean("jedisClient");
		try {
			if(bslBsPlanInfo != null) {
				List<BslProductInfoCollect> bslSaleInfoDetails;
				String bsFlag = bslBsPlanInfo.getBsFlag();
				if(DictItemOperation.收发类别_委外仓废品发货 .equals(bsFlag) || DictItemOperation.收发类别_废品发货 .equals(bsFlag)
						|| DictItemOperation.收发类别_废品转场发货 .equals(bsFlag)){
					bslSaleInfoDetails = prodService.querySaleOutBillWaste(queryExample);
				}else{
					bslSaleInfoDetails = prodService.querySaleOutBill(queryExample);
				}
				//表头
				List<PDFCell> headList = new ArrayList<>();
				headList.add(new PDFCell("销售出库单", CreatePdfUtil.headfont, Element.ALIGN_CENTER, 11, true));
				headList.add(new PDFCell("制单日期："+DateUtil.getFormatText(new Date(),"yyyy-MM-dd HH:mm:ss"), CreatePdfUtil.keyfont, Element.ALIGN_RIGHT, 11, true));
				headList.add(new PDFCell("供应商：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell("湖南巨工机械制造有限公司", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 8, true));
				headList.add(new PDFCell("订单客户：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell(bslBsPlanInfo.getBsCustomer(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 8, true));
				headList.add(new PDFCell("出库单号：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell(!StringUtils.isBlank(queryExample.getPlanDetailId()) ? StringUtil.castToString(bslBsPlanInfo.getBsId())+"/"+queryExample.getPlanDetailId() : StringUtil.castToString(bslBsPlanInfo.getBsId()), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				Date updateDate = bslBsPlanInfo.getUpdDate();
				if(!StringUtils.isBlank(queryExample.getPlanDetailId())) {
					BslSaleInfoDetail bslSaleInfoDetail = saleDetailService.getBslSaleInfoDetailById(queryExample.getPlanDetailId());
					updateDate = bslSaleInfoDetail.getUpdDate();
				}
				headList.add(new PDFCell("出库日期：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(DateUtil.getFormatText(updateDate,"yyyy-MM-dd"), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				headList.add(new PDFCell("仓   库：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				headList.add(new PDFCell("出库类别：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(jedisClient.get("bsFlagS:"+bsFlag), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				
				String [] fields = {"makeName","prodNorm","prodOrirawid","prodMaterial","prodLevel","prodNum","unit","planOutputVolume","prodWeight","planFinistDate"};
				String [] titles = {"生产批号","炉号","厂家卷号","物料名称","规格", "钢种", "定尺(米)","数量(卷/支)", "重量(吨)","质量等级"};
				List inList = new ArrayList<>();
				
				Integer prodNumSum = 0;
				Float prodWeightTotal = 0f;
				for (BslProductInfoCollect bslProductInfo : bslSaleInfoDetails) {
					inList.add(
							new BslMakePlanInfoDetailExtra(
									bslProductInfo.getProdPlanNo(),
									bslProductInfo.getProdLuno(),
									bslProductInfo.getProdOrirawid(),
									bslProductInfo.getProdName(),
									bslProductInfo.getProdNorm(),
									jedisClient.get("prodMaterial:"+bslProductInfo.getProdMaterial()),
									StringUtil.castToString(bslProductInfo.getProdLength()),
									StringUtil.castToString(bslProductInfo.getProdNumCount()),
									StringUtil.castToString(bslProductInfo.getProdWeightTotal()),
									jedisClient.get("prodLevel:"+bslProductInfo.getProdLevel())
									)
							);
					prodNumSum += bslProductInfo.getProdNumCount();
					prodWeightTotal += bslProductInfo.getProdWeightTotal();
				}
				prodWeightTotal = ((float)Math.round(prodWeightTotal*1000))/1000;
				List<PDFCell> footList = new ArrayList<>();
				footList.add(new PDFCell("合计", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				if(prodNumSum == 0 && prodWeightTotal == 0f) {
					for (int i = 0; i < 10; i++) {
						footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
					}
				} else {
					for (int i = 0; i < 7; i++) {
						footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
					}
					footList.add(new PDFCell(StringUtil.castToString(prodNumSum), CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
					footList.add(new PDFCell(StringUtil.castToString(prodWeightTotal), CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
					footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
				}
				footList.add(new PDFCell("备   注：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 8, true));
				footList.add(new PDFCell("收货签名：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				footList.add(new PDFCell("签收日期：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, false));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				footList.add(new PDFCell("第一联：留底联（白色）   第二联：客户联（粉红）    第三联：回单联    第四联：财务联    第五联：门卫联", CreatePdfUtil.keyfont, Element.ALIGN_LEFT, 11, true));
				footList.add(new PDFCell("制单：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell(queryExample.getLoginUserId(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 2, true));
				footList.add(new PDFCell("复核：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 2, true));
				footList.add(new PDFCell("审批：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				String pdfName = "销售调度通知单.pdf";
				String savePath = Thread.currentThread().getContextClassLoader().getResource("pdf/").getPath() + pdfName;
				new CreatePdfUtil().generatePDFs(headList, fields, titles, inList, footList, savePath);
				request.setAttribute("pdfName", pdfName);
				request.setAttribute("pdfname", "销售调度通知单");
				return "pdf";
			}
		}catch (Exception e) {
			e.printStackTrace();
			DictItemOperation.log.error("打印pdf出现异常", e);
		} finally {
			jedisClient.close();
		}
		return "error";
	}
	
	/**
	 * 废品销售出库通知单
	 * @param example
	 * @param request
	 * @param response
	 * @return
	 */
	private String saleWasteByIds(QueryExample queryExample, HttpServletRequest request, HttpServletResponse response) {
		BSLResult result = receiptService.getReceiptByBsBsId(queryExample.getPlanId());
		BslBsPlanInfo bslBsPlanInfo = null;
		if(result != null && result.getStatus() == ErrorCodeInfo.交易成功) {
			bslBsPlanInfo = (BslBsPlanInfo)result.getData();
		}
		JedisClient jedisClient = (JedisClient)SpringContextUtils.getBean("jedisClient");
		try {
			if(bslBsPlanInfo != null) {
				List<BslProductInfoCollect> bslSaleInfoDetails = prodService.querySaleOutBillWaste(queryExample);
				String bsFlag = bslBsPlanInfo.getBsFlag();
				//表头
				List<PDFCell> headList = new ArrayList<>();
				headList.add(new PDFCell("销售出库单", CreatePdfUtil.headfont, Element.ALIGN_CENTER, 11, true));
				headList.add(new PDFCell("制单日期："+DateUtil.getFormatText(new Date(),"yyyy-MM-dd HH:mm:ss"), CreatePdfUtil.keyfont, Element.ALIGN_RIGHT, 11, true));
				headList.add(new PDFCell("供应商：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell("湖南巨工机械制造有限公司", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 8, true));
				headList.add(new PDFCell("订单客户：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell(bslBsPlanInfo.getBsCustomer(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 8, true));
				headList.add(new PDFCell("出库单号：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell(!StringUtils.isBlank(queryExample.getPlanDetailId()) ? StringUtil.castToString(bslBsPlanInfo.getBsId())+"/"+queryExample.getPlanDetailId() : StringUtil.castToString(bslBsPlanInfo.getBsId()), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				Date updateDate = bslBsPlanInfo.getUpdDate();
				if(!StringUtils.isBlank(queryExample.getPlanDetailId())) {
					BslSaleInfoDetail bslSaleInfoDetail = saleDetailService.getBslSaleInfoDetailById(queryExample.getPlanDetailId());
					updateDate = bslSaleInfoDetail.getUpdDate();
				}
				headList.add(new PDFCell("出库日期：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(DateUtil.getFormatText(updateDate,"yyyy-MM-dd"), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				headList.add(new PDFCell("仓   库：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				headList.add(new PDFCell("出库类别：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(jedisClient.get("bsFlagS:"+bsFlag), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				
				String [] fields = {"makeName","prodNorm","prodOrirawid","prodMaterial","prodLevel","prodNum","unit","planOutputVolume","prodWeight","planFinistDate"};
				String [] titles = {"生产批号","炉号","厂家卷号","物料名称","规格", "钢种", "定尺(米)","数量(卷/支)", "重量(吨)","质量等级"};
				List inList = new ArrayList<>();
				
				Integer prodNumSum = 0;
				Float prodWeightTotal = 0f;
				for (BslProductInfoCollect bslProductInfo : bslSaleInfoDetails) {
					inList.add(
							new BslMakePlanInfoDetailExtra(
									bslProductInfo.getProdPlanNo(),
									bslProductInfo.getProdLuno(),
									bslProductInfo.getProdOrirawid(),
									bslProductInfo.getProdName(),
									bslProductInfo.getProdNorm(),
									jedisClient.get("prodMaterial:"+bslProductInfo.getProdMaterial()),
									StringUtil.castToString(bslProductInfo.getProdLength()),
									StringUtil.castToString(bslProductInfo.getProdNumCount()),
									StringUtil.castToString(bslProductInfo.getProdWeightTotal()),
									jedisClient.get("prodLevel:"+bslProductInfo.getProdLevel())
									)
							);
					prodNumSum += bslProductInfo.getProdNumCount();
					prodWeightTotal += bslProductInfo.getProdWeightTotal();
				}
				prodWeightTotal = ((float)Math.round(prodWeightTotal*1000))/1000;
				List<PDFCell> footList = new ArrayList<>();
				footList.add(new PDFCell("合计", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				if(prodNumSum == 0 && prodWeightTotal == 0f) {
					for (int i = 0; i < 10; i++) {
						footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
					}
				} else {
					for (int i = 0; i < 7; i++) {
						footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
					}
					footList.add(new PDFCell(StringUtil.castToString(prodNumSum), CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
					footList.add(new PDFCell(StringUtil.castToString(prodWeightTotal), CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
					footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
				}
				footList.add(new PDFCell("备   注：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 8, true));
				footList.add(new PDFCell("收货签名：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				footList.add(new PDFCell("签收日期：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, false));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				footList.add(new PDFCell("第一联：留底联（白色）   第二联：客户联（粉红）    第三联：回单联    第四联：财务联    第五联：门卫联", CreatePdfUtil.keyfont, Element.ALIGN_LEFT, 11, true));
				footList.add(new PDFCell("制单：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell(queryExample.getLoginUserId(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 2, true));
				footList.add(new PDFCell("复核：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 2, true));
				footList.add(new PDFCell("审批：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				String pdfName = "销售调度通知单.pdf";
				String savePath = Thread.currentThread().getContextClassLoader().getResource("pdf/").getPath() + pdfName;
				new CreatePdfUtil().generatePDFs(headList, fields, titles, inList, footList, savePath);
				request.setAttribute("pdfName", pdfName);
				request.setAttribute("pdfname", "销售调度通知单");
				return "pdf";
			}
		}catch (Exception e) {
			e.printStackTrace();
			DictItemOperation.log.error("打印pdf出现异常", e);
		} finally {
			jedisClient.close();
		}
		return "error";
	}
	
	/**
	 * 销售出库单详单
	 * @param example
	 * @param request
	 * @param response
	 * @return
	 */
	private String saleProd(QueryExample queryExample, HttpServletRequest request, HttpServletResponse response) {
		BSLResult result = receiptService.getReceiptByBsBsId(queryExample.getPlanId());
		BslBsPlanInfo bslBsPlanInfo = null;
		if(result != null && result.getStatus() == ErrorCodeInfo.交易成功) {
			bslBsPlanInfo = (BslBsPlanInfo)result.getData();
		}
		JedisClient jedisClient = (JedisClient)SpringContextUtils.getBean("jedisClient");
		try {
			if(bslBsPlanInfo != null) {
				BslProductInfoExample productInfoExample = new BslProductInfoExample();
				Criteria criteria = productInfoExample.createCriteria();
				criteria.andProdOutPlanEqualTo(bslBsPlanInfo.getBsId());
				if(!StringUtils.isBlank(queryExample.getPlanDetailId())){
					criteria.andProdSaleSernoEqualTo(queryExample.getPlanDetailId());
				}
				List<BslProductInfo> prodList = prodService.getProdList(productInfoExample);
				//表头
				List<PDFCell> headList = new ArrayList<>();
				headList.add(new PDFCell("销售出库单详单", CreatePdfUtil.headfont, Element.ALIGN_CENTER, 14, true));
				headList.add(new PDFCell("制单日期："+DateUtil.getFormatText(new Date(),"yyyy-MM-dd HH:mm:ss"), CreatePdfUtil.keyfont, Element.ALIGN_RIGHT, 14, true));
				headList.add(new PDFCell("供应商：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell("湖南巨工机械制造有限公司", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 11, true));
				headList.add(new PDFCell("订单客户：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell(bslBsPlanInfo.getBsCustomer(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 11, true));
				headList.add(new PDFCell("出库单号：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell(!StringUtils.isBlank(queryExample.getPlanDetailId()) ? StringUtil.castToString(bslBsPlanInfo.getBsId())+"/"+queryExample.getPlanDetailId() : StringUtil.castToString(bslBsPlanInfo.getBsId()), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				Date updateDate = bslBsPlanInfo.getUpdDate();
				if(!StringUtils.isBlank(queryExample.getPlanDetailId())) {
					BslSaleInfoDetail bslSaleInfoDetail = saleDetailService.getBslSaleInfoDetailById(queryExample.getPlanDetailId());
					updateDate = bslSaleInfoDetail.getUpdDate();
				}
				headList.add(new PDFCell("出库日期：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell(DateUtil.getFormatText(updateDate,"yyyy-MM-dd"), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				headList.add(new PDFCell("仓   库：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				headList.add(new PDFCell("出库类别：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell(jedisClient.get("bsFlag:"+bslBsPlanInfo.getBsFlag()), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				
				
				String [] fields = {"makeName","prodNorm","prodMaterial","prodLevel","prodNum","unit","planOutputVolume","prodWeight","planFinistDate","collectedUnits","makeProdNorm","remark","note"};
				String [] titles = {"产品编号","产品名称","规格","钢种","质量等级","定尺/米", "记载重量/吨","复磅重量/吨","状态","发货仓库", "炉号","生产批号", "备注"};
				List inList = new ArrayList<>();
				Float prodWeightTotal = 0f;
				for (BslProductInfo bslProductInfo : prodList) {
					inList.add(
							new BslMakePlanInfoDetailExtra(
									bslProductInfo.getProdId(),
									bslProductInfo.getProdName(),
									bslProductInfo.getProdNorm(),
									jedisClient.get("prodMaterial:"+bslProductInfo.getProdMaterial()), 
									jedisClient.get("prodLevel:"+bslProductInfo.getProdLevel()),
									bslProductInfo.getProdLength() == null ? "" : String.valueOf(bslProductInfo.getProdLength()),
									bslProductInfo.getProdRecordWeight()!=null ?String.valueOf(bslProductInfo.getProdRecordWeight()):"",
									bslProductInfo.getProdRelWeight()!=null ?String.valueOf(bslProductInfo.getProdRelWeight()):"",
									jedisClient.get("prodStatus:"+bslProductInfo.getProdStatus()),
									StringUtil.castToString(bslProductInfo.getProdSourceCompany()), 
									StringUtil.castToString(bslProductInfo.getProdLuno()),
									StringUtil.castToString(bslProductInfo.getProdOutPlan()),
									StringUtil.castToString(bslProductInfo.getProdCompany()), 
									StringUtil.castToString(bslProductInfo.getRemark())
									)
							);
					prodWeightTotal += bslProductInfo.getProdRelWeight();
				}
				prodWeightTotal = ((float)Math.round(prodWeightTotal*1000))/1000;
				List<PDFCell> footList = new ArrayList<>();
				footList.add(new PDFCell("合计", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				for (int i = 0; i < 13; i++) {
					if(i == 7){
						footList.add(new PDFCell(StringUtil.castToString(prodWeightTotal), CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
					}else{
						footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
					}
				}
				footList.add(new PDFCell("备   注：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 4, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 10, true));
				footList.add(new PDFCell("收货签名：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT,4, true));
				footList.add(new PDFCell("签收日期：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				footList.add(new PDFCell("第一联：留底联（白色）   第二联：客户联（粉红）    第三联：回单联    第四联：财务联    第五联：门卫联", CreatePdfUtil.keyfont, Element.ALIGN_LEFT, 14, true));
				footList.add(new PDFCell("制单：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell(queryExample.getLoginUserId(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				footList.add(new PDFCell("复核：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				footList.add(new PDFCell("审批：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				String pdfName = "销售出库单详单.pdf";
				String savePath = Thread.currentThread().getContextClassLoader().getResource("pdf/").getPath() + pdfName;
				new CreatePdfUtil().generatePDFs(headList, fields, titles, inList, footList, savePath);
				request.setAttribute("pdfName", pdfName);
				request.setAttribute("pdfname", "销售出库单详单");
				return "pdf";
			}
		}catch (Exception e) {
			e.printStackTrace();
			DictItemOperation.log.error("打印pdf出现异常", e);
		} finally {
			jedisClient.close();
		}
		return "error";
	}

	/**
	 * 原料入库通知单
	 * @param queryExample
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String receipt(QueryExample queryExample, HttpServletRequest request, HttpServletResponse response) {
		BSLResult result = receiptService.getReceiptByBsBsId(queryExample.getPlanId());
		BslBsPlanInfo bslBsPlanInfo = null;
		if(result != null && result.getStatus() == ErrorCodeInfo.交易成功) {
			bslBsPlanInfo = (BslBsPlanInfo)result.getData();
		}
		JedisClient jedisClient = (JedisClient)SpringContextUtils.getBean("jedisClient");
		try {
			if(bslBsPlanInfo != null) {
				BslProductInfoExample productInfoExample = new BslProductInfoExample();
				Criteria criteria = productInfoExample.createCriteria();
				criteria.andProdPlanNoEqualTo(bslBsPlanInfo.getBsId());
				List<BslProductInfo> prodList = prodService.getProdList(productInfoExample);
				//表头
				List<PDFCell> headList = new ArrayList<>();
				String bsFlag = "0".equals(bslBsPlanInfo.getBsFlag())?"购销":"来料";
				headList.add(new PDFCell("原料入库通知单("+bsFlag+")", CreatePdfUtil.headfont, Element.ALIGN_CENTER, 14, true));
				headList.add(new PDFCell("制单日期："+DateUtil.getFormatText(new Date(),"yyyy-MM-dd HH:mm:ss"), CreatePdfUtil.keyfont, Element.ALIGN_RIGHT, 14, true));
				headList.add(new PDFCell("工　　厂：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell("湖南巨工机械制造有限公司", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 5, true));
				headList.add(new PDFCell("通知单号码：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslBsPlanInfo.getBsId(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 5, true));
				headList.add(new PDFCell("总质量：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslBsPlanInfo.getBsRelweight()!=null ? String.valueOf(bslBsPlanInfo.getBsRelweight()):"", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 5, true));
				headList.add(new PDFCell("总卷数：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(String.valueOf(bslBsPlanInfo.getBsAmt()), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 5, true));
				headList.add(new PDFCell("订单号：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslBsPlanInfo.getBsOrderNo(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 5, true));
				headList.add(new PDFCell("供应商：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslBsPlanInfo.getBsCompany(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 5, true));
				headList.add(new PDFCell("运输单位：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslBsPlanInfo.getBsTransport(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 5, true));
				headList.add(new PDFCell("运输车号：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslBsPlanInfo.getBsCarno(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 5, true));
				headList.add(new PDFCell("提货方式：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(jedisClient.get("bsGettype:"+bslBsPlanInfo.getBsGettype()), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 5, true));
				headList.add(new PDFCell("制单日期：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(DateUtil.getFormatText(bslBsPlanInfo.getCrtDate(),"yyyy-MM-dd HH:mm:ss"), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 5, true));
				headList.add(new PDFCell("备   注：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslBsPlanInfo.getRemark(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 12, true));
				
				String [] fields = {"makeName","prodNorm","prodMaterial","prodLevel","prodNum","unit","planOutputVolume","prodWeight","planFinistDate","collectedUnits","makeProdNorm","remark"};
				String [] titles = {"原料编号","原料名称","炉号","规格","钢种","记载重量/吨","复磅重量/吨","入库重量/吨","状态","质量等级","发货仓库", "厂家", "备注"};
				List inList = new ArrayList<>();
				for (BslProductInfo bslProductInfo : prodList) {
					inList.add(
							new BslMakePlanInfoDetailExtra(
									bslProductInfo.getProdId(),
									bslProductInfo.getProdName(), 
									bslProductInfo.getProdLuno(),
									bslProductInfo.getProdNorm(),
									jedisClient.get("prodMaterial:"+bslProductInfo.getProdMaterial()), 
									bslProductInfo.getProdRecordWeight()!=null ?String.valueOf(bslProductInfo.getProdRecordWeight()):"",
									bslProductInfo.getProdRelWeight()!=null ?String.valueOf(bslProductInfo.getProdRelWeight()):"",
									bslProductInfo.getProdPrintWeight()!=null ?String.valueOf(bslProductInfo.getProdPrintWeight()):"",
									jedisClient.get("prodStatus:"+bslProductInfo.getProdStatus()),
									jedisClient.get("prodLevel:"+bslProductInfo.getProdLevel()),
									bslProductInfo.getProdSourceCompany(), 
									bslProductInfo.getProdCompany(), 
									bslProductInfo.getRemark()
									)
							);
				}
				
				List<PDFCell> footList = new ArrayList<>();
				footList.add(new PDFCell("第一联：上料（白色）第二联：飞锯（粉红）第三联：司磅（淡蓝） 第四联： 物流（淡黄）", CreatePdfUtil.keyfont, Element.ALIGN_LEFT, 14, true));
				footList.add(new PDFCell("制单：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell(queryExample.getLoginUserId(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				footList.add(new PDFCell("复核：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				footList.add(new PDFCell("审批：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				String pdfName = "原料入库通知单.pdf";
				String savePath = Thread.currentThread().getContextClassLoader().getResource("pdf/").getPath() + pdfName;
				new CreatePdfUtil().generatePDFs(headList, fields, titles, inList, footList, savePath);
				request.setAttribute("pdfName", pdfName);
				request.setAttribute("pdfname", "纵剪带生产调度单");
				return "pdf";
			}
		}catch (Exception e) {
			e.printStackTrace();
			DictItemOperation.log.error("打印pdf出现异常", e);
		} finally {
			jedisClient.close();
		}
		return "error";
	}

	/**
	 * 产成品生产调度单
	 * @param example
	 * @param response
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String finishedProd(QueryExample queryExample, HttpServletRequest request, HttpServletResponse response) {
		BslMakePlanInfo bslMakePlanInfo = makePlanService.getMakePlanInfoById(queryExample.getPlanId());
		List<BslMakePlanInfoDetail> list = prodPlanService.getProdPlanInfoDetail(queryExample.getPlanId());
		JedisClient jedisClient = (JedisClient)SpringContextUtils.getBean("jedisClient");
		String errorMsg = "";
		try {
			if(bslMakePlanInfo != null) {
				//表头
				List<PDFCell> headList = new ArrayList<>();
				String makeType = "0".equals(bslMakePlanInfo.getMakeType())?"购销":"来料";
				headList.add(new PDFCell("产成品生产调度单("+makeType+")", CreatePdfUtil.headfont, Element.ALIGN_CENTER, 12, true));
				headList.add(new PDFCell("制单日期："+DateUtil.getFormatText(new Date(),"yyyy-MM-dd HH:mm:ss"), CreatePdfUtil.keyfont, Element.ALIGN_RIGHT, 12, true));
				headList.add(new PDFCell("工　　厂：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell("湖南巨工机械制造有限公司", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				headList.add(new PDFCell("生产机组：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(jedisClient.get("planDepartment:"+bslMakePlanInfo.getPlanDepartment()), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				headList.add(new PDFCell("原料规格：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslMakePlanInfo.getProdNorm(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				headList.add(new PDFCell("原料炉号：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				headList.add(new PDFCell("钢   种：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(jedisClient.get("prodMaterial:"+bslMakePlanInfo.getProdMaterial()), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				headList.add(new PDFCell("生产批号：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslMakePlanInfo.getPlanId(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				headList.add(new PDFCell("钢   厂：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslMakePlanInfo.getCompany(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				headList.add(new PDFCell("来料客户：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslMakePlanInfo.getCustomer(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				headList.add(new PDFCell("用料数量：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslMakePlanInfo.getProdNum(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				headList.add(new PDFCell("用料重量：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslMakePlanInfo.getProdWeight(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				headList.add(new PDFCell("销售订单号：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslMakePlanInfo.getProdOrder(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));;
				headList.add(new PDFCell("备   注：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslMakePlanInfo.getRemark(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				
				String [] fields = {"makeName","prodNorm","prodMaterial","prodLevel","prodNum","unit","planOutputVolume","prodWeight","planFinistDate","collectedUnits","remark"};
				String [] titles = {"产品名称","规格","钢种","质量等级","条数","单位","计划产出量","销售订单号","计划完工日期","短溢装","备注"};
				List inList = new ArrayList<>();
				if(list != null && list.size() > 0) {
					for (BslMakePlanInfoDetail makePlanInfoDetail : list) {
						inList.add(
								new BslMakePlanInfoDetailExtra(
										bslMakePlanInfo.getMakeName(),
										makePlanInfoDetail.getProdNorm(), 
										jedisClient.get("prodMaterial:"+bslMakePlanInfo.getProdMaterial()), 
										jedisClient.get("prodLevel:"+makePlanInfoDetail.getProdLevel()),
										makePlanInfoDetail.getProdNum() == null ? "" : String.valueOf(makePlanInfoDetail.getProdNum())+"支",
										"吨",
										makePlanInfoDetail.getPlanOutputVolume()+"吨",
										bslMakePlanInfo.getProdOrder(),
										DateUtil.getFormatText(makePlanInfoDetail.getPlanFinistDate(),"yyyy-MM-dd"),
										makePlanInfoDetail.getPlanDyz(),//短溢装
										bslMakePlanInfo.getMakeProdNorm(), 
										makePlanInfoDetail.getRemark()
										)
								);
					}
				}
				
				List<PDFCell> footList = new ArrayList<>();
				footList.add(new PDFCell("第一联：上料（白色）第二联：飞锯（粉红）第三联：司磅（淡蓝） 第四联： 物流（淡黄）", CreatePdfUtil.keyfont, Element.ALIGN_LEFT, 12, true));
				footList.add(new PDFCell("制单：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell(queryExample.getLoginUserId(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				footList.add(new PDFCell("复核：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				footList.add(new PDFCell("审批：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				String pdfName = "产成品生产调度单.pdf";
				String savePath = Thread.currentThread().getContextClassLoader().getResource("pdf/").getPath() + pdfName;
				new CreatePdfUtil().generatePDFs(headList, fields, titles, inList, footList, savePath);
				request.setAttribute("pdfName", pdfName);
				request.setAttribute("pdfname", "产成品生产调度单");//pdf在线预览页Title
				return "pdf";
			}
		}catch (Exception e) {
			e.printStackTrace();
			errorMsg = e.getMessage();
			DictItemOperation.log.error("打印pdf出现异常", e);
		} finally {
			jedisClient.close();
		}
		request.setAttribute("errorMsg", errorMsg);
		return "error";
	}

	/**
	 * 纵剪带生产调度单
	 * 导出pdf
	 * @param example
	 * @param response
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String semiFinishProd(QueryExample queryExample, HttpServletRequest request, HttpServletResponse response) {
		BslMakePlanInfo bslMakePlanInfo = makePlanService.getMakePlanInfoById(queryExample.getPlanId());
		BSLResult bslResult = makePlanDetailService.queryMakePlanInfoDetailList(queryExample);
		JedisClient jedisClient = (JedisClient)SpringContextUtils.getBean("jedisClient");
		try {
			if(bslMakePlanInfo != null) {
				//表头
				List<PDFCell> headList = new ArrayList<>();
				String makeType = "0".equals(bslMakePlanInfo.getMakeType())?"自用":"外销";
				headList.add(new PDFCell("纵剪带生产调度单("+makeType+")", CreatePdfUtil.headfont, Element.ALIGN_CENTER, 13, true));
				headList.add(new PDFCell("制单日期："+DateUtil.getFormatText(new Date(),"yyyy-MM-dd HH:mm:ss"), CreatePdfUtil.keyfont, Element.ALIGN_RIGHT, 13, true));
				headList.add(new PDFCell("工　　厂：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell("湖南巨工机械制造有限公司", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				headList.add(new PDFCell("生产机组：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(jedisClient.get("planDepartment:"+bslMakePlanInfo.getPlanDepartment()), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 5, true));
				headList.add(new PDFCell("原料规格：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslMakePlanInfo.getProdNorm(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				headList.add(new PDFCell("原料炉号：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 5, true));
				headList.add(new PDFCell("钢   种：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(jedisClient.get("prodMaterial:"+bslMakePlanInfo.getProdMaterial()), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				headList.add(new PDFCell("生产批号：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslMakePlanInfo.getPlanId(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 5, true));
				headList.add(new PDFCell("钢   厂：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslMakePlanInfo.getCompany(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				headList.add(new PDFCell("来料客户：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslMakePlanInfo.getCustomer(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 5, true));
				headList.add(new PDFCell("用料数量：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslMakePlanInfo.getProdNum(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				headList.add(new PDFCell("用料重量：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslMakePlanInfo.getProdWeight(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 5, true));
				headList.add(new PDFCell("销售订单号：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslMakePlanInfo.getProdOrder(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));;
				headList.add(new PDFCell("备   注：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(bslMakePlanInfo.getRemark(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 5, true));;
				
				String [] fields = {"makeName","prodNorm","prodMaterial","prodLevel","prodNum","unit","planOutputVolume","prodWeight","planFinistDate","collectedUnits","makeProdNorm","remark"};
				String [] titles = {"产品名称","规格","钢种","质量等级","条数","单位","计划产出量","实际重量","计划完工日期","实收机组","产成品规格","备注"};
				List inList = new ArrayList<>();
				if(bslResult != null && bslResult.getStatus() == ErrorCodeInfo.交易成功) {
					List<BslMakePlanInfoDetail> list = (List<BslMakePlanInfoDetail>) bslResult.getData();
					for (BslMakePlanInfoDetail makePlanInfoDetail : list) {
						inList.add(
								new BslMakePlanInfoDetailExtra(
										bslMakePlanInfo.getMakeName(),
										makePlanInfoDetail.getProdNorm(), 
										jedisClient.get("prodMaterial:"+bslMakePlanInfo.getProdMaterial()), 
										jedisClient.get("prodLevel:"+makePlanInfoDetail.getProdLevel()),
										makePlanInfoDetail.getProdNum() == null ? "" : String.valueOf(makePlanInfoDetail.getProdNum())+"支",
										"吨",
										makePlanInfoDetail.getPlanOutputVolume()!=null ? makePlanInfoDetail.getPlanOutputVolume()+"吨" : "",
										makePlanInfoDetail.getProdWeight()!=null ?String.valueOf(makePlanInfoDetail.getProdWeight())+"吨" : "",
										DateUtil.getFormatText(makePlanInfoDetail.getPlanFinistDate(),"yyyy-MM-dd"),
										jedisClient.get("collectedUnits:"+makePlanInfoDetail.getCollectedUnits()), 
										bslMakePlanInfo.getMakeProdNorm(), 
										makePlanInfoDetail.getRemark()
										)
								);
					}
				}
				
				List<PDFCell> footList = new ArrayList<>();
				footList.add(new PDFCell("第一联：计划部留底（白色）       第二联：机组生产（粉红）", CreatePdfUtil.keyfont, Element.ALIGN_LEFT, 13, true));
				footList.add(new PDFCell("制单：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell(queryExample.getLoginUserId(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 5, true));
				footList.add(new PDFCell("复核：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 6, true));
				String pdfName = "纵剪带生产调度单.pdf";
				String savePath = Thread.currentThread().getContextClassLoader().getResource("pdf/").getPath() + pdfName;
				new CreatePdfUtil().generatePDFs(headList, fields, titles, inList, footList, savePath);
				request.setAttribute("pdfName", pdfName);
				request.setAttribute("pdfname", "纵剪带生产调度单");
				return "pdf";
			}
		}catch (Exception e) {
			e.printStackTrace();
			DictItemOperation.log.error("打印pdf出现异常", e);
		} finally {
			jedisClient.close();
		}
		return "error";
	}
	
	/**
	 * 销售出库通知单补打
	 * @param example
	 * @param request
	 * @param response
	 * @return
	 */
	private String saleProdByCarIds(QueryExample queryExample, HttpServletRequest request, HttpServletResponse response) {
		BSLResult result = receiptService.getReceiptByBsBsId(queryExample.getPlanId());
		String prodOutCarno = queryExample.getProdOutCarno();
		
		//获取该车次的产品信息
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdOutCarnoEqualTo(prodOutCarno);
		List<BslProductInfo> productInfos = prodService.getProdList(bslProductInfoExample);
		//获取产品编号
		List<String> prodIdsList = new ArrayList<String>();
		if(productInfos!= null && productInfos.size()>0){
			for (BslProductInfo productInfo : productInfos) {
				prodIdsList.add(productInfo.getProdId());
			}
			queryExample.setProdIdsList(prodIdsList);
		}
		
		JedisClient jedisClient = (JedisClient)SpringContextUtils.getBean("jedisClient");
		//获取出库仓库
		String prodRuc = queryExample.getProdBc();
		
		BslBsPlanInfo bslBsPlanInfo = null;
		if(result != null && result.getStatus() == ErrorCodeInfo.交易成功) {
			bslBsPlanInfo = (BslBsPlanInfo)result.getData();
		}
		try {
			if(bslBsPlanInfo != null) {
				List<BslProductInfoCollect> bslSaleInfoDetails = prodService.querySaleOutByProds(queryExample);
				if(prodIdsList == null || prodIdsList.size()<=0){
					bslSaleInfoDetails = new ArrayList<BslProductInfoCollect>();
				}
				//表头
				List<PDFCell> headList = new ArrayList<>();
				headList.add(new PDFCell("销售出库单", CreatePdfUtil.headfont, Element.ALIGN_CENTER, 11, true));
				headList.add(new PDFCell("制单日期："+DateUtil.getFormatText(new Date(),"yyyy-MM-dd HH:mm:ss"), CreatePdfUtil.keyfont, Element.ALIGN_RIGHT, 11, true));
				headList.add(new PDFCell("供应商：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell("湖南巨工机械制造有限公司", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 8, true));
				headList.add(new PDFCell("订单客户：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell(bslBsPlanInfo.getBsCustomer(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				headList.add(new PDFCell("出库车次：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(prodOutCarno, CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				headList.add(new PDFCell("出库单号：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(!StringUtils.isBlank(queryExample.getPlanDetailId()) ? StringUtil.castToString(bslBsPlanInfo.getBsId())+"/"+queryExample.getPlanDetailId() : StringUtil.castToString(bslBsPlanInfo.getBsId()), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				Date updateDate = bslBsPlanInfo.getUpdDate();
				if(!StringUtils.isBlank(queryExample.getPlanDetailId())) {
					BslSaleInfoDetail bslSaleInfoDetail = saleDetailService.getBslSaleInfoDetailById(queryExample.getPlanDetailId());
					updateDate = bslSaleInfoDetail.getUpdDate();
				}
				headList.add(new PDFCell("出库日期：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell(DateUtil.getFormatText(updateDate,"yyyy-MM-dd"), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				headList.add(new PDFCell("出库仓库：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, true));
				headList.add(new PDFCell(prodRuc, CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				headList.add(new PDFCell("出库类别：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				headList.add(new PDFCell(jedisClient.get("bsFlagS:"+bslBsPlanInfo.getBsFlag()), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				
				String [] fields = {"makeName","prodNorm","prodOrirawid","prodMaterial","prodLevel","prodNum","unit","planOutputVolume","prodWeight","planFinistDate"};
				String [] titles = {"生产批号","炉号","厂家卷号","物料名称","规格", "钢种", "定尺(米)","数量(卷/支)", "重量(吨)","质量等级"};
				List inList = new ArrayList<>();
				
				Integer prodNumSum = 0;
				Float prodWeightTotal = 0f;
				for (BslProductInfoCollect bslProductInfo : bslSaleInfoDetails) {
					inList.add(
							new BslMakePlanInfoDetailExtra(
									bslProductInfo.getProdPlanNo(),
									bslProductInfo.getProdLuno(),
									bslProductInfo.getProdOrirawid(),
									bslProductInfo.getProdName(),
									bslProductInfo.getProdNorm(),
									jedisClient.get("prodMaterial:"+bslProductInfo.getProdMaterial()),
									StringUtil.castToString(bslProductInfo.getProdLength()),
									StringUtil.castToString(bslProductInfo.getProdNumCount()),
									StringUtil.castToString(bslProductInfo.getProdWeightTotal()),
									jedisClient.get("prodLevel:"+bslProductInfo.getProdLevel())
									)
							);
					prodNumSum += bslProductInfo.getProdNumCount();
					prodWeightTotal += bslProductInfo.getProdWeightTotal();
				}
				prodWeightTotal = ((float)Math.round(prodWeightTotal*1000))/1000;
				List<PDFCell> footList = new ArrayList<>();
				footList.add(new PDFCell("合计", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				if(prodNumSum == 0 && prodWeightTotal == 0f) {
					for (int i = 0; i < 10; i++) {
						footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
					}
				} else {
					for (int i = 0; i < 7; i++) {
						footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
					}
					footList.add(new PDFCell(StringUtil.castToString(prodNumSum), CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
					footList.add(new PDFCell(StringUtil.castToString(prodWeightTotal), CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
					footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_CENTER, 1, true));
				}
				footList.add(new PDFCell("备   注：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 8, true));
				footList.add(new PDFCell("收货签名：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 3, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				footList.add(new PDFCell("签收日期：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 2, false));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 3, true));
				footList.add(new PDFCell("第一联：留底联（白色）   第二联：客户联（粉红）    第三联：回单联    第四联：财务联    第五联：门卫联", CreatePdfUtil.keyfont, Element.ALIGN_LEFT, 11, true));
				footList.add(new PDFCell("制单：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell(queryExample.getLoginUserId(), CreatePdfUtil.textfont, Element.ALIGN_LEFT, 2, true));
				footList.add(new PDFCell("复核：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 2, true));
				footList.add(new PDFCell("审批：", CreatePdfUtil.keyfont, Element.ALIGN_CENTER, 1, true));
				footList.add(new PDFCell("", CreatePdfUtil.textfont, Element.ALIGN_LEFT, 4, true));
				String pdfName = "销售调度通知单.pdf";
				String savePath = Thread.currentThread().getContextClassLoader().getResource("pdf/").getPath() + pdfName;
				new CreatePdfUtil().generatePDFs(headList, fields, titles, inList, footList, savePath);
				request.setAttribute("pdfName", pdfName);
				request.setAttribute("pdfname", "销售调度通知单");
				return "pdf";
			}
		}catch (Exception e) {
			e.printStackTrace();
			DictItemOperation.log.error("打印pdf出现异常", e);
		} finally {
			jedisClient.close();
		}
		return "error";
	}
	
	
}
