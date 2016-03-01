/**
 * tianque-com.tianque.component.web.filter-IsLoginValidateInterceptor.java Created on Apr 9, 2009
 * Copyright (c) 2010 by 杭州天阙科技有限公司
 */
package com.tianque.web.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.mortbay.util.UrlEncoded;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.tianque.controller.LoginType;
import com.tianque.controller.LoginValidateType;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.CookieUtil;
import com.tianque.core.util.GlobalValue;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.customLog.CustomLoggerFactory;
import com.tianque.customLog.domain.CustomLogger;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.logger.constant.CustomLoggerTypes;
import com.tianque.logger.domain.RecordAccessOverTime;
import com.tianque.mobile.vo.ErrorResponse;
import com.tianque.mobile.vo.ErrorResponse.SessionError;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.userAuth.api.SessionManagerDubboService;
import com.tianque.util.HttpClientUtils;
import com.tianque.web.api.vo.Body;
import com.tianque.web.api.vo.Head;
import com.tianque.web.api.vo.Response;

/**
 * Title: ***<br>
 * 
 * @author <a href=mailto:nifeng@hztianque.com>倪峰</a><br>
 * @description ***<br/>
 * @version 1.0
 */
public class LoginValidateFilter implements Filter {

	private static final CustomLogger logger = CustomLoggerFactory
			.getCustomLogger(LoginValidateFilter.class);
	private static final Logger log = LoggerFactory.getLogger(LoginValidateFilter.class);
	private FilterConfig filterConfig;
	private SessionManagerDubboService sessionManagerDubboService;

	private PermissionService permissionService;
	private OrganizationDubboService organizationDubboService;
	private TencentUserService tencentUserService;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		Long startTime = System.currentTimeMillis();
		ExcelImportHelper.isImport.set(false);
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String url = request.getRequestURI();

		if (StringUtil.isStringAvaliable(url) && "/cd".equals(url)) {
			response.getWriter().print(
					"<script>document.location.href='/login/chengdu/login.jsp'</script>");
			return;
		}
		if (StringUtil.isStringAvaliable(url) && "/ga".equals(url)) {
			response.getWriter().print(
					"<script>document.location.href='/login/guangan/login.jsp'</script>");
			return;
		}
		if (StringUtil.isStringAvaliable(url) && "/xc".equals(url)) {
			response.getWriter().print(
					"<script>document.location.href='/login/xichang/login.jsp'</script>");
			return;
		}
		if (StringUtil.isStringAvaliable(url) && "/ch".equals(url)) {
			response.getWriter().print(
					"<script>document.location.href='/login/chenghua/login.jsp'</script>");
			return;
		}
		if (StringUtil.isStringAvaliable(url) && "/ws".equals(url)) {
			response.getWriter().print(
					"<script>document.location.href='/login/wusheng/login.jsp'</script>");
			return;
		}
		if (StringUtil.isStringAvaliable(url) && "/jinjiang".equals(url)) {
			response.getWriter().print(
					"<script>document.location.href='/login/jinjiang/login.jsp'</script>");
			return;
		}
		if (StringUtil.isStringAvaliable(url) && "/nc".equals(url)) {
			response.getWriter().print(
					"<script>document.location.href='/login/cqnanchuan/login.jsp'</script>");
			return;
		}
		if (StringUtil.isStringAvaliable(url) && "/hc".equals(url)) {
			response.getWriter().print(
					"<script>document.location.href='/login/cqhechuan/login.jsp'</script>");
			return;
		}
		if (StringUtil.isStringAvaliable(url)
				&& "/analysis".toUpperCase().equals(url.toUpperCase())) {
			response.getWriter().print(
					"<script>document.location.href='/login/judgmentAnalysis/login.jsp'</script>");
			return;
		}
		if (url.endsWith(".jpg") || url.endsWith(".gif") || url.endsWith(".css")
				|| url.endsWith(".js") || url.endsWith(".png") || url.endsWith(".bmp")
				|| url.endsWith(".ico") || url.endsWith("1.txt") || url.endsWith(".apk")
				|| url.indexOf("/druid") > -1) {
			chain.doFilter(servletRequest, servletResponse);
			ThreadVariable.clearThreadVariable();
			return;
		}
		if (url.equals("/sysadmin/mobileUpdate/getMobileUpdateByCategoryId.action")) {
			chain.doFilter(servletRequest, servletResponse);
			ThreadVariable.clearThreadVariable();
			return;
		}
		// /sysadmin/ab"
		if (url.endsWith("/sysadmin/ab/findAbTest.action")) {
			chain.doFilter(servletRequest, servletResponse);
			ThreadVariable.clearThreadVariable();
			return;
		}
		//微信用户openId处理 START
		//		if (url.startsWith("/weChatHiddenDangerManage")) {
		boolean setWeChatOpenId = false;
		String openId = null;
		if ("wechat_redirect".equals(request.getParameter("state"))) {
			String code = request.getParameter("code");
			String appid = request.getParameter("weChatUserId");
			TencentUser tencentUser = this.getTencentUserService().getTencentUserByWeChatUserId(
					appid);
			if (null != tencentUser) {
				// 拉取用户信息
				String weixinUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
						+ tencentUser.getAppId() + "&secret=" + tencentUser.getAppSecret()
						+ "&code=" + code + "&grant_type=authorization_code";
				String resultStr = HttpClientUtils.postProxyToOutside(weixinUrl, null);
				Map<String, String> resultMap = null;
				
				try{
					resultMap = JSON.parseObject(resultStr, Map.class);
					openId = resultMap.get("openid");//访问页面的微信用户openid
				}catch(Exception e){
					log.error("微信openId获取错误：" + resultStr, e);
				}
				if (null != openId && !"".equals(openId)) {
					ThreadVariable.setWeChatOpenId(openId);
					setWeChatOpenId = true;

					Cookie cookie = new Cookie("weChatOpenId", openId);
					cookie.setPath("/");
					cookie.setMaxAge(12 * 60 * 60);
					response.addCookie(cookie);
					response.setCharacterEncoding("UTF-8");
					response.flushBuffer();
				}
			}
		}
		if (!setWeChatOpenId) {
			Cookie[] cookies = request.getCookies();
			String weChatOpenId = null;
			if (cookies != null) {
				for (Cookie c : cookies) {
					if ("weChatOpenId".equalsIgnoreCase(c.getName())) {
						weChatOpenId = c.getValue();
						break;
					}
				}
			}
			ThreadVariable.setWeChatOpenId(weChatOpenId);
		}
		//		}
		//微信用户openId处理 END

		// validateSSOUser(request, response);
		if (isNotLoginValidate(url, request)) {
			chain.doFilter(servletRequest, servletResponse);
			ThreadVariable.clearThreadVariable();
			return;
		}
		String sessionId = request.getParameter(GlobalValue.LOGIN_SESSION_ID);
		if (sessionId == null || "".equals(sessionId.trim())) {
			sessionId = CookieUtil.getSesssionIdFromCookies(request);
		}
		HashMap<String, Object> isLoginResultMap = getSessionManager().isLogin(sessionId,
				request.getRequestURI(), CookieUtil.getIpAddr(request));
		LoginValidateType loginValidateType = (LoginValidateType) isLoginResultMap
				.get(GlobalValue.LOGIN_FAILURE_MSG + "_TYPE");
		if (LoginValidateType.isLogined.equals(loginValidateType)) {
			ThreadVariable.setUser((User) isLoginResultMap.get("newUser"));
			ThreadVariable.setOrganization((Organization) isLoginResultMap.get("newOrganization"));
			ThreadVariable.setSession(isLoginResultMap.get("newSession"));
			ThreadVariable.getSession().setOrganization(ThreadVariable.getOrganization());
			CookieUtil.putSessionIdInCookies(request, response, GlobalValue.LOGIN_SESSION_ID,
					sessionId);
		}
		if (!LoginValidateType.isLogined.equals(loginValidateType)) {
			String errorMessage;
			if (LoginValidateType.sessionTimeOut.equals(loginValidateType)) {
				errorMessage = "因长时间不使用系统，请重新登录!";
			} else if (LoginValidateType.noLogin.equals(loginValidateType)) {
				errorMessage = "账号已经登出!";
			} else {
				errorMessage = "账号在异地登录，请确认密码安全，建议修改。";
			}
			if ("true".equals(request.getParameter("tqmobile"))) {
				response.addHeader(ErrorResponse.KEY_HTTP_HEAD, ErrorResponse.VALUE_ERROR_CAUGHT);
				response.setContentType("application/json");
				response.getWriter().print(
						"{errorCode:'" + SessionError.LOGIN_ELSEWHERE + "',errorMessage:''}");
				return;
			}
			if (url.startsWith("/webApi/")) {
				String oldSessionId = CookieUtil.getOldSessionId(request);
				String userName = request.getParameter("userName");
				String password = request.getParameter("password");
				String validateCode = request.getParameter("validateCode");
				String tqmobile = request.getParameter("tqmobile");
				String imsi = request.getParameter("imsi");
				CookieUtil.clearSessionIdFromCookie(request, ServletActionContext.getResponse());
				User user = new User();
				user.setUserName(userName);
				user.setPassword(password);
				user.setMobile(tqmobile);
				user.setImsi(imsi);
				Session session = new Session();
				session.setAccessIp(CookieUtil.getIpAddr(request));
				session.setValidateCode(validateCode);
				session.setSessionId(CookieUtil.getSesssionIdFromCookies(request));
				session.setLastUrl(request.getRequestURI());
				HashMap<String, Object> resultMap = sessionManagerDubboService.login(user, session,
						oldSessionId, null);
				LoginType loginResult = (LoginType) resultMap.get(GlobalValue.LOGIN_FAILURE_MSG
						+ "_TYPE");
				if (LoginType.loginSuccess.equals(loginResult)) {
					session = (Session) resultMap.get("newSession");
					ThreadVariable.setSession(session);
					CookieUtil.putSessionIdInCookies(request, ServletActionContext.getResponse(),
							GlobalValue.LOGIN_SESSION_ID, session.getSessionId());
					user = getPermissionService().getSimpleUserById(session.getUserId());
					Organization org = getOrganizationDubboService().getFullOrgById(
							user.getOrganization().getId());
					user.setOrganization(org);
					ThreadVariable.setUser(user);
					ThreadVariable.setOrganization(org);
					CookieUtil.putSessionIdInCookies(request, response,
							GlobalValue.LOGIN_SESSION_ID, session.getSessionId());
					chain.doFilter(servletRequest, servletResponse);
					ThreadVariable.clearThreadVariable();
					return;
				}
				response.setContentType("application/json");
				response.setCharacterEncoding("utf-8");
				Head head = new Head();
				Body body = new Body();
				head.setResultCode("0");
				head.setResultMsg(errorMessage);
				body.setResult("");
				Response response_ = new Response(head, body);
				try {
					response.getWriter().print(JSONUtil.serialize(response_));
				} catch (JSONException e) {
					e.printStackTrace();
					response.getWriter().print(
							"{'body':{'result':''},'head':{'resultCode':'0','resultMsg':'"
									+ errorMessage + "'}}");
				}
				return;
			}
			response.setContentType("text/html");
			response.getWriter().print(
					"<script>document.location.href='/login.jsp?errorMessage="
							+ UrlEncoded.encodeString(errorMessage) + "'</script>");
			ThreadVariable.clearThreadVariable();
			return;
		} else {
			chain.doFilter(servletRequest, servletResponse);
			long processTime = System.currentTimeMillis() - startTime;
			if (processTime > 3000) {
				logger.log(buildLogDomain(processTime, url));
			}
			ThreadVariable.clearThreadVariable();
			return;
		}

	}

	private RecordAccessOverTime buildLogDomain(long processTime, String url) {
		RecordAccessOverTime recordAccessOverTime = new RecordAccessOverTime();
		recordAccessOverTime.setLogType(CustomLoggerTypes.RECORD_ACCESS_OVER_TIME);
		recordAccessOverTime.setAccessOverTime(processTime);
		recordAccessOverTime.setOpreateUrl(url);
		recordAccessOverTime.setOpreateDate(CalendarUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		recordAccessOverTime.setOpreateOrgName(ThreadVariable.getOrganization().getOrgName());
		recordAccessOverTime.setOpreateUser(ThreadVariable.getUser().getUserName());
		return recordAccessOverTime;
	}

	// private void validateSSOUser(HttpServletRequest httpRequest,
	// HttpServletResponse response) {
	// Object object = httpRequest
	// .getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
	// if (object != null) {
	// Assertion assertion = (Assertion) object;
	// String loginName = assertion.getPrincipal().getName();
	// String user = loginUser(loginName, httpRequest, response);
	// }
	// }
	//
	// private String loginUser(String loginName, HttpServletRequest
	// httpRequest,
	// HttpServletResponse response) {
	// if (!getSessionManager().isLogin(httpRequest, response)) {
	// getSessionManager()
	// .loginBySSOUser(httpRequest, response, loginName);
	// }
	// return loginName;
	// }

	private boolean isNotLoginValidate(String url, HttpServletRequest request) {
		for (String path : GlobalValue.IS_NOT_LOGIN_VALIDATE_PATH) {
			if (url.equals(request.getContextPath() + path)
					|| (url.endsWith("/login.jsp") && !url.endsWith("/index.jsp"))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 在别的地方被登录
	 * 
	 * @param request
	 * @return
	 */
	// private boolean hasFired(HttpServletRequest request) {
	// String sessionId = CookieUtil.getSesssionIdFromCookies(request);
	// Session session = getSessionManager().findSessionBySessionId(sessionId);
	// if (session != null)
	// return !session.isLogin()&&session.getUserName()!=null;
	// return false;
	// }

	@Override
	public void destroy() {
		filterConfig = null;
		sessionManagerDubboService = null;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	private SessionManagerDubboService getSessionManager() {
		if (this.sessionManagerDubboService == null) {
			WebApplicationContext applicationContext = WebApplicationContextUtils
					.getWebApplicationContext(filterConfig.getServletContext());
			AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext
					.getAutowireCapableBeanFactory();
			this.sessionManagerDubboService = (SessionManagerDubboService) autowireCapableBeanFactory
					.getBean("sessionManagerDubboService");
		}
		return sessionManagerDubboService;
	}

	private PermissionService getPermissionService() {
		if (this.permissionService == null) {
			WebApplicationContext applicationContext = WebApplicationContextUtils
					.getWebApplicationContext(filterConfig.getServletContext());
			AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext
					.getAutowireCapableBeanFactory();
			this.permissionService = (PermissionService) autowireCapableBeanFactory
					.getBean("permissionService");
		}
		return permissionService;
	}

	private OrganizationDubboService getOrganizationDubboService() {
		if (this.organizationDubboService == null) {
			WebApplicationContext applicationContext = WebApplicationContextUtils
					.getWebApplicationContext(filterConfig.getServletContext());
			AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext
					.getAutowireCapableBeanFactory();
			this.organizationDubboService = (OrganizationDubboService) autowireCapableBeanFactory
					.getBean("organizationDubboService");
		}
		return organizationDubboService;
	}

	private TencentUserService getTencentUserService() {
		if (this.tencentUserService == null) {
			WebApplicationContext applicationContext = WebApplicationContextUtils
					.getWebApplicationContext(filterConfig.getServletContext());
			AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext
					.getAutowireCapableBeanFactory();
			this.tencentUserService = (TencentUserService) autowireCapableBeanFactory
					.getBean("tencentUserService");
		}
		return tencentUserService;
	}
}
