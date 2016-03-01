package com.tianque.plugin.dataManage.population.druggyTemp.validator;

import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.population.druggyTemp.domain.DruggyTemp;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;

@Component("druggyTempValidator")
public class DruggyTempValidatorImpl extends
		AbstactDataManageValidator<DruggyTemp> {

	@Override
	public ValidateResult validateSpecializedInfo(DruggyTemp domain) {
		ValidateResult validateResult = new ValidateResult();
		if (null == domain.getDetoxicateCase()
				|| null == domain.getDetoxicateCase().getId()) {
			validateResult.addErrorMessage(getColumNo("detoxicateCase")
					+ "戒毒情况必须输入");
		}
		if (!validateHelper.nullObj(domain.getBirthday())
				&& !validateHelper.nullObj(domain.getFerretOutDate())
				&& !validateHelper.endDateIsSameorBigForStartDate(
						domain.getFerretOutDate(), domain.getBirthday())) {
			validateResult.addErrorMessage(getColumNo("ferretOutDate")
					+ "查获日期不能小于出生日期");
		}
		if (!validateHelper.nullObj(domain.getBirthday())
				&& !validateHelper.nullObj(domain.getDrugFristDate())
				&& !validateHelper.endDateIsSameorBigForStartDate(
						domain.getDrugFristDate(), domain.getBirthday())) {
			validateResult.addErrorMessage(getColumNo("drugFristDate")
					+ "首吸日期不能小于出生日期");
		}
		if (!validateHelper.nullObj(domain.getDrugFristDate())
				&& !validateHelper.endDateIsSameorBigForStartDate(
						CalendarUtil.getTomorrow(), domain.getDrugFristDate())) {
			validateResult.addErrorMessage(getColumNo("drugFristDate")
					+ "首吸日期不能大于当前日期");
		}
		if (!validateHelper.nullObj(domain.getFerretOutDate())
				&& !validateHelper.nullObj(domain.getDrugFristDate())
				&& !validateHelper.endDateIsSameorBigForStartDate(
						domain.getFerretOutDate(), domain.getDrugFristDate())) {
			validateResult.addErrorMessage(getColumNo("ferretOutDate")
					+ "查获日期不能小于首吸日期");
		}
		if (!validateHelper.nullObj(domain.getFerretOutDate())
				&& !validateHelper.endDateIsSameorBigForStartDate(
						CalendarUtil.getTomorrow(), domain.getFerretOutDate())) {
			validateResult.addErrorMessage(getColumNo("ferretOutDate")
					+ "查获日期不能大于当前日期");
		}
		if (!validateHelper.emptyString(domain.getControlActuality())) {
			if(!validateHelper.isConSpeCharacters(domain
							.getControlActuality())){
				validateResult.addErrorMessage(getColumNo("controlActuality")
						+ "管控现状不能含有特殊字符 ");
			}
			if (validateHelper.illegalStringLength(0, 50,
					domain.getControlActuality())) {
				validateResult.addErrorMessage(getColumNo("controlActuality")
						+ "管控现状不能大于50个字符");
			}
		}
		if (!validateHelper.emptyString(domain.getDrugType())) {
			if(!validateHelper.isConSpeCharacters(domain.getDrugType())){
				validateResult.addErrorMessage(getColumNo("drugType")
						+ "滥用毒品种类不能含有特殊字符 ");
			}
			if(validateHelper.illegalStringLength(0, 50,
					domain.getDrugType())){
				validateResult.addErrorMessage(getColumNo("drugType")
						+ "滥用毒品种类不能大于50个字符");
			}
		}
		return validateResult;
	}

}
