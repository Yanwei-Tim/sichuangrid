package com.tianque.service.vo;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.ReportLevel;
import com.tianque.domain.ReportState;
import com.tianque.domain.ReportType;

public class BaseStatistics extends BaseDomain {

	private ReportType reportType;
	private String year;
	private String month;
	private String title;
	private Organization organization;
	private String orgInternalCode;
	private ReportState reportState;
	private ReportLevel reportLevel;
	private Date reportDate;
	private String reportPerson;

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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

	public ReportState getReportState() {
		return reportState;
	}

	public void setReportState(ReportState reportState) {
		this.reportState = reportState;
	}

	public ReportLevel getReportLevel() {
		return reportLevel;
	}

	public void setReportLevel(ReportLevel reportLevel) {
		this.reportLevel = reportLevel;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportPerson() {
		return reportPerson;
	}

	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
	}

}
