package com.tianque.job.leaderView;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.plugin.analyzing.service.LeaderViewService;
import com.tianque.sysadmin.service.JobMonitorService;

@Component("leaderYouthsViewCountJob")
public class LeaderYouthsViewCountJob implements Job {
	private final static Logger logger = LoggerFactory
			.getLogger(LeaderYouthsViewCountJob.class);
	@Autowired
	private LeaderViewService leaderViewService;
	@Autowired
	private JobMonitorService jobMonitorService;

	private boolean run;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		leaderViewHourMessage();
	}

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
