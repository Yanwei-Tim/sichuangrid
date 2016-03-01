package com.tianque.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.tianque.core.base.BaseDomain;

@SuppressWarnings("serial")
public class IssueDealStat extends BaseDomain {
	private Organization org;
	private String orgInternalCode;
	private PropertyDict orgLevel;
	private PropertyDict orgType;

	private int year;

	private int month;

	private Date statStartTime;
	private Date statEndTime;
	/**
	 * 本月立案（新增）
	 */
	private int monthAddCount;
	/**
	 * 本年立案（新增）
	 */
	private int yearAddCount;
	/**
	 * 累计（全部）立案（新增）
	 */
	private int totalAddCount;

	/**
	 * 本月正常在办
	 */
	private int monthNomalDealingCount;
	/**
	 * 本月超期在办（包含以前超期）
	 */
	private int monthOvertimeDealingCount;

	/**
	 * 本月正常办结
	 */
	private int monthNomalCompletedCount;
	/**
	 * 本月超期办结
	 */
	private int monthOvertimeCompletedCount;
	// /**
	// * 本月办结的本月立案数
	// */
	// private int monthCompletedCount;

	/**
	 * 本年办结
	 */
	private int yearCompletedCount;
	/**
	 * 累计办结
	 */
	private int totalCompletedCount;

	/**
	 * 本月已经办结的本部门立案的服务办事数量
	 */
	private int monthComplededAddCount;

	/**
	 * 本年已经办结的本部门立案的服务办事数量
	 */
	private int yearComplededAddCount;

	/**
	 * 累计已经办结的本部门立案的服务办事数量
	 */
	private int totalComplededAddCount;

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public PropertyDict getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(PropertyDict orgLevel) {
		this.orgLevel = orgLevel;
	}

	public PropertyDict getOrgType() {
		return orgType;
	}

	public void setOrgType(PropertyDict orgType) {
		this.orgType = orgType;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Date getStatStartTime() {
		return statStartTime;
	}

	public void setStatStartTime(Date statStartTime) {
		this.statStartTime = statStartTime;
	}

	public Date getStatEndTime() {
		return statEndTime;
	}

	public void setStatEndTime(Date statEndTime) {
		this.statEndTime = statEndTime;
	}

	public int getMonthAddCount() {
		return monthAddCount;
	}

	public void setMonthAddCount(int monthAddCount) {
		this.monthAddCount = monthAddCount;
	}

	public int getYearAddCount() {
		return yearAddCount;
	}

	public void setYearAddCount(int yearAddCount) {
		this.yearAddCount = yearAddCount;
	}

	public int getTotalAddCount() {
		return totalAddCount;
	}

	public void setTotalAddCount(int totalAddCount) {
		this.totalAddCount = totalAddCount;
	}

	public int getMonthNomalDealingCount() {
		return monthNomalDealingCount;
	}

	public void setMonthNomalDealingCount(int monthNomalDealingCount) {
		this.monthNomalDealingCount = monthNomalDealingCount;
	}

	public int getMonthOvertimeDealingCount() {
		return monthOvertimeDealingCount;
	}

	public void setMonthOvertimeDealingCount(int monthOvertimeDealingCount) {
		this.monthOvertimeDealingCount = monthOvertimeDealingCount;
	}

	public int getMonthNomalCompletedCount() {
		return monthNomalCompletedCount;
	}

	public void setMonthNomalCompletedCount(int monthNomalCompletedCount) {
		this.monthNomalCompletedCount = monthNomalCompletedCount;
	}

	public int getMonthOvertimeCompletedCount() {
		return monthOvertimeCompletedCount;
	}

	public void setMonthOvertimeCompletedCount(int monthOvertimeCompletedCount) {
		this.monthOvertimeCompletedCount = monthOvertimeCompletedCount;
	}

	public int getYearCompletedCount() {
		return yearCompletedCount;
	}

	public void setYearCompletedCount(int yearCompletedCount) {
		this.yearCompletedCount = yearCompletedCount;
	}

	public int getTotalCompletedCount() {
		return totalCompletedCount;
	}

	public void setTotalCompletedCount(int totalCompletedCount) {
		this.totalCompletedCount = totalCompletedCount;
	}

	public int getMonthComplededAddCount() {
		return monthComplededAddCount;
	}

	public void setMonthComplededAddCount(int monthComplededAddCount) {
		this.monthComplededAddCount = monthComplededAddCount;
	}

	public int getYearComplededAddCount() {
		return yearComplededAddCount;
	}

	public void setYearComplededAddCount(int yearComplededAddCount) {
		this.yearComplededAddCount = yearComplededAddCount;
	}

	public int getTotalComplededAddCount() {
		return totalComplededAddCount;
	}

	public void setTotalComplededAddCount(int totalComplededAddCount) {
		this.totalComplededAddCount = totalComplededAddCount;
	}

	public String getSelfOrgCompleteRata() {
		if (totalAddCount == 0) {
			return "";
		} else {
			return divide(getTotalCompletedCount() * 100, getTotalAddCount()) + "%";
		}
	}

	public String getTotalOrgCompleteRata() {
		if (totalAddCount == 0) {
			return "";
		} else {
			return divide(getTotalComplededAddCount() * 100, getTotalAddCount()) + "%";
		}
	}

	public String getSelfOrgYearCompleteRata() {
		if (yearAddCount == 0) {
			return "";
		} else {
			return divide(getYearCompletedCount() * 100, getYearAddCount()) + "%";
		}
	}

	public String getTotalOrgYearCompleteRata() {
		if (yearAddCount == 0) {
			return "";
		} else {
			return divide(getYearComplededAddCount() * 100, getYearAddCount()) + "%";
		}
	}

	private String divide(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(String.valueOf(d1));
		return bd1.divide(new BigDecimal(String.valueOf(d2)), 2, BigDecimal.ROUND_HALF_UP)
				.toString();
	}

	public static void main(String[] arg) {
		BigDecimal bd1 = new BigDecimal(String.valueOf(10));
		System.out.println(bd1.divide(new BigDecimal(String.valueOf(3)), 2,
				BigDecimal.ROUND_HALF_UP));
	}
}
