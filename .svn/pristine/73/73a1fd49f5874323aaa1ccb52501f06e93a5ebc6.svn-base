package com.tianque.partyBuilding.volunteerTeam.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.partyBuilding.volunteerTeam.domain.VolunteerTeam;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 
 * @author
 * @date 2014-02-11 15:27:44
 */
@Component("volunteerTeamValidator")
public class VolunteerTeamValidator implements DomainValidator<VolunteerTeam> {

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
		Organization organization = organizationDubboService.getSimpleOrgById(org.getId());
		if (organization == null) {
			return false;
		}
		return true;
	}

	private boolean orgIsGrid(Organization org) {
		return organizationDubboService.isGridOrganization(org.getId());
	}

	@Override
	public ValidateResult validate(VolunteerTeam domain) {
		ValidateResult validateResult = new ValidateResult();

		if (!StringUtil.isStringAvaliable(domain.getName())) {
			validateResult.addErrorMessage("组织名称不能为空");
		}
		return validateResult;
	}
}