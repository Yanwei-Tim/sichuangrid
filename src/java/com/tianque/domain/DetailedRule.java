package com.tianque.domain;

import java.util.List;

import com.tianque.core.base.BaseDomain;

public class DetailedRule extends BaseDomain {

	private Evaluate evaluate;

	private DetailedRule parentRule;

	private List<DetailedRule> detailedRules;

	private String content;

	private Integer score;

	private Integer standardScore;
	private Integer selfAssessmentScore;
	private String summary;

	private Long seq;

	private PropertyDict ruleType;
	private String scoreReason;

	public String getScoreReason() {
		return scoreReason;
	}

	public void setScoreReason(String scoreReason) {
		this.scoreReason = scoreReason;
	}

	public DetailedRule getParentRule() {
		return parentRule;
	}

	public void setParentRule(DetailedRule parentRule) {
		this.parentRule = parentRule;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<DetailedRule> getDetailedRules() {
		return detailedRules;
	}

	public void setDetailedRules(List<DetailedRule> detailedRules) {
		this.detailedRules = detailedRules;
	}

	public Evaluate getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Evaluate evaluate) {
		this.evaluate = evaluate;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public PropertyDict getRuleType() {
		return ruleType;
	}

	public void setRuleType(PropertyDict ruleType) {
		this.ruleType = ruleType;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getStandardScore() {
		return standardScore;
	}

	public void setStandardScore(Integer standardScore) {
		this.standardScore = standardScore;
	}

	public Integer getSelfAssessmentScore() {
		return selfAssessmentScore;
	}

	public void setSelfAssessmentScore(Integer selfAssessmentScore) {
		this.selfAssessmentScore = selfAssessmentScore;
	}

}
