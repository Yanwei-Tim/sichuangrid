package com.tianque.plugin.taskList.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.taskList.domain.TermerRecord;

@Component("termerRecordValidator")
public class TermerRecordValidatorImpl implements DomainValidator<TermerRecord> {
	@Autowired
	public ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(TermerRecord domain) {
		ValidateResult result = new ValidateResult();
		if (domain.getRecordDate() == null) {
			result.addErrorMessage("时间不能为空！");
		}
		if (validateHelper.emptyString(domain.getAddress())) {
			result.addErrorMessage("地点不能为空！");
		} else if (validateHelper.illegalStringLength(1, 50, domain.getAddress())) {
			result.addErrorMessage("地点不能输入超过50个字符！");
		}
		if (validateHelper.emptyString(domain.getName())) {
			result.addErrorMessage("姓名不能为空！");
		} else if (validateHelper.illegalStringLength(1, 10, domain.getName())) {
			result.addErrorMessage("姓名不能输入超过10个字符！");
		}
		if (validateHelper.emptyString(domain.getExceptionSituationInfo())) {

		} else if (validateHelper.illegalStringLength(1, 250, domain.getExceptionSituationInfo())) {
			result.addErrorMessage("异常情况不能输入超过250个字符！");
		}
		if (validateHelper.emptyString(domain.getMark())) {

		} else if (validateHelper.illegalStringLength(0, 300, domain.getMark())) {
			result.addErrorMessage("备注不能输入超过300个字符！");
		}
//		if(domain.getHelpPeople()==null){
//			result.addErrorMessage("帮扶人员不能为空！");
//		}
		if(domain.getIdCard()!=null&&!domain.getIdCard().equals("")&&
				validateHelper.illegalIdcard(domain.getIdCard())){
			result.addErrorMessage("身份证输入不合法！");
		}
		return result;
	}

}
