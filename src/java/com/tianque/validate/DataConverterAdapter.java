package com.tianque.validate;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.exception.base.OperationFailedException;

public abstract class DataConverterAdapter<T> extends AbstractDataConverter<T>
		implements DomainValidator<T> {

	@Override
	@Deprecated
	public ValidateResult validate(T domain) {
		throw new OperationFailedException("此方法已经不用");
	}
}
