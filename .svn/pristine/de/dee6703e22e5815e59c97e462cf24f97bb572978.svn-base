package com.tianque.job.realization;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.job.SessionHelper;
import com.tianque.sysadmin.service.JobMonitorService;

@Component("orgLoginStanalsJob")
public class OrgLoginStanalsJob implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private transient com.tianque.plugin.analyzing.service.OrgLoginStanalsService orgLoginStanalsService;
	@Autowired
	private JobMonitorService jobMonitorService;

	private static Logger logger = LoggerFactory
			.getLogger(OrgLoginStanalsJob.class);

	/**
	 * 服务办事类别月报统计
	 */
	public void addMonthOrgLoginStanals() {
		logger.error("登录统计OrgLoginStanalsJob开始执行");
		SessionHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(OrgLoginStanalsJob.class
				.getName());
		long startTime = System.currentTimeMillis();
		try {
			// 生成登录日志
			orgLoginStanalsService.addMonthOrgLoginStanalssJob(
					CalendarUtil.getNowYear(), CalendarUtil.getNowMonth());

			logger.error("顺利完成");
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行登录统计job", true);
		} catch (Exception ex) {
			jobMonitorService.updateJobMonitor(id,
					"执行登录统计job出现异常：" + ex.getMessage(), false);
			logger.error("", ex);
		}

	}

}
