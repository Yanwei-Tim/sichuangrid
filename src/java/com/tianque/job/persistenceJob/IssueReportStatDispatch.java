package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.statAnalyse.issueManage.listManage.service.IssueReportService;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:事件统计列表数据job
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-10 下午11:40:40
 */
@Component("issueReportStatDispatch")
public class IssueReportStatDispatch implements Serializable {
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
}
