package com.tianque.partyBuilding.doubleRegActivities.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.partyBuilding.doubleRegActivities.domain.MembersEnroll;

public interface MembersEnrollDao {
	MembersEnroll addMembersEnroll(MembersEnroll membersEnroll);

	MembersEnroll updateMembersEnroll(MembersEnroll membersEnroll);

	MembersEnroll getMembersEnrollById(Long id);

	MembersEnroll getMembersEnroll(MembersEnroll membersEnroll);

	MembersEnroll getMembersEnrollByIdCardNo(String idCardNo);

	PageInfo<MembersEnroll> findMembersEnrollForPage(
			MembersEnroll membersEnroll, Pager pager);

	boolean deleteMembersEnroll(List<Long> ids, String regActivitiesType);

	MembersEnroll getMembersEnrollByMemberID(Long memberId,
			String regActivitiesType);

	List<MembersEnroll> getMembersEnrollByMemberIDs(List<Long> memberIds,
			String regActivitiesType);

	MembersEnroll logoutMemberEnroll(Long id, Long logoutStatus, Long isEnroll);
}
