package com.tianque.plugin.dataManage.dustbin.dustbinTemp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.dustbin.dustbinTemp.domain.DustbinTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;

@Component("dustbinTempValidator")
public class DustbinTempValidatorImpl implements
		DomainValidatorTemp<DustbinTemp> {

	@Autowired
	private ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(DustbinTemp domain) {
		ValidateResult validateResult = new ValidateResult();

		if (domain.getPartType() == null) {
			validateResult.addErrorMessage(getColumNo("partType") + "部件类型必须选择");
		}
		if (domain.getPartName() == null) {
			validateResult.addErrorMessage(getColumNo("partName") + "部件名称必须选择");
		}
		if (validateHelper.emptyString(domain.getDeptName())) {
			validateResult.addErrorMessage(getColumNo("deptName") + "主管部门必须输入");
		}
		if (validateHelper.emptyString(domain.getDustbinCode())) {
			validateResult.addErrorMessage(getColumNo("dustbinCode")
					+ "部件标识码必须输入");
		} else if (!validateHelper.isConSpeCharacters(domain.getDustbinCode())) {
			validateResult.addErrorMessage(getColumNo("dustbinCode")
					+ "部件标识码不能包含特殊字符");
		}
		return validateResult;
	}

	public String getColumNo(String key) {
		return ExcelImportHelper.getColumNo(key);
	}

}
