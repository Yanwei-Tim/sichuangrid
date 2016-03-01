package com.tianque.plugin.dataManage.population.unsettledPopulationTemp.validator;

import org.springframework.stereotype.Component;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.plugin.dataManage.population.unsettledPopulationTemp.domain.UnsettledPopulationTemp;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;

@Component("unsettledPopulationTempValidator")
public class UnsettledPopulationTempValidatorImpl extends
		AbstactDataManageValidator<UnsettledPopulationTemp> {

	public ValidateResult validateBaseInfo(Countrymen domain) {
		ValidateResult result = new ValidateResult();
		// 姓名验证
		validatorName(domain.getName(), result, null);

		if (!validateHelper.emptyString(domain.getIdCardNo())
				&& validateHelper.illegalIdcard(domain.getIdCardNo())) {
			result.addErrorMessage(getColumNo("idCardNo") + "身份证号码输入不正确");
		}
		
		/** 出生日期验证 */
		if (!validateHelper.nullObj(domain.getBirthday())) {
			if (!validateHelper.endDateIsSameorBigForStartDate(
					CalendarUtil.getTomorrow(), domain.getBirthday())) {
				result.addErrorMessage(getColumNo("birthday") + "出生日期不能大于当前日期");
			}
		}

		// 性别验证
		validatorifGender(domain.getGender(), result, null,
				domain.getIdCardNo());
		// 省市县验证
		if (!validateHelper.emptyString(domain.getProvince())
				&& validateHelper.illegalStringLength(0, 20,
						domain.getProvince())) {
			result.addErrorMessage(getColumNo("province") + "户籍地省不能输入大于20字符");
		}
		if (!validateHelper.emptyString(domain.getCity())
				&& validateHelper.illegalStringLength(0, 20, domain.getCity())) {
			result.addErrorMessage(getColumNo("city") + "户籍地市不能输入大于20字符");
		}
		if (!validateHelper.emptyString(domain.getDistrict())
				&& validateHelper.illegalStringLength(0, 20,
						domain.getDistrict())) {
			result.addErrorMessage(getColumNo("district") + "户籍地县不能输入大于20字符");
		}

		if (!validateHelper.emptyString(domain.getUsedName())) {
			if(validateHelper.illegalStringLength(2, 20,
					domain.getUsedName())){
				result.addErrorMessage(getColumNo("usedName") + "曾用名/别名只能输入2-20个字符");
			}
			if (!validateHelper.isConSpeCharacters(domain.getUsedName())) {
				result.addErrorMessage(getColumNo("usedName")
						+ "曾用名/别名不能含有特殊字符");
			}
		}
		// 联系人电话验证
		validatorifMobileNumber(domain.getMobileNumber(), result, null);
		// 联系人固定电话验证
		validatorifTelephone(domain.getTelephone(), result, null);
		// 工作单位验证
		validatorWorkUnit(domain.getWorkUnit(), result, null);

		if (domain.getStature() != null) {
			if (validateHelper.illegalInteger(String.valueOf(domain
					.getStature()))) {
				result.addErrorMessage(getColumNo("stature")
						+ "身高只能输入不大于300的正整数");
			} else if (Double.valueOf(domain.getStature()).longValue() > 300) {
				result.addErrorMessage(getColumNo("stature")
						+ "身高只能输入不大于300的正整数");
			}
		}

		// 职业验证
		validatorCareer(domain.getCareer(), result, null);

		// 婚姻状况验证
		validatorifMaritalState(domain.getMaritalState(), result, null);
		// 民族验证
		validatorNation(domain.getNation(), result, null);

		if (null != domain.getFaith()
				&& null != domain.getFaith().getDisplayName()) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.FAITH, domain.getFaith().getDisplayName()))
				result.addErrorMessage(getColumNo("faith") + "宗教信仰输入不正确");
		}
		// 文化程度验证
		validatorSchooling(domain.getSchooling(), result, null);

		if (null != domain.getBloodType()
				&& null != domain.getBloodType().getDisplayName()) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.BLOOD_TYPE, domain.getBloodType()
							.getDisplayName()))
				result.addErrorMessage(getColumNo("bloodType") + "血型输入不正确");
		}
		// 政治面貌 验证
		validatorPoliticalBackground(domain.getPoliticalBackground(), result,
				null);
		// 户籍地详址验证
		validatorNativePlaceAddress(domain.getNativePlaceAddress(), result,
				null);
		// 临时居所验证
		validateOtherAddress(domain.getOtherAddress(), result, null);

		if (!validateHelper.emptyString(domain.getEmail())) {
			if (validateHelper.illegalStringLength(0, 50, domain.getEmail())) {
				result.addErrorMessage(getColumNo("email") + "电子邮箱不能输入大于50字符");
			} else if (validateHelper.illegalEmail(domain.getEmail())) {
				result.addErrorMessage(getColumNo("email") + "电子邮箱格式输入不正确");
			}
		}

		if (!validateHelper.emptyString(domain.getOtherAddress())) {
			if (validateHelper.illegalStringLength(0, 50,
					domain.getOtherAddress())) {
				result.addErrorMessage(getColumNo("otherAddress")
						+ "临时居所不能输入大于50字符");
			}
		}
		// 备注验证
		validatorRemark(domain.getRemark(), result, null);
		return result;

	}

	@Override
	public ValidateResult validateSpecializedInfo(UnsettledPopulationTemp domain) {
		ValidateResult result = new ValidateResult();
		if (null != domain.getCertificateType()
				&& !validateHelper.emptyString(domain.getCertificateType()
						.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.CERTIFICATEHOLD_TYPE, domain
							.getCertificateType().getDisplayName())) {
				result.addErrorMessage(getColumNo("certificateType")
						+ "持证类型选择不正确");
			}
		}

		if (!validateHelper.emptyString(domain.getCertificateNo())
				&& validateHelper.illegalStringLength(0, 50,
						domain.getCertificateNo())) {
			result.addErrorMessage(getColumNo("certificateNo")
					+ "持证编号不能输入大于50字符");
		} else if (!validateHelper.emptyString(domain.getCertificateNo())
				&& !validateHelper
						.isConSpeCharacters(domain.getCertificateNo())) {
			result.addErrorMessage(getColumNo("certificateNo") + "持证编号不能包含特殊字符");
		}

		if (null != domain.getUnSettedTime()
				&& !validateHelper.endDateIsSameorBigForStartDate(
						CalendarUtil.getTomorrow(), domain.getUnSettedTime())) {
			result.addErrorMessage(getColumNo("unSettedTime") + "未落户时间不能大于当前时间");
		}

		if (!validateHelper.nullObj(domain.getUnSettedReason())
				&& !validateHelper.emptyString(domain.getUnSettedReason()
						.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.UNSETTEDREASON, domain.getUnSettedReason()
							.getDisplayName())) {
				result.addErrorMessage(getColumNo("unSettedReason")
						+ "未落户原因选择不正确");
			}
		}
		return result;
	}
}
