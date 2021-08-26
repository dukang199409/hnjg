package com.bsl.service.rawmaterialsmanager;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslLunoQuality;
import com.bsl.select.QueryCriteria;

/**
 * 质量信息管理接口
 * duk-20190319
 */
public interface QualityService {
	
	//初始化查询所有质量管理信息
	EasyUIDataGridResult getQualityService(Integer page, Integer rows);
	//根据条件查询所有质量管理信息
	BSLResult getQualityService(QueryCriteria queryCriteria);
	//新增质量管理信息
	BSLResult addQualityInfo(BslLunoQuality bslLunoQuality);
	//修改质量管理信息
	BSLResult updateQualityInfo(BslLunoQuality bslLunoQuality);
	//删除质量管理信息
	BSLResult deleteQualityInfo(String luId);
	
}
