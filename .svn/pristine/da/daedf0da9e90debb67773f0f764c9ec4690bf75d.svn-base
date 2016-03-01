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

public class WorkingRecordForQuarterAlgorithm implements WorkingRecordStrategy {

	private PropertyDictService propertyDictService;

	public WorkingRecordForQuarterAlgorithm(PropertyDictService propertyDictService) {
		this.propertyDictService = propertyDictService;
	}

	@Override
	public String[][] transform(List<ReportWorkingRecord> reportWorking,
			String[][] reportWorkingRecord, int iteration, Date dealDate) {
		if (DateForWorkingRecordUtil.isNowYear(dealDate)) {
			equalNowYearForQuarter(reportWorking, reportWorkingRecord, iteration, dealDate);
		} else {
			unEqualNowYearForQuarter(reportWorking, reportWorkingRecord, iteration, dealDate);
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
		int month = DateForWorkingRecordUtil.acquireMonth(null) + 1;
		if (!DateForWorkingRecordUtil.isNowYear(dealDate)) {
			sun = 4;
		} else {
			// 如果没到本季度最后一个月，则本季度不可催报
			if (month % 3 == 0) {
				sun = DateForWorkingRecordUtil.getThisSeasonTime(DateForWorkingRecordUtil
						.acquireMonth(null) + 1);
			} else {
				sun = DateForWorkingRecordUtil.getThisSeasonTime(DateForWorkingRecordUtil
						.acquireMonth(null) + 1) - 1;
			}
		}
		return sun;
	}

	private void equalNowYearWhenCreate(String[][] reportWorkingRecord, int iteration, Date dealDate) {
		int month = DateForWorkingRecordUtil.acquireMonth(null) + 1;
		for (int quarter = 2; quarter < createSun(dealDate) + 2; quarter++) {
			if (month % 3 == 0) {
				if ((quarter == DateForWorkingRecordUtil.getThisSeasonTime(month) || quarter == DateForWorkingRecordUtil
						.getThisSeasonTime(month) + 1)) {
					reportWorkingRecord[iteration][quarter] = WorkingRecordDealStat.CAN_SUMBIT;
				} else {
					reportWorkingRecord[iteration][quarter] = WorkingRecordDealStat.NOT_SUBMIT;
				}
			} else {
				if ((quarter == DateForWorkingRecordUtil.getThisSeasonTime(month) || quarter == DateForWorkingRecordUtil
						.getThisSeasonTime(month) - 1)) {
					reportWorkingRecord[iteration][quarter] = WorkingRecordDealStat.CAN_SUMBIT;
				} else {
					reportWorkingRecord[iteration][quarter] = WorkingRecordDealStat.NOT_SUBMIT;
				}
			}
		}
	}

	private void unEqualNowYearWhenCreate(String[][] reportWorkingRecord, int iteration,
			Date dealDate) {
		int sun = createSun(dealDate);
		int month = DateForWorkingRecordUtil.acquireMonth(null) + 1;
		for (int quarter = 2; quarter < sun + 2; quarter++) {
			if (month >= 1 && month <= 2) {
				if ((quarter == sun || quarter == sun + 1)) {
					reportWorkingRecord[iteration][quarter] = WorkingRecordDealStat.CAN_SUMBIT;
				} else {
					reportWorkingRecord[iteration][quarter] = WorkingRecordDealStat.NOT_SUBMIT;
				}
			} else if (month >= 3 && month <= 5) {
				if (quarter == sun + 1) {
					reportWorkingRecord[iteration][quarter] = WorkingRecordDealStat.CAN_SUMBIT;
				} else {
					reportWorkingRecord[iteration][quarter] = WorkingRecordDealStat.NOT_SUBMIT;
				}
			} else {
				reportWorkingRecord[iteration][quarter] = WorkingRecordDealStat.NOT_SUBMIT;
			}
		}
	}

	private void equalNowYearForQuarter(List<ReportWorkingRecord> reportWorking,
			String[][] reportWorkingRecord, int y, Date dealDate) {
		// 先把已经上报的状态设置为已上报
		int sun = createSun(dealDate);
		int nowMonth = DateForWorkingRecordUtil.acquireMonth(null) + 1;
		for (int recordNum = 0; recordNum < reportWorking.size(); recordNum++) {
			PropertyDict dict = propertyDictService.getPropertyDictByDomainNameAndDictId(
					PropertyTypes.WORKING_RECORD_SUBMITSTATE, reportWorking.get(recordNum)
							.getSubmitState().getId());
			int quarterDate = (int) reportWorking.get(recordNum).getReportTime();
			if (dict.getInternalId() == 1
					&& quarterDate <= DateForWorkingRecordUtil.getThisSeasonTime(nowMonth)) {
				reportWorkingRecord[y][quarterDate + 1] = WorkingRecordDealStat.SUBMIT;
			}
		}
		for (int quarter = 0; quarter < sun; quarter++) {
			if (reportWorkingRecord[y][quarter + 2] == null) {
				if (nowMonth % 3 == 0) {
					if (DateForWorkingRecordUtil.getThisSeasonTime(nowMonth) - 1 == quarter
							|| DateForWorkingRecordUtil.getThisSeasonTime(nowMonth) - 2 == quarter) {
						reportWorkingRecord[y][quarter + 2] = WorkingRecordDealStat.CAN_SUMBIT;
					} else {
						reportWorkingRecord[y][quarter + 2] = WorkingRecordDealStat.NOT_SUBMIT;
					}
				} else {
					if (DateForWorkingRecordUtil.getThisSeasonTime(nowMonth) - 2 == quarter
							|| DateForWorkingRecordUtil.getThisSeasonTime(nowMonth) - 3 == quarter) {
						reportWorkingRecord[y][quarter + 2] = WorkingRecordDealStat.CAN_SUMBIT;
					} else {
						reportWorkingRecord[y][quarter + 2] = WorkingRecordDealStat.NOT_SUBMIT;
					}
				}
			}
		}
	}

	private void unEqualNowYearForQuarter(List<ReportWorkingRecord> reportWorking,
			String[][] reportWorkingRecord, int y, Date dealDate) {
		// 先把已经上报的状态设置为已上报
		int sun = createSun(dealDate);
		int nowMonth = DateForWorkingRecordUtil.acquireMonth(null) + 1;
		for (int recordNum = 0; recordNum < reportWorking.size(); recordNum++) {
			PropertyDict dict = propertyDictService.getPropertyDictByDomainNameAndDictId(
					PropertyTypes.WORKING_RECORD_SUBMITSTATE, reportWorking.get(recordNum)
							.getSubmitState().getId());
			int quarterDate = (int) reportWorking.get(recordNum).getReportTime();
			if (dict.getInternalId() == 1) {
				reportWorkingRecord[y][quarterDate + 1] = WorkingRecordDealStat.SUBMIT;
			}
		}
		for (int quarter = 0; quarter < sun; quarter++) {
			if (reportWorkingRecord[y][quarter + 2] == null) {
				if (nowMonth >= 1 && nowMonth <= 2) {
					if (quarter == sun - 1 || quarter == sun - 2) {
						reportWorkingRecord[y][quarter + 2] = WorkingRecordDealStat.CAN_SUMBIT;
					} else {
						reportWorkingRecord[y][quarter + 2] = WorkingRecordDealStat.NOT_SUBMIT;
					}
				} else if (nowMonth >= 3 && nowMonth <= 5) {
					if (quarter == sun - 1) {
						reportWorkingRecord[y][quarter + 2] = WorkingRecordDealStat.CAN_SUMBIT;
					} else {
						reportWorkingRecord[y][quarter + 2] = WorkingRecordDealStat.NOT_SUBMIT;
					}
				} else {
					reportWorkingRecord[y][quarter + 2] = WorkingRecordDealStat.NOT_SUBMIT;
				}
			}
		}
	}
}
