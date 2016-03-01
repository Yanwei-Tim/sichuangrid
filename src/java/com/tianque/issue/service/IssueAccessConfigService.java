package com.tianque.issue.service;

import com.tianque.issue.domain.IssueAccessConfig;
import com.tianque.issue.state.IssueOperate;

public interface IssueAccessConfigService {
	/**
	 * 添加红黄牌设置
	 * 
	 * @param IssueAccessConfig
	 * @return
	 */
	public IssueAccessConfig addIssueAccessConfig(IssueAccessConfig bean);

	/**
	 * 查询红黄牌
	 * 
	 * @param IssueAccessConfig
	 * @return
	 */
	public IssueAccessConfig getIssueAccessConfig();

	/**
	 * 根据事件处理类型 得到打分设置
	 * 
	 * @param issueOperate
	 * @return
	 */
	public Double getIssueScoresConfig(IssueOperate issueOperate);
}
