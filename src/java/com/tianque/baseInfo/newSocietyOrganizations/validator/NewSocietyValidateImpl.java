package com.tianque.baseInfo.newSocietyOrganizations.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.newSocietyOrganizations.domain.NewSocietyOrganizations;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("newSocietyOrganizationsValidator")
public class NewSocietyValidateImpl implements
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

	@Override
	public ValidateResult validate(NewSocietyOrganizations domain) {
		ValidateResult validateResult = new ValidateResult();
		boolean nameEmpty = validateHelper.emptyString(domain.getName());
		if (nameEmpty) {
			validateResult.addErrorMessage(getColumNo("name") + "组织名称必须输入");
		}
		if (!validateHelper.isConSpeCharacters(domain.getName())) {
			validateResult.addErrorMessage(getColumNo("name") + "组织名称不能有特殊字符");
		}

		if (!nameEmpty
				&& validateHelper.illegalStringLength(2, 20, domain.getName())) {
			validateResult.addErrorMessage(getColumNo("name")
					+ "组织名称只能输入2-20个字符");
		}
		if (!typeIsNotNull(domain.getType())) {
			validateResult.addErrorMessage(getColumNo("name") + "组织类别必须不能为空");
		}

		boolean addressEmpty = validateHelper.emptyString(domain.getAddress());
		/*
		 * fateson add 住所可以为空 if (addressEmpty) {
		 * validateResult.addErrorMessage(getColumNo("address") + "住所必须输入"); }
		 */
		if (!addressEmpty
				&& validateHelper.illegalStringLength(2, 50,
						domain.getAddress())) {
			validateResult.addErrorMessage(getColumNo("address")
					+ "住所只能输入2-50个字符");
		}
		if (!addressEmpty
				&& !validateHelper.isConSpeCharacters(domain.getAddress())) {
			validateResult.addErrorMessage(getColumNo("address") + "住所不能有特殊字符");
		}
		boolean orgIsNotNull = validateOrgIsNotNull(domain.getOrganization());
		/*
		 * if (orgIsNotNull && !organizationDubboService.isGridOrganization(domain
		 * .getOrganization().getId())) {
		 * validateResult.addErrorMessage(getColumNo("organization") +
		 * "所属网格必须为片组片格"); }
		 */

		// String userOrgInternalCode = organizationDubboService.getSimpleOrgById(
		// ThreadVariable.getUser().getOrganization().getId()).getOrgInternalCode();
		// String userOrgName = organizationDubboService.getSimpleOrgById(
		// ThreadVariable.getUser().getOrganization().getId()).getOrgName();
		// if (userOrgInternalCode.length() == 13 && orgIsNotNull
		// && domain.getOrganization().getOrgName() != null) {
		// if (!domain.getOrganization().getOrgName().equals(userOrgName)) {
		// validateResult.addErrorMessage(getColumNo("organization") +
		// "权限越界，不能将数据导入到该网格下！");
		// }
		// }

		if (!validateHelper.nullObj(domain.getValidityStartDate())
				&& !validateHelper.nullObj(domain.getValidityEndDate())) {
			if (validateHelper.endDateIsSameorBigForStartDate(
					domain.getValidityStartDate(), domain.getValidityEndDate())) {
				validateResult.addErrorMessage(getColumNo("validityEndDate")
						+ "有效期结束日期不能小于有效期开始日期");
			}
		}

		boolean legalPersonNameEmpty = validateHelper.emptyString(domain
				.getLegalPerson());
		if (legalPersonNameEmpty) {
			validateResult.addErrorMessage(getColumNo("legalPerson")
					+ "法定代表人必须输入");
		}

		if (!legalPersonNameEmpty
				&& validateHelper.illegalStringLength(2, 20,
						domain.getLegalPerson())) {
			validateResult.addErrorMessage(getColumNo("legalPerson")
					+ "法定代表人只能输入2-20个字符");
		}

		if (!legalPersonNameEmpty
				&& !validateHelper.isConSpeCharacters(domain.getLegalPerson())) {
			validateResult.addErrorMessage(getColumNo("legalPerson")
					+ "法定代表人不能有特殊字符");
		}

		if (!validateHelper.emptyString(domain.getLegalPersonTelephone())
				&& validateHelper.illegalTelephone(domain
						.getLegalPersonTelephone())) {
			validateResult.addErrorMessage(getColumNo("legalPersonTelephone")
					+ "固定电话只能输入数字和-");
		}
		if (!validateHelper.emptyString(domain.getLegalPersonMobileNumber())
				&& validateHelper.illegalMobilePhone(domain
						.getLegalPersonMobileNumber())) {
			validateResult
					.addErrorMessage(getColumNo("legalPersonMobileNumber")
							+ "联系手机只能输入1开头的11位数字");
		}
		if (validateHelper.illegalStringLength(0, 20, domain.getChargeUnit())) {
			validateResult.addErrorMessage(getColumNo("chargeUnit")
					+ "业务主管单位输入不能大于20个字符");
		}
		if (validateHelper.illegalStringLength(0, 32,
				domain.getRegistrationNumber())) {
			validateResult.addErrorMessage(getColumNo("registrationNumber")
					+ "登记证号码输入不能大于32位数字");
		}
		if (domain.getPersonNum() != null && domain.getPersonNum() > 999999999) {
			validateResult.addErrorMessage(getColumNo("personNum")
					+ "成员数不能大于999999999人");
		}
		if (domain.getPartyNum() != null && domain.getPartyNum() > 999999999) {
			validateResult.addErrorMessage(getColumNo("partyNum")
					+ "党员人数不能大于999999999人");
		}
		if (domain.getPersonNum() != null && domain.getPersonNum() <= 999999999
				&& domain.getPartyNum() != null
				&& domain.getPartyNum() <= 999999999
				&& domain.getPersonNum() < domain.getPartyNum()) {
			validateResult.addErrorMessage(getColumNo("personNum")
					+ "党员人数不能大于成员数");
		}
		if (validateHelper
				.illegalStringLength(0, 200, domain.getIntroduction())) {
			validateResult.addErrorMessage(getColumNo("introduction")
					+ "简介最大只能输入200个字符");
		}
		if (validateHelper.illegalStringLength(0, 200, domain.getHonor())) {
			validateResult.addErrorMessage(getColumNo("honor")
					+ "获得荣誉最大只能输入200个字符");
		}
		if (validateHelper.illegalStringLength(0, 200, domain.getRemark())) {
			validateResult.addErrorMessage(getColumNo("remark")
					+ "备注最大只能输入200个字符");
		}

		return validateResult;
	}

	private String getColumNo(String key) {
		StringBuffer bf = new StringBuffer();
		if (!StringUtils.isEmpty(ExcelImportHelper.getDataColumMap(key))) {
			bf.append("第[").append(ExcelImportHelper.realRow.get())
					.append("]行");
			bf.append("第[")
					.append(Integer.valueOf(ExcelImportHelper
							.getDataColumMap(key)) + 1).append("]列");
		} else {
			bf.append("");
		}
		return bf.toString();
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

}
