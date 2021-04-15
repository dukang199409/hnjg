package com.bsl.service.sale;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslSaleInfoDetail;
import com.bsl.pojo.BslSaleInfoDetailExample;
import com.bsl.select.QueryCriteria;

/**
 * 销售出库详细管理接口
 * duk-20190319
 */
public interface SaleDetailService {
	
	//初始化查询所有销售出库详细信息
	EasyUIDataGridResult getSaleDetailService(Integer page, Integer rows);
	//根据条件查询所有销售出库详细信息
	BSLResult getSaleDetailService(QueryCriteria queryCriteria);
	//根据实体类条件查询所有销售出库详细信息
	BSLResult getSaleDetailServiceByBsDetailInfo(BslSaleInfoDetailExample bslSaleInfoDetailExample);
	//新增固定产品的销售出库详细信息
	BSLResult addSaleDetailInfoById(String inputUser,String salePlanId,String[] arrasy);
	//新增指令类型的销售出库详细信息
	BSLResult addSaleDetailInfo(BslSaleInfoDetail bslSaleInfoDetail);
	//修改指令类型的销售出库详细信息
	BSLResult updateSaleDetailInfo(BslSaleInfoDetail bslSaleInfoDetail);
	//修改固定产品的销售出库详细信息
	BSLResult updateSaleDetailInfoById(BslSaleInfoDetail bslSaleInfoDetail);
	//删除销售出库详细信息
	BSLResult deleteSaleDetailInfo(BslSaleInfoDetail bslSaleInfoDetail);
	//根据销售单号查询类别以及能出库的产品信息
	BSLResult getBsFlagAndProdInfoService(String bsId);
	//根据销售流水查询实体类
	BslSaleInfoDetail getBslSaleInfoDetailById(String planDetailId);
	//刷新该详细计划状态
	BSLResult updateSaleDetailStatus(String saleSerno);
	//根据单号id查询废品可发货详细信息
	BSLResult getWaitSaleWassteGroups(String salePlanId);
	
}
