package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.job.JobHelper;
import com.tianque.job.realization.CorrectionExpire;
import com.tianque.plugin.analyzing.service.BaseSituationStatementService;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.PermissionService;

/**
 * @Description:基本信息统计报表job
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-10 上午01:22:45
 */
@Component("basesituationStatementStatisticsDispatch")
public class BasesituationStatementStatisticsDispatch implements Serializable {
	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private BaseSituationStatementService baseSituationStatementService;

	public void addBasesituationStatementStatistics() {
		logger.error("基本信息报表统计job开始执行");
		// 根据当前时间获取上个月所在的年份和月份
		int year = CalendarUtil.getLastYearByMonth();
		int month = CalendarUtil.getLastMonthByMonth();
		long startTime = System.currentTimeMillis();
		JobHelper.createMockAdminSession();
		ThreadVariable.setUser(permissionService.findUserByUserName("admin"));
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			baseSituationStatementService.addBaseStituationStatementHistory(
					year, month);
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行基本信息报表统计job！", true);
		} catch (Exception e) {
			logger.error("基本信息报表统计job执行错误：", e);
			jobMonitorService.updateJobMonitor(id,
					"基本信息报表统计job执行错误:" + e.getMessage(), false);
		}
	}
}
