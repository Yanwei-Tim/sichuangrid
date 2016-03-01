package com.tianque.dao;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.StatementStatistic;
import com.tianque.issue.domain.IssueLogNew;

public interface StatementStatisticDao {
	// 方法没有显式调用
	// public List<Long> findOrganizationIds();

	public StatementStatistic getIssueLogStartTime();

	// 方法没有显式调用
	// public PropertyDict getStatementTypeByDomainNameAndDisplayName(String
	// domainName,
	// String displayName);

	public StatementStatistic getSimpleStatementStatisticById(Long id);

	public StatementStatistic addStatementStatistic(
			StatementStatistic statementStatistic);

	public List<Integer> getYearsByOrgId(Long orgId);

	public List<Integer> getMonthsByOrgId(Long orgId, Integer year);

	public PageInfo<StatementStatistic> findStatementStatisticsForPageByOrgId(
			Long orgId, Integer year, Integer month, Long statementTypeId,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public PageInfo<StatementStatistic> findStatementStatisticsForPageByOrgInternalCode(
			String orgInternalCode, Integer year, Integer month,
			Long statementTypeId, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	// ======================================共用分割线======================================
	// 累计办件
	public Long getStatementTotalByOrgId(Long orgId, Date startTime,
			Date endTime, List<Long> issueDealStates);

	public Long getStatementTotalByOrgInternalCode(String orgInternalCode,
			Date startTime, Date endTime, List<Long> issueDealStates);

	// (当期立案)累计立案
	public Long getCurrentPeriodRegisterOrRegisterTotalByOrgId(Long orgId,
			Date startTime, Date endTime, List<Long> issueDealTypes);

	public Long getCurrentPeriodRegisterOrRegisterTotalByOrgInternalCode(
			String orgInternalCode, Date startTime, Date endTime,
			List<Long> issueDealTypes);

	// 累计移交
	public Long getTransferTotalByOrgId(Long orgId, Date startTime,
			Date endTime, List<Long> issueDealTypes);

	public Long getTransferTotalByOrgInternalCode(String orgInternalCode,
			Date startTime, Date endTime, List<Long> issueDealTypes);

	// 累计结案数
	public Long getEndTotalByOrgId(Long orgId, Date startTime, Date endTime,
			Long dealState);

	public Long getEndTotalByOrgInternalCode(String orgInternalCode,
			Date startTime, Date endTime, Long dealState);

	// ========================================年报分割线========================================
	// 当期立案
	public Long getCurrentPeriodRegisterCountByOrgId(Long orgId,
			Long statementTypeId);

	public Long getCurrentPeriodRegisterCountByOrgInternalCode(
			String orgInternalCode, Long statementTypeId);

	// 逾期未受理
	public Long getOverdueNotAcceptedCountByOrgId(Long orgId,
			Long statementTypeId);

	// 正常未受理
	public Long getNormalNotAcceptedCountByOrgId(Long orgId,
			Long statementTypeId);

	public Long getNormalNotAcceptedcCountByOrgInternalCode(
			String orgInternalCode, Long statementTypeId);

	// 七天在办
	public Long getSevenDaysInDealCountByOrgId(Long orgId, Long statementTypeId);

	public Long getSevenDaysInDealCountByOrgInternalCode(
			String orgInternalCode, Long statementTypeId);

	// 超七天在办
	public Long getOvertakeSevenDaysInDealCountByOrgId(Long orgId,
			Long statementTypeId);

	public Long getOvertakeSevenDaysInDealCountByOrgInternalCode(
			String orgInternalCode, Long statementTypeId);

	// 按期办结
	public Long getOnScheduleHandleCountByOrgId(Long orgId, Long statementTypeId);

	public Long getOnScheduleHandleCountByOrgInternalCode(
			String orgInternalCode, Long statementTypeId);

	// 超期办结
	public Long getOverdueHandleCountByOrgId(Long orgId, Long statementTypeId);

	public Long getOverdueHandleCountByOrgInternalCode(String orgInternalCode,
			Long statementTypeId);

	// ========================================月报分割线========================================
	// 按期办结(超期办结)
	public List<IssueLogNew> getOnScheduleHandleOrOverdueHandleIssueIdByOrgId(
			Long orgId, Date startTime, Date endTime, List<Long> issueDealStates);

	public List<IssueLogNew> getOnScheduleHandleOrOverdueHandleIssueIdByOrgInternalCode(
			String orgInternalCode, Date startTime, Date endTime,
			List<Long> issueDealStates);

	public Integer countOnScheduleHandleOrOverdueHandleByIssueLogId(
			Long issueLogId, Date startTime, Date endTime);

	// 七天在办(超七天在办)
	public List<IssueLogNew> getSevenDaysInDealOrOvertakeSevenDaysInDealByOrgId(
			Long orgId, Date startTime, Date endTime, Long dealState);

	public List<IssueLogNew> getSevenDaysInDealOrOvertakeSevenDaysInDealByOrgInternalCode(
			String orgInternalCode, Date startTime, Date endTime, Long dealState);

}
