package com.tianque.dao;

import java.util.List;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.issue.domain.IssueStep;

public interface IssueWorkFlowDao {

	IssueStep addIssueStep(IssueStep step);

	IssueStep findLastNotCompleteIssueStepByOrg(Long issueId, Long dealOrgId);

	IssueStep getIssueStepById(Long stepId);

	IssueStep updateIssueStepExceptOrg(IssueStep step);

	List<Organization> findChildOrgsByOrgcodeAndName(PropertyDict orgType, String parentCode,
			String tag, String exceptOrgIds, int maxCount);

	List<Organization> findChildOrgsByParentIdAndName(PropertyDict orgType, Long id, String tag,
			String exceptOrgIds, int maxCount);

	List<Organization> findChildOrgsByParentFunIdAndName(Long id, String tag, String exceptOrgIds,
			int maxCount);

}
