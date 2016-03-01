package com.tianque.plugin.weChat.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {

	public static final String DEFAULT_FORMAT = "yyyy-MM-dd";

	/***
	 * 默认格式的现在日期
	 * 
	 * @return
	 */
	public static Date now() {
		return new Date();
	}

	/***
	 * 返回指定格式的现在日期
	 * 
	 * @return
	 */
	public static Date now(String format) {
		return formatDate(format, now());
	}

	/***
	 * yyyy-MM-dd 格式的现在日期
	 * 
	 * @return
	 */
	public static Date today() {
		return formatDate(DEFAULT_FORMAT, now());
	}

	/***
	 * yyyy-MM-dd 格式的日期
	 * 
	 * @return
	 */
	public static Date getTomorrow() {
		Calendar c = convertToCalendar(today());
		c.add(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	/***
	 * yyyy-MM-dd 格式的日期
	 * 
	 * @return
	 */
	public static Date getNewDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(c.DAY_OF_MONTH, day);
		return c.getTime();
	}

	/***
	 * 按照指定的格式解析日期字符串
	 * 
	 * @return
	 */
	public static Date parseDate(String dateString, String format) {
		if (!StringUtil.isStringAvaliable(dateString)) {
			return null;
		}
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern(format);
		formater.setLenient(false);
		try {
			return formater.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}

	/***
	 * 按照指定的格式格式化日期
	 * 
	 * @return
	 */
	private static Date formatDate(String format, Date date) {
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern(format);
		formater.setLenient(false);
		try {
			return formater.parse(formater.format(date));
		} catch (ParseException e) {
			return now();
		}
	}

	/***
	 * 返回yyyy-MM-dd 格式的日期字符串
	 * 
	 * @return
	 */
	public static String format(Date date) {
		return format(DEFAULT_FORMAT, date);
	}

	/***
	 * 返回指定格式的日期字符串
	 * 
	 * @return
	 */
	public static String format(String format, Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern(format);
		return formater.format(date);
	}

	/***
	 * 返回今天是星期几 例如：星期一
	 * 
	 * @return
	 */
	public static String getWeekDay() {
		return format("EEEE", today());
	}

	/***
	 * 返回指定的日期是星期几 例如：星期一
	 * 
	 * @return
	 */
	public static String getWeekDay(Date date) {
		return format("EEEE", date);
	}

	/***
	 * 返回现在日期的年份
	 * 
	 * @return
	 */
	public static int getNowYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/***
	 * 返回现在日期的月份
	 * 
	 * @return
	 */
	public static int getNowMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public static int getNextMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		return cal.get(Calendar.MONTH) + 1;
	}

	/***
	 * 返回现在日期的天
	 * 
	 * @return
	 */
	public static int getNowDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	/***
	 * 本月最后一天的天数
	 * @param year
	 * @param month
	 * @return
	 */
	public static int lastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, 1);
		return cal.getMaximum(Calendar.DAY_OF_MONTH);
	}

	public static int getLastYear() {
		return Calendar.getInstance().get(Calendar.YEAR) - 1;
	}

	public static int getLastMonth() {
		Calendar temp = Calendar.getInstance();
		temp.add(Calendar.MONTH, -1);
		return temp.get(Calendar.MONTH) + 1;
	}

	public static void main(String[] args) {
		//		System.out.println(getLastYear() + ":" + getLastMonth());
		//		System.out.println(format(getMonthStart(2013, 12)));
		//		System.out.println(format(getMonthEnd(2013, 12)));
		//		System.out.println(format("yyyy-MM-dd HH:mm:ss", DateUtil.getStartTime(CalendarUtil
		//				.getMonthStart(2014, 3))));
		//		System.out.println(format("yyyy-MM-dd HH:mm:ss", DateUtil.getEndTime(CalendarUtil
		//				.getMonthStart(2014, 3))));
		//		System.out.println(lastDayOfMonth(2014, 5));
		//		for (int i = 1; i < 13; i++) {
		//			//System.out.println(CalendarUtil.getLastMonthEnd(2014, i));
		//		}
		//		
		//		System.out.println();
		System.out.println(getNextMonth());
		System.out.println(CalendarUtil.getDate(CalendarUtil.getLastMonthStart(2015, 3), 26));
		System.out.println(CalendarUtil.getMonthEnd(2015, 3));
		System.out.println(CalendarUtil.getDate(CalendarUtil.getMonthStart(2015, 3), 26));

	}

	public static int getLastMonthYearValue() {
		Calendar temp = Calendar.getInstance();
		temp.add(Calendar.MONTH, -1);
		return temp.get(Calendar.YEAR);
	}

	public static Date getMonthStart(int year, int month) {
		if (0 == year) {
			return null;
		}
		return parseDate(String.valueOf(year) + month, "yyyyM");
	}

	public static Date getNextMonthStart(int year, int month) {
		if (0 == year) {
			return null;
		}
		Calendar temp = getMonthEndCalendar(year, month);
		temp.add(Calendar.DAY_OF_MONTH, 1);
		return temp.getTime();
	}

	public static Date getNextDay(Date date) {
		if (null == date) {
			return null;
		}
		Calendar temp = Calendar.getInstance();
		temp.setTime(date);
		temp.add(Calendar.DAY_OF_MONTH, 1);
		return temp.getTime();
	}

	public static Date getLastDay(Date date) {
		if (null == date) {
			return null;
		}
		Calendar temp = Calendar.getInstance();
		temp.setTime(date);
		temp.add(Calendar.DAY_OF_MONTH, -1);
		return temp.getTime();
	}

	public static Date getLastMonthEnd(int year, int month) {
		if (month == 1) {
			year = year - 1;
			month = 12;
		} else {
			month = month - 1;
		}

		Calendar temp = getMonthEndCalendar(year, month);

		//这样写会导致10月份只获取到30日,31日会丢失
		//temp.add(Calendar.MONTH, -1);
		return temp.getTime();
	}

	public static Date getLastMonthStart(int year, int month) {
		Calendar result = Calendar.getInstance();
		result.setTime(getMonthStart(year, month));
		result.add(Calendar.MONTH, -1);
		return result.getTime();
	}

	public static Date getMonthEnd(int year, int month) {
		return getMonthEndCalendar(year, month).getTime();
	}

	private static Calendar getMonthEndCalendar(int year, int month) {
		Calendar result = Calendar.getInstance();
		result.setTime(getMonthStart(year, month));
		result.set(Calendar.DAY_OF_MONTH, result.getActualMaximum(Calendar.DAY_OF_MONTH));
		return result;
	}

	/***
	 * 返回指定日期的年份
	 * 
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar c = convertToCalendar(date);
		return c.get(Calendar.YEAR);
	}

	/***
	 * 返回指定日期的月份
	 * 
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar c = convertToCalendar(date);
		return c == null ? 1 : c.get(Calendar.MONTH) + 1;
	}

	public static int getDay(Date date) {
		Calendar c = convertToCalendar(date);
		return c == null ? 1 : c.get(Calendar.DAY_OF_MONTH);
	}

	public static Integer getHour(Date date) {
		Calendar c = convertToCalendar(date);
		return c == null ? null : c.get(Calendar.HOUR_OF_DAY);
	}

	public static String getHourStr(Date date) {
		Integer hour = getHour(date);
		if (hour == null) {
			return null;
		}
		return hour.toString().length() > 1 ? hour.toString() : "0" + hour;

	}

	public static Integer getMinute(Date date) {
		Calendar c = convertToCalendar(date);
		return c == null ? null : c.get(Calendar.MINUTE);
	}

	public static String getMinuteStr(Date date) {
		Integer minute = getMinute(date);
		if (minute == null) {
			return null;
		}
		return minute.toString().length() > 1 ? minute.toString() : "0" + minute;
	}

	public static Calendar convertToCalendar(Date date) {
		if (date == null) {
			return null;
		}
		Calendar result = Calendar.getInstance();
		result.setTime(date);
		return result;
	}

	/**
	 * 获取当前月第一天  字符串格式
	 * @param dateFormat
	 * @return
	 */
	public static String getCurrMonthFirstDay(String dateFormat) {

		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		//获取当前月第一天：
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天 
		String first = format.format(c.getTime());

		return first;
	}

	/**
	 * 获取当前月最后一天  字符串格式
	 * @param dateFormat
	 * @return
	 */
	public static String getCurrMonthLastDay(String dateFormat) {

		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		//获取当前月最后一天
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = format.format(ca.getTime());

		return last;
	}

	/**
	 * 得到当前周  周一的日期
	 * 
	 * @return
	 */
	public static Date getMonday() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
		return cal.getTime();
	}

	/**
	 * 得到当前月 第一天的日期
	 * 
	 * @return
	 */
	public static Date getCurrMonthFirstDay() {
		//获取当前月第一天：
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天 
		return c.getTime();
	}

	/**
	 * 获取当前月最后一天
	 * @param dateFormat
	 * @return
	 */
	public static Date getCurrMonthLastDay() {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		return ca.getTime();
	}

	//某年第一天
	public static Date getYearFirtDay(int year) {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.YEAR, year);
		ca.set(Calendar.DAY_OF_YEAR, 1);// 本年第一天
		return ca.getTime();
	}

	//今年第一天
	public static Date getCurrentYearFirtDay() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_YEAR, 1);// 本年第一天
		return c.getTime();
	}

	//某年最后一天
	public static Date getYearLastDay(int year) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.add(Calendar.YEAR, 1);
		c.set(Calendar.DAY_OF_YEAR, 1);// 本年最后一天
		c.add(Calendar.DAY_OF_YEAR, -1);
		return c.getTime();
	}

	//今年最后一天
	public static Date getCurrentYearLastDay() {
		//		Calendar c = Calendar.getInstance();
		//		c.add(Calendar.YEAR, 1);
		//		c.set(Calendar.DAY_OF_YEAR, 1);// 本年最后一天
		//		c.add(Calendar.DAY_OF_YEAR, -1);
		//		return c.getTime();
		return getYearLastDay(getNowYear());
	}

	/***
	 * 两个时间的差
	 * @param max
	 * @param min
	 * @return
	 */
	public static int getMinusDay(Date max, Date min) {
		Date d1 = formatDate(DEFAULT_FORMAT, max);
		Date d2 = formatDate(DEFAULT_FORMAT, min);
		long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
		Long days = diff / (1000 * 60 * 60 * 24);
		return (days.intValue() + 1);
	}

	public static int getMinusDay(Date min) {
		return getMinusDay(now(), min);
	}

	public static Date getDate(int year, int month, int day) {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.YEAR, year);
		ca.set(Calendar.MONTH, month);
		ca.set(Calendar.DAY_OF_MONTH, day);
		return ca.getTime();
	}

	public static Date getDateEnd(Date date, int day) {
		Calendar result = Calendar.getInstance();
		result.setTime(getDate(date, day));
		result.set(Calendar.DAY_OF_MONTH, result.getActualMaximum(Calendar.DAY_OF_MONTH));
		return result.getTime();
	}

	public static Date getDate(Date date, int day) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_MONTH, day);
		return ca.getTime();
	}
}
