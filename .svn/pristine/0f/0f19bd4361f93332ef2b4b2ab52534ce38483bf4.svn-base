package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.leaderViewCache.constants.LeaderViewCacheType;
import com.tianque.baseInfo.leaderViewCache.service.LeaderViewCacheService;
import com.tianque.job.JobHelper;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:清除缓存表数据job
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-10 下午04:06:59
 */
@Component("cleanLeaderviewcacheDispatch")
public class CleanLeaderviewcacheDispatch implements Serializable {
	private static Logger logger = LoggerFactory
			.getLogger(CleanLeaderviewcacheDispatch.class);

	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private LeaderViewCacheService leaderViewCacheService;

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
