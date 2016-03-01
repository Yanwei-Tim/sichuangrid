package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.plugin.dataManage.common.DataManageDBJobService;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * 对数据库新导入数据进行操作的job类
 * 
 * @author Administrator
 */
@Component("dataManageDBDispatch")
public class DataManageDBDispatch implements Serializable {
	@Autowired
	private DataManageDBJobService dataManageDBJobService;
	@Autowired
	private JobMonitorService jobMonitorService;

	/**
	 * 对导入数据进行处理
	 */
	public void disposeDB() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			dataManageDBJobService.toDisposeDB();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行数据管理导入数据处理job", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"数据管理导入数据处理job出现异常：" + e.getMessage(), false);
		}
	}
}
