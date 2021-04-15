package com.bsl.service.user.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bsl.common.pojo.BSLException;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.common.utils.StringUtil;
import com.bsl.mapper.BslMenuInfoMapper;
import com.bsl.mapper.BslUsertypeRoleMapper;
import com.bsl.pojo.BslMenuInfo;
import com.bsl.pojo.BslMenuInfoExample;
import com.bsl.pojo.BslUsertypeRole;
import com.bsl.pojo.BslUsertypeRoleExample;
import com.bsl.pojo.BslUsertypeRoleExample.Criteria;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.user.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 权限管理
 * @author huangyl
 * @date 2019年5月27日  
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired	 
	BslUsertypeRoleMapper bslRoleMapper;
	
	@Autowired	 
	BslMenuInfoMapper bslMenuInfoMapper;

	/**
	 * 初始化查询所有权限信息 
	 */
	@Override
	public EasyUIDataGridResult getRoleService(Integer page, Integer rows) {
		//查询条件状态 
		BslUsertypeRoleExample bslRoleExample = new BslUsertypeRoleExample();
		Criteria criteria = bslRoleExample.createCriteria();
		criteria.andUserTypeNotEqualTo(DictItemOperation.人员角色_管理员);//筛选出不是管理员角色的权限
		criteria.andMenuTypeEqualTo("1");//筛选出不是父节点的菜单权限
		//分页处理
		PageHelper.startPage(page,rows);
		//调用sql查询
		bslRoleExample.setOrderByClause("`menu_id` asc,`user_type` asc,`role_id` desc");
		List<BslUsertypeRole> bslRoles = bslRoleMapper.selectByExample(bslRoleExample);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(bslRoles);
		//取记录总条数
		PageInfo<BslUsertypeRole> pageInfo = new PageInfo<>(bslRoles);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("roleServiceImpl");
		result.setMethodName("getRoleService");
		return result;
	}

	/**
	 *根据条件查询权限信息 
	 */
	@Override
	public BSLResult getRoleService(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslUsertypeRoleExample bslRoleExample = new BslUsertypeRoleExample();
		Criteria criteria = bslRoleExample.createCriteria();
		criteria.andUserTypeNotEqualTo(DictItemOperation.人员角色_管理员);//筛选出不是管理员角色的权限
		criteria.andMenuTypeEqualTo("1");//筛选出不是父节点的菜单权限
		if(!StringUtils.isBlank(queryCriteria.getBslRoleId())){
			criteria.andRoleIdEqualTo(Integer.parseInt(queryCriteria.getBslRoleId()));
		}
		if(!StringUtils.isBlank(queryCriteria.getUserType())){
			criteria.andUserTypeEqualTo(queryCriteria.getUserType());
		}
		if(!StringUtils.isBlank(queryCriteria.getNoOpenPages())){
			criteria.andMenuIdLike("%"+queryCriteria.getNoOpenPages()+"%");
		}
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		if(StringUtils.isBlank(queryCriteria.getSort()) || StringUtils.isBlank(queryCriteria.getOrder())){
			bslRoleExample.setOrderByClause("`menu_id` asc,`user_type` asc,`role_id` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){
				bslRoleExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslUsertypeRole> bslRoles = bslRoleMapper.selectByExample(bslRoleExample);
		PageInfo<BslUsertypeRole> pageInfo = new PageInfo<BslUsertypeRole>(bslRoles);
		return BSLResult.ok(bslRoles,"roleServiceImpl","getRoleService",pageInfo.getTotal(),bslRoles);
	}

	/**
	 *新增人员权限信息 
	 */
	@Override
	public BSLResult addRoleInfoById(String[] arrays) {
		if(arrays.length <= 0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"没有需要新增权限内容！");
		}
		BslUsertypeRole bslRole = new BslUsertypeRole();
		Set<String> parentMenuIds = new HashSet<>();
		for (String roleInfoString : arrays) {
			String[] roleInfo = roleInfoString.split(";");
			String userType = roleInfo[0];
			String menuId = "";
			if(!"undefined".equals(roleInfo[1]) && !"".equals(roleInfo[1])){
				menuId = roleInfo[1];
			}
			if(roleInfo.length == 4 && !"undefined".equals(roleInfo[3]) && !"".equals(roleInfo[3])){
				bslRole.setRemark(roleInfo[3]);
			}
			/**
			 * 将父节点权限自动添加
			 */
			String parentMenuId = menuId.substring(0, 4)+"0";
			if(!parentMenuIds.contains(parentMenuId)){
				BslMenuInfo bslMenuInfo = bslMenuInfoMapper.selectByPrimaryKey(menuId);
				parentMenuIds.add(parentMenuId);
				//开始插入
				bslRole.setUserType(userType);
				bslRole.setMenuId(parentMenuId);
				bslRole.setMenuName(bslMenuInfo.getMenuName());
				bslRole.setMenuType("0");//父节点
				int result = bslRoleMapper.insert(bslRole);
				if(result<0){
					throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
				}else if(result==0){
					throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增失败");
				}
			}
			
			//开始插入
			bslRole.setUserType(userType);
			bslRole.setMenuId(menuId);
			bslRole.setMenuName(roleInfo[2]);
			bslRole.setMenuType("1");//子节点
			
			int result = bslRoleMapper.insert(bslRole);
			if(result<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(result==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增失败");
			}
		}
		
		return BSLResult.ok();
	}

	/**
	 *删除人员权限信息 
	 */
	@Override
	public BSLResult deleteSaleDetailInfo(String[] arrays) {
		if(arrays.length <= 0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"没有需要删除的权限内容！");
		}
		Set<String> parentMenuIds = new HashSet<>();
		Map<String, Integer> map = new HashMap<>();
		for (String roleInfoString : arrays) {
			String[] roleInfo = roleInfoString.split(";");
			String menuId = roleInfo[2];
			String key = menuId.substring(0,4)+"0";
			if(parentMenuIds.contains(key)) {
				map.put(key, map.get(key) + 1);
			}else {
				map.put(key, 1);
				parentMenuIds.add(key);
			}
		}
		Set<String> delParentMenuIds = new HashSet<>();
		Set<Integer> delParentRoleIds = new HashSet<>();
		BslUsertypeRoleExample example = null;
		for (String roleInfoString : arrays) {
			String[] roleInfo = roleInfoString.split(";");
			String id = roleInfo[0];
			String userType = roleInfo[1];
			String menuId = roleInfo[2];
			if(StringUtils.isBlank(id)){
				throw new BSLException(ErrorCodeInfo.错误类型_参数为空,"规则编号不能为空！");
			}
			String parentMenuId = menuId.substring(0, 4)+"0";
			if(!delParentMenuIds.contains(parentMenuId)&&parentMenuIds.contains(parentMenuId)){
				// TODO 子节点删完时，父节点自动删除，待完善
				example = new BslUsertypeRoleExample();
				Criteria criteria = example.createCriteria();
				criteria.andMenuIdLike(StringUtil.likeStr(menuId.substring(0, 4)));
				criteria.andUserTypeEqualTo(userType);
				List<BslUsertypeRole> list = bslRoleMapper.selectByExample(example);
				if(list != null && list.size()-1 == map.get(parentMenuId)) {
					delParentMenuIds.add(parentMenuId);
					for (BslUsertypeRole bslUsertypeRole : list) {
						if(bslUsertypeRole.getMenuId().equals(parentMenuId)) {
							delParentRoleIds.add(bslUsertypeRole.getRoleId());
							break;
						}
					}
				}
			}
			//删除
			int result = bslRoleMapper.deleteByPrimaryKey(Integer.parseInt(id));
			if(result<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(result==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"删除失败");
			}
		}
		//删除父节点
		for (Integer id : delParentRoleIds) {
			//删除
			int result = bslRoleMapper.deleteByPrimaryKey(id);
			if(result<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(result==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"删除失败");
			}
		}
		return BSLResult.ok();
	}

	/**
	 * 获取可限制交易列表
	 * @param userType
	 * @return
	 */
	@Override
	public BSLResult getMenuByUserType(String userType) {
		//获取已经限制的交易信息
		BslUsertypeRoleExample bslRoleExample = new BslUsertypeRoleExample();
		Criteria criteria = bslRoleExample.createCriteria();
		criteria.andUserTypeEqualTo(userType);
		List<BslUsertypeRole> bslRoles = bslRoleMapper.selectByExample(bslRoleExample);
		//创建交易查询实例
		BslMenuInfoExample example = new BslMenuInfoExample();
		com.bsl.pojo.BslMenuInfoExample.Criteria criteriaBslMenuInfo = example.createCriteria();
		//查询该角色已经限制的交易
		List<String> menuIds = new ArrayList<String>();
		if(bslRoles != null && bslRoles.size()>0){
			for (BslUsertypeRole bslRole : bslRoles) {
				menuIds.add(bslRole.getMenuId());
			}
			criteriaBslMenuInfo.andMenuIdNotIn(menuIds);
		}
		criteriaBslMenuInfo.andMenuTypeEqualTo("1");
		example.setOrderByClause("`menu_id` asc");
		List<BslMenuInfo> selectByExample = bslMenuInfoMapper.selectByExample(example);
		return BSLResult.ok(selectByExample);
	}

}