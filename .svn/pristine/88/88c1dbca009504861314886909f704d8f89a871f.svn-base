package com.tianque.job;

import java.util.UUID;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.GlobalValue;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;

public class SessionHelper {

	public static void createMockAdminSession() {

		createMockSessionByUserName("admin", "超级管理员");
	}

	public static void createMockSessionByUserName(String userName,
			String realName) {
		Session session = new Session();
		session.setUserName(userName);
		session.setUserRealName(realName);
		session.setAccessIp("127.0.0.1");
		session.setAccessTime(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		session.setLoginDate(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		session.setLogin(true);
		session.setUserId(1L);
		session.setSessionId(GlobalValue.JOB_COOKIE
				+ UUID.randomUUID().toString());//指定值及调用dubbo服务时出现session失效
		Organization org = new Organization();
		org.setId(1L);
		org.setOrgInternalCode("1.");
		session.setOrganization(org);
		session.setOrgInternalCode(org.getOrgInternalCode());
		User user = new User();
		ThreadVariable.setUser(user);
		user.setOrganization(org);
		ThreadVariable.setSession(session);
	}

	public static void createMockSessionByUserNameAndOrg(String userName,
			String realName, Organization organization) {
		Session session = new Session();
		session.setUserName(userName);
		session.setUserRealName(realName);
		session.setAccessIp("127.0.0.1");
		session.setAccessTime(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		session.setLoginDate(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		session.setLogin(true);
		session.setUserId(1L);
		session.setSessionId(GlobalValue.JOB_COOKIE
				+ UUID.randomUUID().toString());
		session.setOrganization(organization);
		session.setOrgInternalCode(organization.getOrgInternalCode());
		User user = new User();
		ThreadVariable.setUser(user);
		user.setOrganization(organization);
		ThreadVariable.setSession(session);
	}
}
