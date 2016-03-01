package com.tianque.job.persistenceJob;

import java.io.Serializable;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.statRegrad.service.DefalutStatRegradedPointsService;
import com.tianque.statRegrad.service.IssueGradeService;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:事件处理成绩表综合指标job
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-11 上午10:36:54
 */
@Component("statRegradeDispatch")
public class StatRegradeDispatch implements Serializable {
	private static Logger logger = LoggerFactory
			.getLogger(StatRegradeDispatch.class);
	@Autowired
	private IssueGradeService issueGradeService;
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private DefalutStatRegradedPointsService defalutStatRegradedPointsService;

	public void statRegradedPoints() {
		logger.debug("事件处理成绩表综合指标job开始执行");
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		Integer month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		try {
			long startTime = System.currentTimeMillis();
			issueGradeService.statRegradedPoints(null);
			// integratedIndicatorForRegradedPointService.updateRegradedPoints();
			logger.debug("事件处理成绩表综合指标job开始结束");
			defalutStatRegradedPointsService.createRegradedPointStatTable(year,
					month);
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行事件处理成绩表综合指标job", true);
		} catch (Exception e) {
			logger.error("", e);
			jobMonitorService.updateJobMonitor(id,
					"事件处理成绩表综合指标job出现异常：" + e.getMessage(), false);
		}
	}
}
