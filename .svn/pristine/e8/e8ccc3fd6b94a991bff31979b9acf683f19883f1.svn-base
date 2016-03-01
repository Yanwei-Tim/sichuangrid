package com.tianque.plugin.dataManage.location.safetyProductionTemp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.location.safetyProductionTemp.domain.SafetyProductionTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Repository("safetyProductionTempValidator")
public class SafetyProductionTempValidatorImpl implements
		DomainValidatorTemp<SafetyProductionTemp> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	@Override
	public ValidateResult validate(SafetyProductionTemp domain) {
		ValidateResult validateResult = new ValidateResult();
		boolean nameEmpty = validateHelper.emptyString(domain.getName());
		if (nameEmpty) {
			validateResult.addErrorMessage(getColumNo("name") + "企业名称必须输入");
		} else {
			if (!validateHelper.isConSpeCharacters(domain.getName())) {
				validateResult.addErrorMessage(getColumNo("name")
						+ "企业名称不能包含特殊字符");
			}
		}
		if (domain.getType() == null) {
			validateResult.addErrorMessage(getColumNo("type") + "企业类型必须输入");
		}
		if (!nameEmpty
				&& validateHelper.illegalStringLength(2, 50, domain.getName())) {
			validateResult.addErrorMessage(getColumNo("name")
					+ "企业名称只能输入2-50个字符");
		}

		boolean legalPersonEmpty = validateHelper.emptyString(domain
				.getManager());
		if (legalPersonEmpty) {
			validateResult.addErrorMessage(getColumNo("manager") + "法人代表必须输入");
		}
		if (!legalPersonEmpty
				&& validateHelper.illegalStringLength(2, 20,
						domain.getManager())) {
			validateResult.addErrorMessage(getColumNo("manager")
					+ "法人代表只能输入2-20个字符");
		}
		if (!legalPersonEmpty
				&& !validateHelper.isConSpeCharacters(domain.getManager())) {
			validateResult.addErrorMessage(getColumNo("manager")
					+ "法人代表不能包含特殊字符");
		}

		boolean addressEmpty = validateHelper.emptyString(domain.getAddress());
		if (addressEmpty) {
			validateResult.addErrorMessage(getColumNo("address") + "企业地址必须输入");
		}
		if (!nameEmpty
				&& validateHelper.illegalStringLength(2, 50,
						domain.getAddress())) {
			validateResult.addErrorMessage(getColumNo("address")
					+ "企业地址只能输入2-50个字符");
		}
		if (!validateHelper.emptyString(domain.getBusinessLicense())) {
			if (validateHelper.illegalStringLength(6, 20,
					domain.getBusinessLicense())) {
				validateResult.addErrorMessage(getColumNo("businessLicense")
						+ "工商执照号码只能输入6-20个字符");
			}
		}
		if (domain.getRegisteredCapital() != null
				&& domain.getRegisteredCapital().longValue() >= 1000000000L) {
			validateResult.addErrorMessage(getColumNo("registeredCapital")
					+ "注册资金不能大于999999999万元");
		}
		if (domain.getArea() != null
				&& domain.getArea().longValue() >= 1000000000L) {
			validateResult.addErrorMessage(getColumNo("area")
					+ "面积不能大于999999999平方米");
		}
		if (domain.getEmployeeAmount() != null
				&& validateHelper.illegalNumberZZ(domain.getEmployeeAmount()
						.toString())
				&& validateHelper.illegalNumericRange(domain
						.getEmployeeAmount().toString(), 1, 999999999)) {
			validateResult.addErrorMessage(getColumNo("employeeAmount")
					+ "员工数只能输入不大于999999999的正整数");
		}
		if (domain.getPartyMemberAmount() != null
				&& validateHelper.illegalNumberZZ(domain.getPartyMemberAmount()
						.toString())
				&& validateHelper.illegalNumericRange(domain
						.getPartyMemberAmount().toString(), 1, 999999999)) {
			validateResult.addErrorMessage(getColumNo("partyMemberAmount")
					+ "党员数只能输入不大于999999999的正整数");
		}
		if (domain.getEmployeeAmount() != null
				&& domain.getEmployeeAmount() <= 999999999
				&& domain.getPartyMemberAmount() != null
				&& domain.getPartyMemberAmount() <= 999999999
				&& domain.getEmployeeAmount() < domain.getPartyMemberAmount()) {
			validateResult.addErrorMessage(getColumNo("partyMemberAmount")
					+ "党员数不能大于员工数");
		}
		if (!validateHelper.emptyString(domain.getEnterpriseTelephone())
				&& validateHelper.illegalTelephone(domain
						.getEnterpriseTelephone())) {
			validateResult.addErrorMessage(getColumNo("enterpriseTelephone")
					+ "企业联系电话输入不正确");
		}
		if (!validateHelper.emptyString(domain.getFax())
				&& validateHelper.illegalTelephone(domain.getFax())) {
			validateResult.addErrorMessage(getColumNo("fax") + "企业传真号码输入不正确");
		}
		if (!validateHelper.emptyString(domain.getTelephone())
				&& validateHelper.illegalTelephone(domain.getTelephone())) {
			validateResult.addErrorMessage(getColumNo("telephone")
					+ "法人联系电话输入不正确");
		}
		if (!validateHelper.emptyString(domain.getMobileNumber())
				&& validateHelper.illegalMobilePhone(domain.getMobileNumber())) {
			validateResult.addErrorMessage(getColumNo("mobileNumber")
					+ "法人手机号码输入不正确");
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
			validateResult.addErrorMessage(getColumNo("remark")
					+ "备注不能输入大于300字符");
		}
		return validateResult;
	}

	public String getColumNo(String key) {
		return ExcelImportHelper.getColumNo(key);
	}

}
