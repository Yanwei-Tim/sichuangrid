package com.tianque.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.plugin.analysisNew.service.BaseinfoStatisticNewService;
import com.tianque.plugin.analysisNew.util.AnalyseUtilNew;
import com.tianque.plugin.analyzing.service.BaseinfoStatisticService;
import com.tianque.sysadmin.service.JobMonitorService;

@Component("automaticAttentionPopulationStat")
public class AutomaticAttentionPopulationStat implements Job {
	@Autowired
	private BaseinfoStatisticService baseinfoStatisticService;
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private BaseinfoStatisticNewService baseinfoStatisticNewService;

	/**
	 * 自动统计趋势图数据
	 */
	public void statisticsMonthMessage() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			if (AnalyseUtilNew.IS_NEWANALYSE_JOB) {
				baseinfoStatisticNewService.generateHistoryStatistic();
			} else {
				baseinfoStatisticService.generateHistoryStatistic();
			}
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "自动统计趋势图数据job", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"自动统计趋势图数据job出现异常：" + e.getMessage(), false);
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		statisticsMonthMessage();

	}
}
