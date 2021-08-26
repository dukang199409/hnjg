package com.bsl.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bsl.common.SpringContextUtils;
import com.bsl.common.utils.BSLResult;
import com.bsl.controller.common.AutoEnumUtil;
import com.bsl.pojo.BslUserInfo;
import com.bsl.service.user.UserService;

/**
 * 拦截器
 * @author huangyl
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;
	
	private static Logger log = Logger.getLogger(LoginInterceptor.class);
	
	private List<String> exceptUrls;
	 
    public List<String> getExceptUrls() {
        return exceptUrls;
    }
 
    public void setExceptUrls(List<String> exceptUrls) {
        this.exceptUrls = exceptUrls;
    }

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// handler执行之前
		String requestUri = request.getRequestURI();
		System.out.println("request.getContextPath()===:"+request.getContextPath());
        if(requestUri.startsWith(request.getContextPath())){
            requestUri = requestUri.substring(request.getContextPath().length(), requestUri.length());
        }
//        String[] uri = requestUri.split("/");
//        requestUri = uri != null && uri.length > 1 ? (uri[1].startsWith("/") ? uri[1] : "/" + uri[1]) : "";
        //系统根目录
        if (StringUtils.equals("/",requestUri) || exceptUrls.contains(requestUri)) {//|| exceptUrls.contains(requestUri)
            return true;
        }
        //放行exceptUrls中配置的url
        for (String url : exceptUrls) {
            if(url.endsWith("/**")){
                if (requestUri.startsWith(url.substring(0, url.length() - 3))) {
                    return true;
                }
            } else if (requestUri.startsWith(url)) {
                return true;
            }
        }
		//判断用户是否登录
		//1从cookie中取token
//		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(1800);
		
		 //将数据存储到session中
		String token = String.valueOf(session.getAttribute("TT_TOKEN"));
		log.info("preHandle===token:"+token);
		//根据token换取用户信息，调用sso系统的接口
		BSLResult bslResult = userService.getUserByToken(token);
		log.info("preHandle===bslResult:"+bslResult);
		BslUserInfo user = null;
		Boolean isFlag = false;
		//如果取不到用户信息
		if(bslResult.getStatus() == 200) {
			user = (BslUserInfo) bslResult.getData();
			log.info("用户信息：" + user);
			if(user != null) {
				isFlag = true;
				//把用户信息放入Request
				request.setAttribute("user", user);
				// 读数据库，自动回显所有枚举
				AutoEnumUtil enumUtil = (AutoEnumUtil)SpringContextUtils.getBean("autoEnumUtil");
				log.info("preHandle===servletPath:" + request.getServletPath());
				String[] url = request.getServletPath().split("/");
				String page = url != null ? url[url.length-1] : "";
				log.info("preHandle===page:" + page);
				enumUtil.addEnums(page, request);
			}
		}	
		if(!isFlag) {
			request.setAttribute("user", null);
			session.removeAttribute("TT_TOKEN");
			//跳转到登录页面，把用户请求的url作为参数传递给登录页面
			response.sendRedirect(userService.getLoginUrl()+"?redirect="+request.getRequestURL());
		}
		//返回值决定handler是否执行，true执行，false不执行
		//如果取到用户信息，返回true放行
		
		return isFlag;
	}

}