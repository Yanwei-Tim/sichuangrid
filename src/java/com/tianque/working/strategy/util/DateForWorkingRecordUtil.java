package com.tianque.working.strategy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tianque.working.domain.ReportWorkingRecord;

public class DateForWorkingRecordUtil {

	private DateForWorkingRecordUtil() {

	}

	public static boolean isNowYear(Date dealDate) {
		return compareYear(dealDate) == 0;
	}

	public static int compareYear(Date dealDate) {
		int year = Integer.valueOf(new SimpleDateFormat("yyyy").format(dealDate));
		int nowYear = Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date()));
		int compare = 0;
		if (year > nowYear) {
			compare = -1;
		}
		if (year < nowYear) {
			compare = 1;
		}
		return compare;
	}

	public static int getThisHalfYearTime(int month) {
		int halfYear = 0;
		if (month >= 1 && month <= 6) {
			halfYear = 1;
		}
		if (month >= 7 && month <= 12) {
			halfYear = 2;
		}
		return halfYear;
	}

	public static int getThisSeasonTime(int month) {
		int quarter = 0;
		if (month >= 1 && month <= 3) {
			quarter = 1;
		}
		if (month >= 4 && month <= 6) {
			quarter = 2;
		}
		if (month >= 7 && month <= 9) {
			quarter = 3;
		}
		if (month >= 10 && month <= 12) {
			quarter = 4;
		}
		return quarter;
	}

	/**
	 * 返回月份-1
	 */
	@SuppressWarnings("deprecation")
	public static int acquireMonth(ReportWorkingRecord reportWorkingRecord) {
		Date now = new Date();
		if (reportWorkingRecord != null) {
			if (reportWorkingRecord.getReportTime() != 0) {
				return Integer.parseInt((reportWorkingRecord.getReportTime() - 1) + "");
			} else {
				return now.getMonth();
			}
		} else {
			return now.getMonth();
		}
	}
}
