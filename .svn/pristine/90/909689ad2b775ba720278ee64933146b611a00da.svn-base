package com.tianque.base.excel;

import java.util.UUID;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.unitils.UnitilsJUnit4;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;

/**
 * @描述:
 * @版权:
 * @公司:
 * @作者:王熙斌
 * @创建时间：2012-11-27 下午4:19:16
 **/
@RunWith(MockitoJUnitRunner.class)
public abstract class BaseServiceTest extends UnitilsJUnit4 {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before
	public void beforeTest() throws Exception {
		createSession();
	}

	private void createSession() throws Exception {
		Session session = new Session();
		session.setUserName("admin");
		session.setUserRealName("超级管理员");
		session.setAccessIp("127.0.0.1");
		session.setAccessTime(com.tianque.core.util.CalendarUtil
				.now("yyyy-MM-dd HH:mm:ss"));
		session.setLoginDate(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		session.setLoginDate(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		session.setLogin(true);
		session.setUserId(1L);
		session.setSessionId(UUID.randomUUID().toString());
		Organization org = new Organization();
		org.setId(1L);
		org.setOrgInternalCode("1.1.");
		session.setOrganization(org);
		session.setOrgInternalCode("1.1.");
		User user = new User();
		user.setOrganization(org);
		ThreadVariable.setOrganization(org);
		ThreadVariable.setUser(user);
		ThreadVariable.setSession(session);
	}
}
