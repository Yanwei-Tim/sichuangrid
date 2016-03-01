package com.tianque.validate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.validate.AbstractWorkingValidator;
import com.tianque.xichang.working.poorPeople.domain.PoorPeople;
import com.tianque.xichang.working.poorPeople.service.PoorPeopleService;

/**
 * @ClassName: PoorPeopleValidateImpl
 * @Description: 三本台账-困难群众台账-验证类
 * @author wangxiaohu wsmalltiger@163.com
 * @date Dec 24, 2013 4:44:53 PM
 */
@Service("poorPeopleValidator")
public class PoorPeopleValidateImpl
		extends
			AbstractWorkingValidator<PoorPeople> {

	@Autowired
	private PoorPeopleService poorPeopleService;

	@Override
	public ValidateResult validateSpecializedInfo(PoorPeople domain) {
		ValidateResult result = new ValidateResult();
		validateSerialNumberUnique(domain, result, null);
		validatorName(domain.getName(), result, null);
		validatorIdCardNo(domain.getIdCardNo(), result, null);
		validatorPermanentAddress(domain.getPermanentAddress(), result, null);
		validateHelpInfo(domain.getHelpInfo(), result, null);
		validatePoorBigInfo(domain.getPoorBigInfo(), result, null);
		validatePoorInfo(domain.getPoorInfo(), result, null);
		return result;
	}

	public void validateSerialNumberUnique(PoorPeople poorPeople,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		String message = poorPeopleService.hasDuplicateSerialNumber(poorPeople);
		if (message != null) {
			result
					.addErrorMessage(getColumNo("serialNumber") + apstr
							+ message);
		}
	}
	public void validatePoorInfo(PropertyDict poorInfo, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (null == poorInfo
				|| (null == poorInfo.getId() && validateHelper
						.emptyString(poorInfo.getDisplayName()))) {
			result.addErrorMessage(getColumNo("poorInfo") + apstr
					+ "困难原因（子类）必须输入");
		} else if (null != poorInfo
				&& !validateHelper.emptyString(poorInfo.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.POORINFO, poorInfo.getDisplayName()))
				result.addErrorMessage(getColumNo("poorInfo") + apstr
						+ "困难原因（子类）输入不正确");
		}
	}

	public void validatePoorBigInfo(PropertyDict poorBigInfo,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (null == poorBigInfo
				|| (null == poorBigInfo.getId() && validateHelper
						.emptyString(poorBigInfo.getDisplayName()))) {
			result.addErrorMessage(getColumNo("poorBigInfo") + apstr
					+ "困难原因（大类）必须输入");
		} else if (null != poorBigInfo
				&& !validateHelper.emptyString(poorBigInfo.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.POORBIGINFO, poorBigInfo.getDisplayName()))
				result.addErrorMessage(getColumNo("poorBigInfo") + apstr
						+ "困难原因（大类）输入不正确");
		}
	}

	public void validateHelpInfo(String helpInfo, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (validateHelper.emptyString(helpInfo)) {
			result.addErrorMessage(getColumNo("helpInfo") + apstr + "编号必须输入");
		} else if (validateHelper.illegalStringLength(2, 50, helpInfo)) {
			result.addErrorMessage(getColumNo("helpInfo") + apstr
					+ "编号只能输入2-50个字符");
		}
	}

}
