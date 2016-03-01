package com.tianque.domain;

import java.util.List;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.core.util.StringUtil;

public class Evaluate extends BaseDomain {

	private String year;
	private String title;

	private Integer totalScore;
	private PropertyDict evaluateType;

	private List<DetailedRule> detailedRules;

	private Organization organization;
	private String orgInternalCode;
	private Integer selfAssessmentTotalScore;

	private int state;
	private Integer templateTotalScore;

	private Boolean isEvaluateUsed;

	public String evaluateResult;

	public Evaluate standardEvaluate;

	public String getEvaluateResult() {
		return evaluateResult;
	}

	public void setEvaluateResult(String evaluateResult) {
		this.evaluateResult = evaluateResult;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public PropertyDict getEvaluateType() {
		return evaluateType;
	}

	public void setEvaluateType(PropertyDict evaluateType) {
		this.evaluateType = evaluateType;
	}

	public List<DetailedRule> getDetailedRules() {
		return detailedRules;
	}

	public void setDetailedRules(List<DetailedRule> detailedRules) {
		this.detailedRules = detailedRules;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Boolean getIsEvaluateUsed() {
		return isEvaluateUsed;
	}

	public void setIsEvaluateUsed(Boolean isEvaluateUsed) {
		this.isEvaluateUsed = isEvaluateUsed;
	}

	public Evaluate getStandardEvaluate() {
		return standardEvaluate;
	}

	public void setStandardEvaluate(Evaluate standardEvaluate) {
		this.standardEvaluate = standardEvaluate;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Integer getSelfAssessmentTotalScore() {
		return selfAssessmentTotalScore;
	}

	public void setSelfAssessmentTotalScore(Integer selfAssessmentTotalScore) {
		this.selfAssessmentTotalScore = selfAssessmentTotalScore;
	}

	public Integer getTemplateTotalScore() {
		return templateTotalScore;
	}

	public void setTemplateTotalScore(Integer templateTotalScore) {
		this.templateTotalScore = templateTotalScore;
	}

	public String getEncryptId() {
		String orgCode = this.orgInternalCode;
		if (!StringUtil.isStringAvaliable(orgCode) && organization != null) {
			orgCode = this.organization.getOrgInternalCode();
		}
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(), orgCode, null);
	}
}
