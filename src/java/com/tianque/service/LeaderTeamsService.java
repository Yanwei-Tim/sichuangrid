package com.tianque.service;

import java.util.List;

import com.tianque.domain.LeaderTeams;

public interface LeaderTeamsService {
	public LeaderTeams getLeaderTeamsById(Long id);

	public LeaderTeams addLeaderTeams(LeaderTeams leaderTeams);

	public LeaderTeams updateLeaderTeamsById(LeaderTeams leaderTeams);

	public void deleteLeaderTeamsById(Long id);

	public List<LeaderTeams> getLeaderTeamsListByOrgId(LeaderTeams leaderTeams);

	public void sortLeaderById(List<LeaderTeams> leaderTeamsList);
}
