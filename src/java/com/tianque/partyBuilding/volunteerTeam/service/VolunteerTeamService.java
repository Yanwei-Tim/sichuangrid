package com.tianque.partyBuilding.volunteerTeam.service;

import com.tianque.core.base.BaseService;
import com.tianque.partyBuilding.volunteerTeam.domain.VolunteerTeam;
import com.tianque.partyBuilding.volunteerTeam.domain.vo.SearchVolunteerTeamVo;

/**
 * 党员志愿者队伍表:业务逻辑层接口
 * 
 * @author
 * @date 2014-02-11 15:27:44
 */
public interface VolunteerTeamService extends
		BaseService<VolunteerTeam, SearchVolunteerTeamVo> {

	public Boolean hasDuplicate(VolunteerTeam volunteerTeam);

}
