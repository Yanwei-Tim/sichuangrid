package com.tianque.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.state.IssueContext;
import com.tianque.state.IssueDealType;
import com.tianque.state.IssueStateNew;

public class DistrictAdministrativeRegionUnReadState extends IssueStateNew {
	public void read(IssueContext issueContext) {
		setStateClass(issueContext, DistrictAdministrativeRegionReadedState.class.getName());
	}

	@Override
	public List<Long> getConDo() {
		List list = new ArrayList();
		list.add(IssueDealType.READ);
		return list;
	}
}
