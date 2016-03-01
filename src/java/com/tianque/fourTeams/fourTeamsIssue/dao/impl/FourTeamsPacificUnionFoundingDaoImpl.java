package com.tianque.fourTeams.fourTeamsIssue.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.dao.impl.BaseDaoImpl;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsPacificUnionFoundingDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsPacificUnionFounding;

@Repository("fourTeamsPacificUnionFoundingDao")
public class FourTeamsPacificUnionFoundingDaoImpl extends
		BaseDaoImpl<FourTeamsPacificUnionFounding> implements FourTeamsPacificUnionFoundingDao {

	@Override
	public List<FourTeamsPacificUnionFounding> findPacificUnionFoundingsByYearAndParentOrgId(
			Integer year, Long parentOrgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("parentOrgId", parentOrgId);
		return findForList(map, "findPacificUnionFoundingsByYearAndParentOrgId");
	}

	@Override
	public boolean addPacificUnionFoundings(
			List<FourTeamsPacificUnionFounding> pacificUnionFoundings) {
		List<Long> ids = batchInsertDatas(pacificUnionFoundings,
				"pacificUnionFounding.addPacificUnionFounding");
		return ids != null && ids.size() == pacificUnionFoundings.size();
	}

	@Override
	public boolean deletePacificUnionFoundingsByYearAndParentOrgId(
			Integer year, Long parentOrgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("parentOrgId", parentOrgId);
		return delete(map, "deletePacificUnionFoundingsByYearAndParentOrgId");
	}
}
