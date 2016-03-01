package com.tianque.issue.service;

import java.util.List;

import com.tianque.issue.domain.PacificUnionFounding;

public interface PacificUnionFoundingService {

	public List<PacificUnionFounding> findPacificUnionFoundingsByYearAndParentOrgId(
			Integer year, Long parentOrgId);

	public boolean addPacificUnionFoundings(Integer year, Long parentOrgId,
			List<PacificUnionFounding> pacificUnionFoundings);

	/****************** 迁移合并方法 ***********************/
	public PacificUnionFounding getPacificUnionFoundingByOrgAndYear(
			PacificUnionFounding pacificUnionFounding);

	public int updatePacificUnionFounding(
			PacificUnionFounding pacificUnionFounding);
}
