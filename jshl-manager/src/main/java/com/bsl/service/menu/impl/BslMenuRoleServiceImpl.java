package com.bsl.service.menu.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsl.common.pojo.TreeNode;
import com.bsl.common.utils.JsonUtils;
import com.bsl.mapper.BslMenuInfoMapper;
import com.bsl.mapper.BslUsertypeInfoMapper;
import com.bsl.mapper.BslUsertypeRoleMapper;
import com.bsl.pojo.BslMenuInfo;
import com.bsl.pojo.BslMenuInfoExample;
import com.bsl.pojo.BslUsertypeInfo;
import com.bsl.pojo.BslUsertypeInfoExample;
import com.bsl.pojo.BslUsertypeRole;
import com.bsl.pojo.BslUsertypeRoleExample;
import com.bsl.pojo.BslUsertypeRoleExample.Criteria;
import com.bsl.service.menu.BslMenuRoleService;

@Service
public class BslMenuRoleServiceImpl implements BslMenuRoleService {

	@Autowired
	private BslUsertypeInfoMapper bslUsertypeInfoMapper;
	@Autowired
	private BslUsertypeRoleMapper bslUsertypeRoleMapper;
	@Autowired
	private BslMenuInfoMapper bslMenuInfoMapper;

	@Override
	public String getAllowOpenMenu(String userId) throws Exception {
		List<BslMenuInfo> ls = new ArrayList<>();
		List<TreeNode> la = null;
		List<TreeNode> lb = null;
		TreeNode c = null;
		// 查询该用户下所有角色
		BslUsertypeInfoExample exam = new BslUsertypeInfoExample();
		com.bsl.pojo.BslUsertypeInfoExample.Criteria createCriteria = exam.createCriteria();
		createCriteria.andUserIdEqualTo(userId);
		List<BslUsertypeInfo> bslUsertypeInfos = bslUsertypeInfoMapper.selectByExample(exam);
		//检验用户是否拥有角色
		if(bslUsertypeInfos == null || bslUsertypeInfos.size() == 0) {
			la = new ArrayList<>();
			// 定一个空的集合
			lb = new ArrayList<>();
			c = new TreeNode("0", "该用户没有角色", "/enums/limit/0", "1", lb);
			la.add(c);
			return JsonUtils.objectToJson(la);
		}
		// 遍历角色，查询所有角色下
		BslUsertypeRoleExample example = null;
		Criteria criteria = null;
		for (BslUsertypeInfo bslUsertypeInfo : bslUsertypeInfos) {
			example = new BslUsertypeRoleExample();
			criteria = example.createCriteria();
			criteria.andUserTypeEqualTo(bslUsertypeInfo.getUserType());
			example.setOrderByClause("`menu_id`");
			// 某个角色下的所有权限
			List<BslUsertypeRole> bslUsertypeRoles = bslUsertypeRoleMapper.selectByExample(example);
			//输出该用户的所有权限
			for (BslUsertypeRole bslUsertypeRole : bslUsertypeRoles) {
				ls.add(bslMenuInfoMapper.selectByPrimaryKey(bslUsertypeRole.getMenuId()));
			}
		}
		
		if(ls == null || ls.size() == 0) {
			la = new ArrayList<>();
			// 定一个空的集合
			lb = new ArrayList<>();
			c = new TreeNode("0", "该角色没有设置菜单权限", "/enums/limit/1", "1", lb);
			la.add(c);
			return JsonUtils.objectToJson(la);
		}
		// 定义一个集合来装该用户的父节点
		List<BslMenuInfo> parent = new ArrayList<>();
		// 定义一个集合来装该用户的子节点
		List<BslMenuInfo> child = new ArrayList<>();
		// 去重集合
		Set<BslMenuInfo> menuSet = new HashSet<>();
		for (BslMenuInfo ss : ls) {
			if(!menuSet.contains(ss)) {//去重
				if ("0".equals(ss.getMenuParentId()) && "0".equals(ss.getMenuType())) {
					parent.add(ss);
				} else {
					child.add(ss);
				}
				menuSet.add(ss);
			}
		}
		// 定义一个集合来装某父节点下的子节点
		la = new ArrayList<>();
		for (BslMenuInfo lls : parent) {
			// 定一个空的集合
			lb = new ArrayList<>();
			// 将treeNode对象转为Children
			c = new TreeNode(lls.getMenuId(), lls.getMenuName(), lls.getMenuUrl(), lls.getMenuType(), lb);
			la.add(c);
			getNextTreeNodes(lls, la, c, lb, child);
		}
		return JsonUtils.objectToJson(la);
	}

	private void getNextTreeNodes(BslMenuInfo s, List<TreeNode> la, TreeNode ca, List<TreeNode> lb,
			List<BslMenuInfo> child) {
		// 得到该父节点下所有的子节点的权限
		BslMenuInfoExample example = new BslMenuInfoExample();
		com.bsl.pojo.BslMenuInfoExample.Criteria criteria = example.createCriteria();
		criteria.andMenuParentIdEqualTo(s.getMenuId());
		List<BslMenuInfo> lia = bslMenuInfoMapper.selectByExample(example);
		if (lia != null && lia.size() > 0) {
			TreeNode cb = null;
			for (BslMenuInfo sa : lia) {
				// 判断子节点是否有权限访问
				if (child.contains(sa)) {
					cb = new TreeNode(sa.getMenuId(), sa.getMenuName(), sa.getMenuUrl(), sa.getMenuType());
					lb.add(cb);
					ca.setChildren(lb);
				}
				// 注意，如果你的子节点下还有子节点，就还需要递归往下调用
			}
		}
	}
}
