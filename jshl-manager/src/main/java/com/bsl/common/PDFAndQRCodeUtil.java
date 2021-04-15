package com.bsl.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.bsl.common.pojo.BSLException;
import com.bsl.common.utils.FileUtil;
import com.bsl.common.utils.PDFUtil;
import com.bsl.common.utils.QRCodeUtil;
import com.bsl.select.ErrorCodeInfo;
/**
 * 生成内含二维码的pdf文件
 * @author huangyl
 * @date 2019年5月3日  
 *
 */
public class PDFAndQRCodeUtil {
	
	private static Logger log = Logger.getLogger(PDFAndQRCodeUtil.class.getClass());
	
	public static void createQRCodeToPDFFile(Map<String,Object> paramMap,Map<String,String> fileNameMap) {
		log.info("==========createQRCodeToPDFFile========begin");
//		OutputStream outp = null;
//		BufferedInputStream bins = null;
//		FileInputStream in = null;
//		BufferedOutputStream bous = null;
		try {
			Map<String,Object> inMap = new HashMap<>();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			//1.生成二维码
			String qrCodePath = classLoader.getResource("pdf/").getPath() + fileNameMap.get("qrCodeName");
			String templatePath = classLoader.getResource("pdf/").getPath() + fileNameMap.get("pdfTemplateName");
			String newPDFPath = classLoader.getResource("pdf/").getPath() + fileNameMap.get("newPDFName");
			String fontPath = classLoader.getResource("fonts/").getPath()+"simsun.ttc,0";
			String qrData = fileNameMap.get("qrData");
			log.info("==========生成二维码-createQRCode========"+qrData);
			QRCodeUtil.createQRCode(qrCodePath, qrData);//"/Users/huangyl/Desktop/qrcode.png"
			
			//2.生成pdf文件
			Map<String, Object> imgmap = new HashMap<String, Object>();
			imgmap.put("qrcode", qrCodePath);
			inMap.put("dataFields", paramMap);
			inMap.put("qrcodeFields", imgmap);
			inMap.put("templatePath", templatePath);
			inMap.put("newPDFPath", newPDFPath);
			inMap.put("fontPath", fontPath);
			System.out.println(inMap);
			//创建pdf文件
			log.info("==========创建pdf文件-interviewReportPDF========"+inMap);
			PDFUtil.interviewReportPDF(inMap);
			
//			String newPDFName = fileNameMap.get("newPDFName");
//			newPDFName = new String(newPDFName.getBytes("UTF-8"),"ISO-8859-1");
//			in= new FileInputStream(new File(newPDFPath));
//			bins = new BufferedInputStream(in);
//
//			byte[] b = new byte[bins.available()];
//			bins.read(b);
//			bins.close();
//			response.reset();//非常重要
//			response.setContentType("application/x-msdownload");
//			response.addHeader("Content-Disposition", "attachment;filename=" + newPDFName);
//			response.addHeader("Content-Length", "" + new File(newPDFPath).length());
//			outp = response.getOutputStream();
//			bous = new BufferedOutputStream(outp);
//			bous.write(b);
//			bous.flush();
//			bous.close();
			//3.删除二维码文件和pdf文件
			FileUtil.delectFile(qrCodePath);
			log.info("==========createQRCodeToPDFFile========end");
//			FileUtil.delectFile(newPDFPath);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("生成pdf文件出现异常", e);
			throw new BSLException(ErrorCodeInfo.错误类型_交易异常,"生成pdf文件出现异常");
		} finally {
			
		}
	}
}
