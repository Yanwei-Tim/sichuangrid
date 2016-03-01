package com.tianque.plugin.analysisNew.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.CalendarUtil;

@Repository("issueAnalysisChartNewDao")
public class IssueAnalysisChartNewDaoImpl extends AbstractBaseDao implements
		IssueAnalysisChartNewDao {

	@Override
	public List<Map<String, Object>> getStatisticColumnFromHistory(Long orgId,
			Integer year, Integer month, Integer quarter, String issueTypeDomain) {
		Map map = createQueryMap(orgId, year, month, quarter);
		map.put("domainname", issueTypeDomain);
		return getSqlMapClientTemplate()
				.queryForList(
						"issueanalysisNew.getCountColumnByMonthAndIssueDomainName",
						map);
	}

	@Override
	public List<Map<String, Object>> getStatisticColumnFromRealTime(Long orgId,
			Date startDate, Date endDate, String issueTypeDomain) {
		Map map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("domainname", issueTypeDomain);
		return getSqlMapClientTemplate().queryForList(
				"issueanalysisNew.getCountColumnByTimeAndIssueDomainName", map);
	}

	@Override
	public List<Map<String, Object>> getAllIssueDomainStatisticPieFromHistory(
			String orgCode, Integer year, Integer month, Integer quarter) {
		Map map = createQueryMap(null, year, month, quarter);
		map.put("orgCode", orgCode);
		return getSqlMapClientTemplate().queryForList(
				"issueanalysisNew.getAllIssueDomainTypeCountPieByMonthAndYear",
				map);
	}

	@Override
	public List<Map<String, Object>> getAllIssueDomainStatisticPieFromRealTime(
			String orgCode, Date startDate, Date endDate) {
		Map map = new HashMap<String, Object>();
		map.put("orgCode", orgCode);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return getSqlMapClientTemplate().queryForList(
				"issueanalysisNew.getAllIssueDomainTypeCountPieByTime", map);
	}

	@Override
	public List<Map<String, Object>> getAllIssueTypeStatisticPieFromHistory(
			String orgCode, Integer year, Integer month, Integer quarter,
			Long issueTypeDomainId) {
		Map map = createQueryMap(null, year, month, quarter);
		map.put("orgCode", orgCode);
		map.put("issueTypeDomainId", issueTypeDomainId);
		return getSqlMapClientTemplate()
				.queryForList(
						"issueanalysisNew.getIssueTypeCountPieByMonthAndYearAndIssueDomain",
						map);
	}

	@Override
	public List<Map<String, Object>> getAllIssueTypeStatisticPieFromRealTime(
			String orgCode, Date startDate, Date endDate, Long issueTypeDomainId) {
		Map map = new HashMap<String, Object>();
		map.put("orgCode", orgCode);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("issueTypeDomainId", issueTypeDomainId);
		return getSqlMapClientTemplate().queryForList(
				"issueanalysisNew.getAllIssueTypeCountPieByTime", map);
	}

	@Override
	public void createIssueAnalysisChartData() {
		Map map = new HashMap<String, Object>();
		map.put("year", CalendarUtil.getYear(new Date()));
		map.put("month", CalendarUtil.getMonth(new Date()));
		map.put("sysDate", new Date());
		map.put("beforeMonthLastDay", CalendarUtil.getBeforeMonthLastDay());
		map.put("nextMonthFirstDay", CalendarUtil.getNextMonthFirstDay());
		getSqlMapClientTemplate().insert(
				"issueanalysisNew.createIssueAnalysisChartData", map);
	}

	@Override
	public Integer getIssueTendencyCountFromHistory(String orgCode,
			Integer year, Integer month, Integer quarter) {
		Map map = createQueryMap(null, year, month, quarter);
		map.put("orgCode", orgCode);
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"issueanalysisNew.getIssueTendencyCountFromHistory", map);
		return null == count ? 0 : count;
	}

	private Map<String, Object> createQueryMap(Long orgId, Integer year,
			Integer month, Integer quarter) {
		Map map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("year", year);
		map.put("month", month);
		map.put("quarter", quarter);
		return map;
	}

}
