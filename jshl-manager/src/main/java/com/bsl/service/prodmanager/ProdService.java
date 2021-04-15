package com.bsl.service.prodmanager;

import java.util.List;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.reportbean.BslProductInfoCollect;
import com.bsl.select.QueryCriteria;
import com.bsl.select.QueryExample;

/**
 * 产品入库
 * duk-20190320
 */
public interface ProdService {
	
	//初始化查询所有产品信息
	EasyUIDataGridResult getProdService(Integer page, Integer rows);
	//根据条件查询所有产品信息
	BSLResult getProdService(QueryCriteria queryCriteria);
	//产品确认入库
	BSLResult addCfmProdInfo(BslProductInfo bslProductInfo,int sumNum);
	//产品补录入库
	BSLResult addCfmProdInfoBuLu(BslProductInfo bslProductInfo,int sumNum);
	//已入库产品信息修改
	BSLResult updateProdInfo(BslProductInfo bslProductInfo);
	//已入库产品信息删除
	BSLResult delete(String prodId,String user);
	//根据盘号获取该盘已经入库的包数
	BSLResult getProdRuNums(String prodId);
	
	List<BslProductInfo> getProdList(BslProductInfoExample productInfoExample);
	
	/**
	 * 查询汇总后销售出库单 自定义sql
	 * @param queryExample
	 * @return
	 */
	List<BslProductInfoCollect> querySaleOutBill(QueryExample queryExample);
	List<BslProductInfoCollect> querySaleOutBillWaste(QueryExample queryExample);
	List<BslProductInfoCollect> querySaleOutByProds(QueryExample queryExample);
	//发货产品新增车号字段
	BSLResult updateProdCarNo(List<String> prods,String prodCarNo);
	
	/**
	 * 外协厂库存管理
	 */
	//根据条件查询所有外协厂产品信息
	BSLResult getWxProdService(QueryCriteria queryCriteria);
	//待处理品处理产品入库--本厂
	BSLResult addProdFromDclinfo(BslProductInfo bslProductInfo,int sumNum);
	//外协产品补录入库
	BSLResult addProdWxinfoB(BslProductInfo bslProductInfo,int sumNum);
	//外协产品修改
	BSLResult updateProdWxInfo(BslProductInfo bslProductInfo);
	//已入库产品信息删除
	BSLResult deleteWxProd(String prodId,String user);
}
