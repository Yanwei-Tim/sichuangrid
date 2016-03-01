package com.tianque.partyBuilding.members.dao;

import java.util.List;

import com.tianque.partyBuilding.members.domain.Member;
import com.tianque.partyBuilding.members.domain.MemberAssociatePartyOrg;

public interface MemberAssociatePartyOrgDao {
	public MemberAssociatePartyOrg addMemberAssociatePartyOrg(
			MemberAssociatePartyOrg associatePartyOrg);

	public MemberAssociatePartyOrg updateMemberAssociatePartyOrg(
			MemberAssociatePartyOrg associatePartyOrg);

	public boolean deleteMemberAssociatePartyOrgByMemberIds(
			Integer partyOrgType, List<Long> ids);

	public MemberAssociatePartyOrg getMemberAssociatePartyOrg(
			MemberAssociatePartyOrg associatePartyOrg);

	public Integer getMemberNumByPartyOrgTypeAndOrgIdOrOrgCode(
			int partyOrgType, List<Long> orgIdList);

	public Integer countByPartyOrgTypeAndPartyOrgAndOrgId(Integer partyOrgType,
			Long orgId, String partyOrg);

	public void updatePartyOrgByPartyOrgTypeAndPartyOrg(Integer partyOrgType,
			String oldPartyOrg, String newPartyOrg);

	public boolean isExistMemberInPartyOrg(Member member,
			boolean isInteractiveParty);
}
