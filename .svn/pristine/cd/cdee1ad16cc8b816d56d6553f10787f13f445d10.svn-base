package com.tianque.issue.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.utils.CustomDateUtil;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.issue.dao.CompletedIssueDao;
import com.tianque.issue.domain.CompletedIssue;

@Repository("completedIssueDao")
public class CompletedIssueDaoImpl extends AbstractBaseDao implements
		CompletedIssueDao {

	@Override
	public CompletedIssue addCompletedIssue(CompletedIssue completedIssue) {
		getSqlMapClientTemplate().insert("issue.addCompletedIssue",
				completedIssue);
		return completedIssue;
	}

	@Override
	public PageInfo<CompletedIssue> findCompletedIssueByPage(Long orgId,
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, Long issueType) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("topIssuesOrgId", orgId);
		map.put("createOrgInternalCode", orgInternalCode);
		map.put("userOrgLevel", ThreadVariable.getOrganization().getOrgLevel()
				.getInternalId());
		List<CompletedIssue> ret = getSqlMapClientTemplate().queryForList(
				"issue.findCompletedIssueByPage", map, (page - 1) * rows, rows);
		PageInfo<CompletedIssue> result = new PageInfo<CompletedIssue>();
		result.setTotalRowSize((Long) getSqlMapClientTemplate().queryForObject(
				"issue.countCompletedIssue", map));
		result.setCurrentPage(rows);
		result.setPerPageSize(page);
		result.setResult(ret);

		return result;
	}

	@Override
	public CompletedIssue buildCompletedIssueByIssueId(Map<String, Object> param) {
		return (CompletedIssue) getSqlMapClientTemplate().queryForObject(
				"issue.findJurisdictionsCompletedByIssueId", param);
	}

	@Override
	public PageInfo<CompletedIssue> findCompletedIssueByPage(Organization org,
			Integer page, Integer rows, String sidx, String sord,
			Long orgLevel, Long functionalOrgType, String leaderView,
			Long sourceType, Long issueType, String evaluationType,
			int isCurrentOrg, int orgCodeFindLevel, Long searchOrgId,
			String searchOrgCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		map.put("searchOrgId", searchOrgId);
		if (searchOrgId != null) {
			map.put("orgCodeFindLevel", orgCodeFindLevel);
			map.put("searchOrgCode", searchOrgCode);
		}
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		if (leaderView != null && !"".equals(leaderView)) {
			map.put("leaderView", leaderView);
		}

		if (sourceType != null) {
			map.put("sourceType", sourceType);
		}
		map.put("evaluationType", evaluationType);
		map.put("functionalOrgType", functionalOrgType);
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		map.put("isCurrentOrg", isCurrentOrg);
		map.put("userOrgLevel", ThreadVariable.getOrganization().getOrgLevel()
				.getInternalId());
		Long total = 0L;
		Object totalObj = getSqlMapClientTemplate().queryForObject(
				"issue.countCompletedIssue", map);

		map.put("sortField", sidx);
		map.put("order", sord);

		List<CompletedIssue> ret = getSqlMapClientTemplate().queryForList(
				"issue.findCompletedIssueByPage", map, (page - 1) * rows, rows);

		if (null != totalObj) {
			total = (Long) totalObj;
		}

		PageInfo<CompletedIssue> result = new PageInfo<CompletedIssue>();
		result.setTotalRowSize(total);
		result.setCurrentPage(page);
		result.setPerPageSize(rows);
		result.setResult(ret);

		return result;
	}

	@Override
	public boolean topIssue(Map<String, Object> topIssue) {
		int ret = getSqlMapClientTemplate().update("issue.completedTopIssue",
				topIssue);
		return ret > 0 ? true : false;
	}

	@Override
	public void publicltycassIssue(Long issueId, int publicltycass) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueId", issueId);
		map.put("publicltycass", publicltycass);
		getSqlMapClientTemplate().update("issue.publicltycassIssue", map);
	}

	@Override
	public boolean removeCompletedIssueByIssueId(Long issueId) {
		int deleteCount = (int) getSqlMapClientTemplate().delete(
				"issue.removeCompletedIssueByIssueId", issueId);
		return deleteCount > 0 ? true : false;
	}

	@Override
	public CompletedIssue getCompletedByIssueId(Long issueId) {
		return (CompletedIssue) getSqlMapClientTemplate().queryForObject(
				"issue.getCompletedByIssueId", issueId);
	}

	@Override
	public PageInfo<CompletedIssue> findGradeDelayIssueByPage(Organization org,
			Integer page, Integer rows, String sidx, String sord,
			Long orgLevel, Long functionalOrgType, String leaderView,
			Long sourceType, Long issueType, String evaluationType,
			int isCurrentOrg, int orgCodeFindLevel, Long searchOrgId,
			String searchOrgCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		map.put("searchOrgId", searchOrgId);
		if (searchOrgId != null) {
			map.put("orgCodeFindLevel", orgCodeFindLevel);
			map.put("searchOrgCode", searchOrgCode);
		}
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		if (leaderView != null && !"".equals(leaderView)) {
			map.put("leaderView", leaderView);
		}

		if (sourceType != null) {
			map.put("sourceType", sourceType);
		}
		map.put("evaluationType", evaluationType);
		map.put("functionalOrgType", functionalOrgType);
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		map.put("isCurrentOrg", isCurrentOrg);
		map.put("userOrgLevel", ThreadVariable.getOrganization().getOrgLevel()
				.getInternalId());
		/** 20天以内的展示的条件 */
		map.put("limitDate", CustomDateUtil
				.dateBeforeNowDateByDays(CustomDateUtil.DAYS_BEFORE));
		Long total = 0L;
		Object totalObj = getSqlMapClientTemplate().queryForObject(
				"issue.countGradeDelayIssue", map);

		map.put("sortField", sidx);
		map.put("order", sord);

		List<CompletedIssue> ret = getSqlMapClientTemplate()
				.queryForList("issue.findGradeDelayIssueByPage", map,
						(page - 1) * rows, rows);

		if (null != totalObj) {
			total = (Long) totalObj;
		}

		PageInfo<CompletedIssue> result = new PageInfo<CompletedIssue>();
		result.setTotalRowSize(total);
		result.setCurrentPage(page);
		result.setPerPageSize(rows);
		result.setResult(ret);

		return result;
	}

	@Override
	public void updateEventStateByIssueId(Long issueId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueId", issueId);
		getSqlMapClientTemplate().update(
				"issue.updateCompletedIssueEventStateByIssueId", map);

	}

}
