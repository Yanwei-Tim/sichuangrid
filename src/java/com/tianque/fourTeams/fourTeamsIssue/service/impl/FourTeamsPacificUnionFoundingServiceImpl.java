package com.tianque.fourTeams.fourTeamsIssue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsPacificUnionFoundingDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsPacificUnionFounding;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsPacificUnionFoundingService;

@Service("fourTeamsPacificUnionFoundingService")
public class FourTeamsPacificUnionFoundingServiceImpl implements
		FourTeamsPacificUnionFoundingService {
	@Autowired
	private FourTeamsPacificUnionFoundingDao pacificUnionFoundingDao;

	@Override
	public List<FourTeamsPacificUnionFounding> findPacificUnionFoundingsByYearAndParentOrgId(
			Integer year, Long parentOrgId) {
		return pacificUnionFoundingDao
				.findPacificUnionFoundingsByYearAndParentOrgId(year,
						parentOrgId);
	}

	@Override
	@Transactional
	public boolean addPacificUnionFoundings(Integer year, Long parentOrgId,
			List<FourTeamsPacificUnionFounding> pacificUnionFoundings) {
		pacificUnionFoundingDao
				.deletePacificUnionFoundingsByYearAndParentOrgId(year,
						parentOrgId);
		return pacificUnionFoundingDao
				.addPacificUnionFoundings(pacificUnionFoundings);
	}
}
