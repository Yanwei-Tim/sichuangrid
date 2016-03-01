package com.tianque.component.interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.annotation.PermissionFilters;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.core.util.CookieUtil;
import com.tianque.core.util.GlobalValue;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Session;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.userAuth.api.SessionManagerDubboService;

public class PermissionInterceptor extends AbstractInterceptor {
	private final static Logger logger = LoggerFactory
			.getLogger(PermissionInterceptor.class);

	@Autowired
	private SessionManagerDubboService sessionManagerDubboService;
	@Autowired
	private PermissionService permissionService;

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		String action = ai.getProxy().getActionName();
		Class c = ai.getAction().getClass();
		Method method = c.getDeclaredMethod(ai.getProxy().getMethod());
		String className = c.getName();
		if (-1 != c.getName().indexOf('$')) {
			className = c.getName().substring(0, c.getName().indexOf('$'));
		}
		Map<String, Object> parameters = ai.getInvocationContext()
				.getParameters();
		List<String> orgCodeValue = decodeParameterValues(parameters);
		ai.getInvocationContext().setParameters(parameters);
		HttpServletRequest request = (HttpServletRequest) ai
				.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		String sessionId = request.getParameter(GlobalValue.LOGIN_SESSION_ID);
		Cookie[] cookies = request.getCookies();
		if (sessionId == null && cookies == null
				&& !isNotLoginValidate(request.getRequestURI(), request)) {//2015.11.10添加isNotLoginValidate解决第一次访问白名单url时sessionId和cookies都不存在，被判定为没权限
			return GlobalValue.NOT_HAVE_PERMISSION_RESULT;
		}
		if (cookies != null) {
			sessionId = CookieUtil.getSesssionIdFromCookies(request);
		}
		if (havePermission(
				Class.forName(className).getMethod(method.getName()),
				sessionId, action, orgCodeValue)) {
			return ai.invoke();
		} else {
			return GlobalValue.NOT_HAVE_PERMISSION_RESULT;
		}
	}

	private boolean isNotLoginValidate(String url, HttpServletRequest request) {
		for (String path : GlobalValue.IS_NOT_LOGIN_VALIDATE_PATH) {
			if (url.equals(request.getContextPath() + path)
					|| (url.endsWith("/login.jsp") && !url.endsWith("/index.jsp"))) {
				return true;
			}
		}
		return false;
	}

	public boolean havePermission(Method method, String sessionId,
			String action, List<String> orgCodeValue) {
		/*
		 * id加密的权限控制暂时去除 EncryptAnnotation encryptAnnotation = method
		 * .getAnnotation(EncryptAnnotation.class); if (orgCodeValue == null ||
		 * orgCodeValue.isEmpty()) { String tqmobile =
		 * request.getParameter("tqmobile"); if (!(tqmobile != null &&
		 * !"".equals(tqmobile) && tqmobile .equals("true")) &&
		 * encryptAnnotation != null &&
		 * encryptAnnotation.annotationType().getName()
		 * .equals(EncryptAnnotation.class.getName())) { return false; } } else
		 * if (!organizationFilter(orgCodeValue)) { return false; }
		 */
		PermissionFilter filter = method.getAnnotation(PermissionFilter.class);
		PermissionFilters filters = method
				.getAnnotation(PermissionFilters.class);
		if (filter == null && filters == null) {
			return true;
		}
		if (filters != null
				&& filters.annotationType().getName()
						.equals(PermissionFilters.class.getName())) {
			return permissionFilters(method, action, sessionId);
		} else if (filter != null
				&& filter.annotationType().getName()
						.equals(PermissionFilter.class.getName())) {
			return permissionFilter(method, sessionId);
		}
		return true;
	}

	private boolean permissionFilter(Method method, String sessionId) {
		logger.debug("into permissionFilter");
		PermissionFilter permission = method
				.getAnnotation(PermissionFilter.class);
		String ename = permission.ename();
		logger.debug("ename:={}", ename);
		if (isSessionHavePermission(sessionId, ename)) {
			logger.debug("ename:={}", true);
			return true;
		} else {
			logger.debug("ename:={}", false);
			return false;
		}
	}

	private boolean permissionFilters(Method method, String action,
			String sessionId) {
		logger.debug("into permissionFilters");
		PermissionFilters annotation = method
				.getAnnotation(PermissionFilters.class);
		PermissionFilter[] permissionAnnotations = annotation.value();
		for (int i = 0; i < permissionAnnotations.length; i++) {
			PermissionFilter permission = permissionAnnotations[i];
			String ename = permission.ename();
			String actionName = permission.actionName();
			if (action.equals(actionName)
					&& isSessionHavePermission(sessionId, ename)) {
				return true;
			}
		}
		return false;
	}

	private boolean isSessionHavePermission(String sessionId, String ename) {
		Session session = ThreadVariable.getSession();
		if (session == null || session.getUserId() == null)
			session = sessionManagerDubboService
					.findSessionBySessionId(sessionId);
		return session == null ? false : permissionService.isUserHasPermission(
				session.getUserId(), ename);
	}

	private List<String> decodeParameterValues(Map<String, Object> parameters) {
		if (parameters == null) {
			return null;
		}
		List<String> orgCodeValues = new ArrayList<String>();
		Set entrySet = parameters.entrySet();
		String[] strings = null;
		String[] values = null;
		int strLength = 0;
		for (Iterator it = entrySet.iterator(); it.hasNext();) {
			Entry entry = (Entry) it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof String[]) {
				values = (String[]) value;// 类型转换
				strLength = values.length;
				strings = new String[strLength];
				for (int i = 0; i < strLength; i++) {
					strings[i] = decodeIdValues(values[i], orgCodeValues);
				}
				parameters.put((String) key, strings);
			}
		}
		return orgCodeValues;
	}

	private String decodeIdValues(String arrayValue, List<String> orgCodeValues) {
		if (!StringUtil.isStringAvaliable(arrayValue)) {
			return arrayValue;
		}
		if (arrayValue
				.indexOf(BaseDomainIdEncryptUtil.DISCERN_ENCRYPT_CONSTANT) != -1) {
			Map decodeDomainId = BaseDomainIdEncryptUtil
					.decodeDomainId(arrayValue);
			if (decodeDomainId == null) {
				return arrayValue;
			}
			Object domainId = decodeDomainId
					.get(BaseDomainIdEncryptUtil.DOMIAN_ID_CONSTANT);
			if (domainId != null) {
				arrayValue = String.valueOf(domainId);
			}
			Object orgCode = decodeDomainId
					.get(BaseDomainIdEncryptUtil.ORG_CODE_CONSTANT);
			if (orgCode != null && !"".equals(orgCode)
					&& !"null".equals(orgCode)) {
				String[] orgCodes = String.valueOf(orgCode).split(",");
				orgCodeValues.addAll(Arrays.asList(orgCodes));
			}
		}
		return arrayValue;
	}
}