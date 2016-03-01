package com.tianque.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.issue.service.IssueExtractionService;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @author n-235
 * 
 **** 
 * 
 */
@Component("issueExtractionJob")
public class IssueExtractionJob implements Job {

	@Autowired
	private IssueExtractionService extractionService;
	@Autowired
	private JobMonitorService jobMonitorService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		extractionIssues();
	}

	private void extractionIssues() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			extractionService.exectueExtractionIssue();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行已办结事件分离job", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"已办结事件分离job出现异常：" + e.getMessage(), false);
		}
	}
}
