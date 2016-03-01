package com.tianque.approval.validator;

import org.springframework.stereotype.Component;

import com.tianque.approval.domain.ApprovalItem;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;

@Component("approvalItemValidator")
public class ApprovalItemValidatorImpl implements DomainValidator<ApprovalItem> {

	@Override
	public ValidateResult validate(ApprovalItem domain) {
		StringBuffer bf = new StringBuffer();
		ValidateResult validateResult = new ValidateResult();
		if (null == domain) {
			validateResult.addErrorMessage(bf.append("申请事项不能为空").toString());
		}

		if (null == domain.getApprovalNumber() || "".equals(domain.getApprovalNumber().trim())) {
			validateResult.addErrorMessage(bf.append("申请事项编号不能为空").toString());
		}

		if (null == domain.getName() || "".equals(domain.getName().trim())) {
			validateResult.addErrorMessage(bf.append("申请事项名称不能为空").toString());
		}

		if (null == domain.getIdCardNo() || "".equals(domain.getIdCardNo().trim())) {
			validateResult.addErrorMessage(bf.append("申请事项身份证不能为空").toString());
		}

		if (null == domain.getMobileNumber() || "".equals(domain.getMobileNumber().trim())) {
			validateResult.addErrorMessage(bf.append("申请事项手机号码不能为空").toString());
		}

		if (null == domain.getCurrentAddress() || "".equals(domain.getCurrentAddress().trim())) {
			validateResult.addErrorMessage(bf.append("申请事项常住地址不能为空").toString());
		}

		if (null == domain.getItem() || null == domain.getItem().getId()) {
			validateResult.addErrorMessage(bf.append("事项不能为空").toString());
		}
		if (null == domain.getStatus()) {
			validateResult.addErrorMessage(bf.append("事项状态不能为空").toString());
		}
		return validateResult;
	}
}
