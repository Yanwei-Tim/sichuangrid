package com.tianque.sms.validator;

import org.springframework.stereotype.Component;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.sms.domain.Smstrafficmanage;

/**
 * 
 * @author
 * @date 2013-07-02 15:29:10
 */
@Component("smstrafficmanageValidator")
public class SmstrafficmanageValidator implements
		DomainValidator<Smstrafficmanage> {

	@Override
	public ValidateResult validate(Smstrafficmanage domain) {
		ValidateResult validateResult = new ValidateResult();

		// TODO Auto-generated method stub

		return validateResult;
	}
}