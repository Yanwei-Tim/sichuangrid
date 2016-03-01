package com.tianque.fourTeams.dao;

import java.util.List;

import com.tianque.domain.IssueTypeDomain;

public interface FourTeamsIssueTypeDomainDao {

	List<IssueTypeDomain> findIssueTypeDomains();

	IssueTypeDomain addIssueTypeDomain(IssueTypeDomain issueTypeDomain);

	IssueTypeDomain getIssueTypeDoaminByDomainName(String domainName);

	IssueTypeDomain getIssueTypeDoaminById(Long id);

	List<IssueTypeDomain> findIssueTypeDomainsByModule(String module);
	
	List<IssueTypeDomain> findIssueTypeDomainsByDomainName(String domainName);
}
