package com.bsl.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.bsl.common.pojo.PDFCell;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 生成pdf
 * 
 */
public class CreatePdfUtil{
    Document document = new Document();// 建立一个Document对象

    public static Font headfont;// 设置字体大小
    public static Font keyfont;// 设置字体大小
    public static Font keyfont1;// 设置字体大小
    public static Font textfont;// 设置字体大小
    public static Font textfont2;// 设置字体大小
    public static Font keyfont2;// 设置字体大小

    static{
        //中文格式
        BaseFont bfChinese;
        try{
        	String fontPath = CreatePdfUtil.class.getClassLoader().getResource("fonts/").getPath()+"msyhbd.ttc,0";//simsun.ttc
            // 设置中文显示
            bfChinese = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H,//UtilPath.getRootPath() +
					BaseFont.EMBEDDED);
            headfont = new Font(bfChinese, 12, Font.BOLD);// 设置字体大小
            keyfont = new Font(bfChinese, 10, Font.BOLD);// 设置字体大小
            keyfont1 = new Font(bfChinese, 8, Font.BOLD);// 设置字体大小
            keyfont2 = new Font(bfChinese, 8, Font.BOLD);// 设置字体大小
            textfont = new Font(bfChinese, 10, Font.BOLD);// 设置字体大小
            textfont2 = new Font(bfChinese, 8, Font.NORMAL);// 设置A3字体大小
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 文成文件
     * @param file 待生成的文件名
     */
    public CreatePdfUtil(File file){
        document.setPageSize(PageSize.A4);// 设置页面大小
        try{
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 文成文件
     * @param file 待生成的文件名
     */
    public CreatePdfUtil(File file,int flag){
    	if(flag == 1){
            document.setPageSize(PageSize.A3);// 设置页面大小
    	}else{
    		document.setPageSize(PageSize.A4);// 设置页面大小
    	}
        try{
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public CreatePdfUtil(){
        
    }
    
    public void initFile(File file){
        document.setPageSize(PageSize.A4);// 设置页面大小
        try{
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    

    int maxWidth = 520;
    
    /**
     * 为表格添加一个内容
     * @param value           值
     * @param font            字体
     * @param align            对齐方式
     * @return                添加的文本框
     */
    public PdfPCell createCell(String value, Font font, int align){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }
    
    /**
     * 为表格添加一个内容
     * @param value           值
     * @param font            字体
     * @return                添加的文本框
     */
    public PdfPCell createCell(String value, Font font){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }

    /**
     * 为表格添加一个内容
     * @param value           值
     * @param font            字体
     * @param align            对齐方式
     * @param colspan        占多少列
     * @return                添加的文本框
     */
    public PdfPCell createCell(String value, Font font, int align, int colspan){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }
    
    /**
     * 为表格添加一个内容
     * @param value           值
     * @param font            字体
     * @param align            对齐方式
     * @param colspan        占多少列
     * @param boderFlag        是否有有边框
     * @return                添加的文本框
     */
    public PdfPCell createCell(String value, Font font, int align, int colspan,
            boolean boderFlag){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value, font));
        cell.setPadding(3.0f);
        if (!boderFlag){
            cell.setBorder(0);
            cell.setPaddingTop(15.0f);
            cell.setPaddingBottom(8.0f);
        }
        return cell;
    }

    /**
     * 创建一个表格对象
     * @param colNumber  表格的列数
     * @return              生成的表格对象
     */
    public PdfPTable createTable(int colNumber){
        PdfPTable table = new PdfPTable(colNumber);
        try{
            table.setTotalWidth(maxWidth);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return table;
    }
    
    /**
     * 创建一个表格对象 A3
     * @param colNumber  表格的列数
     * @return              生成的表格对象
     */
    public PdfPTable createTable2(int colNumber){
        PdfPTable table = new PdfPTable(colNumber);
        try{
            table.setTotalWidth(760);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return table;
    }

    public PdfPTable createTable(float[] widths){
        PdfPTable table = new PdfPTable(widths);
        try{
            table.setTotalWidth(maxWidth);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return table;
    }

    public PdfPTable createBlankTable(){
        PdfPTable table = new PdfPTable(1);
        table.getDefaultCell().setBorder(0);
        table.addCell(createCell("", keyfont));
        table.setSpacingAfter(20.0f);
        table.setSpacingBefore(20.0f);
        return table;
    }

    public void HZ(PdfPTable table, int colNum) {
    	// 添加备注,靠左，不显示边框
        table.addCell(createCell("纵剪带生产调度单（自用/来料加工/外销三项选一）", keyfont, Element.ALIGN_CENTER, colNum,true));
        table.addCell(createCell("工厂：", keyfont, Element.ALIGN_CENTER, 2,true));
        table.addCell(createCell("湖南宝顺联冷弯科技有限公司", keyfont, Element.ALIGN_CENTER, 2,true));
        table.addCell(createCell("生产机组：", keyfont, Element.ALIGN_CENTER, 2,true));
        table.addCell(createCell(" ", keyfont, Element.ALIGN_CENTER, 1,true));
        
        table.addCell(createCell("纵剪带规格：", keyfont, Element.ALIGN_CENTER, 2,true));
        table.addCell(createCell("8*1500*C", keyfont, Element.ALIGN_CENTER, 2,true));
        table.addCell(createCell("卷数：", keyfont, Element.ALIGN_CENTER, 2,true));
        table.addCell(createCell("Q345B、12卷", keyfont, Element.ALIGN_CENTER, 1,true));
        
        table.addCell(createCell("钢   种：", keyfont, Element.ALIGN_CENTER, 2,true));
        table.addCell(createCell(" ", keyfont, Element.ALIGN_CENTER, 2,true));
        table.addCell(createCell("生产批号：", keyfont, Element.ALIGN_CENTER, 2,true));
        table.addCell(createCell("自动生产", keyfont, Element.ALIGN_CENTER, 1,true));
        
        table.addCell(createCell("钢    厂：", keyfont, Element.ALIGN_CENTER, 2,true));
        table.addCell(createCell(" ", keyfont, Element.ALIGN_CENTER, 2,true));
        table.addCell(createCell("原料炉号：", keyfont, Element.ALIGN_CENTER, 2,true));
        table.addCell(createCell("自选", keyfont, Element.ALIGN_CENTER, 1,true));
        
        table.addCell(createCell("来料客户：", keyfont, Element.ALIGN_CENTER, 2,true));
        table.addCell(createCell("湖南中联重科建筑起重机械有限责任公司（+手输入）", keyfont, Element.ALIGN_CENTER, 2,true));
        table.addCell(createCell("销售订单：", keyfont, Element.ALIGN_CENTER, 2,true));
        table.addCell(createCell(" ", keyfont, Element.ALIGN_CENTER, 1,true));
        
        table.addCell(createCell("备   注：", keyfont, Element.ALIGN_CENTER, 2,true));
        table.addCell(createCell(" ", keyfont, Element.ALIGN_CENTER, colNum-2,true));
    }
    
    public void footPDF(PdfPTable table, int colNum) {
    	table.addCell(createCell("第一联：上料（白色）第二联：飞锯（粉红）第三联：司磅（淡蓝） 第四联： 物流（淡黄）", keyfont, Element.ALIGN_LEFT, colNum,true));
    	 table.addCell(createCell("制单：", keyfont, Element.ALIGN_CENTER, 1,true));
         table.addCell(createCell(" ", keyfont, Element.ALIGN_CENTER, 1,true));
         table.addCell(createCell("复核:", keyfont, Element.ALIGN_CENTER, 1,true));
         table.addCell(createCell(" ", keyfont, Element.ALIGN_CENTER, 1,true));
         table.addCell(createCell("审批:", keyfont, Element.ALIGN_CENTER, 1,true));
         table.addCell(createCell(" ", keyfont, Element.ALIGN_CENTER, 2,true));
    }
    
    /**
     * 生成pdf
     * @param headList 单条总记录
     * @param fields 英文表格字段名
     * @param titles 中文表格字段名
     * @param list 表格数据
     * @param colNum 总列数
     * @param footList 底部数据
     */
    public <T> void generatePDF(List<PDFCell> headList,String[] fields,String[] titles,List<T> list,int colNum,List<PDFCell> footList) {
        
        // 创建一个只有5列的表格
        PdfPTable table = createTable(colNum);
        for (PDFCell pdfCell : headList) {
        	table.addCell(createCell(pdfCell.getValue(), pdfCell.getFont(), pdfCell.getAlign(),pdfCell.getColspan(),pdfCell.isBoderFlag()));
		}
//        // 添加备注,靠左，不显示边框
//        table.addCell(createCell("中联产品范围规格表", keyfont, Element.ALIGN_LEFT, colNum,false));
        
        table.addCell(createCell("行号", keyfont, Element.ALIGN_CENTER));
        //设置表头
        for(int i = 1 ; i < colNum ; i++){
            table.addCell(createCell(titles[i-1], keyfont, Element.ALIGN_CENTER));
        }
        if(null != list && list.size() > 0){
            int size = list.size();
            for(int i = 0 ; i < size ; i++){
                T t = list.get(i);
                table.addCell(createCell(String.valueOf(i+1), textfont));
                for(int j = 1 ; j < colNum ; j ++){
                    //获得首字母
                    String firstLetter = fields[j-1].substring(0,1).toUpperCase(); 
                    
                    //获得get方法,getName,getAge等
                    String getMethodName = "get" + firstLetter + fields[j-1].substring(1);
                   
                    Method method;
                    Class classType = list.get(0).getClass();
                    try{
                        //通过反射获得相应的get方法，用于获得相应的属性值
                        method = classType.getMethod(getMethodName, new Class[]{});
                        try{
                             System.out.print(getMethodName +":" + method.invoke(t, new Class[]{}) +",");
                             //添加数据
                             Object obj = method.invoke(t, new Class[]{});
                             table.addCell(createCell(obj != null ? obj.toString() : "", textfont));
                        }catch (IllegalArgumentException e){
                            e.printStackTrace();
                        }catch (IllegalAccessException e){
                            e.printStackTrace();
                        }catch (InvocationTargetException e){
                            e.printStackTrace();
                        }  
                    }catch (SecurityException e){
                        e.printStackTrace();
                    }catch (NoSuchMethodException e){
                        e.printStackTrace();
                    }
                }
                
                System.out.println("");
            }
        } else {
        	//没有明细时，添加一行空白行
        	for(int j = 0 ; j < colNum ; j ++){
        		table.addCell(createCell("", textfont, Element.ALIGN_CENTER, 1, true));
        	}
        }
        //表格下面数据
        for (PDFCell pdfCell : footList) {
        	table.addCell(createCell(pdfCell.getValue(), pdfCell.getFont(), pdfCell.getAlign(),pdfCell.getColspan(),pdfCell.isBoderFlag()));
		}
        try{
            //将表格添加到文档中
            document.add(table);
        }catch (DocumentException e){
            e.printStackTrace();
        }
        
        //关闭流
        document.close();
    }
    
    /**
     * 生成pdf A3
     * @param headList 单条总记录
     * @param fields 英文表格字段名
     * @param titles 中文表格字段名
     * @param list 表格数据
     * @param colNum 总列数
     * @param footList 底部数据
     */
    public <T> void generatePDF2(List<PDFCell> headList,String[] fields,String[] titles,List<T> list,int colNum,List<PDFCell> footList) {
        
        // 创建一个只有5列的表格
        PdfPTable table = createTable2(colNum+8);
        for (PDFCell pdfCell : headList) {
        	table.addCell(createCell(pdfCell.getValue(), pdfCell.getFont(), pdfCell.getAlign(),pdfCell.getColspan(),pdfCell.isBoderFlag()));
		}
//        // 添加备注,靠左，不显示边框
//        table.addCell(createCell("中联产品范围规格表", keyfont, Element.ALIGN_LEFT, colNum,false));
        
        table.addCell(createCell("行号", keyfont2, Element.ALIGN_CENTER));
        //设置表头
        for(int i = 1 ; i < colNum ; i++){
        	if(i==1 || i==3){
        		table.addCell(createCell(titles[i-1], keyfont2, Element.ALIGN_CENTER,3,true));
        	}else if (i==2 || i==4 || i==5 || i==7) {
        		table.addCell(createCell(titles[i-1], keyfont2, Element.ALIGN_CENTER,2,true));
			}else{
        		table.addCell(createCell(titles[i-1], keyfont2, Element.ALIGN_CENTER));
        	}
        }
        if(null != list && list.size() > 0){
            int size = list.size();
            for(int i = 0 ; i < size ; i++){
                T t = list.get(i);
                table.addCell(createCell(String.valueOf(i+1), textfont2));
                for(int j = 1 ; j < colNum ; j ++){
                    //获得首字母
                    String firstLetter = fields[j-1].substring(0,1).toUpperCase(); 
                    
                    //获得get方法,getName,getAge等
                    String getMethodName = "get" + firstLetter + fields[j-1].substring(1);
                   
                    Method method;
                    Class classType = list.get(0).getClass();
                    try{
                        //通过反射获得相应的get方法，用于获得相应的属性值
                        method = classType.getMethod(getMethodName, new Class[]{});
                        try{
                             System.out.print(getMethodName +":" + method.invoke(t, new Class[]{}) +",");
                             //添加数据
                             Object obj = method.invoke(t, new Class[]{});
                             if(j==1 || j==3){
                            	 table.addCell(createCell(obj != null ? obj.toString():"",textfont2, Element.ALIGN_CENTER, 3, true));
                             }else if (j==2 || j==4 || j==5 || j==7) {
                            	 table.addCell(createCell(obj != null ? obj.toString():"",textfont2, Element.ALIGN_CENTER, 2, true));
                             }else{
                                 table.addCell(createCell(obj != null ? obj.toString():"",textfont2, Element.ALIGN_CENTER, 1, true));
                             }
                        }catch (IllegalArgumentException e){
                            e.printStackTrace();
                        }catch (IllegalAccessException e){
                            e.printStackTrace();
                        }catch (InvocationTargetException e){
                            e.printStackTrace();
                        }  
                    }catch (SecurityException e){
                        e.printStackTrace();
                    }catch (NoSuchMethodException e){
                        e.printStackTrace();
                    }
                }
                
                System.out.println("");
            }
        } else {
        	//没有明细时，添加一行空白行
        	for(int j = 1 ; j < colNum ; j ++){
        		if(j==1 || j==3){
        			 table.addCell(createCell("", textfont, Element.ALIGN_CENTER, 3, true));
        		}else if (j==2 || j==4 || j==5 || j==7) {
                	 table.addCell(createCell("", textfont, Element.ALIGN_CENTER, 2, true));
				}else{
                	 table.addCell(createCell("", textfont, Element.ALIGN_CENTER, 1, true));
                 }
        	}
        }
        //一行空行
    	//没有明细时，添加一行空白行
    	table.addCell(createCell(String.valueOf(list.size()+1), textfont2));
    	for(int j = 1 ; j < colNum ; j ++){
    		if(j==1 || j==3){
    			 table.addCell(createCell("", textfont, Element.ALIGN_CENTER, 3, true));
             }else if (j==2 || j==4 || j==5 || j==7) {
            	 table.addCell(createCell("", textfont, Element.ALIGN_CENTER, 2, true));
			}else{
            	 table.addCell(createCell("", textfont, Element.ALIGN_CENTER, 1, true));
             }
    	}
        //表格下面数据
        for (PDFCell pdfCell : footList) {
        	table.addCell(createCell(pdfCell.getValue(), pdfCell.getFont(), pdfCell.getAlign(),pdfCell.getColspan(),pdfCell.isBoderFlag()));
		}
        try{
            //将表格添加到文档中
            document.add(table);
        }catch (DocumentException e){
            e.printStackTrace();
        }
        
        //关闭流
        document.close();
    }
    
    
    /**
     * 提供外界调用的接口，生成以head为表头，list为数据的pdf
     * @param head  //数据表头
     * @param list  //数据
     * @return        //excel所在的路径
     */
    public <T> void generatePDFs(List<PDFCell> headList,String[] fields,String[] titles,List<T> list,List<PDFCell> footList,String savePath){
        //获得存储的根目录
//        String savePath = "/Users/huangyl/Desktop/Download/test.pdf";
        
        //获得当天存储的路径,不存在则生成当天的文件夹
//        String realSavePath = new GenerateFold().getFold(savePath);
//        
//        saveFilePathAndName = new GenerateFileName().generateFileName(realSavePath,"pdf");
        
        File file = new File(savePath);
        try{
            file.createNewFile();
        }catch (IOException e1){
     
            e1.printStackTrace();
        }
            initFile(file);
        try{
            file.createNewFile();  //生成一个pdf文件
        }catch (IOException e){
            e.printStackTrace();
        }
        new CreatePdfUtil(file).generatePDF(headList,fields,titles,list,fields.length+1,footList);
    }
    
    /**
     * 提供外界调用的接口，生成以head为表头，list为数据的pdf
     * @param head  //数据表头
     * @param list  //数据
     * @return        //excel所在的路径A3
     */
    public <T> void generatePDFs2(List<PDFCell> headList,String[] fields,String[] titles,List<T> list,List<PDFCell> footList,String savePath){
        //获得存储的根目录
//        String savePath = "/Users/huangyl/Desktop/Download/test.pdf";
        
        //获得当天存储的路径,不存在则生成当天的文件夹
//        String realSavePath = new GenerateFold().getFold(savePath);
//        
//        saveFilePathAndName = new GenerateFileName().generateFileName(realSavePath,"pdf");
        
        File file = new File(savePath);
        try{
            file.createNewFile();
        }catch (IOException e1){
     
            e1.printStackTrace();
        }
            initFile(file);
        try{
            file.createNewFile();  //生成一个pdf文件
        }catch (IOException e){
            e.printStackTrace();
        }
        new CreatePdfUtil(file,1).generatePDF2(headList,fields,titles,list,fields.length+1,footList);
    }
    
    public static void main(String[] args) {
        System.out.println("begin");
        
        String [] head = {"name","age","height","adress","sex","love"};
        
//        List<User> list = new ArrayList<User>();
//        User user1 = new User("李逍遥",21,185,"渔村","男","打架");
//        User user2 = new User("林月如",18,177,"南武林","女","打架");
        
//        list.add(user1);
//        list.add(user2);
        
        
//        String filePath = new CreatePdf().generatePDFs(head,list);
//        System.out.println(filePath);
//        System.out.println("end");
    }
}