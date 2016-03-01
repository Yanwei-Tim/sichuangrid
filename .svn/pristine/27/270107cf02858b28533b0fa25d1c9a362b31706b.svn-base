package com.tianque.plugin.dataManage.population.rectificativePersonTemp.validator;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.population.rectificativePersonTemp.domain.RectificativePersonTemp;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;

@Component("rectificativePersonTempValidator")
public class RectificativePersonTempValidatorImpl extends
		AbstactDataManageValidator<RectificativePersonTemp> {

	@Override
	public ValidateResult validateSpecializedInfo(RectificativePersonTemp domain) {
		ValidateResult validateResult = new ValidateResult();
		// if (validateHelper.nullObj(domain.getAccusation())) {
		// validateResult.addErrorMessage(getColumNo("accusation") + "罪名必须输入");
		// }
		if (!validateHelper.emptyString(domain.getAccusation())
				&& validateHelper.illegalStringLength(0, 50,
						domain.getAccusation())) {
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
		if (!validateHelper.nullObj(domain.getRectifyStartDate())
				&& !validateHelper.nullObj(domain.getRectifyEndDate())) {
			if (domain.getRectifyStartDate().after(new Date())) {
				validateResult.addErrorMessage(getColumNo("rectifyStartDate")
						+ "社区矫正开始日期不能大于当前时间");
			}
			if (domain.getRectifyEndDate().before(new Date())) {
				validateResult.addErrorMessage(getColumNo("rectifyEndDate")
						+ "社区矫正结束日期不能小于等于当前时间");
			}
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
		if (null != domain.getPenaltyTerm()
				&& validateHelper.illegalStringLength(0, 20,
						domain.getPenaltyTerm())) {
			validateResult.addErrorMessage(getColumNo("penaltyTerm")
					+ "原判刑期不能输入大于20个字符");
		}
		if (null != domain.getRecentSituation()
				&& validateHelper.illegalStringLength(0, 100,
						domain.getRecentSituation())) {
			validateResult.addErrorMessage(getColumNo("recentSituation")
					+ "近况描述不能输入大于300个字符");
		}
		return validateResult;
	}

}
