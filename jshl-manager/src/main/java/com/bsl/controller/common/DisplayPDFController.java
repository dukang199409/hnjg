package com.bsl.controller.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsl.common.utils.FileUtil;

/**
 * 在线预览pdf
 * @Auothor wzx
 * @Date 2016/12/18 0018
 */
@Controller
public class DisplayPDFController {

    @RequestMapping("/displayPDF/{pdfName}")
    public void displayPDF(HttpServletResponse response,HttpServletRequest request,@PathVariable String pdfName) {
        try {
//        	pdfName=new String(pdfName.getBytes("ISO-8859-1"),"UTF-8") + ".pdf";
        	pdfName=pdfName + ".pdf";
        	String pdfFilePath = Thread.currentThread().getContextClassLoader().getResource("pdf/").getPath() + pdfName;
            File file = new File(pdfFilePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            response.reset();//非常重要
            response.setContentType("multipart/form-data");
            OutputStream outputStream = response.getOutputStream();
            IOUtils.write(IOUtils.toByteArray(fileInputStream), outputStream);
			FileUtil.delectFile(pdfFilePath);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}