package com.tianque.usedInfoOptmize.job;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.job.realization.CorrectionExpire;
import com.tianque.plugin.analyzing.service.UsedInfoEverDayCountOptmizeHelper;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:网格化服务管理信息系统使用情况每天统计
 * @author zhangyouwei@hztianque.com
 * @date: 2015-4-28 下午01:51:07
 */
@Component("usedInfoDataEverDayCountOptmizeDispatch")
public class UsedInfoDataEverDayCountOptmizeDispatch implements Serializable {

	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private UsedInfoEverDayCountOptmizeHelper usedInfoEverDayCountOptmizeHelper;

	public void createUsedInfoEverDayCountCache() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			logger.error("执行网格化服务管理信息系统使用情况每天统计并放入缓存job开始执行");
			usedInfoEverDayCountOptmizeHelper.executeDataByModelForUsedInfo();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行网格化服务管理信息系统使用情况每天统计并放入缓存job", true);
			logger.error("执行网格化服务管理信息系统使用情况每天统计并放入缓存job完成");
		} catch (Exception e) {
			logger.error("执行网格化服务管理信息系统使用情况每天统计并放入缓存job出现异常：", e);
			jobMonitorService.updateJobMonitor(id,
					"执行网格化服务管理信息系统使用情况每天统计并放入缓存job出现异常：" + e.getMessage(),
					false);
		}
	}
}
