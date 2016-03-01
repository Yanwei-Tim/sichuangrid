package com.tianque.domain;

import java.util.Date;

import com.tianque.core.base.BaseDomain;

public class IssueTypeStanal extends BaseDomain {
	private static final long serialVersionUID = 1L;

	private Organization org;
	private String orgInternalCode;
	private IssueTypeDomain issueTypeDomain;
	private IssueType issueType;
	private String domainName;
	private String typeName;
	private Integer count;
	private Date startDate;
	private Date endDate;
	private Date startTime;
	private int year;
	private int month;

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

	public IssueTypeDomain getIssueTypeDomain() {
		return issueTypeDomain;
	}

	public void setIssueTypeDomain(IssueTypeDomain issueTypeDomain) {
		this.issueTypeDomain = issueTypeDomain;
	}

	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
}
