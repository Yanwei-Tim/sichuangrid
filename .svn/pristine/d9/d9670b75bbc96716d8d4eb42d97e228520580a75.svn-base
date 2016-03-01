package com.tianque.plugin.analysisNew.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.domain.PropertyDict;
import com.tianque.plugin.analysisNew.domain.IssueAreaStatNew;
import com.tianque.plugin.analysisNew.domain.IssueTypeStat;

public interface IssueReportNewService {

	public List<IssueAreaStatNew> findIssueAreaStatsByYearAndMonthAndParentOrgId(
			Integer year, Integer month, Long parentOrgId, Integer queryType,
			PropertyDict reportDateType);

	public List<IssueTypeStat> findIssueLevelStatsByYearAndMonthAndParentOrgId(
			Integer year, Integer month, Long parentOrgId, Integer queryType,
			PropertyDict reportDateType);

	public void statIssueReport();

	// add by bing 2014年11月12日 下午6:01:44
	public void addIssueHandleStats(Map<String, Date> date, Long taskId);

	public void addIssueClassificationStats(Map<String, Date> date, Long orgId);

	public void addIssueStepStats(Map<String, Date> date, Long orgId);

	public List<Long> findOrgByCondition(int taskItemNum, List<Long> idModList,
			int fetchNum, int year, int month, String targetIssueTable);

	// add by bing 2014年11月12日 下午6:01:49
}
