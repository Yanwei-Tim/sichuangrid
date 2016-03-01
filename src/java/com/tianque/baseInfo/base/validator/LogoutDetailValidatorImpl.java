package com.tianque.baseInfo.base.validator;

import org.springframework.stereotype.Component;

import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.service.vo.IsEmphasis;

@Component("logoutDetailValidator")
public class LogoutDetailValidatorImpl implements DomainValidator<LogoutDetail> {

	@Override
	public ValidateResult validate(LogoutDetail domain) {
		StringBuffer bf = new StringBuffer();
		ValidateResult validateResult = new ValidateResult();

		if (IsEmphasis.IsNotEmphasis.equals(domain.getLogout())) {
			if (null == domain.getLogoutReason() || domain.getLogoutReason().isEmpty()) {
				validateResult.addErrorMessage(bf.append("注销原因必须输入").toString());
			}
		}

		return validateResult;
	}
}
