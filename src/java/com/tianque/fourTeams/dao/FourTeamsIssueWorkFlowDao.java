package com.tianque.fourTeams.dao;

import java.util.List;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;

public interface FourTeamsIssueWorkFlowDao {

	FourTeamsIssueStep addIssueStep(FourTeamsIssueStep step);

	FourTeamsIssueStep findLastNotCompleteIssueStepByOrg(Long issueId, Long dealOrgId);

	FourTeamsIssueStep getIssueStepById(Long stepId);

	FourTeamsIssueStep updateIssueStepExceptOrg(FourTeamsIssueStep step);

	List<Organization> findChildOrgsByOrgcodeAndName(PropertyDict orgType,
			String parentCode, String tag, String exceptOrgIds, int maxCount);

	List<Organization> findChildOrgsByParentIdAndName(PropertyDict orgType,
			Long id, String tag, String exceptOrgIds, int maxCount);

	List<Organization> findChildOrgsByParentFunIdAndName(Long id, String tag,
			String exceptOrgIds, int maxCount);

}
