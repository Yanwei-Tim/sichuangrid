package com.tianque.approval.validator;

import org.springframework.stereotype.Component;

import com.tianque.approval.domain.ItemAttachment;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;

@Component("itemAttachmentValidator")
public class ItemAttachmentValidatorImpl implements DomainValidator<ItemAttachment> {

	@Override
	public ValidateResult validate(ItemAttachment domain) {
		StringBuffer bf = new StringBuffer();
		ValidateResult validateResult = new ValidateResult();
		if (null == domain) {
			validateResult.addErrorMessage(bf.append("事项附件不能为空").toString());
		}

		if (null == domain.getItem() || null == domain.getItem().getId()) {
			validateResult.addErrorMessage(bf.append("事项附件事项不能为空").toString());
		}

		if (null == domain.getFileName() || "".equals(domain.getFileName().trim())) {
			validateResult.addErrorMessage(bf.append("事项附件名称不能为空").toString());
		}

		if (null == domain.getFileActualUrl() || "".equals(domain.getFileActualUrl().trim())) {
			validateResult.addErrorMessage(bf.append("事项附件路径不能为空").toString());
		}

		return validateResult;
	}
}
