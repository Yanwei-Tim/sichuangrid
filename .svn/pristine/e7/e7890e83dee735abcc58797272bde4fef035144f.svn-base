package com.tianque.incident.domain;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.PropertyDict;

/**
 * @author lizhining
 *         应急事件预案对象
 */
public class IncidentDealPlan extends BaseDomain {

	/**
	 * 事件类型
	 * 防止空指针
	 */
	private IncidentType incidentType;
	/**
	 * 分级
	 */
	private PropertyDict degree;
	/**
	 * 终止条件
	 */
	private String endRule;
	/**
	 * 是否成立领导小组
	 */
	private Boolean leadTeam = false;
	/**
	 * 是否成立处置小组
	 */
	private Boolean complyTeam = false;

	private IncidentDealTeam leaderTeam;

	private List<IncidentDealTeam> dealingTeams = new ArrayList<IncidentDealTeam>();

	public String getEndRule() {
		return endRule;
	}

	public void setEndRule(String endRule) {
		this.endRule = endRule;
	}

	public Boolean getLeadTeam() {
		return leadTeam;
	}

	public void setLeadTeam(Boolean leadTeam) {
		this.leadTeam = leadTeam;
	}

	public Boolean getComplyTeam() {
		return complyTeam;
	}

	public void setComplyTeam(Boolean complyTeam) {
		this.complyTeam = complyTeam;
	}

	public IncidentType getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(IncidentType incidentType) {
		this.incidentType = incidentType;
	}

	public PropertyDict getDegree() {
		return degree;
	}

	public void setDegree(PropertyDict degree) {
		this.degree = degree;
	}

	public IncidentDealTeam getLeaderTeam() {
		return leaderTeam;
	}

	public void setLeaderTeam(IncidentDealTeam leaderTeam) {
		this.leaderTeam = leaderTeam;
	}

	public List<IncidentDealTeam> getDealingTeams() {
		return dealingTeams;
	}

	public void setDealingTeams(List<IncidentDealTeam> dealingTeams) {
		this.dealingTeams = dealingTeams;
	}
}
