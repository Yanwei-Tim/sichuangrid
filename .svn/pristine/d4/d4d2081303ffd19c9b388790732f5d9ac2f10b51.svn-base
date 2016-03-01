package com.tianque.plugin.serviceTeam.serviceTeamMember.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.domain.PropertyDict;

public class ServiceTeamMember extends ServiceTeamMemberBase {
	/** 主键 **/
	private Long memberId;
	/** 团队主键 **/
	private Long teamId;
	/** 成员基本信息表主键 **/
	private Long baseId;
	/** 职务 **/
	private PropertyDict position;
	/** 是否在职 （默认在职为1） **/
	private Long onDuty;
	/** 离职/再重新担任原因 **/
	private String shiftDutyReason;
	/** 离职/再重新担任日期 **/
	private Date shiftDutyDate;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public PropertyDict getPosition() {
		return position;
	}

	public String getShiftDutyReason() {
		return shiftDutyReason;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getShiftDutyDate() {
		return shiftDutyDate;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public void setPosition(PropertyDict position) {
		this.position = position;
	}

	public void setShiftDutyReason(String shiftDutyReason) {
		this.shiftDutyReason = shiftDutyReason;
	}

	public void setShiftDutyDate(Date shiftDutyDate) {
		this.shiftDutyDate = shiftDutyDate;
	}

	public Long getOnDuty() {
		return onDuty;
	}

	public void setOnDuty(Long onDuty) {
		this.onDuty = onDuty;
	}

	public Long getBaseId() {
		return baseId;
	}

	public void setBaseId(Long baseId) {
		this.baseId = baseId;
	}
}