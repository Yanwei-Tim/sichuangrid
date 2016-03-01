package com.tianque.fourTeams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.vo.PageInfo;
import com.tianque.fourTeams.dao.FourTeamsHistoricalIssueDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsHistoricalIssue;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;
import com.tianque.fourTeams.service.FourTeamsHistoricalIssueService;

@Service("fourTeamsIistoricalIssueService")
@Transactional
public class FourTeamsHistoricalIssueServiceImpl implements FourTeamsHistoricalIssueService {

	@Autowired
	private FourTeamsHistoricalIssueDao historicalIssueDao;

	@Override
	public FourTeamsHistoricalIssue addHistoricalIssue(FourTeamsHistoricalIssue historicalIssue) {
		return historicalIssueDao.addHistoricalIssue(historicalIssue);
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findHistoricalIssueForPage(Long orgId,
			Integer page, Integer rows, String sidx, String sord) {
		return historicalIssueDao.findHistoricalIssueForPage(orgId, page, rows,
				sidx, sord);
	}

	@Override
	public void deleteHistoricalIssueByIssueIdAndOrgId(Long issueId, Long orgId) {
		historicalIssueDao.deleteHistoricalIssueByIssueIdAndOrgId(issueId,
				orgId);
	}

	@Override
	public FourTeamsHistoricalIssue findHistoricalIssueByIssueIdAndOrgId(Long issueId,
			Long orgId) {
		return historicalIssueDao.findHistoricalIssueByIssueIdAndOrgId(issueId,
				orgId);
	}

	public FourTeamsHistoricalIssueDao getHistoricalIssueDao() {
		return historicalIssueDao;
	}

	public void setHistoricalIssueDao(FourTeamsHistoricalIssueDao historicalIssueDao) {
		this.historicalIssueDao = historicalIssueDao;
	}
}
