package com.tianque.partyBuilding.volunteerTeam.dao;

import com.tianque.core.base.BaseDao;
import com.tianque.partyBuilding.volunteerTeam.domain.VolunteerTeam;
import com.tianque.partyBuilding.volunteerTeam.domain.vo.SearchVolunteerTeamVo;

/**
 * 党员志愿者队伍表:数据操作层接口
 * 
 * @author
 * @date 2014-02-11 15:27:44
 */
public interface VolunteerTeamDao extends BaseDao<VolunteerTeam, SearchVolunteerTeamVo> {

	public VolunteerTeam getByOrgIdAndName(Long orgId, String name);

}
