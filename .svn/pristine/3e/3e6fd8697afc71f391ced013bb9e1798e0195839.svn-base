package com.tianque.partyBuilding.volunteerTeam.domain;

import org.apache.struts2.json.JSONUtil;

import com.tianque.domain.Organization;

/**
 * 党员志愿者队伍成员表:实体类(VOLUNTEER_MEMBER)
 * 
 * @author
 * @date 2014-02-12 10:48:15
 */
public class VolunteerMember extends com.tianque.core.base.BaseDomain implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 所属网格(ORGID) */
	private Long orgId;
	/** 队伍id(TEAMID) */
	private Long teamId;
	/** 成员id(MEMBERID) */
	private Long memberId;
	/** 所属网格编号(ORGINTERNALCODE) */
	private String orgInternalCode;
	private Organization organization;

	public VolunteerMember() {

	}

	public VolunteerMember(Long orgId, Long teamId, Long memberId, String orgInternalCode) {
		this.orgId = orgId;
		this.teamId = teamId;
		this.memberId = memberId;
		this.orgInternalCode = orgInternalCode;
	}

	/** get 所属网格(orgId) */
	public Long getOrgId() {
		return orgId;
	}

	/** set 所属网格(ORGID) */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	/** get 队伍id(teamId) */
	public Long getTeamId() {
		return teamId;
	}

	/** set 队伍id(TEAMID) */
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	/** get 成员id(memberId) */
	public Long getMemberId() {
		return memberId;
	}

	/** set 成员id(MEMBERID) */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	/** get 所属网格编号(orgInternalCode) */
	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	/** set 所属网格编号(ORGINTERNALCODE) */
	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (Exception e) {
			return super.toString();
		}
	}
}
