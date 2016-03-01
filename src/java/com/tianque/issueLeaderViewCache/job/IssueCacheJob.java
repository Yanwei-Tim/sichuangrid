package com.tianque.issueLeaderViewCache.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.issueLeaderViewCache.service.IssueLeaderViewCacheService;
import com.tianque.job.JobHelper;
import com.tianque.job.realization.CorrectionExpire;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:事件省级列表缓存job
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-10 下午11:29:04
 */
@Component("issueCacheJob")
public class IssueCacheJob implements Job {
	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private IssueLeaderViewCacheService issueLeaderViewCacheService;

	private void handleIssueCache() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			logger.error("事件列表缓存Job开始执行");
			issueLeaderViewCacheService.handleIssueCache();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行事件列表缓存Job", true);
			logger.error("事件列表缓存Job完成");
		} catch (Exception e) {
			logger.error("事件列表缓存Job出现异常：", e);
			jobMonitorService.updateJobMonitor(id,
					"事件列表缓存Job出现异常：" + e.getMessage(), false);
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		handleIssueCache();
	}
}
