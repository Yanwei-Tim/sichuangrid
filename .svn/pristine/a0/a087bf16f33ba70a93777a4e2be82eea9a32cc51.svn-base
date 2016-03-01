package com.tianque.init;

import com.tianque.core.cache.util.MemCachedManage;
import com.tianque.init.impl.CreateSessionForTestInitialization;
import com.tianque.init.impl.DatabaseInitialization;
import com.tianque.init.impl.StopClearSessionSchedulerInitialization;
import com.tianque.util.ApplicationContextFactory;

public class DaoTestEnvironmentBuilder extends InitializationsRunner {

	public DaoTestEnvironmentBuilder() throws Exception {
		new DatabaseInitialization(ContextType.test).init();
		getXMemcachedClient().flushAll();
		addInitialization(new StopClearSessionSchedulerInitialization(
				ContextType.test));
		addInitialization(new CreateSessionForTestInitialization());
	}

	public void builderTestEnv() throws Exception {
		executeInitialization();
		logger.info("Dao测试环境初始化结束!");
	}

	private MemCachedManage getXMemcachedClient() {
		return (MemCachedManage) ApplicationContextFactory.getInstance()
				.getBean(ContextType.test, "memCachedManage");
	}
}
