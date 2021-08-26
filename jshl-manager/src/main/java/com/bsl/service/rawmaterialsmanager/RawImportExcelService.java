package com.bsl.service.rawmaterialsmanager;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.bsl.common.utils.BSLResult;

/**
 * 炉号信息导入
 * duk-20190320
 */
public interface RawImportExcelService {
	
	BSLResult updateProdLuQuality(CommonsMultipartFile file);
	
}
