package com.tianque.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.rpc.RpcException;
import com.tianque.controller.vo.CurrentLoginInfoVo;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.CookieUtil;
import com.tianque.core.util.GlobalValue;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.userAuth.api.SessionManagerDubboService;

@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("sessionManageController")
public class SessionManageController extends BaseAction {
	private static final String PASSWORD_OUT_TIME = "passwordOutTime";// 密码超过给定时间没有修改（一个月）
	private Session session;
	private User user;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private Organization organization;
	private String path;
	private List<Session> sessions;
	private CurrentLoginInfoVo currentLoginInfoVo;
	private Boolean bool;
	public String[] loginnames;
	public int accountType;
	public String passwordOutTime;

	public String login() throws Exception {
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
			if (LoginType.loginSuccessNeedUpdatePsw.equals(loginResult)) {
				setPasswordOutTime(PASSWORD_OUT_TIME);
			}
			return loginResult.name();
		} catch (RpcException e) {
			errorMessage = "{userName:'登陆超时！'}";
			return LoginType.loginFailure.name();
		}
	}

	public String loginForMobile() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String oldSessionId = CookieUtil.getOldSessionId(request);
		String sessionId = CookieUtil.getSesssionIdFromCookies(request);
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String validateCode = request.getParameter("validateCode");
		String tqmobile = request.getParameter("tqmobile");
		String imsi = request.getParameter("imsi");
		String imsiNo = request.getParameter("imsiNo");
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
				.loginForMobile(user, session, oldSessionId, imsiNo);
		session = (Session) resultMap.get("newSession");
		sessionId = session.getSessionId();
		ThreadVariable.setSession(session);
		LoginType loginResult = (LoginType) resultMap
				.get(GlobalValue.LOGIN_FAILURE_MSG + "_TYPE");
		if (LoginType.loginSuccess.equals(loginResult)) {
			CookieUtil.putSessionIdInCookies(request,
					ServletActionContext.getResponse(),
					GlobalValue.LOGIN_SESSION_ID, sessionId);
		}
		errorMessage = (String) resultMap.get(GlobalValue.LOGIN_FAILURE_MSG);
		return loginResult.name();
	}

	public String mockLogin() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Session session = new Session();
		session.setAccessIp(CookieUtil.getIpAddr(request));
		session.setLastUrl(request.getRequestURI());
		String userName = request.getParameter("switchUserName");
		String oldSessionId = CookieUtil.getOldSessionId();
		String curSessionId = CookieUtil.getSessionId();
		HashMap<String, Object> resultMap = sessionManagerDubboService
				.mockLogin(session, userName, oldSessionId, curSessionId);
		LoginType loginResult = (LoginType) resultMap
				.get(GlobalValue.LOGIN_FAILURE_MSG + "_TYPE");
		if (LoginType.loginSuccess.equals(loginResult)) {
			session = (Session) resultMap.get("newSession");
			ThreadVariable.setSession(session);
			CookieUtil.clearSessionIdFromCookie(request,
					ServletActionContext.getResponse());
			CookieUtil.putSessionIdInCookies(request,
					ServletActionContext.getResponse(),
					GlobalValue.LOGIN_SESSION_ID, session.getSessionId());
			CookieUtil.putSessionIdInCookies(request,
					ServletActionContext.getResponse(),
					GlobalValue.OLD_LOGIN_SESSION_ID,
					(String) resultMap.get("oldSessionId"));
		}
		errorMessage = (String) resultMap.get(GlobalValue.LOGIN_FAILURE_MSG);
		return loginResult.name();
	}

	public String logout() throws Exception {
		String sessionId = getSessionIdFromCookie(ServletActionContext
				.getRequest());
		sessionManagerDubboService.logout(sessionId);
		CookieUtil.clearValueInCookies(ServletActionContext.getResponse(),
				GlobalValue.LOGIN_SESSION_ID, sessionId);
		if (isJinJiang()) {
			return "jinjiang";
		}
		if (isChengDu()) {
			return "chengdu";
		}
		if (isXiCang()) {
			return "xicang";
		}
		if (isNanChuan()) {
			return "nanchuan";
		}
		if (isHeChuan()) {
			return "hechuan";
		}
		return SUCCESS;
	}

	private String getSessionIdFromCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return "";
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(GlobalValue.LOGIN_SESSION_ID)) {
				return cookie.getValue();
			}
		}
		return "";
	}

	public String getSessionList() throws Exception {
		PageInfo pageInfo = ControllerHelper.processAllOrgRelativeName(
				sessionManagerDubboService.getSessionsByOrgInternalCode(
						ThreadVariable.getUser().getOrgInternalCode(),
						this.getPage(), this.getRows(), sidx, sord,
						organization, accountType), organizationDubboService,
				new String[] { "organization" }, null);
		this.gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String findAllSessions() throws Exception {
		PageInfo<Session> pageInfo = sessionManagerDubboService
				.getSessionsByOrgInternalCode(ThreadVariable.getUser()
						.getOrgInternalCode(), this.getPage(), this.getRows(),
						sidx, sord, organization, accountType);
		sessions = pageInfo.getResult();
		return SUCCESS;
	}

	public String getLoginInfo() throws Exception {
		session = ThreadVariable.getSession();
		if (this.session == null || !session.isLogin()) {
			return SUCCESS;
		}
		user = ThreadVariable.getUser();
		organization = ThreadVariable.getOrganization();
		//		user = this.permissionService.getSimpleUserById(session.getUserId());
		//		organization = organizationDubboService.getSimpleOrgById(user
		//				.getOrganization().getId());
		path = ControllerHelper.getRelativeOrgNameByOrgId(user
				.getOrganization().getId(), organizationDubboService);
		organization.setOrgName(path);
		user.setOrganization(organization);
		return SUCCESS;
	}

	public String getCurrentLoginInfoByHaiNing() throws Exception {
		return getCurrentLoginInfo(false);
	}

	public String getCurrentLoginInfoByYuH() throws Exception {
		return getCurrentLoginInfo(true);
	}

	public String getCurrentLoginInfo(boolean isYuH) throws Exception {
		currentLoginInfoVo = new CurrentLoginInfoVo();
		session = ThreadVariable.getSession();
		if (this.session == null || !session.isLogin()) {
			return SUCCESS;
		}
		user = this.permissionService.getSimpleUserById(session.getUserId());
		currentLoginInfoVo.setUserId(user.getId());
		currentLoginInfoVo.setOrgId(user.getOrganization().getId());
		organization = organizationDubboService.getFullOrgById(user
				.getOrganization().getId());
		if (isYuH) {
			this.isYuH();
		} else {
			this.isHaiNing();
		}
		if (bool != null && bool) {// 如果是余杭
			currentLoginInfoVo.setYuhangUser(true);
			String fullPinyin = "";
			if (organization.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG
					&& organization.getOrgLevel().getInternalId() == OrganizationLevel.DISTRICT) {
				fullPinyin = organizationDubboService.getSimpleOrgById(
						organization.getParentOrg().getId()).getFullPinyin();
			} else if ("海宁市".equals(organization.getOrgName())
					|| "余杭区".equals(organization.getOrgName())
					|| OrganizationLevel.TOWN == organization.getOrgLevel()
							.getInternalId()
					|| OrganizationLevel.VILLAGE == organization.getOrgLevel()
							.getInternalId()) {
				fullPinyin = organization.getFullPinyin();
			} else {
				organization = organizationDubboService
						.getTownOrganizationById(organization.getId());
				fullPinyin = organization.getFullPinyin();
			}
			currentLoginInfoVo.setOrgFullPyForTown(fullPinyin);
			currentLoginInfoVo.setOrgLevel(organization.getOrgLevel());
			currentLoginInfoVo.setDepartMentNo(organization.getDepartmentNo());
		}
		return SUCCESS;
	}

	public String isHaiNing() {
		return isUserBelongToSpecifiedOrg("海宁市");
	}

	public String isYuH() {
		return isUserBelongToSpecifiedOrg("余杭区");
	}

	public boolean isChengDu() {
		return isBelongToSpecifiedOrg("成都市");
	}

	public boolean isXiCang() {
		return isBelongToSpecifiedOrg("西昌市");
	}

	public boolean isNanChuan() {
		return isBelongToSpecifiedOrg("南川区");
	}

	public boolean isHeChuan() {
		return isBelongToSpecifiedOrg("合川区");
	}

	public boolean isJinJiang() {
		return isBelongToSpecifiedOrg("锦江区");
	}

	public String isXiCangLogo() {
		return isUserBelongToSpecifiedOrg("西昌市");
	}

	public String isWuShengLogo() {
		return isUserBelongToSpecifiedOrg("武胜县");
	}

	/**
	 * 判断用户是否属于指定的org
	 * 
	 * @return
	 */
	private String isUserBelongToSpecifiedOrg(String orgName) {
		if (ThreadVariable.getOrganization() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		List<String> yuhOrgInternaCodes = organizationDubboService
				.findOrganizationInternaCodeSpecifiedOrgName(orgName);
		String orgTemp = organizationDubboService
				.getOrgInternalCodeById(ThreadVariable.getOrganization()
						.getId());

		if (!StringUtil.isStringAvaliable(orgTemp)) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		for (String yuhOrgInternaCode : yuhOrgInternaCodes) {
			if (orgTemp.startsWith(yuhOrgInternaCode)) {
				bool = true;
				break;
			}
		}
		return SUCCESS;
	}

	/**
	 * 判断用户是否属于指定的org
	 * 
	 * @return
	 */
	private boolean isBelongToSpecifiedOrg(String orgName) {
		bool = false;
		if (ThreadVariable.getOrganization() == null) {
			return false;

		}
		List<String> yuhOrgInternaCodes = organizationDubboService
				.findOrganizationInternaCodeSpecifiedOrgName(orgName);
		String orgTemp = organizationDubboService
				.getOrgInternalCodeById(ThreadVariable.getOrganization()
						.getId());

		if (!StringUtil.isStringAvaliable(orgTemp)) {
			return false;
		}
		for (String yuhOrgInternaCode : yuhOrgInternaCodes) {
			if (orgTemp.startsWith(yuhOrgInternaCode)) {
				bool = true;
				break;
			}
		}
		return bool;
	}

	public String isProvince() throws Exception {
		if (ThreadVariable.getOrganization() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization orgTemp = organizationDubboService
				.getFullOrgById(ThreadVariable.getOrganization().getId());
		if (orgTemp == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		if (OrganizationLevel.PROVINCE > orgTemp.getOrgLevel().getInternalId()
				&& orgTemp.getOrgLevel().getInternalId() >= OrganizationLevel.VILLAGE) {
			bool = true;
		} else {
			bool = false;
		}
		return SUCCESS;
	}

	/** 余杭迁移新增 2013-08-15 */
	public String isVillageOrg() throws Exception {
		if (ThreadVariable.getOrganization() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization orgTemp = organizationDubboService
				.getFullOrgById(ThreadVariable.getOrganization().getId());
		if (orgTemp == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		if (OrganizationLevel.VILLAGE == orgTemp.getOrgLevel().getInternalId()) {
			bool = true;
		} else {
			bool = false;
		}
		return SUCCESS;
	}

	public String getCurrentSession() {
		session = ThreadVariable.getSession();
		return SUCCESS;
	}

	public String deleteSession() {
		sessionManagerDubboService.deleteSessionBySessionId(sessionId);
		return SUCCESS;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private String sessionId;
	public String loginname;

	@Autowired
	private SessionManagerDubboService sessionManagerDubboService;

	@Autowired
	private PermissionService permissionService;

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	/**
	 * 显示页
	 */
	private Integer page = 1;
	/**
	 * 角色grid的数据源
	 */
	private GridPage gridPage;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public GridPage getGridPage() {
		return gridPage;
	}

	public void setGridPage(GridPage gridPage) {
		this.gridPage = gridPage;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	/**
	 * 排序的字段名
	 */
	private String sidx;

	/**
	 * 排序的顺序
	 */
	private String sord;

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 一页行数
	 */
	private Integer rows = 15;

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public CurrentLoginInfoVo getCurrentLoginInfoVo() {
		return currentLoginInfoVo;
	}

	public void setCurrentLoginInfoVo(CurrentLoginInfoVo currentLoginInfoVo) {
		this.currentLoginInfoVo = currentLoginInfoVo;
	}

	public Boolean getBool() {
		return bool;
	}

	public void setBool(Boolean bool) {
		this.bool = bool;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public String getPasswordOutTime() {
		return passwordOutTime;
	}

	public void setPasswordOutTime(String passwordOutTime) {
		this.passwordOutTime = passwordOutTime;
	}
}
