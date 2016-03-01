package com.tianque.validate.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PartyMemberInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("partyMemberInfoValidator")
public class PartyMemberInfoValidatorImpl extends AbstractCountrymenValidator<PartyMemberInfo> {

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

	public boolean illegalBirthWorkRetireDate(Date birthday, Date enterWorkDate, Date retireDate) {
		if (enterWorkDate == null && retireDate == null) {
			return false;
		} else if (enterWorkDate != null && retireDate == null) {
			return !birthday.before(enterWorkDate);
		} else if (enterWorkDate == null && retireDate != null) {
			return !birthday.before(retireDate);
		} else if (enterWorkDate != null && retireDate != null) {
			return !(birthday.before(enterWorkDate) && enterWorkDate.before(retireDate));
		}
		return true;
	}

	@Override
	public ValidateResult validateSpecializedInfo(PartyMemberInfo domain) {
		ValidateResult result = new ValidateResult();
		if (!validateHelper.emptyString(domain.getJoinPartyBranch())
				&& validateHelper.illegalStringLength(0, 140, domain.getJoinPartyBranch())) {
			result.addErrorMessage("入党时所在党支部名称不能大于140个字符");
		}
		if (!validateHelper.emptyString(domain.getPartyPosition())
				&& validateHelper.illegalStringLength(0, 48, domain.getPartyPosition())) {
			result.addErrorMessage("党内主要职务不能大于48个字符");
		}
		if (!validateHelper.emptyString(domain.getRewardsPunishment())
				&& validateHelper.illegalStringLength(0, 350, domain.getRewardsPunishment())) {
			result.addErrorMessage("奖惩情况不能大于350个字符");
		}
		if (!validateHelper.emptyString(domain.getExpertise())
				&& validateHelper.illegalStringLength(0, 380, domain.getExpertise())) {
			result.addErrorMessage("专长不能大于380个字符");
		}
		if (!validateHelper.emptyString(domain.getFlowPartyBranch())
				&& validateHelper.illegalStringLength(0, 140, domain.getExpertise())) {
			result.addErrorMessage("流入地（单位）党支部不能大于140个字符");
		}
		if (!validateHelper.emptyString(domain.getPartyBranchContacts())
				&& validateHelper.illegalStringLength(0, 8, domain.getPartyBranchContacts())) {
			result.addErrorMessage("党支部联系人不能大于8个字符");
		}
		if (!validateHelper.emptyString(domain.getBranchMobileNumber())
				&& validateHelper.illegalMobilePhone(domain.getBranchMobileNumber())) {
			result.addErrorMessage("联系手机（党支部联系人)只能输入1开头的11位数字");
		}
		if (!validateHelper.emptyString(domain.getBranchTelephone())) {
			if (validateHelper.illegalStringLength(0, 20, domain.getBranchTelephone())) {
				result.addErrorMessage("固定电话（党支部联系人）不能输入大于20字符");
			} else if (validateHelper.illegalTelephone(domain.getTelephone())) {
				result.addErrorMessage("固定电话（党支部联系人）只能输入数字和-");
			}
		}
		if (!validateHelper.nullObj(domain.getBecomesDate())
				&& !validateHelper.nullObj(domain.getJoinPartyDate())
				&& !validateHelper.endDateIsSameorBigForStartDate(domain.getBecomesDate(),
						domain.getJoinPartyDate())) {
			result.addErrorMessage("入党时间不能大于转正时间");
		}

		if (!validateHelper.nullObj(domain.getJoinPartyBranchDate())
				&& !validateHelper.nullObj(domain.getJoinPartyDate())
				&& !validateHelper.endDateIsSameorBigForStartDate(domain.getJoinPartyBranchDate(),
						domain.getJoinPartyDate())) {
			result.addErrorMessage("进入党支部时间不能大于入党时间");
		}
		if (!validateHelper.nullObj(domain.getFlowPartyBranchDate())
				&& !validateHelper.nullObj(domain.getJoinPartyDate())
				&& !validateHelper.endDateIsSameorBigForStartDate(domain.getFlowPartyBranchDate(),
						domain.getJoinPartyDate())) {
			result.addErrorMessage("流入时间不能大于入党时间");
		}
		return result;
	}
}
