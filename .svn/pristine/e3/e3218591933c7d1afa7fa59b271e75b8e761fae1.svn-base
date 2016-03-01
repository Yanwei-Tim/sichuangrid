package com.tianque.statAnalyse.issueManage.listManage.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.dao.impl.BaseDaoImpl;
import com.tianque.statAnalyse.issueManage.listManage.constant.QueryType;
import com.tianque.statAnalyse.issueManage.listManage.dao.IssueReportStatDao;
import com.tianque.statAnalyse.issueManage.listManage.domain.IssueAreaStat;

@Repository("issueReportStatDao")
public class IssueReportStatDaoImpl extends BaseDaoImpl<IssueAreaStat>
		implements IssueReportStatDao {

	public IssueReportStatDaoImpl() {
		super.prefix = "issueReportStat";
	}

	@Override
	public void addIssueHandleStats(List<IssueAreaStat> issueAreaStats) {
		batchInsertDatas(issueAreaStats, "issueReportStat.addIssueHandleStat");
	}

	@Override
	public void addIssueClassificationStats(List<IssueAreaStat> issueAreaStats) {
		batchInsertDatas(issueAreaStats,
				"issueReportStat.addIssueClassificationStat");
	}

	@Override
	public void addIssueStepStats(List<IssueAreaStat> issueAreaStats) {
		batchInsertDatas(issueAreaStats, "issueReportStat.addIssueStepStat");
	}

	@Override
	public IssueAreaStat getHistoryIssueAreaStatsByYearAndMonthAndOrgCode(
			Integer year, Integer month, String orgInternalCode,
			Integer queryType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("orgInternalCode", orgInternalCode);
		return get(map, QueryType.STATMENT.get(queryType));
	}

}
