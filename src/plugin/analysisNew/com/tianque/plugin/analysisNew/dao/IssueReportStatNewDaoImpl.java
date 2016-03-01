package com.tianque.plugin.analysisNew.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.dao.impl.BaseDaoImpl;
import com.tianque.plugin.analysisNew.domain.IssueAreaStatNew;
import com.tianque.plugin.analysisNew.util.QueryType;

@Repository("issueReportStatNewDao")
public class IssueReportStatNewDaoImpl extends BaseDaoImpl<IssueAreaStatNew>
		implements IssueReportStatNewDao {

	public IssueReportStatNewDaoImpl() {
		super.prefix = "issueReportStat";
	}

	@Override
	public void addIssueHandleStats(List<IssueAreaStatNew> issueAreaStats) {
		batchInsertDatas(issueAreaStats,
				"issueReportStatNew.addIssueHandleStat");
	}

	@Override
	public void addIssueClassificationStats(
			List<IssueAreaStatNew> issueAreaStats) {
		batchInsertDatas(issueAreaStats,
				"issueReportStatNew.addIssueClassificationStat");
	}

	@Override
	public void addIssueStepStats(List<IssueAreaStatNew> issueAreaStats) {
		batchInsertDatas(issueAreaStats, "issueReportStatNew.addIssueStepStat");
	}

	@Override
	public IssueAreaStatNew getHistoryIssueAreaStatsByYearAndMonthAndOrgCode(
			Integer year, Integer month, String orgInternalCode,
			Integer queryType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("orgInternalCode", orgInternalCode);
		return get(map, QueryType.STATMENT.get(queryType));
	}

}
