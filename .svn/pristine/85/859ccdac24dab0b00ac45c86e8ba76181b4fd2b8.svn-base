package com.tianque.plugin.dataManage.population.dangerousGoodsPractitionerTemp.validator;

import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.population.dangerousGoodsPractitionerTemp.domain.DangerousGoodsPractitionerTemp;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;

@Component("dangerousGoodsPractitionerTempValidator")
public class DangerousGoodsPractitionerTempValidatorImpl extends
		AbstactDataManageValidator<DangerousGoodsPractitionerTemp> {

	@Override
	public ValidateResult validateSpecializedInfo(
			DangerousGoodsPractitionerTemp domain) {
		ValidateResult validateResult = new ValidateResult();
		if (null == domain.getDangerousWorkingType()
				|| null == domain.getDangerousWorkingType().getId()) {
			validateResult.addErrorMessage(getColumNo("dangerousWorkingType")
					+ "危险从业类别必须输入");
		}
		if (validateHelper.emptyString(domain.getLegalPerson())) {
			// validateResult.addErrorMessage(bf.append(getColumNo("legalPerson")).append("所在单位法人代表必须输入").toString());
		} else if (validateHelper.illegalStringLength(2, 20,
				domain.getLegalPerson())) {
			validateResult.addErrorMessage(getColumNo("legalPerson")
					+ "所在单位法人代表输入2-20个字符");
		} else if (!validateHelper.isConSpeCharacters(domain.getLegalPerson())) {
			validateResult.addErrorMessage(getColumNo("legalPerson")
					+ "所在单位法人代表名字不能包含特殊字符");
		} else if (validateHelper.illegalExculdeParticalChar(domain
				.getLegalPerson())) {
			validateResult.addErrorMessage(getColumNo("legalPerson")
					+ "所在单位法人代表名字只能输入数字,字母,中文字符");
		}
		if (validateHelper.emptyString(domain.getLegalPersonMobileNumber())) {
			// validateResult.addErrorMessage(bf.append(getColumNo("legalPersonMobileNumber")).append("所在单位法人代表联系手机不能为空").toString());
		} else if (validateHelper.illegalMobilePhone(domain
				.getLegalPersonMobileNumber())) {
			validateResult
					.addErrorMessage(getColumNo("legalPersonMobileNumber")
							+ "所在单位法人代表联系手机只能输入1开头的11位数字");
		}
		if (validateHelper.emptyString(domain.getLegalPersonTelephone())) {
			// validateResult.addErrorMessage(bf.append(getColumNo("legalPersonTelephone")).append("所在单位法人代表联系电话不能为空").toString());
		} else if (validateHelper.illegalStringLength(2, 20,
				domain.getLegalPersonTelephone())) {
			validateResult.addErrorMessage(getColumNo("legalPersonTelephone")
					+ "所在单位法人代表联系电话不能小于2个字符,不能超过20个字符");
		} else if (validateHelper.illegalTelephone(domain
				.getLegalPersonTelephone())) {
			validateResult.addErrorMessage(getColumNo("legalPersonTelephone")
					+ "所在单位法人代表联系电话只能输入数字和-");
		}

		if (validateHelper.emptyString(domain.getTelephone())) {
		} else if (validateHelper.illegalStringLength(2, 20,
				domain.getTelephone())) {
			validateResult.addErrorMessage(getColumNo("telephone")
					+ "所在单位法人代表联系电话不能小于2个字符,不能超过20个字符");
		} else if (validateHelper.illegalTelephone(domain.getTelephone())) {
			validateResult.addErrorMessage(getColumNo("telephone")
					+ "所在单位法人代表联系电话只能输入数字和-");
		}

		if (null != domain.getPeriodOfValidityStart()
				&& null != domain.getBirthday()
				&& !validateHelper
						.endDateIsSameorBigForStartDate(
								domain.getPeriodOfValidityStart(),
								domain.getBirthday())) {
			validateResult.addErrorMessage(getColumNo("periodOfValidityStart")
					+ "从业资格证的有效开始日期不能小于出生日期");
		}
		if (null != domain.getPeriodOfValidityStart()
				&& validateHelper.endDateIsSameorBigForStartDate(
						domain.getPeriodOfValidityStart(),
						CalendarUtil.now("yyyy-MM-dd"))) {
			validateResult.addErrorMessage(getColumNo("periodOfValidityStart")
					+ "从业资格证的有效开始日期不能大于当前日期");
		}
		if (null != domain.getPeriodOfValidityStart()
				&& null != domain.getPeriodOfValidityEnd()
				&& validateHelper.endDateIsSameorBigForStartDate(
						domain.getPeriodOfValidityStart(),
						domain.getPeriodOfValidityEnd())) {
			validateResult.addErrorMessage(getColumNo("periodOfValidityEnd")
					+ "从业资格证的有效结束日期不能小于有效开始日期");
		}
		if (null != domain.getWorkingCertificate()) {
			if (validateHelper.illegalStringLength(0, 20,
					domain.getWorkingCertificate())) {
				validateResult.addErrorMessage(getColumNo("workingCertificate")
						+ "从业资格证书不能超过20个字符");
			}
			if (validateHelper.illegalExculdeParticalChar(domain
					.getWorkingCertificate())) {
				validateResult.addErrorMessage(getColumNo("workingCertificate")
						+ "从业资格证书不能输入非法字符");
			}
		}
		if (null != domain.getWorkingCertificateNo()
				&& validateHelper.illegalStringLength(0, 20,
						domain.getWorkingCertificateNo())) {
			validateResult.addErrorMessage(getColumNo("workingCertificateNo")
					+ "从业资格证书号不能超过20个字符");
		}
		return validateResult;
	}
}
