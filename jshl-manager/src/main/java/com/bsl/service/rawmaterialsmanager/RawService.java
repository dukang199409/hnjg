package com.bsl.service.rawmaterialsmanager;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.select.QueryCriteria;

/**
 * 入库单录入
 * duk-20190320
 */
public interface RawService {
	
	//初始化查询所有入库单(卷板)信息
	EasyUIDataGridResult getRawService(Integer page, Integer rows);
	//根据条件查询所有入库单(卷板)信息
	BSLResult getRawService(QueryCriteria queryCriteria);
	//根据实体类条件查询所有预录入入库单信息
	BSLResult getRawServiceByProductInfo(BslProductInfoExample bslProductInfoExample);
	//入库单(卷板)确认入库
	BSLResult addCfmRawInfo(BslProductInfo bslProductInfo) throws Exception;
	//已入库入库单(卷板)信息修改
	BSLResult updateRawInfo(BslProductInfo bslProductInfo);
	//剩余卷板重新入库
	BSLResult addReRawInfo(BslProductInfo bslProductInfo);
	BslProductInfo queryByPrimaryKey(String prodId);
	//扫码新增数据处理
	BSLResult rawAddSMDeal(String rawInfo);
	//删除
	BSLResult deleteRaw(String prodId);
	
	
}
