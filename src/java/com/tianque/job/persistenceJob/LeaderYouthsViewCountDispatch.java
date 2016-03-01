package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.plugin.analyzing.service.LeaderViewService;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:青少年领导视图当前月job
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-11 上午12:08:40
 */
@Component("leaderYouthsViewCountDispatch")
public class LeaderYouthsViewCountDispatch implements Serializable {
	private final static Logger logger = LoggerFactory
			.getLogger(LeaderYouthsViewCountDispatch.class);
	@Autowired
	private LeaderViewService leaderViewService;
	@Autowired
	private JobMonitorService jobMonitorService;

	private boolean run;

	public void leaderViewHourMessage() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		synchronized (logger) {
			if (run) {
				jobMonitorService.updateJobMonitor(id, "上一次任务未执行完毕", false);
				return;
			}
			run = true;
		}
		try {
			long startTime = System.currentTimeMillis();
			leaderViewService.statisticsYouthsAddCountByOrgIdForJob();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行青少年领导视图总数统计job", true);
		} catch (Exception e) {
			logger.error("", e);
			jobMonitorService.updateJobMonitor(id,
					"执行青少年领导视图总数统计job出现异常：" + e.getMessage(), false);
		} finally {
			run = false;
		}
	}
}
