package com.tianque.validate.impl;

import org.springframework.stereotype.Component;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PartyConstructionFiles;

@Component("partyConstructionFilesValidator")
public class PartyConstructionFilesValidatorImpl implements DomainValidator<PartyConstructionFiles> {

	@Override
	public ValidateResult validate(PartyConstructionFiles domain) {
		ValidateResult result = new ValidateResult();

		boolean orgIsNotNull = validateOrgIsNotNull(domain.getOrganization());
		if (!orgIsNotNull) {
			result.addErrorMessage("所属网格不能为空");
		}
		return result;
	}

	private boolean validateOrgIsNotNull(Organization org) {
		if (org == null) {
			return false;
		}
		if (org != null && org.getId() == null) {
			return false;
		}
		return true;
	}
}
