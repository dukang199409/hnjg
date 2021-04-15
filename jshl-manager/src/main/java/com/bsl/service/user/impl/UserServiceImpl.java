package com.bsl.service.user.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.bsl.common.pojo.BSLException;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.common.utils.JsonUtils;
import com.bsl.dao.JedisClient;
import com.bsl.mapper.BslUserInfoMapper;
import com.bsl.mapper.BslUsertypeInfoMapper;
import com.bsl.pojo.BslUserInfo;
import com.bsl.pojo.BslUserInfoExample;
import com.bsl.pojo.BslUserInfoExample.Criteria;
import com.bsl.pojo.BslUsertypeInfo;
import com.bsl.pojo.BslUsertypeInfoExample;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryExample;
import com.bsl.service.user.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 用户管理
 * 
 * @author huangyl
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	private static Logger log = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private BslUserInfoMapper bslUserInfoMapper;
	
	@Autowired
	private BslUsertypeInfoMapper bslUserTypeMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;

	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;

	@Value("${SSO_DOMAIN_BASE_URL}")
	private String SSO_DOMAIN_BASE_URL;

	@Value("${SSO_PAGE_LOGIN_URL}")
	private String SSO_PAGE_LOGIN_URL;

	@Value("${REDIS_USER_ROLE_KEY}")
	private String REDIS_USER_ROLE_KEY;

	@Value("${REDIS_NEXT_USER_ID_KEY}")
	private String REDIS_NEXT_USER_ID_KEY;
	
	/**
	 * 用户信息查询
	 */
	@Override
	public EasyUIDataGridResult getUserList(int page, int rows) {
		BslUserInfoExample example = new BslUserInfoExample();
		// 分页处理
		PageHelper.startPage(page, rows);
		example.setOrderByClause("`user_id` asc");
		List<BslUserInfo> list = bslUserInfoMapper.selectByExample(example);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<BslUserInfo> pageInfo = new PageInfo<BslUserInfo>(list);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("userServiceImpl");//UserServiceImpl.class.getName()
		result.setMethodName("queryUserList");
		return result;
	}
	
	/**
	 * 根据条件查询用户信息
	 */
	@Override
	public BSLResult queryUserList(QueryExample queryExample) {
		BslUserInfoExample example = new BslUserInfoExample();
		if (queryExample != null) {
			Criteria criteria = example.createCriteria();
			if (!StringUtils.isBlank(queryExample.getUserId())) {
				criteria.andUserIdLike("%"+queryExample.getUserId()+"%");
			}
			if (!StringUtils.isBlank(queryExample.getUserName())) {
				criteria.andUserNameLike("%"+queryExample.getUserName()+"%");
			}
			// 分页处理
			if(queryExample.getPage() != null && queryExample.getRows() != null) {
				PageHelper.startPage(Integer.valueOf(queryExample.getPage()), Integer.valueOf(queryExample.getRows()));
			}
			//调用sql查询
			if(StringUtils.isBlank(queryExample.getSort()) || StringUtils.isBlank(queryExample.getOrder())){
				example.setOrderByClause("`user_id` asc");
			}else{
				String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryExample.getSort());
				if(!StringUtils.isBlank(sortSql)){	
					example.setOrderByClause("`"+sortSql+"` "+ queryExample.getOrder());
				}
			}
		}
		List<BslUserInfo> list = bslUserInfoMapper.selectByExample(example);
		PageInfo<BslUserInfo> pageInfo = new PageInfo<BslUserInfo>(list);
		return BSLResult.ok(list, "userServiceImpl", "queryUserList",pageInfo.getTotal(),list);
	}
	
	/**
	 * 新增用户
	 */
	@Override
	public BSLResult register(BslUserInfo bslUserInfo) {
		String userId = queryNewUserId();
		bslUserInfo.setCrtDate(new Date());
		bslUserInfo.setUserId(userId);
		bslUserInfo.setUserStatus("0");//0-正常
		bslUserInfo.setUserPassword("123456");//密码默认123456
		int insert = bslUserInfoMapper.insert(bslUserInfo);
		if(insert<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(insert==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增失败");
		}
		return BSLResult.ok(userId);
	}
	
	/**
	 * 修改用户信息
	 */
	@Override
	public BSLResult update(BslUserInfo bslUserInfo) {
		int updateByPrimaryKeySelective = bslUserInfoMapper.updateByPrimaryKeySelective(bslUserInfo);
		if(updateByPrimaryKeySelective<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(updateByPrimaryKeySelective==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改失败");
		}
		return BSLResult.ok();
	}

	/**
	 * 删除用户信息
	 */
	@Override
	public BSLResult delete(String userId) {
		int deleteByPrimaryKey = bslUserInfoMapper.deleteByPrimaryKey(userId);
		if(deleteByPrimaryKey<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(deleteByPrimaryKey==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"删除失败");
		}
		return BSLResult.ok();
	}

	
	/**
	 * 修改密码
	 */
	@Override
	public BSLResult editPwd(String inputUser, String oldPwd, String newPwd) {
		BslUserInfo bslUserInfo = bslUserInfoMapper.selectByPrimaryKey(inputUser);
		if(!oldPwd.equals(bslUserInfo.getUserPassword())){
			return BSLResult.build(ErrorCodeInfo.错误类型_状态校验错误, "原密码错误！");
		}
		bslUserInfo.setUserPassword(newPwd);
		int result = bslUserInfoMapper.updateByPrimaryKeySelective(bslUserInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改密码失败");
		}
		return BSLResult.ok(inputUser);
	}
	
	/**
	 * 修改电话
	 */
	@Override
	public BSLResult editTel(String userId, String userTel,HttpServletRequest request, HttpServletResponse response) {
		BslUserInfo bslUserInfo = bslUserInfoMapper.selectByPrimaryKey(userId);
		bslUserInfo.setUserTel(userTel);
		int result = bslUserInfoMapper.updateByPrimaryKeySelective(bslUserInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改电话号码失败");
		}
		//更新redis
//		String token = UUID.randomUUID().toString();
		HttpSession session = request.getSession();
		 //将数据存储到session中
		String token = String.valueOf(session.getAttribute("TT_TOKEN"));
//		jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(bslUserInfo));
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
		// 添加写cookie的逻辑，cookie的有效期是关闭浏览器失效。
//		CookieUtils.setCookie(request, response, "TT_TOKEN", token);
		return BSLResult.ok(userId);
	}

	/**
	 * 用户登录
	 */
	@Override
	public BSLResult login(String userId, String password, HttpServletRequest request, HttpServletResponse response) {
//		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		HttpSession session = request.getSession();
		 //将数据存储到session中
		String token = String.valueOf(session.getAttribute("TT_TOKEN"));
		log.info("token:"+token);
		BSLResult bslResult = getUserByToken(token);
		if(bslResult != null && bslResult.getStatus() == ErrorCodeInfo.交易成功) {
			BslUserInfo userInfo = (BslUserInfo) bslResult.getData();
			if(userInfo != null) {
				log.info("userInfo用户登录："+userInfo);
				jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
				return BSLResult.ok(token);
			}
		}
		BslUserInfo user = bslUserInfoMapper.selectByPrimaryKey(userId);
		if(user == null ){
			return BSLResult.build(400, "用户不存在或密码错误");
		}
		// 比对密码
		// if(!user.getUserPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
		if (!user.getUserPassword().equals(password)) {
			return BSLResult.build(400, "用户不存在或密码错误");
		}
		if (!"0".equals(user.getUserStatus())) {
			return BSLResult.build(400, "该员工账号已停用");
		}
		log.info("login_userId:"+userId+",pwd:"+password);
		// 生成token
		token = UUID.randomUUID().toString();
		// 保存用户之前,把用户对象中的密码清空
		user.setUserPassword(null);
		//获取用户角色信息
		StringBuffer sBuffer = new StringBuffer("");
		BslUsertypeInfoExample bslUsertypeInfoExample = new BslUsertypeInfoExample();
		com.bsl.pojo.BslUsertypeInfoExample.Criteria criteria = bslUsertypeInfoExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<BslUsertypeInfo> bslUsertypeInfos = bslUserTypeMapper.selectByExample(bslUsertypeInfoExample);
		for (BslUsertypeInfo bslUsertypeInfo : bslUsertypeInfos) {
			sBuffer.append(bslUsertypeInfo.getUserType()+";");
		}
		user.setRemark(sBuffer.toString());	
		// 把用户信息写入redis
		jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);

		// 添加写cookie的逻辑，cookie的有效期是关闭浏览器失效。
//		CookieUtils.setCookie(request, response, "TT_TOKEN", token);
		session.setAttribute("TT_TOKEN", token);
		// 返回token
		return BSLResult.ok(token);
	}
	
	@Override
	public BSLResult getUserByToken(String token) {
		// 根据token
		String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
		log.info("getUserByToken:"+json);
		if (StringUtils.isBlank(json)) {
			return BSLResult.build(400, "此session已过期，请重新登录");
		}
		// 更新过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
		BslUserInfo user = (BslUserInfo) JsonUtils.jsonToPojo(json, BslUserInfo.class);
		return BSLResult.ok(user);
	}
	
	@Override
	public BSLResult checkData(String content, Integer type) {
		BslUserInfoExample example = new BslUserInfoExample();
		Criteria criteria = example.createCriteria();
		// 对数据进行校验：1、2、3分别代表username、phone、email
		// 用户名校验
		if (1 == type) {
			criteria.andUserNameEqualTo(content);
			// 电话校验
		} else if (2 == type) {
			criteria.andUserTelEqualTo(content);
		}
		List<BslUserInfo> list = bslUserInfoMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return BSLResult.ok(true);
		}
		return BSLResult.ok(false);
	}


	@Override
	public String getLoginUrl() {
		return SSO_DOMAIN_BASE_URL + SSO_PAGE_LOGIN_URL;
	}

	@Override
	public BSLResult checkUserRole(String userType) {
//		String json = jedisClient.get(REDIS_USER_ROLE_KEY + ":" + userType);
//		if (!StringUtils.isBlank(json)) {
////			BslRole bslRole = JsonUtils.jsonToPojo(json, BslRole.class);
////			if (bslRole != null && bslRole.getUserType() != null)
//			return BSLResult.ok(json);
//		}
//		BslRoleExample example = new BslRoleExample();
//		com.bsl.pojo.BslRoleExample.Criteria criteria = example.createCriteria();
//		criteria.andUserTypeEqualTo(userType);
//		List<BslRole> bslRoles = bslRoleMapper.selectByExample(example);
//		if(bslRoles == null || bslRoles.size() == 0) {
//			BslRole bslRole = (BslRole) null;
//			jedisClient.set(REDIS_USER_ROLE_KEY + ":" + userType, JsonUtils.objectToJson(bslRole));
//			jedisClient.expire(REDIS_USER_ROLE_KEY + ":" + userType, SSO_SESSION_EXPIRE);
//			return BSLResult.ok(bslRole);
//		}
//		if (bslRoles != null && bslRoles.size() > 0) {
//			StringBuffer sf = new StringBuffer();
//			for (BslRole bslRole : bslRoles) {
//				sf.append(bslRole.getNoOpenPages()).append("|");
//			}
//			String noOpenPages = sf.toString();
//			jedisClient.set(REDIS_USER_ROLE_KEY + ":" + userType, noOpenPages);
//			jedisClient.expire(REDIS_USER_ROLE_KEY + ":" + userType, SSO_SESSION_EXPIRE);
//			return BSLResult.ok(noOpenPages);
//		}
		return BSLResult.build(ErrorCodeInfo.错误类型_状态校验错误, "没有权限");
	}

	/**
	 * 用户编号自生成六位
	 */
	@Override
	public String queryNewUserId() {
		long incr = jedisClient.incr(REDIS_NEXT_USER_ID_KEY);
		String userId = String.format("%06d", incr);
		return userId;
	}

	@Override
	public BSLResult loginOut(QueryExample queryExample, HttpServletRequest request, HttpServletResponse response) {
		// 1从cookie中取token
//		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		HttpSession session = request.getSession();
		 //将数据存储到session中
		String token = String.valueOf(session.getAttribute("TT_TOKEN"));
		jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
		jedisClient.del(REDIS_USER_ROLE_KEY + ":" + queryExample.getUserType());
//		CookieUtils.deleteCookie(request, response, "TT_TOKEN");
		//清除session
		session.removeAttribute("TT_TOKEN");
		return BSLResult.ok();
	}

	@Override
	public List<BslUserInfo> exportToExcel() {
		return bslUserInfoMapper.selectByExample(new BslUserInfoExample());
	}

}
