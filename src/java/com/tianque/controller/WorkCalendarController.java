package com.tianque.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.systemLog.util.ModelType;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.WorkCalendar;
import com.tianque.service.WorkCalendarService;
import com.tianque.util.DateFormat;

@Controller("workCalendarController")
@Scope("prototype")
@Transactional
public class WorkCalendarController extends BaseAction {

	@Autowired
	private WorkCalendarService workCalendarService;

	private WorkCalendar workCalendar;

	private List<List<WorkCalendar>> list;
	private List<WorkCalendar> loginOfCurrentWeek;
	public boolean flg;

	public String monthAndDay = "";

	public Set<Long> set;

	public List<Organization> orgList;

	private String userName;

	public String addWorkCalendar() {
		if (workCalendar.getYear().intValue() >= Calendar.getInstance().get(
				Calendar.YEAR) + 2) {
			errorMessage = "初始化年份不能跳年份";
			return ERROR;
		}
		flg = workCalendarService.addCalendar(workCalendar);
		list = workCalendarService.findCalendar(workCalendar.getYear(),
				workCalendar.getCalendarType(), workCalendar.getOrgId());
		if (list.get(0) != null && list.get(0).size() != 0) {
			monthAndDayString(list);
		}
		return SUCCESS;
	}

	public String updateWorkCalendarIsWeek() throws ParseException {
		if (monthAndDay == null
				|| monthAndDay == ""
				|| monthAndDay.substring(monthAndDay.lastIndexOf("-") + 1)
						.equals("undefined,")) {
			errorMessage = "没有要指定编辑的日期";
			return ERROR;
		}
		java.text.DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String[] date = monthAndDay.split(",");
		for (int i = 0; i < date.length; i++) {
			String[] month = date[i].split(",");
			for (int j = 0; j < month.length; j++) {
				Date d = f.parse(workCalendar.getYear() + "-" + date[i]);
				if (new Date().getTime() > d.getTime()) {
					errorMessage = "不能编辑已过时间";
					return ERROR;
				}
			}
		}
		flg = workCalendarService.updateCalendarIsWeek(date,
				workCalendar.getYear(), workCalendar.getCalendarType(),
				workCalendar.getOrgId());
		return SUCCESS;
	}

	public String updateWorkCalendarIsWork() throws ParseException {
		if (monthAndDay == null
				|| monthAndDay == ""
				|| monthAndDay.substring(monthAndDay.lastIndexOf("-") + 1)
						.equals("undefined,")) {
			errorMessage = "没有要指定编辑的日期";
			return ERROR;
		}
		java.text.DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String[] date = monthAndDay.split(",");
		for (int i = 0; i < date.length; i++) {
			String[] month = date[i].split(",");
			for (int j = 0; j < month.length; j++) {
				Date d = f.parse(workCalendar.getYear() + "-" + date[i]);
				if (new Date().getTime() > d.getTime()) {
					errorMessage = "不能编辑已过时间";
					return ERROR;
				}
			}
		}
		flg = workCalendarService.updateCalendarIsWork(date,
				workCalendar.getYear(), workCalendar.getCalendarType(),
				workCalendar.getOrgId());
		return SUCCESS;
	}

	public String updateWorkTime() throws ParseException {
		if (monthAndDay == null
				|| monthAndDay == ""
				|| monthAndDay.substring(monthAndDay.lastIndexOf("-") + 1)
						.equals("undefined,")) {
			errorMessage = "没有要指定编辑的日期";
			return ERROR;
		}
		java.text.DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String[] date = monthAndDay.split(",");
		for (int i = 0; i < date.length; i++) {
			String[] month = date[i].split(",");
			for (int j = 0; j < month.length; j++) {
				Date d = f.parse(workCalendar.getYear() + "-" + date[i]);
				if (new Date().getTime() > d.getTime()) {
					errorMessage = "不能编辑已过时间";
					return ERROR;
				}
			}
		}
		flg = workCalendarService.updateWorkTime(date, workCalendar);
		if (flg) {
			list = workCalendarService.findCalendar(workCalendar.getYear(),
					workCalendar.getCalendarType(), workCalendar.getOrgId());
			if (list.get(0) != null && list.get(0).size() != 0) {
				monthAndDay = "";
				monthAndDayString(list);
			}
		}
		return SUCCESS;
	}

	@SuppressWarnings("static-access")
	public String findWorkCalendar() {
		flg = false;
		Calendar can = Calendar.getInstance();
		if (workCalendar == null) {
			workCalendar = new WorkCalendar();
			workCalendar.setYear((long) can.get(can.YEAR));
		}
		list = workCalendarService.findCalendar(workCalendar.getYear(),
				workCalendar.getCalendarType(), workCalendar.getOrgId());
		if (list != null && list.get(0) != null && list.get(0).size() != 0) {
			monthAndDayString(list);
		}
		set = workCalendarService.getWorkCalendarByYear(null,
				workCalendar.getCalendarType(), workCalendar.getOrgId());
		set.add((long) can.get(can.YEAR));
		set.add((long) can.get(can.YEAR) + 1);
		Set setYear = workCalendarService.getWorkCalendarByYear(
				workCalendar.getYear(), workCalendar.getCalendarType(),
				workCalendar.getOrgId());
		if (setYear == null || setYear.size() == 0) {
			flg = true;
		}
		if (mode.equals("change")) {
			return "change";
		}
		userName = ThreadVariable.getUser().getUserName();
		return SUCCESS;
	}

	public String findFeatureOrgs() {
		orgList = workCalendarService.findFeatureOrgs(workCalendar);
		return SUCCESS;
	}

	public String deleteWorkCalendar() {
		if (workCalendar.getYear().intValue() <= Calendar.getInstance().get(
				Calendar.YEAR)) {
			errorMessage = "不能删除已经开始使用的日历";
			return ERROR;
		}
		workCalendarService.deleteCalendar(workCalendar);
		return SUCCESS;
	}

	/**
	 * 查询当前周，用户的登录情况
	 * 
	 * @return SUCCESS
	 */
	public String findLoginOfCurrentWeek() throws Exception {
		if (ThreadVariable.getSession().getOrgInternalCode() != null
				&& ThreadVariable.getUser() != null
				&& ThreadVariable.getUser().getUserName() != null
				&& StringUtil.isStringAvaliable(ThreadVariable.getUser()
						.getUserName())) {
			// 如果当前日期没有初始化，则进行初始化工作
			workCalendar = new WorkCalendar();
			workCalendar.setYear((long) Calendar.getInstance().get(
					Calendar.YEAR));
			if (!isHasYear()) {
				initWorkCalendar();
			}
			loginOfCurrentWeek = workCalendarService.findLoginOfCurrentWeek(
					ThreadVariable.getSession().getOrgInternalCode(),
					DateFormat.getCurrnetWeekBeginDate(),
					DateFormat.getCurrnetWeekEndDate(), ModelType.LOGGIN,
					OperatorType.LOGIN, ThreadVariable.getUser().getUserName());
			return SUCCESS;
		}
		return ERROR;
	}

	private void initWorkCalendar() {
		// 初始化工作时间
		workCalendar.setStartWorkTimeAM("08:30:00");
		workCalendar.setEndWorkTimeAM("11:30:00");
		workCalendar.setStartWorkTimePM("13:30:00");
		workCalendar.setEndWorkTimePM("17:30:00");
		flg = workCalendarService.addCalendar(workCalendar);
		list = workCalendarService.findCalendar(workCalendar.getYear(),
				workCalendar.getCalendarType(), workCalendar.getOrgId());
		if (list != null && list.get(0) != null && list.get(0).size() != 0) {
			monthAndDayString(list);
		}
	}

	private String monthAndDayString(List<List<WorkCalendar>> list) {
		if (list.get(1).size() == 28) {
			list.get(1).add(new WorkCalendar());
			list.get(1).add(new WorkCalendar());
		} else {
			list.get(1).add(new WorkCalendar());
		}
		for (int i = 0; i < 31; i++) {
			if (i <= 29) {
				monthAndDay += "{january:'"
						+ constructWorkTime(list.get(0).get(i), "true") + "',"
						+ "february: '"
						+ constructWorkTime(list.get(1).get(i), "false") + "',"
						+ "march: '"
						+ constructWorkTime(list.get(2).get(i), "false") + "',"
						+ "april: '"
						+ constructWorkTime(list.get(3).get(i), "false") + "',"
						+ "may: '"
						+ constructWorkTime(list.get(4).get(i), "false") + "',"
						+ "june: '"
						+ constructWorkTime(list.get(5).get(i), "false") + "',"
						+ "july: '"
						+ constructWorkTime(list.get(6).get(i), "false") + "',"
						+ "august: '"
						+ constructWorkTime(list.get(7).get(i), "false") + "',"
						+ "september: '"
						+ constructWorkTime(list.get(8).get(i), "false") + "',"
						+ "october: '"
						+ constructWorkTime(list.get(9).get(i), "false") + "',"
						+ "november: '"
						+ constructWorkTime(list.get(10).get(i), "false")
						+ "'," + "december: '"
						+ constructWorkTime(list.get(11).get(i), "false")
						+ "' },";
			} else {
				monthAndDay += "{january: '"
						+ constructWorkTime(list.get(0).get(i), "true") + "',"
						+ "february: '{day:0,isSelect:false,holiday:0}' ,"
						+ "march: '"
						+ constructWorkTime(list.get(2).get(i), "false") + "',"
						+ "april: '{day:0,isSelect:false,holiday:0}' ,"
						+ "may: '"
						+ constructWorkTime(list.get(4).get(i), "false") + "',"
						+ "june: '{day:0,isSelect:false,holiday:0}' ,"
						+ "july: '"
						+ constructWorkTime(list.get(6).get(i), "false") + "',"
						+ "august: '"
						+ constructWorkTime(list.get(7).get(i), "false") + "',"
						+ "september: '{day:0,isSelect:false,holiday:0}' ,"
						+ "october: '"
						+ constructWorkTime(list.get(9).get(i), "false") + "',"
						+ "november: '{day:0,isSelect:false,holiday:0}' ,"
						+ "december: '"
						+ constructWorkTime(list.get(11).get(i), "false")
						+ "'}";
			}
		}
		return monthAndDay;
	}

	private String constructWorkTime(WorkCalendar workCalendar, String isSelect) {
		String str = "{day: " + workCalendar.getDay() + " ,isSelect:"
				+ isSelect + ",holiday: " + workCalendar.getHoliday();
		if (workCalendar.getStartWorkTimeAM() != null) {
			str += " ,startAM:"
					+ workCalendar.getStartWorkTimeAM().replace(":", "-");
		}
		if (workCalendar.getEndWorkTimeAM() != null) {
			str += " ,endAM:"
					+ workCalendar.getEndWorkTimeAM().replace(":", "-");
		}
		if (workCalendar.getStartWorkTimePM() != null) {
			str += " ,startPM:"
					+ workCalendar.getStartWorkTimePM().replace(":", "-");
		}
		if (workCalendar.getEndWorkTimePM() != null) {
			str += " ,endPM:"
					+ workCalendar.getEndWorkTimePM().replace(":", "-");
		}
		str += " }";
		return str;
	}

	public String isYear() {
		flg = true;
		set = workCalendarService.getWorkCalendarByYear(workCalendar.getYear(),
				workCalendar.getCalendarType(), workCalendar.getOrgId());
		if (set == null || set.size() == 0) {
			flg = false;
		}
		return SUCCESS;
	}

	public boolean isHasYear() {
		flg = true;
		set = workCalendarService.getWorkCalendarByYear(workCalendar.getYear(),
				workCalendar.getCalendarType(), workCalendar.getOrgId());
		if (set == null || set.size() == 0) {
			flg = false;
		}
		return flg;
	}

	public WorkCalendar getWorkCalendar() {
		return workCalendar;
	}

	public void setWorkCalendar(WorkCalendar workCalendar) {
		this.workCalendar = workCalendar;
	}

	public boolean isFlg() {
		return flg;
	}

	public void setFlg(boolean flg) {
		this.flg = flg;
	}

	public String getMonthAndDay() {
		return monthAndDay;
	}

	public void setMonthAndDay(String monthAndDay) {
		this.monthAndDay = monthAndDay;
	}

	public Set<Long> getSet() {
		return set;
	}

	public void setSet(Set<Long> set) {
		this.set = set;
	}

	public List<List<WorkCalendar>> getList() {
		return list;
	}

	public void setList(List<List<WorkCalendar>> list) {
		this.list = list;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<WorkCalendar> getLoginOfCurrentWeek() {
		return loginOfCurrentWeek;
	}

	public void setLoginOfCurrentWeek(List<WorkCalendar> loginOfCurrentWeek) {
		this.loginOfCurrentWeek = loginOfCurrentWeek;
	}

	public List<Organization> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<Organization> orgList) {
		this.orgList = orgList;
	}
}
