package com.tianque.dao;

import java.util.List;

import com.tianque.domain.ExamineCatalog;

public interface ExamineCatalogDao {
	public ExamineCatalog addExamineCatalog(ExamineCatalog examineCatalog);

	public ExamineCatalog getSimpleExamineCatalogById(Long id);

	public List<ExamineCatalog> findExamineCatalogs();

	public List<ExamineCatalog> findSelectedExamineCatalogsByPlanId(Long planId);
}
