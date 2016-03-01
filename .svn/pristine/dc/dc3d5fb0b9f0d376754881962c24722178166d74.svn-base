package com.tianque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.vo.PageInfo;
import com.tianque.dao.HistoricalIssueDao;
import com.tianque.issue.domain.HistoricalIssue;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.service.HistoricalIssueService;

@Service("historicalIssueService")
@Transactional
public class HistoricalIssueServiceImpl implements HistoricalIssueService {

	@Autowired
	private HistoricalIssueDao historicalIssueDao;

	@Override
	public HistoricalIssue addHistoricalIssue(HistoricalIssue historicalIssue) {
		return historicalIssueDao.addHistoricalIssue(historicalIssue);
	}

	@Override
	public PageInfo<IssueViewObject> findHistoricalIssueForPage(Long orgId, Integer page,
			Integer rows, String sidx, String sord) {
		return historicalIssueDao.findHistoricalIssueForPage(orgId, page, rows, sidx, sord);
	}

	@Override
	public void deleteHistoricalIssueByIssueIdAndOrgId(Long issueId, Long orgId) {
		historicalIssueDao.deleteHistoricalIssueByIssueIdAndOrgId(issueId, orgId);
	}

	@Override
	public HistoricalIssue findHistoricalIssueByIssueIdAndOrgId(Long issueId, Long orgId) {
		return historicalIssueDao.findHistoricalIssueByIssueIdAndOrgId(issueId, orgId);
	}

	public HistoricalIssueDao getHistoricalIssueDao() {
		return historicalIssueDao;
	}

	public void setHistoricalIssueDao(HistoricalIssueDao historicalIssueDao) {
		this.historicalIssueDao = historicalIssueDao;
	}
}
