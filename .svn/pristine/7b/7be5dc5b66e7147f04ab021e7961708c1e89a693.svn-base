package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.dao.IssueTypeDomainDao;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.service.IssueTypeDomainService;

@Service("issueTypeDomainService")
@Transactional
public class IssueTypeDomainServiceImpl extends AbstractBaseService implements
		IssueTypeDomainService {

	@Autowired
	private IssueTypeDomainDao issueTypeDomainDao;

	@Override
	public List<IssueTypeDomain> findIssueTypeDomains() {
		return issueTypeDomainDao.findIssueTypeDomains();
	}

	@Override
	public IssueTypeDomain addIssueTypeDomain(IssueTypeDomain issueTypeDomain) {
		return issueTypeDomainDao.addIssueTypeDomain(issueTypeDomain);
	}

	@Override
	public IssueTypeDomain getIssueTypeDoaminById(Long id) {
		return issueTypeDomainDao.getIssueTypeDoaminById(id);
	}

	@Override
	public IssueTypeDomain getIssueTypeDoaminByDomainName(String domainName) {
		return issueTypeDomainDao.getIssueTypeDoaminByDomainName(domainName);
	}

	@Override
	public List<IssueTypeDomain> findIssueTypeDomainsByDomainName(
			String domainName) {
		return issueTypeDomainDao.findIssueTypeDomainsByDomainName(domainName);
	}

	@Override
	public List<IssueTypeDomain> findIssueTypeDomainsToIssue() {
		return issueTypeDomainDao.findIssueTypeDomainsToIssue();
	}

}
