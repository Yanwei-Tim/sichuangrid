package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.IssueInvestigationMediateDao;
import com.tianque.domain.IssueInvestigationMediate;

@SuppressWarnings("unchecked")
@Repository("issueInvestigationMediateDao")
public class IssueInvestigationMediateDaoImpl extends AbstractBaseDao implements
		IssueInvestigationMediateDao {

	@Override
	public void deleteIssueInvestigationMediateByStartDateAndOrg(int year, int month,
			String orgInternalCode) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("year", year);
		condition.put("month", month);
		condition.put("orgInternalCode", orgInternalCode);
		getSqlMapClientTemplate().delete(
				"issueInvestigationMediate.deleteIssueInvestigationMediateByStartDateAndOrg",
				condition);
	}

	@Override
	public void addIssueInvestigationMediates(List<IssueInvestigationMediate> mediates) {
		batchInsertDate(mediates, "issueInvestigationMediate.addIssueInvestigationMediate");
	}

	@Override
	public List<IssueInvestigationMediate> sumIssueInvestigationMediateByOrgAndMonth(int year,
			int month, String orgInternalCode) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("year", year);
		condition.put("month", month);
		condition.put("orgInternalCode", orgInternalCode);
		return getSqlMapClientTemplate().queryForList(
				"issueInvestigationMediate.sumIssueInvestigationMediateByOrgAndMonth", condition);
	}

	@Override
	public Long statYearTotalCountByIssueType(String orgInternalCode, int year, int month,
			Long typeId, String subset) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("year", year);
		condition.put("month", month);
		condition.put("issueTypeId", typeId);
		condition.put("subset", subset);
		condition.put("orgInternalCode", orgInternalCode);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"issueInvestigationMediate.statYearTotalCount", condition);
	}

	@Override
	public Long statYearTotalCountByTypeName(String orgInternalCode, int year, int month,
			String typeDisplayName, String subset) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("year", year);
		condition.put("month", month);
		condition.put("typename", typeDisplayName);
		condition.put("subset", subset);
		condition.put("orgInternalCode", orgInternalCode);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"issueInvestigationMediate.statYearTotalCount", condition);
	}

}
