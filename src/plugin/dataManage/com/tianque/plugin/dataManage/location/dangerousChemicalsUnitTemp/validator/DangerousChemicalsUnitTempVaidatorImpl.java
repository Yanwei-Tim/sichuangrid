package com.tianque.plugin.dataManage.location.dangerousChemicalsUnitTemp.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.plugin.dataManage.location.dangerousChemicalsUnitTemp.domain.DangerousChemicalsUnitTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("dangerousChemicalsUnitTempValidator")
public class DangerousChemicalsUnitTempVaidatorImpl implements
		DomainValidatorTemp<DangerousChemicalsUnitTemp> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	@Override
	public ValidateResult validate(DangerousChemicalsUnitTemp domain) {
		ValidateResult validateResult = new ValidateResult();
		if (validateHelper.emptyString(domain.getName())) {
			validateResult.addErrorMessage(getColumNo("name") + "单位名称必须输入");
		}
		if (!validateHelper.emptyString(domain.getName())
				&& validateHelper.illegalStringLength(1, 50, domain.getName())) {
			validateResult.addErrorMessage(getColumNo("name") + "单位名不能超过50个字符");
		}
		if (!validateHelper.emptyString(domain.getName())
				&& !validateHelper.isConSpeCharacters(domain.getName())) {
			validateResult.addErrorMessage(getColumNo("name") + "单位名不能有特殊字符");
		}
		if (!validateHelper.emptyString(domain.getAddress())
				&& validateHelper.illegalStringLength(0, 50,
						domain.getAddress())) {
			validateResult.addErrorMessage(getColumNo("address")
					+ "单位地址不能超过50个字符");
		}
		boolean orgIsNotNull = validateOrgIsNotNull(domain.getOrganization());
		String userOrgInternalCode = organizationDubboService.getSimpleOrgById(
				ThreadVariable.getUser().getOrganization().getId())
				.getOrgInternalCode();
		String userOrgName = organizationDubboService.getSimpleOrgById(
				ThreadVariable.getUser().getOrganization().getId())
				.getOrgName();
		if (userOrgInternalCode.length() == 13 && orgIsNotNull
				&& domain.getOrganization().getOrgName() != null) {
			if (!domain.getOrganization().getOrgName().equals(userOrgName)) {
				validateResult.addErrorMessage(getColumNo("organization")
						+ "权限越界，不能将数据导入到该网格下！");
			}
		}
		if (validateOrgIsNotNull(domain.getOrganization())
				&& !validateOrgIfExsist(domain.getOrganization())) {
			validateResult.addErrorMessage(getColumNo("organization")
					+ "找不到指定网格");
		}
		if (!validateHelper.emptyString(domain.getTelephone())
				&& validateHelper.illegalTelephone(domain.getTelephone())) {
			validateResult.addErrorMessage(getColumNo("telephone")
					+ "联系电话只能输入数字和-");
		}
		if (!validateHelper.emptyString(domain.getTelephone())
				&& validateHelper.illegalStringLength(1, 20,
						domain.getTelephone())) {
			validateResult.addErrorMessage(getColumNo("telephone")
					+ "联系电话最大输入20个字符");
		}
		if (validateHelper.illegalStringLength(0, 20, domain.getManager())) {
			validateResult.addErrorMessage(getColumNo("manager")
					+ "负责人最大输入20个字符");
		}
		if (validateHelper.illegalStringLength(0, 50, domain.getUnitType())) {
			validateResult.addErrorMessage(getColumNo("unitType")
					+ "单位类别最大输入50个字符");
		}
		if (validateHelper
				.illegalStringLength(0, 50, domain.getCommodityType())) {
			validateResult.addErrorMessage(getColumNo("commodityType")
					+ "货物类别最大输入50个字符");
		}
		if (validateHelper.illegalStringLength(0, 50, domain.getCopyScope())) {
			validateResult.addErrorMessage(getColumNo("copyScope")
					+ "副本许可范围最大输入50个字符");
		}
		if (validateHelper
				.illegalStringLength(0, 50, domain.getStorageDevice())) {
			validateResult.addErrorMessage(getColumNo("storageDevice")
					+ "存储设备最大输入50个字符");
		}
		if (!validateHelper.emptyString(domain.getRemark())
				&& validateHelper.illegalStringLength(0, 200,
						domain.getRemark())) {
			validateResult.addErrorMessage(getColumNo("remark")
					+ "备注最多只能输入300个字符");
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

	private boolean validateOrgIfExsist(Organization org) {
		Organization organization = organizationDubboService
				.getSimpleOrgById(org.getId());
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

}
