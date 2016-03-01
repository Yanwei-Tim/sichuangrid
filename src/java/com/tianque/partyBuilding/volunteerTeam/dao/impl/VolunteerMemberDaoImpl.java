package com.tianque.partyBuilding.volunteerTeam.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.core.vo.PageInfo;
import com.tianque.partyBuilding.members.domain.Member;
import com.tianque.partyBuilding.volunteerTeam.dao.VolunteerMemberDao;
import com.tianque.partyBuilding.volunteerTeam.domain.VolunteerMember;
import com.tianque.partyBuilding.volunteerTeam.domain.vo.SearchVolunteerMemberVo;

/**
 * 党员志愿者队伍成员表:数据操作层
 * 
 * @author
 * @date 2014-02-12 10:48:16
 */
@Repository("volunteerMemberDao")
public class VolunteerMemberDaoImpl extends
		BaseDaoImpl<VolunteerMember, SearchVolunteerMemberVo> implements
		VolunteerMemberDao {

	@Override
	public VolunteerMember add(VolunteerMember volunteerMember) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"volunteerMember.addVolunteerMember", volunteerMember);
		volunteerMember.setId(id);
		return volunteerMember;
	}

	@Override
	public PageInfo findPagerBySearchVo(SearchVolunteerMemberVo searchVo,
			Integer page, Integer rows, String sidx, String sord) {
		searchVo.setSortField(sidx);
		searchVo.setOrder(sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"volunteerMember.countMembersBySearchVo", searchVo);

		List<Member> resultList = getSqlMapClientTemplate().queryForList(
				"volunteerMember.findMembersBySearchVo", searchVo,
				(page - 1) * rows, rows);

		return new PageInfo<Member>(page, rows, countNum, resultList);
	}

	@Override
	public Boolean deleteByTeamIdAndMemberId(Long teamId, Long memberId,
			Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("teamId", teamId);
		map.put("memberId", memberId);
		map.put("orgId", orgId);
		return getSqlMapClientTemplate().delete(
				"volunteerMember.deleteByTeamIdAndMemberId", map) > 0;
	}

	@Override
	public Member getMemberById(Long memberId) {
		return (Member) getSqlMapClientTemplate().queryForObject(
				"volunteerMember.getMemberById", memberId);

	}

	@Override
	public PageInfo findMembersByOrgCodeAndTeamId(
			SearchVolunteerMemberVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		searchVo.setSortField(sidx);
		searchVo.setOrder(sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"volunteerMember.countMembersByOrgCodeAndTeamId", searchVo);

		List<Member> resultList = getSqlMapClientTemplate().queryForList(
				"volunteerMember.findMembersByOrgCodeAndTeamId", searchVo,
				(page - 1) * rows, rows);

		return new PageInfo<Member>(page, rows, countNum, resultList);
	}
}
