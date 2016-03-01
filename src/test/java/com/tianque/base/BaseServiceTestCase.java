package com.tianque.base;

import net.rubyeye.xmemcached.MemcachedClient;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.ThreadVariable;
import com.tianque.init.impl.AlterUserIdInThreadVariable;
import com.tianque.init.impl.CreateSessionForTestInitialization;
import com.tianque.init.impl.IssueTypeDomainsInitialization;
import com.tianque.init.impl.OrganizationInitialization;
import com.tianque.init.impl.PermissionXmlInit;
import com.tianque.init.impl.SystemPropertiesInitialization;
import com.tianque.init.impl.UserRoleInitialization;
import com.tianque.service.IssueTypeDomainService;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.sysadmin.service.PropertyDomainService;
import com.tianque.sysadmin.service.PermissionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public abstract class BaseServiceTestCase {
	public final static Logger logger = LoggerFactory
			.getLogger(BaseServiceTestCase.class);

	@Autowired
	private MemcachedClient memcachedClient;
	@Autowired
	private PropertyDomainService propertyDomainService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private javax.sql.DataSource dataSource;
	@Autowired
	private IssueTypeDomainService typeDomainService;
	@Autowired
	private IssueTypeService typeService;

	@Before
	public void setup() throws Exception {
		logger.info("BaseServiceTestCase:beforeMethod start");
		ThreadVariable.clearThreadVariable();
		try {
			long startTime = System.currentTimeMillis();
			TestDatabaseInit.init(dataSource);
			long endTime = System.currentTimeMillis();
			logger.info("rebuild耗时：" + (endTime - startTime));
			memcachedClient.flushAll();
			new CreateSessionForTestInitialization().init();
			new SystemPropertiesInitialization(propertyDomainService,
					propertyDictService).init();
			new PermissionXmlInit(permissionService).init();
			new OrganizationInitialization(organizationDubboService,
					propertyDictService).init();
			new UserRoleInitialization(permissionService,
					organizationDubboService, propertyDictService).init();
			new IssueTypeDomainsInitialization(typeDomainService, typeService)
					.init();
			new AlterUserIdInThreadVariable(permissionService,
					organizationDubboService).init();
		} catch (Exception e) {
			logger.error("异常信息", e);
		}
		logger.info("BaseServiceTestCase:beforeMethod end");
	}

	@BeforeClass
	public static void beforeClass() throws Exception {
		logger.info("BaseServiceTestCase:beforeClass start");
		boolean dontInitDatabase = Boolean.parseBoolean(System
				.getProperty("dontInitDatabase"));
		if (!dontInitDatabase) {
			TestDatabaseInit.rebuildDatabase();
			System.setProperty("dontInitDatabase", "true");
		}
		logger.info("BaseServiceTestCase:beforeClass end");
	}
}
