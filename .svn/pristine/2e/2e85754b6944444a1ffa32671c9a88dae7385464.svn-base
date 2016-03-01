package com.tianque.fourTeams.dao;

import java.util.Date;
import java.util.List;

import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;

public interface FourTeamsIssueLogDaoNew {

	public FourTeamsIssueLogNew findIssueLogNewByIssueIdAndTargeorgIdNotDealorgId(
			Long issueId, Long targeorgId, Long dealorgId);

	public FourTeamsIssueLogNew addIssueLog(FourTeamsIssueLogNew issueLog);

	public FourTeamsIssueLogNew getIssueLogById(Long id);

	public Long getDealMaxStepIndexByIssueId(Long issueId);

	public List<FourTeamsIssueLogNew> findIssueLogsByIssueId(Long issueId);

	public void updateIssueLogDealState(Long id, Long dealState);

	public void updateIssueLogLogCompleteTime(Long id, Date logCompleteTime);

	public void updateSupervisionState(Long id, Long supervisionState);

	public FourTeamsIssueLogNew getCompleteIssueLogNewByIssueId(Long issueId);

	public FourTeamsIssueLogNew getLastOperationLog(Long issueId, Long orgId, Long state);

	public FourTeamsIssueLogNew getIssueLogByIdAndSuperviseState(Long issueLogId,
			Long superviseState, Long doneState, Long completeState);

	public void deleteIssueLogByIssueId(Long id);

	public List<FourTeamsIssueLogNew> findIssueLogByOrgIdAndDate(Long orgId,
			Date startDate, Date endDate);

	public Long countOverTimeNotDoneByOverTimeLogTable(Long year, Long month,
			Date endDate, Long targeOrgId);

	public List<FourTeamsIssueLogNew> findIssueLogByOrgInternalCodeAndDate(
			String orgInternalCode, Date startDate, Date endDate);

	public List<FourTeamsIssueLogNew> findDealStatIssueLogByOrgInternalCodeAndDate(
			String orgInternalCode, Date startDate, Date endDate);

}
