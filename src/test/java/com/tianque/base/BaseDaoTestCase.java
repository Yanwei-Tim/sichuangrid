package com.tianque.base;

import net.rubyeye.xmemcached.MemcachedClient;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.init.ContextType;
import com.tianque.init.impl.CreateSessionForTestInitialization;
import com.tianque.util.ApplicationContextFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml",
		"classpath:applicationContext.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public abstract class BaseDaoTestCase {
	final static Logger logger = LoggerFactory.getLogger(BaseDaoTestCase.class);

	@Autowired
	private MemcachedClient memcachedClient;

	@Qualifier("dataSource")
	@Autowired
	private javax.sql.DataSource dataSource;

	@Before
	public void setup() throws Exception {
		try {
			logger.error("BaseDaoTestCase:before start");
			TestDatabaseInit.init(dataSource);
			memcachedClient.flushAll();
			new CreateSessionForTestInitialization().init();
			logger.error("BaseDaoTestCase:before end");
		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
		}
	}

	@BeforeClass
	public static void beforeClass() throws Exception {

		logger.error("BaseDaoTestCase:beforeClass start");
		try {
			boolean dontInitDatabase = Boolean.parseBoolean(System.getProperty("dontInitDatabase"));
			if (!dontInitDatabase) {
				TestDatabaseInit.rebuildDatabase();
				ApplicationContextFactory.getInstance().close(ContextType.test);
				System.setProperty("dontInitDatabase", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.error("BaseDaoTestCase:beforeClass end");

	}
}