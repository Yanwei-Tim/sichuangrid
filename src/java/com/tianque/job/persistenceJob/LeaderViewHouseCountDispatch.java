package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.plugin.analyzing.service.LeaderViewService;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:房屋领导视图当前月job
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-11 上午12:03:28
 */
@Component("leaderViewHouseCountDispatch")
public class LeaderViewHouseCountDispatch implements Serializable {

	@Autowired
	private LeaderViewService leaderViewService;
	@Autowired
	private JobMonitorService jobMonitorService;

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
