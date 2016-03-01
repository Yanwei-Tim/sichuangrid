package com.tianque.partyBuilding.volunteerTeam.service;

import com.tianque.core.base.BaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.partyBuilding.members.domain.Member;
import com.tianque.partyBuilding.volunteerTeam.domain.VolunteerMember;
import com.tianque.partyBuilding.volunteerTeam.domain.vo.SearchVolunteerMemberVo;

/**
 * 党员志愿者队伍成员表:业务逻辑层接口
 * 
 * @author
 * @date 2014-02-12 10:48:16
 */
public interface VolunteerMemberService extends
		BaseService<VolunteerMember, SearchVolunteerMemberVo> {

	public PageInfo findPagerBySearchVo(SearchVolunteerMemberVo searchVo,
			Integer page, Integer rows, String sidx, String sord);

	public Boolean deleteByTeamIdAndMemberId(Long teamId, Long memberId,
			Long orgId);

	public Member getMemberById(Long memberId);

	public PageInfo findMembersByOrgCodeAndTeamId(
			SearchVolunteerMemberVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

}
