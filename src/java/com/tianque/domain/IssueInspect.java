package com.tianque.domain;

import com.tianque.core.base.BaseDomain;

public class IssueInspect extends BaseDomain {
	private String createrepUnion;
	private String createrepDate;
	private String createrepPerson;
	private String auditPerson;
	private String remark;
	private String repdate;
	private long issueType;
	private long issueCount;
	private long dealCount;
	private long finishCount;
	private long inssueId;
	private String submitState;
	private String submitDate;
	private long cityIssueCount;
	private long cityDealCount;
	private long cityFinishcount;
	private long districtIssueCount;
	private long districtDealCount;
	private long districtFinishCount;
	private long townIssueCount;
	private long townDealCount;
	private long townFinishCount;
	private long issueLevel;
	private String repname;
	private long dailyDirectoryId;

	public long getDailyDirectoryId() {
		return dailyDirectoryId;
	}

	public void setDailyDirectoryId(long dailyDirectoryId) {
		this.dailyDirectoryId = dailyDirectoryId;
	}

	public String getRepname() {
		return repname;
	}

	public void setRepname(String repname) {
		this.repname = repname;
	}

	public long getIssueLevel() {
		return issueLevel;
	}

	public void setIssueLevel(long issueLevel) {
		this.issueLevel = issueLevel;
	}

	public String getSubmitState() {
		return submitState;
	}

	public void setSubmitState(String submitState) {
		this.submitState = submitState;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public long getCityIssueCount() {
		return cityIssueCount;
	}

	public void setCityIssueCount(long cityIssueCount) {
		this.cityIssueCount = cityIssueCount;
	}

	public long getCityDealCount() {
		return cityDealCount;
	}

	public void setCityDealCount(long cityDealCount) {
		this.cityDealCount = cityDealCount;
	}

	public long getCityFinishcount() {
		return cityFinishcount;
	}

	public void setCityFinishcount(long cityFinishcount) {
		this.cityFinishcount = cityFinishcount;
	}

	public long getDistrictIssueCount() {
		return districtIssueCount;
	}

	public void setDistrictIssueCount(long districtIssueCount) {
		this.districtIssueCount = districtIssueCount;
	}

	public long getDistrictDealCount() {
		return districtDealCount;
	}

	public void setDistrictDealCount(long districtDealCount) {
		this.districtDealCount = districtDealCount;
	}

	public long getDistrictFinishCount() {
		return districtFinishCount;
	}

	public void setDistrictFinishCount(long districtFinishCount) {
		this.districtFinishCount = districtFinishCount;
	}

	public long getTownIssueCount() {
		return townIssueCount;
	}

	public void setTownIssueCount(long townIssueCount) {
		this.townIssueCount = townIssueCount;
	}

	public long getTownDealCount() {
		return townDealCount;
	}

	public void setTownDealCount(long townDealCount) {
		this.townDealCount = townDealCount;
	}

	public long getTownFinishCount() {
		return townFinishCount;
	}

	public void setTownFinishCount(long townFinishCount) {
		this.townFinishCount = townFinishCount;
	}

	public long getIssueType() {
		return issueType;
	}

	public void setIssueType(long issueType) {
		this.issueType = issueType;
	}

	public long getIssueCount() {
		return issueCount;
	}

	public void setIssueCount(long issueCount) {
		this.issueCount = issueCount;
	}

	public long getDealCount() {
		return dealCount;
	}

	public void setDealCount(long dealCount) {
		this.dealCount = dealCount;
	}

	public long getFinishCount() {
		return finishCount;
	}

	public void setFinishCount(long finishCount) {
		this.finishCount = finishCount;
	}

	public long getInssueId() {
		return inssueId;
	}

	public void setInssueId(long inssueId) {
		this.inssueId = inssueId;
	}

	public String getCreaterepUnion() {
		return createrepUnion;
	}

	public void setCreaterepUnion(String createrepUnion) {
		this.createrepUnion = createrepUnion;
	}

	public String getCreaterepDate() {
		return createrepDate;
	}

	public void setCreaterepDate(String createrepDate) {
		this.createrepDate = createrepDate;
	}

	public String getCreaterepPerson() {
		return createrepPerson;
	}

	public void setCreaterepPerson(String createrepPerson) {
		this.createrepPerson = createrepPerson;
	}

	public String getAuditPerson() {
		return auditPerson;
	}

	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRepdate() {
		return repdate;
	}

	public void setRepdate(String repdate) {
		this.repdate = repdate;
	}

}
