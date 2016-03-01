package com.tianque.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.TeamInfo;

public interface TeamInfoDao {

	public TeamInfo addTeamInfo(TeamInfo team, String teamType);

	public TeamInfo updateTeamInfo(TeamInfo team, String teamType);

	public void deleteTeamInfo(Long id);

	public PageInfo<TeamInfo> findTeamInfosByOwnerOrgIdAndTeamType(Long orgId, Integer pageNum,
			Integer pageSize, String sidx, String sord, String teamType);

	TeamInfo getTeamById(Long teamId);

}
