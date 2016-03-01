package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:两新组织统计job，可以不启用
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-11 上午09:05:14
 */
@Component("newEconomicDispatch")
public class NewEconomicDispatch implements Serializable {
	// private NewEconomicStatService newEconomicStatService;
	@Autowired
	private JobMonitorService jobMonitorService;

	public void statisticsMonthMessage() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			// newEconomicStatService.addEconomicStat();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行NewEconomic", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"服务NewEconomic出现异常：" + e.getMessage(), false);
		}
	}
}
