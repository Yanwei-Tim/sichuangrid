package com.tianque.plugin.statistics.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.job.JobHelper;
import com.tianque.plugin.statistics.service.GeneralSituationService;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.PermissionService;

/**
 * 研判分析统计报表Job
 */
@Component("generalSituationByYeatTypeJob")
public class GeneralSituationByYeatTypeJob implements Job {
	private static Logger logger = LoggerFactory
			.getLogger(GeneralSituationByYeatTypeJob.class);
	@Autowired
	private GeneralSituationService generalSituationService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private JobMonitorService jobMonitorService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

	}

	public void addGeneralSituationHistory() {
		logger.error("研判分析统计报表JOB开始执行");
		int year = CalendarUtil.getNowYear();
		int month = CalendarUtil.getNowMonth();
		excuteMonthJob(year, month);
		if(month==3 || month==6 || month==9 || month == 12){
			int quarter = CalendarUtil.getCurrentTimeForQuarter(year,month);
			excuteQuarterJob(year, quarter);
			if(month==6){
				excuteYearTypeJob(year, 0);
			}else if(month==12){
				excuteYearTypeJob(year, 1);
				excuteYearTypeJob(year, 2);
			}
		}
	}
	
	private void excuteMonthJob(int year,int month){
		long startTime = System.currentTimeMillis();
		JobHelper.createMockAdminSession();
		ThreadVariable.setUser(permissionService.findUserByUserName("admin"));
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			generalSituationService.createGeneralSituationByDate(year, month, null, 0, null);
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行研判分析月统计job！", true);
		} catch (Exception e) {
			logger.error("研判分析月统计报表 job执行错误：", e);
			jobMonitorService.updateJobMonitor(id,
					"研判分析月统计报表job执行错误:" + e.getMessage(), false);
		}
	}
	
	private void excuteQuarterJob(int year,int quarter){
		long startTime = System.currentTimeMillis();
		JobHelper.createMockAdminSession();
		ThreadVariable.setUser(permissionService.findUserByUserName("admin"));
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			generalSituationService.createGeneralSituationByDate(year, null, quarter, 1, null);
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行研判分析季度统计job！", true);
		} catch (Exception e) {
			logger.error("研判分析季度统计报表 job执行错误：", e);
			jobMonitorService.updateJobMonitor(id,
					"研判分析季度统计报表job执行错误:" + e.getMessage(), false);
		}
	}
	
	private void excuteYearTypeJob(int year,int yearType){
		long startTime = System.currentTimeMillis();
		JobHelper.createMockAdminSession();
		ThreadVariable.setUser(permissionService.findUserByUserName("admin"));
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			generalSituationService.createGeneralSituationByDate(year, null, null, 2, yearType);
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行研判分析年度统计job！", true);
		} catch (Exception e) {
			logger.error("研判分析年度统计报表 job执行错误：", e);
			jobMonitorService.updateJobMonitor(id,
					"研判分析年度统计报表job执行错误:" + e.getMessage(), false);
		}
	}
}
