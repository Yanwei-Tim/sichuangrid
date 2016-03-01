package com.tianque.plugin.dataManage.location.actualCompanyTemp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.location.actualCompanyTemp.domain.ActualCompanyTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;

@Component("actualCompanyTempValidator")
public class ActualCompanyTempValidatorImpl implements DomainValidatorTemp<ActualCompanyTemp> {

	@Autowired
	private ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(ActualCompanyTemp domain) {
		ValidateResult validateResult = new ValidateResult();

		if (validateHelper.emptyString(domain.getName())) {
			validateResult.addErrorMessage(getColumNo("name") + "单位名称不能为空");
		}
		if (!validateHelper.emptyString(domain.getName())
				&& validateHelper.illegalStringLength(0, 50, domain.getName())) {
			validateResult.addErrorMessage(getColumNo("name") + "单位名称不能大于50字符");
		}
		if (!validateHelper.emptyString(domain.getName())
				&& !validateHelper.isConSpeCharacters(domain.getName())) {
			validateResult.addErrorMessage(getColumNo("name") + "单位名称不能有特殊字符");
		}
		if (validateHelper.emptyString(domain.getAddress()) || "".equals(domain.getAddress())) {
			validateResult.addErrorMessage(getColumNo("address") + "单位地址不能为空");
		}
		if (!validateHelper.emptyString(domain.getAddress())
				&& validateHelper.illegalStringLength(0, 50, domain.getAddress())) {
			validateResult.addErrorMessage(getColumNo("address") + "单位地址不能大于50字符");
		}

		boolean legalPersonEmpty = validateHelper.emptyString(domain.getManager());
		if (!legalPersonEmpty && validateHelper.illegalStringLength(2, 20, domain.getManager())) {
			validateResult.addErrorMessage(getColumNo("manager") + "法人代表只能输入2-20个字符");
		}
		if (!legalPersonEmpty && !validateHelper.isConSpeCharacters(domain.getManager())) {
			validateResult.addErrorMessage(getColumNo("manager") + "法人代表不能包含特殊字符");
		}
		if (validateHelper.emptyString(domain.getBusinessLicenseNo())) {
			validateResult.addErrorMessage(getColumNo("businessLicenseNo") + "营业执照号码不能为空");
		}
		if (!validateHelper.emptyString(domain.getBusinessLicenseNo())
				&& validateHelper.illegalStringLength(0, 50, domain.getBusinessLicenseNo())) {
			validateResult.addErrorMessage(getColumNo("businessLicenseNo") + "营业执照号码不能大于50字符");
		}
		if (validateHelper.emptyString(domain.getOrgCode()) || "".equals(domain.getOrgCode())) {
			validateResult.addErrorMessage(getColumNo("orgCode") + "组织机构代码不能为空");
		}
		if (!validateHelper.emptyString(domain.getOrgCode())
				&& validateHelper.illegalStringLength(0, 50, domain.getOrgCode())) {
			validateResult.addErrorMessage(getColumNo("orgCode") + "组织机构代码不能大于50字符");
		}
		if (!validateHelper.emptyString(domain.getTelephone())
				&& validateHelper.illegalTelephone(domain.getTelephone())) {
			validateResult.addErrorMessage(getColumNo("telephone") + "固定电话只能输入数字和-");
		}
		if (!validateHelper.emptyString(domain.getTelephone())
				&& validateHelper.illegalStringLength(0, 20, domain.getTelephone())) {
			validateResult.addErrorMessage(getColumNo("telephone") + "固定电话不能大于20字符");
		}
		if (!validateHelper.emptyString(domain.getFax())
				&& validateHelper.illegalTelephone(domain.getFax())) {
			validateResult.addErrorMessage(getColumNo("fax") + "单位传真只能输入数字和-");
		}
		if (!validateHelper.emptyString(domain.getFax())
				&& validateHelper.illegalStringLength(0, 20, domain.getFax())) {
			validateResult.addErrorMessage(getColumNo("fax") + "单位传真不能大于20字符");
		}
		if (!validateHelper.nullObj(domain.getRegisteredCapital())
				&& validateHelper.illegalNumericRange(domain.getRegisteredCapital().toString(), 1,
						999999.9999)) {
			validateResult.addErrorMessage(getColumNo("registeredCapital")
					+ "注册资本(万元)只能输入1到999999.9999之间的数");
		}
		if (!validateHelper.emptyString(domain.getBusinessScope())
				&& validateHelper.illegalStringLength(0, 50, domain.getBusinessScope())) {
			validateResult.addErrorMessage(getColumNo("businessScope") + "经营范围不能大于50字符");
		}
		if (!validateHelper.emptyString(domain.getRegistrationAddress())
				&& validateHelper.illegalStringLength(0, 50, domain.getRegistrationAddress())) {
			validateResult.addErrorMessage(getColumNo("registrationAddress") + "注册地址不能大于50字符");
		}
		if (!validateHelper.nullObj(domain.getEmployeesNum())
				&& validateHelper.illegalNumberZZ(domain.getEmployeesNum().toString())
				&& validateHelper.illegalStringLength(1, 9, domain.getEmployeesNum().toString())) {
			validateResult
					.addErrorMessage(getColumNo("employeesNum") + "从业人数只能输入1-999999999之间的正整数");
		}
		if (!validateHelper.emptyString(domain.getSecurityChief())
				&& !validateHelper.isConSpeCharacters(domain.getSecurityChief())) {
			validateResult.addErrorMessage(getColumNo("securityChief") + "治安负责人不能有特殊字符");
		}
		if (!validateHelper.emptyString(domain.getSecurityChief())
				&& validateHelper.illegalStringLength(0, 50, domain.getSecurityChief())) {
			validateResult.addErrorMessage(getColumNo("securityChief") + "治安负责人不能大于50个字符");
		}
		if (!validateHelper.emptyString(domain.getSupervisoryDepartment())
				&& validateHelper.illegalStringLength(0, 30, domain.getSupervisoryDepartment())) {
			validateResult.addErrorMessage(getColumNo("supervisoryDepartment") + "管理部门不能大于30个字符");
		}
		if (!validateHelper.emptyString(domain.getCompetentDepartment())
				&& validateHelper.illegalStringLength(0, 30, domain.getCompetentDepartment())) {
			validateResult.addErrorMessage(getColumNo("competentDepartment") + "主管部门不能大于30个字符");
		}
		if (!validateHelper.emptyString(domain.getRemark())
				&& validateHelper.illegalStringLength(0, 300, domain.getRemark())) {
			validateResult.addErrorMessage(getColumNo("remark") + "备注最多只能输入300个字符");
		}
		return validateResult;
	}

	public String getColumNo(String key) {
		return ExcelImportHelper.getColumNo(key);
	}

}
