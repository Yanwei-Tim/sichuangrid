package com.tianque.fourTeams.fourTeamsIssue.service;

import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAccessConfig;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;

public interface FourTeamsIssueAccessConfigService {
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
	 * 根据事件处理类型 得到打分设置
	 * 
	 * @param issueOperate
	 * @return
	 */
	public Double getIssueScoresConfig(FourTeamsIssueOperate issueOperate);
}
