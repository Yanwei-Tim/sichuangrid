package com.tianque.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.StatementStatisticDao;
import com.tianque.domain.StatementStatistic;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.domain.IssueLogNew;

@SuppressWarnings("unchecked")
@Repository("statementStatisticDao")
public class StatementStatisticDaoImpl extends AbstractBaseDao implements
		StatementStatisticDao {
	// 方法没有显式调用
	// @Override
	// public List<Long> findOrganizationIds() {
	// return getSqlMapClientTemplate().queryForList(
	// "statementStatistic.findOrganizationIds");
	// }

	@Override
	public StatementStatistic getIssueLogStartTime() {
		return (StatementStatistic) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getIssueLogStartTime");
	}

	// 方法没有显式调用
	/*
	 * @Override public PropertyDict getStatementTypeByDomainNameAndDisplayName(
	 * String domainName, String displayName) { Map<String, Object> map = new
	 * HashMap<String, Object>(); map.put("domainName", domainName);
	 * map.put("displayName", displayName); return (PropertyDict)
	 * getSqlMapClientTemplate() .queryForObject(
	 * "statementStatistic.getStatementTypeByDomainNameAndDisplayName", map); }
	 */

	@Override
	public StatementStatistic getSimpleStatementStatisticById(Long id) {
		return (StatementStatistic) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getSimpleStatementStatisticById", id);
	}

	@Override
	public StatementStatistic addStatementStatistic(
			StatementStatistic statementStatistic) {
		if (!validate(statementStatistic))
			throw new BusinessValidationException();
		Long id = (Long) getSqlMapClientTemplate().insert(
				"statementStatistic.addStatementStatistic", statementStatistic);
		return getSimpleStatementStatisticById(id);
	}

	@Override
	public List<Integer> getYearsByOrgId(Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		return getSqlMapClientTemplate().queryForList(
				"statementStatistic.getYearsByOrgId", map);
	}

	@Override
	public List<Integer> getMonthsByOrgId(Long orgId, Integer year) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("year", year);
		return getSqlMapClientTemplate().queryForList(
				"statementStatistic.getMonthsByOrgId", map);
	}

	@Override
	public PageInfo<StatementStatistic> findStatementStatisticsForPageByOrgId(
			Long orgId, Integer year, Integer month, Long statementTypeId,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("year", year);
		map.put("month", month);
		map.put("statementTypeId", statementTypeId);
		map.put("sortField", sidx);
		map.put("order", sord);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.countStatementStatistics", map);

		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		List<StatementStatistic> list = getSqlMapClientTemplate().queryForList(
				"statementStatistic.findStatementStatistics", map,
				(pageNum - 1) * pageSize, pageSize);

		PageInfo<StatementStatistic> pageInfo = new PageInfo<StatementStatistic>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public PageInfo<StatementStatistic> findStatementStatisticsForPageByOrgInternalCode(
			String orgInternalCode, Integer year, Integer month,
			Long statementTypeId, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("year", year);
		map.put("month", month);
		map.put("statementTypeId", statementTypeId);
		map.put("sortField", sidx);
		map.put("order", sord);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.countStatementStatistics", map);

		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		List<StatementStatistic> list = getSqlMapClientTemplate().queryForList(
				"statementStatistic.findStatementStatistics", map,
				(pageNum - 1) * pageSize, pageSize);

		PageInfo<StatementStatistic> pageInfo = new PageInfo<StatementStatistic>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private boolean validate(StatementStatistic statementStatistic) {
		if (statementStatistic == null)
			return false;
		if (statementStatistic.getOrganization() == null
				|| statementStatistic.getOrganization().getId() == null)
			return false;
		if (statementStatistic.getOrgInternalCode() == null
				|| statementStatistic.getOrgInternalCode().equals(""))
			return false;
		if (statementStatistic.getStatementType() == null
				|| statementStatistic.getStatementType().getId() == null)
			return false;
		if (statementStatistic.getStatementTotal() == null)
			return false;
		if (statementStatistic.getCurrentPeriodRegister() == null)
			return false;
		if (statementStatistic.getRegisterTotal() == null)
			return false;
		if (statementStatistic.getOverdueNotAccepted() == null)
			return false;
		if (statementStatistic.getNormalNotAccepted() == null)
			return false;
		if (statementStatistic.getSevenDaysInDeal() == null)
			return false;
		if (statementStatistic.getOvertakeSevenDaysInDeal() == null)
			return false;
		if (statementStatistic.getOnScheduleHandle() == null)
			return false;
		if (statementStatistic.getOverdueHandle() == null)
			return false;
		if (statementStatistic.getTransferTotal() == null)
			return false;
		if (statementStatistic.getEndTotal() == null)
			return false;
		if (statementStatistic.getOnScheduleRateOfEnd() == null
				|| statementStatistic.getOnScheduleRateOfEnd().equals(""))
			return false;
		if (statementStatistic.getRateOfEnding() == null
				|| statementStatistic.getRateOfEnding().equals(""))
			return false;
		if (statementStatistic.getYear() == null)
			return false;
		return true;
	}

	// ======================================共用分割线======================================
	// 累计办件
	@Override
	public Long getStatementTotalByOrgId(Long orgId, Date startTime,
			Date endTime, List<Long> issueDealStates) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("issueDealStates", issueDealStates);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getStatementTotal", map);
	}

	@Override
	public Long getStatementTotalByOrgInternalCode(String orgInternalCode,
			Date startTime, Date endTime, List<Long> issueDealStates) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("issueDealStates", issueDealStates);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getStatementTotal", map);
	}

	// 当期立案(累计立案)
	@Override
	public Long getCurrentPeriodRegisterOrRegisterTotalByOrgId(Long orgId,
			Date startTime, Date endTime, List<Long> issueDealTypes) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("issueDealTypes", issueDealTypes);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getCurrentPeriodRegisterOrRegisterTotal",
				map);
	}

	@Override
	public Long getCurrentPeriodRegisterOrRegisterTotalByOrgInternalCode(
			String orgInternalCode, Date startTime, Date endTime,
			List<Long> issueDealTypes) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("issueDealTypes", issueDealTypes);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getCurrentPeriodRegisterOrRegisterTotal",
				map);
	}

	// 累计移交
	@Override
	public Long getTransferTotalByOrgId(Long orgId, Date startTime,
			Date endTime, List<Long> issueDealTypes) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("issueDealTypes", issueDealTypes);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getTransferTotal", map);
	}

	@Override
	public Long getTransferTotalByOrgInternalCode(String orgInternalCode,
			Date startTime, Date endTime, List<Long> issueDealTypes) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("issueDealTypes", issueDealTypes);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getTransferTotal", map);
	}

	// 累计结案数
	@Override
	public Long getEndTotalByOrgId(Long orgId, Date startTime, Date endTime,
			Long dealState) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("dealState", dealState);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getEndTotal", map);
	}

	@Override
	public Long getEndTotalByOrgInternalCode(String orgInternalCode,
			Date startTime, Date endTime, Long dealState) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("dealState", dealState);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getEndTotal", map);
	}

	// ========================================年报分割线========================================
	// 当期立案
	public Long getCurrentPeriodRegisterCountByOrgId(Long orgId,
			Long statementTypeId) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("orgId", orgId);
		map.put("statementTypeId", statementTypeId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getCurrentPeriodRegisterCount", map);
	}

	public Long getCurrentPeriodRegisterCountByOrgInternalCode(
			String orgInternalCode, Long statementTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("statementTypeId", statementTypeId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getCurrentPeriodRegisterCount", map);
	}

	// 逾期未受理
	public Long getOverdueNotAcceptedCountByOrgId(Long orgId,
			Long statementTypeId) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("orgId", orgId);
		map.put("statementTypeId", statementTypeId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getOverdueNotAcceptedCount", map);
	}

	public Long getOverdueNotAcceptedCountByOrgInternalCode(
			String orgInternalCode, Long statementTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("statementTypeId", statementTypeId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getOverdueNotAcceptedCount", map);
	}

	// 正常未受理
	public Long getNormalNotAcceptedCountByOrgId(Long orgId,
			Long statementTypeId) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("orgId", orgId);
		map.put("statementTypeId", statementTypeId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getNormalNotAcceptedCount", map);
	}

	public Long getNormalNotAcceptedcCountByOrgInternalCode(
			String orgInternalCode, Long statementTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("statementTypeId", statementTypeId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getNormalNotAcceptedCount", map);
	}

	// 七天在办
	public Long getSevenDaysInDealCountByOrgId(Long orgId, Long statementTypeId) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("orgId", orgId);
		map.put("statementTypeId", statementTypeId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getSevenDaysInDealCount", map);
	}

	public Long getSevenDaysInDealCountByOrgInternalCode(
			String orgInternalCode, Long statementTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("statementTypeId", statementTypeId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getSevenDaysInDealCount", map);
	}

	// 超七天在办
	public Long getOvertakeSevenDaysInDealCountByOrgId(Long orgId,
			Long statementTypeId) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("orgId", orgId);
		map.put("statementTypeId", statementTypeId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getOvertakeSevenDaysInDealCount", map);
	}

	public Long getOvertakeSevenDaysInDealCountByOrgInternalCode(
			String orgInternalCode, Long statementTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("statementTypeId", statementTypeId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getOvertakeSevenDaysInDealCount", map);
	}

	// 按期办结
	public Long getOnScheduleHandleCountByOrgId(Long orgId, Long statementTypeId) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("orgId", orgId);
		map.put("statementTypeId", statementTypeId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getOnScheduleHandleCount", map);
	}

	public Long getOnScheduleHandleCountByOrgInternalCode(
			String orgInternalCode, Long statementTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("statementTypeId", statementTypeId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getOnScheduleHandleCount", map);
	}

	// 超期办结
	public Long getOverdueHandleCountByOrgId(Long orgId, Long statementTypeId) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("orgId", orgId);
		map.put("statementTypeId", statementTypeId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getOverdueHandleCount", map);
	}

	public Long getOverdueHandleCountByOrgInternalCode(String orgInternalCode,
			Long statementTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("statementTypeId", statementTypeId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"statementStatistic.getOverdueHandleCount", map);
	}

	// ========================================月报分割线========================================

	// 按期办结(超期办结)
	@Override
	public List<IssueLogNew> getOnScheduleHandleOrOverdueHandleIssueIdByOrgId(
			Long orgId, Date startTime, Date endTime, List<Long> issueDealStates) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("issueDealStates", issueDealStates);
		return getSqlMapClientTemplate().queryForList(
				"statementStatistic.getOnScheduleHandleOrOverdueHandle", map);
	}

	@Override
	public List<IssueLogNew> getOnScheduleHandleOrOverdueHandleIssueIdByOrgInternalCode(
			String orgInternalCode, Date startTime, Date endTime,
			List<Long> issueDealStates) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("issueDealStates", issueDealStates);
		return getSqlMapClientTemplate().queryForList(
				"statementStatistic.getOnScheduleHandleOrOverdueHandle", map);
	}

	@Override
	public Integer countOnScheduleHandleOrOverdueHandleByIssueLogId(
			Long issueLogId, Date startTime, Date endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueLogId", issueLogId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"statementStatistic.countOnScheduleHandleOrOverdueHandleByIssueLogId",
						map);
	}

	// 七天在办(超七天在办)
	@Override
	public List<IssueLogNew> getSevenDaysInDealOrOvertakeSevenDaysInDealByOrgId(
			Long orgId, Date startTime, Date endTime, Long dealState) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("dealState", dealState);
		return getSqlMapClientTemplate()
				.queryForList(
						"statementStatistic.getSevenDaysInDealOrOvertakeSevenDaysInDeal",
						map);
	}

	@Override
	public List<IssueLogNew> getSevenDaysInDealOrOvertakeSevenDaysInDealByOrgInternalCode(
			String orgInternalCode, Date startTime, Date endTime, Long dealState) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("dealState", dealState);
		return getSqlMapClientTemplate()
				.queryForList(
						"statementStatistic.getSevenDaysInDealOrOvertakeSevenDaysInDeal",
						map);
	}

}