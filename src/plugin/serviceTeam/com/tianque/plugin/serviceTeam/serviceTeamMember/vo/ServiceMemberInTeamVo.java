package com.tianque.plugin.serviceTeam.serviceTeamMember.vo;

import com.tianque.core.vo.BaseVo;

/**
 * 服务成员和团队的对象
 * 
 * @author T26
 */
public class ServiceMemberInTeamVo extends BaseVo {

	/*** 成员在团队中的id */
	private Long serviceMemberId;
	/*** 成员的baseid（成员在基本信息表中的） */
	private Long memberBaseId;
	/*** 成员在团队中的团队Id */
	private Long teamId;
	/*** 成员在团队中的团队名称 */
	private String teamName;

	public Long getServiceMemberId() {
		return serviceMemberId;
	}

	public void setServiceMemberId(Long serviceMemberId) {
		this.serviceMemberId = serviceMemberId;
	}

	public Long getMemberBaseId() {
		return memberBaseId;
	}

	public void setMemberBaseId(Long memberBaseId) {
		this.memberBaseId = memberBaseId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}
