package com.tianque.plugin.analysisNew.domain;

import java.io.Serializable;
import java.util.List;

import com.tianque.domain.PropertyDict;

public class IssueTypeStat implements Serializable {
	private PropertyDict orgType;
	private List<IssueLevelStat> issueLevelStats;

	public PropertyDict getOrgType() {
		return orgType;
	}

	public void setOrgType(PropertyDict orgType) {
		this.orgType = orgType;
	}

	public List<IssueLevelStat> getIssueLevelStats() {
		return issueLevelStats;
	}

	public void setIssueLevelStats(List<IssueLevelStat> issueLevelStats) {
		this.issueLevelStats = issueLevelStats;
	}
}
