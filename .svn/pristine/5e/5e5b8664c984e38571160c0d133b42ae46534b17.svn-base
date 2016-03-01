package com.tianque.job;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.service.StatisticsTendencyChartService;
import com.tianque.sysadmin.service.JobMonitorService;

@SuppressWarnings("serial")
@Component("automaticStatisticsTendencyChart")
/**
 * 重点人员和以前的重点单位的月统计现在没有在用，现在用baseInfoStatistic表里面的数据
 */
public class AutomaticStatisticsTendencyChart implements Serializable {
	@Autowired
	private StatisticsTendencyChartService statisticsTendencyChartService;
	@Autowired
	private JobMonitorService jobMonitorService;

	/**
	 * 自动统计趋势图数据
	 */
	public void statisticsMonthMessage() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			statisticsTendencyChartService.statisticsMonthMessage();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "自动统计趋势图数据job", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"自动统计趋势图数据job出现异常：" + e.getMessage(), false);
		}
	}

}
