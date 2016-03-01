package com.tianque.fourTeams.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsPublicltyCass;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;

public interface FourTeamsPublicltyCassDao {

	public FourTeamsPublicltyCass addPublicltyCass(FourTeamsPublicltyCass publicltyCass);

	public FourTeamsPublicltyCass getSimplePublicltyCassById(Long id);

	public PageInfo<FourTeamsIssueViewObject> findPublicltyCassForPage(Long orgId,
			Integer page, Integer rows, String sidx, String sord);

	public void deletePublicltyCassByIssueIdAndOrgId(Long issueId, Long orgId);

	public void deletePublicltyCassByIssueId(Long issueId);

	public List<FourTeamsPublicltyCass> findPublicltyCassByIssueIdAndOrgId(Long issueId,
			Long orgId);
}
