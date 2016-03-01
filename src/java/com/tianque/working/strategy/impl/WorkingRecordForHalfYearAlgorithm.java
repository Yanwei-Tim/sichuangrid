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

public class WorkingRecordForHalfYearAlgorithm implements WorkingRecordStrategy {

	private PropertyDictService propertyDictService;

	public WorkingRecordForHalfYearAlgorithm(PropertyDictService propertyDictService) {
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
			sun = 2;
		} else {
			sun = DateForWorkingRecordUtil.getThisHalfYearTime(DateForWorkingRecordUtil
					.acquireMonth(null) + 1);
		}
		return sun;
	}

	private void equalNowYearWhenCreate(String[][] reportWorkingRecord, int iteration, Date dealDate) {
		int month = DateForWorkingRecordUtil.acquireMonth(null) + 1;
		for (int halfYear = 2; halfYear < createSun(dealDate) + 2; halfYear++) {
			if (month >= 6 && month <= 11 && halfYear == 2) {
				reportWorkingRecord[iteration][halfYear] = WorkingRecordDealStat.CAN_SUMBIT;
			} else if (month == 12) {
				reportWorkingRecord[iteration][halfYear] = WorkingRecordDealStat.CAN_SUMBIT;
			}
		}
	}

	private void unEqualNowYearWhenCreate(String[][] reportWorkingRecord, int iteration,
			Date dealDate) {
		int month = DateForWorkingRecordUtil.acquireMonth(null) + 1;
		for (int halfYear = 2; halfYear < createSun(dealDate) + 2; halfYear++) {
			if (month >= 1 && month <= 5) {
				reportWorkingRecord[iteration][halfYear] = WorkingRecordDealStat.CAN_SUMBIT;
			} else if ((month >= 6 && month <= 11) && halfYear == 3) {
				reportWorkingRecord[iteration][halfYear] = WorkingRecordDealStat.CAN_SUMBIT;
			} else {
				reportWorkingRecord[iteration][halfYear] = WorkingRecordDealStat.NOT_SUBMIT;
			}
		}
	}

	private void equalNowYear(List<ReportWorkingRecord> reportWorking,
			String[][] reportWorkingRecord, int y, Date dealDate) {
		// 先把已经上报的状态设置为已上报
		int sun = createSun(dealDate);
		int nowMonth = DateForWorkingRecordUtil.acquireMonth(null) + 1;
		for (int recordNum = 0; recordNum < reportWorking.size(); recordNum++) {
			PropertyDict dict = propertyDictService.getPropertyDictByDomainNameAndDictId(
					PropertyTypes.WORKING_RECORD_SUBMITSTATE, reportWorking.get(recordNum)
							.getSubmitState().getId());
			int halfDate = (int) reportWorking.get(recordNum).getReportTime();
			if (dict.getInternalId() == 1
					&& halfDate <= DateForWorkingRecordUtil.getThisHalfYearTime(nowMonth)) {
				reportWorkingRecord[y][halfDate + 1] = WorkingRecordDealStat.SUBMIT;
			}
		}
		for (int halfYear = 0; halfYear < sun; halfYear++) {
			if (reportWorkingRecord[y][halfYear + 2] == null) {
				if (nowMonth >= 6 && nowMonth <= 11 && halfYear == 0) {
					reportWorkingRecord[y][halfYear + 2] = WorkingRecordDealStat.CAN_SUMBIT;
				} else if (nowMonth == 12) {
					reportWorkingRecord[y][halfYear + 2] = WorkingRecordDealStat.CAN_SUMBIT;
				}
			}
		}
	}

	private void unEqualNowYear(List<ReportWorkingRecord> reportWorking,
			String[][] reportWorkingRecord, int y, Date dealDate) {
		// 先把已经上报的状态设置为已上报
		int sun = createSun(dealDate);
		int nowMonth = DateForWorkingRecordUtil.acquireMonth(null) + 1;
		for (int recordNum = 0; recordNum < reportWorking.size(); recordNum++) {
			PropertyDict dict = propertyDictService.getPropertyDictByDomainNameAndDictId(
					PropertyTypes.WORKING_RECORD_SUBMITSTATE, reportWorking.get(recordNum)
							.getSubmitState().getId());
			int halfDate = (int) reportWorking.get(recordNum).getReportTime();
			if (dict.getInternalId() == 1) {
				reportWorkingRecord[y][halfDate + 1] = WorkingRecordDealStat.SUBMIT;
			}
		}
		for (int halfYear = 0; halfYear < sun; halfYear++) {
			if (reportWorkingRecord[y][halfYear + 2] == null) {
				if (nowMonth >= 1 && nowMonth <= 5) {
					reportWorkingRecord[y][halfYear + 2] = WorkingRecordDealStat.CAN_SUMBIT;
				} else if ((nowMonth >= 6 && nowMonth <= 11) && halfYear == sun - 1) {
					reportWorkingRecord[y][halfYear + 2] = WorkingRecordDealStat.CAN_SUMBIT;
				} else {
					reportWorkingRecord[y][halfYear + 2] = WorkingRecordDealStat.NOT_SUBMIT;
				}
			}
		}
	}

}
