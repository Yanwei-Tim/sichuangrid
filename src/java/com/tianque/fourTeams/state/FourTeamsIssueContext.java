package com.tianque.fourTeams.state;

import java.util.HashMap;
import java.util.Map;

import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;

public class FourTeamsIssueContext {
	public static final String ISSUE_KEY = FourTeamsIssueContext.class.getName()
			+ "_issue";
	public static final String APPCONTEXT_KEY = FourTeamsIssueContext.class.getName()
			+ "_appcontext";
	public static final String DEAL_ORG_LEVEL_KEY = FourTeamsIssueContext.class
			.getName()
			+ "_dealOrgLevel";
	public static final String DEAL_ORG_TYPE_KEY = FourTeamsIssueContext.class.getName()
			+ "_dealOrgType";
	public static final String TELL_TARGET_USER_ID_LIST_KEY = FourTeamsIssueContext.class
			.getName()
			+ "_tellTargetUserIdList";
	public static final String ISSUE_STATU_KEY = FourTeamsIssueContext.class.getName()
			+ "_issue_statu_key";
	public static final String RETURN_ISSUE_LOGS = FourTeamsIssueContext.class.getName()
			+ "returnIssueLogs";

	public static final String FOR_ISSUE_LOG_KEY = FourTeamsIssueContext.class.getName()
			+ "_forIssueLog";
	public static final String ISSUE_LOG_NEW_KEY = FourTeamsIssueContext.class.getName()
			+ "_issueLogNew";
	public static final String TELL_TARGET_ORG_ID_LIST_KEY = FourTeamsIssueContext.class
			.getName()
			+ "_tellTargetOrgIdList";
	public static final String MAX_STEP_INDEX_KEY = FourTeamsIssueContext.class
			.getName()
			+ "maxStepIndex";

	private Map<String, Object> map = new HashMap<String, Object>();

	public Object getValue(String key) {
		return map.get(key);
	}

	public void setVale(String key, Object value) {
		map.put(key, value);
	}

	public FourTeamsIssueNew getIssue() {
		return (FourTeamsIssueNew) map.get(ISSUE_KEY);
	}

	public FourTeamsIssueLogNew getIssueLogNew() {
		return (FourTeamsIssueLogNew) map.get(ISSUE_LOG_NEW_KEY);
	}

	public FourTeamsIssueLogNew getForIssueLog() {
		return (FourTeamsIssueLogNew) map.get(FOR_ISSUE_LOG_KEY);
	}
}
