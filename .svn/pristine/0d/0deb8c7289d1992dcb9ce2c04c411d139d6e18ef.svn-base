package com.tianque.job;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.service.IssueTypeStanalService;
import com.tianque.sysadmin.service.JobMonitorService;

@Component("issueTypeStanalJob")
public class IssueTypeStanalJob implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IssueTypeStanalService issueTypeStanalService;
	@Autowired
	private JobMonitorService jobMonitorService;

	/**
	 * 服务办事类别月报统计
	 */
	public void addMonthIssueTypeStanals() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			// issueTypeStanalService.addMonthIssueTypeStanals(CalendarUtil.getLastMonthYearValue(),
			// CalendarUtil.getLastMonth());
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行服务办事类别月报统计job", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"服务办事类别月报统计job出现异常：" + e.getMessage(), false);
		}
	}

}
