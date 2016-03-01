package com.tianque.newVillage.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.ThreadVariable;
import com.tianque.newVillage.dao.InfrastructureDao;
import com.tianque.newVillage.domain.Infrastructure;

/**
 * @ClassName: InfrastructureDaoImpl
 * @Description: 基础设施
 */
@Repository("infrastructureDao")
public class InfrastructureDaoImpl extends AbstractBaseDao implements
		InfrastructureDao {

	@Override
	public Infrastructure addInfrastructure(Infrastructure infrastructure) {
		if (infrastructure != null) {
			infrastructure.setCreateUser(ThreadVariable.getUser().getName());
			infrastructure.setSourcesState(0L);
		}
		Long id = (Long) getSqlMapClientTemplate().insert(
				"infrastructure.addInfrastructure", infrastructure);
		return getInfrastructureById(id);
	}

	@Override
	public Infrastructure getInfrastructureById(Long id) {
		return (Infrastructure) getSqlMapClientTemplate().queryForObject(
				"infrastructure.getInfrastructure", id);
	}

	@Override
	public void deleteInfrastructureById(String[] id) {
		getSqlMapClientTemplate().delete("infrastructure.deleteInfrastructure",
				id);
	}

	@Override
	public Infrastructure updateInfrastructure(Infrastructure infrastructure) {
		if (infrastructure != null) {
			infrastructure.setUpdateUser(ThreadVariable.getUser().getName());
		}
		getSqlMapClientTemplate().update("infrastructure.updateInfrastructure",
				infrastructure);
		return getInfrastructureById(infrastructure.getId());
	}

	@Override
	public Infrastructure getInfrastructureByBasicId(Long basicId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("basicId", basicId);
		return (Infrastructure) getSqlMapClientTemplate().queryForObject(
				"infrastructure.getInfrastructureByBasicId", map);
	}

}
