package com.tianque.job.leaderView;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.plugin.analyzing.service.LeaderViewService;
import com.tianque.sysadmin.service.JobMonitorService;

@Component("leaderViewHouseCountJob")
public class LeaderViewHouseCountJob implements Job {

	@Autowired
	private LeaderViewService leaderViewService;
	@Autowired
	private JobMonitorService jobMonitorService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		leaderViewHouseInfoMessage();
	}

	public void leaderViewHouseInfoMessage() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			leaderViewService.statisticsHouseInfoAddCountByOrgIdForJob();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行房屋模型领导视图job", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"执行房屋模型领导视图job出现异常：" + e.getMessage(), false);
		}
	}
}
