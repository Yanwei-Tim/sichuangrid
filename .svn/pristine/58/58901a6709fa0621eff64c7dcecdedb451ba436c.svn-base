package com.tianque.baseInfo.superiorVisit.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisitHistory;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;

@Component("SuperiorVisitHistoryValidator")
public class SuperiorVisitHistoryValidatorImpl implements
		DomainValidator<SuperiorVisitHistory> {
	@Autowired
	public ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(SuperiorVisitHistory domain) {
		ValidateResult result = new ValidateResult();
		if (domain.getVisitDate() == null) {
			result.addErrorMessage("服务时间必须输入");
		}
		if (validateHelper.emptyString(domain.getVisitReason())) {
			result.addErrorMessage("上访原因必须输入");
		}
		if (!validateHelper.emptyString(domain.getVisitReason())
				&& validateHelper.illegalStringLength(0, 400,
						domain.getVisitReason())) {
			result.addErrorMessage("上访原因不能大于400个字符");
		}
		if (null == domain.getVisitState()) {
			result.addErrorMessage("上访状态必须输入");
		}
		return result;
	}
}
