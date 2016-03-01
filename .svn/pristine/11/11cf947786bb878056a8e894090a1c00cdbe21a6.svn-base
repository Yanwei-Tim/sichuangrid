package com.tianque.plugin.weChat.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期格式处理类
 */
public final class DateUtil {
	private DateUtil() {
	};

	/**
	 * 返回:20100910210637578
	 */
	public static String formate(Date date) {
		if (date == null) {
			return "";
		}
		return String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%1$tL", date);
	}

	/**
	 * 返回:2010-09-10 21:08:17
	 */
	public static String formateYMDHMS(Date date) {
		if (date == null) {
			return "";
		}
		return String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", date);
	}

	/**
	 * 返回:2010_9
	 */
	public static String formateY_M(Date date) {
		if (date == null) {
			return "";
		}
		int month = CalendarUtil.getMonth(date);
		int year = CalendarUtil.getYear(date);

		return year + "_" + month;

	}

	/**
	 * 返回:2010-09-10
	 */
	public static String formateYMD(Date date) {
		if (date == null) {
			return "";
		}
		return String.format("%1$tY-%1$tm-%1$td", date);
	}

	/**
	 * 返回:2010年09月10日
	 */
	public static String formateYMD_CN(Date date) {
		if (date == null) {
			return "";
		}
		return String.format("%1$tY年%1$tm月%1$td日", date);
	}

	/**
	 * 返回:09-10
	 */
	public static String formateMD(Date date) {
		if (date == null) {
			return "";
		}
		return String.format("%1$tm-%1$td", date);
	}

	/**
	 * 返回2010
	 * @param date
	 * @return
	 */
	public static String formateY(Date date) {

		if (date == null) {
			return "";
		}
		return String.format("%1$tY", date);

	}

	/**
	 * 返回:09月10日
	 */
	public static String formateMD_CN(Date date) {
		if (date == null) {
			return "";
		}
		return String.format("%1$tm月%1$td日", date);
	}

	/**
	 * 按照指定的格式进行转化
	 * 
	 * @param d
	 * @param sFormat
	 * @return
	 */
	public static String toString(java.util.Date d, String sFormat) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(sFormat);
		String dateString = formatter.format(d);
		return dateString;
	}

	public static java.util.Date parseDate(String sDate, String sFormat) {
		if (null == sDate || "".equals(sDate)) {
			return null;
		}
		java.text.SimpleDateFormat formatter = null;

		java.util.Date utildate = null;
		try {
			formatter = new java.text.SimpleDateFormat(sFormat);
			utildate = formatter.parse(sDate);
		} catch (ParseException e) {
			utildate = new Date();
		}

		return utildate;
	}

	/**
	 * 获取23:59:59时间点
	 * 
	 * @param Date
	 * @return Date 年月日不变，时分秒改为当天的23:59:59
	 */
	public static Date getEndTime(Date date) {
		if (null == date) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 获取00:00:00时间点
	 * 
	 * @param Date
	 * @return Date 年月日不变，时分秒改为当天的00:00:00
	 */
	public static Date getStartTime(Date date) {
		if (null == date) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		return calendar.getTime();
	}

	//  当前日期减n天后的日期，
	public static Date beforeNowDate(int n) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.add(Calendar.DAY_OF_MONTH, -n);
		return rightNow.getTime();
	}

	public static boolean isActiveTime(Date lastDate) {
		Date nowDate = new Date();//当前时间\r 
		long nowTime = nowDate.getTime();
		long lastTime = lastDate.getTime();//以前的时间\r 
		long time = nowTime - lastTime;//时间相减比较。 
		long temp = time / (1000 * 60);
		if (temp < (48 * 60))
			return true;
		else
			return false;
	}

	public static boolean compareDate(Date firstDate, Date secondDate) {
		long firstTime = firstDate.getTime();
		long secondTime = secondDate.getTime();
		long time = firstTime - secondTime;
		return time > 0 ? true : false;
	}

	/**
	 * 2011-11-11T12:12:12Z格式时间
	 * 获得 2014-12-25 10:59:05
	 * @param String
	 * @return
	 */
	public static String getUTCDate(String str) {
		return str.replace("T", " ").replace("Z", "");
	}

	public static void main(String[] args) {
		//System.out.println(parseDate("2011-11-11 12:12:12", "yyyy-MM-dd HH:mm:ss"));
		//		System.out.println(formate(new Date()));
		//		System.out.println(formateYMDHMS(new Date()));
		//		System.out.println(formateYMD(new Date()));
		//		System.out.println(formateYMD_CN(new Date()));
		//		System.out.println(formateMD(new Date()));
		//		System.out.println(formateMD_CN(new Date()));
		//		System.out.println(getEndTime(new Date()));
		//   System.out.println(beforeNowDate(2).toLocaleString());
	}
}