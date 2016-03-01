package com.tianque.partyBuilding.members.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.partyBuilding.members.domain.Member;

public interface MemberDao {
	Member addMember(Member member);

	Member updateMember(Member member);

	Member getMemberById(Long id);

	PageInfo<Member> findMembersForPage(Member member, Pager pager);

	Member getMemberByIdCardNo(String idCardNo);

	boolean isExsistedPartyOrg(String partyOrg, Integer partyOrgType);
}
