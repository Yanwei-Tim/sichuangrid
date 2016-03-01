package com.tianque.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.realization.CorrectionExpire;
import com.tianque.plugin.analysisNew.service.UserActivateReportNewService;
import com.tianque.plugin.analysisNew.util.AnalyseUtilNew;
import com.tianque.plugin.analyzing.service.UserActivateReportService;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:研判分析用户激活率job
 * @author zhangyouwei@hztianque.com
 * @date: 2015-1-16 上午09:42:11
 */
@Component("userActivateReportJob")
public class UserActivateReportJob implements Job {
	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private UserActivateReportService userActivateReportService;

	@Autowired
	private UserActivateReportNewService userActivateReportNewService;

	public void createUserActivateReportData() {
		JobHelper.createMockAdminSession();

		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			logger.error("执行研判分析用户激活率job开始执行");
			if (AnalyseUtilNew.IS_NEWANALYSE_JOB) {
				userActivateReportNewService.createUserActivateReportData();
			} else {
				userActivateReportService.createUserActivateReportData();
			}
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行研判分析用户激活率job", true);
			logger.error("执行研判分析用户激活率job完成");
		} catch (Exception e) {
			logger.error("研判分析用户激活率job出现异常：", e);
			jobMonitorService.updateJobMonitor(id,
					"研判分析用户激活率job出现异常：" + e.getMessage(), false);
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		createUserActivateReportData();
	}
}
