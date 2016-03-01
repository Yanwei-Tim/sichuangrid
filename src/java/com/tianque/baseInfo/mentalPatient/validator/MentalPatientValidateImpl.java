package com.tianque.baseInfo.mentalPatient.validator;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.tianque.baseInfo.mentalPatient.domain.MentalPatient;
import com.tianque.core.validate.ValidateResult;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("mentalPatientValidator")
public class MentalPatientValidateImpl extends
		AbstractCountrymenValidator<MentalPatient> {

	@Override
	public ValidateResult validateSpecializedInfo(MentalPatient domain) {
		ValidateResult validateResult = new ValidateResult();
		if (null == domain.getDangerLevel()
				|| null == domain.getDangerLevel().getId()) {
			validateResult.addErrorMessage(getColumNo("dangerLevel")
					+ "危险程度必须输入");
		}
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
			if (domain.getRecoveryTime().after(new Date())) {
				validateResult.addErrorMessage(getColumNo("recoveryTime")
						+ "康复时间不能大于当前时间");
			}
		}
		if (domain.getDiseaseTime() != null) {
			if (domain.getDiseaseTime().after(new Date())) {
				validateResult.addErrorMessage(getColumNo("diseaseTime")
						+ "发病时间不能大于当前时间");
			}
		}
		if (domain.getRecoveryTime() != null && domain.getDiseaseTime() != null) {
			if (domain.getRecoveryTime().before(domain.getDiseaseTime())) {
				validateResult.addErrorMessage(getColumNo("recoveryTime")
						+ "康复时间应大于发病时间");
			}
		}
		return validateResult;
	}
}
