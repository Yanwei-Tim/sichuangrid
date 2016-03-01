package com.tianque.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.LeaderTeamsDao;
import com.tianque.domain.LeaderTeams;

@Repository("leaderTeamsDao")
public class LeaderTeamsDaoImpl extends AbstractBaseDao implements LeaderTeamsDao {
	public LeaderTeams getLeaderTeamsById(Long id) {
		return (LeaderTeams) getSqlMapClientTemplate().queryForObject(
				"leaderTeams.getLeaderTeamsById", id);
	}

	public LeaderTeams addLeaderTeams(LeaderTeams leaderTeams) {
		Long id = (Long) getSqlMapClientTemplate()
				.insert("leaderTeams.addLeaderTeams", leaderTeams);
		return getLeaderTeamsById(id);
	}

	public LeaderTeams updateLeaderTeamsById(LeaderTeams leaderTeams) {
		getSqlMapClientTemplate().update("leaderTeams.updateLeaderTeamsById", leaderTeams);
		leaderTeams = getLeaderTeamsById(leaderTeams.getId());
		return leaderTeams;
	}

	public void deleteLeaderTeamsById(Long id) {
		getSqlMapClientTemplate().delete("leaderTeams.deleteLeaderTeamsById", id);
	}

	@Override
	public List<LeaderTeams> getLeaderTeamsListByOrgId(LeaderTeams leaderTeams) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("leaderTeams.getLeaderTeamsListByOrgId",
				leaderTeams);
	}

}
