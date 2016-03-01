package com.tianque.issueLeaderViewCache.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.utils.CustomDateUtil;
import com.tianque.dao.AbstractBaseDao;
import com.tianque.issue.constants.IssueViewType;
import com.tianque.issue.domain.CompletedIssue;
import com.tianque.issue.state.IssueSourceState;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.issueLeaderViewCache.dao.IssueLeaderViewCacheDao;
import com.tianque.issueLeaderViewCache.domain.IssueCacheVO;

@Repository("issueLeaderViewCacheDao")
public class IssueLeaderViewCacheDaoImpl extends AbstractBaseDao implements
		IssueLeaderViewCacheDao {
	private static int cachePage = 200;

	@Override
	public List<String> findPageInfoByCacheKey(String cacheKey, Integer page,
			Integer rows) {
		return getSqlMapClientTemplate().queryForList(
				"issueLeaderViewCache.findPageInfoByCacheKey", cacheKey,
				(page - 1) * rows, rows);
	}

	@Override
	public Integer getCountByCacheKey(String cacheKey) {
		List<Integer> counts = getSqlMapClientTemplate().queryForList(
				"issueLeaderViewCache.getCountByCacheKey", cacheKey);
		return (counts == null || counts.size() == 0) ? 0 : counts.get(0);
	}

	@Override
	public void deleteByCacheKey(String cacheKey) {
		getSqlMapClientTemplate().delete(
				"issueLeaderViewCache.deleteByCacheKey", cacheKey);
	}

	@Override
	public void addIssueCacheForBatch(List<IssueCacheVO> cacheVOList) {
		batchDeleteData(cacheVOList, "issueLeaderViewCache.addIssueCache");
	}

	@Override
	public Integer countJurisdictionsIssue(String orgLevel, String orgCode,
			String issueViewType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgLevel", orgLevel);
		map.put("orgCode", orgCode);
		if (IssueViewType.NEED.equals(issueViewType)) {
			map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"issueLeaderViewCache.countJurisdictionsNeedDo", map);
		} else if (IssueViewType.CHECKPENDING.equals(issueViewType)) {
			map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
			map.put("superviselevel", IssueState.RED_CARD_SUPERVISE);
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"issueLeaderViewCache.countJurisdictionsAuditDelayByLevel",
					map);
		} else if (IssueViewType.VERIFICATION.equals(issueViewType)) {
			map.put("completeCode", IssueState.ISSUEVERIFICATION_CODE);
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"issueLeaderViewCache.countJurisdictionsVerification", map);
		} else if (IssueViewType.CHECKGRADE.equals(issueViewType)) {
			map.put("limitDate", CustomDateUtil
					.dateBeforeNowDateByDays(CustomDateUtil.DAYS_BEFORE));
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"issueLeaderViewCache.countGradeDelayIssue", map);
		} else if (IssueViewType.FOLLOW.equals(issueViewType)) {
			map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
			map.put("issueCompleteCode", IssueState.ISSUECOMPLETE_CODE);
			map.put("verification", IssueState.VERIFICATION);
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"issueLeaderViewCache.countJurisdictionsFollow", map);
		} else if (IssueViewType.DONE.equals(issueViewType)) {
			map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"issueLeaderViewCache.countJurisdictionsDone", map);
		} else if ("historyDone".equals(issueViewType)) {
			map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"issueLeaderViewCache.countJurisdictionsDoneHistory", map);
		} else if (IssueViewType.COMPLETED.equals(issueViewType)) {
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"issueLeaderViewCache.countCompletedIssue", map);
		} else if (IssueViewType.SUBMIT.equals(issueViewType)) {
			map.put("submit", IssueSourceState.submit);
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"issueLeaderViewCache.countJurisdictionsSubmit", map);
		} else if (IssueViewType.ASSIGN.equals(issueViewType)) {
			map.put("assgin", IssueSourceState.assign);
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"issueLeaderViewCache.countJurisdictionsAssgin", map);
		} else if (IssueViewType.SKIP.equals(issueViewType)) {
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"issueLeaderViewCache.countJurisdictionsSkipIssue", map);
		} else if (IssueViewType.PUBLICLTYCASSDONE.equals(issueViewType)) {
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"issueLeaderViewCache.countJurisdictionsPublicltyCassDone",
					map);
		} else if (IssueViewType.TIMEOUT.equals(issueViewType)) {
			map.put("supervise", IssueState.YELLOW_CARD_SUPERVISE);
			map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
			map.put("verification", IssueState.ISSUEVERIFICATION_CODE);
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"issueLeaderViewCache.countTimeOutIssue", map);
		}
		return null;
	}

	@Override
	public List<IssueViewObject> findJurisdictionsIssue(String orgLevel,
			String orgCode, String issueViewType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgLevel", orgLevel);
		map.put("orgCode", orgCode);
		if (IssueViewType.NEED.equals(issueViewType)) {
			map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
			return getSqlMapClientTemplate().queryForList(
					"issueLeaderViewCache.findJurisdictionsNeedDo", map, 0,
					cachePage);
		} else if (IssueViewType.CHECKPENDING.equals(issueViewType)) {
			map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
			map.put("superviselevel", IssueState.RED_CARD_SUPERVISE);
			return getSqlMapClientTemplate().queryForList(
					"issueLeaderViewCache.findJurisdictionAuditDelay", map, 0,
					cachePage);
		} else if (IssueViewType.VERIFICATION.equals(issueViewType)) {
			map.put("completeCode", IssueState.ISSUEVERIFICATION_CODE);
			return getSqlMapClientTemplate().queryForList(
					"issueLeaderViewCache.findJurisdictionsVerification", map,
					0, cachePage);
		} else if (IssueViewType.FOLLOW.equals(issueViewType)) {
			map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
			map.put("issueCompleteCode", IssueState.ISSUECOMPLETE_CODE);
			map.put("verification", IssueState.VERIFICATION);
			return getSqlMapClientTemplate().queryForList(
					"issueLeaderViewCache.findJurisdictionsFollow", map, 0,
					cachePage);
		} else if (IssueViewType.DONE.equals(issueViewType)) {
			map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
			return getSqlMapClientTemplate().queryForList(
					"issueLeaderViewCache.findJurisdictionsDone", map, 0,
					cachePage);
		} else if ("historyDone".equals(issueViewType)) {
			map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
			return getSqlMapClientTemplate().queryForList(
					"issueLeaderViewCache.findJurisdictionsDoneHistory", map,
					0, cachePage);
		} else if (IssueViewType.SUBMIT.equals(issueViewType)) {
			map.put("submit", IssueSourceState.submit);
			return getSqlMapClientTemplate().queryForList(
					"issueLeaderViewCache.findJurisdictionsSubmit", map, 0,
					cachePage);
		} else if (IssueViewType.ASSIGN.equals(issueViewType)) {
			map.put("assgin", IssueSourceState.assign);
			return getSqlMapClientTemplate().queryForList(
					"issueLeaderViewCache.findJurisdictionsAssgin", map, 0,
					cachePage);
		} else if (IssueViewType.SKIP.equals(issueViewType)) {
			return getSqlMapClientTemplate().queryForList(
					"issueLeaderViewCache.findJurisdictionsSkeipIssue", map, 0,
					cachePage);
		} else if (IssueViewType.PUBLICLTYCASSDONE.equals(issueViewType)) {
			return getSqlMapClientTemplate().queryForList(
					"issueLeaderViewCache.findJurisdictionsPublicltyCassDone",
					map, 0, cachePage);
		} else if (IssueViewType.TIMEOUT.equals(issueViewType)) {
			map.put("supervise", IssueState.YELLOW_CARD_SUPERVISE);
			map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
			map.put("verification", IssueState.ISSUEVERIFICATION_CODE);
			return getSqlMapClientTemplate().queryForList(
					"issueLeaderViewCache.findTimeOutIssue", map, 0, cachePage);
		}
		return null;
	}

	public List<CompletedIssue> findGradeDelayAndCompletedIssueByPage(
			String orgLevel, String orgCode, String issueViewType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgLevel", orgLevel);
		map.put("orgCode", orgCode);
		if (IssueViewType.CHECKGRADE.equals(issueViewType)) {
			map.put("limitDate", CustomDateUtil
					.dateBeforeNowDateByDays(CustomDateUtil.DAYS_BEFORE));
			return getSqlMapClientTemplate().queryForList(
					"issueLeaderViewCache.findGradeDelayIssueByPage", map, 0,
					cachePage);
		} else if (IssueViewType.COMPLETED.equals(issueViewType)) {
			return getSqlMapClientTemplate().queryForList(
					"issueLeaderViewCache.findCompletedIssueByPage", map, 0,
					cachePage);
		}
		return null;
	}

	@Override
	public void addIssueCache(IssueCacheVO issueCacheVO) {

		getSqlMapClientTemplate().insert("issueLeaderViewCache.addIssueCache",
				issueCacheVO);
	}
}
