package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.ExaminePlanDao;
import com.tianque.domain.ExaminePlan;

@Repository("examinePlanDao")
public class ExaminePlanDaoImpl extends AbstractBaseDao implements ExaminePlanDao {
	@Override
	public ExaminePlan addExaminePlan(ExaminePlan examinePlan) {
		Long id = (Long) getSqlMapClientTemplate()
				.insert("examinePlan.addExaminePlan", examinePlan);
		return getSimpleExaminePlanById(id);
	}

	@Override
	public ExaminePlan getSimpleExaminePlanById(Long id) {
		return (ExaminePlan) getSqlMapClientTemplate().queryForObject(
				"examinePlan.getSimpleExaminePlanById", id);
	}

	@Override
	public void deleteExaminePlanById(Long id) {
		getSqlMapClientTemplate().delete("examinePlan.deleteExaminePlanById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExaminePlan> findExaminePlans() {
		return getSqlMapClientTemplate().queryForList("examinePlan.findExaminePlans");
	}

	@Override
	public Integer countExsistedExaminePlanByYear(String year) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("year", year);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"examinePlan.countExsistedExaminePlanByYear", map);
	}

	@Override
	public PageInfo<ExaminePlan> findExaminePlansForPage(Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sortField", sidx);
		map.put("order", sord);

		Integer countExaminePlans = (Integer) getSqlMapClientTemplate().queryForObject(
				"examinePlan.countExaminePlansForListPage");
		int pageCount = 0;
		if (pageSize != 0 && countExaminePlans > 0)
			pageCount = (countExaminePlans - 1) / pageSize + 1;
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		List<ExaminePlan> list = getSqlMapClientTemplate().queryForList(
				"examinePlan.findExaminePlansForListPage", map, (pageNum - 1) * pageSize, pageSize);

		PageInfo<ExaminePlan> pageInfo = new PageInfo<ExaminePlan>();
		pageInfo.setResult(list);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setTotalRowSize(countExaminePlans);
		pageInfo.setCurrentPage(pageNum);

		return pageInfo;
	}

	@Override
	public List<Integer> findExaminePlanYears() {
		return (List<Integer>) getSqlMapClientTemplate().queryForList(
				"examinePlan.findExaminePlanYears");
	}
}
