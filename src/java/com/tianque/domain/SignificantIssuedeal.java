package com.tianque.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.SignificantIssuedealAttachFiles;

public class SignificantIssuedeal extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private DailyDirectory dailyDirectory;
	private SignificantIssuedeal fromKeyAreasOfInvestigationInfo;
	private Organization organization;
	private String orgInternalCode;
	private Long subOrgid;
	private Long status;
	private Date reportedDate;
	private Organization investigationOrg;

	private Date investigationDate;
	private String address;
	private String significantIssuedealReason;
	private String accountabilityUnit;
	private String accountabilityLeading;
	private String remarks;
	private List<SignificantIssuedealAttachFiles> significantIssuedealAttachFiles;

	private String strInvestigationDate;
	/** 是否过期录入 */
	private Long expiredEntering;

	public String getStrInvestigationDate() {
		return strInvestigationDate;
	}

	public void setStrInvestigationDate(String strInvestigationDate) {
		this.strInvestigationDate = strInvestigationDate;
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

	public List<SignificantIssuedealAttachFiles> getSignificantIssuedealAttachFiles() {
		if (significantIssuedealAttachFiles == null) {
			return new ArrayList<SignificantIssuedealAttachFiles>();
		}
		return significantIssuedealAttachFiles;
	}

	public void setSignificantIssuedealAttachFiles(
			List<SignificantIssuedealAttachFiles> significantIssuedealAttachFiles) {
		this.significantIssuedealAttachFiles = significantIssuedealAttachFiles;
	}

	public Long getExpiredEntering() {
		return expiredEntering;
	}

	public void setExpiredEntering(Long expiredEntering) {
		this.expiredEntering = expiredEntering;
	}
}
