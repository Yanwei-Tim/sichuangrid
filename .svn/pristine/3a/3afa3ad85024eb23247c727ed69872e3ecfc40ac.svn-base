package com.tianque.newVillage.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.ThreadVariable;
import com.tianque.newVillage.dao.IndustryDevelopmentDao;
import com.tianque.newVillage.domain.IndustryDevelopment;

/**
 * @ClassName: IndustryDevelopmentDaoImpl
 * @Description: 产业发展
 */
@Repository("industryDevelopmentDao")
public class IndustryDevelopmentDaoImpl extends AbstractBaseDao implements
		IndustryDevelopmentDao {

	@Override
	public IndustryDevelopment addIndustryDevelopment(
			IndustryDevelopment industryDevelopment) {
		if (industryDevelopment != null) {
			industryDevelopment.setCreateUser(ThreadVariable.getUser()
					.getName());
			industryDevelopment.setSourcesState(0L);
		}
		Long id = (Long) getSqlMapClientTemplate().insert(
				"industryDevelopment.addIndustryDevelopment",
				industryDevelopment);
		return getIndustryDevelopmentById(id);
	}

	@Override
	public IndustryDevelopment getIndustryDevelopmentById(Long id) {
		return (IndustryDevelopment) getSqlMapClientTemplate().queryForObject(
				"industryDevelopment.getIndustryDevelopment", id);
	}

	@Override
	public void deleteIndustryDevelopmentById(String[] id) {
		getSqlMapClientTemplate().delete(
				"industryDevelopment.deleteIndustryDevelopment", id);
	}

	@Override
	public IndustryDevelopment updateIndustryDevelopment(
			IndustryDevelopment industryDevelopment) {
		if (industryDevelopment != null) {
			industryDevelopment.setUpdateUser(ThreadVariable.getUser()
					.getName());
		}
		getSqlMapClientTemplate().update(
				"industryDevelopment.updateIndustryDevelopment",
				industryDevelopment);
		return getIndustryDevelopmentById(industryDevelopment.getId());
	}

	@Override
	public IndustryDevelopment getIndustryDevelopmentByBasicId(Long basicId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("basicId", basicId);
		return (IndustryDevelopment) getSqlMapClientTemplate().queryForObject(
				"industryDevelopment.getIndustryDevelopmentByBasicId", map);
	}

}
