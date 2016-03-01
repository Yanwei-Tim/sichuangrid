package com.tianque.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.tianque.domain.WorkCalendar;

public interface WorkCalendarDao {

	public boolean addCalendar(WorkCalendar workCalendar);

	public boolean updateCalendarIsWeek(String[] idCount, Long year,
			int calendarType, Long orgId);

	public boolean updateCalendarIsWork(String[] idCount, Long year,
			int calendarType, Long orgId);

	public List<List<WorkCalendar>> findCalendar(Long year, int calendarType,
			Long orgId);

	public void deleteCalendar(WorkCalendar workCalendar);

	public Set<Long> getWorkCalendarByYear(Long year);

	public Set<Long> getWorkCalendarByYear(Long year, int calendarType,
			Long orgId);

	public String getPreviousDate(String dateTime, int days);

	public String getFutureDate(String dateTime, int days);

	/** 特色日历 **/
	public String getFutureDateByCityOrgId(String dateTime, int days,
			Long cityOrgId);

	public WorkCalendar getWorkCalendarByDateString(String dateStr);

	public Integer getWorkDaysFromStartTimeToEndTime(Date startTime,
			Date endTime);

	public Integer getWorkDaysFromStartTimeToEndTimeByFeature(Date startTime,
			Date endTime, Long orgId);

	public List<String> getWorkDaysInfoFromStartTimeToEndTime(Date startTime,
			Date endTime);

	public List<String> getWorkDaysInfoFromStartTimeToEndTimeByFeature(
			Date startTime, Date endTime, Long orgId);

	/**
	 * 修改工作日历的工作时间
	 * 
	 * @param date
	 * @param workCalendar
	 * @return
	 */
	public boolean updateWorkTime(String[] date, WorkCalendar workCalendar);

	/**
	 * 通过默认日历获取本周的日期时间段
	 * 
	 * @return
	 */
	public List<WorkCalendar> findCurrentWeek(Date startTime, Date endTime);

	/**
	 * 通过地区特色日历获取本周的日期时间段
	 * 
	 * @return
	 */
	public List<WorkCalendar> findCurrentWeekByFeature(Date startTime,
			Date endTime, Long orgId);

	public List<Long> findFeatureOrgIds(WorkCalendar workcalendar);

	/**
	 * 为特色地区新增默认日历
	 * 
	 * @param workCalendar
	 * @return
	 */
	public boolean addDefaultCalendar(WorkCalendar workCalendar);

}
