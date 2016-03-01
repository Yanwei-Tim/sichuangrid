package com.tianque.baseInfo.positiveInfo.validator;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("positiveInfoValidator")
public class PositiveInfoValidatorImpl extends
		AbstractCountrymenValidator<PositiveInfo> implements
		DomainValidator<PositiveInfo> {
	@Autowired
	private ValidateHelper validateHelper;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	@Override
	public ValidateResult validateSpecializedInfo(PositiveInfo domain) {
		ValidateResult validateResult = new ValidateResult();
		
		/** 犯罪行为验证*/
		if(!validateHelper.emptyString(domain.getCriminalBehavior())){
			if(validateHelper.illegalStringLength(0, 300,
					domain.getCriminalBehavior())){
				validateResult.addErrorMessage(getColumNo("criminalBehavior") + "犯罪行为不能输入大于300个字符");
			}
			if(!validateHelper.isConSpeCharacters(domain.getCriminalBehavior())){
				validateResult.addErrorMessage(getColumNo("criminalBehavior") + "犯罪行为不能输入特殊字符");
			}
		}
		
		if (domain.getNation() == null) {
			validateResult.addErrorMessage(getColumNo("nation") + "民族必须选择");
		}
		if (domain.getPoliticalBackground() == null) {
			validateResult.addErrorMessage(getColumNo("politicalBackground")
					+ "政治面貌必须选择");
		}
		if (domain.getMaritalState() == null) {
			validateResult.addErrorMessage(getColumNo("maritalState")
					+ "婚姻状况必须选择");
		}
		if (domain.getSchooling() == null) {
			validateResult
					.addErrorMessage(getColumNo("schooling") + "文化程度必须选择");
		}

		if (!typeIsNotNull(domain.getPositiveInfoType())) {
			validateResult.addErrorMessage(getColumNo("positiveInfoType")
					+ "人员类型必须输入");
		}
		boolean caseReasonEmpty = validateHelper.emptyString(domain
				.getCaseReason());
		if (caseReasonEmpty) {
			validateResult.addErrorMessage(getColumNo("caseReason")
					+ "原罪名（错）必须输入");
		}
		if (!caseReasonEmpty
				&& validateHelper.illegalStringLength(2, 50, domain
						.getCaseReason())) {
			validateResult.addErrorMessage(getColumNo("caseReason")
					+ "原罪名（错）不能小于2个字符并且不能大于50个字符");
		}
		
		if (!caseReasonEmpty && !validateHelper.isConSpeCharacters(domain
						.getCaseReason())) {
			validateResult.addErrorMessage(getColumNo("caseReason")
					+ "原罪名（错）不能含有特殊字符");
		}
		
		if (validateHelper.emptyString(domain.getLaborEduAddress())) {
			validateResult.addErrorMessage(getColumNo("laborEduAddress")
					+ "劳教(服刑)场所必须输入");
		}
		if (!validateHelper.emptyString(domain.getLaborEduAddress())
				&& !validateHelper.isConSpeCharacters(domain.getLaborEduAddress())) {
			validateResult.addErrorMessage(getColumNo("laborEduAddress")
					+ "劳教(服刑)场所不能输入特殊字符");
		}
		if (!validateHelper.emptyString(domain.getLaborEduAddress())
				&& validateHelper.illegalStringLength(2, 50, domain
						.getLaborEduAddress())) {
			validateResult.addErrorMessage(getColumNo("laborEduAddress")
					+ "劳教(服刑)场所不能小于2个字符并且不能大于50个字符");
		}
		if (validateHelper.emptyString(domain.getImprisonmentDate())) {
			validateResult.addErrorMessage(getColumNo("imprisonmentDate")
					+ "原判刑期必须输入");
		}
		if (!validateHelper.emptyString(domain.getImprisonmentDate())
				&& validateHelper.illegalStringLength(2, 16, domain
						.getImprisonmentDate())) {
			validateResult.addErrorMessage(getColumNo("imprisonmentDate")
					+ "原判刑期不能小于2个字符并且不能大于16个字符");
		}
		if (!validateHelper.emptyString(domain.getImprisonmentDate())
				&& !validateHelper.isConSpeCharacters(domain.getImprisonmentDate())) {
			validateResult.addErrorMessage(getColumNo("imprisonmentDate")
					+ "原判刑期不能输入特殊字符");
		}
		
		boolean releaseOrBackDate = validateHelper.nullObj(domain
				.getReleaseOrBackDate());
		if (releaseOrBackDate) {
			validateResult.addErrorMessage(getColumNo("releaseOrBackDate")
					+ "解教（刑释）日期必须输入或格式错误 正确格式例如：1990-10-10");
		}
		if (!releaseOrBackDate
				&& new Date().before(domain.getReleaseOrBackDate())) {
			validateResult.addErrorMessage(getColumNo("releaseOrBackDate")
					+ "解教（刑释）日期不能大于当前系统时间");
		}
		if (!releaseOrBackDate
				&& !validateHelper.nullObj(domain.getResettlementDate())
				&& !validateHelper.endDateIsSameorBigForStartDate(domain
						.getResettlementDate(), domain.getReleaseOrBackDate())) {
			validateResult.addErrorMessage(getColumNo("resettlementDate")
					+ "安置日期不能小于解教（刑释）日期");
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
					&& !validateHelper.endDateIsSameorBigForStartDate(domain
							.getCrimeDate(), domain.getReleaseOrBackDate())) {
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
					+ "本年度重犯，重犯日期不能输入");
		}

		if (!validateHelper.emptyString(domain.getNoResettlementReason())) {
			if(validateHelper.illegalStringLength(0, 50, domain
					.getNoResettlementReason())){
				validateResult.addErrorMessage(getColumNo("noResettlementReason")
						+ "未安置原因不能输入大于50字符");
			}
			if(!validateHelper.isConSpeCharacters(domain.getNoResettlementReason())){
				validateResult.addErrorMessage(getColumNo("noResettlementReason")
						+ "未安置原因不能输入特殊字符");
			}
		}
		if (!validateHelper.nullObj(domain.getResettlementDate())
				&& !validateHelper.nullObj(domain.getNoResettlementReason())) {
			validateResult.addErrorMessage(getColumNo("noResettlementReason")
					+ "不能同时填写安置日期和未安置原因");

		}
		return validateResult;
	}
}
