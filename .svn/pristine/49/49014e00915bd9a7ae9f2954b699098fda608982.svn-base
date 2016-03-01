package com.tianque.partyBuilding.organizations.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.partyBuilding.organizations.domain.RegionalPartyMember;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("regionalPartyMemberValidator")
public class RegionalPartyMemberValidator implements
		DomainValidator<RegionalPartyMember> {

	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(RegionalPartyMember domain) {
		ValidateResult validateResult = new ValidateResult();
		if (!orgIsNotNull(domain.getOrganization())) {
			validateResult.addErrorMessage("组织机构不能为空");
		}
		if (validateHelper.emptyString(domain.getName())) {
			validateResult.addErrorMessage("姓名不能为空");
		}
		if (validateHelper.nullObj(domain.getGender())
				|| validateHelper.nullObj(domain.getGender().getId())) {
			validateResult.addErrorMessage("性别不能为空");
		}
		if (validateHelper.nullObj(domain.getPartyOrgDuty())
				|| validateHelper.nullObj(domain.getPartyOrgDuty().getId())) {
			validateResult.addErrorMessage("区域党委职务不能为空");
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
}
