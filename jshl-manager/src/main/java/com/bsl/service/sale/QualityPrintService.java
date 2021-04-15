package com.bsl.service.sale;

import java.util.List;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslCarRetchaInfo;
import com.bsl.reportbean.BslProductQualityInfo;
import com.bsl.select.QueryCriteria;

/**
 * 质量书打印接口
 * duk-20190319
 */
public interface QualityPrintService {
	
	//初始化查询所有车次信息
	EasyUIDataGridResult getCarSernoService(Integer page, Integer rows);
	//根据条件查询所有车次信息
	BSLResult getCarSernoServiceByCriteria(QueryCriteria queryCriteria);
	//根据条件查询车次发货详细信息
	BSLResult getCarDeatilInfo(String prodOutCarno);
	//根据车号集合获取车次发货详细信息
	List<BslProductQualityInfo> getCarDetailByList(List<String> cars);
	//记录打印次数
	BSLResult updatePrintNum(String prodOutCarno);
	//记录车次信息
	BSLResult insertCCInfo(String prodOutCarno,String wxFlag,String bsGettype);
	//修改车次信息
	BSLResult updateCarDeatilInfo(BslCarRetchaInfo bslCarRetchaInfo);
	
}
