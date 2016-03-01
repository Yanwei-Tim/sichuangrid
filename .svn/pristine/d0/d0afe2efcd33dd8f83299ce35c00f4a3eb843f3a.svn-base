package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.dao.ExamineItemDao;
import com.tianque.domain.ExamineCatalog;
import com.tianque.domain.ExamineItem;
import com.tianque.domain.vo.ExamineItemVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.ExamineCatalogService;
import com.tianque.service.ExamineItemService;

@Service("examineItemService")
public class ExamineItemServiceImpl extends AbstractBaseService implements
		ExamineItemService {

	@Autowired
	private ExamineItemDao examineItemDao;
	@Autowired
	private ExamineCatalogService examineCatalogService;

	@Override
	public ExamineItem addExamineItem(ExamineItem examineItem) {
		return examineItemDao.addExamineItem(examineItem);
	}

	@Override
	public ExamineItem getSimpleExamineItemById(Long id) {
		return examineItemDao.getSimpleExamineItemById(id);
	}

	@Override
	public void deleteExamineItemByExaminePlanId(Long examinePlanId) {
		examineItemDao.deleteExamineItemByExaminePlanId(examinePlanId);
	}

	@Override
	public List<ExamineItem> findExamineItemsByExamineCatalogId(
			Long examineCatalogId) {
		return examineItemDao.findExamineItemsByExamineCatalogIdAndPlanId(
				examineCatalogId, null, null);
	}

	@Override
	public List<ExamineItem> findExamineItemsByExaminePlanId(Long examinePlanId) {
		return examineItemDao.findExamineItemsByExaminePlanId(examinePlanId);
	}

	@Override
	public ExamineItem getExamineItemById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id 不能为空");
		}
		return examineItemDao.getSimpleExamineItemById(id);
	}

	@Override
	public List<ExamineItem> findSelectedExamineItemsByExaminePlanId(Long planId) {
		return examineItemDao.findSelectedExamineItemsByExaminePlanId(planId);
	}

	@Override
	public void updateExamineItemSelectedById(Boolean selected, Long id) {
		examineItemDao.updateExamineItemSelectedById(selected, id);
	}

	@Override
	public int getExamineItemSelectedCountByOwnerItemId(Long ownerItemId) {
		return examineItemDao
				.getExamineItemSelectedCountByOwnerItemId(ownerItemId);
	}

	@Override
	public List<ExamineItemVo> findSelectedExamineItemVosByExaminePlanId(
			Long planId) {
		List<ExamineCatalog> selectedExamineCatalogs = examineCatalogService
				.findSelectedExamineCatalogsByPlanId(planId);

		return examineCatalogService.conversionExamineItemVos(
				selectedExamineCatalogs, planId, true);
	}
}
