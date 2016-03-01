package com.tianque.fourTeams.fourTeamsIssue.validator.impl;

public abstract class FourTeamsAbstractValidator {

	protected boolean legalTitleString(String value) {
		return value.replaceAll(
				"[\u4e00-\u9fa5]*\\w*-*_*\\s*）*\"*'*<*>*《*》*（*\\(*\\)*", "")
				.length() == 0;
	}

	protected boolean legalAddressString(String value) {
		return value.replaceAll("[\u4e00-\u9fa5]*\\w*-*\\s*）*#*（*\\(*\\)*", "")
				.length() == 0;
	}

	protected boolean legalNamesString(String value) {
		return value.replaceAll("[\u4e00-\u9fa5]*\\w*,*\\s*，*、*", "").length() == 0;
	}
}
