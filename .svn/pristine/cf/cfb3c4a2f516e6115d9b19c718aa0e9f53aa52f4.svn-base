package com.tianque.component;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.tianque.userAuth.api.SessionManagerDubboService;

public class SessionTimeOutScheduling extends QuartzJobBean {
	private static Logger logger = LoggerFactory
			.getLogger(SessionTimeOutScheduling.class);

	public void deleteTimeOutSessions() {
		sessionManagerDubboService.deleteSessionsWhenTimeOut();
	}

	private ApplicationContext applicationContext;
	private SessionManagerDubboService sessionManagerDubboService;

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		try {
			if (applicationContext == null) {
				SchedulerContext schedulerContext = context.getScheduler()
						.getContext();
				applicationContext = (ApplicationContext) schedulerContext
						.get("applicationContext");
			}
			sessionManagerDubboService = (SessionManagerDubboService) applicationContext
					.getBean("sessionManagerDubboService");
			deleteTimeOutSessions();
		} catch (Exception e) {
			logger.error("SessionTimeOut job出错", e);
		}
	}
}
