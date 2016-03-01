package com.tianque.job.realization;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.User;
import com.tianque.job.JobHelper;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.factory.PlatformMessageFactory;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.domain.DailyYear;
import com.tianque.working.service.DailyYearService;

@Component("whetherAutoStartDailyYearJob")
public class WhetherAutoStartDailyYearJob implements Job {
	private static Logger logger = LoggerFactory.getLogger(CorrectionExpire.class);

	@Autowired
	private DailyYearService dailyYearService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private PlatformMessageService platformMessageService;
	@Autowired
	private PlatformMessageFactory platformMessageFactory;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		dailyYearJob();
	}

	private void dailyYearJob() {
		long startTime = System.currentTimeMillis();
		logger.error("台帐目录job开始执行！");
		JobHelper.createMockAdminSession();
		ThreadVariable.setUser(permissionService.findUserByUserName("admin"));
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			List<DailyYear> autoStartList = dailyYearService.findAutoStartDailyYear();
			List<DailyYear> manuallyStartList = dailyYearService.findManuallyStartDailyYear();
			if (autoStartList != null && !autoStartList.isEmpty()) {
				for (int i = 0; i < autoStartList.size(); i++) {
					DailyYear dailyYear = autoStartList.get(i);
					dailyYear.setWhetherAutoStart(Long.valueOf(2));
					dailyYearService.startDailyYearById(dailyYear.getId());
					sendAutoStartMessage(dailyYear);
				}
			}

			if (manuallyStartList != null && !manuallyStartList.isEmpty()) {
				for (int i = 0; i < manuallyStartList.size(); i++) {
					DailyYear dailyYear = manuallyStartList.get(i);
					sendManuallyStartMessage(dailyYear);
				}
			}
			jobMonitorService.updateJobMonitor(id, "花了" + (System.currentTimeMillis() - startTime)
					+ "执行台帐目录job！", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id, "台帐目录job执行错误:" + e.getMessage(), false);
		}
	}

	private void sendAutoStartMessage(DailyYear dailyYear) {
		PlatformMessage pm = platformMessageFactory
				.createAutoStartDailyYearPlatformMessage(dailyYear);
		User user = permissionService.findUserByUserName(dailyYear.getCreateUser());
		pm.setReceiverId(user.getId());
		platformMessageService.sendPlatformMessageToUser(pm);
	}

	private void sendManuallyStartMessage(DailyYear dailyYear) {
		PlatformMessage pm = platformMessageFactory
				.createManuallyStartDailyYearPlatformMessage(dailyYear);
		User user = permissionService.findUserByUserName(dailyYear.getCreateUser());
		pm.setReceiverId(user.getId());
		platformMessageService.sendPlatformMessageToUser(pm);
	}
}
