package com.tianque.baseInfo.scenicManage.praiseScenicSpot.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.scenicManage.praiseScenicSpot.domain.PraiseScenicSpot;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("praiseScenicSpotValidator")
public class PraiseScenicSpotValidatorImpl implements
		DomainValidator<PraiseScenicSpot> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	@Override
	public ValidateResult validate(PraiseScenicSpot domain) {
		ValidateResult validateResult = new ValidateResult();
		return validateResult;
	}

}
