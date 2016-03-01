package com.tianque.issue.dao;

import java.util.List;

import com.tianque.issue.domain.PacificUnionFounding;

public interface PacificUnionFoundingDao {

	List<PacificUnionFounding> findPacificUnionFoundingsByYearAndParentOrgId(
			Integer year, Long parentOrgId);

	boolean addPacificUnionFoundings(
			List<PacificUnionFounding> pacificUnionFoundings);

	boolean deletePacificUnionFoundingsByYearAndParentOrgId(Integer year,
			Long parentOrgId);

	public PacificUnionFounding getPacificUnionFoundingByOrgAndYear(
			PacificUnionFounding pacificUnionFounding);

	public int updatePacificUnionFounding(
			PacificUnionFounding pacificUnionFounding);

}
