package com.bsl.service.rawmaterialsmanager.impl;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bsl.common.utils.BSLResult;
import com.bsl.mapper.BslBsPlanInfoMapper;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslProductInfoExample.Criteria;
import com.bsl.select.DictItemOperation;
import com.bsl.select.QueryCriteria;
import com.bsl.service.rawmaterialsmanager.RawCompareService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 重量比对实现类
 * duk-20190319
 */
@Service
public class RawCompareServiceImpl implements RawCompareService {

	@Autowired	 
	BslProductInfoMapper bslProductInfoMapper;
	@Autowired	 
	BslBsPlanInfoMapper bslBsPlanInfoMapper;
	

	/**
	 * 根据原料入库通知单号查询卷板信息
	 */
	@Override
	public BSLResult getProdInfoService(QueryCriteria queryCriteria) {
		
		//创建查询的实例，并赋值
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdTypeEqualTo(DictItemOperation.产品类型_卷板);
		criteria.andProdSourceNotEqualTo(DictItemOperation.产品来源_剩余入库);
		if(!StringUtils.isBlank(queryCriteria.getProdId())){
			criteria.andProdIdEqualTo(queryCriteria.getProdId());
		}
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		if(StringUtils.isBlank(queryCriteria.getSort()) || StringUtils.isBlank(queryCriteria.getOrder())){
			bslProductInfoExample.setOrderByClause("`prod_id` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){	
				bslProductInfoExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		PageInfo<BslProductInfo> pageInfo = new PageInfo<BslProductInfo>(bslProductInfos);
		return BSLResult.ok(bslProductInfos,"rawCompareServiceImpl","getProdInfoService",pageInfo.getTotal(),bslProductInfos);
	}

}
