package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.ExaminePlanDao;
import com.tianque.domain.ExamineItem;
import com.tianque.domain.ExaminePlan;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.ExamineItemService;
import com.tianque.service.ExaminePlanService;

@Service("examinePlanService")
@Transactional
public class ExaminePlanServiceImpl extends AbstractBaseDao implements
		ExaminePlanService {
	@Autowired
	private ExaminePlanDao examinePlanDao;
	@Autowired
	private ExamineItemService examineItemService;

	@Override
	public ExaminePlan addExaminePlan(ExaminePlan examinePlan) {
		if (examinePlan.getExamineItems() == null)
			throw new BusinessValidationException("参数错误");
		if (examinePlanDao
				.countExsistedExaminePlanByYear(examinePlan.getYear()) > 0)
			throw new BusinessValidationException("统计错误");
		ExaminePlan addExaminePlan = examinePlanDao.addExaminePlan(examinePlan);
		List<ExamineItem> examineItems = examinePlan.getExamineItems();
		List<ExamineItem> examineItemsInit = examineItemService
				.findExamineItemsByExaminePlanId(null);
		ExamineItem topExamineItem = new ExamineItem();
		for (ExamineItem examineItem : examineItemsInit) {
			ExaminePlan plan = new ExaminePlan();
			plan.setId(addExaminePlan.getId());
			examineItem.setPlan(plan);
			examineItem.setSelected(false);

			if (examineItem.getOwnerItem() == null
					|| examineItem.getOwnerItem().getId() == null) {
				topExamineItem = examineItem;
			} else {
				ExamineItem ownerItem = new ExamineItem();
				ownerItem.setId(topExamineItem.getId());
				examineItem.setOwnerItem(ownerItem);
			}

			for (ExamineItem examineItemSelect : examineItems) {
				examineItemSelect = examineItemService
						.getExamineItemById(examineItemSelect.getId());
				if (examineItemSelect.getId().equals(examineItem.getId())) {
					examineItem.setSelected(true);
					examineItemService.updateExamineItemSelectedById(true,
							examineItem.getOwnerItem().getId());
				}
			}

			ExamineItem refItem = new ExamineItem();
			refItem.setId(examineItem.getId());
			examineItem.setRefItem(refItem);
			examineItemService.addExamineItem(examineItem);
		}

		return addExaminePlan;
	}

	@Override
	public ExaminePlan getSimpleExaminePlanById(Long id) {
		return examinePlanDao.getSimpleExaminePlanById(id);
	}

	@Override
	public void deleteExaminePlanById(Long id) {
		examinePlanDao.deleteExaminePlanById(id);
		examineItemService.deleteExamineItemByExaminePlanId(id);
	}

	@Override
	public ExaminePlan updateExaminePlan(ExaminePlan examinePlan) {
		if (examinePlan.getExamineItems() == null)
			throw new BusinessValidationException("参数错误");
		processExamineItems(
				examineItemService.findSelectedExamineItemsByExaminePlanId(examinePlan
						.getId()), examinePlan.getExamineItems());
		return examinePlanDao.getSimpleExaminePlanById(examinePlan.getId());
	}

	private void processExamineItems(List<ExamineItem> oldList,
			List<ExamineItem> newList) {
		for (ExamineItem examineItem : oldList) {
			if (examineItem.getOwnerItem() != null) {
				if (!newList.contains(examineItem)) {
					examineItemService.updateExamineItemSelectedById(false,
							examineItem.getId());
					updateOwnerExamineItemSelectedById(examineItem
							.getOwnerItem().getId());
				}
			}
		}
		for (ExamineItem examineItem : newList) {
			if (!oldList.contains(examineItem)) {
				examineItemService.updateExamineItemSelectedById(true,
						examineItem.getId());
				examineItemService.updateExamineItemSelectedById(
						true,
						examineItemService
								.getExamineItemById(examineItem.getId())
								.getOwnerItem().getId());
			}
		}
	}

	private void updateOwnerExamineItemSelectedById(Long id) {
		if (examineItemService.getExamineItemSelectedCountByOwnerItemId(id) == 0) {
			examineItemService.updateExamineItemSelectedById(false, id);
		}
	}

	@Override
	public List<ExaminePlan> findExaminePlans() {
		return examinePlanDao.findExaminePlans();
	}

	@Override
	public PageInfo<ExaminePlan> findExaminePlansForPage(Integer page,
			Integer rows, String sidx, String sord) {

		return examinePlanDao.findExaminePlansForPage(page, rows, sidx, sord);
	}

	@Override
	public Boolean exsistedExaminePlanByYear(String year) {
		int count = examinePlanDao.countExsistedExaminePlanByYear(year);
		return count > 0 ? true : false;
	}

	@Override
	public List<Integer> findExaminePlanYears() {
		return examinePlanDao.findExaminePlanYears();
	}
}
