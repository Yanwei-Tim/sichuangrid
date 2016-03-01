package com.tianque.fourTeams.fourTeamsIssue.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueApplyDelay;

public interface FourTeamsIssueApplyDelayDao {

	FourTeamsIssueApplyDelay saveApplyDelay(FourTeamsIssueApplyDelay issueApplyDelay);

	FourTeamsIssueApplyDelay getApplyDelayById(Long id);

	PageInfo<FourTeamsIssueApplyDelay> findIssueDelayList(Long issueStepId,
			Integer page, Integer rows, String sidx, String sord);

	FourTeamsIssueApplyDelay getIssueApplyDelayByIssueStepIdAndDelayState(
			Long issueStepId);

	FourTeamsIssueApplyDelay auditDelay(FourTeamsIssueApplyDelay issueApplyDelay);

}
