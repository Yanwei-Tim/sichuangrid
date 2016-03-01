package com.tianque.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.leaderViewCache.constants.LeaderViewCacheType;
import com.tianque.baseInfo.leaderViewCache.service.LeaderViewCacheService;
import com.tianque.job.realization.CorrectionExpire;
import com.tianque.sysadmin.service.JobMonitorService;

@Component("cleanLeaderviewcacheJob")
public class CleanLeaderviewcacheJob implements Job {
	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);

	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private LeaderViewCacheService leaderViewCacheService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		cleanLeaderviewcache();
	}

	public void cleanLeaderviewcache() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			logger.error("清除缓存表数据job开始执行");
			leaderViewCacheService
					.deleteLeaderViewCache(LeaderViewCacheType.OTHER_TYPE);
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "清除缓存表数据job", true);
			logger.error("清除缓存表数据job完成");
		} catch (Exception e) {
			logger.error("清除缓存表数据job出现异常：", e);
			jobMonitorService.updateJobMonitor(id,
					"清除缓存表数据job出现异常：" + e.getMessage(), false);
		}

	}
}
