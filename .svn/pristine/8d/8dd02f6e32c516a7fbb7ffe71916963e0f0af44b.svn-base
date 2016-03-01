package com.tianque.plugin.taskList.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.taskList.domain.Receipt;

@Component("receiptValidator")
public class ReceiptValidatorImpl implements DomainValidator<Receipt> {
	@Autowired
	public ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(Receipt domain) {
		ValidateResult result = new ValidateResult();
		if (validateHelper.emptyString(domain.getAttitude())) {

		} else if (validateHelper.illegalStringLength(0, 200, domain.getAttitude())) {
			result.addErrorMessage("签收不能输入超过200个字符！");
		}
		return result;
	}

}
