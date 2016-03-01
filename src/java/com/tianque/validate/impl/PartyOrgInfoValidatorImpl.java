package com.tianque.validate.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PartyOrgInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("partyOrgInfoValidator")
public class PartyOrgInfoValidatorImpl extends
		AbstractCountrymenValidator<PartyOrgInfo> {

	@Autowired
	public ValidateHelper validateHelper;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	public boolean typeIsNotNull(PropertyDict p) {
		if (p == null || p.getId() == null) {
			return false;
		}
		return true;
	}

	public boolean validateOrgIsNotNull(Organization org) {
		if (org == null) {
			return false;
		}
		if (org != null && org.getId() == null) {
			return false;
		}
		return true;
	}

	public boolean validateOrgIsGrid(Organization org) {
		return organizationDubboService.isGridOrganization(org.getId());
	}

	public boolean illegalBirthWorkRetireDate(Date birthday,
			Date enterWorkDate, Date retireDate) {
		if (enterWorkDate == null && retireDate == null) {
			return false;
		} else if (enterWorkDate != null && retireDate == null) {
			return !birthday.before(enterWorkDate);
		} else if (enterWorkDate == null && retireDate != null) {
			return !birthday.before(retireDate);
		} else if (enterWorkDate != null && retireDate != null) {
			return !(birthday.before(enterWorkDate) && enterWorkDate
					.before(retireDate));
		}
		return true;
	}

	@Override
	public ValidateResult validateSpecializedInfo(PartyOrgInfo domain) {
		ValidateResult result = new ValidateResult();
		if (validateHelper.emptyString(domain.getPartyBranchName())) {
			result.addErrorMessage("党支部名称必须输入");
		} else if (!validateHelper.emptyString(domain.getPartyBranchName())
				&& validateHelper.illegalStringLength(0, 75,
						domain.getPartyBranchName())) {
			result.addErrorMessage("党支部名称不能大于75个字符");
		}
		if (validateHelper.emptyString(domain.getPartyBranchSecretary())) {
			result.addErrorMessage("党支部书记名称必须输入");
		} else if (!validateHelper
				.emptyString(domain.getPartyBranchSecretary())
				&& validateHelper.illegalStringLength(0, 25,
						domain.getPartyBranchSecretary())) {
			result.addErrorMessage("党支部书记名称不能大于25个字符");
		}
		if (null == domain.getEstablishedDate()) {
			result.addErrorMessage("党支部成立时间必须输入");
		}
		if (!validateHelper.emptyString(domain.getIdCardNo())
				&& validateHelper.illegalIdcard(domain.getIdCardNo())) {
			result.addErrorMessage("身份证号码输入不正确");
		}
		if (!validateHelper.emptyString(domain.getMobileNumber())
				&& validateHelper.illegalMobilePhone(domain.getMobileNumber())) {
			result.addErrorMessage("联系手机只能输入1开头的11位数字");
		}
		if (!validateHelper.emptyString(domain.getTelephone())) {
			if (validateHelper
					.illegalStringLength(0, 20, domain.getTelephone())) {
				result.addErrorMessage("固定电话不能输入大于20字符");
			} else if (validateHelper.illegalTelephone(domain.getTelephone())) {
				result.addErrorMessage("固定电话只能输入数字和-");
			}
		}
		if (!validateHelper.emptyString(domain.getPartyBranchAddr())
				&& validateHelper.illegalStringLength(0, 150,
						domain.getPartyBranchAddr())) {
			result.addErrorMessage("党组织地址不能大于150个字符");
		}
		if (null != domain.getPartyNumbers()
				&& domain.getPartyNumbers() > 999999999) {
			result.addErrorMessage("党员人数不能大于999999999人");
		}
		return result;
	}
}
