package com.tianque.incident.validate.impl;

import org.springframework.stereotype.Component;

import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.PropertyDict;
import com.tianque.incident.domain.IncidentType;
import com.tianque.incident.validate.AbstractIncidentValidator;

@Component("incidentTypeValidator")
public class IncidentTypeValidatorImpl extends AbstractIncidentValidator<IncidentType> {
	private boolean subjectionIsNotNull(PropertyDict subjection) {
		if (subjection == null || subjection.getId() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ValidateResult validateSpecializedIncident(IncidentType domain) {
		ValidateResult validateResult = new ValidateResult();

		if (!subjectionIsNotNull(domain.getSubjection())) {
			validateResult.addErrorMessage("事件类别不能为空");
		}
		if (validateHelper.emptyString(domain.getName())) {
			validateResult.addErrorMessage("事件名称不能为空");
		}
		if (validateHelper.illegalStringLength(2, 20, domain.getName())) {
			validateResult.addErrorMessage("事件名称只能输入2-20个字符");
		}
		if (validateHelper.emptyString(domain.getDescription())) {
			validateResult.addErrorMessage("事件描述不能为空");
		}
		if (validateHelper.illegalStringLength(2, 500, domain.getDescription())) {
			validateResult.addErrorMessage("事件描述只能输入2-500个字符");
		}
		return validateResult;
	}

}
