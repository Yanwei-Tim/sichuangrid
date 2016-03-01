package com.tianque.eventSource.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.eventSource.domain.EventSource;

@Component("eventSourceValidator")
public class EventSourceValidator implements DomainValidator<EventSource> {
	@Autowired
	protected ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(EventSource domain) {
		ValidateResult result = new ValidateResult();
		if (validateHelper.emptyString(domain.getTitle())) {
			result.addErrorMessage("标题不能为空");
		} else if (validateHelper.illegalStringLength(0, 20, domain.getTitle())) {
			result.addErrorMessage("标题长度不能大于20");
		}
		if (validateHelper.emptyString(domain.getContent())) {
			result.addErrorMessage("内容不能为空");
		} else if (validateHelper.illegalStringLength(0, 200, domain.getContent())) {
			result.addErrorMessage("内容长度不能大于200");
		}
		if (domain.getSourceType() == null) {
			result.addErrorMessage("来源方式未知");
		}
		if (domain.getSourceDate() == null) {
			result.addErrorMessage("来源时间不确定");
		}
		return result;
	}

}
