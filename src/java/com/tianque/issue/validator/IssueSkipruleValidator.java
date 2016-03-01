package com.tianque.issue.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.issue.domain.IssueSkiprule;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 
 * @author
 * @date 2013-11-22 14:51:42
 */
@Component("IssueSkipruleValidator")
public class IssueSkipruleValidator implements DomainValidator<IssueSkiprule> {

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

	private boolean orgIsNotNull(Organization org) {
		if (org == null) {
			return false;
		}
		if (org != null && org.getId() == null) {
			return false;
		}
		return true;
	}

	private boolean orgIfExsist(Organization org) {
		Organization organization = organizationDubboService.getSimpleOrgById(org
				.getId());
		if (organization == null) {
			return false;
		}
		return true;
	}

	private boolean orgIsGrid(Organization org) {
		return organizationDubboService.isGridOrganization(org.getId());
	}

	@Override
	public ValidateResult validate(IssueSkiprule domain) {
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