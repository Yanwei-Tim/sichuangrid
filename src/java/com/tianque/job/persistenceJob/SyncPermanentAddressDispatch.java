package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.permanentAddress.job.service.SyncPermanentAddressJobService;
import com.tianque.job.JobHelper;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:同步户籍地job
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-11 上午10:45:40
 */
@Component("syncPermanentAddressDispatch")
public class SyncPermanentAddressDispatch implements Serializable {
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
}
