package com.tianque.eventSource.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.issue.vo.IssueViewObject;

public interface SearchCommandCenterDao {
	/** 查询指挥中心待办事件 */
	public PageInfo<IssueViewObject> searchCommandCenterIssueForPageBySearchIssueVoNew(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows, String sidx, String sord);

	public PageInfo<IssueViewObject> searchDoneIssues(SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows, String sidx, String sord);
}
