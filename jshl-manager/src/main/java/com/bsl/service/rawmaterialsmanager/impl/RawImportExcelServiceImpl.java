package com.bsl.service.rawmaterialsmanager.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.ptg.StringPtg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bsl.common.pojo.BSLException;
import com.bsl.common.utils.BSLResult;
import com.bsl.mapper.BslLunoQualityMapper;
import com.bsl.pojo.BslLunoQuality;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.service.rawmaterialsmanager.RawImportExcelService;


/**
 * 入库单录入实现类
 * duk-20190319
 */
@Service
public class RawImportExcelServiceImpl implements RawImportExcelService {

	@Autowired	 
	BslLunoQualityMapper bslLunoQualityMapper;

	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";
	
	/**
	 * 导入炉号质量信息EXCEL
	 */
	@Override
	public BSLResult updateProdLuQuality(CommonsMultipartFile file) {
		int insNum = 0;
		int updNum = 0;
		List<BslLunoQuality> qualitys = readExcel(file);
		if(qualitys == null || qualitys.size()<=0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "未获取到质量信息");
		}
		for (BslLunoQuality bslLunoQuality : qualitys) {
			BslLunoQuality bslLunoQualityTmp = bslLunoQualityMapper.selectByPrimaryKey(bslLunoQuality.getLuId());
			if(bslLunoQualityTmp == null){
				int result = bslLunoQualityMapper.insert(bslLunoQuality);
				insNum += result;
			}else{
				int result = bslLunoQualityMapper.updateByPrimaryKeySelective(bslLunoQuality);
				updNum += result;
			}
		}
		return BSLResult.ok("插入成功，插入"+insNum+"条，更新"+updNum+"条");
	}

	/**
	 * 读取EXCEL获取质量信息
	 * @param url
	 * @return
	 */
	private List<BslLunoQuality> readExcel(CommonsMultipartFile file) {
		List<BslLunoQuality> qualitys = new ArrayList<BslLunoQuality>();
		int errorRow = 0;//记录出错行号
		String fileName = file.getOriginalFilename();
		if(StringUtils.isBlank(fileName)){
			throw new BSLException(ErrorCodeInfo.错误类型_参数为空, "路径不能为空");
		}
		//判断使用excel版本
		Workbook wb = null;
		InputStream in = null;
		try {
			in	= file.getInputStream();
			if(fileName.endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
	            wb = new HSSFWorkbook(in);
	        }else if(fileName.endsWith(EXCEL_XLSX)){    // Excel 2007/2010
	            wb = new XSSFWorkbook(in);
	        }else{
	        	throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"必须为XLS或者XLSX文件");
	        }
            //开始读取excel 只读第一个页签
        	Sheet sheet = wb.getSheetAt(0);
        	// 校验sheet是否合法
            if (sheet == null) {
            	throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "解析Excel失败，获取Sheet页失败！");
            }
            //检验第一行是否有数据
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            if (firstRow == null) {
            	throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "解析Excel失败，在第一行没有读取到任何数据！");
            }
            // 解析每一行的数据，构造数据对象(第二行开始)
            int rowFirst = firstRowNum + 1;
            int rowEnd = sheet.getPhysicalNumberOfRows();
            //创建炉号质量信息实例
            BslLunoQuality bslLunoQuality;
            for (int rowNum = rowFirst; rowNum < rowEnd; rowNum++) {
            	bslLunoQuality = new BslLunoQuality();
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                	throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "解析Excel失败，在第"+rowNum+"行没有读取到任何数据！");
                }
                //循环该行
                for (int c = 0; c < row.getPhysicalNumberOfCells(); c++) {
                	errorRow = c+1;
                	if(c==0){
                		String prodLuid = getCellString(row.getCell(0));
                		if(prodLuid.contains(".")){
                			throw new BSLException(ErrorCodeInfo.错误类型_参数为空, "解析Excel失败，第"+rowNum+"行炉号格式不正确，请设置为文本格式！");
                		}
                		bslLunoQuality.setLuId(prodLuid);
                	}else if (c==1) {
                		
                		 if(StringUtils.isBlank(getCellString(row.getCell(0)))){
                			String prodOriId = getCellString(row.getCell(1));
                     		if(prodOriId.contains(".")){
                     			prodOriId = prodOriId.split("\\.")[0];
                     		}
                         	if(StringUtils.isBlank(prodOriId)){
                         		throw new BSLException(ErrorCodeInfo.错误类型_参数为空, "解析Excel失败，第"+rowNum+"行炉号与原卷号不能同时为空！");
                         	}else{
                         		bslLunoQuality.setLuId("bsl"+prodOriId);
                         	}
                         }
					}else if (c==2) {
						bslLunoQuality.setLuCompany(getCellString(row.getCell(2)));
					}else if (c==3) {
						String ChemicalC = getCellString(row.getCell(3));
						if(!StringUtils.isBlank(ChemicalC)){
							bslLunoQuality.setChemicalC(Math.round(Float.valueOf(ChemicalC)));
						}
					}else if (c==4) {
						String ChemicalSi = getCellString(row.getCell(4));
						if(!StringUtils.isBlank(ChemicalSi)){
							bslLunoQuality.setChemicalSi(Math.round(Float.valueOf(ChemicalSi)));
						}
					}else if (c==5) {
						String ChemicalMn = getCellString(row.getCell(5));
						if(!StringUtils.isBlank(ChemicalMn)){
							bslLunoQuality.setChemicalMn(Math.round(Float.valueOf(ChemicalMn)));
						}
					}else if (c==6) {
						String ChemicalP = getCellString(row.getCell(6));
						if(!StringUtils.isBlank(ChemicalP)){
							bslLunoQuality.setChemicalP(Math.round(Float.valueOf(ChemicalP)));
						}
					}else if (c==7) {
						String ChemicalS = getCellString(row.getCell(7));
						if(!StringUtils.isBlank(ChemicalS)){
							bslLunoQuality.setChemicalS(Math.round(Float.valueOf(ChemicalS)));
						}
					}else if (c==8) {
						String ChemicalTi = getCellString(row.getCell(8));
						if(!StringUtils.isBlank(ChemicalTi)){
							bslLunoQuality.setChemicalTi(Math.round(Float.valueOf(ChemicalTi)));
						}
					}else if (c==9) {
						String ChemicalNi = getCellString(row.getCell(9));
						if(!StringUtils.isBlank(ChemicalNi)){
							bslLunoQuality.setChemicalNi(Math.round(Float.valueOf(ChemicalNi)));
						}
					}else if (c==10) {
						String ChemicalCr = getCellString(row.getCell(10));
						if(!StringUtils.isBlank(ChemicalCr)){
							bslLunoQuality.setChemicalCr(Math.round(Float.valueOf(ChemicalCr)));
						}
					}else if (c==11) {
						String ChemicalCu = getCellString(row.getCell(11));
						if(!StringUtils.isBlank(ChemicalCu)){
							bslLunoQuality.setChemicalCu(Math.round(Float.valueOf(ChemicalCu)));
						}
					}else if (c==12) {
						String ChemicalNb = getCellString(row.getCell(12));
						if(!StringUtils.isBlank(ChemicalNb)){
							bslLunoQuality.setChemicalNb(Math.round(Float.valueOf(ChemicalNb)));
						}
					}else if (c==13) {
						String MechanicalS = getCellString(row.getCell(13));
						if(!StringUtils.isBlank(MechanicalS)){
							bslLunoQuality.setMechanicalS(Float.valueOf(MechanicalS));
						}
					}else if (c==14) {
						String MechanicalB = getCellString(row.getCell(14));
						if(!StringUtils.isBlank(MechanicalB)){
							bslLunoQuality.setMechanicalB(Float.valueOf(MechanicalB));
						}
					}else if (c==15) {
						String MechanicalL = getCellString(row.getCell(15));
						if(!StringUtils.isBlank(MechanicalL)){
							bslLunoQuality.setMechanicalL(Float.valueOf(MechanicalL));
						}
					}else if (c==16) {
						String Bendwc = getCellString(row.getCell(16));
						if(!StringUtils.isBlank(Bendwc)){
							bslLunoQuality.setBendwc(Bendwc);
						}
					}else if (c==17) {
						String Bendyb = getCellString(row.getCell(17));
						if(!StringUtils.isBlank(Bendyb)){
							bslLunoQuality.setBendyb(Bendyb);
						}
					}else if (c==18) {
						String ImpactT = getCellString(row.getCell(18));
						if(!StringUtils.isBlank(ImpactT)){
							bslLunoQuality.setImpactT(Float.valueOf(ImpactT));
						}
					}else if (c==19) {
						String ImpactN1 = getCellString(row.getCell(19));
						if(!StringUtils.isBlank(ImpactN1)){
							bslLunoQuality.setImpactN1(ImpactN1);
						}
					}else if (c==20) {
						String ImpactN2 = getCellString(row.getCell(20));
						if(!StringUtils.isBlank(ImpactN2)){
							bslLunoQuality.setImpactN2(ImpactN2);
						}
					}else if (c==21) {
						String ImpactN3 = getCellString(row.getCell(21));
						if(!StringUtils.isBlank(ImpactN3)){
							bslLunoQuality.setImpactN3(ImpactN3);
						}
					}else if (c==22) {
						try {
							if(StringUtils.isBlank(getCellString(row.getCell(22))) || "未知类型".equals(getCellString(row.getCell(22)))){
								bslLunoQuality.setCrtDate(new Date());
							}else if(getCellString(row.getCell(22)).contains("-")){
								bslLunoQuality.setCrtDate(DictItemOperation.日期转换实例.parse(getCellString(row.getCell(22))));
							}else if(getCellString(row.getCell(22)).length() == 8){
								bslLunoQuality.setCrtDate(DictItemOperation.日期转换实例yyyyMMdd.parse(getCellString(row.getCell(22))));
							}else{
								throw new BSLException(ErrorCodeInfo.错误类型_新增前参数校验, "解析Excel失败，第"+rowNum+"行日期格式必须为空或yyyymmdd或yyyy-mm-dd！");
							}
						} catch (Exception e) {
							bslLunoQuality.setCrtDate(new Date());
						}
					}else if (c==23) {
						
					}else if (c==24) {
						bslLunoQuality.setRemark(getCellString(row.getCell(24)));
					}
                }
                qualitys.add(bslLunoQuality);
            }
		} catch (Exception e) {
			throw new BSLException(ErrorCodeInfo.错误类型_交易异常,"出错行号："+errorRow+",原因："+e.getMessage());
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return qualitys;
	}
	
	/**
	 * 获取单元格内容
	 * @param cell
	 * @return
	 */
	private String getCellString(Cell cell){
		String cellValue = "";
		if (null != cell) {
            // 以下是判断数据的类型
            switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                cellValue = cell.getNumericCellValue() + "";
                break;
            case HSSFCell.CELL_TYPE_STRING: // 字符串
                cellValue = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BLANK: // 空值
                cellValue = "";
                break;
            default:
                cellValue = "未知类型";
                break;
            }
        }
		return cellValue.trim();
	}

}
