package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.plugin.analysisNew.service.StatisticsPopulationNewService;
import com.tianque.plugin.analysisNew.util.AnalyseUtilNew;
import com.tianque.plugin.analyzing.service.StatisticsPopulationService;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:户籍人员统计类型图数据job
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-10 上午01:07:30
 */
@Component("automaticHouseholdStaffPopulationStatDispatch")
public class AutomaticHouseholdStaffPopulationStatDispatch implements
		Serializable {

	@Autowired
	private StatisticsPopulationService populationService;
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private StatisticsPopulationNewService populationNewService;

	/**
	 * 实有人口户籍人员自动统计类型图数据（实有人口其他类别有类型图也可用）
	 */
	public void statisticsMonthMessage() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			if (AnalyseUtilNew.IS_NEWANALYSE_JOB) {
				populationNewService.addHouseholdStaffPopulationStat();
			} else {
				populationService.addHouseholdStaffPopulationStat();
			}
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "自动统计人口信息类型图数据job", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"自动统计人口信息类型图数据job出现异常：" + e.getMessage(), false);
		}
	}
}
