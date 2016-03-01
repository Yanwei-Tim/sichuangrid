package com.tianque.approval.validator;

import org.springframework.stereotype.Component;

import com.tianque.approval.domain.ApprovalItemFile;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;

@Component("approvalItemFileValidator")
public class ApprovalItemFileValidatorImpl implements DomainValidator<ApprovalItemFile> {

	@Override
	public ValidateResult validate(ApprovalItemFile domain) {
		StringBuffer bf = new StringBuffer();
		ValidateResult validateResult = new ValidateResult();
		if (null == domain) {
			validateResult.addErrorMessage(bf.append("申请事项附件不能为空").toString());
		}

		if (null == domain.getApprovalItem() || null == domain.getApprovalItem().getId()) {
			validateResult.addErrorMessage(bf.append("申请事项附件事项不能为空").toString());
		}

		if (null == domain.getFileName() || "".equals(domain.getFileName().trim())) {
			validateResult.addErrorMessage(bf.append("申请事项附件名称不能为空").toString());
		}

		if (null == domain.getFileActualUrl() || "".equals(domain.getFileActualUrl().trim())) {
			validateResult.addErrorMessage(bf.append("申请事项附件路径不能为空").toString());
		}

		return validateResult;
	}
}
