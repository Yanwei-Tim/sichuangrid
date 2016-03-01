package com.tianque.working.domain;

import java.util.Calendar;
import java.util.Date;

public class ExpirationDate {
	public static Date getMonthOfHalfAYear(Long reportTime, Integer end, int deadLineStart) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, end);
		if (TimeLimitHelper.LAST_MONTH_OF_FIRST_HALF_A_YEAR == deadLineStart) {
			if (reportTime == 1) {
				calendar.set(Calendar.MONTH, 5);
			}
			if (reportTime == 2) {
				calendar.set(Calendar.MONTH, 11);
			}
		} else {
			if (reportTime == 1) {
				calendar.set(Calendar.MONTH, 6);
			}
			if (reportTime == 2) {
				calendar.set(Calendar.MONTH, 0);
				calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
			}
		}
		return calendar.getTime();
	}

	public static Date getMonthOfQuarter(Long reportTime, Integer end, int deadLineStart) {
		Calendar calendar = Calendar.getInstance();
		if (TimeLimitHelper.LAST_MONTH_OF_THIS_QUARTER == deadLineStart) {
			if (reportTime == 1) {
				calendar.set(Calendar.MONTH, 2);
			}
			if (reportTime == 2) {
				calendar.set(Calendar.MONTH, 5);
			}
			if (reportTime == 3) {
				calendar.set(Calendar.MONTH, 8);
			}
			if (reportTime == 4) {
				calendar.set(Calendar.MONTH, 11);
			}
		} else {
			if (reportTime == 1) {
				calendar.set(Calendar.MONTH, 3);
			}
			if (reportTime == 2) {
				calendar.set(Calendar.MONTH, 6);
			}
			if (reportTime == 3) {
				calendar.set(Calendar.MONTH, 9);
			}
			if (reportTime == 4) {
				calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
				calendar.set(Calendar.MONTH, 0);
			}
		}
		calendar.set(Calendar.DAY_OF_MONTH, end);
		return calendar.getTime();
	}

	public static Date getMonth(Long reportTime, Integer end, int deadLineStart) {
		Calendar calendar = Calendar.getInstance();
		if (TimeLimitHelper.THIS_MONTH == deadLineStart) {
			calendar.set(Calendar.MONTH, reportTime.intValue() - 1);
		} else {
			calendar.set(Calendar.MONTH, reportTime.intValue());
		}
		calendar.set(Calendar.DAY_OF_MONTH, end);
		return calendar.getTime();
	}

}
