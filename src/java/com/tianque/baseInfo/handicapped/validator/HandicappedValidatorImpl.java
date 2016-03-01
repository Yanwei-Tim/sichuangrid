package com.tianque.baseInfo.handicapped.validator;

import org.springframework.stereotype.Component;

import com.tianque.baseInfo.handicapped.domain.Handicapped;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("handicappedValidator")
public class HandicappedValidatorImpl extends
		AbstractCountrymenValidator<Handicapped> {

	@Override
	public ValidateResult validateSpecializedInfo(Handicapped domain) {
		ValidateResult validateResult = new ValidateResult();

		// 该两项不需要验证
		/*
		 * if (null == domain.getDisabilityReason() ||
		 * domain.getDisabilityReason().trim().isEmpty()) {
		 * validateResult.addErrorMessage(getColumNo("disabilityReason") +
		 * "残疾原因必须输入"); } if (null == domain.getDisabilityType() ||
		 * domain.getDisabilityType().getId() == null) {
		 * validateResult.addErrorMessage(getColumNo("disabilityType") +
		 * "残疾类型必须输入"); }
		 */
		if (!validateHelper.emptyString(domain.getDisabilityReason())) {
			if (validateHelper.illegalStringLength(0, 50,
					domain.getDisabilityReason())) {
				validateResult.addErrorMessage(getColumNo("disabilityReason")
						+ "残疾原因长度不能超过50个字符");
			}
			if (validateHelper.illegalExculdeParticalChar(domain
					.getDisabilityReason())) {
				validateResult.addErrorMessage(getColumNo("disabilityReason")
						+ "残疾原因不能输入非法字符");
			}
		}
		if (validateHelper.emptyString(domain.getDisabilityCardNo())) {
			validateResult.addErrorMessage(getColumNo("disabilityCardNo")
					+ "残疾证号必须输入");
		}

		// 残疾证号 长度验证
		if (!validateHelper.emptyString(domain.getDisabilityCardNo())) {
			if (validateHelper.illegalStringLength(0, 30,
					domain.getDisabilityCardNo())) {
				validateResult.addErrorMessage(getColumNo("disabilityCardNo")
						+ "残疾证号长度不能超过30个字符");
			}
			if (validateHelper.illegalExculdeParticalChar(domain
					.getDisabilityCardNo())) {
				validateResult.addErrorMessage(getColumNo("disabilityCardNo")
						+ "残疾证号不能输入非法字符");
			}
		}

		if (!validateHelper.nullObj(domain.getBirthday())
				&& !validateHelper.nullObj(domain.getDisabilityDate())
				&& !validateHelper.endDateIsSameorBigForStartDate(
						domain.getDisabilityDate(), domain.getBirthday())) {
			validateResult.addErrorMessage(getColumNo("disabilityDate")
					+ "残疾日期不能小于出生日期");
		}

		/** 残疾时间不能大于当前时间 */
		if (!validateHelper.nullObj(domain.getDisabilityDate())
				&& !validateHelper.endDateIsSameorBigForStartDate(
						CalendarUtil.getTomorrow(), domain.getDisabilityDate())) {
			validateResult.addErrorMessage(getColumNo("disabilityDate")
					+ "残疾日期不能大于当前日期");
		}

		/** 监护人字段验证 */
		if (!validateHelper.emptyString(domain.getGuardianName())) {
			if (validateHelper.illegalStringLength(0, 20,
					domain.getGuardianName())) {
				validateResult.addErrorMessage(getColumNo("guardianName")
						+ "监护人不能输入大于20个字符");
			}
			if (validateHelper.illegalExculdeParticalChar(domain
					.getGuardianName())) {
				validateResult.addErrorMessage(getColumNo("guardianName")
						+ "监护人不能输入非法字符");
			}
		}

		return validateResult;
	}
}
