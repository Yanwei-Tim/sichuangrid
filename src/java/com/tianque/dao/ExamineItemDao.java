package com.tianque.dao;

import java.util.List;

import com.tianque.domain.ExamineItem;

public interface ExamineItemDao {
	public ExamineItem addExamineItem(ExamineItem examineItem);

	public ExamineItem getSimpleExamineItemById(Long id);

	public void deleteExamineItemByExaminePlanId(Long examinePlanId);

	public List<ExamineItem> findExamineItemsByExamineCatalogIdAndPlanId(Long examineCatalogId,
			Long planId, Boolean selected);

	public List<ExamineItem> findExamineItemsByExaminePlanId(Long examinePlanId);

	public List<ExamineItem> findSelectedExamineItemsByExaminePlanId(Long planId);

	public void updateExamineItemSelectedById(Boolean selected, Long id);

	public int getExamineItemSelectedCountByOwnerItemId(Long ownerItemId);
}
