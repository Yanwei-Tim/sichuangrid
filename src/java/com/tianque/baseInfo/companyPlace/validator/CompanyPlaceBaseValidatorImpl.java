package com.tianque.baseInfo.companyPlace.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.companyPlace.domain.CompanyPlaceBase;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("companyPlaceBaseValidator")
public class CompanyPlaceBaseValidatorImpl implements
		DomainValidator<CompanyPlaceBase> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(CompanyPlaceBase domain) {
		return null;
	}

}
