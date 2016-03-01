package com.tianque.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.working.domain.DailyDirectory;

@SuppressWarnings("serial")
public class KeyAreasOfInvestigationInfo extends BaseDomain {
	private DailyDirectory dailyDirectory;
	private KeyAreasOfInvestigationInfo fromKeyAreasOfInvestigationInfo;
	private Organization organization;
	private String orgInternalCode;
	private Organization investigationOrg;
	private String areaName;
	private String mainProblem;
	private String policiesAndMeasures;
	private String warningOrListing;// 警示或挂牌
	private String remark;
	private Long status;
	private Date investigationDate;
	private Date reportedDate;
	private Long subOrgid;
	/** 是否过期录入 */
	private Long expiredEntering;

	private String investigatOrgDate;

	public String getInvestigatOrgDate() {
		return investigatOrgDate;
	}

	public void setInvestigatOrgDate(String investigatOrgDate) {
		this.investigatOrgDate = investigatOrgDate;
	}

	public KeyAreasOfInvestigationInfo getFromKeyAreasOfInvestigationInfo() {
		return fromKeyAreasOfInvestigationInfo;
	}

	public void setFromKeyAreasOfInvestigationInfo(
			KeyAreasOfInvestigationInfo fromKeyAreasOfInvestigationInfo) {
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

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getMainProblem() {
		return mainProblem;
	}

	public void setMainProblem(String mainProblem) {
		this.mainProblem = mainProblem;
	}

	public String getPoliciesAndMeasures() {
		return policiesAndMeasures;
	}

	public void setPoliciesAndMeasures(String policiesAndMeasures) {
		this.policiesAndMeasures = policiesAndMeasures;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getInvestigationDate() {
		return investigationDate;
	}

	public void setInvestigationDate(Date investigationDate) {
		this.investigationDate = investigationDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}

	public DailyDirectory getDailyDirectory() {
		return dailyDirectory;
	}

	public void setDailyDirectory(DailyDirectory dailyDirectory) {
		this.dailyDirectory = dailyDirectory;
	}

	public Organization getInvestigationOrg() {
		return investigationOrg;
	}

	public void setInvestigationOrg(Organization investigationOrg) {
		this.investigationOrg = investigationOrg;
	}

	public Long getSubOrgid() {
		return subOrgid;
	}

	public void setSubOrgid(Long subOrgid) {
		this.subOrgid = subOrgid;
	}

	public String getWarningOrListing() {
		return warningOrListing;
	}

	public void setWarningOrListing(String warningOrListing) {
		this.warningOrListing = warningOrListing;
	}

	public Long getExpiredEntering() {
		return expiredEntering;
	}

	public void setExpiredEntering(Long expiredEntering) {
		this.expiredEntering = expiredEntering;
	}
}
