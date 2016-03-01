package com.tianque.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.plugin.analysisNew.service.StatisticsPopulationNewService;
import com.tianque.plugin.analysisNew.util.AnalyseUtilNew;
import com.tianque.plugin.analyzing.service.StatisticsPopulationService;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * 青少年自动统计job
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月1日
 */
@Component("automaticYouthPopulationStat")
public class AutomaticYouthPopulationStat implements Job {

	private final static Logger logger = LoggerFactory
			.getLogger(AutomaticYouthPopulationStat.class);

	@Autowired
	private StatisticsPopulationService populationService;
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private StatisticsPopulationNewService populationNewService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		statisticsMonthMessage();
	}

	/**
	 * 青少年人员自动统计类型图数据
	 */
	public void statisticsMonthMessage() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			int year = CalendarUtil.getLastMonthYearValue();
			int month = CalendarUtil.getLastMonth();
			if (AnalyseUtilNew.IS_NEWANALYSE_JOB) {
				populationNewService.addYouthPopulationStat(year, month);
			} else {
				populationService.addYouthPopulationStat(year, month);
			}
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "自动统计青少年类型图数据job", true);
		} catch (Exception e) {
			logger.error("", e);
			jobMonitorService.updateJobMonitor(id,
					"自动统计青少年类型图数据job出现异常：" + e.getMessage(), false);
		}
	}
}
