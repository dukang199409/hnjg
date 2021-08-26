package com.bsl.service.waste;

import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslWasteWeightInfo;
import com.bsl.select.QueryCriteria;

/**
 * 废品管理
 * duk-20190320
 */
public interface WasteService {

	//根据条件查询所有废品库存信息
	BSLResult getWasteService(QueryCriteria queryCriteria);
	//废品入库
	BSLResult updateWasteIn(BslWasteWeightInfo bslWasteWeightInfo,String inputUser);
	//废品质量修改
	BSLResult updateWasteCheck(BslWasteWeightInfo bslWasteWeightInfo,String inputUser);
	
	
}
