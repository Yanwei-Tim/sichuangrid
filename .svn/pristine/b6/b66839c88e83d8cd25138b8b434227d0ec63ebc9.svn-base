package com.tianque.fourTeams.service;

import java.util.List;

import org.apache.struts2.json.JSONException;

import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;

public interface FourTeamsIssueLogService {
	public FourTeamsIssueLogNew findIssueLogNewByIssueIdAndTargeorgIdNotDealorgId(
			Long issueId, Long targeorgId, Long dealorgId);

	public FourTeamsIssueLogNew concept(FourTeamsIssueLogNew issueLogVo);

	public List<FourTeamsIssueLogNew> findIssueLogsByIssueId(Long issueId);

	public void command(FourTeamsIssueLogNew issueLogVo);

	public FourTeamsIssueViewObject superviseIssueLog(FourTeamsIssueLogNew issueLogVo,
			int points, String superviseType) throws JSONException;

	public FourTeamsIssueViewObject cancelSuperviseIssueLog(FourTeamsIssueLogNew issueLogVo);

	public FourTeamsIssueLogNew getFullIssueLogById(Long id);

	public FourTeamsIssueLogNew getIssueLogByIdAndSuperviseState(Long issueLogId,
			Long superviseState, Long doneState, Long completeState);

	public List<FourTeamsIssueAttachFile> findIssueLogAttachFilesById(Long issueId);

	public FourTeamsIssueLogNew findLastOperationLog(Long issueId, Long orgId, Long state);

	public FourTeamsIssueAttachFile getIssueAttachFileById(Long id);

	public FourTeamsIssueLogNew getCompleteLogByIssueId(Long issueId);
}
