package com.tianque.job;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.userAccountLoginDetail.service.UserAccountLoginDetailService;

@Component("userAccountLoginDetailWeekJob")
public class UserAccountLoginDetailWeekJob implements Serializable{
	private static Logger logger = LoggerFactory
	.getLogger(UserAccountLoginDetailWeekJob.class);
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private UserAccountLoginDetailService userAccountLoginDetailService;
	@Autowired
	private PermissionService permissionService;
	
	public void createUserAccountLoginDetail() {
		int year = CalendarUtil.getNowYear();
		int month = CalendarUtil.getNowMonth();
		long startTime = System.currentTimeMillis();
		JobHelper.createMockAdminSession();
		ThreadVariable.setUser(permissionService.findUserByUserName("admin"));
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {

			userAccountLoginDetailService.createUserAccountLoginDetailForWeek(year, month);

			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行各层级账号登录详情统计job！", true);
		} catch (Exception e) {
			logger.error("各层级账号登录详情统计job执行错误：", e);
			jobMonitorService.updateJobMonitor(id,
					"各层级账号登录详情统计job执行错误:" + e.getMessage(), false);
		}
	}
}
