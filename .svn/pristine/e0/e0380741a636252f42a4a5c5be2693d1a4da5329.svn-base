package com.tianque.plugin.dataManage.location.builddatasTemp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.location.builddatasTemp.domain.BuilddatasTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;

@Component("builddatasTempValidator")
public class BuilddatasTempValidatorImpl implements
		DomainValidatorTemp<BuilddatasTemp> {

	@Autowired
	private ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(BuilddatasTemp domain) {
		ValidateResult validateResult = new ValidateResult();

		if (validateHelper.emptyString(domain.getBuildingname())) {
			validateResult.addErrorMessage(getColumNo("buildingname")
					+ "楼宇名称不能为空");
		}
		if (validateHelper.illegalStringLength(0, 50, domain.getBuildingname())) {
			validateResult.addErrorMessage(getColumNo("buildingname")
					+ "楼宇名称不能输入大于50字符");
		}
		if (validateHelper.illegalExculdeParticalChar(domain.getBuildingname())) {
			validateResult.addErrorMessage(getColumNo("buildingname")
					+ "楼宇名称不能输入非法字符");
		}
		if (validateHelper.emptyString(domain.getBuildingaddress())) {
			validateResult.addErrorMessage(getColumNo("buildingaddress")
					+ "楼宇地址不能为空");
		}
		if (validateHelper.illegalStringLength(0, 50,
				domain.getBuildingaddress())) {
			validateResult.addErrorMessage(getColumNo("buildingaddress")
					+ "楼宇地址不能输入大于50字符");
		}
		if (validateHelper.illegalExculdeParticalChar(domain
				.getBuildingaddress())) {
			validateResult.addErrorMessage(getColumNo("buildingaddress")
					+ "楼宇地址不能输入非法字符");
		}
		if (domain.getBuildingstructures() == null
				|| domain.getBuildingstructures().getId() == null) {
			validateResult.addErrorMessage(getColumNo("buildingstructures")
					+ "建筑结构不能为空");
		}
		if (domain.getType() == null || domain.getType().getId() == null) {
			validateResult.addErrorMessage(getColumNo("type") + "楼宇类型不能为空");
		}
		if (!validateHelper.emptyString(domain.getOwner())) {
			if (validateHelper.illegalStringLength(0, 20, domain.getOwner())) {
				validateResult.addErrorMessage(getColumNo("owner")
						+ "业主不能输入大于20字符");
			}
			if (validateHelper.illegalExculdeParticalChar(domain.getOwner())) {
				validateResult.addErrorMessage(getColumNo("owner")
						+ "业主不能输入非法字符");
			}
		}
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
		// 联系电话验证
		if (!validateHelper.emptyString(domain.getPhone())) {
			if (validateHelper.illegalTelephone(domain.getPhone())) {
				validateResult.addErrorMessage(getColumNo("phone")
						+ "联系电话不合法，只能输数字和横杠(-)");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(7, 15, domain.getPhone())) {
				validateResult.addErrorMessage(getColumNo("phone") + "联系电话不能小于"
						+ 7 + "个字符,不能大于" + 15 + "个字符");
			}
		}
		return validateResult;
	}

	public String getColumNo(String key) {
		return ExcelImportHelper.getColumNo(key);
	}

}
