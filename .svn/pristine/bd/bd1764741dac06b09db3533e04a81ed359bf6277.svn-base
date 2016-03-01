package com.tianque.service;

import java.util.List;

import com.tianque.domain.ExamineCatalog;
import com.tianque.domain.vo.ExamineItemVo;

public interface ExamineCatalogService {
	public ExamineCatalog addExamineCatalog(ExamineCatalog examineCatalog);

	public List<ExamineItemVo> findExamineItemVosByPlanId(Long planId);

	public List<ExamineCatalog> findSelectedExamineCatalogsByPlanId(Long planId);

	public List<ExamineItemVo> conversionExamineItemVos(List<ExamineCatalog> examineCatalogs,
			Long planId, Boolean selected);
}
