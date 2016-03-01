package com.tianque.partyBuilding.organizations.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.partyBuilding.organizations.domain.Partyresource;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 
 * @author
 * @date 2014-01-15 09:22:20
 */
@Component("partyresourceValidator")
public class PartyresourceValidator implements DomainValidator<Partyresource> {

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
	public ValidateResult validate(Partyresource domain) {
		ValidateResult validateResult = new ValidateResult();
		if (null == domain.getOrganization()
				|| null == domain.getOrganization().getId()) {
			validateResult.addErrorMessage("组织机构为空");
		}
		if (!StringUtil.isStringAvaliable(domain.getName())) {
			validateResult.addErrorMessage("组织名称必须输入");
		} else if (validateHelper.illegalStringLength(0, 50, domain.getName())) {
			validateResult.addErrorMessage("组织名称输入不能超过50个字符");
		}
		if (!StringUtil.isStringAvaliable(domain.getManager())) {
			validateResult.addErrorMessage("负责人必须输入");
		} else if (!validateHelper.isConSpeCharacters(domain.getManager())) {
			validateResult.addErrorMessage("负责人不能输入特殊字符");
		} else if (validateHelper.illegalStringLength(0, 20,
				domain.getManager())) {
			validateResult.addErrorMessage("负责人输入不能超过20个字符");
		}
		if (!StringUtil.isStringAvaliable(domain.getTelephone())) {
			validateResult.addErrorMessage("联系电话必须输入");
		} else if (validateHelper.illegalTelephone(domain.getTelephone())) {
			validateResult.addErrorMessage("请输入正确的联系电话");
		}
		if (!validateHelper.emptyString(domain.getAddress())
				&& validateHelper.illegalStringLength(0, 50,
						domain.getAddress())) {
			validateResult.addErrorMessage("地点输入不能超过50个字符");
		}
		if (!validateHelper.emptyString(domain.getRemark())
				&& validateHelper.illegalStringLength(0, 300,
						domain.getRemark())) {
			validateResult.addErrorMessage("备注输入不能超过300个字符");
		}
		return validateResult;
	}
}