package com.tianque.fourTeams.fourTeamsIssue.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueApplyDelay;

/**
 * @ClassName: IssueApplyDelayService
 * @Description: 申请延期服务接口
 * @author wangxiaohu wsmalltiger@163.com
 * @date Nov 25, 2013 3:58:51 PM
 */
public interface FourTeamsIssueApplyDelayService {

	FourTeamsIssueApplyDelay applyDelay(FourTeamsIssueApplyDelay issueApplyDelay);

	PageInfo<FourTeamsIssueApplyDelay> findIssueDelayList(Long issueStepId,
			Integer page, Integer rows, String sidx, String sord);

	FourTeamsIssueApplyDelay getIssueApplyDelayByIssueStepIdAndDelayState(
			Long issueStepId);

	FourTeamsIssueApplyDelay auditDelay(FourTeamsIssueApplyDelay issueApplyDelay);

	int getJurisdictionsAuditDelayCount(Long orgId);

}
