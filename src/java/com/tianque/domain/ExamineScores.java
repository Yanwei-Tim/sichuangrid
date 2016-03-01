package com.tianque.domain;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.base.BaseDomain;

public class ExamineScores extends BaseDomain {
	private static final long serialVersionUID = 1L;

	// 年度
	private String year;

	// 年度得分
	private Double annualActualScore;

	// 年度总分
	private Double annualMaxScore;

	// 部门
	private Organization org;

	// 部门编码
	private String orgInternalCode;

	// 适用的年度评分计划
	private ExaminePlan plan;

	// 具体评分情况
	private List<ExamineScoreRecord> scoreRecords = new ArrayList<ExamineScoreRecord>();

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Double getAnnualActualScore() {
		return annualActualScore;
	}

	public void setAnnualActualScore(Double annualActualScore) {
		this.annualActualScore = annualActualScore;
	}

	public Double getAnnualMaxScore() {
		return annualMaxScore;
	}

	public void setAnnualMaxScore(Double annualMaxScore) {
		this.annualMaxScore = annualMaxScore;
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

	public ExaminePlan getPlan() {
		return plan;
	}

	public void setPlan(ExaminePlan plan) {
		this.plan = plan;
	}

	public List<ExamineScoreRecord> getScoreRecords() {
		return scoreRecords;
	}

	public void setScoreRecords(List<ExamineScoreRecord> scoreRecords) {
		this.scoreRecords = scoreRecords;
	}
}
