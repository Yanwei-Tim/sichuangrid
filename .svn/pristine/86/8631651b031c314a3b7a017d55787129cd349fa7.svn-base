package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.dao.LeaderTeamsDao;
import com.tianque.domain.LeaderTeams;
import com.tianque.service.LeaderTeamsService;

@Service("leaderTeamsService")
@Transactional
public class LeaderTeamsServiceImpl implements LeaderTeamsService {
	@Autowired
	private LeaderTeamsDao leaderTeamsDao;

	public LeaderTeams getLeaderTeamsById(Long id) {
		return leaderTeamsDao.getLeaderTeamsById(id);
	}

	public LeaderTeams addLeaderTeams(LeaderTeams leaderTeams) {
		return leaderTeamsDao.addLeaderTeams(leaderTeams);
	}

	public LeaderTeams updateLeaderTeamsById(LeaderTeams leaderTeams) {

		return leaderTeamsDao.updateLeaderTeamsById(leaderTeams);
	}

	public void deleteLeaderTeamsById(Long id) {
		leaderTeamsDao.deleteLeaderTeamsById(id);
	}

	@Override
	public List<LeaderTeams> getLeaderTeamsListByOrgId(LeaderTeams leaderTeams) {
		return leaderTeamsDao.getLeaderTeamsListByOrgId(leaderTeams);
	}

	@Override
	public void sortLeaderById(List<LeaderTeams> leaderTeamsList) {
		if (leaderTeamsList != null && leaderTeamsList.size() > 0) {
			for (LeaderTeams leader : leaderTeamsList) {
				leaderTeamsDao.updateLeaderTeamsById(leader);
			}
		}

	}
}
