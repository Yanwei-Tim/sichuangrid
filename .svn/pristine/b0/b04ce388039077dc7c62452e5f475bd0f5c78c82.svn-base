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

public class WorkingRecordForYearAlgorithm implements WorkingRecordStrategy {

	private PropertyDictService propertyDictService;

	public WorkingRecordForYearAlgorithm(PropertyDictService propertyDictService) {
		this.propertyDictService = propertyDictService;
	}

	@Override
	public String[][] transform(List<ReportWorkingRecord> reportWorking,
			String[][] reportWorkingRecord, int iteration, Date dealDate) {
		int nowMonth = DateForWorkingRecordUtil.acquireMonth(null) + 1;
		for (int recordNum = 0; recordNum < reportWorking.size(); recordNum++) {
			PropertyDict dict = propertyDictService.getPropertyDictByDomainNameAndDictId(
					PropertyTypes.WORKING_RECORD_SUBMITSTATE, reportWorking.get(recordNum)
							.getSubmitState().getId());
			if (dict.getInternalId() == 1) {
				reportWorkingRecord[iteration][2] = WorkingRecordDealStat.SUBMIT;
			}
		}

		if (reportWorkingRecord[iteration][2] == null) {
			if (DateForWorkingRecordUtil.isNowYear(dealDate)) {
				if (nowMonth == 12) {
					reportWorkingRecord[iteration][2] = WorkingRecordDealStat.CAN_SUMBIT;
				}
			} else {
				if (nowMonth >= 1 && nowMonth <= 11) {
					reportWorkingRecord[iteration][2] = WorkingRecordDealStat.CAN_SUMBIT;
				} else {
					reportWorkingRecord[iteration][2] = WorkingRecordDealStat.NOT_SUBMIT;
				}
			}
		}

		return reportWorkingRecord;
	}

	@Override
	public String[][] createReportWorkingRecord(String[][] reportWorkingRecord, int iteration,
			Date dealDate) {
		int month = DateForWorkingRecordUtil.acquireMonth(null) + 1;
		if (DateForWorkingRecordUtil.isNowYear(dealDate)) {
			if (month == 12) {
				reportWorkingRecord[iteration][2] = WorkingRecordDealStat.CAN_SUMBIT;
			}
		} else {
			if (month >= 1 && month <= 11) {
				reportWorkingRecord[iteration][2] = WorkingRecordDealStat.CAN_SUMBIT;
			} else {
				reportWorkingRecord[iteration][2] = WorkingRecordDealStat.NOT_SUBMIT;
			}
		}

		return reportWorkingRecord;
	}

}
