package com.tianque.working.strategy.impl;

import java.util.Date;
import java.util.List;

import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.constants.WorkingRecordDealStat;
import com.tianque.working.domain.ReportWorkingRecord;
import com.tianque.working.strategy.WorkingRecordStrategy;
import com.tianque.working.strategy.util.DateForWorkingRecordUtil;

public class WorkingRecordForMonthAlgorithm implements WorkingRecordStrategy {

	private PropertyDictService propertyDictService;

	public WorkingRecordForMonthAlgorithm(PropertyDictService propertyDictService) {
		this.propertyDictService = propertyDictService;
	}

	@Override
	public String[][] transform(List<ReportWorkingRecord> reportWorking,
			String[][] reportWorkingRecord, int iteration, Date dealDate) {
		if (DateForWorkingRecordUtil.isNowYear(dealDate)) {
			equalNowYear(reportWorking, reportWorkingRecord, iteration, dealDate);
		} else {
			unEqualNowYear(reportWorking, reportWorkingRecord, iteration, dealDate);
		}
		return reportWorkingRecord;
	}

	@Override
	public String[][] createReportWorkingRecord(String[][] reportWorkingRecord, int iteration,
			Date dealDate) {
		if (DateForWorkingRecordUtil.isNowYear(dealDate)) {
			equalNowYearWhenCreate(reportWorkingRecord, iteration, dealDate);
		} else {
			unEqualNowYearWhenCreate(reportWorkingRecord, iteration, dealDate);
		}
		return reportWorkingRecord;
	}

	private int createSun(Date dealDate) {
		int sun = 0;
		if (!DateForWorkingRecordUtil.isNowYear(dealDate)) {
			sun = 12;
		} else {
			sun = DateForWorkingRecordUtil.acquireMonth(null) + 1;
		}
		return sun;
	}

	private void equalNowYearWhenCreate(String[][] reportWorkingRecord, int iteration, Date dealDate) {
		int sun = createSun(dealDate);
		for (int month = 2; month < sun + 2; month++) {
			if ((month == DateForWorkingRecordUtil.acquireMonth(null) + 1 || month == DateForWorkingRecordUtil
					.acquireMonth(null) + 2)) {
				reportWorkingRecord[iteration][month] = WorkingRecordDealStat.CAN_SUMBIT;
			} else {
				reportWorkingRecord[iteration][month] = WorkingRecordDealStat.NOT_SUBMIT;
			}
		}

	}

	private void unEqualNowYearWhenCreate(String[][] reportWorkingRecord, int iteration,
			Date dealDate) {
		int sun = createSun(dealDate);
		for (int month = 2; month < sun + 2; month++) {
			if (month == sun + 1 && DateForWorkingRecordUtil.acquireMonth(null) == 0) {
				reportWorkingRecord[iteration][month] = WorkingRecordDealStat.CAN_SUMBIT;
			} else {
				reportWorkingRecord[iteration][month] = WorkingRecordDealStat.NOT_SUBMIT;
			}
		}
	}

	private void equalNowYear(List<ReportWorkingRecord> reportWorking,
			String[][] reportWorkingRecord, int y, Date dealDate) {
		// 先把已经上报的状态设置为已上报
		int sun = createSun(dealDate);
		for (int recordNum = 0; recordNum < reportWorking.size(); recordNum++) {
			PropertyDict dict = propertyDictService.getPropertyDictByDomainNameAndDictId(
					PropertyTypes.WORKING_RECORD_SUBMITSTATE, reportWorking.get(recordNum)
							.getSubmitState().getId());
			int monthDate = (int) reportWorking.get(recordNum).getReportTime();
			if (dict.getInternalId() == 1
					&& monthDate <= DateForWorkingRecordUtil.acquireMonth(null) + 1) {
				reportWorkingRecord[y][monthDate + 1] = WorkingRecordDealStat.SUBMIT;
			}
		}
		for (int month = 0; month < sun; month++) {
			if (reportWorkingRecord[y][month + 2] == null) {
				if (DateForWorkingRecordUtil.acquireMonth(null) == month
						|| DateForWorkingRecordUtil.acquireMonth(null) - 1 == month) {
					reportWorkingRecord[y][month + 2] = WorkingRecordDealStat.CAN_SUMBIT;
				} else {
					reportWorkingRecord[y][month + 2] = WorkingRecordDealStat.NOT_SUBMIT;
				}
			}
		}
	}

	private void unEqualNowYear(List<ReportWorkingRecord> reportWorking,
			String[][] reportWorkingRecord, int y, Date dealDate) {
		// 先把已经上报的状态设置为已上报
		int sun = createSun(dealDate);
		for (int recordNum = 0; recordNum < reportWorking.size(); recordNum++) {
			PropertyDict dict = propertyDictService.getPropertyDictByDomainNameAndDictId(
					PropertyTypes.WORKING_RECORD_SUBMITSTATE, reportWorking.get(recordNum)
							.getSubmitState().getId());
			int monthDate = DateForWorkingRecordUtil.acquireMonth(reportWorking.get(recordNum)) + 1;
			if (dict.getInternalId() == 1) {
				reportWorkingRecord[y][monthDate + 1] = WorkingRecordDealStat.SUBMIT;
			}
		}
		for (int month = 0; month < sun; month++) {
			if (reportWorkingRecord[y][month + 2] == null) {
				if (11 == month && DateForWorkingRecordUtil.acquireMonth(null) == 0) {
					reportWorkingRecord[y][month + 2] = WorkingRecordDealStat.CAN_SUMBIT;
				} else {
					reportWorkingRecord[y][month + 2] = WorkingRecordDealStat.NOT_SUBMIT;
				}
			}
		}
	}

}
