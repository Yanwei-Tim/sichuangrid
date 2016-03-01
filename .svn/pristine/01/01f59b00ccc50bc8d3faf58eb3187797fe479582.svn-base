package com.tianque.baseInfo.rectificativePerson.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.positiveInfo.service.PositiveInfoService;
import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.validate.ValidateResult;
import com.tianque.util.DateFormat;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("rectificativePersonValidator")
public class RectificativeValidateImpl extends
		AbstractCountrymenValidator<RectificativePerson> {

	@Autowired
	private PositiveInfoService positiveInfoService;

	@Override
	public ValidateResult validateSpecializedInfo(RectificativePerson domain) {
		ValidateResult validateResult = new ValidateResult();
		if (positiveInfoService.hasDuplicatePosiviteInfos(domain
				.getOrganization().getId(), domain.getIdCardNo(), null)) {
			validateResult.addErrorMessage(getColumNo("idCardNo")
					+ "该人员已存在于刑事解教人员中");
		}
		if (validateHelper.nullObj(domain.getAccusation())) {
			validateResult.addErrorMessage(getColumNo("accusation") + "罪名必须输入");
		}
		if (validateHelper.illegalStringLength(0, 50, domain.getAccusation())) {
			validateResult.addErrorMessage(getColumNo("accusation")
					+ "罪名不能输入大于50个字符");
		}
		if (validateHelper.nullObj(domain.getRectifyStartDate())) {
			validateResult.addErrorMessage(getColumNo("rectifyStartDate")
					+ "社区矫正开始日期必须输入");
		}
		if (validateHelper.nullObj(domain.getRectifyEndDate())) {
			validateResult.addErrorMessage(getColumNo("rectifyEndDate")
					+ "社区矫正结束日期必须输入");
		}
		if (!validateHelper.nullObj(domain.getRectifyEndDate())
				&& !validateHelper.nullObj(domain.getRectifyStartDate())
				&& !validateHelper.endDateIsSameorBigForStartDate(
						domain.getRectifyEndDate(),
						domain.getRectifyStartDate())) {
			validateResult.addErrorMessage(getColumNo("rectifyEndDate")
					+ "社区矫正结束日期不能小于社区矫正开始日期");
		}
		if (!ConstantsProduct.SourcesState.CLAIM
				.equals(domain.getSourcesState())
				&& !validateHelper.nullObj(domain.getRectifyEndDate())
				&& !validateHelper.endDateIsSameorBigForStartDate(domain
						.getRectifyEndDate(), DateFormat.parseDate(
						DateFormat.getAddDay("date", 1), "yyyy-MM-dd"))) {
			validateResult.addErrorMessage(getColumNo("rectifyEndDate")
					+ "社区矫正结束日期不能小于今天");
		}
		if (!validateHelper.nullObj(domain.getBirthday())
				&& !validateHelper.nullObj(domain.getRectifyStartDate())
				&& !validateHelper.endDateIsSameorBigForStartDate(
						domain.getRectifyStartDate(), domain.getBirthday())) {
			validateResult.addErrorMessage(getColumNo("rectifyStartDate")
					+ "社区矫正开始日期不能小于出生日期");
		}
		if (domain.getExecuteType() == null) {
			validateResult.addErrorMessage(getColumNo("executeType")
					+ "执法类别必须输入");
		}
		if (!validateHelper.emptyString(domain.getPenaltyTerm()) &&
				validateHelper.illegalStringLength(0, 20, domain.getPenaltyTerm())) {
			validateResult.addErrorMessage(getColumNo("penaltyTerm")
					+ "原判刑期不能输入大于20个字符");
		}
		if (!validateHelper.emptyString(domain.getRecentSituation()) &&
				validateHelper.illegalStringLength(0, 300,
				domain.getRecentSituation())) {
			validateResult.addErrorMessage(getColumNo("recentSituation")
					+ "近况描述不能输入大于300个字符");
		}
		return validateResult;
	}
}
