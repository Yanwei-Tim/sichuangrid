package com.tianque.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.realization.CorrectionExpire;
import com.tianque.plugin.analyzing.service.CompanyPlaceLeaderViewService;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:单位场所领导视图当月数据统计job(每隔一段时间统计一次然后放到缓存，只针对县级以上)
 * @author zhangyouwei@hztian.com
 * @date: 2014-8-21 下午02:21:14
 */
@Component("companyPlaceLeaderViewJob")
public class CompanyPlaceLeaderViewJob implements Job {
	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private CompanyPlaceLeaderViewService companyPlaceLeaderViewService;

	public void companyPlaceLeaderView() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			logger.info("单位场所领导视图数据统计job开始执行");
			companyPlaceLeaderViewService
					.companyPlaceLeaderViewStatisticsforAdd();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "单位场所领导视图数据统计job", true);
			logger.info("单位场所领导视图数据统计job完成");
		} catch (Exception e) {
			logger.error("单位场所领导视图数据统计job出现异常：", e);
			jobMonitorService.updateJobMonitor(id,
					"单位场所领导视图数据统计job出现异常：" + e.getMessage(), false);
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		companyPlaceLeaderView();

	}

}
