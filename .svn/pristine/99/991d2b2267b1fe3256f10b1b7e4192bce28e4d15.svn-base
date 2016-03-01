package com.tianque.plugin.serviceTeam.serviceRecord.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecord;

@Component("serviceRecordValidator")
public class ServiceRecordValidatorImpl implements DomainValidator<ServiceRecord> {
	@Autowired
	public ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(ServiceRecord domain) {
		ValidateResult result = new ValidateResult();
		if (domain.getOccurDate() == null) {
			result.addErrorMessage("服务时间必须输入");
		}
		if (validateHelper.emptyString(domain.getOccurPlace())) {
			result.addErrorMessage("发生地点必须输入");
		}
		return result;
	}

}
