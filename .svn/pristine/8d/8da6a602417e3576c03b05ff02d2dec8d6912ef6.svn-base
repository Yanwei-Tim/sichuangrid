package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.service.IssueAnalysisChartService;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:事件按照大类小类维度统计数量Job
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-10 下午11:13:45
 */
@Component("issueAnalysisChartDispatch")
public class IssueAnalysisChartDispatch implements Serializable {
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
}
