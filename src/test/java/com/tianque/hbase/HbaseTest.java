package com.tianque.hbase;

import java.sql.SQLException;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.systemOperateLog.dao.SystemOperateLogDao;
import com.tianque.systemOperateLog.domain.SystemOperateLog;
import com.tianque.systemOperateLog.service.SystemOperateLogService;
import com.tianque.systemOperateLog.vo.SystemOperateLogVo;

public class HbaseTest {

	private SystemOperateLogService systemOperateLogService;
	private HbaseDataSource dataSourceHbase;
	private SystemOperateLogDao systemOperateLogHbaseDao;
	private SystemOperateLogDao systemOperateLogDao;

	@Before
	public void setUp() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext-development.xml",
						"applicationContext.xml" });
		createSession();
		systemOperateLogService = (SystemOperateLogService) context
				.getBean("systemOperateLogService");
		systemOperateLogHbaseDao = (SystemOperateLogDao) context
				.getBean("systemOperateLogHbaseDao");
		systemOperateLogDao = (SystemOperateLogDao) context
				.getBean("systemOperateLogDao");
		dataSourceHbase = (HbaseDataSource) context.getBean("dataSourceHbase");
	}

	public static void main(String[] args) throws Exception {
		HbaseTest hbaseTest = new HbaseTest();
		hbaseTest.setUp();
		for (int i = 0; i < 3; i++) {
			hbaseTest.new InsertHbaseThread("thread" + i).start();
		}
		// hbaseTest.new InsertHbaseThread("AAAA").start();
		// hbaseTest.new InsertHbaseThread("BBBB").start();

		// hbaseTest.testQuery();
	}

	@Test
	public void testConnection() throws SQLException {
		for (int i = 0; i < 10; i++) {
			System.out.println(dataSourceHbase.getConnection());
		}
	}

	@Test
	public void testInsertToHbase() throws SQLException {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			SystemOperateLog systemOperateLog = systemOperateLogHbaseDao
					.addSystemOperateLog(createSystemOperateLog());
			System.out.println(i);
		}
		System.out.println("总共耗时：" + (System.currentTimeMillis() - start));
	}

	@Test
	public void testQuery() throws SQLException {
		SystemOperateLogVo systemOperateLogVo = new SystemOperateLogVo();
		systemOperateLogVo.setDataKeyword("110106198801301950");
		// systemOperateLogVo.setDataKeyword("aaaaa");
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			// System.out.println(i);
			PageInfo<SystemOperateLog> pageInfo = systemOperateLogService
					.findAllSystemLogsForPage(systemOperateLogVo, 1, 20, "", "");
			System.out.println(pageInfo.getResult().size());
		}
		System.out.println("耗时" + (System.currentTimeMillis() - start));
	}

	private SystemOperateLog createSystemOperateLog() {
		return systemOperateLogDao.getSystemOperateLogById(88033978L);
	}

	private void createSession() {
		Session session = new Session();
		session.setUserName("admin");
		session.setUserRealName("超级管理员");
		session.setAccessIp("127.0.0.1");
		session.setAccessTime(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
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
		// session = getSessionDao().addSession(session);
		User user = new User();
		user.setId(1L);
		user.setUserName("admin");
		user.setOrganization(org);
		ThreadVariable.setUser(user);
		ThreadVariable.setSession(session);
	}

	public class InsertHbaseThread extends Thread {
		String name = "";

		public InsertHbaseThread(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			createSession();
			for (int i = 0; i < 50000; i++) {
				SystemOperateLog systemOperateLog = systemOperateLogService
						.addSystemOperateLog(createSystemOperateLog());
				System.out.println("线程" + name + "新增数据，id为："
						+ systemOperateLog.getId() + "数量：" + i);

			}
		}
	}

}
