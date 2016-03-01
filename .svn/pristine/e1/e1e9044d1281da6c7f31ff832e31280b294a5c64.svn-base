package com.tianque.newVillage.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.ThreadVariable;
import com.tianque.newVillage.dao.OrganizationConstructionDao;
import com.tianque.newVillage.domain.OrganizationConstruction;

/**
 * @ClassName: OrganizationConstructionDaoImpl
 * @Description: 基层组织
 */
@Repository("organizationConstructionDao")
public class OrganizationConstructionDaoImpl extends AbstractBaseDao implements
		OrganizationConstructionDao {

	@Override
	public OrganizationConstruction addOrgConstruction(
			OrganizationConstruction orgConstruction) {
		if (orgConstruction != null) {
			orgConstruction.setCreateUser(ThreadVariable.getUser().getName());
			orgConstruction.setSourcesState(0L);
		}
		Long id = (Long) getSqlMapClientTemplate().insert(
				"organizationConstruction.addOrgConstruction", orgConstruction);
		return getOrgConstructionById(id);
	}

	@Override
	public OrganizationConstruction getOrgConstructionById(Long id) {
		return (OrganizationConstruction) getSqlMapClientTemplate()
				.queryForObject("organizationConstruction.getOrgConstruction",
						id);
	}

	@Override
	public void deleteOrgConstructionById(String[] id) {
		getSqlMapClientTemplate().delete(
				"organizationConstruction.deleteOrgConstruction", id);
	}

	@Override
	public OrganizationConstruction updateOrgConstruction(
			OrganizationConstruction orgConstruction) {
		if (orgConstruction != null) {
			orgConstruction.setUpdateUser(ThreadVariable.getUser().getName());
		}
		getSqlMapClientTemplate().update(
				"organizationConstruction.updateOrgConstruction",
				orgConstruction);
		return getOrgConstructionById(orgConstruction.getId());

	}

	@Override
	public OrganizationConstruction getOrgConstructionByBasicId(Long basicId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("basicId", basicId);
		return (OrganizationConstruction) getSqlMapClientTemplate()
				.queryForObject(
						"organizationConstruction.getOrgConstructionByBasicId",
						map);
	}
}
