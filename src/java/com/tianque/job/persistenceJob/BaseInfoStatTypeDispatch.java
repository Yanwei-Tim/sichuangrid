package com.tianque.job.persistenceJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.plugin.analysisNew.service.BaseInfoStatTypeNewService;
import com.tianque.plugin.analysisNew.util.AnalyseUtilNew;
import com.tianque.plugin.analyzing.service.BaseInfoStatTypeService;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:旧的单位场所历史数据job
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-9 下午04:07:31
 */
@Component("baseInfoStatTypeDispatch")
public class BaseInfoStatTypeDispatch {
	private static Logger logger = LoggerFactory
			.getLogger(BaseInfoStatTypeDispatch.class);
	@Autowired
	private BaseInfoStatTypeService baseInfoStatTypeService;

	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private BaseInfoStatTypeNewService baseInfoStatTypeNewService;

	public void saveBaseInfoStatType() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			JobHelper.createMockAdminSession();
			if (AnalyseUtilNew.IS_NEWANALYSE_JOB) {
				baseInfoStatTypeNewService.addBaseInfoStatType();
			} else {
				baseInfoStatTypeService.addBaseInfoStatType();
			}
			jobMonitorService
					.updateJobMonitor(id, "花了"
							+ (System.currentTimeMillis() - startTime)
							+ "研判分析job", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"研判分析job出现异常：" + e.getMessage(), false);
		}
	}
}
