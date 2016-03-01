package com.tianque.validate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Dustbin;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("dustbinValidator")
public class DustbinValidatorImpl implements DomainValidator<Dustbin> {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	public ValidateHelper validateHelper;

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

	@Override
	public ValidateResult validate(Dustbin domain) {
		ValidateResult validateResult = new ValidateResult();
		/*
		 * if (validateHelper.emptyString(domain.getUnitName())){
		 * validateResult.addErrorMessage("单位名必须输入");
		 * }
		 * if
		 * (!validateHelper.emptyString(domain.getUnitName())&&validateHelper.illegalStringLength(1,
		 * 50,domain.getUnitName())){
		 * validateResult.addErrorMessage("单位名不能超过50个字符");
		 * }
		 * if
		 * (!validateHelper.emptyString(domain.getUnitAddress())&&validateHelper.illegalStringLength
		 * (0, 50,domain.getUnitAddress())){
		 * validateResult.addErrorMessage("单位地址不能超过50个字符");
		 * }
		 */
		if (!validateOrgIsNotNull(domain.getOrganization())) {
			validateResult.addErrorMessage(getColumNo("organization") + "所属网格必须输入");
		}
		if (validateOrgIsNotNull(domain.getOrganization())
				&& !validateOrgIfExsist(domain.getOrganization())) {
			validateResult.addErrorMessage(getColumNo("organization") + "找不到指定网格");
		}
		if (validateOrgIsNotNull(domain.getOrganization())
				&& !validateOrgIsGrid(domain.getOrganization())) {
			validateResult.addErrorMessage(getColumNo("organization") + "所属网格必须为片组片格");
		}
		if (domain.getPartType() == null) {
			validateResult.addErrorMessage(getColumNo("partType") + "部件类型必须选择");
		}
		if (domain.getPartName() == null) {
			validateResult.addErrorMessage(getColumNo("partName") + "部件名称必须选择");
		}
		if (validateHelper.emptyString(domain.getDeptName())){
			validateResult.addErrorMessage(getColumNo("deptName") + "主管部门必须输入");
		}
		if (validateHelper.emptyString(domain.getDustbinCode())){
			validateResult.addErrorMessage(getColumNo("dustbinCode") + "部件标识码必须输入");
		} else if (!validateHelper.isConSpeCharacters(domain.getDustbinCode())) {
			validateResult.addErrorMessage(getColumNo("dustbinCode") + "部件标识码不能包含特殊字符");
		}
		/*
		 * if (!validateHelper.emptyString(domain.getTelephone()) &&
		 * validateHelper.illegalTelephone(domain.getTelephone())){
		 * validateResult.addErrorMessage("联系电话只能输入数字和-");
		 * }
		 * if (!validateHelper.emptyString(domain.getRemark()) &&
		 * validateHelper.illegalStringLength(0,300,domain.getRemark())){
		 * validateResult.addErrorMessage("备注最多只能输入300个字符");
		 * }
		 */
		return validateResult;
	}
	
	public String getColumNo(String key) {
		return ExcelImportHelper.getColumNo(key);
	}

}
