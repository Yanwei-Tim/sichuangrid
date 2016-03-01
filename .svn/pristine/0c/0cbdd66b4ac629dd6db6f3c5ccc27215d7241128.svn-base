package com.tianque.fourTeams.fourTeamsIssue.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.domain.Organization;

public class FourTeamsIssueOperationContext {
	private Map<String, Object> context = new HashMap<String, Object>();
	private final String TARGET_ORG_KEY = FourTeamsIssueOperationContext.class.getName()
			+ "_TARGET_ORG";
	private final String CONTENT_KEY = FourTeamsIssueOperationContext.class.getName()
			+ "_CONTENT";
	private final String OPERATOR_KEY = FourTeamsIssueOperationContext.class.getName()
			+ "_OPERATOR_KEY";
	private final String TELL_ORG_KEY = FourTeamsIssueOperationContext.class.getName()
			+ "_TELL_ORG_KEY";
	private final String CURRENT_OPERATE_ORG_KEY = FourTeamsIssueOperationContext.class
			.getName()
			+ "_CURRENT_OPERATE_ORG_KEY";
	public static final String SUPERVISE_LEVEL_KEY = FourTeamsIssueOperationContext.class
			.getName()
			+ "_SUPERVISE_LEVEL_KEY";

	public FourTeamsIssueOperationContext(Organization loginedOrg, Organization target,
			String dealMsg, String operator) {
		context.put(TARGET_ORG_KEY, target);
		context.put(CONTENT_KEY, dealMsg);
		context.put(OPERATOR_KEY, operator);
		context.put(CURRENT_OPERATE_ORG_KEY, loginedOrg);
	}

	public Organization getTargetOrg() {
		return (Organization) context.get(TARGET_ORG_KEY);
	}

	public Organization getCurrentLoginedOrg() {
		return (Organization) context.get(CURRENT_OPERATE_ORG_KEY);
	}

	public String getOperationContent() {
		return (String) context.get(CONTENT_KEY);
	}

	public void addParameter(String key, Object value) {
		context.put(key, value);
	}

	public Object getParameter(String key) {
		return context.get(key);
	}

	public List<Organization> getTellOrgs() {
		return (List<Organization>) context.get(TELL_ORG_KEY);
	}

	// public String[] getOperationFiles(){
	// return (String[]) context.get(ATTACH_FILES_KEY);
	// }
	//
	public void addTellOrg(Organization org) {
		if (getTellOrgs() == null) {
			List<Organization> orgs = new ArrayList<Organization>();
			context.put(TELL_ORG_KEY, orgs);
		}
		getTellOrgs().add(org);
	}

}
