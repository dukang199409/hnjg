package com.bsl.service.sale;

import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslProductInfo;

/**
 * 销售退回/磅差处理接口
 * duk-20190319
 */
public interface ProdReturnService {
	
	//销售退回/磅差处理
	BSLResult updateProdReturn(BslProductInfo bslProductInfo,String dealType,Float prodPrice);
	
	//根据出库单号获取流水信息
	BSLResult getCarInfo(String prodOutPlan);
	
}
