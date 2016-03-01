package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.ExaminePlan;

public interface ExaminePlanDao {
	public ExaminePlan addExaminePlan(ExaminePlan examinePlan);

	public ExaminePlan getSimpleExaminePlanById(Long id);

	public void deleteExaminePlanById(Long id);

	public List<ExaminePlan> findExaminePlans();

	public Integer countExsistedExaminePlanByYear(String year);

	public PageInfo<ExaminePlan> findExaminePlansForPage(Integer page, Integer rows, String sidx,
			String sord);

	public List<Integer> findExaminePlanYears();
}
