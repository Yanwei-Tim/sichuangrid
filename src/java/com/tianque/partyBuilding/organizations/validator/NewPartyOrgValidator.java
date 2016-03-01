package com.tianque.partyBuilding.organizations.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.partyBuilding.organizations.domain.NewPartyOrg;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 
 * @author
 * @date 2014-01-14 15:44:08
 */
@Component("newPartyOrgValidator")
public class NewPartyOrgValidator implements DomainValidator<NewPartyOrg> {

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
	public ValidateResult validate(NewPartyOrg domain) {
		ValidateResult validateResult = new ValidateResult();
		if (!orgIsNotNull(domain.getOrganization())) {
			validateResult.addErrorMessage("组织机构不能为空");
		}
		if (validateHelper.emptyString(domain.getName())) {
			validateResult.addErrorMessage("组织名称不能为空");
		}
		if (validateHelper.nullObj(domain.getType())) {
			validateResult.addErrorMessage("党组织类别不能为空");
		}
		return validateResult;
	}
}