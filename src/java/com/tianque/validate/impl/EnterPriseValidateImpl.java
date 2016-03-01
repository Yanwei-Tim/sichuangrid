package com.tianque.validate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Enterprise;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Repository("enterPriseValidator")
public class EnterPriseValidateImpl implements DomainValidator<Enterprise> {
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

	// private boolean typeIsNotNull(PropertyDict p) {
	// if (p == null || p.getId() == null) {
	// return false;
	// }
	// return true;
	// }

	private boolean validateOrgIsNotNull(Organization org) {
		if (org == null) {
			return false;
		}
		if (org != null && org.getId() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ValidateResult validate(Enterprise domain) {
		ValidateResult validateResult = new ValidateResult();
		boolean nameEmpty = validateHelper.emptyString(domain.getName());
		if (nameEmpty) {
			validateResult.addErrorMessage(getColumNo("name") + "场所名称必须输入");
		} else {
			if (!validateHelper.isConSpeCharacters(domain.getName())) {
				validateResult.addErrorMessage(getColumNo("name") + "场所名称不能包含特殊字符");
			}
		}

		if ("securityKey".equals(domain.getKeyType())
				|| "safetyProductionKey".equals(domain.getKeyType())
				|| "fireSafetyKey".equals(domain.getKeyType())) {
			if (domain.getType() == null) {
				validateResult.addErrorMessage(getColumNo("type") + "企业类型必须输入");
			}
		}

		// if (domain.getType() == null) {
		// validateResult.addErrorMessage(getColumNo("type") + "企业类型必须输入");
		// }
		if (!nameEmpty && validateHelper.illegalStringLength(2, 50, domain.getName())) {
			validateResult.addErrorMessage(getColumNo("name") + "场所名称只能输入2-50个字符");
		}
		boolean orgIsNotNull = validateOrgIsNotNull(domain.getOrganization());
		if (!orgIsNotNull) {
			validateResult.addErrorMessage(getColumNo("organization") + "所属网格必须输入");
		}
		if (orgIsNotNull
				&& !organizationDubboService.isGridOrganization(domain.getOrganization().getId())) {
			validateResult.addErrorMessage(getColumNo("organization") + "所属网格必须为片组片格");
		}

		boolean legalPersonEmpty = validateHelper.emptyString(domain.getLegalPerson());
		if (legalPersonEmpty) {
			validateResult.addErrorMessage(getColumNo("legalPerson") + "负责人代表必须输入");
		}
		if (!legalPersonEmpty && validateHelper.illegalStringLength(2, 20, domain.getLegalPerson())) {
			validateResult.addErrorMessage(getColumNo("legalPerson") + "负责人只能输入2-20个字符");
		}
		if (!legalPersonEmpty && !validateHelper.isConSpeCharacters(domain.getLegalPerson())) {
			validateResult.addErrorMessage(getColumNo("legalPerson") + "负责人不能包含特殊字符");
		}

		boolean addressEmpty = validateHelper.emptyString(domain.getAddress());
		if (addressEmpty) {
			validateResult.addErrorMessage(getColumNo("address") + "场所地址必须输入");
		}
		if (!nameEmpty && validateHelper.illegalStringLength(2, 50, domain.getAddress())) {
			validateResult.addErrorMessage(getColumNo("address") + "场所地址只能输入2-50个字符");
		}
		if (!validateHelper.emptyString(domain.getBusinessLicense())) {
			if (validateHelper.illegalStringLength(6, 20, domain.getBusinessLicense())) {
				validateResult.addErrorMessage(getColumNo("businessLicense") + "工商执照号码只能输入6-20个字符");
			}
		}
		if (domain.getRegisteredCapital() != null
				&& domain.getRegisteredCapital().longValue() > 999999999) {
			validateResult.addErrorMessage(getColumNo("registeredCapital") + "注册资金不能大于999999999万元");
		}
		if (domain.getArea() != null && domain.getArea().longValue() > 999999999) {
			validateResult.addErrorMessage(getColumNo("area") + "面积不能大于999999999平方米");
		}
		if (domain.getEmployeeAmount() != null && domain.getEmployeeAmount() > 999999999) {
			validateResult.addErrorMessage(getColumNo("employeeAmount") + "员工数不能大于999999999人");
		}
		if (domain.getPartyMemberAmount() != null && domain.getPartyMemberAmount() > 999999999) {
			validateResult.addErrorMessage(getColumNo("partyMemberAmount") + "党员数不能大于999999999人");
		}
		if (domain.getEmployeeAmount() != null && domain.getEmployeeAmount() <= 999999999
				&& domain.getPartyMemberAmount() != null
				&& domain.getPartyMemberAmount() <= 999999999
				&& domain.getEmployeeAmount() < domain.getPartyMemberAmount()) {
			validateResult.addErrorMessage(getColumNo("partyMemberAmount") + "党员数不能大于员工数");
		}
		if (!validateHelper.emptyString(domain.getEnterpriseTelephone())
				&& validateHelper.illegalTelephone(domain.getEnterpriseTelephone())) {
			validateResult.addErrorMessage(getColumNo("enterpriseTelephone") + "企业联系电话输入不正确");
		}
		if (!validateHelper.emptyString(domain.getFax())
				&& validateHelper.illegalTelephone(domain.getFax())) {
			validateResult.addErrorMessage(getColumNo("fax") + "企业传真号码输入不正确");
		}
		if (!validateHelper.emptyString(domain.getTelephone())
				&& validateHelper.illegalTelephone(domain.getTelephone())) {
			validateResult.addErrorMessage(getColumNo("telephone") + "法人联系电话输入不正确");
		}
		if (!validateHelper.emptyString(domain.getMobileNumber())
				&& validateHelper.illegalMobilePhone(domain.getMobileNumber())) {
			validateResult.addErrorMessage(getColumNo("mobileNumber") + "法人手机号码输入不正确");
		}
		if (validateHelper.illegalStringLength(0, 100, domain.getHiddenCases())) {
			validateResult.addErrorMessage(getColumNo("hiddenCases") + "隐患情况不能输入大于100字符");
		}
		if (validateHelper.illegalStringLength(0, 100, domain.getHiddenRectification())) {
			validateResult.addErrorMessage(getColumNo("hiddenRectification") + "隐患整改情况不能输入大于100字符");
		}
		if (validateHelper.illegalStringLength(0, 200, domain.getRemark())) {
			validateResult.addErrorMessage(getColumNo("备注") + "备注不能输入大于200字符");
		}
		return validateResult;
	}

	public String getColumNo(String key) {
		return ExcelImportHelper.getColumNo(key);
	}
}
