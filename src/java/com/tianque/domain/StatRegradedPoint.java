package com.tianque.domain;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;

public class StatRegradedPoint extends BaseDomain {

	private Organization parentOrg;

	private Organization org;

	private String orgName;

	private Double yellowAmout;

	private Double redAmout;

	private Double deductPointByPerson;

	private Double addPointByPerson;

	private Double assessmentUser;

	private Double amoutPoint;

	private Integer year;

	private Integer month;

	private boolean audited;

	private String auditUser;

	private Double integratedIndicator;

	private Date auditDate;

	/** 默认的基准分为100.0分 */
	private Double referencePoint = 100.0;

	public Double getAssessmentUser() {
		return assessmentUser;
	}

	public void setAssessmentUser(Double assessmentUser) {
		this.assessmentUser = assessmentUser;
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

	public boolean isAudited() {
		return audited;
	}

	public void setAudited(boolean audited) {
		this.audited = audited;
	}

	public String getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public Organization getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(Organization parentOrg) {
		this.parentOrg = parentOrg;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public Double getYellowAmout() {
		return yellowAmout;
	}

	public void setYellowAmout(Double yellowAmout) {
		this.yellowAmout = yellowAmout;
	}

	public Double getRedAmout() {
		return redAmout;
	}

	public void setRedAmout(Double redAmout) {
		this.redAmout = redAmout;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Double getAmoutPoint() {
		return amoutPoint;
	}

	public void setAmoutPoint(Double amoutPoint) {
		this.amoutPoint = amoutPoint;
	}

	public Double getDeductPointByPerson() {
		return deductPointByPerson;
	}

	public void setDeductPointByPerson(Double deductPointByPerson) {
		this.deductPointByPerson = deductPointByPerson;
	}

	public Double getAddPointByPerson() {
		return addPointByPerson;
	}

	public void setAddPointByPerson(Double addPointByPerson) {
		this.addPointByPerson = addPointByPerson;
	}

	public Double getReferencePoint() {
		return referencePoint;
	}

	public void setReferencePoint(Double referencePoint) {
		this.referencePoint = referencePoint;
	}

	public Double getIntegratedIndicator() {
		return integratedIndicator;
	}

	public void setIntegratedIndicator(Double integratedIndicator) {
		this.integratedIndicator = integratedIndicator;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getOrg().getId(), null,
				null);
	}
}
