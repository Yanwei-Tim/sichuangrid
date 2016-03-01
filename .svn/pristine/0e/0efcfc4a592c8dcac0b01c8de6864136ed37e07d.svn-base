package com.tianque.partyBuilding.organizations.service;

import java.util.List;

import com.tianque.core.base.BaseService;
import com.tianque.partyBuilding.organizations.domain.PartyOrgMember;
import com.tianque.partyBuilding.organizations.domain.vo.SearchPartyOrgMemberVo;

/**
 * 两新组织党组织表:业务逻辑层接口
 * 
 * @author
 * @date 2014-01-14 15:44:08
 */
public interface PartyOrgMemberService extends BaseService<PartyOrgMember, SearchPartyOrgMemberVo> {
	/**
	 * 批量编辑支部成员
	 * 
	 * @param members
	 * @return
	 */
	public List<PartyOrgMember> editPartyOrgMembers(Long partyOrgId, List<PartyOrgMember> members);

	public List<PartyOrgMember> getByPartyOrgId(Long partyOrgId);

	public boolean deleteByPartyOrgId(Long partyOrgId);

}
