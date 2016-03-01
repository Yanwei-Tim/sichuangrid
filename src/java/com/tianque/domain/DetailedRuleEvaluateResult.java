package com.tianque.domain;

public class DetailedRuleEvaluateResult {
	private Long[] detailedRuleIds;
	private String[] scores;
	private String[] summarys;
	private String[] scoreReason;

	public Long[] getDetailedRuleIds() {
		return detailedRuleIds;
	}

	public void setDetailedRuleIds(Long[] detailedRuleIds) {
		this.detailedRuleIds = detailedRuleIds;
	}

	public String[] getScores() {
		return scores;
	}

	public void setScores(String[] scores) {
		this.scores = scores;
	}

	public String[] getSummarys() {
		return summarys;
	}

	public void setSummarys(String[] summarys) {
		this.summarys = summarys;
	}

	public String[] getScoreReason() {
		return scoreReason;
	}

	public void setScoreReason(String[] scoreReason) {
		this.scoreReason = scoreReason;
	}

}
