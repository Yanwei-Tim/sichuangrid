package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.issue.service.IssueExtractionService;
import com.tianque.job.JobHelper;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @author n-235 已办结事件分离job
 **** 
 * 
 */
@Component("issueExtractionDispatch")
public class IssueExtractionDispatch implements Serializable {

	@Autowired
	private IssueExtractionService extractionService;
	@Autowired
	private JobMonitorService jobMonitorService;

	public void extractionIssues() {
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
