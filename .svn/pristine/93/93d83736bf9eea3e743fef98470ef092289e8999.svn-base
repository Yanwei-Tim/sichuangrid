package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.excelimportlog.service.ExcelImportLogService;
import com.tianque.job.JobHelper;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:删除过期的导入日志从表中取过期的时间是多长
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-16 下午10:27:44
 */
@Component("excelImportLogCleanDispatch")
public class ExcelImportLogCleanDispatch implements Serializable {
	private static Logger logger = LoggerFactory
			.getLogger(ExcelImportLogCleanDispatch.class);
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private ExcelImportLogService excelImportLogService;

	public void excelImportLogClean() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			logger.error("导入日志删除过期消息job开始执行");
			excelImportLogService.excelImportLogClean();

			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行导入日志删除过期job", true);
			logger.error("执行删除导入日志过期job完成");
		} catch (Exception e) {
			logger.error("删除导入日志过期job出现异常：", e);
			jobMonitorService.updateJobMonitor(id,
					"删除导入日志过期job出现异常：" + e.getMessage(), false);
		}
	}
}
