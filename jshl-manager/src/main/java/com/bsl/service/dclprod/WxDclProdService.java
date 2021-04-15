package com.bsl.service.dclprod;

import java.util.List;

import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslProductInfo;
import com.bsl.select.QueryCriteria;

/**
 * 外协厂待处理品管理
 * duk-20190320
 */
public interface WxDclProdService {
	
	//根据条件查询所有外协产待处理品信息
	BSLResult getDclWxProdServiceByCriteria(QueryCriteria queryCriteria);
	//获取待处理的待处理品信息
	List<BslProductInfo> getDclInfoDeal();
	//待处理品完成单个
	BSLResult updateFinishStatus(String prodId,String user);
	//待处理品完成批量
	BSLResult updateFinishAllStatus(String[] arrays,String user);
	
}
