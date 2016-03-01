package com.tianque.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.userAuth.api.SessionManagerDubboService;

@Controller("singleLoginController")
@SuppressWarnings("serial")
@Scope("prototype")
@Transactional
public class SingleLoginController extends BaseAction implements
		ServletRequestAware {
	private final static String FILEPERMISSION_ACCESSKEY = "f1b09eea8d7e69f7fae3d14f37ac82f1";
	private final static String FILEPERMISSION_VIEW_ENAME = "viewFile";
	private final static String FILEPERMISSION_EDIT_ENAME = "editFile";
	private final static String FILEPERMISSION_MANAGE_ENAME = "manageFile";

	@Autowired
	private PermissionService permissioinDubboService;
	@Autowired
	private SessionManagerDubboService sessionManagerDubboService;

	private Map<String, Object> filePermissionVo;
	private String sid;
	private String accessKey;

	private HttpServletRequest request;

	public String filePermission() {
		filePermissionVo = new HashMap<String, Object>();
		if (sid == null || "".equals(sid.trim()) || accessKey == null
				|| "".equals(accessKey.trim())) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		if (!FILEPERMISSION_ACCESSKEY.equals(accessKey)) {
			this.errorMessage = "未经过授权!";
			return ERROR;
		}
		Session session = sessionManagerDubboService
				.findSessionBySessionId(sid);
		if (session == null) {
			this.errorMessage = "未登录或登录超时!";
			return ERROR;
		} else if (!session.isLogin()) {
			this.errorMessage = "你的帐号已经在别的地方登录！";
			return ERROR;
		}
		processFilePermission(session);
		return SUCCESS;
	}

	private void processFilePermission(Session session) {
		filePermissionVo.put("logined", true);
		User user = permissioinDubboService.getSimpleUserById(session
				.getUserId());
		if (user != null) {
			filePermissionVo.put("internalCode", user.getOrgInternalCode());
			filePermissionVo.put("isAdmin", user.isAdmin());
			filePermissionVo.put("view", false);
			filePermissionVo.put("edit", false);
			filePermissionVo.put("isManager", false);
			if (permissioinDubboService.isUserHasPermission(user.getId(),
					FILEPERMISSION_VIEW_ENAME))
				filePermissionVo.put("view", true);
			if (permissioinDubboService.isUserHasPermission(user.getId(),
					FILEPERMISSION_EDIT_ENAME))
				filePermissionVo.put("edit", true);
			if (permissioinDubboService.isUserHasPermission(user.getId(),
					FILEPERMISSION_MANAGE_ENAME))
				filePermissionVo.put("isManager", true);

			// sessionManager.updateSessionAccessTimeBySessionId(session.getSessionId(),
			// Calendar
			// .getInstance().getTime(), request.getRequestURI(),
			// request.getRemoteAddr());
		}
	}

	public Map<String, Object> getFilePermissionVo() {
		return filePermissionVo;
	}

	public void setFilePermissionVo(Map<String, Object> filePermissionVo) {
		this.filePermissionVo = filePermissionVo;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
