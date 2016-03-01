package com.tianque.baseInfo.primaryOrg.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.primaryOrg.domain.FourLevelPlatform;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;

/**
 * 
 * @author
 * @date 2014-03-10 09:38:17
 */
@Component("fourLevelPlatformValidator")
public class FourLevelPlatformValidator implements
		DomainValidator<FourLevelPlatform> {

	@Autowired
	private ValidateHelper validateHelper;
	/** 备注长度 */
	private final int remarkLength = 100;

	/** 名称及管理部门最小长度 */
	private final int minLength = 2;
	/** 名称及管理部门最大长度 */
	private final int maxLength = 30;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	@Override
	public ValidateResult validate(FourLevelPlatform domain) {
		ValidateResult validateResult = new ValidateResult();
		if (validateHelper.emptyString(domain.getName())) {
			validateResult.addErrorMessage("名称必须输入");
		} else if (validateHelper.illegalStringLength(minLength, maxLength,
				domain.getName())) {
			validateResult.addErrorMessage("名称只能输入" + minLength + "-"
					+ maxLength + "个字符");
		}
		if (validateHelper.emptyString(domain.getTeamTypeName())) {
			validateResult.addErrorMessage("组织类型必须输入");
		}

		if (!validateHelper.nullObj(domain.getSupervisorydepartment())
				&& !validateHelper.emptyString(domain
						.getSupervisorydepartment())) {
			if (validateHelper.illegalStringLength(minLength, maxLength,
					domain.getSupervisorydepartment())) {
				validateResult.addErrorMessage("管理部门只能输入" + minLength + "-"
						+ maxLength + "个字符");
			}
		}

		if (!validateHelper.emptyString(domain.getRemark())) {
			if (validateHelper.illegalStringLength(0, remarkLength,
					domain.getRemark())) {
				validateResult.addErrorMessage("备注不能大于" + remarkLength + "个字符");
			}
		}

		return validateResult;
	}
}