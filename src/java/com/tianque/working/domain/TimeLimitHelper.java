package com.tianque.working.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 工作台帐目录 时限字段辅助类
 */
public class TimeLimitHelper {

	/** 无 */
	public static final int NO_TIME_LIMIT = 1;
	/** 有效天数 */
	public static final int EFFECTIVE_DAYS = 2;
	/** 截止时间 */
	public static final int DEADLINE_TYPE = 3;

	/** 普通台账 */
	public static final int ORDINARY_LEDGER = 1;
	/** 按月上报台账 */
	public static final int REPORTED_LEDGER_MONTHLY = 2;
	/** 按季度上报台账 */
	public static final int REPORTED_LEDGER_QUARTERLY = 3;
	/** 按半年上报台账 */
	public static final int REPORTED_LEDGER_YEAR = 4;

	/** 本月 */
	public static final int THIS_MONTH = 1;
	/** 次月 */
	public static final int NEXT_MONTH = 2;
	/** 本季度末月 */
	public static final int LAST_MONTH_OF_THIS_QUARTER = 3;
	/** 下季度第一个月 */
	public static final int FIRST_MONTH_OF_NEXT_QUARTER = 4;
	/** 本半年末月 */
	public static final int LAST_MONTH_OF_FIRST_HALF_A_YEAR = 5;
	/** 次半年首月 */
	public static final int FIRST_MONTH_OF_NEXT_HALF_A_YEAR = 6;

	public static Map getTimeLimitMap() {
		Map map = new HashMap();
		map.put(NO_TIME_LIMIT, "无");
		map.put(EFFECTIVE_DAYS, "有效天数");
		map.put(DEADLINE_TYPE, "截止时间");
		return map;
	}

	public static Map getDeadlineTypeMap() {
		Map map = new HashMap();
		map.put(ORDINARY_LEDGER, "普通台账");
		map.put(REPORTED_LEDGER_MONTHLY, "按月上报台账 ");
		map.put(REPORTED_LEDGER_QUARTERLY, "按季度上报台账");
		map.put(REPORTED_LEDGER_YEAR, "按半年上报台账");
		return map;
	}

	public static Map getDeadlineStartTypeMap() {
		Map map = new HashMap();
		map.put(THIS_MONTH, "本月");
		map.put(NEXT_MONTH, "次月 ");
		map.put(LAST_MONTH_OF_THIS_QUARTER, "本季度末月");
		map.put(FIRST_MONTH_OF_NEXT_QUARTER, "下季度第一个月 ");
		map.put(LAST_MONTH_OF_FIRST_HALF_A_YEAR, "本半年末月");
		map.put(FIRST_MONTH_OF_NEXT_HALF_A_YEAR, "次半年首月");
		return map;
	}

	public static Date getEndDate(DailyDirectory dailyDirectory) {
		Date targetDate = null;
		if (null != dailyDirectory.getTimeLimit() && dailyDirectory.getTimeLimit() == DEADLINE_TYPE) {
			switch (dailyDirectory.getDeadlineType()) {
			case ORDINARY_LEDGER:
				if (dailyDirectory.getDeadlineDate() != null) {
					targetDate = dailyDirectory.getDeadlineDate();
				}
				break;
			case REPORTED_LEDGER_MONTHLY:
				if (dailyDirectory.getDeadlineStart() == THIS_MONTH) {
					targetDate = getThisMonth(dailyDirectory.getDeadlineEnd());
				}
				if (dailyDirectory.getDeadlineStart() == NEXT_MONTH) {
					targetDate = getNextMonth(dailyDirectory.getDeadlineEnd());
				}
				break;
			case REPORTED_LEDGER_QUARTERLY:
				if (dailyDirectory.getDeadlineStart() == LAST_MONTH_OF_THIS_QUARTER) {
					targetDate = getLastMonthOfThisQuarter(dailyDirectory.getDeadlineEnd());
				}
				if (dailyDirectory.getDeadlineStart() == FIRST_MONTH_OF_NEXT_QUARTER) {
					targetDate = getFirstMonthOfNextQuarter(dailyDirectory.getDeadlineEnd());
				}
				break;
			case REPORTED_LEDGER_YEAR:
				if (dailyDirectory.getDeadlineStart() == LAST_MONTH_OF_FIRST_HALF_A_YEAR) {
					targetDate = getLastMonthOfThisHalfAYear(dailyDirectory.getDeadlineEnd());
				}
				if (dailyDirectory.getDeadlineStart() == FIRST_MONTH_OF_NEXT_HALF_A_YEAR) {
					targetDate = getFirstMonthOfNextHalfAYear(dailyDirectory.getDeadlineEnd());
				}
				break;
			default:
				break;
			}
		}
		return targetDate;
	}

	private static Date getFirstMonthOfNextHalfAYear(Integer end) {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, end);
		if (month >= 0 && month <= 5) {
			calendar.set(Calendar.MONTH, 6);
		}
		if (month >= 6 && month <= 11) {
			calendar.set(Calendar.MONTH, 0);
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
		}
		return calendar.getTime();
	}

	private static Date getLastMonthOfThisHalfAYear(Integer end) {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, end);
		if (month >= 0 && month <= 5) {
			calendar.set(Calendar.MONTH, 5);
		}
		if (month >= 6 && month <= 11) {
			calendar.set(Calendar.MONTH, 11);
		}
		return calendar.getTime();
	}

	private static Date getFirstMonthOfNextQuarter(Integer end) {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, end);
		if (month >= 0 && month <= 2) {
			calendar.set(Calendar.MONTH, 3);
		}
		if (month >= 3 && month <= 5) {
			calendar.set(Calendar.MONTH, 6);
		}
		if (month >= 6 && month <= 8) {
			calendar.set(Calendar.MONTH, 9);
		}
		if (month >= 9 && month <= 11) {
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
			calendar.set(Calendar.MONTH, 0);
		}
		return calendar.getTime();
	}

	private static Date getLastMonthOfThisQuarter(Integer end) {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, end);
		if (month >= 0 && month <= 2) {
			calendar.set(Calendar.MONTH, 2);
		}
		if (month >= 3 && month <= 5) {
			calendar.set(Calendar.MONTH, 5);
		}
		if (month >= 6 && month <= 8) {
			calendar.set(Calendar.MONTH, 8);
		}
		if (month >= 9 && month <= 11) {
			calendar.set(Calendar.MONTH, 11);
		}
		return calendar.getTime();
	}

	private static Date getNextMonth(Integer end) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, end);
		return calendar.getTime();
	}

	private static Date getThisMonth(Integer end) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, end);
		return calendar.getTime();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test1() {
		DailyDirectory dailyDirectory = new DailyDirectory();
		dailyDirectory.setTimeLimit(3);
		dailyDirectory.setDeadlineType(1);
		Date dateNow = new Date();
		dateNow.setDate(30);
		dailyDirectory.setDeadlineDate(dateNow);
		Date date = getEndDate(dailyDirectory);
		System.out.println(date);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test2() {
		DailyDirectory dailyDirectory = new DailyDirectory();
		dailyDirectory.setTimeLimit(3);
		dailyDirectory.setDeadlineType(2);
		dailyDirectory.setDeadlineStart(1);
		dailyDirectory.setDeadlineEnd(5);
		Date dateNow = new Date();
		dateNow.setDate(30);
		dailyDirectory.setDeadlineDate(dateNow);
		Date date = getEndDate(dailyDirectory);
		System.out.println(date);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test3() {
		DailyDirectory dailyDirectory = new DailyDirectory();
		dailyDirectory.setTimeLimit(3);
		dailyDirectory.setDeadlineType(2);
		dailyDirectory.setDeadlineStart(2);
		dailyDirectory.setDeadlineEnd(5);
		Date dateNow = new Date();
		dateNow.setDate(30);
		dailyDirectory.setDeadlineDate(dateNow);
		Date date = getEndDate(dailyDirectory);
		System.out.println(date);
	}

}
