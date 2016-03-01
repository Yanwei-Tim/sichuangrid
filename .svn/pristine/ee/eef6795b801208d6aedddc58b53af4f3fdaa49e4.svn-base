package com.tianque.issue;

import java.util.HashMap;
import java.util.Map;

import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.state.IssueState;

public final class IssueHelper {
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

	public static IssueState constructIssueStateFromStep(IssueStep step) throws Exception {
		return (IssueState) Class.forName(step.getState()).newInstance();
	}
}
