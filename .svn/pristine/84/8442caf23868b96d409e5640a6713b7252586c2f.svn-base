package com.tianque.baseInfo.superiorVisit.validator;

import org.springframework.stereotype.Component;

import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisit;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("superiorVisitValidator")
public class SuperiorVisitValidatorImpl extends AbstractCountrymenValidator<SuperiorVisit> {

	@Override
	public ValidateResult validateSpecializedInfo(SuperiorVisit domain) {
		ValidateResult validateResult = new ValidateResult();
		if (validateHelper.emptyString(domain.getVisitReason())) {
			validateResult.addErrorMessage(getColumNo("visitReason") + "上访原因必须输入");
		} else if (validateHelper.illegalStringLength(0, 200, domain.getVisitReason())) {
			validateResult.addErrorMessage(getColumNo("visitReason") + "上访原因最多只能输入200个字符");
		}
		if (!validateHelper.nullObj(domain.getVisitState())
				&& !validateHelper.emptyString(domain.getVisitState().getDisplayName())
				&& validateHelper.illegalPropertyDictDisplayName(
						PropertyTypes.SUPERIOR_VISIT_STATUS, domain.getVisitState()
								.getDisplayName())) {
			validateResult.addErrorMessage(getColumNo("visitState") + "上访状态输入不正确");
		}
		if (!validateHelper.nullObj(domain.getVisitTypes())
				&& !validateHelper
						.existsSamePropertyTypes(domain.getVisitTypes(),
								PropertyTypes.NORMAL_SUPERIOR_VISIT,
								PropertyTypes.EXCEPTION_SUPERIOR_VISIT)) {
			validateResult.addErrorMessage(getColumNo("visitTypes") + "上访类型只能是正常访或异常访中一种");
		}
		return validateResult;
	}

}
