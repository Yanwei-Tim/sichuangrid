package com.tianque.state;

import java.util.HashMap;
import java.util.Map;

import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;

public class IssueContext {
	public static final String ISSUE_KEY = IssueContext.class.getName() + "_issue";
	public static final String APPCONTEXT_KEY = IssueContext.class.getName() + "_appcontext";
	public static final String DEAL_ORG_LEVEL_KEY = IssueContext.class.getName() + "_dealOrgLevel";
	public static final String DEAL_ORG_TYPE_KEY = IssueContext.class.getName() + "_dealOrgType";
	public static final String TELL_TARGET_USER_ID_LIST_KEY = IssueContext.class.getName()
			+ "_tellTargetUserIdList";
	public static final String ISSUE_STATU_KEY = IssueContext.class.getName() + "_issue_statu_key";
	public static final String RETURN_ISSUE_LOGS = IssueContext.class.getName() + "returnIssueLogs";

	public static final String FOR_ISSUE_LOG_KEY = IssueContext.class.getName() + "_forIssueLog";
	public static final String ISSUE_LOG_NEW_KEY = IssueContext.class.getName() + "_issueLogNew";
	public static final String TELL_TARGET_ORG_ID_LIST_KEY = IssueContext.class.getName()
			+ "_tellTargetOrgIdList";
	public static final String MAX_STEP_INDEX_KEY = IssueContext.class.getName() + "maxStepIndex";

	private Map<String, Object> map = new HashMap<String, Object>();

	public Object getValue(String key) {
		return map.get(key);
	}

	public void setVale(String key, Object value) {
		map.put(key, value);
	}

	public IssueNew getIssue() {
		return (IssueNew) map.get(ISSUE_KEY);
	}

	public IssueLogNew getIssueLogNew() {
		return (IssueLogNew) map.get(ISSUE_LOG_NEW_KEY);
	}

	public IssueLogNew getForIssueLog() {
		return (IssueLogNew) map.get(FOR_ISSUE_LOG_KEY);
	}
}
