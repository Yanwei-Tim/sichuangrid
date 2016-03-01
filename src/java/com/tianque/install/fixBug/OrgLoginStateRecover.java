package com.tianque.install.fixBug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.util.CalendarUtil;
import com.tianque.init.ContextType;
import com.tianque.job.SessionHelper;
import com.tianque.plugin.analyzing.service.OrgLoginStanalsService;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.util.ApplicationContextFactory;

public class OrgLoginStateRecover {
	private static Logger logger = LoggerFactory
			.getLogger(OrgLoginStateRecover.class);

	public static void main(String[] args) {
		logger.error("开始处理...");
		SessionHelper.createMockAdminSession();
		Long id = getJobMonitorService().addJobMonitor(
				OrgLoginStateRecover.class.getName());

		long startTime = System.currentTimeMillis();
		try {
			// 行政部门生成登录日志
			getOrgLoginStanalsService().addMonthOrgLoginStanalssJob(
					CalendarUtil.getLastMonthYearValue(),
					CalendarUtil.getLastMonth());

			logger.error("顺利完成");
			getJobMonitorService().updateJobMonitor(
					id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行登录统计job", true);
			System.exit(0);
		} catch (Exception ex) {
			getJobMonitorService().updateJobMonitor(id,
					"执行登录统计job出现异常：" + ex.getMessage(), false);
			ex.printStackTrace();
			logger.error(ex.getMessage());
			System.exit(0);
		}

	}

	private static OrgLoginStanalsService getOrgLoginStanalsService() {
		return (OrgLoginStanalsService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.production, "orgLoginStanalsService");
	}

	private static JobMonitorService getJobMonitorService() {
		return (JobMonitorService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.production, "jobMonitorService");
	}
}
