/*   
 * Copyright (c) 2014-2020 TianQue Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * TianQue. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with TianQue.
 *   
 */
package com.tianque.core.dubbo.filter;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.exception.base.IllegalOperationException;
import com.tianque.init.impl.CreateSessionForTestInitialization;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.userAuth.api.SessionManagerDubboService;

/**
 * @ClassName: DubboRPCFilter
 * @Description: dubbo请求过滤器
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2015年3月9日 下午4:02:57
 */
public class DubboRPCFilter implements Filter {

	private static final String DUBBO_URL_PREFIX = "dubbo:";
	private static final String INIT_APP_COOKIE = "INIT_APP";

	private static SessionManagerDubboService sessionManagerDubboService;
	private static PermissionDubboService permissionDubboService;

	public static String[] whiteList = GridProperties.DUBBO_WHITE_LIST
			.split(";");

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {
		ThreadVariable.clearThreadVariable();
		String accessUrl = invoker.getUrl().toString();
		String url = invoker.getInterface().getName() + "."
				+ invocation.getMethodName();
		if (!accessUrl.startsWith(DUBBO_URL_PREFIX) || isNeedFilter(url)) {
			return getResult(invoker, invocation);
		}
		String cookie = invocation.getAttachment("cookie");
		if (!StringUtil.isStringAvaliable(cookie)) {
			throw new IllegalOperationException("非法操作，cookie 不存在！URL[" + url
					+ "]");
		}
		if (INIT_APP_COOKIE.equals(cookie)) {
			try {
				new CreateSessionForTestInitialization().init();
			} catch (Exception e) {
				throw new IllegalOperationException("创建初始化session失败！URL[" + url
						+ "]");
			}
			return getResult(invoker, invocation);
		}
		Session session = getSessionManagerDubboService()
				.findSessionBySessionId(cookie);
		if (session == null) {
			throw new IllegalOperationException("登录身份信息失效，请重新登录！URL[" + url
					+ "]");
		}
		session.setAccessTime(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		User user = getPermissionDubboService().getSimpleUserById(
				session.getUserId());
		user.setOrganization(session.getOrganization());
		ThreadVariable.setUser(user);
		ThreadVariable.setOrganization(session.getOrganization());
		ThreadVariable.setSession(session);
		return getResult(invoker, invocation);
	}

	private Result getResult(Invoker<?> invoker, Invocation invocation) {
		return invoker.invoke(invocation);
	}

	private boolean isNeedFilter(String url) {
		for (String witeName : whiteList) {
			if (url.trim().equals(witeName)) {
				return true;
			}
		}
		return false;
	}

	private static SessionManagerDubboService getSessionManagerDubboService() {
		if (sessionManagerDubboService == null) {
			sessionManagerDubboService = (SessionManagerDubboService) SpringBeanUtil
					.getBeanFromSpringByBeanName("sessionManagerDubboService");
		}
		return sessionManagerDubboService;
	}

	private static PermissionDubboService getPermissionDubboService() {
		if (permissionDubboService == null) {
			permissionDubboService = (PermissionDubboService) SpringBeanUtil
					.getBeanFromSpringByBeanName("permissionDubboService");
		}
		return permissionDubboService;
	}
}
