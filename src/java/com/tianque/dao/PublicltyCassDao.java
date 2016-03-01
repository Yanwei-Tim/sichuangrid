package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.issue.domain.PublicltyCass;
import com.tianque.issue.vo.IssueViewObject;

public interface PublicltyCassDao {

	public PublicltyCass addPublicltyCass(PublicltyCass publicltyCass);

	public PublicltyCass getSimplePublicltyCassById(Long id);

	public PageInfo<IssueViewObject> findPublicltyCassForPage(Long orgId, Integer page,
			Integer rows, String sidx, String sord);

	public void deletePublicltyCassByIssueIdAndOrgId(Long issueId, Long orgId);

	public void deletePublicltyCassByIssueId(Long issueId);

	public List<PublicltyCass> findPublicltyCassByIssueIdAndOrgId(Long issueId, Long orgId);
}
