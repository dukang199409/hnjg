package com.bsl.service.sale;

import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslProductInfo;

/**
 * 销售退回重新入库处理接口
 * duk-20190319
 */
public interface ProdRetReAddService {
	
	//销售退回重新入库
	BSLResult addRetProd(BslProductInfo bslProductInfo);
	
}
