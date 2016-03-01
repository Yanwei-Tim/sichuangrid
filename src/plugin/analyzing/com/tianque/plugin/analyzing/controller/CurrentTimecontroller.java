package com.tianque.plugin.analyzing.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;

@Controller("currentTimecontroller")
@Scope("request")
@Transactional
public class CurrentTimecontroller extends BaseAction {
	private List<Integer> list;
	private List<Integer> monthlist = new ArrayList<Integer>();
	private int currenYear;

	public String getCurrentTimeForYear() {
		list = new ArrayList<Integer>();
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		for (int i = year; i > year - 6; i--) {
			list.add(i);
		}
		return SUCCESS;
	}

	/**
	 * 为研判分析一些特殊的模块提供查询年份（如果当前月是一月则只查询到上一年的12月份）（登陆情况、党委部门）
	 * 
	 * @return
	 */
	public String getCurrentTimeForYearToSpecial() {
		list = new ArrayList<Integer>();
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		if (calendar.get(Calendar.MONTH) == 0) {
			year = year - 1;
		}
		for (int i = year; i > year - 6; i--) {
			list.add(i);
		}
		return SUCCESS;
	}

	/**
	 * 人口模块，见义勇为的业务信息（获奖年份从2000至今）
	 * 
	 * @return
	 */
	public String getYearToSpecial() {
		list = new ArrayList<Integer>();
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		if (calendar.get(Calendar.MONTH) == 0) {
			currentYear = currentYear - 1;
		}
		for (int i = 2000; i <= currentYear; i++) {
			list.add(i);
		}
		return SUCCESS;
	}

	public String getCurrentTimeForIntegrativeQueryYear() {
		list = new ArrayList<Integer>();
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		list.add(-1);
		for (int i = year; i > year - 100; i--) {

			list.add(i);
		}
		return SUCCESS;
	}

	public String getCurrentTimeForMonth() {
		monthlist = new ArrayList<Integer>();
		Calendar calendar = Calendar.getInstance();
		int month = 0;
		if (currenYear == calendar.get(Calendar.YEAR)) {
			month = calendar.get(Calendar.MONTH) + 1;
		} else {
			month = 12;
		}
		for (int i = month; i > 0; i--) {
			monthlist.add(i);
		}
		return SUCCESS;
	}

	/**
	 * 为研判分析一些特殊的模块提供查询月份（只查询到当前月份的上个月）（登陆情况、党委部门）
	 * 
	 * @return
	 */
	public String getCurrentTimeForMonthToSpecial() {
		monthlist = new ArrayList<Integer>();
		Calendar calendar = Calendar.getInstance();
		int month = 0;
		if (currenYear == calendar.get(Calendar.YEAR)) {
			month = calendar.get(Calendar.MONTH);
		} else {
			month = 12;
		}
		for (int i = month; i > 0; i--) {
			monthlist.add(i);
		}
		return SUCCESS;
	}

	public String getCurrentTimeForQuarter() {
		list = new ArrayList<Integer>();
		Calendar calendar = Calendar.getInstance();
		int quarter = 0;
		if (currenYear == calendar.get(Calendar.YEAR)) {
			quarter = calendar.get(Calendar.MONTH) / 3 + 1;
		} else {
			quarter = 4;
		}
		for (int i = quarter; i >= 1; i--) {
			list.add(i);
		}
		return SUCCESS;
	}

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}

	public int getCurrenYear() {
		return currenYear;
	}

	public void setCurrenYear(int currenYear) {
		this.currenYear = currenYear;
	}

	public List<Integer> getMonthlist() {
		return monthlist;
	}

	public void setMonthlist(List<Integer> monthlist) {
		this.monthlist = monthlist;
	}

}
