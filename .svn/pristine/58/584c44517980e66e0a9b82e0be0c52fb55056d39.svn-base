package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.TeamInfoDao;
import com.tianque.domain.ServiceTeam;
import com.tianque.domain.TeamInfo;
import com.tianque.domain.VolunteerTeam;
import com.tianque.domain.constants.TeamType;

@Repository("teamInfoDao")
public class TeamInfoDaoImpl extends AbstractBaseDao implements TeamInfoDao {

	@Override
	public TeamInfo addTeamInfo(TeamInfo team, String teamType) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("teamType", teamType);
		condition.put("teamInfo", team);
		Long id = (Long) getSqlMapClientTemplate().insert("teamInfo.addTeamInfo", condition);
		return getTeamById(id);
	}

	@Override
	public TeamInfo updateTeamInfo(TeamInfo team, String teamType) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("teamType", teamType);
		condition.put("teamInfo", team);
		getSqlMapClientTemplate().update("teamInfo.updateTeamInfo", condition);
		team = getTeamById(team.getId());
		return team;
	}

	@Override
	public void deleteTeamInfo(Long id) {
		getSqlMapClientTemplate().delete("teamInfo.deleteTeam", id);

	}

	private PageInfo<TeamInfo> createPageInfo(int pageNum, int pageSize, Integer countNum, List list) {
		PageInfo<TeamInfo> pageInfo = new PageInfo<TeamInfo>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	@Override
	public PageInfo<TeamInfo> findTeamInfosByOwnerOrgIdAndTeamType(Long orgId, Integer pageNum,
			Integer pageSize, String sidx, String sord, String teamType) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("teamType", teamType);
		condition.put("orgId", orgId);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"teamInfo.countTeamInfosByOwnerOrgIdAndTeamType", condition);
		if (isStrotFieldAvaliable(sidx)) {
			condition.put("sortField", sidx);
		}
		condition.put("order", sord);
		List<ElderlyPeople> list = getSqlMapClientTemplate().queryForList(
				"teamInfo.findTeamInfosByOwnerOrgIdAndTeamType", condition,
				(pageNum - 1) * pageSize, pageSize);

		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public TeamInfo getTeamById(Long teamId) {
		String teamType = (String) getSqlMapClientTemplate().queryForObject(
				"teamInfo.getTeamTypeById", teamId);
		if (TeamType.SERVICE_TEAM.equals(teamType)) {
			return (ServiceTeam) getSqlMapClientTemplate().queryForObject(
					"teamInfo.getServiceTeamById", teamId);
		} else if (TeamType.VOLUNTEER_TEAM.equals(teamType)) {
			return (VolunteerTeam) getSqlMapClientTemplate().queryForObject(
					"teamInfo.getVolunteerTeam", teamId);
		} else {
			return null;
		}
	}

}
