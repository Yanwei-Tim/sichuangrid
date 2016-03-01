package com.tianque.job;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.sysadmin.service.JobMonitorService;

/***
 * @Description:systemLogs在分表前将上个月的日志备份到systemLogs_Archive表并且清除上个月的日志的job现在没有必要这样做了
 * @author zhangyouwei@hztianque.com
 * @date: 2015-2-2 上午10:30:49
 */
@Component("archiveSystemLogsJob")
public class ArchiveSystemLogsJob implements Serializable {

	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private JobMonitorService jobMonitorService;

	public void archiveSystemLogs() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			systemLogService.archiveSystemLogs();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行系统日志文档job", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"系统日志文档job出现异常：" + e.getMessage(), false);
		}
	}
}
