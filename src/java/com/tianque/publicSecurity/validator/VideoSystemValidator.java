package com.tianque.publicSecurity.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.publicSecurity.domain.VideoSystem;

/**
 * 监控视频 
 */
@Component("videoSystemValidator")
public class VideoSystemValidator implements DomainValidator<VideoSystem> {

	@Autowired
	private ValidateHelper validateHelper;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	@Override
	public ValidateResult validate(VideoSystem domain) {
		ValidateResult validateResult = new ValidateResult();
		if (validateHelper.emptyString(domain.getVideoNo())) {
			validateResult.addErrorMessage("监控视频编号必须输入");
		}
		if (!validateHelper.emptyString(domain.getAddress())
				&& validateHelper.illegalStringLength(0, 30, domain.getAddress())) {
			validateResult.addErrorMessage("地址只能输入0-30个字符");
		}
		return validateResult;
	}
}