package com.tianque.mobile.leaderAnalysis;

public interface LeaderAnalysisAdapter {
	public String getGeneralSituation() throws Exception;

	public String getPopulationDatasDetails() throws Exception;

	/**
	 * 事件列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getIssueDatasList() throws Exception;

	/**
	 * 事件饼图
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getStatisticPie() throws Exception;

	/**
	 * 事件柱状图
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getStatisticColumn() throws Exception;

	/**
	 * 事件环比图
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getIssueSequentialChart() throws Exception;

	/**
	 * 事件总况列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getIssueTotalCaseList() throws Exception;

}
