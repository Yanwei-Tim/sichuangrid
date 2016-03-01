package com.tianque.fourTeams.fourTeamsIssue.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStandardForFunOrg;

public interface FourTeamsIssueStandardForFunOrgDao {
	public FourTeamsIssueStandardForFunOrg getIssueStandardForFunOrgById(Long id);

	public FourTeamsIssueStandardForFunOrg addIssueStandardForFunOrg(
			FourTeamsIssueStandardForFunOrg issueStandardForFunOrg);

	public FourTeamsIssueStandardForFunOrg updateIssueStandardForFunOrg(
			FourTeamsIssueStandardForFunOrg issueStandardForFunOrg);

	public PageInfo<FourTeamsIssueStandardForFunOrg> findIssueStandardForFunOrgsForList(
			Integer pageNum, Integer pageSize, String sidx, String sord,
			Long userLevel);

	public boolean deleteIssueStandardForFunOrgByIds(Long[] ids);

	public FourTeamsIssueStandardForFunOrg getIssueStandardForFunOrgByOrgIdAndItemId(
			Long orgId, Long itemId);

	public List<FourTeamsIssueStandardForFunOrg> findItemTypeByFunOrgId(Long funOrgId);

	public boolean validateRepeatByOrgIdAndItemName(
			FourTeamsIssueStandardForFunOrg issueStandardForFunOrg);
}
