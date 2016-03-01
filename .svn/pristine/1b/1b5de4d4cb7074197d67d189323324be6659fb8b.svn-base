package com.tianque.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.job.realization.CorrectionExpire;
import com.tianque.plugin.analyzing.service.BaseSituationStatementService;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.PermissionService;
@Component("basesituationStatementStatisticsJob")
public class BasesituationStatementStatisticsJob implements Job{
	private static Logger logger = LoggerFactory
	.getLogger(CorrectionExpire.class);
	
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private BaseSituationStatementService baseSituationStatementService;
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
	}
	
	public void addBasesituationStatementStatistics(){
		logger.error("基本信息报表统计job开始执行");
		//根据当前时间获取上个月所在的年份和月份
		int year = CalendarUtil.getLastYearByMonth();
		int month = CalendarUtil.getLastMonthByMonth();
		long startTime = System.currentTimeMillis();
		JobHelper.createMockAdminSession();
		ThreadVariable.setUser(permissionService.findUserByUserName("admin"));
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try{
			baseSituationStatementService.addBaseStituationStatementHistory(year, month);
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行基本信息报表统计job！", true);
		}catch(Exception e){
			logger.error("基本信息报表统计job执行错误：", e);
			jobMonitorService.updateJobMonitor(id,
					"基本信息报表统计job执行错误:" + e.getMessage(), false);
		}
	}
}
