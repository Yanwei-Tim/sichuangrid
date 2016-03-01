package com.tianque.eventSource.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.vo.IssueViewObject;

public interface CommandCenterDao {
	public PageInfo<IssueViewObject> findMyDone(Long orgId, IssueNew issue, Integer page,
			Integer rows, String sidx, String sord);

	/** 查找指挥中心待办事件列表 */
	public PageInfo<IssueViewObject> findCommandCenterIssueForPageByTargeOrgIdAndDealState(
			Long targeOrgId, Long dealState, Integer page, Integer rows, String sidx, String sord,
			List<PropertyDict> sourcekind);

	/** 得到指挥中心待办事件的总条数 */
	public Integer getCommandCenterCount(Long orgId, List<PropertyDict> sourcekind);

	/** 得到事件类型 */
	public List<String> getIssueType(Long issueId);
}
