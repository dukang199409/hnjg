package com.bsl.service.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslUserInfo;
import com.bsl.select.QueryExample;

public interface UserService {
	BSLResult checkData(String content, Integer type);
	BSLResult register(BslUserInfo bslUserInfo);
	BSLResult login(String username, String password, HttpServletRequest request,HttpServletResponse response);
	BSLResult getUserByToken(String token);
	BSLResult checkUserRole(String userType);
	BSLResult update(BslUserInfo bslUserInfo);
	EasyUIDataGridResult getUserList(int page,int rows);
	String queryNewUserId();
	String getLoginUrl();
	BSLResult delete(String userId);
	BSLResult loginOut(QueryExample queryExample, HttpServletRequest request, HttpServletResponse response);
	BSLResult queryUserList(QueryExample example);
	List<BslUserInfo> exportToExcel();
	BSLResult editPwd(String inputUser,String oldPwd,String newPwd);
	BSLResult editTel(String userId,String userTel,HttpServletRequest request, HttpServletResponse response);
}
