package com.bsl.service.serach;

import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslProductInfo;

/**
 * 满足条件产品剩余信息查询接口
 * duk-20190320
 */
public interface ProdSerachService {
	
	//根据条件查询所有满足条件的在库产品信息
	BSLResult getLeftInfo(BslProductInfo bslProductInfo,String flag);
	
}
