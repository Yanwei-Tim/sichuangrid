package com.tianque.plugin.taskList.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.taskList.domain.DruggyTask;

@Component("druggyTaskValidator")
public class DruggyTaskValidatorImpl implements DomainValidator<DruggyTask> {
	@Autowired
	public ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(DruggyTask domain) {
		ValidateResult result = new ValidateResult();
		if (domain.getTime() == null) {
			result.addErrorMessage("时间不能为空！");
		}
		if (domain.getName() == null) {
			result.addErrorMessage("姓名不能为空！");
		} else if (validateHelper.illegalStringLength(1, 15, domain.getName())) {
			result.addErrorMessage("姓名不能超过10个字符！");
		}
		if (domain.getPlace() == null) {
			result.addErrorMessage("地点不能为空！");
		} else if (validateHelper.illegalStringLength(1, 50, domain.getPlace())) {
			result.addErrorMessage("地点不能输入超过50个字符！");
		}
		if (domain.getFamilyTel() == null) {
			result.addErrorMessage("家属联系电话不能为空！");
		}
//		if(domain.getHelpPeople()==null){
//			result.addErrorMessage("帮扶人员不能为空！");
//		}
		if(domain.getIdCard()!=null&&!domain.getIdCard().equals("")&&
				validateHelper.illegalIdcard(domain.getIdCard())){
			result.addErrorMessage("身份证输入不合法！");
		}
//		if(domain.getPhone()!=null&&validateHelper.illegalTelephoneOrMobile(domain.getPhone())){
//			result.addErrorMessage("电话号码输入不合法！");
//		}
		return result;
	}

	public void validatorDruggyTask(DruggyTask domain) {
		ValidateResult result = validate(domain);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
	}
}
