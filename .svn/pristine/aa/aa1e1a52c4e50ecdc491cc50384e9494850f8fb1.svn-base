package com.tianque.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.state.IssueContext;
import com.tianque.state.IssueDealType;
import com.tianque.state.IssueStateNew;

public class DistrictFunctionalOrgUnConceptStateNew extends IssueStateNew {
	public void concept(IssueContext issueContext) {
		setStateClass(issueContext, DistrictFunctionalOrgProcessingStateNew.class.getName());
	}

	@Override
	public List<Long> getConDo() {
		List list = new ArrayList();
		list.add(IssueDealType.CONCEPT);
		return list;
	}
}
