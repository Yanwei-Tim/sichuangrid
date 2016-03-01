package com.tianque.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.realization.CorrectionExpire;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * 
 * 删除互动交流收、发件箱过期消息
 * 
 * 1、收件人类型为个人的消息保留2个月，2个月之前的写job由系统自动删除
 * 
 * 2、收件人类型为部门的消息保留1个月，1个月之前的写job由系统自动删除
 * 
 * 3、由admin发出的消息保留1个月，1个月之前的写job由系统自动删除
 * 
 * 4、admin账号的发件箱只保留1个月的信息，1个月之前的写job由系统自动删除
 * 
 * @author zhangyouwei@hztianque.com
 * 
 */
@Component("interactCommunicationMessageCleanJob")
public class InteractCommunicationMessageCleanJob implements Job {
	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private PlatformMessageService platformMessageService;

	public void cleanInteractCommunicationMessage() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			logger.error("互动交流收发件箱删除过期消息job开始执行");
			platformMessageService.cleanInteractCommunicationMessage();

			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行互动交流收发件箱删除过期消息job", true);
			logger.error("执行互动交流收发件箱删除过期消息job完成");
		} catch (Exception e) {
			logger.error("互动交流收发件箱删除过期消息job出现异常：", e);
			jobMonitorService.updateJobMonitor(id,
					"互动交流收发件箱删除过期消息job出现异常：" + e.getMessage(), false);
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		cleanInteractCommunicationMessage();
	}
}
