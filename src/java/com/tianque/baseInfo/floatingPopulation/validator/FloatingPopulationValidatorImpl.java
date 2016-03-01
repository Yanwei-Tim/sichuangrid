package com.tianque.baseInfo.floatingPopulation.validator;

import org.springframework.stereotype.Service;

import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.util.DateFormat;
import com.tianque.validate.AbstractCountrymenValidator;

@Service("floatingPopulationValidator")
public class FloatingPopulationValidatorImpl extends
		AbstractCountrymenValidator<FloatingPopulation> {

	@Override
	public void validatorifMaritalState(PropertyDict maritalState,
			ValidateResult result, String apstr) {
		// 重写 验证婚姻
		apstr = apstr == null ? "" : apstr;
		// 说明是导入的
		if (null == maritalState
				|| (null == maritalState.getId() && validateHelper
						.emptyString(maritalState.getDisplayName()))) {
			result.addErrorMessage(getColumNo("maritalState") + apstr
					+ "婚姻状况必须输入");
			return;
		}

		if (null != maritalState
				&& !validateHelper.emptyString(maritalState.getDisplayName())) {
			if (validateHelper
					.illegalPropertyDictDisplayName(
							PropertyTypes.MARITAL_STATUS, maritalState
									.getDisplayName()))
				result.addErrorMessage(getColumNo("maritalState") + apstr
						+ "婚姻状况输入不正确");
		}
	}

	// 重写验证 文化程度

	@Override
	public void validatorSchooling(PropertyDict schooling,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;

		if (null == schooling
				|| (null == schooling.getId() && validateHelper
						.emptyString(schooling.getDisplayName()))) {
			result
					.addErrorMessage(getColumNo("schooling") + apstr
							+ "文化程度必须输入");
			return;
		}

		if (null != schooling
				&& !validateHelper.emptyString(schooling.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.SCHOOLING, schooling.getDisplayName()))
				result.addErrorMessage(getColumNo("schooling") + apstr
						+ "文化程度输入不正确");
		}
	}

	// 重写民族验证
	@Override
	public void validatorNation(PropertyDict nation, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;

		if (null == nation
				|| (null == nation.getId() && validateHelper.emptyString(nation
						.getDisplayName()))) {
			result.addErrorMessage(getColumNo("nation") + apstr + "民族必须输入");
			return;
		}

		if (null != nation
				&& !validateHelper.emptyString(nation.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.NATION, nation.getDisplayName()))
				result
						.addErrorMessage(getColumNo("nation") + apstr
								+ "民族输入不正确");
		}
	}

	// 重写政治面貌验证
	@Override
	public void validatorPoliticalBackground(PropertyDict politicalBackground,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;

		if (null == politicalBackground
				|| (null == politicalBackground.getId() && validateHelper
						.emptyString(politicalBackground.getDisplayName()))) {
			result.addErrorMessage(getColumNo("politicalBackground") + apstr
					+ "政治面貌必须输入");
			return;
		}

		if (null != politicalBackground
				&& !validateHelper.emptyString(politicalBackground
						.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.POLITICAL_BACKGROUND, politicalBackground
							.getDisplayName()))
				result.addErrorMessage(getColumNo("politicalBackground")
						+ apstr + "政治面貌输入不正确");
		}
	}

	@Override
	public ValidateResult validateSpecializedInfo(FloatingPopulation domain) {
		ValidateResult result = new ValidateResult();

		if (domain.getInflowingReason() == null) {
			result.addErrorMessage(getColumNo("inflowingReason") + "流入原因必须输入");
		}

		if (domain.getStayLocationType() == null) {
			result.addErrorMessage(getColumNo("stayLocationType") + "暂住处所必须输入");
		}

		if (domain.getInflowingDate() == null) {
			result.addErrorMessage(getColumNo("inflowingDate")
					+ "流入时间必须输入或流入时间输入不正确，正确格式 例如: '1930-01-01'.");
		} else if (validateHelper.endDateIsSameorBigForStartDate(domain
				.getInflowingDate(), DateFormat.parseDate(DateFormat.getAddDay(
				"date", 1), "yyyy-MM-dd"))) {
			result.addErrorMessage(getColumNo("inflowingDate") + "流入时间不能大于今天");
		} else if (null != domain.getBirthday()
				&& validateHelper.endDateIsSameorBigForStartDate(domain
						.getBirthday(), domain.getInflowingDate())) {
			result
					.addErrorMessage(getColumNo("inflowingDate")
							+ "流入时间不能小于出生日期");
		}

		if (domain.getRegisterDate() != null
				&& validateHelper.endDateIsSameorBigForStartDate(domain
						.getRegisterDate(), DateFormat.parseDate(DateFormat
						.getAddDay("date", 1), "yyyy-MM-dd"))) {
			result.addErrorMessage(getColumNo("registerDate") + "登记日期不能大于今天");
		} else if (null != domain.getRegisterDate()
				&& null != domain.getInflowingDate()
				&& domain.getInflowingDate().after(domain.getRegisterDate())) {
			result.addErrorMessage(getColumNo("registerDate") + "登记日期不能小于流入时间");
		}

		if (domain.getExpectedDatedue() == null) {
			result
					.addErrorMessage(getColumNo("expectedDatedue")
							+ "预计到期日期必须输入或格式错误，正确格式 例如:1990-10-10");
		}

		if (!validateHelper.emptyString(domain.getInflowingProvince())
				&& validateHelper.illegalStringLength(0, 20, domain
						.getInflowingProvince())) {
			result.addErrorMessage(getColumNo("inflowingProvince"),
					"流入来源省不能输入大于20字符");
		}
		if (!validateHelper.emptyString(domain.getInflowingCity())
				&& validateHelper.illegalStringLength(0, 20, domain
						.getInflowingCity())) {
			result.addErrorMessage(getColumNo("inflowingCity"),
					"流入来源市不能输入大于20字符");
		}
		if (!validateHelper.emptyString(domain.getInflowingDistrict())
				&& validateHelper.illegalStringLength(0, 20, domain
						.getInflowingDistrict())) {
			result.addErrorMessage(getColumNo("inflowingDistrict"),
					"流入来源县不能输入大于20字符");
		}
		if (!validateHelper.emptyString(domain.getCertificateNumber())
				&& validateHelper.illegalStringLength(0, 50, domain
						.getCertificateNumber())) {
			result.addErrorMessage(getColumNo("certificateNumber")+
					"证件编号不能输入大于50字符");
		}

		return result;
	}
}
