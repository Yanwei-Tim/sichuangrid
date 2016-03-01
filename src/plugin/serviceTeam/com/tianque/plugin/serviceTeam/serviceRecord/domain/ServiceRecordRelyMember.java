package com.tianque.plugin.serviceTeam.serviceRecord.domain;

public class ServiceRecordRelyMember {

	private Long id;
	/** 记录id **/
	private Long recordId;
	/** 所属人id **/
	private Long memberId;
	/** 所属人名称 **/
	private String memberName;
	/** 队伍Id **/
	private Long teamId;
	/** 合并重复数据时的标准成员id */
	private Long standardMemberId;

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRecordId() {
		return recordId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getStandardMemberId() {
		return standardMemberId;
	}

	public void setStandardMemberId(Long standardMemberId) {
		this.standardMemberId = standardMemberId;
	}

}
