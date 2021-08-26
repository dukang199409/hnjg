package com.bsl.service.sale;

import com.bsl.common.utils.BSLResult;
import com.bsl.select.QueryCriteria;

/**
 * 废品销售出库管理接口
 * duk-20190319
 */
public interface SaleWasteService {
	
	//根据条件查询废品计划信息
	BSLResult getOutWastesService(QueryCriteria criteria);
	//发货出库
	BSLResult updateSaleWasteOutPut(String saleSerno,Float prodOutWeight,String inputUser);
	//误发退回
	BSLResult updateReBackSaleWaste(String saleSerno,Float prodOutWeight,String inputUser);
	
}
