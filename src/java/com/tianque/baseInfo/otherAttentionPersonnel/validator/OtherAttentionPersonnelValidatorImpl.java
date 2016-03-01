package com.tianque.baseInfo.otherAttentionPersonnel.validator;

import org.springframework.stereotype.Component;

import com.tianque.baseInfo.otherAttentionPersonnel.domain.OtherAttentionPersonnel;
import com.tianque.core.validate.ValidateResult;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("otherAttentionPersonnelValidator")
public class OtherAttentionPersonnelValidatorImpl extends
		AbstractCountrymenValidator<OtherAttentionPersonnel> {

	@Override
	public ValidateResult validateSpecializedInfo(OtherAttentionPersonnel domain) {
		ValidateResult validateResult = new ValidateResult();

		if (!validateHelper.emptyString(domain.getWorkCondition())
				&& validateHelper.illegalStringLength(0, 50,
						domain.getWorkCondition())) {
			validateResult.addErrorMessage(getColumNo("workCondition")
					+ "工作情况不能大于50个字符");
		}
		if (!validateHelper.emptyString(domain.getAttentionReason())
				&& validateHelper.illegalStringLength(0, 20,
						domain.getAttentionReason())) {
			validateResult.addErrorMessage(getColumNo("attentionReason")
					+ "关注原因不能大于20个字符");
		}
		return validateResult;
	}
}
