package com.tianque.partyBuilding.organizations.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.partyBuilding.organizations.domain.RegionalBuildInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("regionalBuildInfoValidator")
public class RegionalBuildInfoValidator implements
		DomainValidator<RegionalBuildInfo> {

	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(RegionalBuildInfo domain) {
		ValidateResult validateResult = new ValidateResult();
		if (!orgIsNotNull(domain.getOrganization())) {
			validateResult.addErrorMessage("组织机构不能为空");
		}
		if (validateHelper.emptyString(domain.getServiceItem())) {
			validateResult.addErrorMessage("服务项目名称不能为空");
		}
		if (validateHelper.emptyString(domain.getAdvancementInfo())) {
			validateResult.addErrorMessage("推进情况不能为空");
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
