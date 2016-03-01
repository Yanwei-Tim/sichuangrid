package com.tianque.state;

import java.util.List;

import com.tianque.issue.domain.IssueLogNew;

public abstract class IssueStateNew {

	public abstract List<Long> getConDo();

	public void concept(IssueContext issueContext) {
	}

	public void complete(IssueContext issueContext) {
	}

	public void sendback(IssueContext issueContext) {
	}

	public void submitForward(IssueContext issueContext) {
	}

	public void assign(IssueContext issueContext) {
	}

	public void comment(IssueContext issueContext) {
	}

	public void tell(IssueContext issueContext) {
	}

	public void read(IssueContext issueContext) {
	}

	protected void setStateClass(IssueContext issueContext, String name) {
		IssueLogNew issueLog = issueContext.getIssueLogNew();
		issueLog.setStateClass(name);
		issueContext.setVale(IssueContext.ISSUE_LOG_NEW_KEY, issueLog);
	}
}
