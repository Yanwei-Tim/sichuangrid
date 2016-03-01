package com.tianque.job.realization;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.sysadmin.service.JobMonitorService;

@Component("newEconomic")
public class NewEconomic implements Job {
	// private NewEconomicStatService newEconomicStatService;
	@Autowired
	private JobMonitorService jobMonitorService;

	public void statisticsMonthMessage() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			// newEconomicStatService.addEconomicStat();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行NewEconomic", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"服务NewEconomic出现异常：" + e.getMessage(), false);
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		statisticsMonthMessage();
	}
}
