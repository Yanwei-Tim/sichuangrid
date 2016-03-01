package com.tianque.issue.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.issue.constants.IssueTag;
import com.tianque.issue.constants.IssueViewType;
import com.tianque.issue.dao.IssueHistoryDao;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueMap;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.state.IssueSourceState;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.state.IssueStepGroup;
import com.tianque.issue.vo.IssueViewObject;

/**
 * @author n-235
 * 
 */
@Repository("issueHistoryDao")
public class IssueHistoryDaoImpl extends AbstractBaseDao implements
		IssueHistoryDao {

	private PageInfo<IssueViewObject> createIssueVoPageInfoInstance(
			int totalRecord, int pageSize, int pageIndex) {
		PageInfo<IssueViewObject> result = new PageInfo<IssueViewObject>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}

	private int getJurisdictionsSkipIssueHistoryCount(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"issueHistory.getJurisdictionsSkipIssueHistoryCount", map);
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsSkipIssueHistory(
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode) {

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
		map.put("functionalOrgType", functionalOrgType);
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		// map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
		map.put("issueTag", IssueTag.SKIP_ISSUE);
		PageInfo<IssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsSkipIssueHistoryCount(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"issueHistory.findJurisdictionsSkipIssueHistory", map,
				(page - 1) * rows, rows));
		return result;

	}

	private int getJurisdictionsDoneHistoryCount(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"issueHistory.getJurisdictionsDoneHistoryCount", map);
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsDoneHistory(
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode) {

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
		map.put("functionalOrgType", functionalOrgType);
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
		map.put("issueTag", IssueTag.DONE_ISSUE);
		PageInfo<IssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsDoneHistoryCount(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (IssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList(
					"issueHistory.findJurisdictionsDoneHistory", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList(
					"issueHistory.findJurisdictionsDoneHistory", map,
					(page - 1) * rows, rows));
		}
		return result;
	}

	private int getJurisdictionsAssginCountHistoryCount(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"issueHistory.getJurisdictionsAssginCountHistoryCount", map);
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsAssginHistory(
			String seachValue, Organization org, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType, int orgCodeFindLevel, Long searchOrgId,
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
		map.put("functionalOrgType", functionalOrgType);
		map.put("seachValue", seachValue);
		map.put("orgId", org.getId());
		map.put("orgCode", org.getOrgInternalCode());
		map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
		map.put("assgin", IssueSourceState.assign);
		map.put("issueTag", IssueTag.ASSIGN_ISSUE);
		PageInfo<IssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsAssginCountHistoryCount(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (IssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList(
					"issueHistory.findJurisdictionsAssginHistory", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList(
					"issueHistory.findJurisdictionsAssginHistory", map,
					(page - 1) * rows, rows));
		}
		return result;

	}

	private int getJurisdictionsSubmitHistoryCount(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"issueHistory.getJurisdictionsSubmitHistoryCount", map);
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsSubmitHistory(
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		map.put("searchOrgId", searchOrgId);
		if (searchOrgId != null) {
			map.put("orgCodeFindLevel", orgCodeFindLevel);
			map.put("searchOrgCode", searchOrgCode);
		}
		if (leaderView != null && !"".equals(leaderView)) {
			map.put("leaderView", leaderView);
		}
		if (sourceType != null) {
			map.put("sourceType", sourceType);
		}
		map.put("functionalOrgType", functionalOrgType);
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
		map.put("submit", IssueSourceState.submit);
		map.put("issueTag", IssueTag.SUBMIT_ISSUE);
		PageInfo<IssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsSubmitHistoryCount(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (IssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList(
					"issueHistory.findJurisdictionsSubmitHistory", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList(
					"issueHistory.findJurisdictionsSubmitHistory", map,
					(page - 1) * rows, rows));
		}
		return result;

	}

	@Override
	public List<IssueLogNew> loadIssueOperationLogsHistoryByIssueId(Long id) {
		return getSqlMapClientTemplate().queryForList(
				"issueHistory.loadIssueOperationLogsHistoryByIssueId", id);
	}

	@Override
	public IssueNew getFullIssueHistoryById(Long id) {
		IssueNew historyIssue = (IssueNew) getSqlMapClientTemplate()
				.queryForObject("issueHistory.getFullIssueHistoryById", id);
		if (historyIssue != null) {
			historyIssue.setHistoryIssue(Boolean.TRUE);
		}
		return historyIssue;
	}

	@Override
	public List<IssueStepGroup> getIssueStepGroupHistoryByIssueId(Long id) {
		return getSqlMapClientTemplate().queryForList(
				"issueHistory.getIssueStepGroupHistoryByIssueId", id);
	}

	@Override
	public IssueMap getIssueMapHistoryByStepGroup(IssueStepGroup issueStepGroup) {
		return (IssueMap) getSqlMapClientTemplate().queryForObject(
				"issueHistory.getIssueMapHistoryByStepGroup", issueStepGroup);
	}

	@Override
	public IssueStepGroup getSimpleIssueStepHistoryGroupById(Long id) {
		return (IssueStepGroup) getSqlMapClientTemplate().queryForObject(
				"issueHistory.getSimpleIssueStepHistoryGroupById", id);

	}

	@Override
	public List<IssueLogNew> findIssueDealLogHistoryByIssueMapAndIssueStepGroup(
			IssueMap issueMap, IssueStepGroup group) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueId", group.getIssue().getId());
		map.put("orgId", issueMap.getOrgId());
		map.put("startId", group.getEntyLog().getId());
		map.put("endId", group.getOutLog() == null ? null : group.getOutLog()
				.getId());
		return getSqlMapClientTemplate()
				.queryForList(
						"issueHistory.findIssueDealLogHistoryByIssueMapAndIssueStepGroup",
						map);

	}

	@Override
	public List<IssueStep> searchIssueStepsHistoryByIssueId(Long issueId) {
		return getSqlMapClientTemplate().queryForList(
				"issueHistory.searchIssueStepsHistoryByIssueId", issueId);
	}

	@Override
	public List<IssueStep> searchAllIssueStepsHistoryByStepId(Long stepId) {
		return getSqlMapClientTemplate().queryForList(
				"issueHistory.searchAllIssueStepsHistoryByStepId", stepId);
	}

	@Override
	public boolean deleteIssueLogHistoryByIssueId(Long issueId) {
		getSqlMapClientTemplate().delete(
				"issueHistory.deleteIssueLogHistoryByIssueId", issueId);
		return true;
	}

	@Override
	public boolean deleteIssueHistoryById(Long issueId) {
		getSqlMapClientTemplate().delete("issueHistory.removeIssueHistory",
				issueId);
		return true;
	}

	@Override
	public boolean deleteIssueStepsHistoryByIssueId(Long issueId) {
		getSqlMapClientTemplate().delete(
				"issueHistory.deleteIssueStepsHistoryByIssueId", issueId);
		return true;
	}

	@Override
	public IssueNew getFullIssueByHistoryStepId(Long stepId) {
		return (IssueNew) getSqlMapClientTemplate().queryForObject(
				"issueHistory.getFullIssueByHistoryStepId", stepId);
	}

	@Override
	public IssueStep getSimpleIssueStepHistoryById(Long id) {
		return (IssueStep) getSqlMapClientTemplate().queryForObject(
				"issueHistory.loadSimpleStepHistory", id);
	}

}
