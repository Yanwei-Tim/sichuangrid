package com.tianque.fourTeams.fourTeamsIssue;

import java.util.HashMap;
import java.util.Map;

import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueState;

public final class FourTeamsIssueHelper {
	static Map<String, String> RELATETYPENAMES = new HashMap<String, String>();
	static {
		RELATETYPENAMES.put("v", "n");
	}

	public static String translateRelateObjectType(String key) {
		if (RELATETYPENAMES.containsKey(key)) {
			return RELATETYPENAMES.get(key);
		}
		return key;
	}

	public static FourTeamsIssueState constructIssueStateFromStep(FourTeamsIssueStep step)
			throws Exception {
		return (FourTeamsIssueState) Class.forName(step.getState()).newInstance();
	}
}
