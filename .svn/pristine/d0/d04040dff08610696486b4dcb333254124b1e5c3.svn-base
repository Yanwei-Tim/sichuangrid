package com.tianque.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.CookieUtil;
import com.tianque.core.util.GlobalValue;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.userAuth.api.SessionManagerDubboService;

@Transactional
@Scope("request")
@SuppressWarnings("serial")
@Controller("sSOLoginController")
public class SSOLoginController extends BaseAction {

	@Autowired
	private SessionManagerDubboService sessionManagerDubboService;

	public String login() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String oldSessionId = CookieUtil.getOldSessionId(request);
		String sessionId = CookieUtil.getSesssionIdFromCookies(request);
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String validateCode = request.getParameter("validateCode");
		String tqmobile = request.getParameter("tqmobile");
		String imsi = request.getParameter("imsi");
		CookieUtil.clearSessionIdFromCookie(request,
				ServletActionContext.getResponse());
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setMobile(tqmobile);
		user.setImsi(imsi);
		Session session = new Session();
		session.setAccessIp(CookieUtil.getIpAddr(request));
		session.setValidateCode(validateCode);
		session.setSessionId(sessionId);
		session.setLastUrl(request.getRequestURI());
		HashMap<String, Object> resultMap = sessionManagerDubboService.login(
				user, session, oldSessionId, "sso");
		errorMessage = (String) resultMap.get(GlobalValue.LOGIN_FAILURE_MSG);
		LoginType loginResult = (LoginType) resultMap
				.get(GlobalValue.LOGIN_FAILURE_MSG + "_TYPE");

		if (LoginType.loginSuccess.equals(loginResult)) {
			CookieUtil.putSessionIdInCookies(request,
					ServletActionContext.getResponse(),
					GlobalValue.LOGIN_SESSION_ID, sessionId);
		}
		return loginResult.name();
	}

}
