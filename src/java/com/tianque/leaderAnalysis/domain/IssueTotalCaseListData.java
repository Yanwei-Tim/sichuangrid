package com.tianque.leaderAnalysis.domain;

import java.io.Serializable;
import java.util.List;

import com.tianque.domain.Organization;

/**
 * @Description:手机领导视图事件总况列表展示类
 * @author zhangyouwei@hztianque.com
 * @date: 2015-4-3 下午05:24:16
 */
public class IssueTotalCaseListData implements Serializable {
	private Organization org;
	private List<GeneralSituation> generalSituations;

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public List<GeneralSituation> getGeneralSituations() {
		return generalSituations;
	}

	public void setGeneralSituations(List<GeneralSituation> generalSituations) {
		this.generalSituations = generalSituations;
	}

}
