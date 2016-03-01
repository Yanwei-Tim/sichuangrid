package com.tianque.plugin.dataManage.population.otherAttentionPersonnelTemp.validator;

import org.springframework.stereotype.Component;

import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.population.otherAttentionPersonnelTemp.domain.OtherAttentionPersonnelTemp;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;

@Component("otherAttentionPersonnelTempValidator")
public class OtherAttentionPersonnelTempValidatorImpl extends
		AbstactDataManageValidator<OtherAttentionPersonnelTemp> {

	@Override
	public ValidateResult validateSpecializedInfo(
			OtherAttentionPersonnelTemp domain) {
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
