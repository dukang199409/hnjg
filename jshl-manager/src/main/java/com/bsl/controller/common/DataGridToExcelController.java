package com.bsl.controller.common;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bsl.common.ExcelUtil;
import com.bsl.common.SpringContextUtils;
import com.bsl.common.utils.BSLResult;
import com.bsl.select.DictItemOperation;
import com.bsl.select.QueryCriteria;
import com.bsl.select.QueryExample;

/**
 * 导出Excel
 * 
 * @author huangyl
 * @date 2019年4月22日
 *
 */
@Controller
@RequestMapping("/export")
public class DataGridToExcelController {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
	public void exportExcel(QueryExample example,HttpServletResponse response) throws Exception {  
//		response.setContentType("application/octet-stream");
//		response.setHeader("Content-Disposition", "attachment;filename="+ExportUtils.getExcelName(example.getExcelName()));
		//创建Excel
//		HSSFWorkbook wb = new HSSFWorkbook();
//		HSSFSheet sheet = wb.createSheet("sheet0");
		DictItemOperation.log.info("====exportExcel方法====开始"+example);
		ServletOutputStream out = null;
		try {
			// 获取bean,注意这里用实现类的接口强转去获得目标bean的代理对象，才能成功执行下面的反射方法
			Object obj= SpringContextUtils.getBean(example.getClassName());
			// 获取方法，这个selectAll实际上是父类的方法
			Method method=ReflectionUtils.findMethod(obj.getClass(),example.getMethodName(),QueryExample.class);
			// 反射执行方法
			BSLResult result = (BSLResult) method.invoke(obj, example);
			DictItemOperation.log.info("====method.invoke方法===="+result);
			List list = (List) result.getData();
//			String titles = new String(example.getTitles().getBytes("ISO-8859-1"),"UTF-8");
//			ExportUtils.outputHeader(example.getTitles().split(","), sheet);
//			ExportUtils.outputColumns(example.getFields().split(","), list, sheet, 2);
			//获取输出流，写入excel并关闭
			out = response.getOutputStream();
			
			LinkedHashMap<String,String> fieldMap = new LinkedHashMap<>();
			String[] fields = example.getFields().split(",");
			String[] titles = example.getTitles().split(",");
			for (int i = 0; i < fields.length; i++) {
				fieldMap.put(fields[i], titles[i]);
			}
			ExcelUtil.listToExcel(list, fieldMap, example.getExcelName(), ExcelUtil.getExcelName(example.getExcelName(),example.getLoginUserId()), response);
//			wb.write(out);
			out.flush();
		} catch(Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			e.printStackTrace();
		} finally{
			if(out != null){
				out.close();
			}
		}
		DictItemOperation.log.info("====exportExcel方法====结束");
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/exportExcelExtra", method = RequestMethod.POST)
	public void exportExcelExtra(QueryCriteria example,HttpServletResponse response) throws Exception {  
		DictItemOperation.log.info("====exportExcelExtra方法====开始"+example);
		ServletOutputStream out = null;
		try {
			// 获取bean,注意这里用实现类的接口强转去获得目标bean的代理对象，才能成功执行下面的反射方法
			Object obj= SpringContextUtils.getBean(example.getClassName());
			// 获取方法，这个selectAll实际上是父类的方法
			Method method=ReflectionUtils.findMethod(obj.getClass(),example.getMethodName(),QueryCriteria.class);
			// 反射执行方法
			BSLResult result = (BSLResult) method.invoke(obj, example);
			List list = (List) result.getData();
			//获取输出流，写入excel并关闭
			out = response.getOutputStream();
			
			LinkedHashMap<String,String> fieldMap = new LinkedHashMap<>();
			String[] fields = example.getFields().split(",");
			String[] titles = example.getTitles().split(",");
			for (int i = 0; i < fields.length; i++) {
				fieldMap.put(fields[i], titles[i]);
			}
			ExcelUtil.listToExcel(list, fieldMap, example.getExcelName(), ExcelUtil.getExcelName(example.getExcelName(),example.getLoginUserId()), response);
			out.flush();
		} catch(Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			e.printStackTrace();
		} finally{
			if(out != null){
				out.close();
			}
		}
		DictItemOperation.log.info("====exportExcelExtra方法====结束");
	}
}
