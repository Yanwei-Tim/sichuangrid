package com.tianque.statAnalyse.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.statAnalyse.issueManage.listManage.service.IssueReportService;
import com.tianque.sysadmin.service.JobMonitorService;

@Component("issueReportStatJob")
public class IssueReportStatJob implements Job {
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private IssueReportService issueReportService;

	public void statIssueReport() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			issueReportService.statIssueReport();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行事件处理报表job", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"事件处理报表job出现异常：" + e.getMessage(), false);
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		statIssueReport();
	}

}
