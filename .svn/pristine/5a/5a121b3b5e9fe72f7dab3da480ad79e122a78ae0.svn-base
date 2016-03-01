package com.tianque.newVillage.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.ThreadVariable;
import com.tianque.newVillage.dao.EnvironmentalReformDao;
import com.tianque.newVillage.domain.EnvironmentalReform;

/**
 * @ClassName: EnvironmentalReformDaoImpl
 * @Description: 环境改造
 */
@Repository("environmentalReformDao")
public class EnvironmentalReformDaoImpl extends AbstractBaseDao implements
		EnvironmentalReformDao {

	@Override
	public EnvironmentalReform addEnvironmentalReform(
			EnvironmentalReform environmentalReform) {
		if (environmentalReform != null) {
			environmentalReform.setCreateUser(ThreadVariable.getUser()
					.getName());
			environmentalReform.setSourcesState(0L);
		}
		Long id = (Long) getSqlMapClientTemplate().insert(
				"environmentalReform.addEnvironmentalReform",
				environmentalReform);
		return getEnvironmentalReformById(id);
	}

	@Override
	public EnvironmentalReform getEnvironmentalReformById(Long id) {
		return (EnvironmentalReform) getSqlMapClientTemplate().queryForObject(
				"environmentalReform.getEnvironmentalReform", id);
	}

	@Override
	public void deleteEnvironmentalReformById(String[] id) {
		getSqlMapClientTemplate().delete(
				"environmentalReform.deleteEnvironmentalReform", id);
	}

	@Override
	public EnvironmentalReform updateEnvironmentalReform(
			EnvironmentalReform environmentalReform) {
		if (environmentalReform != null) {
			environmentalReform.setUpdateUser(ThreadVariable.getUser()
					.getName());
		}
		getSqlMapClientTemplate().update(
				"environmentalReform.updateEnvironmentalReform",
				environmentalReform);
		return getEnvironmentalReformById(environmentalReform.getId());
	}

	@Override
	public EnvironmentalReform getEnvironmentalReformByBasicId(Long basicId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("basicId", basicId);
		return (EnvironmentalReform) getSqlMapClientTemplate().queryForObject(
				"environmentalReform.getEnvironmentalReformByBasicId", map);
	}

}
