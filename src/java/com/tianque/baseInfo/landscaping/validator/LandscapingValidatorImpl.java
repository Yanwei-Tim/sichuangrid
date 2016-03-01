package com.tianque.baseInfo.landscaping.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.landscaping.domain.Landscaping;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("landscapingValidator")
public class LandscapingValidatorImpl implements DomainValidator<Landscaping> {
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private boolean validateOrgIfExsist(Organization org) {
		Organization organization = organizationDubboService.getSimpleOrgById(org.getId());
		if (organization == null) {
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

	@Autowired
	private ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(Landscaping domain) {
		ValidateResult validateResult = new ValidateResult();
		if (validateHelper.emptyString(domain.getObjName())) {
			validateResult.addErrorMessage("部件名称必须输入");
		}
		if (!validateHelper.emptyString(domain.getObjName())
				&& validateHelper.illegalStringLength(2, 20, domain.getObjName())) {
			validateResult.addErrorMessage("部件名称必须输入只能输入2-20个字符");
		}
		if (validateHelper.emptyString(domain.getObjNum())) {
			validateResult.addErrorMessage("部件名称必须输入");
		}

		if (!validateOrgIsNotNull(domain.getOrganization())) {
			validateResult.addErrorMessage("所属网格必须输入");
		}
		if (validateOrgIsNotNull(domain.getOrganization())
				&& !validateOrgIfExsist(domain.getOrganization())) {
			validateResult.addErrorMessage("找不到指定网格");
		}
		if (validateOrgIsNotNull(domain.getOrganization())
				&& !validateOrgIsGrid(domain.getOrganization())) {
			validateResult.addErrorMessage("所属网格必须为片组片格");
		}
		if (validateHelper.emptyString(domain.getDeptCode())) {
			validateResult.addErrorMessage("主管部门代码必须输入");
		}

		if (validateHelper.emptyString(domain.getDeptName())) {
			validateResult.addErrorMessage("主管部门名称必须输入");
		}

		if (!validateHelper.emptyString(domain.getRemark())
				&& validateHelper.illegalStringLength(0, 300, domain.getRemark())) {
			validateResult.addErrorMessage("备注最多只能输入300个字符");
		}
		return validateResult;
	}

}
