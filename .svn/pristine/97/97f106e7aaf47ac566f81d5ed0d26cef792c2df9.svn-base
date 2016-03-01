package com.tianque.issueLeaderViewCache.dao;

import java.util.List;

import com.tianque.issue.domain.CompletedIssue;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.issueLeaderViewCache.domain.IssueCacheVO;

public interface IssueLeaderViewCacheDao {

	public List<String> findPageInfoByCacheKey(String cacheKey, Integer page,
			Integer rows);

	public Integer getCountByCacheKey(String cacheKey);

	public void deleteByCacheKey(String cacheKey);

	public void addIssueCacheForBatch(List<IssueCacheVO> cacheVOList);

	public void addIssueCache(IssueCacheVO issueCacheVO);

	public Integer countJurisdictionsIssue(String orgLevel, String orgCode,
			String issueViewType);

	public List<IssueViewObject> findJurisdictionsIssue(String orgLevel,
			String orgCode, String issueViewType);

	public List<CompletedIssue> findGradeDelayAndCompletedIssueByPage(
			String orgLevel, String orgCode, String issueViewType);

}
