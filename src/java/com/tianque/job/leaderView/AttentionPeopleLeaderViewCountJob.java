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

@Component("attentionPeopleLeaderViewCountJob")
public class AttentionPeopleLeaderViewCountJob implements Job {

	private final static Logger logger = LoggerFactory
			.getLogger(AttentionPeopleLeaderViewCountJob.class);

	@Autowired
	private LeaderViewService leaderViewService;
	@Autowired
	private JobMonitorService jobMonitorService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		attentionPeopleLeaderViewMessage();
	}

	public void attentionPeopleLeaderViewMessage() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			leaderViewService
					.statisticsAttentionPopulationAddCountByOrgIdForJob();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行业务人员领导视图job", true);
		} catch (Exception e) {
			logger.error("", e);
			jobMonitorService.updateJobMonitor(id,
					"执行业务人员领导视图job出现异常：" + e.getMessage(), false);
		}
	}
}
