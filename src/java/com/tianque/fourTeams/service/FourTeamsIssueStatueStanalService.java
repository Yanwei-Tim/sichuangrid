package com.tianque.fourTeams.service;

import com.tianque.domain.IssueStatueStanal;

public interface FourTeamsIssueStatueStanalService {

	public IssueStatueStanal addIssueStatueStanal(IssueStatueStanal issueStatueStanal);

	public IssueStatueStanal getIssueStatueStanalStatCount(IssueStatueStanal issueStatueStanal);

	public IssueStatueStanal getIssueStatueStanalStatCountByYearAndMonth(Long orgId,
			Integer sysBeginYear, Integer month);

}
