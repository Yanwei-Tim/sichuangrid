package com.tianque.domain;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.CalendarUtil;

@SuppressWarnings("serial")
public class WorkCalendar extends BaseDomain {
	private Long year;

	private Long month;

	private Long day;

	private Long dayofyear;

	private Long holiday;

	private Date actualDate;

	/** 开始工作时间 上午 */
	private String startWorkTimeAM;
	/** 结束工作时间 上午 */
	private String endWorkTimeAM;
	/** 开始工作时间 下午 */
	private String startWorkTimePM;
	/** 结束工作时间 下午 */
	private String endWorkTimePM;

	private int isLogin;// 0：未登录；1：登录；2：将来的日期
	private String weekday;// 周几
	private int calendarType;// 日历类型；0：默认日历；1：特色日历
	/** 所属网格 */
	private Long orgId;

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public Long getDay() {
		return day;
	}

	public void setDay(Long day) {
		this.day = day;
	}

	public Long getDayofyear() {
		return dayofyear;
	}

	public void setDayofyear(Long dayofyear) {
		this.dayofyear = dayofyear;
	}

	public Long getHoliday() {
		return holiday;
	}

	public void setHoliday(Long holiday) {
		this.holiday = holiday;
	}

	public Date getActualDate() {
		if (actualDate == null) {
			actualDate = CalendarUtil.parseDate(year + "-" + month + "-" + day,
					"yyyy-M-d");
		}
		return actualDate;
	}

	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}

	public String getStartWorkTimeAM() {
		return startWorkTimeAM;
	}

	public void setStartWorkTimeAM(String startWorkTimeAM) {
		this.startWorkTimeAM = startWorkTimeAM;
	}

	public String getEndWorkTimeAM() {
		return endWorkTimeAM;
	}

	public void setEndWorkTimeAM(String endWorkTimeAM) {
		this.endWorkTimeAM = endWorkTimeAM;
	}

	public String getStartWorkTimePM() {
		return startWorkTimePM;
	}

	public void setStartWorkTimePM(String startWorkTimePM) {
		this.startWorkTimePM = startWorkTimePM;
	}

	public String getEndWorkTimePM() {
		return endWorkTimePM;
	}

	public void setEndWorkTimePM(String endWorkTimePM) {
		this.endWorkTimePM = endWorkTimePM;
	}

	public int getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(int isLogin) {
		this.isLogin = isLogin;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public String getWeekday() {
		return weekday;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public int getCalendarType() {
		return calendarType;
	}

	public void setCalendarType(int calendarType) {
		this.calendarType = calendarType;
	}
}
