package com.tianque.issue.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.issue.dao.SupervisionAdministrationDao;
import com.tianque.issue.domain.SupervisionAdministration;

@Repository("supervisionAdministrationDao")
public class SupervisionAdministrationDaoImpl extends AbstractBaseDao implements
		SupervisionAdministrationDao {

	@Override
	public SupervisionAdministration getSupervisionAdministrationByOrgId(
			Long orgId) {

		return (SupervisionAdministration) getSqlMapClientTemplate()
				.queryForObject(
						"supervisionAdministration.getSupervisionAdministrationByOrgId",
						orgId);
	}

	@Override
	public SupervisionAdministration updateSupervisionAdministration(
			SupervisionAdministration supervisionAdministration) {
		getSqlMapClientTemplate().update(
				"supervisionAdministration.updateSupervisionAdministration",
				supervisionAdministration);
		return getSupervisionAdministrationById(supervisionAdministration
				.getId());
	}

	@Override
	public SupervisionAdministration addSupervisionAdministration(
			SupervisionAdministration supervisionAdministration) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"supervisionAdministration.addSupervisionAdministration",
				supervisionAdministration);
		return getSupervisionAdministrationById(id);
	}

	private SupervisionAdministration getSupervisionAdministrationById(Long id) {
		return (SupervisionAdministration) getSqlMapClientTemplate()
				.queryForObject(
						"supervisionAdministration.getSupervisionAdministrationById",
						id);
	}
}
