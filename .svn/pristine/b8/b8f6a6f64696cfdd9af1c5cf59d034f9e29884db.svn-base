package com.tianque.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.domain.property.OrganizationLevel;
import com.tianque.state.IssueContext;
import com.tianque.state.IssueDealType;
import com.tianque.state.IssueStateNew;

public class ViliageProcessingStateNew extends IssueStateNew {
	public void submitForward(IssueContext issueContext) {
		setStateClass(issueContext, TownUnConceptStateNew.class.getName());
	}

	public void comment(IssueContext issueContext) {
		setStateClass(issueContext, ViliageProcessingStateNew.class.getName());
	}

	public void complete(IssueContext issueContext) {
		setStateClass(issueContext, ViliageCompleteStateNew.class.getName());
	}

	public void sendback(IssueContext issueContext) {
		if (issueContext.getIssueLogNew().getTargeOrg().getOrgLevel().getInternalId() == OrganizationLevel.TOWN) {
			setStateClass(issueContext, TownProcessingStateNew.class.getName());
		} else if (issueContext.getIssueLogNew().getTargeOrg().getOrgLevel().getInternalId() == OrganizationLevel.GRID) {
			setStateClass(issueContext, GridProcessingStateNew.class.getName());
		}
	}

	@Override
	public List<Long> getConDo() {
		List list = new ArrayList();
		list.add(IssueDealType.SUBMIT_FORWARD);
		list.add(IssueDealType.SUBMIT_FORWARD_ADMIN_TO_ADMIN);
		list.add(IssueDealType.COMMENT);
		list.add(IssueDealType.COMPLETE);
		list.add(IssueDealType.SEND_BACK);
		return list;
	}
}
