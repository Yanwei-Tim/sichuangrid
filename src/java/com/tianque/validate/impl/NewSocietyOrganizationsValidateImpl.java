package com.tianque.validate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.newSocietyOrganizations.domain.NewSocietyOrganizations;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("newSocietyOrganizationsValidate")
public class NewSocietyOrganizationsValidateImpl implements
		DomainValidator<NewSocietyOrganizations> {

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

	private boolean typeIsNotNull(PropertyDict type) {
		if (type == null || type.getId() == null) {
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

	@Override
	public ValidateResult validate(NewSocietyOrganizations domain) {
		ValidateResult validateResult = new ValidateResult();
		boolean nameEmpty = validateHelper.emptyString(domain.getName());
		if (nameEmpty) {
			validateResult.addErrorMessage(getColumNo("name") + "组织名称必须输入");
		}
		boolean nameIllage = !nameEmpty
				&& validateHelper.illegalStringLength(2, 20, domain.getName());
		if (nameIllage) {
			validateResult.addErrorMessage(getColumNo("name") + "组织名称只能输入2-20个字符");
		}
		if (!typeIsNotNull(domain.getType())) {
			validateResult.addErrorMessage(getColumNo("type") + "组织类别必须不能为空");
		}

		// 住所能为空了
		/*
		 * if (validateHelper.emptyString(domain.getAddress())) {
		 * validateResult.addErrorMessage(getColumNo("address") + "住所必须不能为空"); }
		 * else if (validateHelper.illegalStringLength(0, 50, domain
		 * .getAddress())) {
		 * validateResult.addErrorMessage(getColumNo("address") +
		 * "住所最多输入50个字符"); }
		 */

		if (!validateHelper.emptyString(domain.getAddress())) {
			if (validateHelper.illegalStringLength(0, 50, domain.getAddress())) {
				validateResult.addErrorMessage(getColumNo("address") + "住所最多输入50个字符");
			}
		}

		boolean orgIsNotNull = validateOrgIsNotNull(domain.getOrganization());
		String userOrgInternalCode = organizationDubboService.getSimpleOrgById(
				ThreadVariable.getUser().getOrganization().getId()).getOrgInternalCode();
		String userOrgName = organizationDubboService.getSimpleOrgById(
				ThreadVariable.getUser().getOrganization().getId()).getOrgName();
		if (userOrgInternalCode.length() == 13 && orgIsNotNull
				&& domain.getOrganization().getOrgName() != null) {
			if (!domain.getOrganization().getOrgName().equals(userOrgName)) {
				validateResult.addErrorMessage("权限越界，不能将数据导入到该网格下！");
			}
		}
		if (!validateHelper.nullObj(domain.getValidityStartDate())
				&& !validateHelper.nullObj(domain.getValidityEndDate())) {
			if (validateHelper.endDateIsSameorBigForStartDate(domain.getValidityStartDate(),
					domain.getValidityEndDate())) {
				validateResult
						.addErrorMessage(getColumNo("validityEndDate") + "有效期结束日期不能小于有效期开始日期");
			}
		}

		boolean legalPersonNameEmpty = validateHelper.emptyString(domain.getLegalPerson());
		if (legalPersonNameEmpty) {
			validateResult.addErrorMessage(getColumNo("legalPerson") + "法定代表人必须输入");
		}
		if (!legalPersonNameEmpty
				&& validateHelper.illegalStringLength(2, 20, domain.getLegalPerson())) {
			validateResult.addErrorMessage(getColumNo("legalPerson") + "法定代表人只能输入2-20个字符");
		}
		if (!validateHelper.emptyString(domain.getLegalPersonTelephone())) {
			if (validateHelper.illegalStringLength(0, 20, domain.getLegalPersonTelephone())) {
				validateResult.addErrorMessage(getColumNo("legalPersonTelephone")
						+ "固定电话不能输入大于20字符");
			} else if (validateHelper.illegalTelephone(domain.getLegalPersonTelephone())) {
				validateResult.addErrorMessage(getColumNo("legalPersonTelephone") + "固定电话只能输入数字和-");
			}
		}
		if (!validateHelper.emptyString(domain.getLegalPersonMobileNumber())
				&& validateHelper.illegalTelephone(domain.getLegalPersonMobileNumber())) {
			validateResult.addErrorMessage(getColumNo("legalPersonMobileNumber") + "联系手机只能输入数字和-");
		}
		if (!validateHelper.emptyString(domain.getLegalPersonMobileNumber())
				&& validateHelper.illegalMobilePhone(domain.getLegalPersonMobileNumber())) {
			validateResult.addErrorMessage(getColumNo("legalPersonMobileNumber")
					+ "联系手机只能输入1开头的11位数字");
		}
		if (validateHelper.illegalStringLength(0, 50, domain.getChargeUnit())) {
			validateResult.addErrorMessage(getColumNo("chargeUnit") + "业务主管单位输入不能大于50个字符");
		}
		if (domain.getPartyNum() != null) {
			if (domain.getPartyNum() > 999999999) {
				validateResult.addErrorMessage(getColumNo("partyNum") + "党员人数不能大于999999999人");
			} else if (domain.getPersonNum() != null
					&& domain.getPartyNum() > domain.getPersonNum()) {
				validateResult.addErrorMessage(getColumNo("partyNum") + "党员人数不能大于成员人数");
			}

		}
		if (validateHelper.illegalStringLength(0, 50, domain.getAddress())) {
			validateResult.addErrorMessage(getColumNo("address") + "住所输入不能大于50个字符");
		}
		if (validateHelper.illegalStringLength(0, 200, domain.getRemark())) {
			validateResult.addErrorMessage(getColumNo("remark") + "备注最大只能输入200个字符");
		}
		return validateResult;
	}

	public String getColumNo(String key) {
		return ExcelImportHelper.getColumNo(key);
	}

}
