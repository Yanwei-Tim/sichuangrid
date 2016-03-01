package com.tianque.job.realization;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.job.SessionHelper;
import com.tianque.plugin.analyzing.service.LoginManageService;
import com.tianque.sysadmin.service.JobMonitorService;

@Component("loginManageJob")
public class LoginManageJob implements Job {
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private LoginManageService loginManageService;

	private static Logger logger = LoggerFactory
			.getLogger(LoginManageJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		addMonthLoginManageJob();
	}

	/**
	 * 用户登录情况月统计
	 */
	private void addMonthLoginManageJob() {
		SessionHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(LoginManageJob.class
				.getName());
		long startTime = System.currentTimeMillis();
		try {
			loginManageService.addLoginManageJob(CalendarUtil.getNowYear(),
					CalendarUtil.getNowMonth());

			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行登录统计job", true);
		} catch (Exception ex) {
			jobMonitorService.updateJobMonitor(id,
					"执行登录统计job出现异常：" + ex.getMessage(), false);
			logger.error("", ex);
		}

	}
}
