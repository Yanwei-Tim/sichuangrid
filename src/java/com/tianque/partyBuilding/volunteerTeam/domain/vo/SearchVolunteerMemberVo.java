package com.tianque.partyBuilding.volunteerTeam.domain.vo;

/**
 * 党员志愿者队伍成员表:SrarchVO(VOLUNTEER_MEMBER)
 * 
 * @author
 * @date 2014-02-12 10:48:16
 */
public class SearchVolunteerMemberVo extends com.tianque.core.base.BaseDomain {

	/** 所属网格 */
	private Long orgId;
	/** 队伍id */
	private Long teamId;
	/** 成员id */
	private Long memberId;
	/** 所属网格编号 */
	private String orgInternalCode;
	/** 查询限制 */
	private Boolean unBound;
	/** 快速搜索条件 */
	private String searchValue;
	/** 过滤志愿者队伍 */
	private String exceptTeamIds;

	public String getExceptTeamIds() {
		return exceptTeamIds;
	}

	public void setExceptTeamIds(String exceptTeamIds) {
		this.exceptTeamIds = exceptTeamIds;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public Boolean getUnBound() {
		return unBound;
	}

	public void setUnBound(Boolean unBound) {
		this.unBound = unBound;
	}

	/** get 所属网格 */
	public Long getOrgId() {
		return orgId;
	}

	/** set 所属网格 */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	/** get 队伍id */
	public Long getTeamId() {
		return teamId;
	}

	/** set 队伍id */
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	/** get 成员id */
	public Long getMemberId() {
		return memberId;
	}

	/** set 成员id */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	/** get 所属网格编号 */
	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	/** set 所属网格编号 */
	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

}
