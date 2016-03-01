package com.tianque.baseInfo.permanentAddress.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.permanentAddress.job.service.SyncPermanentAddressJobService;
import com.tianque.job.JobHelper;
import com.tianque.sysadmin.service.JobMonitorService;

@Component("syncPermanentAddressJob")
public class SyncPermanentAddressJob implements Job {
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private SyncPermanentAddressJobService syncPermanentAddressJobService;

	public void syncPermanentAddress() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			syncPermanentAddressJobService.syncPermanentAddress();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行同步户籍地job", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"同步户籍地job出现异常：" + e.getMessage(), false);
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		syncPermanentAddress();
	}

}
