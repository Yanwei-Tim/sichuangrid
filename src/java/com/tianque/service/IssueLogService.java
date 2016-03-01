package com.tianque.service;

import java.util.List;

import org.apache.struts2.json.JSONException;

import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.vo.IssueViewObject;

public interface IssueLogService {
	public IssueLogNew findIssueLogNewByIssueIdAndTargeorgIdNotDealorgId(
			Long issueId, Long targeorgId, Long dealorgId);

	public IssueLogNew concept(IssueLogNew issueLogVo);

	public List<IssueLogNew> findIssueLogsByIssueId(Long issueId);

	public void command(IssueLogNew issueLogVo);

	public IssueViewObject superviseIssueLog(IssueLogNew issueLogVo,
			int points, String superviseType) throws JSONException;

	public IssueViewObject cancelSuperviseIssueLog(IssueLogNew issueLogVo);

	public IssueLogNew getFullIssueLogById(Long id);

	public IssueLogNew getIssueLogByIdAndSuperviseState(Long issueLogId,
			Long superviseState, Long doneState, Long completeState);

	public List<IssueAttachFile> findIssueLogAttachFilesById(Long issueId);

	public IssueLogNew findLastOperationLog(Long issueId, Long orgId, Long state);

	public IssueAttachFile getIssueAttachFileById(Long id);

	public IssueLogNew getCompleteLogByIssueId(Long issueId);

	public void changeIssueLogByIssueId(IssueLogNew log);
}
