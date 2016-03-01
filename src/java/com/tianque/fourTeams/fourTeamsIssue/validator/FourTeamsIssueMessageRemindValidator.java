package com.tianque.fourTeams.fourTeamsIssue.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueMessageRemind;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 
 * @author
 * 
 */
@Component("FourTeamsIssueMessageRemindValidator")
public class FourTeamsIssueMessageRemindValidator implements
		DomainValidator<FourTeamsIssueMessageRemind> {

	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	@Override
	public ValidateResult validate(FourTeamsIssueMessageRemind domain) {
		ValidateResult validateResult = new ValidateResult();

		return validateResult;
	}
}