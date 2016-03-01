package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.job.JobHelper;
import com.tianque.plugin.analyzing.service.PrimaryOrganizationsStatisticsService;
import com.tianque.service.util.PopulationType;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.PermissionService;

/**
 * @Description:组织机构数据统计job
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-11 上午09:34:45
 */
@Component("organizationAnalyzingDispatch")
public class OrganizationAnalyzingDispatch implements Serializable {
	private static Logger logger = LoggerFactory
			.getLogger(OrganizationAnalyzingDispatch.class);
	@Autowired
	private PrimaryOrganizationsStatisticsService primaryOrganizationsStatisticsService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private JobMonitorService jobMonitorService;

	public void addOrganizationHistory() {
		logger.error("组织机构数据统计JOB开始执行");
		// Calendar calendar = Calendar.getInstance();
		// int year = calendar.get(Calendar.YEAR);
		// int month = calendar.get(Calendar.MONTH);
		int year = CalendarUtil.getNowYear();
		int month = CalendarUtil.getNowMonth();
		long startTime = System.currentTimeMillis();
		JobHelper.createMockAdminSession();
		ThreadVariable.setUser(permissionService.findUserByUserName("admin"));
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			primaryOrganizationsStatisticsService.addPrimaryOrganizationsStat(
					PopulationType.PRIMARYORGANIZATIONS, year, month);
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行组织机构数据统计job！", true);
		} catch (Exception e) {
			logger.error("组织机构数据统计job执行错误：", e);
			jobMonitorService.updateJobMonitor(id,
					"组织机构数据统计job执行错误:" + e.getMessage(), false);
		}
	}
}
