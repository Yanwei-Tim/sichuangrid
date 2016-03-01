package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.issueLeaderViewCache.service.IssueLeaderViewCacheService;
import com.tianque.job.JobHelper;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:事件省级列表缓存job
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-10 下午11:29:04
 */
@Component("issueCacheDispatch")
public class IssueCacheDispatch implements Serializable {
	private static Logger logger = LoggerFactory
			.getLogger(IssueCacheDispatch.class);
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private IssueLeaderViewCacheService issueLeaderViewCacheService;

	public void handleIssueCache() {
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
}
