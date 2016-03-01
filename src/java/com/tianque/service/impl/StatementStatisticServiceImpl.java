package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.StatementStatisticDao;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.StatementStatistic;
import com.tianque.service.StatementStatisticService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("statementStatisticService")
public class StatementStatisticServiceImpl extends AbstractBaseService
		implements StatementStatisticService {
	@Autowired
	private StatementStatisticDao statementStatisticDao;
	@Autowired
	private PropertyDictService propertyDictService;

	// =======================================无奈的分割线=======================================
	@Override
	public PropertyDict getStatementTypeByDomainNameAndDisplayName(
			String domainName, String displayName) {
		return propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(domainName,
						displayName);
	}

	@Override
	public List<Integer> getMonthsByOrgId(Long orgId, Integer year) {
		return statementStatisticDao.getMonthsByOrgId(orgId, year);
	}

	@Override
	public PageInfo<StatementStatistic> findStatementStatisticsForPageByOrgId(
			Long orgId, Long statementTypeId, Integer year, Integer month,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		return statementStatisticDao.findStatementStatisticsForPageByOrgId(
				orgId, year, month, statementTypeId, pageNum, pageSize, sidx,
				sord);
	}

	@Override
	public PageInfo<StatementStatistic> findStatementStatisticsForPageByOrgInternalCode(
			String orgInternalCode, Long statementTypeId, Integer year,
			Integer month, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		return statementStatisticDao
				.findStatementStatisticsForPageByOrgInternalCode(
						orgInternalCode, year, month, statementTypeId, pageNum,
						pageSize, sidx, sord);
	}
}
