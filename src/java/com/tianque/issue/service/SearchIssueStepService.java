package com.tianque.issue.service;

import java.util.List;

import com.tianque.issue.domain.IssueStep;

public interface SearchIssueStepService {

	List<IssueStep> searchIssueStepsByIssueId(Long issueId);

	List<IssueStep> searchAllIssueStepsByStepId(Long stepId);

	/**
	 * 
	 * @Title: findIssueStepByIssueId
	 * @Description: TODO为手机端新增一个查询事件步骤方法
	 * @param @param issueId
	 * @param @return 设定文件
	 * @return IssueStep 返回类型
	 * @author wanggz
	 * @date 2014-6-13 下午06:29:36
	 */
	public IssueStep findIssueStepByIssueId(Long issueId);
}
