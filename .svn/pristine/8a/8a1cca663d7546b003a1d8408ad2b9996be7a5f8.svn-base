package com.tianque.job.realization;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Session;
import com.tianque.job.JobHelper;
import com.tianque.plugin.analysisNew.service.BaseinfoStatisticNewService;
import com.tianque.plugin.analysisNew.util.AnalyseUtilNew;
import com.tianque.plugin.analyzing.service.BaseinfoStatisticService;
import com.tianque.service.util.PopulationType;
import com.tianque.sysadmin.service.JobMonitorService;

@Component("youthStatistJob")
public class YouthStatistJob implements Job {
	@Autowired
	private BaseinfoStatisticService baseinfoStatisticService;
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private BaseinfoStatisticNewService baseinfoStatisticNewService;

	public void statisticsMonthMessage() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			createYouthStatInfos();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行YouthStatistJob", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"YouthStatistJob出现异常：" + e.getMessage(), false);
		}
	}

	private void createYouthStatInfos() {
		int year = CalendarUtil.getLastMonthYearValue();
		int month = CalendarUtil.getLastMonth();
		Session session = ThreadVariable.getSession();
		if (AnalyseUtilNew.IS_NEWANALYSE_JOB) {
			baseinfoStatisticNewService.createHistoryStatisticByType(year,
					month, PopulationType.ALL_YOUTH_POPULATION,
					session.getOrgInternalCode());
		} else {
			baseinfoStatisticService.createHistoryStatisticByType(year, month,
					PopulationType.ALL_YOUTH_POPULATION,
					session.getOrgInternalCode());
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		statisticsMonthMessage();
	}
}
