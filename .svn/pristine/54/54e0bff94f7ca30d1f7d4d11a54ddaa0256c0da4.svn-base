package com.tianque.approval.validator;

import org.springframework.stereotype.Component;

import com.tianque.approval.domain.Item;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;

@Component("itemValidator")
public class ItemValidatorImpl implements DomainValidator<Item> {

	@Override
	public ValidateResult validate(Item domain) {
		StringBuffer bf = new StringBuffer();
		ValidateResult validateResult = new ValidateResult();

		if (null == domain) {
			validateResult.addErrorMessage(bf.append("事项不能为空").toString());
		}

		if (null == domain.getItemNumber() || "".equals(domain.getItemNumber().trim())) {
			validateResult.addErrorMessage(bf.append("事项编号必须输入").toString());
		}

		if (null == domain.getItemName() || "".equals(domain.getItemName().trim())) {
			validateResult.addErrorMessage(bf.append("事项名称必须输入").toString());
		}

		if (null == domain.getPromiseTime()) {
			validateResult.addErrorMessage(bf.append("事项承诺时限必须输入").toString());
		}

		if (null == domain.getLegalTime()) {
			validateResult.addErrorMessage(bf.append("事项法定时限必须输入").toString());
		}

		if (null == domain.getMatterKind() || null == domain.getMatterKind().getId()) {
			validateResult.addErrorMessage(bf.append("事项分类必须输入").toString());
		}
		if (domain.isFees() == false) {
			domain.setStandardFees(0L);
		}
		return validateResult;
	}
}
