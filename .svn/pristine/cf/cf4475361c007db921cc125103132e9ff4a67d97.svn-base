package com.tianque.baseInfo.base.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.base.domain.UpdateIdcardNoLog;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;

/**
 * @Description:身份证号码修改日志验证类
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-31 下午11:39:45
 */
@Component("updateIdcardNoLogValidator")
public class UpdateIdcardNoLogValidatorImpl implements
		DomainValidator<UpdateIdcardNoLog> {
	@Autowired
	private ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(UpdateIdcardNoLog domain) {
		ValidateResult result = new ValidateResult();
		if (domain.getDataId() == null) {
			result.addErrorMessage("修改的数据的id不能为空");
		}
		if (validateHelper.emptyString(domain.getDataType())) {
			result.addErrorMessage("修改的数据的类型不能为空");
		}
		if (validateHelper.emptyString(domain.getDataBeforeOperate())) {
			result.addErrorMessage("修改前的身份证号码不能为空");
		}
		if (validateHelper.emptyString(domain.getDataAfterOperate())) {
			result.addErrorMessage("修改后的身份证号码不能为空");
		}
		return result;
	}

}
