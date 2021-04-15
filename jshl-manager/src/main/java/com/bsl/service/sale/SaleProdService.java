package com.bsl.service.sale;

import com.bsl.common.utils.BSLResult;
import com.bsl.select.QueryCriteria;

/**
 * 销售出库管理接口
 * duk-20190319
 */
public interface SaleProdService {
	
	//根据条件查询可以出库的产品信息
	BSLResult getCanOutProdsService(QueryCriteria criteria);
	//根据条件查询已经出库的产品信息
	BSLResult getOutProdsService(QueryCriteria criteria);
	//发货出库
	BSLResult updateSaleProdOutPut(String prodId,String salePlanId,String saleSerno,String prodCheckuser,Float prodOutWeight);
	//产品复磅
	BSLResult saleProdFb(String prodId,Float prodOutWeight);
	//误发退回
	BSLResult updateReBackSaleProd(String saleSerno,String bsFlag,String checkuser);
	//根据编号数组查询产品信息
	BSLResult getByProdIds(String[] arrays);
	//批量出库
	BSLResult prodOutPl(String salePlanId,String saleSerno,String prodCheckuser,String[] arrays);
	
}
