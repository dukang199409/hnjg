package com.bsl.service.plan;

import java.util.List;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.select.QueryExample;

/**
 * 半成品入库
 */
public interface SemiFinishedProdService {
	//新增半成品入库
	BSLResult add(BslProductInfo bslProductInfo,int sumNum);
	//半成品入库重新入库
	public BSLResult addRe(BslProductInfo bslProductInfo);
	//修改半成品入库信息
	BSLResult update(BslProductInfo bslProductInfo);
	//获取所有半成品信息
	EasyUIDataGridResult getBslProductInfoList(int page,int rows);
	//根据条件获取所有半成品信息
	BSLResult queryBslProductInfoList(QueryExample queryCriteria);
	//根据实体类获取半成品信息
	List<BslProductInfo> query(BslProductInfoExample example);
	//已入库产品信息删除
	BSLResult delete(String prodId,String user);
	//根据生产出库单查询出库的半成品信息
	BSLResult queryMakeInfoList(QueryExample queryCriteria);
	
}
