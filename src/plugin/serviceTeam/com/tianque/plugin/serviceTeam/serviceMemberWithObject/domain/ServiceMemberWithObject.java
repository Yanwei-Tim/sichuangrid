package com.tianque.plugin.serviceTeam.serviceMemberWithObject.domain;

import com.tianque.core.base.BaseDomain;

public class ServiceMemberWithObject extends BaseDomain {
	private Long id;
	/* 服务人员的id */
	private Long memberId;
	/* 服务对象的类型 */
	private String objectType;
	/* 服务对象的名称 */
	private String objectName;
	/* 服务对象的id */
	private Long objectId;
	/* 是否是团队成员 */
	private Long teamMember;
	/* 是否在职 */
	private Long onDuty;
	/* 对象注销状态 */
	private Long objectLogout;
	/* 团队id */
	private Long teamId;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public Long getTeamMember() {
		return teamMember;
	}

	public void setTeamMember(Long teamMember) {
		this.teamMember = teamMember;
	}

	public Long getOnDuty() {
		return onDuty;
	}

	public void setOnDuty(Long onDuty) {
		this.onDuty = onDuty;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getObjectLogout() {
		return objectLogout;
	}

	public void setObjectLogout(Long objectLogout) {
		this.objectLogout = objectLogout;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

}
