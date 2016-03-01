package com.tianque.issue.service;

import java.util.List;

/**
 * 事件已办结部分按时间分离
 * 
 */
public interface IssueExtractionService {
	/**
	 * 执行事件的数据迁移
	 */
	public void exectueExtractionIssue();

	/**
	 * @return 查询已过期的已办结事件的id
	 */
	public List<Long> queryCompletedHistoryIssuesIds();

	/**
	 * 迁移符合要求的数据
	 * 
	 * @param issueIds
	 */
	public void migrationDatas(List<Long> issueIds);

	/**
	 * 删除符合要求的数据
	 * 
	 * @param issueIds
	 */
	public void deleteDatasByIdList(List<Long> issueIds);

}
