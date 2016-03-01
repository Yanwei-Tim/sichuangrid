package com.tianque.plugin.dataManage.population.positiveInfoTemp.validator;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.rectificativePerson.service.RectificativePersonService;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.population.positiveInfoTemp.domain.PositiveInfoTemp;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;

@Component("positiveInfoTempValidator")
public class PositiveInfoTempValidatorImpl extends
		AbstactDataManageValidator<PositiveInfoTemp> {

	@Autowired
	private RectificativePersonService rectificativePersonService;

	@Override
	public ValidateResult validateSpecializedInfo(PositiveInfoTemp domain) {
		ValidateResult validateResult = new ValidateResult();
		if (rectificativePersonService.hasDuplicateRectificativePerson(domain
				.getOrganization().getId(), domain.getIdCardNo(), null)) {
			validateResult.addErrorMessage(getColumNo("idCardNo")
					+ "该人员已存在于社区矫正人员中");
		}
		boolean caseReasonEmpty = validateHelper.emptyString(domain
				.getCaseReason());
		if (!caseReasonEmpty
				&& validateHelper.illegalStringLength(2, 50,
						domain.getCaseReason())) {
			validateResult.addErrorMessage(getColumNo("caseReason")
					+ "原罪名（错）不能小于2个字符并且不能大于50个字符");
		}
		if (!validateHelper.emptyString(domain.getLaborEduAddress())
				&& validateHelper.illegalStringLength(2, 20,
						domain.getLaborEduAddress())) {
			validateResult.addErrorMessage(getColumNo("laborEduAddress")
					+ "劳教(服刑)场所不能小于2个字符并且不能大于20个字符");
		}
		if (!validateHelper.emptyString(domain.getImprisonmentDate())
				&& validateHelper.illegalStringLength(0, 16,
						domain.getImprisonmentDate())) {
			validateResult.addErrorMessage(getColumNo("imprisonmentDate")
					+ "原判刑期不能小于2个字符并且不能大于20个字符");
		}
		boolean releaseOrBackDate = validateHelper.nullObj(domain
				.getReleaseOrBackDate());
		if (!releaseOrBackDate
				&& new Date().before(domain.getReleaseOrBackDate())) {
			validateResult.addErrorMessage(getColumNo("releaseOrBackDate")
					+ "解教（刑释）日期不能大于当前系统时间");
		}
		if (!releaseOrBackDate
				&& !validateHelper.nullObj(domain.getResettlementDate())
				&& !validateHelper.endDateIsSameorBigForStartDate(
						domain.getResettlementDate(),
						domain.getReleaseOrBackDate())) {
			validateResult.addErrorMessage(getColumNo("resettlementDate")
					+ "安置日期不能小于解教（刑释）日期");
		}
		if (domain.getResettlementDate() != null) {
			if (domain.getResettlementDate().after(new Date())) {
				validateResult.addErrorMessage(getColumNo("resettlementDate")
						+ "安置日期不能大于当前时间");
			}
		}
		boolean crimeDate = validateHelper.nullObj(domain.getCrimeDate());
		boolean isCrime = false;
		if (validateHelper.nullObj(domain.getIsCrime())) {
			isCrime = false;
		} else {
			isCrime = domain.getIsCrime();
		}
		if (isCrime) {
			if (crimeDate) {
				validateResult.addErrorMessage(getColumNo("crimeDate")
						+ "本年度重犯，重犯日期必须输入");
			}
			if (!releaseOrBackDate
					&& !crimeDate
					&& !validateHelper.endDateIsSameorBigForStartDate(
							domain.getCrimeDate(),
							domain.getReleaseOrBackDate())) {
				validateResult.addErrorMessage(getColumNo("crimeDate")
						+ "重犯日期不能小于解教（刑释）日期");
			}
			if (!crimeDate && new Date().before(domain.getCrimeDate())) {
				validateResult.addErrorMessage(getColumNo("crimeDate")
						+ "重犯日期不能大于当前系统时间");
			}
		}

		if (!isCrime && !crimeDate) {
			validateResult.addErrorMessage(getColumNo("crimeDate")
					+ "本年度没有重犯，重犯日期不能输入");
		}

		if (validateHelper.illegalStringLength(0, 50,
				domain.getNoResettlementReason())) {
			validateResult.addErrorMessage(getColumNo("noResettlementReason")
					+ "未安置原因不能输入大于50字符");
		}
		if (!validateHelper.nullObj(domain.getResettlementDate())
				&& !validateHelper.nullObj(domain.getNoResettlementReason())) {
			validateResult.addErrorMessage(getColumNo("noResettlementReason")
					+ "不能同时填写安置日期和未安置原因");

		}
		return validateResult;
	}
}
