package com.tianque.validate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.OtherLocale;
import com.tianque.domain.PropertyDict;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("otherLocaleValidator")
public class OtherlocalValidateImpl implements DomainValidator<OtherLocale> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	private boolean typeIsNotNull(PropertyDict p) {
		if (p == null || p.getId() == null) {
			return false;
		}
		return true;
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

	private boolean validateOrgIsGrid(Organization org) {
		return organizationDubboService.isGridOrganization(org.getId());
	}

	@Override
	public ValidateResult validate(OtherLocale domain) {
		ValidateResult validateResult = new ValidateResult();
		boolean nameEmpty = validateHelper.emptyString(domain.getName());
		if (nameEmpty) {
			validateResult.addErrorMessage("姓名必须输入");
		}
		boolean nameIllage = !nameEmpty
				&& validateHelper.illegalStringLength(2, 20, domain.getName());
		if (nameIllage) {
			validateResult.addErrorMessage("姓名只能输入2-20个字符");
		}
		if (!typeIsNotNull(domain.getType())) {
			validateResult.addErrorMessage("场所类型必须输入");
		}
		boolean orgIsNotNull = validateOrgIsNotNull(domain.getOrganization());
		if (!orgIsNotNull) {
			validateResult.addErrorMessage("所属网格必须输入");
		}
		boolean orgIsNotGrid = (orgIsNotNull && !validateOrgIsGrid(domain
				.getOrganization()));
		if (orgIsNotGrid) {
			validateResult.addErrorMessage("所属网格必须为片组片格");
		}
		if (domain.getPractitionerNumber() != null) {
			if (validateHelper.illegalNumberZZ(""
					+ domain.getPractitionerNumber())) {
				validateResult.addErrorMessage("从业人数只能输入正整数");
			} else if (Double.valueOf(domain.getPractitionerNumber())
					.longValue() > 999999999) {
				validateResult.addErrorMessage("从业人数不能超过999999999");
			}
		}
		if (!validateHelper.emptyString(domain.getContactPerson())
				&& validateHelper.illegalStringLength(2, 20,
						domain.getContactPerson())) {
			validateResult.addErrorMessage("联系人只能输入2-30个字符");
		}

		if (!validateHelper.emptyString(domain.getAddress())
				&& validateHelper.illegalStringLength(0, 50,
						domain.getAddress())) {
			validateResult.addErrorMessage("场所地址最多输入50个字符");
		}
		if (!validateHelper.emptyString(domain.getTelephone())) {
			if (validateHelper.illegalTelephone(domain.getTelephone())) {
				validateResult.addErrorMessage("联系电话只能输入数字和-");
			}
		}
		if (!validateHelper.emptyString(domain.getMobileNumber())) {
			if (validateHelper.illegalMobilePhone(domain.getMobileNumber())) {
				validateResult.addErrorMessage("联系手机只能输入1开头的11位数字");
			}
		}
		if (validateHelper.illegalStringLength(0, 200, domain.getRemark())) {
			validateResult.addErrorMessage("备注不能输入大于200字符");
		}
		return validateResult;
	}
}
