package com.tianque.domain.vo;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.domain.Organization;
import com.tianque.domain.SignificantIssuedeal;
import com.tianque.working.domain.DailyDirectory;

public class SignificantIssuedealVo {
	private DailyDirectory dailyDirectory;
	private SignificantIssuedeal fromKeyAreasOfInvestigationInfo;
	private Organization organization;
	private String orgInternalCode;
	private Long subOrgid;
	private Long status;
	private Date reportedDate;
	private Organization investigationOrg;
	/**String元素为orgId集合的字符串表达式，如'1,2,3,4,5' */
	private List<String> orgIdsList;

	private Date investigationDate;
	private String address;
	private String significantIssuedealReason;
	private String accountabilityUnit;
	private String accountabilityLeading;
	private String remarks;
	private Date investigationMinDate;
	private Date investigationMaxDate;

	@JSON(format = "yyyy-MM-dd")
	public Date getInvestigationMinDate() {
		return investigationMinDate;
	}

	public void setInvestigationMinDate(Date investigationMinDate) {
		this.investigationMinDate = investigationMinDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getInvestigationMaxDate() {
		return investigationMaxDate;
	}

	public void setInvestigationMaxDate(Date investigationMaxDate) {
		this.investigationMaxDate = investigationMaxDate;
	}

	public DailyDirectory getDailyDirectory() {
		return dailyDirectory;
	}

	public void setDailyDirectory(DailyDirectory dailyDirectory) {
		this.dailyDirectory = dailyDirectory;
	}

	public SignificantIssuedeal getFromKeyAreasOfInvestigationInfo() {
		return fromKeyAreasOfInvestigationInfo;
	}

	public void setFromKeyAreasOfInvestigationInfo(
			SignificantIssuedeal fromKeyAreasOfInvestigationInfo) {
		this.fromKeyAreasOfInvestigationInfo = fromKeyAreasOfInvestigationInfo;
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

	public Long getSubOrgid() {
		return subOrgid;
	}

	public void setSubOrgid(Long subOrgid) {
		this.subOrgid = subOrgid;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}

	public Organization getInvestigationOrg() {
		return investigationOrg;
	}

	public void setInvestigationOrg(Organization investigationOrg) {
		this.investigationOrg = investigationOrg;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getInvestigationDate() {
		return investigationDate;
	}

	public void setInvestigationDate(Date investigationDate) {
		this.investigationDate = investigationDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSignificantIssuedealReason() {
		return significantIssuedealReason;
	}

	public void setSignificantIssuedealReason(String significantIssuedealReason) {
		this.significantIssuedealReason = significantIssuedealReason;
	}

	public String getAccountabilityUnit() {
		return accountabilityUnit;
	}

	public void setAccountabilityUnit(String accountabilityUnit) {
		this.accountabilityUnit = accountabilityUnit;
	}

	public String getAccountabilityLeading() {
		return accountabilityLeading;
	}

	public void setAccountabilityLeading(String accountabilityLeading) {
		this.accountabilityLeading = accountabilityLeading;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<String> getOrgIdsList() {
		return orgIdsList;
	}

	public void setOrgIdsList(List<String> orgIdsList) {
		this.orgIdsList = orgIdsList;
	}

}
