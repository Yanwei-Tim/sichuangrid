package com.tianque.plugin.dataManage.population.mentalPatientTemp.validator;

import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.population.mentalPatientTemp.domain.MentalPatientTemp;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;

@Component("mentalPatientTempValidator")
public class MentalPatientTempValidatorImpl extends
		AbstactDataManageValidator<MentalPatientTemp> {

	@Override
	public ValidateResult validateSpecializedInfo(MentalPatientTemp domain) {
		ValidateResult validateResult = new ValidateResult();

		if (!validateHelper.emptyString(domain.getPsychosisName())) {
			if (validateHelper.illegalStringLength(0, 50,
					domain.getPsychosisName())) {
				validateResult.addErrorMessage(getColumNo("psychosisName")
						+ "患病名称不能输入大于50字符");
			}
			if (!validateHelper.isConSpeCharacters(domain.getPsychosisName())) {
				validateResult.addErrorMessage(getColumNo("psychosisName")
						+ "患病名称不能包含特殊字符");
			}
		}

		if (domain.isTreat() == false) {
			if (!validateHelper.emptyString(domain.getCureDepartment())) {
				validateResult.addErrorMessage(getColumNo("cureDepartment")
						+ "该患者未接受过治疗，不应有康复机构");
			}
		}

		if (!validateHelper.emptyString(domain.getCureDepartment())) {
			if (validateHelper.illegalStringLength(0, 50,
					domain.getCureDepartment())) {
				validateResult.addErrorMessage(getColumNo("cureDepartment")
						+ "康复机构不能输入大于50字符");
			}
			if (!validateHelper.isConSpeCharacters(domain.getCureDepartment())) {
				validateResult.addErrorMessage(getColumNo("cureDepartment")
						+ "康复机构不能包含特殊字符");
			}
		}

		if (domain.getRecoveryTime() != null) {
			if (domain.getRecoveryTime().after(CalendarUtil.getTomorrow())) {
				validateResult.addErrorMessage(getColumNo("recoveryTime")
						+ "康复时间不能大于当前时间");
			}
		}
		if (domain.getDiseaseTime() != null) {
			if (domain.getDiseaseTime().after(CalendarUtil.getTomorrow())) {
				validateResult.addErrorMessage(getColumNo("diseaseTime")
						+ "发病时间不能大于当前时间");
			}
		}

		if (domain.getRecoveryTime() != null && domain.getDiseaseTime() != null) {
			if (domain.getRecoveryTime().before(domain.getDiseaseTime())) {
				validateResult.addErrorMessage(getColumNo("recoveryTime")
						+ "康复时间应在发病时间之后");
			}
		}
		return validateResult;
	}
}
