package com.bsl.service.dclprod;

import com.bsl.common.utils.BSLResult;
import com.bsl.select.QueryCriteria;

/**
 * 外协厂接收入库
 * duk-20190320
 */
public interface WxProdJsService {
	
	//根据条件查询所有可以接收的出库单信息
	BSLResult getWxTzdJsServiceByCriteria(QueryCriteria queryCriteria);
	//根据出库单号查询废品出库详细流水信息
	BSLResult getWasteSaleInfoByBsId(String bsId);
	//废品接收
	BSLResult updateJsWaste(String saleSerno,Float prodOutWeight,String inputUser);
	//废品退回
	BSLResult updateRebackWaste(String saleSerno,Float prodOutWeight,String inputUser);
	//根据单号等信息查询可以接收的产品信息
	BSLResult getWxCanJsProds(QueryCriteria queryCriteria);
	//根据单号等信息已经接收的产品信息
	BSLResult getWxAlreadyJsProds(QueryCriteria queryCriteria);
	//批量接收转场产品
	BSLResult updateWxJsProdsBSLResult(String prodOutPlan,String prodCheckuser,String[] arrays);
	//退回接收转场产品
	BSLResult updateWxRebackProdsBSLResult(String prodId,String checkuser);
	
}
