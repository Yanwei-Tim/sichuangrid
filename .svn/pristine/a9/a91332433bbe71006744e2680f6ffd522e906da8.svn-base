package com.tianque.baseInfo.utils;

import java.util.Calendar;
import java.util.Date;

public class CustomDateUtil {
	/** 规定多少天迁移数据 **/
	public static final int DAYS_CHANGE = 60;
	/** 提前多少天 **/
	public static final int DAYS_BEFORE = 20;

	/***
	 * 获取当前时间往前推多少年 year年前日期
	 * 
	 * @param year
	 * @return
	 */
	public static Date dateBeforeNowDate(int year) {
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) - year);
		Date date = curr.getTime();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return date;
	}

	/***
	 * 获取当前时间往前推多少年 year年前日期
	 * 
	 * @param year
	 * @return
	 */
	public static Date dateBeforeNowDateByMonth(int month) {
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.MONTH, curr.get(Calendar.MONTH) - month);
		Date date = curr.getTime();
		return date;
	}

	/***
	 * 获取当前时间往前推多少天 day前日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date dateBeforeNowDateByDays(int days) {
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.DATE, curr.get(Calendar.DATE) - days);
		Date date = curr.getTime();
		return date;
	}

}
