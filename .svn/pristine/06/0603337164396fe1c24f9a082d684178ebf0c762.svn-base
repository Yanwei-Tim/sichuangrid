package com.tianque.plugin.dataManage.population.aidsPopulationsTemp.validator;

import org.springframework.stereotype.Component;

import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.population.aidsPopulationsTemp.domain.AidspopulationsTemp;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;

@Component("aidspopulationsTempValidator")
public class AidsPopulationsTempValidatorImpl extends
		AbstactDataManageValidator<AidspopulationsTemp> {

	@Override
	public ValidateResult validateSpecializedInfo(AidspopulationsTemp domain) {
		ValidateResult validateResult = new ValidateResult();
		if (null == domain.getInfectway()
				|| null == domain.getInfectway().getId()) {
			validateResult
					.addErrorMessage(getColumNo("infectway") + "感染途径必须输入");
		}
		if (null == domain.getViolationsofthelaw()
				|| null == domain.getViolationsofthelaw().getId()) {
			validateResult.addErrorMessage(getColumNo("violationsofthelaw")
					+ "违法情况必须输入");
		}
		if (null == domain.getCrimetype()
				|| null == domain.getCrimetype().getId()) {
			validateResult
					.addErrorMessage(getColumNo("crimetype") + "犯罪类型必须输入");
		}
		if (null == domain.getReceivedlevel()
				|| null == domain.getReceivedlevel().getId()) {
			validateResult.addErrorMessage(getColumNo("receivedlevel")
					+ "收治机构所属层级必须输入");
		}
		if (validateHelper.emptyString(domain.getReceivedorganization())) {
			validateResult.addErrorMessage(getColumNo("receivedorganization")
					+ "收治机构必须输入");
		} else {
			if (validateHelper.illegalStringLength(0, 50,
					domain.getReceivedorganization())) {
				validateResult
						.addErrorMessage(getColumNo("receivedorganization")
								+ "收治机构输入不能大于50个字符");
			}
		}

		return validateResult;
	}

}
