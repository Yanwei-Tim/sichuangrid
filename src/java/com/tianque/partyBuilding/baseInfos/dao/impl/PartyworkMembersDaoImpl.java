package com.tianque.partyBuilding.baseInfos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.partyBuilding.baseInfos.dao.PartyworkMembersDao;
import com.tianque.partyBuilding.baseInfos.domain.PartyworkMembers;
import com.tianque.partyBuilding.baseInfos.domain.vo.SearchPartyworkMembersVo;

/**
 * 党工委成员表:数据操作层
 * 
 * @author
 * @date 2014-01-14 15:32:52
 */
@Repository("partyworkMembersDao")
public class PartyworkMembersDaoImpl extends
		BaseDaoImpl<PartyworkMembers, SearchPartyworkMembersVo> implements
		PartyworkMembersDao {

	@Override
	public List<PartyworkMembers> getPartyWorkMembersListByOrgId(
			PartyworkMembers partyworkMembers) {
		return getSqlMapClientTemplate().queryForList(
				"partyworkMembers.getPartyWorkMembersListByOrgId", partyworkMembers);
	}

}
