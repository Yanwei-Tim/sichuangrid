package com.tianque.issue.dao;

import java.util.Date;
import java.util.List;

/**
 * @author n-235
 * 
 */
public interface IssueExtractionDao {

	/**
	 * 根据日期查询符合要求的数据
	 * 
	 * @param date
	 * @return
	 */
	public List<Long> queryCompletedHistoryIssuesIds(Date date);

	/**
	 * 迁移事件主表数据
	 * 
	 * @param issueIds
	 * @return
	 */
	public Long migrationIssuesDatas(List<Long> issueIds);

	/**
	 * 迁移事件步骤
	 * 
	 * @param issueIds
	 * @return
	 */
	public Long migrationIssueStepsDatas(List<Long> issueIds);

	/**
	 * 迁移事件日志
	 * 
	 * @param issueIds
	 * @return
	 */
	public Long migrationIssueLogsDatas(List<Long> issueIds);

	/**
	 * 迁移事件步骤
	 * 
	 * @param issueIds
	 * @return
	 */
	public Long migrationIssueStepsGroupDatas(List<Long> issueIds);

	/**
	 * 迁移已办结表
	 * 
	 * @param issueIds
	 * @return
	 */
	public Long migrationCompletedissueDatas(List<Long> issueIds);

	/**
	 * 删除事件主表记录
	 * 
	 * @param issueIds
	 * @return
	 */
	public int deleteIssuesDatasByIdList(List<Long> issueIds);

	/**
	 * 删除事件步骤表记录
	 * 
	 * @param issueIds
	 * @return
	 */
	public int deleteIssueStepsDatasByIdList(List<Long> issueIds);

	/**
	 * 删除事件日志表记录
	 * 
	 * @param issueIds
	 * @return
	 */
	public int deleteIssueLogsDatasByIdList(List<Long> issueIds);

	/**
	 * 删除事件步骤表记录
	 * 
	 * @param issueIds
	 * @return
	 */
	public int deleteIssueStepsGroupDatasByIdList(List<Long> issueIds);

	/**
	 * 删除已完成的事件记录
	 * 
	 * @param issueIds
	 * @return
	 */
	public int deleteCompletedissueDatasByIdList(List<Long> issueIds);

	/**
	 * 已迁移事件打上标记
	 * 
	 * @param issueIds
	 * @return
	 */
	public int updateMarkCompletedissueDatas(List<Long> issueIds);
}
