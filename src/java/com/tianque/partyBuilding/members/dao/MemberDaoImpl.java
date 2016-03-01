package com.tianque.partyBuilding.members.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.dao.impl.BaseDaoImpl;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.partyBuilding.members.constant.MemberType;
import com.tianque.partyBuilding.members.domain.Member;
import com.tianque.sysadmin.service.PropertyDictService;

@Repository("memberDao")
public class MemberDaoImpl extends BaseDaoImpl<Member> implements MemberDao {
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public Member addMember(Member member) {
		return add(member);
	}

	@Override
	public Member updateMember(Member member) {
		return update(member);
	}

	@Override
	public Member getMemberById(Long id) {
		return get(id);
	}

	@Override
	public PageInfo<Member> findMembersForPage(Member member, Pager pager) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", member.getOrgId());
		map.put("partyOrgType", member.getPartyOrgType());
		map.put("idCardNo", member.getIdCardNo());
		map.put("partyOrg", member.getPartyOrg());
		if ("partyOrg".equalsIgnoreCase(pager.getSortField())) {
			pager.setSortField("mp." + pager.getSortField());
		} else {
			pager.setSortField("pm." + pager.getSortField());
		}
		return findPagerBySearchVo(map, pager, "MembersForPage");
	}

	@Override
	public Member getMemberByIdCardNo(String idCardNo) {
		return get(idCardNo, "getMemberByIdCardNo");
	}

	@Override
	public boolean isExsistedPartyOrg(String partyOrg, Integer partyOrgType) {
		PropertyDict propertyDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.PARTYORGTYPE, MemberType.PARTY_BRANCH);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", ThreadVariable.getOrganization().getId());
		map.put("partyOrg", partyOrg);
		map.put("partyOrgType", partyOrgType);
		map.put("dictId", propertyDict.getId());
		Integer count = countForObject(map, "isExsistedPartyOrg");
		return count != null && count.intValue() > 0;
	}
}
