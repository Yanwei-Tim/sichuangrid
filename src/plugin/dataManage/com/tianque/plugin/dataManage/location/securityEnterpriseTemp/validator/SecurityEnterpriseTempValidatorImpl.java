package com.tianque.plugin.dataManage.location.securityEnterpriseTemp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.location.securityEnterpriseTemp.domain.SecurityEnterpriseTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Repository("securityEnterpriseTempValidator")
public class SecurityEnterpriseTempValidatorImpl implements
		DomainValidatorTemp<SecurityEnterpriseTemp> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	@Override
	public ValidateResult validate(SecurityEnterpriseTemp domain) {
		ValidateResult validateResult = new ValidateResult();
		boolean nameEmpty = validateHelper.emptyString(domain.getName());
		if (nameEmpty) {
			validateResult.addErrorMessage(getColumNo("name") + "场所名称必须输入");
		} else {
			if (!validateHelper.isConSpeCharacters(domain.getName())) {
				validateResult.addErrorMessage(getColumNo("name")
						+ "场所名称不能包含特殊字符");
			}
		}
		if (domain.getType() == null) {
			validateResult.addErrorMessage(getColumNo("type") + "场所类型必须输入");
		}
		if (!nameEmpty
				&& validateHelper.illegalStringLength(2, 50, domain.getName())) {
			validateResult.addErrorMessage(getColumNo("name")
					+ "场所名称只能输入2-50个字符");
		}

		boolean legalPersonEmpty = validateHelper.emptyString(domain
				.getManager());
		if (legalPersonEmpty) {
			validateResult.addErrorMessage(getColumNo("manager") + "负责人代表必须输入");
		}
		if (!legalPersonEmpty
				&& validateHelper.illegalStringLength(2, 20,
						domain.getManager())) {
			validateResult.addErrorMessage(getColumNo("manager")
					+ "负责人只能输入2-20个字符");
		}
		if (!legalPersonEmpty
				&& !validateHelper.isConSpeCharacters(domain.getManager())) {
			validateResult.addErrorMessage(getColumNo("manager")
					+ "负责人不能包含特殊字符");
		}

		boolean addressEmpty = validateHelper.emptyString(domain.getAddress());
		if (addressEmpty) {
			validateResult.addErrorMessage(getColumNo("address") + "场所地址必须输入");
		}
		if (!nameEmpty
				&& validateHelper.illegalStringLength(2, 50,
						domain.getAddress())) {
			validateResult.addErrorMessage(getColumNo("address")
					+ "场所地址只能输入2-50个字符");
		}
		if (!validateHelper.emptyString(domain.getTelephone())
				&& validateHelper.illegalTelephone(domain.getTelephone())) {
			validateResult.addErrorMessage(getColumNo("telephone")
					+ "联系电话输入不正确");
		}
		if (!validateHelper.emptyString(domain.getMobileNumber())
				&& validateHelper.illegalMobilePhone(domain.getMobileNumber())) {
			validateResult.addErrorMessage(getColumNo("mobileNumber")
					+ "手机号码输入不正确");
		}
		if (validateHelper.illegalStringLength(0, 100, domain.getHiddenCases())) {
			validateResult.addErrorMessage(getColumNo("hiddenCases")
					+ "隐患情况不能输入大于100字符");
		}
		if (validateHelper.illegalStringLength(0, 100,
				domain.getHiddenRectification())) {
			validateResult.addErrorMessage(getColumNo("hiddenRectification")
					+ "隐患整改情况不能输入大于100字符");
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
