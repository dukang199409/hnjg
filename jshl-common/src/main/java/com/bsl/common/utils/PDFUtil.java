package com.bsl.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * 生成pdf工具
 * @author huangyl
 * @date 2019年5月2日  
 *
 */
public class PDFUtil {

	// 利用模板生成pdf
	@SuppressWarnings("unchecked")
	public static void interviewReportPDF(Map<String, Object> map) {

		// 模板路径
		String templatePath = String.valueOf(map.get("templatePath"));// UtilPath.getWEB_INF() + "pdf/面试报告模板.pdf";
		// 生成的新文件路径
		String newPDFPath = String.valueOf(map.get("newPDFPath"));// +map.get("phone")FileNameUtil.newFileName()
		String fontPath = String.valueOf(map.get("fontPath"));// +map.get("phone")FileNameUtil.newFileName()
		PdfReader reader;
		FileOutputStream out;
		ByteArrayOutputStream bos;
		PdfStamper stamper;
		try {
			out = new FileOutputStream(newPDFPath);// 输出流
			reader = new PdfReader(templatePath);// 读取pdf模板
			bos = new ByteArrayOutputStream();
			stamper = new PdfStamper(reader, bos);
			AcroFields form = stamper.getAcroFields();
			// 给表单添加中文字体 这里采用系统字体。不设置的话，中文可能无法显示
			BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			form.addSubstitutionFont(bf);

			Map<String, Object> qrcodeFields = (Map<String, Object>) map.get("qrcodeFields");
			// 遍历二维码字段
			for (Map.Entry<String, Object> entry : qrcodeFields.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				// 获取属性的类型
				if (value != null && form.getField(key) != null) {
					// 获取位置(左上右下)
//					FieldPosition fieldPosition = form.getFieldPositions(key).get(0);
					// 绘制二维码
					Rectangle signRect = form.getFieldPositions(key).get(0).position;
					float x = signRect.getLeft();
					float y = signRect.getBottom();
					float width = signRect.getWidth() > signRect.getHeight() ? signRect.getHeight()
							: signRect.getWidth();
//					BarcodeQRCode pdf417 = new BarcodeQRCode(value.toString(), (int) width, (int) width, null);
					// 生成二维码图像
					Image image128 = Image.getInstance(value.toString());
					int pageNo = form.getFieldPositions(key).get(0).page;
					// 绘制在第一页
					PdfContentByte cb = stamper.getOverContent(pageNo);
					
					image128.scaleToFit(width, width);
					// 左边距(居中处理)
//					float marginLeft = (fieldPosition.position.getRight() - fieldPosition.position.getLeft()
//							- image128.getWidth()) / 2;
					// 条码位置
					image128.setAbsolutePosition(x, y);
					// 加入条码
					cb.addImage(image128);
				}
			}
			// 遍历map装入数据
			Map<String, Object> dataFields = (Map<String, Object>) map.get("dataFields");
			for (Entry<String, Object> entry : dataFields.entrySet()) {
				form.setField(entry.getKey(), entry.getValue() == null ? "" : entry.getValue().toString());
			}
			stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
			stamper.close();
			Document doc = new Document();
			PdfCopy copy = new PdfCopy(doc, out);
			doc.open();
			PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
			copy.addPage(importPage);
			doc.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// //测试
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> imgmap = new HashMap<String, Object>();
		imgmap.put("qrcode", "/Users/huangyl/Desktop/qrcode.png");
		map.put("qrcodeFields", imgmap);
		Map<String, Object> map1 = new HashMap<>();
		map1.put("prodName", "123");
		map.put("dataFields", map1);
		map.put("templatePath", "/Users/huangyl/Desktop/Share/物料入库标签.pdf");
		map.put("newPDFPath", "/Users/huangyl/Desktop/123.pdf");
		map.put("fontPath", "/Users/huangyl/Desktop/fonts/simsun.ttc,0");
		System.out.println(map);
		interviewReportPDF(map);
	}
}
