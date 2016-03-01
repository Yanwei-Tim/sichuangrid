package com.tianque.sms.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.sms.domain.Smsquerycondition;

/**
 * 
 * @author
 * @date 2013-07-03 15:25:55
 */
@Component("smsqueryconditionValidator")
public class SmsqueryconditionValidator implements
		DomainValidator<Smsquerycondition> {

	@Override
	public ValidateResult validate(Smsquerycondition domain) {
		ValidateResult validateResult = new ValidateResult();

		if (null == domain || null == domain.getSmsSendObject()
				|| null == domain.getSmsSendObject().getId()) {
			throw new BusinessValidationException("操作失败，请联系管理员！");
		}
		if (!StringUtil.isStringAvaliable(domain.getDescription())) {
			throw new BusinessValidationException("描述不能为空！");
		}
		if (!StringUtil.isStringAvaliable(domain.getKey())) {
			throw new BusinessValidationException("sql语句不能为空！");
		}
		if (!StringUtil.isStringAvaliable(domain.getKey())) {
			throw new BusinessValidationException("中间key不能为空！");
		}
		if (!StringUtil.isStringAvaliable(domain.getType())) {
			throw new BusinessValidationException("类型不能为空！");
		}
		if ("table".equals(domain.getKey())
				&& !"table".equals(domain.getType())) {
			throw new BusinessValidationException("基础sql语句类型只能为table！");
		}
		if (null == domain.getIsNull() || !domain.getIsNull().booleanValue()) {
			Pattern p = Pattern.compile("\\$\\{(.*?)\\}");
			Matcher m = p.matcher(domain.getSqlTemplate());
			if (!m.find()) {
				throw new BusinessValidationException(
						"sql语句格式不对，正确格式：and name like '${XXX}%'");
			}
		}

		return validateResult;
	}
}