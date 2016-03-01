package com.tianque.job.realization;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.tianque.core.util.ThreadVariable;
import com.tianque.job.JobHelper;
import com.tianque.plugin.analysisNew.service.BaseInfoStatTypeNewService;
import com.tianque.plugin.analysisNew.util.AnalyseUtilNew;
import com.tianque.plugin.analyzing.service.BaseInfoStatTypeService;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.PermissionService;

@Component("baseInfoStatTypeJob")
public class BaseInfoStatTypeJob implements Job {
	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);
	@Autowired
	private com.tianque.plugin.analyzing.service.BaseInfoStatTypeService baseInfoStatTypeService;
	@Autowired
	private PermissionService permissionService;
	private ApplicationContext applicationContext;
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private BaseInfoStatTypeNewService baseInfoStatTypeNewService;

	public void saveBaseInfoStatType() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			ThreadVariable.setUser(permissionService
					.findUserByUserName("admin"));
			if (AnalyseUtilNew.IS_NEWANALYSE_JOB) {
				baseInfoStatTypeNewService.addBaseInfoStatType();
			} else {
				baseInfoStatTypeService.addBaseInfoStatType();
			}
			jobMonitorService
					.updateJobMonitor(id, "花了"
							+ (System.currentTimeMillis() - startTime)
							+ "研判分析job", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"研判分析job出现异常：" + e.getMessage(), false);
		}
	}

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			if (applicationContext == null) {
				SchedulerContext schedulerContext = context.getScheduler()
						.getContext();
				applicationContext = (ApplicationContext) schedulerContext
						.get("applicationContext");
			}
			if (AnalyseUtilNew.IS_NEWANALYSE_JOB) {
				baseInfoStatTypeNewService = (BaseInfoStatTypeNewService) applicationContext
						.getBean("searchIdleYouthDao");
			} else {
				baseInfoStatTypeService = (BaseInfoStatTypeService) applicationContext
						.getBean("searchIdleYouthDao");
			}
			saveBaseInfoStatType();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
	}
}
