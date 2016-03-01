package com.tianque.dao;

import java.util.Date;
import java.util.List;

import com.tianque.issue.domain.IssueLogNew;

public interface IssueLogDaoNew {

	public IssueLogNew findIssueLogNewByIssueIdAndTargeorgIdNotDealorgId(
			Long issueId, Long targeorgId, Long dealorgId);

	public IssueLogNew addIssueLog(IssueLogNew issueLog);

	public IssueLogNew getIssueLogById(Long id);

	public Long getDealMaxStepIndexByIssueId(Long issueId);

	public List<IssueLogNew> findIssueLogsByIssueId(Long issueId);

	public void updateIssueLogDealState(Long id, Long dealState);

	public void updateIssueLogLogCompleteTime(Long id, Date logCompleteTime);

	public void updateSupervisionState(Long id, Long supervisionState);

	public IssueLogNew getCompleteIssueLogNewByIssueId(Long issueId);

	public IssueLogNew getLastOperationLog(Long issueId, Long orgId, Long state);

	public IssueLogNew getIssueLogByIdAndSuperviseState(Long issueLogId,
			Long superviseState, Long doneState, Long completeState);

	public void deleteIssueLogByIssueId(Long id);

	public List<IssueLogNew> findIssueLogByOrgIdAndDate(Long orgId,
			Date startDate, Date endDate);

	public Long countOverTimeNotDoneByOverTimeLogTable(Long year, Long month,
			Date endDate, Long targeOrgId);

	public List<IssueLogNew> findIssueLogByOrgInternalCodeAndDate(
			String orgInternalCode, Date startDate, Date endDate);

	public List<IssueLogNew> findDealStatIssueLogByOrgInternalCodeAndDate(
			String orgInternalCode, Date startDate, Date endDate);

	public void changeIssueLogByIssueId(IssueLogNew log);

}
