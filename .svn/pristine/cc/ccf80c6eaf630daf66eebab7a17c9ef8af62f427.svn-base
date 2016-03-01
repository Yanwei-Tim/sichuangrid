package com.tianque.plugin.weChat.util;

import java.util.Date;

import org.joda.time.DateTime;

public class TimeUtil {
	/**根据传入的天数（负数为往前算）和当前日期计算几天后（前）的日期*/
	public static Date getOtherDay(Date date, Integer days) {
		DateTime dt = new DateTime(date);
		dt = dt.dayOfMonth().addToCopy(days);
		date = dt.toDate();
		return date;
	}
}
