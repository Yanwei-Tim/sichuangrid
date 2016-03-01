package com.tianque.validate.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tianque.core.util.CollectionUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.validate.AbstractWorkingValidator;
import com.tianque.xichang.working.steadyWork.domain.PeopleInfo;
import com.tianque.xichang.working.steadyWork.domain.SteadyWork;

@Component("steadyWorkValidator")
public class SteadyWorkValidateImpl extends
		AbstractWorkingValidator<SteadyWork> {

	@Override
	public ValidateResult validateSpecializedInfo(SteadyWork domain) {
		ValidateResult result = new ValidateResult();
		validatePeopleInfo(domain.getPeopleInfos(), result, null);
		validateInvolvingSteadyInfo(domain.getInvolvingSteadyInfo(), result,
				null);
		return result;
	}

	private void validatePeopleInfo(List<PeopleInfo> peopleInfos,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!CollectionUtil.isAvaliable(peopleInfos)) {
			result.addErrorMessage("联系人至少有一个");
		} else {
			validatorName(peopleInfos.get(0).getName(), result, "第一联系人");
			validatorIdCardNo(peopleInfos.get(0).getIdCardNo(), result, "第一身份证");
			if (peopleInfos.size() == 2) {
				validatorName(peopleInfos.get(1).getName(), result, "第二联系人");
				validatorIdCardNo(peopleInfos.get(1).getIdCardNo(), result,
						"第二身份证");
			}

		}
	}

	private void validateInvolvingSteadyInfo(String involvingSteadyInfo,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (validateHelper.emptyString(involvingSteadyInfo)) {
			result.addErrorMessage(getColumNo("involvingSteadyInfo") + apstr
					+ "涉稳事项必须输入");
		} else if (!validateHelper.emptyString(involvingSteadyInfo)
				&& validateHelper.illegalStringLength(2, 60,
						involvingSteadyInfo)) {
			result.addErrorMessage(getColumNo("involvingSteadyInfo") + apstr
					+ "涉稳事项只能输入2-60个字符");
		}
	}
}
