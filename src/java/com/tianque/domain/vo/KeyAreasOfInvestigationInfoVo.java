package com.tianque.domain.vo;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.KeyAreasOfInvestigationInfo;
import com.tianque.domain.Organization;
import com.tianque.working.domain.DailyDirectory;

public class KeyAreasOfInvestigationInfoVo extends BaseDomain {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private DailyDirectory dailyDirectory;
	private KeyAreasOfInvestigationInfo fromKeyAreasOfInvestigationInfo;
	private Organization organization;
	private String orgInternalCode;
	private Organization investigationOrg;
	private String areaName;
	private String mainProblem;
	private String policiesAndMeasures;
	private String remark;
	private Long status;
	private Date investigationMinDate;
	private Date investigationMaxDate;
	private Date reportedDate;
	private Long subOrgid;
	private String warningOrListing;// 警示或挂牌
	/**String元素为orgId集合的字符串表达式，如'1,2,3,4,5' */
	private List<String> orgIdsList;

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

	public Date getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public List<String> getOrgIdsList() {
		return orgIdsList;
	}

	public void setOrgIdsList(List<String> orgIdsList) {
		this.orgIdsList = orgIdsList;
	}
}
