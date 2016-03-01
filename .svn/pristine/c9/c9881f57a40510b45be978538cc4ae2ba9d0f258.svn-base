package com.tianque.statRegrad.dao;

import java.util.Date;
import java.util.List;

import com.tianque.statRegrad.domain.IntegratedIndicator;
import com.tianque.statRegrad.domain.IssueGradeNode;

public interface IntegratedIndicatorForRegradedPointDao {
	public void statRegradedPoints();

	public List<Long> findIssueSumKinds(Long orgType, Long parentOrgId);

	public List<IntegratedIndicator> findIntegratedIndicatorByParentOrgIdAndYearAndMonth(
			Date startDate, Date endDate, Long targeOrgId, Long orgType);

	public void batchUpdateDate(List<IntegratedIndicator> integratedIndicators);

	public void batchAddDate(List<IntegratedIndicator> integratedIndicators);

	public List<IssueGradeNode> queryIssueGradeForList(String date);

	public void addIntegratedIndicator(IntegratedIndicator integratedIndicator);

	public int deleteIssueGrade(String year, String month, List<Long> orgList);

	public List<IntegratedIndicator> findIntegratedIndicatorsByOrgType(
			Long orgType, Long parentOrgId);
}
