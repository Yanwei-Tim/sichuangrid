package com.tianque.job.realization;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.plugin.analyzing.service.ActualCompanyStatService;
import com.tianque.sysadmin.service.JobMonitorService;

@Component("actualCompanyJob")
public class ActualCompanyJob implements Job {
	private ActualCompanyStatService companyService;
	@Autowired
	private JobMonitorService jobMonitorService;

	public void statisticsMonthMessage() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			companyService.addCompanyStat();
			jobMonitorService.updateJobMonitor(id, "花了" + (System.currentTimeMillis() - startTime)
					+ "执行ActualCompanyJob", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id, "ActualCompanyJob出现异常：" + e.getMessage(), false);
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		statisticsMonthMessage();
	}

}
