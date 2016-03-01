package com.tianque.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.realization.CorrectionExpire;
import com.tianque.qinYangIssue.service.IssueJointTempService;
import com.tianque.sysadmin.service.JobMonitorService;

/***
 * @Description:扫描青羊事件对接表，把正确的数据同步到社管事件表中
 * @author zhangyouwei@hztianque.com
 * @date: 2014-12-14 下午04:19:41
 */
@Component("issueJointTempDataSyncJob")
public class IssueJointTempDataSyncJob implements Job {
	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private IssueJointTempService issueJointTempService;

	/**
	 * 同步数据
	 */
	private void syncIssueJointTempData() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			logger.error("执行青羊事件对接数据同步到社管job开始执行");
			issueJointTempService.syncIssueJointTempData(true);
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行青羊事件对接数据同步到社管job", true);
			logger.error("执行青羊事件对接数据同步到社管job完成");
		} catch (Exception e) {
			logger.error("青羊事件对接数据同步到社管job出现异常：", e);
			jobMonitorService.updateJobMonitor(id,
					"青羊事件对接数据同步到社管job出现异常：" + e.getMessage(), false);
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		syncIssueJointTempData();
	}
}
