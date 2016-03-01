package com.tianque.baseInfo.dangerousChemicalsUnit.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.dangerousChemicalsUnit.domain.DangerousChemicalsUnit;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("dangerousChemicalsUnitValidator")
public class DangerousChemicalsUnitVaidatorImpl implements
		DomainValidator<DangerousChemicalsUnit> {
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
	public ValidateResult validate(DangerousChemicalsUnit domain) {
		ValidateResult validateResult = new ValidateResult();
		if (validateHelper.emptyString(domain.getUnitName())) {
			validateResult.addErrorMessage(getColumNo("unitName")
					+ "工作单位或就读学校名称必须输入");
		}
		if (!validateHelper.emptyString(domain.getUnitName())
				&& validateHelper.illegalStringLength(1, 50,
						domain.getUnitName())) {
			validateResult.addErrorMessage(getColumNo("unitName")
					+ "工作单位或就读学校名称不能超过50个字符");
		}
		if (!validateHelper.emptyString(domain.getUnitName())
				&& !validateHelper.isConSpeCharacters(domain.getUnitName())) {
			validateResult.addErrorMessage(getColumNo("unitName")
					+ "工作单位或就读学校名称不能有特殊字符");
		}
		if (!validateHelper.emptyString(domain.getUnitAddress())
				&& validateHelper.illegalStringLength(0, 50,
						domain.getUnitAddress())) {
			validateResult.addErrorMessage(getColumNo("unitAddress")
					+ "单位地址不能超过50个字符");
		}
		if (!validateOrgIsNotNull(domain.getOrganization())) {
			validateResult.addErrorMessage(getColumNo("organization")
					+ "所属网格必须输入");
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
		if (validateOrgIsNotNull(domain.getOrganization())
				&& !validateOrgIsGrid(domain.getOrganization())) {
			validateResult.addErrorMessage(getColumNo("organization")
					+ "所属网格必须为片组片格");
		}
		if (!validateHelper.emptyString(domain.getTelephone())
				&& validateHelper.illegalTelephone(domain.getTelephone())) {
			validateResult.addErrorMessage(getColumNo("telephone")
					+ "联系电话只能输入数字和-");
		}
		if (!validateHelper.emptyString(domain.getRemark())
				&& validateHelper.illegalStringLength(0, 300,
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

	private boolean validateOrgIsGrid(Organization org) {
		return organizationDubboService.isGridOrganization(org.getId());
	}

	private boolean validateOrgIfExsist(Organization org) {
		Organization organization = organizationDubboService.getSimpleOrgById(org
				.getId());
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
