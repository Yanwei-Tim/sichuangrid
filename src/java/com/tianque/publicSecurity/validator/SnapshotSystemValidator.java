package com.tianque.publicSecurity.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.publicSecurity.domain.SnapshotSystem;

/**
 * 
 * @author
 * @date 2014-02-11 15:12:12
 */
@Component("snapshotSystemValidator")
public class SnapshotSystemValidator implements DomainValidator<SnapshotSystem> {

	@Autowired
	private ValidateHelper validateHelper;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	@Override
	public ValidateResult validate(SnapshotSystem domain) {
		ValidateResult validateResult = new ValidateResult();
		if (validateHelper.emptyString(domain.getSnapshotNo())) {
			validateResult.addErrorMessage("抓拍系统编号必须输入");
		} else if (validateHelper.illegalNumber3(domain.getSnapshotNo())) {
			validateResult.addErrorMessage("抓拍系统编号只能输入3位正数");
		}
		if (!validateHelper.emptyString(domain.getAddress())
				&& validateHelper.illegalStringLength(0, 30,
						domain.getAddress())) {
			validateResult.addErrorMessage("地址只能输入0-30个字符");
		}
		return validateResult;
	}
}