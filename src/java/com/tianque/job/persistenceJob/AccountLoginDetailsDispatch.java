package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.job.JobHelper;
import com.tianque.service.AccountLoginDetailsService;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.PermissionService;

@Component("accountLoginDetailsDispatch")
public class AccountLoginDetailsDispatch implements Serializable {
	private static Logger logger = LoggerFactory
			.getLogger(AccountLoginDetailsDispatch.class);
	@Autowired
	private AccountLoginDetailsService accountLoginDetailsService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private JobMonitorService jobMonitorService;

	public void createAccountLoginDetails() {
		int year = CalendarUtil.getNowYear();
		int month = CalendarUtil.getNowMonth();
		long startTime = System.currentTimeMillis();
		JobHelper.createMockAdminSession();
		ThreadVariable.setUser(permissionService.findUserByUserName("admin"));
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {

			accountLoginDetailsService.createAccountDetails((long) year,
					(long) month);

			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行账号月登录详情统计job！", true);
		} catch (Exception e) {
			logger.error("账号月登录详情统计job执行错误：", e);
			jobMonitorService.updateJobMonitor(id,
					"账号月登录详情统计job执行错误:" + e.getMessage(), false);
		}
	}

}
