package com.tianque.issue.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.issue.domain.IssueStandardForFunOrg;

public interface IssueStandardForFunOrgService {

	public IssueStandardForFunOrg getIssueStandardForFunOrgById(Long id);

	public IssueStandardForFunOrg addIssueStandardForFunOrg(
			IssueStandardForFunOrg issueStandardForFunOrg);

	public IssueStandardForFunOrg updateIssueStandardForFunOrg(
			IssueStandardForFunOrg issueStandardForFunOrg);

	public PageInfo<IssueStandardForFunOrg> findIssueStandardForFunOrgsForList(
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public boolean deleteIssueStandardForFunOrgByIds(Long[] ids);

	public IssueStandardForFunOrg getIssueStandardForFunOrgByOrgIdAndItemId(
			Long orgId, Long itemId);

	public List<IssueStandardForFunOrg> findItemTypeByFunOrgId(Long funOrgId);

	public boolean validateRepeatByOrgIdAndItemName(
			IssueStandardForFunOrg issueStandardForFunOrg);
}
