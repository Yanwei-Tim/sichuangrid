package com.tianque.validate.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.ConstructionUnit;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.sysadmin.service.OrganizationDubboService;

public class ConstructionUnitValidateImpl implements DomainValidator<ConstructionUnit> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	private boolean typeIsNotNull(PropertyDict p) {
		if (p == null || p.getId() == null) {
			return false;
		}
		return true;
	}

	private boolean validateOrgIsNotNull(Organization org) {
		if (org == null) {
			return false;
		}
		if (org != null && org.getId() == null) {
			return false;
		}
		return true;
	}

	private boolean validateOrgIsGrid(Organization org) {
		return organizationDubboService.isGridOrganization(org.getId());
	}

	@Override
	public ValidateResult validate(ConstructionUnit domain) {
		ValidateResult validateResult = new ValidateResult();
		boolean nameEmpty = validateHelper.emptyString(domain.getProjectName());
		if (nameEmpty) {
			validateResult.addErrorMessage("工程名必须输入");
		}
		boolean nameIllage = !nameEmpty
				&& validateHelper.illegalStringLength(2, 20, domain.getProjectName());
		if (nameIllage) {
			validateResult.addErrorMessage("工程名只能输入2-20个字符");
		}

		boolean orgIsNotNull = validateOrgIsNotNull(domain.getOrganization());
		if (!orgIsNotNull) {
			validateResult.addErrorMessage("所属网格必须输入");
		}
		boolean orgIsNotGrid = (orgIsNotNull && !validateOrgIsGrid(domain.getOrganization()));
		if (orgIsNotGrid) {
			validateResult.addErrorMessage("所属网格必须为片组片格");
		}
		if (!validateHelper.emptyString(domain.getDevelopmentUnit())
				&& validateHelper.illegalStringLength(2, 20, domain.getDevelopmentUnit())) {
			validateResult.addErrorMessage("开发单位只能输入2-30个字符");
		}
		if (!validateHelper.emptyString(domain.getDevelopmentContactPerson())) {
			if (validateHelper.illegalStringLength(2, 20, domain.getDevelopmentContactPerson())) {
				validateResult.addErrorMessage("开发单位联系人只能输入2-30个字符");
			}
		}
		if (!validateHelper.emptyString(domain.getDevelopmentContactPersonPhone())) {
			if (validateHelper.illegalMobilePhone(domain.getDevelopmentContactPersonPhone())) {
				validateResult.addErrorMessage("联系手机只能输入1开头的11位数字");
			}
		}
		if (validateHelper.illegalStringLength(0, 200, domain.getRemark())) {
			validateResult.addErrorMessage("备注不能输入大于200字符");
		}
		return validateResult;
	}

}
