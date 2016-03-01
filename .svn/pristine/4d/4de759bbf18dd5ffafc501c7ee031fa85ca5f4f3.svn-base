package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.buildDatas.service.BuilddataStatService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.job.JobHelper;
import com.tianque.plugin.analysisNew.service.BuilddataStatNewService;
import com.tianque.plugin.analysisNew.util.AnalyseUtilNew;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:楼宇历史数据job
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-10 上午01:25:53
 */
@Component("builddataDispatch")
public class BuilddataDispatch implements Serializable {
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

}
