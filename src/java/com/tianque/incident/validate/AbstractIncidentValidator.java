package com.tianque.incident.validate;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.PropertyDict;
import com.tianque.incident.domain.component.IncidentDegreeRule;

public abstract class AbstractIncidentValidator<T> {
	@Autowired
	protected ValidateHelper validateHelper;

	public abstract ValidateResult validateSpecializedIncident(T domain);

	public ValidateResult validateIncidentDegree(IncidentDegreeRule domain) {
		ValidateResult validateResult = new ValidateResult();
		if (!degreeIsNotNull(domain.getDegree())) {
			validateResult.addErrorMessage("事件等级不能为空");
		}
		if (validateHelper.emptyString(domain.getRule())) {
			validateResult.addErrorMessage("分级依据不能为空");
		}
		if (validateHelper.illegalStringLength(2, 500, domain.getRule())) {
			validateResult.addErrorMessage("分级依据只能输入2-500个字符");
		}
		return validateResult;
	}

	public ValidateResult IncidentValidate(T domain) {
		ValidateResult validateResult = new ValidateResult();
		validateResult.addAllErrorMessage(validateSpecializedIncident((T) domain));
		return validateResult;
	}

	public ValidateResult IncidentTypeDegreeValidate(IncidentDegreeRule domain) {
		ValidateResult validateResult = new ValidateResult();
		validateResult.addAllErrorMessage(validateIncidentDegree(domain));
		return validateResult;
	}

	private boolean degreeIsNotNull(PropertyDict degree) {
		if (degree == null || degree.getId() == null) {
			return false;
		}
		return true;
	}

}
