package com.tianque.statRegrad.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class IntegratedIndicator extends BaseDomain {
	private String year;
	private String month;
	private Organization organization;
	private Long parentOrgId;
	private Double scord;
	private Integer grade;
	private Integer coefficient;
	private Long issueSum;
	private Long dealIssueSum;

	public IntegratedIndicator() {
	}

	public IntegratedIndicator(IssueGradeNode issueGradeNode, String year,
			String month) {
		organization = new Organization();
		organization.setId(issueGradeNode.getOrgId());
		PropertyDict orgType = new PropertyDict();
		orgType.setId(issueGradeNode.getOrgtype());
		organization.setOrgType(orgType);
		this.parentOrgId = issueGradeNode.getParentId();
		this.issueSum = issueGradeNode.getIssueSum();
		this.dealIssueSum = issueGradeNode.getDealIssueSum();
		this.year = year;
		this.month = month;
		grade = 0;
		coefficient = 0;
		scord = 0D;

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

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public Double getScord() {
		return scord;
	}

	public void setScord(Double scord) {
		this.scord = scord;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Integer coefficient) {
		this.coefficient = coefficient;
	}

	public Long getIssueSum() {
		return issueSum;
	}

	public void setIssueSum(Long issueSum) {
		this.issueSum = issueSum;
	}

	public Long getDealIssueSum() {
		return dealIssueSum;
	}

	public void setDealIssueSum(Long dealIssueSum) {
		this.dealIssueSum = dealIssueSum;
	}

}
