package com.tianque.fourTeams.fourTeamsIssue.service;

import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;

public interface FourTeamsIssueSerialNumberGenerator {
	String nextValue(FourTeamsIssueNew issue);
}
