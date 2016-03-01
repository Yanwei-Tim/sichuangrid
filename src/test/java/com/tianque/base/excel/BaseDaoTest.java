package com.tianque.base.excel;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.cache.util.MemCachedManage;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.util.XlsDataSetBeanFactory;

/**
 * @描述:
 * @版权:
 * @公司:
 * @作者:王熙斌
 * @创建时间：2012-11-27 下午4:19:16
 **/
@RunWith(UnitilsJUnit4TestClassRunner.class)
@SpringApplicationContext({ "classpath:applicationContext-test.xml" })
@Transactional(TransactionMode.ROLLBACK)
public abstract class BaseDaoTest<T extends BaseDomain, DAO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private Class<T> entityClass;
	private Class<DAO> daoClass;

	protected DAO dao;

	@SpringBeanByType
	private MemCachedManage cacheClient;

	@Before
	public void setDao() {
		dao = getDao();
	}

	public abstract DAO getDao();

	public BaseDaoTest() {
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
			this.daoClass = (Class<DAO>) parameterizedType[1];
		}
	}

	@Test
	@ExpectedDataSet
	public void addTest() throws Exception {
		List<T> ts = XlsDataSetBeanFactory.createBeans(this.getClass()
				.getSimpleName() + ".beans.xls",
				"add" + entityClass.getSimpleName() + "s", entityClass, this);

		Method method = addMethod("add" + entityClass.getSimpleName());
		for (T t : ts) {
			t = (T) method.invoke(dao, t);
			assertNotNull(t);
			assertNotNull(t.getId());
		}
	}

	@Test
	@DataSet
	public void deleteById() throws Exception {
		Method method = getMethodName("getSimple" + entityClass.getSimpleName()
				+ "ById");
		T t = (T) method.invoke(dao, 1L);
		assertNotNull(t);
		assertNotNull(t.getId());
		Method deleteMethod = deleteMethod("delete"
				+ entityClass.getSimpleName() + "ById");
		deleteMethod.invoke(dao, t.getId());
		t = (T) method.invoke(dao, t.getId());
		assertNull(t);
	}

	@Test
	@DataSet
	@ExpectedDataSet
	public void updateTest() throws Exception {
		Method method = getMethodName("getSimple" + entityClass.getSimpleName()
				+ "ById");
		T t = (T) method.invoke(dao, 1L);
		t = (T) XlsDataSetBeanFactory.createBean(this.getClass()
				.getSimpleName() + ".beans.xls",
				"update" + entityClass.getSimpleName() + "s", t, this);

		Method updateMethod = updteMethod("update"
				+ entityClass.getSimpleName());
		t = (T) updateMethod.invoke(dao, t);
	}

	private Method addMethod(String method) throws SecurityException,
			NoSuchMethodException {
		if (isMethodExists(method)) {
			return daoClass.getMethod("add" + entityClass.getSimpleName(),
					entityClass);
		} else {
			Method[] methods = daoClass.getMethods();
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName().equals("add")) {
					return methods[i];
				}
			}
			return null;
		}
	}

	private Method deleteMethod(String method) throws SecurityException,
			NoSuchMethodException {
		if (isMethodExists(method)) {
			return daoClass.getMethod("delete" + entityClass.getSimpleName()
					+ "ById", Long.class);
		} else {
			Method[] methods = daoClass.getMethods();
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName().equals("delete")) {
					return methods[i];
				}
			}
			return null;
		}
	}

	private Method updteMethod(String method) throws SecurityException,
			NoSuchMethodException {
		if (isMethodExists(method)) {
			return daoClass.getMethod("update" + entityClass.getSimpleName(),
					entityClass);
		} else {
			Method[] methods = daoClass.getMethods();
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName().equals("update")) {
					return methods[i];
				}
			}
			return null;
		}
	}

	private Method getMethodName(String method) throws SecurityException,
			NoSuchMethodException {
		if (isMethodExists(method)) {
			return daoClass.getMethod("getSimple" + entityClass.getSimpleName()
					+ "ById", Long.class);
		} else {
			Method[] methods = daoClass.getMethods();
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName().equals("get")) {
					return methods[i];
				}
			}
			return null;
		}
	}

	private boolean isMethodExists(String method) {
		Method[] methods = daoClass.getDeclaredMethods();
		for (Method m : methods) {
			if (method.equals(m.getName())) {
				return true;
			}
		}
		return false;
	}

	@Before
	public void beforeTest() {
		try {
			cacheClient.flushAll();
			createSession();
		} catch (Exception e) {
			logger.error("flush cache error:", e);
		}
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
		ThreadVariable.setUser(user);
		ThreadVariable.setOrganization(org);
		ThreadVariable.setSession(session);

	}
}
