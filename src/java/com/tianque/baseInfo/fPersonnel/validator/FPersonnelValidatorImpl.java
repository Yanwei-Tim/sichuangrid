package com.tianque.baseInfo.fPersonnel.validator;

import org.springframework.stereotype.Component;

import com.tianque.baseInfo.fPersonnel.domain.FPersonnel;
import com.tianque.core.validate.ValidateResult;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("fPersonnelValidator")
public class FPersonnelValidatorImpl extends
		AbstractCountrymenValidator<FPersonnel> {

	@Override
	public ValidateResult validateSpecializedInfo(FPersonnel domain) {
		ValidateResult validateResult = new ValidateResult();
		if (!validateHelper.emptyString(domain.getFbusinessRemark())
				&& validateHelper.illegalStringLength(0, 100,
						domain.getFbusinessRemark())) {
			validateResult.addErrorMessage(getColumNo("fbusinessRemark")
					+ "业务备注不能大于100个字符");
		}
		return validateResult;
	}
}
