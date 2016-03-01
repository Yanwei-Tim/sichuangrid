package com.tianque.statAnalyse.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.service.IssueAnalysisChartService;
import com.tianque.sysadmin.service.JobMonitorService;

@Component("issueAnalysisChartJob")
public class IssueAnalysisChartJob implements Job {
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private IssueAnalysisChartService issueAnalysisChartService;

	public void createIssueAnalysisChartData() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();

			issueAnalysisChartService.createIssueAnalysisChartData();

			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行数据统计历史数据job", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"数据统计历史数据job出现异常：" + e.getMessage(), false);
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		createIssueAnalysisChartData();
	}

}
