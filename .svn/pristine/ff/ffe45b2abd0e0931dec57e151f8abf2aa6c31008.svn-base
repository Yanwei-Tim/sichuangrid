package com.tianque.partyBuilding.members.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.util.ThreadVariable;
import com.tianque.dao.impl.BaseDaoImpl;
import com.tianque.partyBuilding.members.constant.MemberType;
import com.tianque.partyBuilding.members.domain.Member;
import com.tianque.partyBuilding.members.domain.MemberAssociatePartyOrg;
import com.tianque.partyBuilding.systemPartys.constant.SystemPartysType;
import com.tianque.util.ParametersConvertUtil;

@Repository("memberAssociatePartyOrgDao")
public class MemberAssociatePartyOrgDaoImpl extends
		BaseDaoImpl<MemberAssociatePartyOrg> implements
		MemberAssociatePartyOrgDao {

	@Override
	public MemberAssociatePartyOrg addMemberAssociatePartyOrg(
			MemberAssociatePartyOrg associatePartyOrg) {
		getSqlMapClientTemplate().insert(
				"memberAssociatePartyOrg.addMemberAssociatePartyOrg",
				associatePartyOrg);
		return getMemberAssociatePartyOrg(associatePartyOrg);
	}

	@Override
	public MemberAssociatePartyOrg updateMemberAssociatePartyOrg(
			MemberAssociatePartyOrg associatePartyOrg) {
		if (isUpdateSuccess(associatePartyOrg, "updateMemberAssociatePartyOrg")) {
			associatePartyOrg = getMemberAssociatePartyOrg(associatePartyOrg);
		}
		return associatePartyOrg;
	}

	@Override
	public boolean deleteMemberAssociatePartyOrgByMemberIds(
			Integer partyOrgType, List<Long> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", ThreadVariable.getOrganization().getId());
		map.put("partyOrgType", partyOrgType);
		map.put("ids", ids);
		return delete(map, "deleteMemberAssociatePartyOrgByMemberIds");
	}

	@Override
	public MemberAssociatePartyOrg getMemberAssociatePartyOrg(
			MemberAssociatePartyOrg associatePartyOrg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", associatePartyOrg.getOrganization().getId());
		map.put("partyOrgType", associatePartyOrg.getPartyOrgType());
		map.put("memberId", associatePartyOrg.getMember().getId());
		return get(map, "getMemberAssociatePartyOrg");
	}

	@Override
	public Integer getMemberNumByPartyOrgTypeAndOrgIdOrOrgCode(
			int partyOrgType, List<Long> orgIdList) {
		if(orgIdList==null || orgIdList.size()==0){
			return 0;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("partyOrgType", partyOrgType);
		map.put("orgIdsList", ParametersConvertUtil.convertToListString(orgIdList));
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"memberAssociatePartyOrg.getMemberNumByPartyOrgTypeAndOrgIdOrOrgCode",
						map);
	}

	@Override
	public Integer countByPartyOrgTypeAndPartyOrgAndOrgId(Integer partyOrgType,
			Long orgId, String partyOrg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("partyOrgType", partyOrgType);
		map.put("orgId", orgId);
		map.put("partyOrg", partyOrg);
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"memberAssociatePartyOrg.countByPartyOrgTypeAndPartyOrgAndOrgId",
						map);
	}

	@Override
	public void updatePartyOrgByPartyOrgTypeAndPartyOrg(Integer partyOrgType,
			String oldPartyOrg, String newPartyOrg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("partyOrgType", partyOrgType);
		map.put("oldPartyOrg", oldPartyOrg);
		map.put("newPartyOrg", newPartyOrg);
		this.getSqlMapClientTemplate()
				.update("memberAssociatePartyOrg.updatePartyorgByPartyOrgTypeAndPartyOrg",
						map);

	}

	@Override
	public boolean isExistMemberInPartyOrg(Member member,
			boolean isInteractiveParty) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", member.getOrgId());
		List<Integer> memberTypes = new ArrayList<Integer>();
		if (isInteractiveParty) {
			memberTypes.add(MemberType.OFFICE_PARTY_ORG);
			memberTypes.add(MemberType.TOWN_PARTY_ORG);
			memberTypes.add(SystemPartysType.BUSINESS_ORG);
			memberTypes.add(SystemPartysType.COLLECTIVE_ORG);
			memberTypes.add(SystemPartysType.TWO_NEW_PARTY);
		} else {
			memberTypes.add(member.getPartyOrgType());
		}
		map.put("memberTypes", memberTypes);
		map.put("idCardNo", member.getIdCardNo());
		Integer count = countForObject(map, "isExistMemberInPartyOrg");
		return count != null && count > 0;
	}

}
