package com.tianque.partyBuilding.doubleRegActivities.domain;

import com.tianque.core.base.BaseDomain;

public class MemberHasVolunteerJobs extends BaseDomain {
	/** 党员报到ID */
	private Long memberId;
	/** 志愿者岗位ID */
	private Long volunteerJobsId;

	public Long getMemberId() {
		return memberId;
	}

	public void setMembersId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getVolunteerJobsId() {
		return volunteerJobsId;
	}

	public void setVolunteerJobsId(Long volunteerJobsId) {
		this.volunteerJobsId = volunteerJobsId;
	}
}
