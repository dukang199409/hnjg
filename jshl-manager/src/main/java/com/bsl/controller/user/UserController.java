package com.bsl.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.pojo.BslUserInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryExample;
import com.bsl.service.user.UserService;

/**
 * 用户Controller
 * @author huangyl
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 展示所有员工
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getUserList(int page,int rows) {
		return userService.getUserList(page,rows);
	}
	
	/**
	 * 根据条件查询员工信息
	 * @param queryExample
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public BSLResult queryUserList(QueryExample queryExample) {
		return userService.queryUserList(queryExample);
	}
	
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable String param,@PathVariable Integer type,String callback) {
		BSLResult result = null;
		if(StringUtils.isBlank(param)) {
			result =  BSLResult.build(400, "校验内容不能为空");
		}
		if(type == null) {
			result =  BSLResult.build(400, "校验内容类型不能为空");
		}
		if(type != 1 && type != 2 && type != 3) {
			result =  BSLResult.build(400, "校验内容类型错误");
		}
		//校验出错
		if(result != null) {
			if(callback != null) {
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			}
			return result;
		}
		//调用服务
		try {
			result =  userService.checkData(param, type);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			result = BSLResult.build(500, e.getMessage());
		}
		
		if(callback != null) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
		return result;
	}
	
	/**
	 * 创建用户
	 * @param BslUserInfo
	 * @return
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult register(BslUserInfo bslUserInfo) {	
		try {
			return userService.register(bslUserInfo);
		}catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(500, e.getMessage());
		}
	}
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public BSLResult login(String userId,String password, HttpServletRequest request,HttpServletResponse response) {
		try {
			return userService.login(userId, password, request, response);
		}catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(500, e.getMessage());
		}
	}
	
	@RequestMapping("/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback) {
		BSLResult result = null;
		try {
			result = userService.getUserByToken(token);
		}catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			e.printStackTrace();
			result = BSLResult.build(500, e.getMessage());
		}
		//判断是否为jsonp调用
		if(callback != null) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
		return result;
	}
	
	@RequestMapping("/queryNewUserId")
	@ResponseBody
	public BSLResult queryNewUserId() {
		String userId = userService.queryNewUserId();
		return BSLResult.ok(userId);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult saveBslUserInfo(BslUserInfo userInfo) {
		if(StringUtils.isBlank(userInfo.getUserName())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "用户名称不能为空");
		}
		try {
			return userService.register(userInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(500, e.getMessage());
		}
	}
	
	/**
	 * 修改用户
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult updateBslUserInfo(BslUserInfo userInfo) {
		if(StringUtils.isBlank(userInfo.getUserId())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "用户id不能为空");
		}
		if(StringUtils.isBlank(userInfo.getUserName())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "用户名称不能为空");
		}
		if(StringUtils.isBlank(userInfo.getUserPassword())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "用户密码不能为空");
		}
		if(StringUtils.isBlank(userInfo.getUserStatus())){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "用户状态不能为空");
		}
		try {
			return userService.update(userInfo);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(500, e.getMessage());
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult deleteBslUserInfo(String userId, HttpServletRequest request) {
		if(StringUtils.isBlank(userId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "用户id不能为空");
		}
		try {
			return userService.delete(userId);
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(500, e.getMessage());
		}
	}
	
	@RequestMapping(value="/loginOut", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult loginOut(QueryExample queryExample, HttpServletRequest request, HttpServletResponse response) {
		try {
			BSLResult bslResult = userService.loginOut(queryExample, request, response);
			if(bslResult != null && bslResult.getStatus() == 200) {
				bslResult.setData("/login");
				return bslResult;
			}
		} catch (Exception e) {
			DictItemOperation.log.info("===========异常:"+e.getMessage());
			return BSLResult.build(500, e.getMessage());
		}
		return null;
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping(value="/editPwd", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult editPwd(String inputUser,String oldPwd,String newPwd){
		if(StringUtils.isBlank(inputUser)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "用户编号不能为空！");
		}
		if(StringUtils.isBlank(oldPwd)) {
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "原密码不能为空");
		}
		if(StringUtils.isBlank(newPwd)) {
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "新密码不能为空");
		}
		try {
			return userService.editPwd(inputUser,oldPwd,newPwd);
		} catch (Exception e) {
			return BSLResult.build(500, e.getMessage());
		}
	}
	
	/**
	 * 修改电话
	 */
	@RequestMapping(value="/editTel", method=RequestMethod.POST)
	@ResponseBody
	public BSLResult editTel(String userId,String userTel,HttpServletRequest request, HttpServletResponse response){
		if(StringUtils.isBlank(userId)){
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "用户编号不能为空！");
		}
		if(StringUtils.isBlank(userTel)) {
			return BSLResult.build(ErrorCodeInfo.错误类型_参数为空, "电话号码不能为空");
		}
		try {
			return userService.editTel(userId,userTel,request,response);
		} catch (Exception e) {
			return BSLResult.build(500, e.getMessage());
		}
	}
}
