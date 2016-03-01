package com.tianque.plugin.dataManage.population.optimalObjectTemp.validator;

import org.springframework.stereotype.Component;

import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.population.optimalObjectTemp.domain.OptimalObjectTemp;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;

@Component("optimalObjectTempValidator")
public class OptimalObjectTempValidatorImpl extends
		AbstactDataManageValidator<OptimalObjectTemp> {

	@Override
	public ValidateResult validateSpecializedInfo(OptimalObjectTemp domain) {

		ValidateResult result = new ValidateResult();

		if (!validateHelper.emptyString(domain.getOptimalCardNo())
				&& validateHelper.illegalStringLength(0, 30,
						domain.getOptimalCardNo())) {
			result.addErrorMessage(getColumNo("optimalCardNo")
					+ "优待证号不能大于30个字符");
		}

		if (!validateHelper.emptyString(domain.getOptimalCardNo())
				&& !validateHelper
						.isConSpeCharacters(domain.getOptimalCardNo())) {
			result.addErrorMessage(getColumNo("optimalCardNo") + "优待证号不能含有特殊字符");
		}

		if (null != domain.getMonthLivingExpenses()
				&& domain.getMonthLivingExpenses() <= 0) {
			result.addErrorMessage(getColumNo("monthLivingExpenses")
					+ "月生活费必须大于零");
		}
		return result;
	}

}
