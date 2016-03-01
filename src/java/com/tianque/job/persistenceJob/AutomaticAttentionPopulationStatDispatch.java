package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.plugin.analysisNew.service.BaseinfoStatisticNewService;
import com.tianque.plugin.analysisNew.util.AnalyseUtilNew;
import com.tianque.plugin.analyzing.service.BaseinfoStatisticService;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:业务人员、两新组织、房屋等历史数据job
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-10 上午01:04:21
 */
@Component("automaticAttentionPopulationStatDispatch")
public class AutomaticAttentionPopulationStatDispatch implements Serializable {
	@Autowired
	private BaseinfoStatisticService baseinfoStatisticService;
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private BaseinfoStatisticNewService baseinfoStatisticNewService;

	/**
	 * 自动统计趋势图数据
	 */
	public void statisticsMonthMessage() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			if (AnalyseUtilNew.IS_NEWANALYSE_JOB) {
				baseinfoStatisticNewService.generateHistoryStatistic();
			} else {
				baseinfoStatisticService.generateHistoryStatistic();
			}
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "自动统计趋势图数据job", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"自动统计趋势图数据job出现异常：" + e.getMessage(), false);
		}
	}

}
