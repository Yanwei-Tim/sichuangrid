package com.tianque.statAnalyse.issueManage.listManage.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.domain.PropertyDict;
import com.tianque.statAnalyse.issueManage.listManage.domain.IssueAreaStat;
import com.tianque.statAnalyse.issueManage.listManage.domain.IssueLevelStat;
import com.tianque.statAnalyse.issueManage.listManage.domain.IssueTypeStat;

public interface IssueReportService {

	public List<IssueAreaStat> findIssueAreaStatsByYearAndMonthAndParentOrgId(
			Integer year, Integer month, Long parentOrgId, Integer queryType,
			PropertyDict reportDateType);

	public List<IssueAreaStat> findIssueAreaStatsByYearAndMonthAndParentOrgIdNew(
			Integer year, Integer month, Long parentOrgId, Integer queryType,
			PropertyDict reportDateType);

	public List<IssueTypeStat> findIssueLevelStatsByYearAndMonthAndParentOrgId(
			Integer year, Integer month, Long parentOrgId, Integer queryType,
			PropertyDict reportDateType);

	public List<IssueLevelStat> findIssueLevelStatsByYearAndMonthAndParentOrgIdNew(
			Integer year, Integer month, Long parentOrgId, Integer queryType,
			PropertyDict reportDateType);

	public void statIssueReport();
	
	public void statIssueReport(int year,int month,String type);

	// add by bing 2014年11月12日 下午6:01:44
	public void addIssueHandleStats(Map<String, Date> date, Long taskId,String type);

	public void addIssueClassificationStats(Map<String, Date> date, Long orgId,String type);

	public void addIssueStepStats(Map<String, Date> date, Long orgId,String type);

	public List<Long> findOrgByCondition(int taskItemNum, List<Long> idModList,
			int fetchNum, int year, int month, String targetIssueTable);

	// add by bing 2014年11月12日 下午6:01:49

}
