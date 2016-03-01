package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.vo.PageInfo;
import com.tianque.dao.PublicltyCassDao;
import com.tianque.issue.domain.PublicltyCass;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.service.PublicltyCassService;

@Service("publicltyCassService")
@Transactional
public class PublicltyCassServiceImpl implements PublicltyCassService {

	@Autowired
	private PublicltyCassDao publicltyCassDao;

	@Override
	public PublicltyCass addPublicltyCass(PublicltyCass publicltyCass) {
		return publicltyCassDao.addPublicltyCass(publicltyCass);
	}

	@Override
	public PageInfo<IssueViewObject> findPublicltyCassForPage(Long orgId, Integer page,
			Integer rows, String sidx, String sord) {
		return publicltyCassDao.findPublicltyCassForPage(orgId, page, rows, sidx, sord);
	}

	@Override
	public void deletePublicltyCassByIssueIdAndOrgId(Long issueId, Long orgId) {
		publicltyCassDao.deletePublicltyCassByIssueIdAndOrgId(issueId, orgId);
	}

	public PublicltyCassDao getPublicltyCassDao() {
		return publicltyCassDao;
	}

	public void setPublicltyCassDao(PublicltyCassDao publicltyCassDao) {
		this.publicltyCassDao = publicltyCassDao;
	}

	@Override
	public List<PublicltyCass> findPublicltyCassByIssueIdAndOrgId(Long issueId, Long orgId) {
		return publicltyCassDao.findPublicltyCassByIssueIdAndOrgId(issueId, orgId);
	}
}
