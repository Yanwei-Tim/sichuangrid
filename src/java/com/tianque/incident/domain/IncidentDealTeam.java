package com.tianque.incident.domain;

import com.tianque.core.base.BaseDomain;

/**
 * @author lizhinng
 *         应急事件预案小组 对象
 */
public class IncidentDealTeam extends BaseDomain {
	// incident 因为没有必要维护双向关系，可以考虑从该对象中移出。
	/**
	 * 应急事件预案对象
	 */
	private IncidentDealPlan incidentDealPlan;
	/**
	 * 是否领导小组
	 */
	private Integer teamType;
	/**
	 * 职责
	 */
	private String responsibility;
	/**
	 * 组成方式
	 */
	private String composition;
	/**
	 * 小组名称
	 */
	private String teamName;
	/**
	 * 牵头部门
	 */
	private String teamLeader;
	/**
	 * 参与部门
	 */
	private String teamMember;

	public Integer getTeamType() {
		return teamType;
	}

	public void setTeamType(Integer teamType) {
		this.teamType = teamType;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamLeader() {
		return teamLeader;
	}

	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}

	public String getTeamMember() {
		return teamMember;
	}

	public void setTeamMember(String teamMember) {
		this.teamMember = teamMember;
	}

	public IncidentDealPlan getIncidentDealPlan() {
		return incidentDealPlan;
	}

	public void setIncidentDealPlan(IncidentDealPlan incidentDealPlan) {
		this.incidentDealPlan = incidentDealPlan;
	}

}
