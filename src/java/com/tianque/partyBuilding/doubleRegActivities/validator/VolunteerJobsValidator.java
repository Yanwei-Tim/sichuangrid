package com.tianque.partyBuilding.doubleRegActivities.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.partyBuilding.doubleRegActivities.domain.VolunteerJobs;

/**
 * 
 * @author
 * @date 2014-02-27 10:06:23
 */
@Component("volunteerJobsValidator")
public class VolunteerJobsValidator implements DomainValidator<VolunteerJobs> {

	@Autowired
	private ValidateHelper validateHelper;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	@Override
	public ValidateResult validate(VolunteerJobs domain) {
		ValidateResult validateResult = new ValidateResult();
		if (validateHelper.emptyString(domain.getName())) {
			validateResult.addErrorMessage("志愿者岗位必须输入");
		} else if (validateHelper.illegalStringLength(0, 20, domain.getName())) {
			validateResult.addErrorMessage("志愿者岗位最多需要输入20个字符");
		}

		if (validateHelper.emptyString(String.valueOf(domain.getClaimPlans()))
				&& validateHelper.illegalNumberZZ(String.valueOf(domain
						.getClaimPlans()))) {
			validateResult.addErrorMessage("拟认领数只能输入正整数");
		}
		return validateResult;
	}
}