package com.tianque.plugin.analysisNew.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.dao.impl.BaseDaoImpl;
import com.tianque.domain.Organization;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.plugin.analysisNew.domain.IssueAreaStatNew;
import com.tianque.plugin.analysisNew.util.QueryType;

@Repository("issueAreaStatNewDao")
public class IssueAreaStatNewDaoImpl extends BaseDaoImpl<IssueAreaStatNew>
		implements IssueAreaStatNewDao {

	@Override
	public IssueAreaStatNew getIssueAreaStatsByYearAndMonthAndOrgCode(
			Date startDate, Date endDate, Organization organization,
			Integer queryType) {
		return get(buildQueryMap(startDate, endDate, organization, queryType),
				QueryType.STATMENT.get(queryType));
	}

	private Map<String, Object> buildQueryMap(Date startDate, Date endDate,
			Organization organization, Integer queryType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		if (QueryType.HISTORY.contains(queryType)) {
			map.put("orgId", organization.getId());
		} else {
			map.put("orgInternalCode", organization.getOrgInternalCode());
		}

		if (queryType.intValue() == QueryType.AREA_CLASSIFICATION
				|| queryType.intValue() == QueryType.AREA_CLASSIFICATION_HISTORY) {
			map.put("contradiction", IssueTypes.PEOPLELIVE_SERVICE);
			map.put("resolveTheContradiction",
					IssueTypes.RESOLVETHECONTRADICTIONS);
			map.put("securityPrecaution", IssueTypes.SECURITYPRECAUTIONS);
			map.put("specialPopulation", IssueTypes.SPECIALPOPULATIONS);
			map.put("socialCondition", IssueTypes.SOCIALCONDITIONS);
			map.put("policiesAndLaw", IssueTypes.POLICIESANDLAWS);
			map.put("emergencie", IssueTypes.EMERGENCIES);
			map.put("otherManage", IssueTypes.OTHERMANAGE);
		}

		if (queryType.intValue() == QueryType.STEP
				|| queryType.intValue() == QueryType.STEP_HISTORY) {
			map.put("resolveTheContradiction",
					IssueTypes.RESOLVETHECONTRADICTIONS);
			map.put("securityPrecaution", IssueTypes.SECURITYPRECAUTIONS);
			map.put("emergencie", IssueTypes.EMERGENCIES);
			map.put("otherManage", IssueTypes.OTHERMANAGE);
		}
		return map;
	}

	@Override
	public IssueAreaStatNew getHistoryIssueAreaStatsByYearAndMonthAndOrgCode(
			Date startDate, Date endDate, String orgInternalCode,
			Integer queryType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("orgInternalCode", orgInternalCode);
		return get(map, QueryType.STATMENT.get(queryType) + "History");
	}
}
