package com.tianque.partyBuilding.baseInfos.dao;

import java.util.List;

import com.tianque.core.base.BaseDao;
import com.tianque.partyBuilding.baseInfos.domain.PartyworkMembers;
import com.tianque.partyBuilding.baseInfos.domain.vo.SearchPartyworkMembersVo;

/**
 * 党工委成员表:数据操作层接口
 * 
 * @author
 * @date 2014-01-14 15:32:52
 */
public interface PartyworkMembersDao extends
		BaseDao<PartyworkMembers, SearchPartyworkMembersVo> {

	/**
	 * 根据组织机构查询成员列表信息
	 * 
	 * @param partyworkMembers
	 * @return
	 */
	List<PartyworkMembers> getPartyWorkMembersListByOrgId(
			PartyworkMembers partyworkMembers);

}
