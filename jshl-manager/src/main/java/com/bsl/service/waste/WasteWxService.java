package com.bsl.service.waste;

import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslWxWasteWeightInfo;
import com.bsl.select.QueryCriteria;

/**
 * 外协厂废品管理
 * duk-20190320
 */
public interface WasteWxService {

	//根据条件查询所有废品库存信息
	BSLResult getWasteService(QueryCriteria queryCriteria);
	//废品入库
	BSLResult updateWasteIn(BslWxWasteWeightInfo bslWxWasteWeightInfo,String inputUser);
	//废品质量修改
	BSLResult updateWasteCheck(BslWxWasteWeightInfo bslWxWasteWeightInfo,String inputUser);
	
	
}
