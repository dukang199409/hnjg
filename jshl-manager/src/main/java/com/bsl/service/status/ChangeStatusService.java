package com.bsl.service.status;

import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslChangeStatusRecord;

/**
 * 产品状态强制维护
 * duk-20190319
 */
public interface ChangeStatusService {
	
	//产品状态强制维护
	BSLResult changeProdStatus(BslChangeStatusRecord bslChangeStatusRecord);
	//根据id查询产品信息
	BSLResult getProdInfoByPk(String prodId);
	
}
