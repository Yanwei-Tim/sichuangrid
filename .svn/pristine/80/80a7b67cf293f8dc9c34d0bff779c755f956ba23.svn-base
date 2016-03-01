package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.qinYangIssue.service.IssueJointTempService;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:同步issue的当前处理状态到对接临时数据表中
 * @author zhangyouwei@hztianque.com
 * @date: 2014-12-15 上午01:54:49
 */
@Component("syncIssueStatusToIssueJointTempDataDispatch")
public class SyncIssueStatusToIssueJointTempDataDispatch implements
		Serializable {

	private static Logger logger = LoggerFactory
			.getLogger(SyncIssueStatusToIssueJointTempDataDispatch.class);
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private IssueJointTempService issueJointTempService;

	/**
	 * 同步数据
	 */
	public void syncIssueStatusToIssueJointTempData() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			logger.error("执行社管对接事件当前处理状态同步到青羊事件对接数据job开始执行");
			issueJointTempService.syncIssueStatusToIssueJointTempData(true);
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行社管对接事件当前处理状态同步到青羊事件对接数据job", true);
			logger.error("执行社管对接事件当前处理状态同步到青羊事件对接数据job完成");
		} catch (Exception e) {
			logger.error("社管对接事件当前处理状态同步到青羊事件对接数据job出现异常：", e);
			jobMonitorService.updateJobMonitor(id,
					"社管对接事件当前处理状态同步到青羊事件对接数据job出现异常：" + e.getMessage(), false);
		}
	}
}
