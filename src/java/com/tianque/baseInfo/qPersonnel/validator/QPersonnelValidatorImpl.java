package com.tianque.baseInfo.qPersonnel.validator;

import org.springframework.stereotype.Component;

import com.tianque.baseInfo.qPersonnel.domain.QPersonnel;
import com.tianque.core.validate.ValidateResult;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("qPersonnelValidator")
public class QPersonnelValidatorImpl extends
		AbstractCountrymenValidator<QPersonnel> {

	@Override
	public ValidateResult validateSpecializedInfo(QPersonnel domain) {
		ValidateResult validateResult = new ValidateResult();
		if (!validateHelper.emptyString(domain.getQbusinessRemark())
				&& validateHelper.illegalStringLength(0, 100,
						domain.getQbusinessRemark())) {
			validateResult.addErrorMessage(getColumNo("qbusinessRemark")
					+ "业务备注不能大于100个字符");
		}
		return validateResult;
	}
}
