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
 * @Description:业务人员当前月领导视图（领导视图上半部分数据）
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-10 上午12:53:49
 */
@Component("attentionPeopleLeaderViewCountDispatch")
public class AttentionPeopleLeaderViewCountDispatch implements Serializable {

	private final static Logger logger = LoggerFactory
			.getLogger(AttentionPeopleLeaderViewCountDispatch.class);

	@Autowired
	private LeaderViewService leaderViewService;
	@Autowired
	private JobMonitorService jobMonitorService;

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
