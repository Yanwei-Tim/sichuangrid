package com.tianque.plugin.taskList.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.taskList.domain.ExceptionalSituationRecord;

/**
 * 异常情况报告验证类
 * @author lanhaifeng
 *
 */
@Component("exceptionalSituationRecordValidator")
public class ExceptionalSituationRecordValidatorImpl implements
		DomainValidator<ExceptionalSituationRecord> {
	@Autowired
	public ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(ExceptionalSituationRecord domain) {
		ValidateResult result = new ValidateResult();
		if (domain.getRecordDate() == null) {
			result.addErrorMessage("时间不能为空！");
		}
		if (validateHelper.emptyString(domain.getAddress())) {
			result.addErrorMessage("地点不能为空！");
		}
		return result;
	}

}
