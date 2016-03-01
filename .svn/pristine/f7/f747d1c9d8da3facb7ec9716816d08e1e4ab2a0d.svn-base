package com.tianque.core.redis.test;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.tianque.dbrouter.RouterManager;
import com.tianque.dbrouter.factory.RouterManagerBeanFactory;
import com.tianque.job.JobHelper;

/**
 * @ClassName: BaseTest
 * @Description: 测试基类
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2015年1月8日 上午10:23:58
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext.xml",
		"classpath*:**/applicationContext-development.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	@Qualifier("zzGrid_main_ds")
	private DataSource dbDataSource;

	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dbDataSource);
	}

	static {
		RouterManager routerManager = RouterManagerBeanFactory.getManager();
		routerManager.addRouters("/dbRouter-development.xml");
	}

	@BeforeClass
	public static void beforClass() {
		JobHelper.createMockAdminSession();
	}

}
