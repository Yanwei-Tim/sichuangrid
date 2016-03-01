package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.enamePermissionCache.service.EnamePermissionCacheService;
import com.tianque.job.JobHelper;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @author weiminglong
 *2015年11月5日上午10:51:14
 */
@Component("enamePermissionCacheDispatch")
public class EnamePermissionCacheDispatch implements Serializable{
	private static Logger logger = LoggerFactory
			.getLogger(EnamePermissionCacheDispatch.class);
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private EnamePermissionCacheService enamePermissionCacheService;

	public void handleEnamePermissionCache() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			logger.error("用户权限缓存Job开始执行");
			enamePermissionCacheService.handleEnamePermissionCache();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行用户权限缓存Job", true);
			logger.error("用户权限缓存Job完成");
		} catch (Exception e) {
			logger.error("用户权限缓存Job出现异常：", e);
			jobMonitorService.updateJobMonitor(id,
					"用户权限缓存Job出现异常：" + e.getMessage(), false);
		}
	}

}
