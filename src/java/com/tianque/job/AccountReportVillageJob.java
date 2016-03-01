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

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.job.realization.CorrectionExpire;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.xichang.working.report.service.AccountReportJobOptmizeHelper;

/**
 * 三本台账报表(社区)job
 * */
@Component("oldAccountReportVillageJob")
public class AccountReportVillageJob implements Job {
	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private AccountReportJobOptmizeHelper accountReportJobOptmizeHelper;

	public void createAccountReportJobData() {
		if (GridProperties.ISSCHEDULE)
			return;
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			logger.info("执行三本台账社区报表job开始执行");
			List<Long> idModList = new ArrayList<Long>();
			idModList.add(0l);
			idModList.add(1l);
			int year = CalendarUtil.getNowYear();
			int month = CalendarUtil.getNowMonth();
			accountReportJobOptmizeHelper
					.executeDataByModelForAccountReportVillage(year, month,
							idModList, "1");
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行三本台账社区报表job", true);
			logger.info("执行三本台账社区报表job完成");
		} catch (Exception e) {
			logger.error("三本台账社区报表job出现异常：", e);
			jobMonitorService.updateJobMonitor(id,
					"三本台账社区报表job出现异常：" + e.getMessage(), false);
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		createAccountReportJobData();
	}

}
