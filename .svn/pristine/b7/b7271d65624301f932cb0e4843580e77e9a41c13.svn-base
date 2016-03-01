package com.tianque.validate.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.buildDatas.domain.Builddatas;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

@Component("buildingValidator")
public class BuildingValidatorImpl implements DomainValidator<Builddatas> {

	@Autowired
	private ValidateHelper validateHelper;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
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

	private boolean validateBuildingStruct(PropertyDict dict) {
		if (dict == null || dict.getId() == null) {
			return false;
		}
		return true;
	}

	private boolean validateType(PropertyDict dict) {
		if (dict == null || dict.getId() == null) {
			return false;
		}
		return true;
	}

	public String getColumNo(String key) {
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

	private boolean validatePhone(String text) {
		if (text.length() >= 2 && text.length() <= 20 && text.charAt(0) != '-'
				&& text.charAt(text.length() - 1) != '-') {
			for (int i = 0; i < text.length(); i++) {
				int charToInt = text.charAt(i);
				if ((charToInt < 48 && charToInt != 45) || charToInt > 57) {
					return true;
				}
			}
			return false;
		}
		return true;
	}

	public ValidateResult validate(Builddatas domain) {
		ValidateResult validateResult = new ValidateResult();

		if (validateHelper.emptyString(domain.getBuildingname())) {
			validateResult.addErrorMessage(getColumNo("buildingname")
					+ "楼宇名称必须输入");
		}

		if (!validateHelper.emptyString(domain.getPhone())) {
			if (validatePhone(domain.getPhone())) {
				validateResult.addErrorMessage(getColumNo("phone")
						+ "电话号码只能输入数字和-，并且不能输入大于20小于2个字符");
			}
		}

		if (!validateHelper.emptyString(domain.getBuildingname())) {
			if (validateHelper.illegalStringLength(0, 50,
					domain.getBuildingname())) {
				validateResult.addErrorMessage(getColumNo("buildingname")
						+ "楼宇名称不能输入大于50字符");
			}
		}
		if (!validateHelper.isConSpeCharacters(domain.getBuildingname())) {
			validateResult.addErrorMessage(getColumNo("buildingname")
					+ "楼宇名称不能输入特殊字符");
		}

		boolean orgIsNotNull = validateOrgIsNotNull(domain.getOrganization());
		if (!orgIsNotNull) {
			validateResult.addErrorMessage(getColumNo("organization")
					+ "所属网格不能为空");
		}
		if (validateHelper.emptyString(domain.getBuildingaddress())) {
			validateResult.addErrorMessage(getColumNo("buildingaddress")
					+ "楼宇地址不能为空");
		}
		if (!validateHelper.emptyString(domain.getBuildingaddress())) {
			if (validateHelper.illegalStringLength(0, 50,
					domain.getBuildingaddress())) {
				validateResult.addErrorMessage(getColumNo("buildingaddress")
						+ "楼宇地址不能输入大于50字符");
			}
		}
		if (!validateHelper.isConSpeCharacters(domain.getBuildingaddress())) {
			validateResult.addErrorMessage(getColumNo("buildingaddress")
					+ "楼宇地址不能输入特殊字符");
		}

		if (!validateBuildingStruct(domain.getBuildingstructures())) {
			validateResult.addErrorMessage(getColumNo("buildingstructures")
					+ "建筑结构不能为空");
		}

		if (!validateType(domain.getType())) {
			validateResult.addErrorMessage(getColumNo("type") + "楼宇类型不能为空");
		}

		/** 业主字段验证 */
		if (!validateHelper.emptyString(domain.getOwner())) {
			if (validateHelper.illegalStringLength(0, 20, domain.getOwner())) {
				validateResult.addErrorMessage(getColumNo("owner")
						+ "业主输入不能超过20个字符");
			}
			if (validateHelper.illegalExculdeParticalChar(domain.getOwner())) {
				validateResult.addErrorMessage(getColumNo("owner")
						+ "业主不能输入非法字符");
			}
		}

		/** 负责人字段验证 */
		if (!validateHelper.emptyString(domain.getResponsibleperson())) {
			if (validateHelper.illegalStringLength(0, 20,
					domain.getResponsibleperson())) {
				validateResult.addErrorMessage(getColumNo("responsibleperson")
						+ "负责人不能输入大于20字符");
			}
			if (validateHelper.illegalExculdeParticalChar(domain
					.getResponsibleperson())) {
				validateResult.addErrorMessage(getColumNo("responsibleperson")
						+ "负责人不能输入非法字符");
			}
		}

		return validateResult;
	}
}
