package com.tianque.fourTeams.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.fourTeams.dao.FourTeamsIssueWorkFlowDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueState;

@Repository("fourTeamsIssueWorkFlowDao")
@SuppressWarnings("unchecked")
public class FourTeamsIssueWorkFlowDaoImpl extends AbstractBaseDao implements
		FourTeamsIssueWorkFlowDao {

	@Override
	public FourTeamsIssueStep addIssueStep(FourTeamsIssueStep step) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"issueStep.addIssueStep", step);
		return loadSimpleIssueStepById(id);
	}

	private FourTeamsIssueStep loadSimpleIssueStepById(Long id) {
		return (FourTeamsIssueStep) getSqlMapClientTemplate().queryForObject(
				"issueStep.loadSimpleStep", id);
	}

	@Override
	public FourTeamsIssueStep findLastNotCompleteIssueStepByOrg(Long issueId,
			Long dealOrgId) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("issue", issueId);
		condition.put("orgId", dealOrgId);
		condition.put("stateCode", FourTeamsIssueState.STEPCOMPLETE_CODE);
		return (FourTeamsIssueStep) getSqlMapClientTemplate().queryForObject(
				"issueStep.findLastNotCompleteIssueStepByOrg", condition);
	}

	@Override
	public FourTeamsIssueStep getIssueStepById(Long stepId) {
		return loadSimpleIssueStepById(stepId);
	}

	@Override
	public FourTeamsIssueStep updateIssueStepExceptOrg(FourTeamsIssueStep step) {
		getSqlMapClientTemplate().update("issueStep.updateIssueStepExceptOrg",
				step);
		return loadSimpleIssueStepById(step.getId());
	}

	@Override
	public List<Organization> findChildOrgsByOrgcodeAndName(
			PropertyDict orgType, String parentCode, String tag,
			String exceptOrgIds, int maxCount) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("orgType", orgType);
		condition.put("parentCode", parentCode);
		condition.put("tag", tag);
		condition.put("exceptOrgIds", exceptOrgIds);
		if (maxCount > 0) {
			return getSqlMapClientTemplate().queryForList(
					"issueStep.findChildOrgsByOrgcodeAndName", condition, 0,
					maxCount);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"issueStep.findChildOrgsByOrgcodeAndName", condition);
		}
	}

	@Override
	public List<Organization> findChildOrgsByParentIdAndName(
			PropertyDict orgType, Long id, String tag, String exceptOrgIds,
			int maxCount) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("orgType", orgType);
		condition.put("parentId", id);
		condition.put("tag", tag);
		condition.put("exceptOrgIds", exceptOrgIds);
		if (maxCount > 0) {
			return getSqlMapClientTemplate().queryForList(
					"issueStep.findChildOrgsByParentIdAndName", condition, 0,
					maxCount);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"issueStep.findChildOrgsByParentIdAndName", condition);
		}
	}

	@Override
	public List<Organization> findChildOrgsByParentFunIdAndName(Long id,
			String tag, String exceptOrgIds, int maxCount) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("parentFunId", id);
		condition.put("tag", tag);
		condition.put("exceptOrgIds", exceptOrgIds);
		if (maxCount > 0) {
			return getSqlMapClientTemplate().queryForList(
					"issueStep.findChildOrgsByParentFunIdAndName", condition,
					0, maxCount);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"issueStep.findChildOrgsByParentFunIdAndName", condition);
		}
	}

}
