package com.tianque.statRegrad.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.ThreadVariable;
import com.tianque.statRegrad.domain.IntegratedIndicator;
import com.tianque.statRegrad.domain.IssueGradeNode;

@Repository("integratedIndicatorForRegradedPointDao")
public class IntegratedIndicatorForRegradedPointDaoImpl extends AbstractBaseDao
		implements IntegratedIndicatorForRegradedPointDao {

	@Override
	public void statRegradedPoints() {
		getSqlMapClientTemplate().insert(
				"integratedIndicator.statRegradedPoints",
				ThreadVariable.getUser().getName());
	}

	@Override
	public List<Long> findIssueSumKinds(Long orgType, Long parentOrgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgType", orgType);
		map.put("parentOrgId", parentOrgId);
		return getSqlMapClientTemplate().queryForList(
				"integratedIndicator.countIssueSumByOrgType", map);
	}

	@Override
	public List<IntegratedIndicator> findIntegratedIndicatorByParentOrgIdAndYearAndMonth(
			Date startDate, Date endDate, Long targeOrgId, Long orgType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("parentOrgId", targeOrgId);
		map.put("orgType", orgType);
		return getSqlMapClientTemplate()
				.queryForList(
						"integratedIndicator.findIntegratedIndicatorByParentOrgIdAndYearAndMonth",
						map);
	}

	@Override
	public void batchUpdateDate(List<IntegratedIndicator> integratedIndicators) {
		batchUpdateDate(integratedIndicators,
				"integratedIndicator.updateIntegratedIndicator");
	}

	@Override
	public List<IntegratedIndicator> findIntegratedIndicatorsByOrgType(
			Long orgType, Long parentOrgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgType", orgType);
		map.put("parentOrgId", parentOrgId);
		return getSqlMapClientTemplate().queryForList(
				"integratedIndicator.findIntegratedIndicatorsByOrgType", map);
	}

	@Override
	public void batchAddDate(List<IntegratedIndicator> integratedIndicators) {
		batchInsertDate(integratedIndicators,
				"integratedIndicator.addIntegratedIndicator");
	}

	@Override
	public List<IssueGradeNode> queryIssueGradeForList(String date) {
		return getSqlMapClientTemplate().queryForList(
				"integratedIndicator.queryIssueGradeForList", date);
	}

	@Override
	public void addIntegratedIndicator(IntegratedIndicator integratedIndicator) {
		getSqlMapClientTemplate().insert(
				"integratedIndicator.addIntegratedIndicator",
				integratedIndicator);

	}

	@Override
	public int deleteIssueGrade(String year, String month, List<Long> orgList) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("year", year);
		condition.put("month", month);
		if (orgList != null) {
			condition.put("orgList", orgList);
		}
		return getSqlMapClientTemplate().delete(
				"integratedIndicator.deleteIssueGrade", condition);
	}

}
