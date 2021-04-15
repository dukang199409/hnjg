package com.bsl.service.enums.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsl.common.pojo.BSLException;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.common.utils.StringUtil;
import com.bsl.dao.JedisClient;
import com.bsl.mapper.BslCodeTableMapper;
import com.bsl.mapper.BslEnumInfoMapper;
import com.bsl.pojo.BslCodeTableExample;
import com.bsl.pojo.BslCodeTableKey;
import com.bsl.pojo.BslEnumInfo;
import com.bsl.pojo.BslEnumInfoExample;
import com.bsl.pojo.BslEnumInfoKey;
import com.bsl.pojo.BslEnumInfoExample.Criteria;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.enums.EnumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

@Service
public class EnumServiceImpl implements EnumService {
	
	private static Logger log = Logger.getLogger(EnumServiceImpl.class);
	
	@Autowired
	private BslEnumInfoMapper bslEnumInfoMapper;
	@Autowired
	private BslCodeTableMapper bslCodeTableMapper;
	@Autowired
	private JedisClient jedisClient;

	/**
	 * 根据枚举英文名查询该枚举下所有的key和value，根据enum_order升序排序
	 */
	@Override
	public List<BslEnumInfo> getBslEnumInfoByEngName(String enumEngName) {
		BslEnumInfoExample bslEnumInfoExample = new BslEnumInfoExample();
		Criteria criteria = bslEnumInfoExample.createCriteria();
		criteria.andEnumEnglishNameEqualTo(enumEngName);
		bslEnumInfoExample.setOrderByClause("`enum_order`");
		return bslEnumInfoMapper.selectByExample(bslEnumInfoExample);
	}

	/**
	 * 获取去重后所有的枚举英文名和中文名
	 */
	@Override
	public List<BslEnumInfo> getEnumChiEngNames() {
		return bslEnumInfoMapper.getEnumChiEngNames();
	}

	/**
	 * 查询码表配置，查找当前页面有哪些枚举英文名
	 */
	@Override
	public List<BslCodeTableKey> getPageEnumEngKeys(String page) {
		BslCodeTableExample example = new BslCodeTableExample();
		com.bsl.pojo.BslCodeTableExample.Criteria criteria = example.createCriteria();
		criteria.andMenuIdEqualTo(page);
		return bslCodeTableMapper.selectByExample(example);
	}

	/**
	 * 查询所有枚举信息
	 */
	@Override
	public EasyUIDataGridResult getEnumList(int page, int rows) {
		BslEnumInfoExample bslEnumInfoExample = new BslEnumInfoExample();
		//分页处理
		PageHelper.startPage(page,rows);
		//调用sql查询
		bslEnumInfoExample.setOrderByClause("`enum_english_name` asc,`enum_key` asc");
		List<BslEnumInfo> selectByExample = bslEnumInfoMapper.selectByExample(bslEnumInfoExample);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(selectByExample);
		//取记录总条数
		PageInfo<BslEnumInfo> pageInfo = new PageInfo<>(selectByExample);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("enumServiceImpl");
		result.setMethodName("getEnumListByCriteria");
		return result;
	}

	/**
	 * 根据条件查询所有枚举信息
	 */
	@Override
	public BSLResult getEnumListByCriteria(QueryCriteria queryCriteria) {
		BslEnumInfoExample bslEnumInfoExample = new BslEnumInfoExample();
		Criteria criteria = bslEnumInfoExample.createCriteria();
		//英文名
		if (!StringUtils.isBlank(queryCriteria.getEnumEnglishName())) {
			criteria.andEnumEnglishNameLike(StringUtil.likeStr(queryCriteria.getEnumEnglishName()));
		}
		//中文名
		if (!StringUtils.isBlank(queryCriteria.getEnumChineseName())) {
			criteria.andEnumChineseNameLike(StringUtil.likeStr(queryCriteria.getEnumChineseName()));
		}
		
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		if(StringUtils.isBlank(queryCriteria.getSort()) || StringUtils.isBlank(queryCriteria.getOrder())){
			bslEnumInfoExample.setOrderByClause("`enum_english_name` asc,`enum_key` asc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){	
				bslEnumInfoExample.setOrderByClause("`enum_english_name` asc,`enum_key` asc");
			}
		}
		List<BslEnumInfo> selectByExample = bslEnumInfoMapper.selectByExample(bslEnumInfoExample);
		PageInfo<BslEnumInfo> pageInfo = new PageInfo<BslEnumInfo>(selectByExample);
		return BSLResult.ok(selectByExample,"enumServiceImpl","getEnumListByCriteria",pageInfo.getTotal(),selectByExample);
	}

	/**
	 * 新增子项
	 */
	@Override
	public BSLResult addEnum(BslEnumInfo bslEnumInfo) {
		BslEnumInfoKey bslEnumInfoKey = new BslEnumInfoKey();
		bslEnumInfoKey.setEnumEnglishName(bslEnumInfo.getEnumEnglishName());
		bslEnumInfoKey.setEnumKey(bslEnumInfo.getEnumKey());
		BslEnumInfo bslEnumInfoSelect = bslEnumInfoMapper.selectByPrimaryKey(bslEnumInfoKey);
		if(bslEnumInfoSelect != null){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"主键冲突，已经存在该枚举该key的信息！");
		}
		int result =  bslEnumInfoMapper.insert(bslEnumInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"新增失败");
		}
		// 同步新增edis
		jedisClient.set(bslEnumInfo.getEnumEnglishName()+":"+bslEnumInfo.getEnumKey(), bslEnumInfo.getEnumValue());
		return BSLResult.ok();
	}

	/**
	 * 修改子项
	 */
	@Override
	public BSLResult editEnum(BslEnumInfo bslEnumInfo) {
		BslEnumInfoKey bslEnumInfoKey = new BslEnumInfoKey();
		bslEnumInfoKey.setEnumEnglishName(bslEnumInfo.getEnumEnglishName());
		bslEnumInfoKey.setEnumKey(bslEnumInfo.getEnumKey());
		BslEnumInfo bslEnumInfoSelect = bslEnumInfoMapper.selectByPrimaryKey(bslEnumInfoKey);
		if(bslEnumInfoSelect == null){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"没有查询到需要修改的子项内容！");
		}
		bslEnumInfoSelect.setEnumValue(bslEnumInfo.getEnumValue());
		bslEnumInfoSelect.setEnumOrder(bslEnumInfo.getEnumOrder());
		int result =  bslEnumInfoMapper.updateByPrimaryKeySelective(bslEnumInfoSelect);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"修改失败");
		}
		// 同步修改redis
		jedisClient.set(bslEnumInfo.getEnumEnglishName()+":"+bslEnumInfo.getEnumKey(), bslEnumInfo.getEnumValue());
		return BSLResult.ok();
	}
	
	/**
	 * 删除子项
	 */
	@Override
	public BSLResult deleteEnum(String[] arrays) {
		if(arrays.length <= 0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"没有需要删除的子项内容！");
		}
		BslEnumInfoKey bslEnumInfoKey = new BslEnumInfoKey();
		String enumEnglishName = "";
		String enumKey = "";
		for (String enumInfos : arrays) {
			String[] enumInfo = enumInfos.split(";");
			enumEnglishName = enumInfo[0];
			enumKey = enumInfo[1];
			bslEnumInfoKey.setEnumEnglishName(enumEnglishName);
			bslEnumInfoKey.setEnumKey(enumKey);
			int resultStock = bslEnumInfoMapper.deleteByPrimaryKey(bslEnumInfoKey);
			if(resultStock<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(resultStock==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"删除失败");
			}
			// 同步删除redis
			jedisClient.del(enumEnglishName+":"+enumKey);
		}
		return BSLResult.ok();
	}

	/**
	 * 一键同步
	 */
	@Override
	public BSLResult synchData() {
		List<BslEnumInfo> list = bslEnumInfoMapper.selectByExample(new BslEnumInfoExample());
		for (BslEnumInfo bslEnumInfo : list) {
			log.debug("一键同步==key:"+bslEnumInfo.getEnumEnglishName()+":"+bslEnumInfo.getEnumKey()+",value:"+bslEnumInfo.getEnumValue());
			jedisClient.set(bslEnumInfo.getEnumEnglishName()+":"+bslEnumInfo.getEnumKey(), bslEnumInfo.getEnumValue());
		}
		return BSLResult.ok();
	}
	
}
