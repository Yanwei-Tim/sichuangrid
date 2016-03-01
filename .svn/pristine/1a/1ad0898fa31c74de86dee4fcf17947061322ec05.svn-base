package com.tianque.eventSource.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.vo.IssueViewObject;

public interface CommandCenterService {

	public PageInfo<IssueViewObject> findCommandCenterIssueForPageByTargeOrgIdAndDealState(
			Long targeOrgId, Long dealState, Integer page, Integer rows, String sidx, String sord);

	public PageInfo<IssueViewObject> findMyDone(Long orgId, IssueNew issue, Integer page,
			Integer rows, String sidx, String sord);
}
