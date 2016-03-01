package com.tianque.plugin.analysisNew.domain;

import java.io.Serializable;
import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;

public class OrgLoginStanals extends BaseDomain implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Organization organization;
	private String orgInternalCode;
	private String orgName;

	private String userName;
	private String name;

	private Integer workday_month = 0;
	private Integer loggedday_month = 0;

	private Integer workday_week1 = 0;
	private Integer loggedday_week1 = 0;

	private Integer workday_week2 = 0;
	private Integer loggedday_week2 = 0;

	private Integer workday_week3 = 0;
	private Integer loggedday_week3 = 0;

	private Integer workday_week4 = 0;
	private Integer loggedday_week4 = 0;

	private Integer workday_week5 = 0;
	private Integer loggedday_week5 = 0;

	/**
	 * 年
	 */
	private Integer year;
	/**
	 * 月
	 */
	private Integer month;
	/**
	 * 统计时间
	 */
	private Date loginStanalDate;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getWorkday_month() {
		return workday_month;
	}

	public void setWorkday_month(Integer workday_month) {
		this.workday_month = workday_month;
	}

	public Integer getLoggedday_month() {
		return loggedday_month;
	}

	public void setLoggedday_month(Integer loggedday_month) {
		this.loggedday_month = loggedday_month;
	}

	public Integer getWorkday_week1() {
		return workday_week1;
	}

	public void setWorkday_week1(Integer workday_week1) {
		this.workday_week1 = workday_week1;
	}

	public Integer getLoggedday_week1() {
		return loggedday_week1;
	}

	public void setLoggedday_week1(Integer loggedday_week1) {
		this.loggedday_week1 = loggedday_week1;
	}

	public Integer getWorkday_week2() {
		return workday_week2;
	}

	public void setWorkday_week2(Integer workday_week2) {
		this.workday_week2 = workday_week2;
	}

	public Integer getLoggedday_week2() {
		return loggedday_week2;
	}

	public void setLoggedday_week2(Integer loggedday_week2) {
		this.loggedday_week2 = loggedday_week2;
	}

	public Integer getWorkday_week3() {
		return workday_week3;
	}

	public void setWorkday_week3(Integer workday_week3) {
		this.workday_week3 = workday_week3;
	}

	public Integer getLoggedday_week3() {
		return loggedday_week3;
	}

	public void setLoggedday_week3(Integer loggedday_week3) {
		this.loggedday_week3 = loggedday_week3;
	}

	public Integer getWorkday_week4() {
		return workday_week4;
	}

	public void setWorkday_week4(Integer workday_week4) {
		this.workday_week4 = workday_week4;
	}

	public Integer getLoggedday_week4() {
		return loggedday_week4;
	}

	public void setLoggedday_week4(Integer loggedday_week4) {
		this.loggedday_week4 = loggedday_week4;
	}

	public Integer getWorkday_week5() {
		return workday_week5;
	}

	public void setWorkday_week5(Integer workday_week5) {
		this.workday_week5 = workday_week5;
	}

	public Integer getLoggedday_week5() {
		return loggedday_week5;
	}

	public void setLoggedday_week5(Integer loggedday_week5) {
		this.loggedday_week5 = loggedday_week5;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Date getLoginStanalDate() {
		return loginStanalDate;
	}

	public void setLoginStanalDate(Date loginStanalDate) {
		this.loginStanalDate = loginStanalDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLoggedDay(final int index, int workDay, int loggedDay) {
		loggedday_month += loggedDay;
		switch (index) {
		case 0:
			loggedday_week1 = loggedDay;
			break;
		case 1:
			loggedday_week2 = loggedDay;
			break;
		case 2:
			loggedday_week3 = loggedDay;
			break;
		case 3:
			loggedday_week4 = loggedDay;
			break;
		case 4:
			loggedday_week5 = loggedDay;
			break;
		default:
			throw new BusinessValidationException("参数错误，index ：" + index);
		}
	}

	public void setWorkDay(int[] workDayArray) {
		for (int i = 0; i < workDayArray.length; i++) {
			setWorkDay(i, workDayArray[i]);
		}
	}

	public void setWorkDay(final int index, int workDays) {
		switch (index) {
		case 0:
			workday_week1 = workDays;
			break;
		case 1:
			workday_week2 = workDays;
			break;
		case 2:
			workday_week3 = workDays;
			break;
		case 3:
			workday_week4 = workDays;
			break;
		case 4:
			workday_week5 = workDays;
			break;
		default:
			throw new BusinessValidationException("参数错误，index ：" + index);
		}
	}

}
