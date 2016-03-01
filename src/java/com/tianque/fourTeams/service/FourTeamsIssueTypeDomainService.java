package com.tianque.fourTeams.service;

import java.util.List;

import com.tianque.domain.IssueTypeDomain;

@Deprecated
public interface FourTeamsIssueTypeDomainService {

	List<IssueTypeDomain> findIssueTypeDomains();

	IssueTypeDomain addIssueTypeDomain(IssueTypeDomain issueTypeDomain);

	IssueTypeDomain getIssueTypeDoaminById(Long id);

	IssueTypeDomain getIssueTypeDoaminByDomainName(String domainName);
	
	List<IssueTypeDomain> findIssueTypeDomainsByDomainName(String domainName);

}
