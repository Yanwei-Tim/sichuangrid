package com.tianque.plugin.analyzing.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.domain.IssueDealStat;
import com.tianque.domain.IssueMonthEndDealingHistory;

@Repository("issueStatDao")
@SuppressWarnings("unchecked")
public class IssueStatDaoImpl extends AbstractBaseDao implements IssueStatDao {

	@Override
	public List<IssueDealStat> findIssueDealStatsByOrgInternalCodeMonthStartDate(
			String orgInternalCode, Date statStart) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("statStartTime", statStart);
		return getSqlMapClientTemplate().queryForList(
				"issueStat.findIssueDealStatsByOrgInternalCodeMonthStartDate", map);
	}

	private IssueDealStat getIssueDealStatById(Long id) {
		return (IssueDealStat) getSqlMapClientTemplate().queryForObject(
				"issueStat.getIssueDealStatById", id);
	}

	@Override
	public void deleteIssueDealStatsByOrgInternalCodeMonthStartDate(String orgInternalCode,
			Date monthStart) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("statStartTime", monthStart);
		getSqlMapClientTemplate().delete(
				"issueStat.deleteIssueDealStatsByOrgInternalCodeMonthStartDate", map);
	}

	@Override
	public IssueDealStat sumIssueDealStatsByOrgInternalCodeAndStatDateAndOrgType(
			String orgInternalCode, Long orgTypeid, Date monthStart) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("statStartTime", monthStart);
		map.put("orgTypeid", orgTypeid);
		return (IssueDealStat) getSqlMapClientTemplate().queryForObject(
				"issueStat.sumIssueDealStatsByOrgInternalCodeAndStatDateAndOrgType", map);
	}

	@Override
	public IssueDealStat getIssueDealStatsByOrgIdAndStatDate(Long orgId, Date monthStart) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("statStartTime", monthStart);
		return (IssueDealStat) getSqlMapClientTemplate().queryForObject(
				"issueStat.getIssueDealStatsByOrgIdAndStatDate", map);
	}

	@Override
	public IssueMonthEndDealingHistory addMonthDealingLogRecord(IssueMonthEndDealingHistory record) {
		Long id = (Long) getSqlMapClientTemplate().insert("issueStat.addMonthDealingLogRecord",
				record);
		return getMonthDealingLogRecordById(id);
	}

	private IssueMonthEndDealingHistory getMonthDealingLogRecordById(Long id) {
		return (IssueMonthEndDealingHistory) getSqlMapClientTemplate().queryForObject(
				"issueStat.getMonthDealingLogRecordById", id);
	}

	@Override
	public List<IssueMonthEndDealingHistory> findMonthDealingLogRecordsByOrgInternalCodeMonthStartDate(
			String orgInternalCode, Date statStart) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("statStartTime", statStart);
		return getSqlMapClientTemplate().queryForList(
				"issueStat.findMonthDealingLogRecordsByOrgInternalCodeMonthStartDate", map);
	}

	@Override
	public void addIssueDealStats(final List<IssueDealStat> stats) {
		batchInsertDate(stats, "issueStat.addIssueDealStat");
	}

	@Override
	public List<IssueDealStat> findIssueDealStatsByOrgId(Long id) {
		return getSqlMapClientTemplate().queryForList("issueStat.findIssueDealStatsByOrgId", id);
	}

	@Override
	public void deleteIssueDealStatsByOrgId(Long orgId) {
		getSqlMapClientTemplate().delete("issueStat.deleteIssueDealStatsByOrgId", orgId);
	}

}
