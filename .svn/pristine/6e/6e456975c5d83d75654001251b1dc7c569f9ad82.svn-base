package com.tianque.peopleLog.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.peopleLog.domain.PeopleLog;

@Component("peopleLogValidator")
public class PeopleLogValidatorImpl implements DomainValidator<PeopleLog> {

	@Autowired
	private ValidateHelper validateHelper;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	@Override
	public ValidateResult validate(PeopleLog domain) {
		ValidateResult validateResult = new ValidateResult();

		if (validateHelper.emptyString(domain.getBelonger())) {
			validateResult.addErrorMessage("日志所属人不能为空");
		}
		if (!validateHelper.emptyString(domain.getTitle())
				&& validateHelper.illegalStringLength(0, 30, domain.getTitle())) {
			validateResult.addErrorMessage("日志标题不能大于30字符");
		}
		if (!validateHelper.emptyString(domain.getContents())
				&& validateHelper.illegalStringLength(0, 300, domain.getContents())) {
			validateResult.addErrorMessage("日志内容不能大于300字符");
		}
		return validateResult;
	}

}
