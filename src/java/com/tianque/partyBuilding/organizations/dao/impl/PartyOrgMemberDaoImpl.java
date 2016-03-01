package com.tianque.partyBuilding.organizations.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.partyBuilding.organizations.dao.PartyOrgMemberDao;
import com.tianque.partyBuilding.organizations.domain.PartyOrgMember;
import com.tianque.partyBuilding.organizations.domain.vo.SearchPartyOrgMemberVo;

/**
 * 两新组织党组织表:数据操作层
 * 
 * @author
 * @date 2014-01-14 15:44:09
 */
@Repository("partyOrgMemberDao")
public class PartyOrgMemberDaoImpl extends BaseDaoImpl<PartyOrgMember, SearchPartyOrgMemberVo> implements
		PartyOrgMemberDao {

	@Override
	public boolean deleteByPartyOrgId(Long partyOrgId) {
		getSqlMapClientTemplate().delete("partyOrgMember.deleteByPartyOrgId", partyOrgId);
		return true;
	}

	@Override
	public List<PartyOrgMember> getByPartyOrgId(Long partyOrgId) {
		return getSqlMapClientTemplate().queryForList("partyOrgMember.getByPartyOrgId", partyOrgId);
	}
}
