package com.tianque.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.tianque.statRegrad.util.RegradedPointUtil;

public class StatAnalyseHelper {

	public static final String START_DATE = "startDate";
	public static final String END_TDATE = "endDate";

	public static boolean isNowYearAndAfterNowMonth(int year, int month) {
		return year == CalendarUtil.getNowYear()
				&& month > CalendarUtil.getNowMonth();
	}

	public static String[] getTime() {
		Date nowDate = new Date();
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		nowCalendar.add(Calendar.MONTH, -12);
		String[] time = new String[12];
		for (int i = 0; i < 12; i++) {
			SimpleDateFormat timePattern = new SimpleDateFormat("yyyy-MM");
			time[i] = timePattern.format(nowCalendar.getTime());
			nowCalendar.add(Calendar.MONTH, 1);
		}

		return time;
	}

	public static Map<String, Date> getStartDateAndEndDate(int year, int month,
			String reportDateTypeName) {
		return RegradedPointUtil.getStartDateAndEndDate(year, month,
				reportDateTypeName);
	}

	public static boolean isNowQuarter(Integer month) {
		return month <= CalendarUtil.getNowMonth()
				&& CalendarUtil.getNowMonth() <= (month + 2);
	}

	public static boolean isNowYear(Integer year) {
		return year == CalendarUtil.getNowYear();
	}

	public static boolean isNowMonth(int month) {
		return month == CalendarUtil.getNowMonth();
	}
}
