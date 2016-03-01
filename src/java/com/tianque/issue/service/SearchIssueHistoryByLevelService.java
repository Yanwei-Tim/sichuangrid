package com.tianque.issue.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.issue.vo.IssueViewObject;

public interface SearchIssueHistoryByLevelService {
	public PageInfo<IssueViewObject> findJurisdictionsDoneHistory(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows);

	public PageInfo<IssueViewObject> findJurisdictionsSubmitHistory(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows);

	public PageInfo<IssueViewObject> findJurisdictionsAssginHistory(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows);

	public PageInfo<IssueViewObject> findJurisdictionsSkipIssueHistory(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows);

}
