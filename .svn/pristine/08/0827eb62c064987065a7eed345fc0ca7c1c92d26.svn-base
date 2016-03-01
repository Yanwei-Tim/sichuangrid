package com.tianque.fourTeams.state;

import java.util.List;

import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;

public abstract class FourTeamsIssueStateNew {

	public abstract List<Long> getConDo();

	public void concept(FourTeamsIssueContext issueContext) {
	}

	public void complete(FourTeamsIssueContext issueContext) {
	}

	public void sendback(FourTeamsIssueContext issueContext) {
	}

	public void submitForward(FourTeamsIssueContext issueContext) {
	}

	public void assign(FourTeamsIssueContext issueContext) {
	}

	public void comment(FourTeamsIssueContext issueContext) {
	}

	public void tell(FourTeamsIssueContext issueContext) {
	}

	public void read(FourTeamsIssueContext issueContext) {
	}

	protected void setStateClass(FourTeamsIssueContext issueContext, String name) {
		FourTeamsIssueLogNew issueLog = issueContext.getIssueLogNew();
		issueLog.setStateClass(name);
		issueContext.setVale(FourTeamsIssueContext.ISSUE_LOG_NEW_KEY, issueLog);
	}
}
