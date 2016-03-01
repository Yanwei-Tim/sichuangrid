package com.tianque.service;

import java.util.List;

import com.tianque.domain.IssueTypeDomain;

public interface IssueTypeDomainService {

	List<IssueTypeDomain> findIssueTypeDomains();

	/**
	 * 查询出事件用到的大类（提供手机事件研判分析用）
	 * */
	public List<IssueTypeDomain> findIssueTypeDomainsToIssue();

	IssueTypeDomain addIssueTypeDomain(IssueTypeDomain issueTypeDomain);

	IssueTypeDomain getIssueTypeDoaminById(Long id);

	IssueTypeDomain getIssueTypeDoaminByDomainName(String domainName);

	List<IssueTypeDomain> findIssueTypeDomainsByDomainName(String domainName);

}
