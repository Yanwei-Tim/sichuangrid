package com.tianque.issue.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.issue.domain.IssueApplyDelay;

public interface IssueApplyDelayDao {

	IssueApplyDelay saveApplyDelay(IssueApplyDelay issueApplyDelay);

	IssueApplyDelay getApplyDelayById(Long id);

	PageInfo<IssueApplyDelay> findIssueDelayList(Long issueStepId,
			Integer page, Integer rows, String sidx, String sord);

	IssueApplyDelay getIssueApplyDelayByIssueStepIdAndDelayState(
			Long issueStepId);

	IssueApplyDelay auditDelay(IssueApplyDelay issueApplyDelay);

}
