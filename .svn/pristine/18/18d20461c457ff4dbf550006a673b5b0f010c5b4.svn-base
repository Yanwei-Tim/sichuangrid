package com.tianque.fourTeams.fourTeamsIssue.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueSkiprule;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 
 * @author
 * 
 */
@Component("FourTeamsIssueSkipruleValidator")
public class FourTeamsIssueSkipruleValidator implements
		DomainValidator<FourTeamsIssueSkiprule> {

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
	public ValidateResult validate(FourTeamsIssueSkiprule domain) {
		ValidateResult validateResult = new ValidateResult();
		if (null == domain.getId()) {
			if (null == domain.getIssueTypeDomainId()) {
				validateResult.addErrorMessage("事件大类必须输入");
			}
			if (null == domain.getIssueTypeId()) {
				validateResult.addErrorMessage("事件子类必须输入");
			}
			if (null == domain.getIssueUrgentLevel()
					|| null == domain.getIssueUrgentLevel().getId()) {
				validateResult.addErrorMessage("重大紧急等级必须输入");
			}
			if (null == domain.getSubmitLevel()) {
				validateResult.addErrorMessage("上报层级必须输入");
			}
		}
		return validateResult;
	}
}