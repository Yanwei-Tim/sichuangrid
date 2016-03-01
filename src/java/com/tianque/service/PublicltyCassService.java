package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.issue.domain.PublicltyCass;
import com.tianque.issue.vo.IssueViewObject;

public interface PublicltyCassService {
	public PublicltyCass addPublicltyCass(PublicltyCass publicltyCass);

	public PageInfo<IssueViewObject> findPublicltyCassForPage(Long orgId, Integer page,
			Integer rows, String sidx, String sord);

	public void deletePublicltyCassByIssueIdAndOrgId(Long issueId, Long orgId);

	public List<PublicltyCass> findPublicltyCassByIssueIdAndOrgId(Long issueId, Long orgId);
}
