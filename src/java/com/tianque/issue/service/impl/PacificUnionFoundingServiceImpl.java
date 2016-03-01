package com.tianque.issue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.issue.dao.PacificUnionFoundingDao;
import com.tianque.issue.domain.PacificUnionFounding;
import com.tianque.issue.service.PacificUnionFoundingService;

@Service("pacificUnionFoundingService")
public class PacificUnionFoundingServiceImpl implements
		PacificUnionFoundingService {
	@Autowired
	private PacificUnionFoundingDao pacificUnionFoundingDao;

	@Override
	public List<PacificUnionFounding> findPacificUnionFoundingsByYearAndParentOrgId(
			Integer year, Long parentOrgId) {
		return pacificUnionFoundingDao
				.findPacificUnionFoundingsByYearAndParentOrgId(year,
						parentOrgId);
	}

	@Override
	@Transactional
	public boolean addPacificUnionFoundings(Integer year, Long parentOrgId,
			List<PacificUnionFounding> pacificUnionFoundings) {
		pacificUnionFoundingDao
				.deletePacificUnionFoundingsByYearAndParentOrgId(year,
						parentOrgId);
		return pacificUnionFoundingDao
				.addPacificUnionFoundings(pacificUnionFoundings);
	}

	/****************** 迁移合并方法 **********************/
	@Override
	public PacificUnionFounding getPacificUnionFoundingByOrgAndYear(
			PacificUnionFounding pacificUnionFounding) {
		return pacificUnionFoundingDao
				.getPacificUnionFoundingByOrgAndYear(pacificUnionFounding);
	}

	@Override
	public int updatePacificUnionFounding(
			PacificUnionFounding pacificUnionFounding) {
		return pacificUnionFoundingDao
				.updatePacificUnionFounding(pacificUnionFounding);
	}
}
