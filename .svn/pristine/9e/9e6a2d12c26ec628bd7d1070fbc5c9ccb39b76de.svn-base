package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.StatementStatistic;

public interface StatementStatisticService {

	public PropertyDict getStatementTypeByDomainNameAndDisplayName(
			String domainName, String displayName);

	public List<Integer> getMonthsByOrgId(Long orgId, Integer year);

	public PageInfo<StatementStatistic> findStatementStatisticsForPageByOrgId(
			Long orgId, Long statementTypeId, Integer year, Integer month,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public PageInfo<StatementStatistic> findStatementStatisticsForPageByOrgInternalCode(
			String orgInternalCode, Long statementTypeId, Integer year,
			Integer month, Integer pageNum, Integer pageSize, String sidx,
			String sord);
}
