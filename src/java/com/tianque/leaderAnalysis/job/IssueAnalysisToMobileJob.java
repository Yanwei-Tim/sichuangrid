package com.tianque.leaderAnalysis.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.job.realization.CorrectionExpire;
import com.tianque.leaderAnalysis.service.IssueAnalysisToMobileService;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:手机事件研判分析，生成各个大类对应的办理情况表
 * @author zhangyouwei@hztianque.com
 * @date: 2015-3-31 下午11:52:59
 */
@Component("issueAnalysisToMobileJob")
public class IssueAnalysisToMobileJob implements Job {

	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private IssueAnalysisToMobileService issueAnalysisToMobileService;

	public void createIssueAnalysisToMobileData() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			logger.error("执行手机事件研判分析，生成各个大类对应的办理情况表job开始执行");
			issueAnalysisToMobileService.createIssueAnalysisToMobileData();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行手机事件研判分析，生成各个大类对应的办理情况表job", true);
			logger.error("执行手机事件研判分析，生成各个大类对应的办理情况表job完成");
		} catch (Exception e) {
			logger.error("手机事件研判分析，生成各个大类对应的办理情况表job出现异常：", e);
			jobMonitorService.updateJobMonitor(id,
					"手机事件研判分析，生成各个大类对应的办理情况表job出现异常：" + e.getMessage(), false);
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		createIssueAnalysisToMobileData();
	}

}