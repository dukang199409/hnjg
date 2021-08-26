package com.bsl.controller.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsl.common.utils.BSLResult;
import com.bsl.common.utils.CookieUtils;
import com.bsl.pojo.BslUserInfo;
import com.bsl.service.menu.BslMenuRoleService;
import com.bsl.service.user.UserService;

@Controller
@RequestMapping("/enums")
public class MenuRoleController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BslMenuRoleService bslMenuRoleService;
	
	@RequestMapping("/allow")
	public void enums(HttpServletRequest request, HttpServletResponse response) {
		String json = "";
		PrintWriter out = null;
		try {
			// 转码
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			//1从cookie中取token
//			String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
			HttpSession session = request.getSession();
			 //将数据存储到session中
			String token = String.valueOf(session.getAttribute("TT_TOKEN"));
			//根据token换取用户信息，调用sso系统的接口
			BSLResult bslResult = userService.getUserByToken(token);
			BslUserInfo user = null;
			//如果取不到用户信息
			if(bslResult.getStatus() == 200) {
				user = (BslUserInfo) bslResult.getData();
				if(user != null) {
					json = bslMenuRoleService.getAllowOpenMenu(user.getUserId());
					out = response.getWriter();
					out.write(json);
					out.flush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}
	
	@RequestMapping("/limit/{lid}")
	public String limit(@PathVariable String lid, HttpServletRequest request) {
		request.setAttribute("limitInfo", getLimitDetail(lid));
		return "limit";
	}
	
	private String getLimitDetail(String lid) {
		switch (lid) {
			case "0":
				return "该用户没有角色,请联系管理员添加该用户角色！";
			case "1":
				return "该角色没有设置菜单权限,请联系管理员进行角色菜单权限设置！";
			default:
				return lid;
		}
	}
}
