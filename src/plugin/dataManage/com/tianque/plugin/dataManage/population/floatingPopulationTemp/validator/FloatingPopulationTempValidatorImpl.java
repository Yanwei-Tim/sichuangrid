package com.tianque.plugin.dataManage.population.floatingPopulationTemp.validator;

import org.springframework.stereotype.Component;

import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.plugin.dataManage.population.floatingPopulationTemp.domain.FloatingPopulationTemp;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;
import com.tianque.util.DateFormat;

@Component("floatingPopulationTempValidator")
public class FloatingPopulationTempValidatorImpl extends
		AbstactDataManageValidator<FloatingPopulationTemp> {

	@Override
	public ValidateResult validateSpecializedInfo(FloatingPopulationTemp domain) {
		ValidateResult result = new ValidateResult();

		// if (domain.getPoliticalBackground() == null) {
		// result.addErrorMessage(getColumNo("politicalBackground") +
		// "政治面貌必须选择");
		// }
		// if (domain.getMaritalState() == null) {
		// result.addErrorMessage(getColumNo("maritalState") + "婚姻状况必须选择");
		// }
		// if (domain.getSchooling() == null) {
		// result.addErrorMessage(getColumNo("schooling") + "文化程度必须选择");
		// }

		if (domain.getInflowingReason() == null) {
			result.addErrorMessage(getColumNo("inflowingReason") + "流入原因必须输入");
		}
		if (domain.getStayLocationType() == null) {
			result.addErrorMessage(getColumNo("stayLocationType") + "暂住处所必须输入");
		}
		validatorStayLocationType(domain.getStayLocationType(), result);
		if (domain.getInflowingDate() == null) {
			result.addErrorMessage(getColumNo("inflowingDate")
					+ "流入时间必须输入或流入时间输入不正确，正确格式 例如: '1930-01-01'.");
		} else if (validateHelper.endDateIsSameorBigForStartDate(
				domain.getInflowingDate(),
				DateFormat.parseDate(DateFormat.today(), "yyyy-MM-dd"))) {
			result.addErrorMessage(getColumNo("inflowingDate") + "流入时间不能大于今天");
		} else if (null != domain.getBirthday()
				&& validateHelper.endDateIsSameorBigForStartDate(
						domain.getBirthday(), domain.getInflowingDate())) {
			result.addErrorMessage(getColumNo("inflowingDate") + "流入时间不能小于出生日期");
		}

		if (domain.getExpectedDatedue() == null) {
			result.addErrorMessage(getColumNo("expectedDatedue")
					+ "预计到期日期必须输入或预计到期日期输入不正确，正确格式 例如: '1930-01-01'.");
		}

		if (domain.getRegisterDate() != null
				&& validateHelper.endDateIsSameorBigForStartDate(
						domain.getRegisterDate(),
						DateFormat.parseDate(DateFormat.today(), "yyyy-MM-dd"))) {
			result.addErrorMessage(getColumNo("registerDate") + "登记日期不能大于今天");
		} else if (null != domain.getRegisterDate()
				&& null != domain.getInflowingDate()
				&& validateHelper.endDateIsSameorBigForStartDate(
						domain.getInflowingDate(), domain.getRegisterDate())) {
			result.addErrorMessage(getColumNo("registerDate") + "登记日期不能小于流入时间");
		}

		if (domain.getExpectedDatedue() != null
				&& domain.getRegisterDate() != null
				&& domain.getInflowingDate() != null) {
			if (domain.getExpectedDatedue().before(domain.getRegisterDate())) {
				result.addErrorMessage(getColumNo("expectedDatedue")
						+ "预计到期日期不能小于登记日期");
			}
			if (domain.getExpectedDatedue().before(domain.getInflowingDate())) {
				result.addErrorMessage(getColumNo("expectedDatedue")
						+ "预计到期日期不能小于流入时间");
			}
		}

		if (!validateHelper.emptyString(domain.getInflowingProvince())
				&& validateHelper.illegalStringLength(0, 20,
						domain.getInflowingProvince())) {
			result.addErrorMessage(getColumNo("inflowingProvince"),
					"流入来源省不能输入大于20字符");
		}
		if (!validateHelper.emptyString(domain.getInflowingCity())
				&& validateHelper.illegalStringLength(0, 20,
						domain.getInflowingCity())) {
			result.addErrorMessage(getColumNo("inflowingCity"),
					"流入来源市不能输入大于20字符");
		}
		if (!validateHelper.emptyString(domain.getInflowingDistrict())
				&& validateHelper.illegalStringLength(0, 20,
						domain.getInflowingDistrict())) {
			result.addErrorMessage(getColumNo("inflowingDistrict"),
					"流入来源县不能输入大于20字符");
		}
		if (!validateHelper.emptyString(domain.getCertificateNumber())
				&& validateHelper.illegalStringLength(0, 50,
						domain.getCertificateNumber())) {
			result.addErrorMessage(getColumNo("certificateNumber")
					+ "证件编号不能输入大于50字符");
		}
		return result;
	}

	public void validatorStayLocationType(PropertyDict stayLocationType,
			ValidateResult result) {
		if (null != stayLocationType
				&& !validateHelper.emptyString(stayLocationType
						.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.STAY_LOCATION_TYPE,
					stayLocationType.getDisplayName()))
				result.addErrorMessage(getColumNo("stayLocationType")
						+ "暂住处所输入不正确");
		}
	}
}
