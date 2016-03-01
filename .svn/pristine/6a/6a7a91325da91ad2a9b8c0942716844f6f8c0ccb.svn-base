package com.tianque.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.ExamineCatalogDao;
import com.tianque.domain.ExamineCatalog;

@Repository("examineCatalogDao")
public class ExamineCatalogDaoImpl extends AbstractBaseDao implements ExamineCatalogDao {
	@Override
	public ExamineCatalog addExamineCatalog(ExamineCatalog examineCatalog) {
		Long id = (Long) getSqlMapClientTemplate().insert("examineCatalog.addExamineCatalog",
				examineCatalog);
		return getSimpleExamineCatalogById(id);
	}

	@Override
	public ExamineCatalog getSimpleExamineCatalogById(Long id) {
		return (ExamineCatalog) getSqlMapClientTemplate().queryForObject(
				"examineCatalog.getSimpleExamineCatalogById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamineCatalog> findExamineCatalogs() {
		return getSqlMapClientTemplate().queryForList("examineCatalog.findExamineCatalogs");
	}

	@Override
	public List<ExamineCatalog> findSelectedExamineCatalogsByPlanId(Long planId) {
		return getSqlMapClientTemplate().queryForList(
				"examineCatalog.findSelectedExamineCatalogsByPlanId", planId);
	}
}
