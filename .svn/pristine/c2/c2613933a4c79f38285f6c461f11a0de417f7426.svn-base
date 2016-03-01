package com.tianque.party.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.TeamInfo;

public interface PartyTeamService {

	public PageInfo<TeamInfo> findTeamInfosByOwnerOrgIdAndTeamType(Long orgId, Integer pageNum,
			Integer pageSize, String sidx, String sord, String teamType);

	public TeamInfo updateTeamInfo(TeamInfo team, String teamType);

	public boolean deleteTeamInfoById(Long id);

	public TeamInfo getPartyTeamById(Long teamId);

	public TeamInfo addPartyTeam(TeamInfo team, String teamType);

}
