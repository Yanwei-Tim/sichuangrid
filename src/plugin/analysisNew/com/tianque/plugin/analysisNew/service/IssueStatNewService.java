package com.tianque.plugin.analysisNew.service;

import java.util.List;

import com.tianque.domain.IssueDealStat;

public interface IssueStatNewService {
	List<IssueDealStat> findAdminstrateOrgIssueDealStatsByMonth(Long rootOrgId,
			int year, int month);

	List<IssueDealStat> findFunctionOrgIssueDealStatsByParentIdMonth(
			Long rootOrgId, int year, int month);

	// List<IssueDealStat> buildAdminstrateOrgIssueDealStatsByMonth(Long
	// rootOrgId,int year,int
	// month);
	// List<IssueDealStat> buildFunctionOrgIssueDealStatsByMonth(Long
	// rootOrgId,int year,int month);
	List<IssueDealStat> buildIssueDealStatsByMonth(Long rootOrgId, int year,
			int month);

	List<IssueDealStat> findRealTimeAdminstrateOrgIssueDealStats(Long id);

	List<IssueDealStat> findRealTimeFunctionOrgIssueDealStats(Long orgId);

}
