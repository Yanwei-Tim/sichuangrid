package com.tianque.peopleLog.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.peopleLog.domain.CommentLog;

@Component("commentLogValidator")
public class CommentLogValidatorImpl implements DomainValidator<CommentLog> {

	@Autowired
	private ValidateHelper validateHelper;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	@Override
	public ValidateResult validate(CommentLog domain) {
		ValidateResult validateResult = new ValidateResult();
		if (!validateHelper.emptyString(domain.getComments())
				&& validateHelper.illegalStringLength(0, 300, domain.getComments())) {
			validateResult.addErrorMessage("日志内容不能大于150字符");
		}
		return validateResult;
	}

}
