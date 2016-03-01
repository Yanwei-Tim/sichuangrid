package com.tianque.job.realization;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.buildDatas.service.BuilddataStatService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.job.JobHelper;
import com.tianque.plugin.analysisNew.service.BuilddataStatNewService;
import com.tianque.plugin.analysisNew.util.AnalyseUtilNew;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @author hxpwangyi@163.com
 * @date 2013-3-26
 */
@Component("builddataJob")
public class BuilddataJob implements Job {
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private BuilddataStatService builddataStatService;
	@Autowired
	private BuilddataStatNewService builddataStatNewService;

	public void statisticBuilddata() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			int year = CalendarUtil.getLastMonthYearValue();
			int month = CalendarUtil.getLastMonth();
			if (AnalyseUtilNew.IS_NEWANALYSE_JOB) {
				builddataStatNewService.addBuilddataStat(year, month);
			} else {
				builddataStatService.addBuilddataStat(year, month);
			}
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行BuilddataJob", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"BuilddataJob出现异常：" + e.getMessage(), false);
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		statisticBuilddata();
	}
}
