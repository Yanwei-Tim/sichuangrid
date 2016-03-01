package com.tianque.working.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.working.domain.WorkingRecord;

@Component("workingRecordValidator")
public class WorkingRecordValidatorImpl implements DomainValidator<WorkingRecord> {

	@Autowired
	public ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(WorkingRecord domain) {
		ValidateResult result = new ValidateResult();
		if (validateHelper.emptyString(domain.getName())) {
			result.addErrorMessage("名称必须输入");
		} else {
			if (validateHelper.illegalStringLength(0, 50, domain.getName())) {
				result.addErrorMessage("名称不能大于50个字符");
			}
		}
		if (domain.getDailyLogType().getId() == null) {
			result.addErrorMessage("类型必须选择");
		}
		return result;
	}

}
