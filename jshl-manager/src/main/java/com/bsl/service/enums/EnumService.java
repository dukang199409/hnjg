package com.bsl.service.enums;

import java.util.List;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslCodeTableKey;
import com.bsl.pojo.BslEnumInfo;
import com.bsl.select.QueryCriteria;

public interface EnumService {
	List<BslEnumInfo> getBslEnumInfoByEngName(String enumEngName);
	List<BslEnumInfo> getEnumChiEngNames();
	List<BslCodeTableKey> getPageEnumEngKeys(String page);
	//查询所有枚举信息
	EasyUIDataGridResult getEnumList(int page,int rows);
	//根据条件查询所有枚举信息
	BSLResult getEnumListByCriteria(QueryCriteria queryCriteria);
	//删除
	BSLResult deleteEnum(String[] arrays);
	//新增
	BSLResult addEnum(BslEnumInfo bslEnumInfo);
	//修改
	BSLResult editEnum(BslEnumInfo bslEnumInfo);
	//一键同步
	BSLResult synchData();
}
