package com.tianque.partyBuilding.doubleRegActivities.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.partyBuilding.doubleRegActivities.domain.PartyOrgReport;

/**
 * 
 * @author
 * @date 2014-02-25 11:13:54
 */
@Component("partyOrgReportValidator")
public class PartyOrgReportValidator implements DomainValidator<PartyOrgReport> {

	@Autowired
	private ValidateHelper validateHelper;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	@Override
	public ValidateResult validate(PartyOrgReport domain) {
		ValidateResult validateResult = new ValidateResult();
		if (validateHelper.emptyString(domain.getName())) {
			validateResult.addErrorMessage("单位名称必须输入");
		} else if (validateHelper.illegalStringLength(1, 20, domain.getName())) {
			validateResult.addErrorMessage("单位名称至多需要输入20个字符");
		}
		if (validateHelper.nullObj(domain.getPartyOrgType().getId())) {
			validateResult.addErrorMessage("党组织类别必须选择");
		}

		return validateResult;
	}
}