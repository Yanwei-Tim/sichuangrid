package com.tianque.partyBuilding.members.service;

import java.util.List;

import com.tianque.partyBuilding.members.domain.Member;
import com.tianque.partyBuilding.members.domain.MemberAssociatePartyOrg;

public interface MemberAssociatePartyOrgService {

	public MemberAssociatePartyOrg addMemberAssociatePartyOrg(
			MemberAssociatePartyOrg associatePartyOrg);

	public MemberAssociatePartyOrg updateMemberAssociatePartyOrg(
			MemberAssociatePartyOrg associatePartyOrg);

	public boolean deleteMemberAssociatePartyOrgByMemberIds(
			Integer partyOrgType, List<Long> ids);

	public MemberAssociatePartyOrg getMemberAssociatePartyOrg(
			MemberAssociatePartyOrg associatePartyOrg);

	/**
	 * @author longzhendong
	 * @说明：根据组织机构级别id和orggode 查询乡镇或者社区
	 * @param partyOrgType
	 * @param internalId
	 * @param orgCode
	 * @return
	 */
	public Integer getMemberNumByPartyOrgTypeAndOrgIdOrOrgCode(
			int partyOrgType, Long internalId, String orgCode);

	public Integer countByPartyOrgTypeAndPartyOrgAndOrgId(Integer partyOrgType,
			Long orgId, String partyOrg);

	/**
	 * @说明: 根据类型和老的组织名称修改组织名称为当前的新的组织名称
	 * @param partyorgType
	 * @param oldPartyOrg
	 */
	public void updatePartyorgByPartyOrgTypeAndPartyorg(Integer partyOrgType,
			String oldPartyOrg, String newPartyOrg);

	public boolean isExistMemberInPartyOrg(Member member,
			boolean isInteractiveParty);
}
