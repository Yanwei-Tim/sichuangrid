package com.tianque.domain;

import java.util.Date;

import com.tianque.core.base.BaseDomain;

public class StatementStatistic extends BaseDomain {

	private static final long serialVersionUID = 1L;

	/** 网格(职能部门) */
	private Organization organization;
	/** 网格(职能部门)InternalCode */
	private String orgInternalCode;
	/** 序号 */
	private Long statementNo;
	/** 报表类型(月报/年报) */
	private PropertyDict statementType;
	/** 累计办件 */
	private Long statementTotal;
	/** 当期立案 */
	private Long currentPeriodRegister;
	/** 累计立案 */
	private Long registerTotal;
	/** 逾期未受理 */
	private Long overdueNotAccepted;
	/** 正常未受理 */
	private Long normalNotAccepted;
	/** 七天在办 */
	private Long sevenDaysInDeal;
	/** 超七天在办 */
	private Long overtakeSevenDaysInDeal;
	/** 按期办结 */
	private Long onScheduleHandle;
	/** 超期办结 */
	private Long overdueHandle;
	/** 累计移交 */
	private Long transferTotal;
	/** 累计结案数 */
	private Long endTotal;
	/** 按期结案率 */
	private String onScheduleRateOfEnd;
	/** 结案率 */
	private String rateOfEnding;
	/** 年份 */
	private Integer year;
	/** 月份 */
	private Integer month;
	/** 服务办事开始时间 */
	private Date startTime;

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

	public Long getStatementNo() {
		return statementNo;
	}

	public void setStatementNo(Long statementNo) {
		this.statementNo = statementNo;
	}

	public PropertyDict getStatementType() {
		return statementType;
	}

	public void setStatementType(PropertyDict statementType) {
		this.statementType = statementType;
	}

	public Long getStatementTotal() {
		return statementTotal;
	}

	public void setStatementTotal(Long statementTotal) {
		this.statementTotal = statementTotal;
	}

	public Long getCurrentPeriodRegister() {
		return currentPeriodRegister;
	}

	public void setCurrentPeriodRegister(Long currentPeriodRegister) {
		this.currentPeriodRegister = currentPeriodRegister;
	}

	public Long getRegisterTotal() {
		return registerTotal;
	}

	public void setRegisterTotal(Long registerTotal) {
		this.registerTotal = registerTotal;
	}

	public Long getOverdueNotAccepted() {
		return overdueNotAccepted;
	}

	public void setOverdueNotAccepted(Long overdueNotAccepted) {
		this.overdueNotAccepted = overdueNotAccepted;
	}

	public Long getNormalNotAccepted() {
		return normalNotAccepted;
	}

	public void setNormalNotAccepted(Long normalNotAccepted) {
		this.normalNotAccepted = normalNotAccepted;
	}

	public Long getSevenDaysInDeal() {
		return sevenDaysInDeal;
	}

	public void setSevenDaysInDeal(Long sevenDaysInDeal) {
		this.sevenDaysInDeal = sevenDaysInDeal;
	}

	public Long getOvertakeSevenDaysInDeal() {
		return overtakeSevenDaysInDeal;
	}

	public void setOvertakeSevenDaysInDeal(Long overtakeSevenDaysInDeal) {
		this.overtakeSevenDaysInDeal = overtakeSevenDaysInDeal;
	}

	public Long getOnScheduleHandle() {
		return onScheduleHandle;
	}

	public void setOnScheduleHandle(Long onScheduleHandle) {
		this.onScheduleHandle = onScheduleHandle;
	}

	public Long getOverdueHandle() {
		return overdueHandle;
	}

	public void setOverdueHandle(Long overdueHandle) {
		this.overdueHandle = overdueHandle;
	}

	public Long getTransferTotal() {
		return transferTotal;
	}

	public void setTransferTotal(Long transferTotal) {
		this.transferTotal = transferTotal;
	}

	public Long getEndTotal() {
		return endTotal;
	}

	public void setEndTotal(Long endTotal) {
		this.endTotal = endTotal;
	}

	public String getOnScheduleRateOfEnd() {
		return onScheduleRateOfEnd;
	}

	public void setOnScheduleRateOfEnd(String onScheduleRateOfEnd) {
		this.onScheduleRateOfEnd = onScheduleRateOfEnd;
	}

	public String getRateOfEnding() {
		return rateOfEnding;
	}

	public void setRateOfEnding(String rateOfEnding) {
		this.rateOfEnding = rateOfEnding;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
}
