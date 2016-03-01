package com.tianque.init.impl;

import org.quartz.impl.StdScheduler;

import com.tianque.init.ContextType;
import com.tianque.init.Initialization;
import com.tianque.util.ApplicationContextFactory;

public class StopClearSessionSchedulerInitialization implements Initialization {
	private ContextType contextType;

	public StopClearSessionSchedulerInitialization(ContextType type) {
		this.contextType = type;
	}

	@Override
	public void init() throws Exception {
		StdScheduler scheduler = (StdScheduler) ApplicationContextFactory.getInstance()
				.getApplicationContext(contextType).getBean("schedulerFactoryBean");
		scheduler.shutdown(true);
	}

}
