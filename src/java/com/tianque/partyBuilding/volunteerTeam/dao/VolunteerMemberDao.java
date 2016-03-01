package com.tianque.partyBuilding.volunteerTeam.dao;

import com.tianque.core.base.BaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.partyBuilding.members.domain.Member;
import com.tianque.partyBuilding.volunteerTeam.domain.VolunteerMember;
import com.tianque.partyBuilding.volunteerTeam.domain.vo.SearchVolunteerMemberVo;

/**
 * 党员志愿者队伍成员表:数据操作层接口
 * 
 * @author
 * @date 2014-02-12 10:48:16
 */
public interface VolunteerMemberDao extends
		BaseDao<VolunteerMember, SearchVolunteerMemberVo> {

	PageInfo findPagerBySearchVo(SearchVolunteerMemberVo searchVo,
			Integer page, Integer rows, String sidx, String sord);

	Boolean deleteByTeamIdAndMemberId(Long teamId, Long memberId, Long orgId);

	Member getMemberById(Long memberId);

	PageInfo findMembersByOrgCodeAndTeamId(SearchVolunteerMemberVo searchVo,
			Integer page, Integer rows, String sidx, String sord);

}
