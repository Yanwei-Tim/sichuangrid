package com.tianque.core.util;

import org.springframework.stereotype.Component;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;

@Component("serviceHelper")
public class ServiceHelper<T extends BaseDomain> {

	public void checkEntityWhenAdd(T entity, DomainValidator validator) {
		ValidateResult validateResult = validator.validate(entity);
		if (validateResult.hasError()) {
			throw new BusinessValidationException(
					validateResult.getErrorMessages());
		}
	}

	public void checkEntityWhenUpdate(T entity, DomainValidator validator) {
		ValidateResult validateResult = validator.validate(entity);
		if (validateResult.hasError()) {
			throw new BusinessValidationException(
					validateResult.getErrorMessages());
		}
	}
}
