package com.tianque.partyBuilding.members.service;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.partyBuilding.members.domain.Member;

public interface MemberService {

	public Member getMemberByPartyOrgTypeAndId(Member member);

	public boolean deleteMemberByIds(Integer partyOrgType, List<Long> ids);

	public PageInfo<Member> findMembersForPage(Member member, Pager pager);

	public Map<String, Object> exsistedIdCard(Member member);

	public Member editMember(Member member);

	public boolean isExsistedPartyOrg(String partyOrg, Integer partyOrgType);

	public Member findMemberById(Long id);

	public Member add(Member member);

	public Member update(Member member);

	public Member findByIdCardNo(String IdCardNo);

	public boolean isExistMemberInPartyOrg(Member member);
}
