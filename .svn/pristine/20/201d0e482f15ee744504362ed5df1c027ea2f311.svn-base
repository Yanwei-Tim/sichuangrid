package com.tianque.partyBuilding.volunteerTeam.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.partyBuilding.volunteerTeam.dao.VolunteerTeamDao;
import com.tianque.partyBuilding.volunteerTeam.domain.VolunteerTeam;
import com.tianque.partyBuilding.volunteerTeam.domain.vo.SearchVolunteerTeamVo;

/**
 * 党员志愿者队伍表:数据操作层
 * 
 * @author
 * @date 2014-02-11 15:27:44
 */
@Repository("volunteerTeamDao")
public class VolunteerTeamDaoImpl extends BaseDaoImpl<VolunteerTeam, SearchVolunteerTeamVo> implements VolunteerTeamDao {

	@Override
	public VolunteerTeam getByOrgIdAndName(Long orgId, String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("name", name);
		return (VolunteerTeam) getSqlMapClientTemplate().queryForObject("volunteerTeam.getByOrgIdAndName", map);
	}

}
