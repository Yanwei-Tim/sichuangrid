package com.tianque.partyBuilding.baseInfos.service;

import java.util.List;

import com.tianque.core.base.BaseService;
import com.tianque.partyBuilding.baseInfos.domain.PartyworkMembers;
import com.tianque.partyBuilding.baseInfos.domain.vo.SearchPartyworkMembersVo;

/**
 * 党工委成员表:业务逻辑层接口
 * 
 * @author
 * @date 2014-01-14 15:32:52
 */
public interface PartyworkMembersService extends
		BaseService<PartyworkMembers, SearchPartyworkMembersVo> {

	/**
	 * 根据组织机构查询成员列表信息
	 * 
	 * @param partyworkMembers
	 * @return
	 */
	public List<PartyworkMembers> getPartyWorkMembersListByOrgId(
			PartyworkMembers partyworkMembers);

	/**
	 * 成员排序
	 * 
	 * @param partyworkMembersList
	 */
	public void sortLeaderById(List<PartyworkMembers> partyworkMembersList);

}
