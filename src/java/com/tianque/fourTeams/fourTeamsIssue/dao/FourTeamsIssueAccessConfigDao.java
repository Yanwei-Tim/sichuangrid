package com.tianque.fourTeams.fourTeamsIssue.dao;

import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAccessConfig;

/**
 * 红黄牌设置
 */
public interface FourTeamsIssueAccessConfigDao {
	/**
	 * 添加红黄牌设置
	 * 
	 * @param FourTeamsIssueAccessConfig
	 * @return
	 */
	public FourTeamsIssueAccessConfig addIssueAccessConfig(FourTeamsIssueAccessConfig bean);

	/**
	 * 查询红黄牌
	 * 
	 * @param FourTeamsIssueAccessConfig
	 * @return
	 */
	public FourTeamsIssueAccessConfig getIssueAccessConfig();

	/**
	 * 更新红黄牌设置
	 * 
	 * @param FourTeamsIssueAccessConfig
	 * @return
	 */
	public FourTeamsIssueAccessConfig updateIssueAccessConfig(FourTeamsIssueAccessConfig bean);

}
