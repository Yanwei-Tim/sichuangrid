package com.tianque.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.realization.CorrectionExpire;
import com.tianque.plugin.analyzing.service.UserActivateReportService;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:研判分析用户覆盖率当月的数据放入缓存和缓存表中
 * @author zhangyouwei@hztianque.com
 * @date: 2015-3-2 下午09:32:14
 */
@Component("userActivateReportNowMonthCacheJob")
public class UserActivateReportNowMonthCacheJob implements Job {
	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private UserActivateReportService userActivateReportService;

	public void createUserActivateReportNowMonthCache() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			logger.error("执行研判分析用户激活率当月数据并放入缓存job开始执行");
//			 userActivateReportService.createUserActivateReportNowMonthCache();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行研判分析用户激活率当月数据并放入缓存job", true);
			logger.error("执行研判分析用户激活率当月数据并放入缓存job完成");
		} catch (Exception e) {
			logger.error("执行研判分析用户激活率当月数据并放入缓存job出现异常：", e);
			jobMonitorService.updateJobMonitor(id,
					"执行研判分析用户激活率当月数据并放入缓存job出现异常：" + e.getMessage(), false);
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		createUserActivateReportNowMonthCache();
	}
}
