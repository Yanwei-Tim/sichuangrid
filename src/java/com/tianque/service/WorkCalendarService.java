package com.tianque.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.tianque.domain.Organization;
import com.tianque.domain.WorkCalendar;
import com.tianque.service.vo.MonthAndWeekWorkDayInfo;

public interface WorkCalendarService {
	public boolean addCalendar(WorkCalendar workCalendar);

	public boolean updateCalendarIsWeek(String[] idCount, Long year,
			int calendarType, Long orgId);

	public boolean updateCalendarIsWork(String[] idCount, Long year,
			int calendarType, Long orgId);

	public List<List<WorkCalendar>> findCalendar(Long year, int calendarType,
			Long orgId);

	public List<Organization> findFeatureOrgs(WorkCalendar workcalendars);

	public void deleteCalendar(WorkCalendar workcalendars);

	public Set<Long> getWorkCalendarByYear(Long year);

	public Set<Long> getWorkCalendarByYear(Long year, int calendarType,
			Long orgId);

	public Date getFutureDate(Date dateTime, int days);

	/** 特色日历查询 */
	public Date getFutureDateByCityOrgId(Date dateTime, int days, Long cityOrgId);

	public boolean isWorkDayByDate(String format);

	/**
	 * 根据Calendar的类获得当前月份的工作周集合 工作日以工作周为单位， 当月不足的周放置下一周
	 */
	public List<Date> getWeekDayByCalendar(int year, int month);

	/**
	 * @param dateStr
	 *            format yyyy-MM-dd
	 * @return
	 */
	public WorkCalendar getWorkCalendarByDateString(String dateStr);

	public MonthAndWeekWorkDayInfo getMonthAndWeekWorkDays(int year, int month,
			Long orgId);

	public Integer getWorkDaysFromStartTimeToEndTime(Date startTime,
			Date endTime);

	public Integer getWorkDaysFromStartTimeToEndTimeByFeature(Date startTime,
			Date endTime, Long orgId);

	/**
	 * 修改工作日历的工作时间
	 * 
	 * @param date
	 * @param workCalendar
	 * @return
	 */
	public boolean updateWorkTime(String[] date, WorkCalendar workCalendar);

	/**
	 * 获取当前周用户的登录情况以及本周的节假日
	 * 
	 * @param OrgCode
	 * @param startDate
	 * @param endDate
	 * @param operationType
	 * @param userName
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public List<WorkCalendar> findLoginOfCurrentWeek(String OrgCode,
			Date startDate, Date endDate, String modelName,
			Integer operationType, String userName);

	/**
	 * 检测是否存在特色日历
	 * 
	 * @param year
	 * @param orgId
	 * @return
	 */
	public boolean checkHasFeatureCalendar(Long year, Long orgId);

	/***
	 * 根据orgID和开始结束时间获得地方特色日历
	 */
	public Integer getWorkDaysByFeature(Date startTime, Date endTime, Long orgId);
}
