package com.tianque.baseInfo.mPersonnel.validator;

import org.springframework.stereotype.Component;

import com.tianque.baseInfo.mPersonnel.domain.MPersonnel;
import com.tianque.core.validate.ValidateResult;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("mPersonnelValidator")
public class MPersonnelValidatorImpl extends
		AbstractCountrymenValidator<MPersonnel> {

	@Override
	public ValidateResult validateSpecializedInfo(MPersonnel domain) {
		ValidateResult validateResult = new ValidateResult();
		if (!validateHelper.emptyString(domain.getMbusinessRemark())
				&& validateHelper.illegalStringLength(0, 100,
						domain.getMbusinessRemark())) {
			validateResult.addErrorMessage(getColumNo("mbusinessRemark")
					+ "业务备注不能大于100个字符");
		}
		return validateResult;
	}
}
