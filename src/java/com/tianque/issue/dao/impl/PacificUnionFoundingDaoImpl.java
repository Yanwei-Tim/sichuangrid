package com.tianque.issue.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.dao.impl.BaseDaoImpl;
import com.tianque.issue.dao.PacificUnionFoundingDao;
import com.tianque.issue.domain.PacificUnionFounding;

@Repository("pacificUnionFoundingDao")
public class PacificUnionFoundingDaoImpl extends
		BaseDaoImpl<PacificUnionFounding> implements PacificUnionFoundingDao {

	@Override
	public List<PacificUnionFounding> findPacificUnionFoundingsByYearAndParentOrgId(
			Integer year, Long parentOrgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("parentOrgId", parentOrgId);
		return findForList(map, "findPacificUnionFoundingsByYearAndParentOrgId");
	}

	@Override
	public boolean addPacificUnionFoundings(
			List<PacificUnionFounding> pacificUnionFoundings) {
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

	@Override
	public PacificUnionFounding getPacificUnionFoundingByOrgAndYear(
			PacificUnionFounding pacificUnionFounding) {
		return (PacificUnionFounding) getSqlMapClientTemplate().queryForObject(
				"pacificUnionFounding.getPacificUnionFoundingByOrgAndYear",
				pacificUnionFounding);
	}

	@Override
	public int updatePacificUnionFounding(
			PacificUnionFounding pacificUnionFounding) {
		return getSqlMapClientTemplate().update(
				"pacificUnionFounding.updatePacificUnionFounding",
				pacificUnionFounding);
	}
}
