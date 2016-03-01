package com.tianque.fourTeams.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.vo.PageInfo;
import com.tianque.fourTeams.dao.FourTeamsPublicltyCassDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsPublicltyCass;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;
import com.tianque.fourTeams.service.FourTeamsPublicltyCassService;

@Service("fourTeamsPublicltyCassService")
@Transactional
public class FourTeamsPublicltyCassServiceImpl implements FourTeamsPublicltyCassService {

	@Autowired
	private FourTeamsPublicltyCassDao publicltyCassDao;

	@Override
	public FourTeamsPublicltyCass addPublicltyCass(FourTeamsPublicltyCass publicltyCass) {
		return publicltyCassDao.addPublicltyCass(publicltyCass);
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findPublicltyCassForPage(Long orgId,
			Integer page, Integer rows, String sidx, String sord) {
		return publicltyCassDao.findPublicltyCassForPage(orgId, page, rows,
				sidx, sord);
	}

	@Override
	public void deletePublicltyCassByIssueIdAndOrgId(Long issueId, Long orgId) {
		publicltyCassDao.deletePublicltyCassByIssueIdAndOrgId(issueId, orgId);
	}

	public FourTeamsPublicltyCassDao getPublicltyCassDao() {
		return publicltyCassDao;
	}

	public void setPublicltyCassDao(FourTeamsPublicltyCassDao publicltyCassDao) {
		this.publicltyCassDao = publicltyCassDao;
	}

	@Override
	public List<FourTeamsPublicltyCass> findPublicltyCassByIssueIdAndOrgId(Long issueId,
			Long orgId) {
		return publicltyCassDao.findPublicltyCassByIssueIdAndOrgId(issueId,
				orgId);
	}
}
