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

@Repository("issueLevelStatNewDaoImpl")
public class IssueLevelStatNewDaoImpl extends BaseDaoImpl<IssueAreaStatNew>
		implements IssueLevelStatNewDao {

	@Override
	public IssueAreaStatNew getIssueLevelStatsByYearAndMonthAndOrgCodeAndOrgLevel(
			Date startDate, Date endDate, Organization organization,
			Long orgLevelId, Integer queryType) {
		return get(
				buildQueryMap(startDate, endDate, organization, queryType,
						orgLevelId, "orglevel"),
				QueryType.STATMENT.get(queryType));
	}

	@Override
	public IssueAreaStatNew getIssueLevelStatsByYearAndMonthAndOrgCodeAndFunOrgType(
			Date startDate, Date endDate, Organization organization,
			Long funOrgType, Integer queryType) {
		return get(
				buildQueryMap(startDate, endDate, organization, queryType,
						funOrgType, "functionalorgtype"),
				QueryType.STATMENT.get(queryType));
	}

	private Map<String, Object> buildQueryMap(Date startDate, Date endDate,
			Organization organization, Integer queryType,
			Long orgLevelIdOrFunOrgType, String fieldName) {
		Map<String, Object> map = buildHistoryQueryMap(startDate, endDate,
				organization, queryType, orgLevelIdOrFunOrgType, fieldName);

		if (queryType.intValue() == QueryType.LEVEL_CLASSIFICATION) {
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
		return map;
	}

	private Map<String, Object> buildHistoryQueryMap(Date startDate,
			Date endDate, Organization organization, Integer queryType,
			Long orgLevelIdOrFunOrgType, String fieldName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("orgInternalCode", organization.getOrgInternalCode());
		map.put("orgId", organization.getId());
		map.put("orgLevelIdOrFunOrgType", orgLevelIdOrFunOrgType);
		map.put("fieldName", fieldName);
		return map;
	}

	@Override
	public IssueAreaStatNew getHistoryIssueLevelStatsByYearAndMonthAndOrgCodeAndOrgLevel(
			Date startDate, Date endDate, Organization organization,
			Long orgLevelId, Integer queryType) {
		return get(
				buildHistoryQueryMap(startDate, endDate, organization,
						queryType, orgLevelId, "orglevel"),
				QueryType.STATMENT.get(queryType) + "History");
	}

	@Override
	public IssueAreaStatNew getHistoryIssueLevelStatsByYearAndMonthAndOrgCodeAndFunOrgType(
			Date startDate, Date endDate, Organization organization,
			Long funOrgType, Integer queryType) {
		return get(
				buildHistoryQueryMap(startDate, endDate, organization,
						queryType, funOrgType, "funorgtype"),
				QueryType.STATMENT.get(queryType) + "History");
	}
}
