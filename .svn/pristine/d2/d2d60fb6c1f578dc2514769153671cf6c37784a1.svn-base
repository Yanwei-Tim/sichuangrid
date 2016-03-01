package com.tianque.plugin.dataManage.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.SpringBeanUtil;
import com.tianque.job.JobHelper;
import com.tianque.plugin.AbstractJobPlugin;
import com.tianque.plugin.dataManage.common.DataManageDBJobService;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * 对数据库新导入数据进行操作的job类
 * 
 * @author Administrator
 */
@Component("dataManageDBJob")
public class DataManageDBJob extends AbstractJobPlugin {
	@Autowired
	private DataManageDBJobService dataManageDBJobService;
	@Autowired
	private JobMonitorService jobMonitorService;

	/**
	 * 对导入数据进行处理
	 */
	public void disposeDB() {
		if (null == dataManageDBJobService) {
			dataManageDBJobService = (DataManageDBJobService) SpringBeanUtil
					.getBeanFromSpringByBeanName("dataManageDBJobService");
		}
		if (null == jobMonitorService) {
			jobMonitorService = (JobMonitorService) SpringBeanUtil
					.getBeanFromSpringByBeanName("jobMonitorService");
		}
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

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		disposeDB();
	}

	@Override
	public String getName() {
		return "dataManageDBJob";
	}

	@Override
	public String getCronExpression() {
		return "0 21 23 * * ?";
		// return "0 0/2 * * * ?";
	}

}
