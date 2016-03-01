package com.tianque.service;

import java.util.List;

import com.tianque.domain.ExamineItem;
import com.tianque.domain.vo.ExamineItemVo;

public interface ExamineItemService {
	public ExamineItem addExamineItem(ExamineItem examineItem);

	public ExamineItem getSimpleExamineItemById(Long id);

	public void deleteExamineItemByExaminePlanId(Long examinePlanId);

	public List<ExamineItem> findExamineItemsByExaminePlanId(Long examinePlanId);

	public ExamineItem getExamineItemById(Long id);

	public List<ExamineItem> findExamineItemsByExamineCatalogId(Long examineCatalogId);

	public List<ExamineItem> findSelectedExamineItemsByExaminePlanId(Long planId);

	public List<ExamineItemVo> findSelectedExamineItemVosByExaminePlanId(Long planId);

	public void updateExamineItemSelectedById(Boolean selected, Long id);

	public int getExamineItemSelectedCountByOwnerItemId(Long ownerItemId);
}
