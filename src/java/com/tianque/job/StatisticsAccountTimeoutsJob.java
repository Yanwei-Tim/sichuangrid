package com.tianque.job;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.GridProperties;
import com.tianque.job.realization.CorrectionExpire;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.xichang.working.statisticsAccountTimeout.service.StatisticsAccountTimeoutsJobHelper;

/**
 * 时限考核成绩job
 * */
@Component("statisticsAccountTimeoutsJob")
public class StatisticsAccountTimeoutsJob implements Job {
	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private StatisticsAccountTimeoutsJobHelper statisticsAccountTimeoutsJobHelper;

	public void createStatisticsAccountTimeoutData() {
		if (GridProperties.ISSCHEDULE)
			return;
		List<Long> idModList = new ArrayList<Long>();
		idModList.add(0l);
		idModList.add(1l);
		String taskParameter = "1";
		addProvinceAndCityStatisticsAccountTimeoutData(idModList, taskParameter);
	}

	private void addProvinceAndCityStatisticsAccountTimeoutData(
			List<Long> idModList, String taskParameter) {
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			logger.error("执行三本台账时限成绩县区job开始执行");

			statisticsAccountTimeoutsJobHelper.executeDataByModel(idModList,
					taskParameter);
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行三本台账时限成绩县区job", true);
			logger.error("执行三本台账时限成绩县区job完成");
		} catch (Exception e) {
			logger.error("三本台账时限考核成绩job出现异常：", e);
			jobMonitorService.updateJobMonitor(id,
					"三本台账时限考核成绩job出现异常：" + e.getMessage(), false);
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		createStatisticsAccountTimeoutData();
	}

}
