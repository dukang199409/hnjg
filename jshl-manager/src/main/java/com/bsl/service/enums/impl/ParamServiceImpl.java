package com.bsl.service.enums.impl;


import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bsl.common.utils.BSLResult;
import com.bsl.common.utils.StringUtil;
import com.bsl.mapper.BslParamInfoMapper;
import com.bsl.pojo.BslParamInfo;
import com.bsl.pojo.BslParamInfoExample;
import com.bsl.pojo.BslParamInfoExample.Criteria;
import com.bsl.select.QueryCriteria;
import com.bsl.service.enums.ParamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

@Service
public class ParamServiceImpl implements ParamService {
	
	@Autowired
	private BslParamInfoMapper bslParamInfoMapper;
	
	@Override
	public BSLResult getParamListByCriteria(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslParamInfoExample bslParamInfoExample = new BslParamInfoExample();
		Criteria criteria = bslParamInfoExample.createCriteria();
		//参数编号
		if (!StringUtils.isBlank(queryCriteria.getParamId())) {
			criteria.andParamIdEqualTo(queryCriteria.getParamId());
		}
		//参数名称
		if (!StringUtils.isBlank(queryCriteria.getParamName())) {
			criteria.andParamNameLike(StringUtil.likeStr(queryCriteria.getParamName()));
		}
		
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		if(StringUtils.isBlank(queryCriteria.getSort()) || StringUtils.isBlank(queryCriteria.getOrder())){
			bslParamInfoExample.setOrderByClause("`param_id` asc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){	
				bslParamInfoExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslParamInfo> bslParamInfos = bslParamInfoMapper.selectByExample(bslParamInfoExample);
		PageInfo<BslParamInfo> pageInfo = new PageInfo<BslParamInfo>(bslParamInfos);
		return BSLResult.ok(bslParamInfos,"paramServiceImpl","getParamListByCriteria",pageInfo.getTotal(),bslParamInfos);
	}
	
	@Override
	public String getValueByParamKey(String paramId) {
		//创建查询的实例，并赋值
		BslParamInfoExample bslParamInfoExample = new BslParamInfoExample();
		Criteria criteria = bslParamInfoExample.createCriteria();
		criteria.andParamIdEqualTo(paramId);
		BslParamInfo bslParamInfo = bslParamInfoMapper.selectByPrimaryKey(paramId);
		if(bslParamInfo == null){
			return null;
		}else{
			return bslParamInfo.getParamValue();
		}
	}
	
	@Override
	public BSLResult editParam(BslParamInfo bslParamInfo) {
		bslParamInfo.setUpdDate(new Date());
		int updateByPrimaryKey = bslParamInfoMapper.updateByPrimaryKey(bslParamInfo);
		return BSLResult.ok(updateByPrimaryKey);
	}

	
	
}
