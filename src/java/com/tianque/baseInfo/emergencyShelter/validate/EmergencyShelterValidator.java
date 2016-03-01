package com.tianque.baseInfo.emergencyShelter.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.emergencyShelter.domain.EmergencyShelter;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("emergencyShelterValidator")
public class EmergencyShelterValidator implements DomainValidator<EmergencyShelter> {
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
	public ValidateResult validate(EmergencyShelter domain) {
		ValidateResult validateResult = new ValidateResult();

		if (!orgIsNotNull(domain.getOrganization())) {
			validateResult.addErrorMessage("所属网格必须输入");
		}
		if (orgIsNotNull(domain.getOrganization()) && !orgIfExsist(domain.getOrganization())) {
			validateResult.addErrorMessage("找不到指定网格");
		}
		if (orgIsNotNull(domain.getOrganization()) && !orgIsGrid(domain.getOrganization())) {

			validateResult.addErrorMessage("所属网格必须为片组片格");
		}
		if (validateHelper.emptyString(domain.getName()) || "".equals(domain.getName())) {
			validateResult.addErrorMessage("场所名称不能为空");
		}
		if (!validateHelper.emptyString(domain.getName())
				&& validateHelper.illegalStringLength(0, 50, domain.getName())) {
			validateResult.addErrorMessage("单位名称不能大于50字符");
		}
		if (!validateHelper.emptyString(domain.getRemark())
				&& validateHelper.illegalStringLength(0, 300, domain.getRemark())) {
			validateResult.addErrorMessage("备注最多只能输入300个字符");
		}
		return validateResult;
	}
}
