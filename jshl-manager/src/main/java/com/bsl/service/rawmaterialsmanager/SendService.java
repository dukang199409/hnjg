package com.bsl.service.rawmaterialsmanager;

import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslSendCheckInfo;
import com.bsl.select.QueryCriteria;

/**
 * 送检查询接口
 * duk-20200522
 */
public interface SendService {
	
	//根据条件查询所有送检信息
	BSLResult getSendService(QueryCriteria queryCriteria);
	//新增送检信息
	BSLResult addSendInfo(BslSendCheckInfo bslSendCheckInfo);
	//修改送检信息
	BSLResult updateSendInfo(BslSendCheckInfo bslSendCheckInfo);
	//修改送检信息
	BSLResult deleteSendInfo(String luId);
	
}
