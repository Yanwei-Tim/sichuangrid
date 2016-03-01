package com.tianque.issue.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.issue.domain.IssueApplyDelay;

/**
 * @ClassName: IssueApplyDelayService
 * @Description: 申请延期服务接口
 * @author wangxiaohu wsmalltiger@163.com
 * @date Nov 25, 2013 3:58:51 PM
 */
public interface IssueApplyDelayService {

	IssueApplyDelay applyDelay(IssueApplyDelay issueApplyDelay);

	PageInfo<IssueApplyDelay> findIssueDelayList(Long issueStepId,
			Integer page, Integer rows, String sidx, String sord);

	IssueApplyDelay getIssueApplyDelayByIssueStepIdAndDelayState(
			Long issueStepId);

	IssueApplyDelay auditDelay(IssueApplyDelay issueApplyDelay);

	/*** 根据层级获取下辖待审核事件 */
	int getJurisdictionsAuditDelayCount(Long orgLevel, Long orgId,
			Long functionalOrgType);

	/** 获取当前层级的待审核事件 */
	int getJurisdictionsAuditDelayCount(Long orgId);

	/** 获取当前层级待办并且已经督办的事件数 */
	int getOverseerIssueCountForMobile(Long orgLevel, Long orgId,
			Long functionalOrgType);

	/** 获取当前层级待办的事件数 */
	int getJurisdictionsNeedDoCountForMobile(Long orgLevel, Long orgId,
			Long functionalOrgType);

}
