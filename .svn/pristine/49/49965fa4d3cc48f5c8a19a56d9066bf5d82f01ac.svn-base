package com.tianque.partyBuilding.doubleRegActivities.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.partyBuilding.doubleRegActivities.domain.MembersEnroll;

public interface MembersEnrollService {
	MembersEnroll add(MembersEnroll membersEnroll);

	MembersEnroll update(MembersEnroll membersEnroll);

	MembersEnroll getMembersEnroll(MembersEnroll membersEnroll);

	MembersEnroll getMembersEnrollByIdCardNo(String idCardNo);

	MembersEnroll getMembersEnrollByMemberID(Long memberId,
			String regActivitiesType);

	List<MembersEnroll> getMembersEnrollByMemberIDs(List<Long> memberIds,
			String regActivitiesType);

	MembersEnroll getMembersEnrollById(Long id);

	PageInfo<MembersEnroll> findMembersEnrollForPage(
			MembersEnroll MembersEnroll, Pager pager);

	boolean deleteMembersEnroll(List<Long> ids, String regActivitiesType);

	MembersEnroll editUpdateMember(MembersEnroll membersEnroll);

	MembersEnroll editAddMember(MembersEnroll membersEnroll);

	MembersEnroll logoutMemberEnroll(Long id, Long logoutStatus, Long isEnroll);

}
