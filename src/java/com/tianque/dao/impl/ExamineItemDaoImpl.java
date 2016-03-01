package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.ExamineItemDao;
import com.tianque.domain.ExamineItem;

@Repository("examineItemDao")
public class ExamineItemDaoImpl extends AbstractBaseDao implements ExamineItemDao {
	@Override
	public ExamineItem addExamineItem(ExamineItem examineItem) {
		Long id = (Long) getSqlMapClientTemplate()
				.insert("examineItem.addExamineItem", examineItem);
		return getSimpleExamineItemById(id);
	}

	@Override
	public ExamineItem getSimpleExamineItemById(Long id) {
		return (ExamineItem) getSqlMapClientTemplate().queryForObject(
				"examineItem.getSimpleExamineItemById", id);
	}

	@Override
	public void deleteExamineItemByExaminePlanId(Long examinePlanId) {
		getSqlMapClientTemplate().delete("examineItem.deleteExamineItemByExaminePlanId",
				examinePlanId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamineItem> findExamineItemsByExamineCatalogIdAndPlanId(Long examineCatalogId,
			Long planId, Boolean selected) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("examineCatalogId", examineCatalogId);
		map.put("planId", planId);
		map.put("selected", selected);

		return getSqlMapClientTemplate().queryForList(
				"examineItem.findExamineItemsByExamineCatalogIdAndPlanId", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamineItem> findExamineItemsByExaminePlanId(Long examinePlanId) {
		return getSqlMapClientTemplate().queryForList(
				"examineItem.findExamineItemsByExaminePlanId", examinePlanId);
	}

	@Override
	public List<ExamineItem> findSelectedExamineItemsByExaminePlanId(Long planId) {

		return getSqlMapClientTemplate().queryForList(
				"examineItem.findSelectedExamineItemsByExaminePlanId", planId);
	}

	@Override
	public void updateExamineItemSelectedById(Boolean selected, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("selected", selected);
		map.put("id", id);

		getSqlMapClientTemplate().update("examineItem.updateExamineItemSelectedById", map);
	}

	@Override
	public int getExamineItemSelectedCountByOwnerItemId(Long ownerItemId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"examineItem.getExamineItemSelectedCountByOwnerItemId", ownerItemId);
	}

}
