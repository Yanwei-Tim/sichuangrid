package com.tianque.issueLeaderViewCache.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.issue.domain.CompletedIssue;
import com.tianque.issue.vo.IssueViewObject;

public interface IssueLeaderViewCacheService {

	PageInfo<IssueViewObject> findPageInfoByCacheKey(String cacheKey,
			Integer page, Integer rows);

	PageInfo<CompletedIssue> findPageInfoByCacheKeyForCompleted(
			String cacheKey, Integer page, Integer rows);

	public Integer getCountByCacheKey(String cacheKey);

	/**
	 * job调用
	 */
	void handleIssueCache();

	Integer countJurisdictionsIssue(String orgLevel, String orgCode,
			String issueViewType);

	List<IssueViewObject> findJurisdictionsIssue(String orgLevel,
			String orgCode, String issueViewType);

	List<CompletedIssue> findGradeDelayAndCompletedIssueByPage(String orgLevel,
			String orgCode, String issueViewType);
}
