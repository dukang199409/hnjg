package com.bsl.controller.user;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bsl.pojo.BslUserInfo;

@Controller
public class PageController {
	
//	@Autowired
//	private UserService userService;

	
	/**
	 * 重定向到index
	 */
	@RequestMapping("/")
	public String toIndex() {
		return "forward:/index";
	}
	
	/**
	 * 打开首页
	 */
	@RequestMapping("/index")
	public String showIndex() {
		return "index";
	}
	
	/**
	 * 展示其他页面
	 * @param page
	 * @return
	 */
	@RequestMapping("/index/{page}")
	public String showPage(@PathVariable String page,HttpServletRequest request, String redirect) {

		BslUserInfo userInfo = (BslUserInfo) request.getAttribute("user");
		if(userInfo != null) {
//			BSLResult bslResult = userService.checkUserRole(userInfo.getUserType());
//			if(bslResult.getStatus() == 200) {
//				String noOpenPages = (String) bslResult.getData();
//				if(noOpenPages == null || !noOpenPages.contains(page)) {
					if(StringUtils.isBlank(redirect)) {
						return page;
					} else {
						return "redirect:" + redirect;//重定向
					}
//				} 
//			}
		}
		return "no_auth";
	}
	
	@RequestMapping("/user/{page}")
	public String showUserPage(@PathVariable String page) {
		return page;
	}
	
	@RequestMapping("/login")
	public String showLogin(String redirect, Model model) {
		model.addAttribute("redirect", redirect);
		return "login";
	}
}
