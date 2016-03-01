package com.tianque.xichang.working.report.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.util.GridProperties;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.xichang.working.report.service.AccountReportJobOptmizeHelper;
import com.tianque.xichang.working.report.service.CreateAccountReportDataService;

/**
 * @Description:生成数据报表类
 * @author zhangyouwei@hztianque.com
 * @date: 2015-4-21 下午04:09:16
 */
@Service("createAccountReportDataService")
public class CreateAccountReportDataServiceImpl implements
		CreateAccountReportDataService {
	private static final int DISTRICT_LEVEL = 1;
	private static final int VILLAGE_LEVEL = 0;
	@Autowired
	private JobMonitorService jobMonitorService;

	@Autowired
	private AccountReportJobOptmizeHelper accountReportJobOptmizeHelper;

	@Override
	public void createAccountReportData(Integer year, Integer month,
			Integer reportLevel) {
		if (year == null || month == null || reportLevel == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			if (GridProperties.ISSCHEDULE)
				return;
			List<Long> idModList = new ArrayList<Long>();
			idModList.add(0l);
			idModList.add(1l);
			String taskParameter = "1";
			String info = "";
			if (reportLevel == DISTRICT_LEVEL) {
				info = "手动生成三本台账区县乡镇报表";
			} else if (reportLevel == VILLAGE_LEVEL) {
				info = "手动生成三本台账社区报表";
			}
			Long id = jobMonitorService.addJobMonitor(info);

			if (reportLevel == DISTRICT_LEVEL) {// 县乡镇
				long startTime = System.currentTimeMillis();
				accountReportJobOptmizeHelper
						.executeDataByModelForAccountReportDistrict(year,
								month, idModList, taskParameter);
				accountReportJobOptmizeHelper
						.executeDataByModelForAccountReportTown(year, month,
								idModList, taskParameter);
				jobMonitorService.updateJobMonitor(id,
						"花了" + (System.currentTimeMillis() - startTime)
								+ "执行三本台账县区，乡镇报表job", true);
			} else if (reportLevel == VILLAGE_LEVEL) {// 社区
				long startTime = System.currentTimeMillis();
				accountReportJobOptmizeHelper
						.executeDataByModelForAccountReportVillage(year, month,
								idModList, taskParameter);

				jobMonitorService.updateJobMonitor(id,
						"花了" + (System.currentTimeMillis() - startTime)
								+ "执行三本台账社区报表job", true);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("手动生成三本台账报表错误", e);
		}

	}
}
