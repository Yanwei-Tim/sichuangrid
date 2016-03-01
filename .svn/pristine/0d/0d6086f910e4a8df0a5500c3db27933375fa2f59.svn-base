package com.tianque.mobile.sysadmin.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.LoginType;
import com.tianque.core.util.CookieUtil;
import com.tianque.core.util.GlobalValue;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.sysadmin.SessionManageMobileAdapter;
import com.tianque.mobile.vo.ErrorResponse;
import com.tianque.mobile.vo.PermissionVo;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.userAuth.api.SessionManagerDubboService;

/**
 * 手机端：登陆
 */
@Transactional
@Scope("request")
@Controller("sessionManageMobileAdapter")
public class SessionManageMobileAdapterImpl extends BaseMobileAction implements
		SessionManageMobileAdapter {

	private static final Logger logger = LoggerFactory
			.getLogger(SessionManageMobileAdapterImpl.class);

	@Autowired
	private SessionManagerDubboService sessionManagerDubboService;
	@Autowired
	private PermissionService permissionService;

	private List<PermissionVo> permissionList;
	/** 用户ID */
	private Long userId;
	/** 修改之前的旧密码 */
	private String oldPassword;
	/** 修改之后的新密码 */
	private String currentPassword;
	private String validatePassword;

	private String email;

	private String reset = "reset";

	public String findPermissionList() {
		permissionList = permissionService
				.findUserAllPermissionEnameAndCnameByUserId(ThreadVariable
						.getSession().getUserId());
		return SUCCESS;
	}

	public String login() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String oldSessionId = CookieUtil.getOldSessionId(request);
			String sessionId = CookieUtil.getSesssionIdFromCookies(request);
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String validateCode = request.getParameter("validateCode");
			String sso = (String) request.getAttribute("sso");
			if (sso != null && !"".equals(sso)) {
				if ("admin".equals(userName)) {
					return LoginType.loginFailure.name();
				}
				password = "password";
			}
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
			HashMap<String, Object> resultMap = sessionManagerDubboService
					.login(user, session, oldSessionId, sso);
			session = (Session) resultMap.get("newSession");
			if (session != null) {
				sessionId = session.getSessionId();
				ThreadVariable.setSession(session);
			}

			LoginType loginResult = (LoginType) resultMap
					.get(GlobalValue.LOGIN_FAILURE_MSG + "_TYPE");

			if (LoginType.loginSuccess.equals(loginResult)
					|| LoginType.firstLogin.equals(loginResult)) {
				session = (Session) resultMap.get("newSession");
				sessionId = session.getSessionId();
				ThreadVariable.setSession(session);
				CookieUtil.putSessionIdInCookies(request,
						ServletActionContext.getResponse(),
						GlobalValue.LOGIN_SESSION_ID, sessionId);
			}
			errorMessage = (String) resultMap
					.get(GlobalValue.LOGIN_FAILURE_MSG);

			if (!loginResult.name().equals(LoginType.loginSuccess.name())
					&& !loginResult.name().equals(LoginType.firstLogin.name())
					&& !loginResult.name().equals(
							LoginType.loginSuccessNeedUpdatePsw.name())) {
				throw new Exception();
			}
			String mobileVersion = request.getParameter("mobileVersion");
			String mobileInnerVersion = request
					.getParameter("mobileInnerVersion");
			String imei = request.getParameter("imei");
			String mobileType = request.getParameter("mobileType");
			String mobileSystemVersion = request.getParameter("mobileSystemVersion");
			sessionManagerDubboService.updateMobileVersion(mobileVersion,
					mobileInnerVersion,imei,mobileType,mobileSystemVersion);
			return loginResult.name();
		} catch (Exception e) {
			logger.warn(e.getMessage());
			errorMessage = "登陆失败："
					+ ((errorMessage == null) ? "" : errorMessage)
					+ ((e.getMessage() == null) ? "" : e.getMessage());
			errorCode = ErrorResponse.SESSION_ERROR[0];
			return MOBILE_ERROR;
		}
	}

	/**
	 * 修改用户密码
	 */
	public String updatePassword() {
		if (permissionService.updatePasswordSuccess(ThreadVariable.getSession()
				.getUserId(), oldPassword, currentPassword, validatePassword,
				email)) {
			return SUCCESS;
		}
		errorMessage = "密码更新失败，请重试！";
		return ERROR;
	}

	public List<PermissionVo> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<PermissionVo> permissionList) {
		this.permissionList = permissionList;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReset() {
		return reset;
	}

	public void setReset(String reset) {
		this.reset = reset;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getValidatePassword() {
		return validatePassword;
	}

	public void setValidatePassword(String validatePassword) {
		this.validatePassword = validatePassword;
	}

}
