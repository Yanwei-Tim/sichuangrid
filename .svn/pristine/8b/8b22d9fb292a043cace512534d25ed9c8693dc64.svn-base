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
 * 三本台账报表(县乡镇)job
 * */
@Component("accountReportJob")
public class AccountReportJob implements Job {
	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private AccountReportJobOptmizeHelper accountReportJobOptmizeHelper;

	public void createAccountReportJobData() {
		if (GridProperties.ISSCHEDULE)
			return;
		List<Long> idModList = new ArrayList<Long>();
		idModList.add(0l);
		idModList.add(1l);
		String taskParameter = "1";
		int year = CalendarUtil.getNowYear();
		int month = CalendarUtil.getNowMonth();
		accountReportDistrict(year, month, idModList, taskParameter);
		accountReportTown(year, month, idModList, taskParameter);
	}

	private void accountReportDistrict(int year, int month,
			List<Long> idModList, String taskParameter) {

		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			logger.error("执行三本台账县区报表job开始执行");
			accountReportJobOptmizeHelper
					.executeDataByModelForAccountReportDistrict(year, month,
							idModList, taskParameter);
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行三本台账县区报表job", true);
			logger.error("执行三本台账县区报表job完成");
		} catch (Exception e) {
			logger.error("三本台账报表job出现异常：", e);
			jobMonitorService.updateJobMonitor(id,
					"三本台账报表job出现异常：" + e.getMessage(), false);
		}
	}

	private void accountReportTown(int year, int month, List<Long> idModList,
			String taskParameter) {
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			logger.error("执行三本台账乡镇报表job开始执行");
			accountReportJobOptmizeHelper
					.executeDataByModelForAccountReportTown(year, month,
							idModList, taskParameter);
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行三本台账乡镇报表job", true);
			logger.error("执行三本台账乡镇报表job完成");
		} catch (Exception e) {
			logger.error("三本台账乡镇报表job出现异常：", e);
			jobMonitorService.updateJobMonitor(id,
					"三本台账乡镇报表job出现异常：" + e.getMessage(), false);
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		createAccountReportJobData();
	}

}
