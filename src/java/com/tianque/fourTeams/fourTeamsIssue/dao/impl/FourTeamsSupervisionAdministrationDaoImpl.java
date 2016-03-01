package com.tianque.fourTeams.fourTeamsIssue.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsSupervisionAdministrationDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsSupervisionAdministration;

@Repository("fourTeamsSupervisionAdministrationDao")
public class FourTeamsSupervisionAdministrationDaoImpl extends AbstractBaseDao implements
		FourTeamsSupervisionAdministrationDao {

	@Override
	public FourTeamsSupervisionAdministration getSupervisionAdministrationByOrgId(
			Long orgId) {

		return (FourTeamsSupervisionAdministration) getSqlMapClientTemplate()
				.queryForObject(
						"supervisionAdministration.getSupervisionAdministrationByOrgId",
						orgId);
	}

	@Override
	public FourTeamsSupervisionAdministration updateSupervisionAdministration(
			FourTeamsSupervisionAdministration supervisionAdministration) {
		getSqlMapClientTemplate().update(
				"supervisionAdministration.updateSupervisionAdministration",
				supervisionAdministration);
		return getSupervisionAdministrationById(supervisionAdministration
				.getId());
	}

	@Override
	public FourTeamsSupervisionAdministration addSupervisionAdministration(
			FourTeamsSupervisionAdministration supervisionAdministration) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"supervisionAdministration.addSupervisionAdministration",
				supervisionAdministration);
		return getSupervisionAdministrationById(id);
	}

	private FourTeamsSupervisionAdministration getSupervisionAdministrationById(Long id) {
		return (FourTeamsSupervisionAdministration) getSqlMapClientTemplate()
				.queryForObject(
						"supervisionAdministration.getSupervisionAdministrationById",
						id);
	}
}
