package com.tianque.fourTeams.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsPublicltyCass;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;

public interface FourTeamsPublicltyCassService {
	public FourTeamsPublicltyCass addPublicltyCass(FourTeamsPublicltyCass publicltyCass);

	public PageInfo<FourTeamsIssueViewObject> findPublicltyCassForPage(Long orgId,
			Integer page, Integer rows, String sidx, String sord);

	public void deletePublicltyCassByIssueIdAndOrgId(Long issueId, Long orgId);

	public List<FourTeamsPublicltyCass> findPublicltyCassByIssueIdAndOrgId(Long issueId,
			Long orgId);
}
