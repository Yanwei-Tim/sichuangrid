package com.tianque.validate.impl;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.sysadmin.service.OrganizationDubboService;

public class ActualPopulationValidateImpl implements
		DomainValidator<ActualPopulation> {

	private ValidateHelper validateHelper;
	private OrganizationDubboService organizationDubboService;

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	private boolean genderIsNotNull(PropertyDict gender) {
		if (null == gender || null == gender.getId()) {
			return false;
		}
		return true;
	}

	private boolean validateOrgIsNotNull(Organization org) {
		if (null == org) {
			return false;
		}
		if (org != null && null == org.getId()) {
			return false;
		}
		return true;
	}

	private boolean validateOrgIsGrid(Organization org) {
		return organizationDubboService.isGridOrganization(org.getId());
	}

	@Override
	public ValidateResult validate(ActualPopulation domain) {
		ValidateResult validateResult = new ValidateResult();
		if (null == domain.getName()) {
			validateResult.addErrorMessage("姓名必须输入");
		} else {
			boolean nameBol = validateHelper.emptyString(domain.getName());
			if (nameBol) {
				validateResult.addErrorMessage("姓名必须输入");
			}
			if (!nameBol
					&& validateHelper.illegalStringLength(2, 20,
							domain.getName())) {
				validateResult.addErrorMessage("姓名只能输入2-20个字符");
			}
		}
		if (!genderIsNotNull(domain.getGender())) {
			validateResult.addErrorMessage("性别必须输入");
		}
		boolean idCardEmpty = validateHelper.emptyString(domain.getIdCardNo());
		if (idCardEmpty) {
			validateResult.addErrorMessage("身份证号码必须输入");
		} else {
			if (validateHelper.illegalIdcard(domain.getIdCardNo())) {
				validateResult.addErrorMessage("身份证号码输入不正确");
			}
		}
		boolean orgBol = validateOrgIsNotNull(domain.getOrganization());
		if (!orgBol) {
			validateResult.addErrorMessage("所属网格不能为空");
		}
		if (orgBol && !validateOrgIsGrid(domain.getOrganization())) {
			validateResult.addErrorMessage("所属网格必须为片组片格");
		}
		// if
		// (validateHelper.illegalStringLength(0,20,domain.getNativeProvince())){
		// validateResult.addErrorMessage("户籍省不能输入大于10字符");
		// }
		// if (validateHelper.illegalStringLength(0,20,domain.getNativeCity())){
		// validateResult.addErrorMessage("户籍市不能输入大于10字符");
		// }
		// if
		// (validateHelper.illegalStringLength(0,20,domain.getNativeDistrict())){
		// validateResult.addErrorMessage("户籍县不能输入大于10字符");
		// }
		if (validateHelper.illegalStringLength(0, 50,
				domain.getNativePlaceAddress())) {
			validateResult.addErrorMessage("户籍地详址不能输入大于50字符");
		}
		if (validateHelper.illegalStringLength(0, 50,
				domain.getCurrentAddress())) {
			validateResult.addErrorMessage("常住地址不能输入大于50字符");
		}
		if (validateHelper.illegalStringLength(0, 50, domain.getWorkUnit())) {
			validateResult.addErrorMessage("工作单位或就读学校不能输入大于50字符");
		}
		if (!validateHelper.emptyString(domain.getTelephone())
				&& validateHelper.illegalTelephone(domain.getTelephone())) {
			validateResult.addErrorMessage("联系电话只能输入数字和-");
		}
		if (validateHelper.illegalStringLength(0, 15, domain.getTelephone())) {
			validateResult.addErrorMessage("联系电话不能输入大于15字符");
		}
		if (!validateHelper.emptyString(domain.getMobileNumber())
				&& validateHelper.illegalMobilePhone(domain.getMobileNumber())) {
			validateResult.addErrorMessage("联系手机只能输入1开头的11位数字");
		}
		if (!validateHelper.emptyString(domain.getUsedName())
				&& validateHelper.illegalStringLength(2, 20,
						domain.getUsedName())) {
			validateResult.addErrorMessage("曾用名只能输入2-20个字符");
		}
		if (validateHelper.illegalStringLength(0, 200, domain.getRemark())) {
			validateResult.addErrorMessage("备注不能输入大于200字符");
		}
		if (validateHelper.illegalStringLength(0, 50, domain.getEmail())) {
			validateResult.addErrorMessage("邮箱不能输入大于50字符");
		}
		if (validateHelper.illegalStringLength(0, 100, domain.getImgUrl())) {
			validateResult.addErrorMessage("图片路径不能大于100字符");
		}

		return validateResult;
	}

}
