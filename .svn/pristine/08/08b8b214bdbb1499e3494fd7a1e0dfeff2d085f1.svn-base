package com.tianque.aidsPopulations.validator;

import org.springframework.stereotype.Component;

import com.tianque.aidsPopulations.domain.Aidspopulations;
import com.tianque.core.validate.ValidateResult;
import com.tianque.validate.AbstractCountrymenValidator;

/**
 * 
 * @author liaoshanshan
 * @date 2013-06-09 16:34:04
 */
@Component("aidspopulationsValidator")
public class AidspopulationsValidatorImpl extends
		AbstractCountrymenValidator<Aidspopulations> {

	@Override
	public ValidateResult validateSpecializedInfo(Aidspopulations domain) {

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
			if (validateHelper.illegalStringLength(0, 30, domain
					.getReceivedorganization())) {
				validateResult
						.addErrorMessage(getColumNo("receivedorganization")
								+ "收治机构输入不能大于30个字符");
			}else if (validateHelper.illegalExculdeParticalChar(domain.getReceivedorganization())) {
				validateResult.addErrorMessage(getColumNo("receivedorganization")
						+ "收治机构不能输入特殊字符");
			}
		}
		

		return validateResult;
	}
}