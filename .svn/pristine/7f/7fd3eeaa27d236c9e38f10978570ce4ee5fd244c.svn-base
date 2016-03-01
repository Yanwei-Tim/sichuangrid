package com.tianque.working.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.domain.Organization;
import com.tianque.working.domain.DailyDirectory;

@SuppressWarnings("serial")
public class SearchSignificantIssuedealWorkingRecord implements Serializable {
	/** 信息填写日期 */
	private Organization organization;
	private String orgInternalCode;
	private DailyDirectory dailyDirectory;

	public DailyDirectory getDailyDirectory() {
		return dailyDirectory;
	}

	public void setDailyDirectory(DailyDirectory dailyDirectory) {
		this.dailyDirectory = dailyDirectory;
	}

	private Organization investigationOrg;
	private Date investigationMinDate;
	private Date investigationMaxDate;
	private String address;// address
	private String significantIssuedealReason;// 矛盾纠纷简况
	private String accountabilityUnit;// 责任单位
	private String accountabilityLeading;// 责任领导
	private String remarks;// 调处情况
	private Date reportedDate;
	private Long subOrgid;
	private Long status;

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

	public Organization getInvestigationOrg() {
		return investigationOrg;
	}

	public void setInvestigationOrg(Organization investigationOrg) {
		this.investigationOrg = investigationOrg;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getInvestigationMinDate() {
		return investigationMinDate;
	}

	public void setInvestigationMinDate(Date investigationMinDate) {
		this.investigationMinDate = investigationMinDate;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getInvestigationMaxDate() {
		return investigationMaxDate;
	}

	public void setInvestigationMaxDate(Date investigationMaxDate) {
		this.investigationMaxDate = investigationMaxDate;
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

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
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

}
