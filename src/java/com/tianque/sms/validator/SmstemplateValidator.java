package com.tianque.sms.validator;

import org.springframework.stereotype.Component;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.sms.domain.Smstemplate;

/**
 * 
 * @author
 * @date 2013-07-03 11:27:49
 */
@Component("smstemplateValidator")
public class SmstemplateValidator implements DomainValidator<Smstemplate> {

	@Override
	public ValidateResult validate(Smstemplate domain) {
		ValidateResult validateResult = new ValidateResult();

		// TODO Auto-generated method stub

		return validateResult;
	}
}