package com.tianque.leaderAnalysis.service;

import java.util.List;
import java.util.Map;

import com.tianque.leaderAnalysis.domain.GeneralSituation;
import com.tianque.leaderAnalysis.domain.IssueAnalysisToMobile;
import com.tianque.leaderAnalysis.domain.IssueTotalCaseListData;

public interface LeaderAnalysisService {
	/** 领导研判分析总况查询 **/
	public Map<Integer, List<GeneralSituation>> getGeneralSituationDatas(
			Long orgId, boolean isGrid);

	/** 查看详细信息 **/
	public Map<String, Object> getPopulationDatasDetails(String tableName,
			Integer orgLevelDistance, Long orgId, int year, int month);

	/**
	 * 事件各个大类对应的列表
	 * 
	 * @param issueType
	 * @param viewType
	 * @param orgId
	 * @param year
	 * @param month
	 * @return
	 */
	public List<IssueAnalysisToMobile> getIssueDatasList(String issueType,
			Long orgId, int year, int month);

	/**
	 * 获取事件总况
	 * 
	 * @param orgId
	 * @param year
	 * @param month
	 * @return
	 */
	public List<IssueTotalCaseListData> getIssueTotalCaseListForMobile(
			Long orgId, int year, int month);
}
