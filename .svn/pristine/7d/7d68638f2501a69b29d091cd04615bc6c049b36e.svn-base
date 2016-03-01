package com.tianque.partyBuilding.doubleRegActivities.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.partyBuilding.doubleRegActivities.domain.ServiceProject;

/**
 * 
 * @author
 * @date 2014-02-26 10:02:48
 */
@Component("serviceProjectValidator")
public class ServiceProjectValidator implements DomainValidator<ServiceProject> {

	@Autowired
	private ValidateHelper validateHelper;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	@Override
	public ValidateResult validate(ServiceProject domain) {
		ValidateResult validateResult = new ValidateResult();
		if (validateHelper.emptyString(domain.getProjectName())) {
			validateResult.addErrorMessage("项目名称必须输入");
		} else if (validateHelper.illegalStringLength(0, 20,
				domain.getProjectName())) {
			validateResult.addErrorMessage("项目名称最多需要输入20个字符");
		}
		if (validateHelper.emptyString(String.valueOf(domain.getClaimPlans()))
				&& validateHelper.illegalNumberZZ(String.valueOf(domain
						.getClaimPlans()))) {
			validateResult.addErrorMessage("拟认领数只能输入正整数");
		}
		return validateResult;
	}
}