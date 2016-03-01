package com.tianque.partyBuilding.organizations.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.partyBuilding.organizations.domain.PartyOrgMember;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 
 * @author
 * @date 2014-01-14 15:44:08
 */
@Component("partyOrgMemberValidator")
public class PartyOrgMemberValidator implements DomainValidator<PartyOrgMember> {

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
	public ValidateResult validate(PartyOrgMember domain) {
		ValidateResult validateResult = new ValidateResult();
		if (orgIsNotNull(domain.getOrganization())) {
			validateResult.addErrorMessage("组织机构不能为空");
		}
		if (validateHelper.emptyString(domain.getName())) {
			validateResult.addErrorMessage("姓名不能为空");
		}
		if (!validateHelper.emptyString(domain.getMobileNumber())
				&& validateHelper.illegalMobilePhone(domain.getMobileNumber())) {
			validateResult.addErrorMessage("请输入一个合法的手机号码");
		}
		if (!validateHelper.emptyString(domain.getTelephone())
				&& validateHelper.illegalTelephone(domain.getTelephone())) {
			validateResult.addErrorMessage("请输入一个合法的联系电话");
		}
		return validateResult;
	}
}