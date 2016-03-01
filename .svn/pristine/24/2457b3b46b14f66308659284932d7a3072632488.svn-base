package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.ExaminePlan;

public interface ExaminePlanService {
	public ExaminePlan addExaminePlan(ExaminePlan examinePlan);

	public ExaminePlan getSimpleExaminePlanById(Long id);

	public void deleteExaminePlanById(Long id);

	public ExaminePlan updateExaminePlan(ExaminePlan examinePlan);

	public List<ExaminePlan> findExaminePlans();

	public PageInfo<ExaminePlan> findExaminePlansForPage(Integer page, Integer rows, String sidx,
			String sord);

	public Boolean exsistedExaminePlanByYear(String year);

	public List<Integer> findExaminePlanYears();
}
