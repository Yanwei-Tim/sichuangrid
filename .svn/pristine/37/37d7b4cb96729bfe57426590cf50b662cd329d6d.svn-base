package com.tianque.partyBuilding.organizations.dao;

import java.util.List;

import com.tianque.core.base.BaseDao;
import com.tianque.partyBuilding.organizations.domain.PartyOrgMember;
import com.tianque.partyBuilding.organizations.domain.vo.SearchPartyOrgMemberVo;

/**
 * 两新组织党组织表:数据操作层接口
 * 
 * @author
 * @date 2014-01-14 15:44:09
 */
public interface PartyOrgMemberDao extends BaseDao<PartyOrgMember, SearchPartyOrgMemberVo> {
	/**
	 * 根据党组织id删除组织成员
	 * 
	 * @param partyOrgId
	 * @return
	 */
	public boolean deleteByPartyOrgId(Long partyOrgId);

	public List<PartyOrgMember> getByPartyOrgId(Long partyOrgId);

}
