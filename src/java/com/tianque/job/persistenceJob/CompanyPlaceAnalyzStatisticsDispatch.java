package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.plugin.analysisNew.service.CompanyPlaceLeaderViewNewService;
import com.tianque.plugin.analysisNew.util.AnalyseUtilNew;
import com.tianque.plugin.analyzing.service.CompanyPlaceLeaderViewService;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:单位场所研判分析统计job
 * @author zhangyouwei@hztian.com
 * @date: 2014-8-21 下午09:41:33
 */
@Component("companyPlaceAnalyzStatisticsDispatch")
public class CompanyPlaceAnalyzStatisticsDispatch implements Serializable {
	private static Logger logger = LoggerFactory
			.getLogger(CleanLeaderviewcacheDispatch.class);
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private CompanyPlaceLeaderViewService companyPlaceLeaderViewService;

	@Autowired
	private CompanyPlaceLeaderViewNewService companyPlaceLeaderViewNewService;

	public void companyPlaceAnalyzStatistics() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			logger.error("单位场所研判分析统计job开始执行");
			if (AnalyseUtilNew.IS_NEWANALYSE_JOB) {
				companyPlaceLeaderViewNewService.companyPlaceAnalyzStatistics();
			} else {
				companyPlaceLeaderViewService.companyPlaceAnalyzStatistics();
			}
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "单位场所研判分析统计job", true);
			logger.error("单位场所研判分析统计job完成");
		} catch (Exception e) {
			logger.error("单位场所研判分析统计job出现异常：", e);
			jobMonitorService.updateJobMonitor(id,
					"单位场所研判分析统计job出现异常：" + e.getMessage(), false);
		}
	}

}
