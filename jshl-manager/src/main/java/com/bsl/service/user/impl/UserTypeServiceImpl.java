package com.bsl.service.user.impl;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bsl.common.pojo.BSLException;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.mapper.BslUsertypeInfoMapper;
import com.bsl.pojo.BslUsertypeInfo;
import com.bsl.pojo.BslUsertypeInfoKey;
import com.bsl.pojo.BslUsertypeRole;
import com.bsl.pojo.UserInfoAndType;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.user.UserTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 销售出库通知单管理实现类
 * duk-20190319
 */
@Service
public class UserTypeServiceImpl implements UserTypeService{
	
	 @Autowired	 
	 BslUsertypeInfoMapper bslUsertypeInfoMapper;
	 
    /**
	 * 加载界面时查询所有人员角色信息
	 */
	@Override
	public EasyUIDataGridResult getAllUserTypeInfoList(Integer page, Integer rows) {
		QueryCriteria criteria = new QueryCriteria();
		//调用sql查询
		criteria.setOrderByClause("`user_id` asc,`user_type` asc");
		//分页处理
		PageHelper.startPage(page,rows);
		List<UserInfoAndType> userInfoAndTypes = bslUsertypeInfoMapper.selectByExampleNew(criteria);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(userInfoAndTypes);
		//取记录总条数
		PageInfo<UserInfoAndType> pageInfo = new PageInfo<>(userInfoAndTypes);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("userTypeServiceImpl");
		result.setMethodName("getUserTypeInfoList");
		return result;
	}
	 
	/**
	 * 获取符合条件的人员角色信息
	 */
	@Override
	public BSLResult getUserTypeInfoList(QueryCriteria queryCriteria) {
		//处理下接收的数据
		if(StringUtils.isBlank(queryCriteria.getUserId())){
			queryCriteria.setUserId(null);
		}else{
			queryCriteria.setUserId("%"+queryCriteria.getUserId()+"%");
		}
		if(StringUtils.isBlank(queryCriteria.getUserName())){
			queryCriteria.setUserName(null);
		}else{
			queryCriteria.setUserName("%"+queryCriteria.getUserName()+"%");
		}
		if(StringUtils.isBlank(queryCriteria.getUserType())){
			queryCriteria.setUserType(null);
		}
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		if(StringUtils.isBlank(queryCriteria.getSort()) || StringUtils.isBlank(queryCriteria.getOrder())){
			queryCriteria.setOrderByClause("`user_id` asc,`user_type` asc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){	
				queryCriteria.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<UserInfoAndType> userInfoAndTypes = bslUsertypeInfoMapper.selectByExampleNew(queryCriteria);
		PageInfo<UserInfoAndType> pageInfo = new PageInfo<UserInfoAndType>(userInfoAndTypes);
		return BSLResult.ok(userInfoAndTypes,"userTypeServiceImpl","getUserTypeInfoList",pageInfo.getTotal(),userInfoAndTypes);
	}

	/**
	 * 新增人员角色信息
	 */
	@Override
	public BSLResult addUserTypeInfo(BslUsertypeInfo bslUsertypeInfo) {
		BslUsertypeInfoKey bslUsertypeInfoKey = new BslUsertypeInfoKey();
		bslUsertypeInfoKey.setUserId(bslUsertypeInfo.getUserId());
		bslUsertypeInfoKey.setUserType(bslUsertypeInfo.getUserType());
		BslUsertypeInfo selectByPrimaryKey = bslUsertypeInfoMapper.selectByPrimaryKey(bslUsertypeInfoKey);
		if(selectByPrimaryKey != null){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"该用户已存在该角色，无需新增！");
		}
		bslUsertypeInfo.setCrtDate(new Date());
		int result = bslUsertypeInfoMapper.insert(bslUsertypeInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增失败");
		}
		return BSLResult.ok();
	}
	
	/**
	 * 删除销售出库通知单
	 */
	@Override
	public BSLResult deleteUserTypeInfo(BslUsertypeInfo bslUsertypeInfo) {
		BslUsertypeInfoKey bslUsertypeInfoKey = new BslUsertypeInfoKey();
		bslUsertypeInfoKey.setUserId(bslUsertypeInfo.getUserId());
		bslUsertypeInfoKey.setUserType(bslUsertypeInfo.getUserType());
		int result = bslUsertypeInfoMapper.deleteByPrimaryKey(bslUsertypeInfoKey);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"删除失败");
		}
		return BSLResult.ok();
	}

}
