package com.tianque.validate.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PartyOrgActivity;
import com.tianque.domain.PropertyDict;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("partyOrgActivityValidator")
public class PartyOrgActivityValidatorImpl extends AbstractCountrymenValidator<PartyOrgActivity> {

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
	public ValidateResult validateSpecializedInfo(PartyOrgActivity domain) {
		ValidateResult result = new ValidateResult();
		if (null == domain.getActivityDate()) {
			result.addErrorMessage("活动时间必须输入");
		}
		if (validateHelper.emptyString(domain.getActivityAddr())) {
			result.addErrorMessage("活动地点必须输入");
		} else if (!validateHelper.emptyString(domain.getActivityAddr())
				&& validateHelper.illegalStringLength(0, 280, domain.getActivityAddr())) {
			result.addErrorMessage("活动地点不能大于280个字符");
		}
		if (validateHelper.emptyString(domain.getActivitySubject())) {
			result.addErrorMessage("活动主题必须输入");
		} else if (!validateHelper.emptyString(domain.getActivitySubject())
				&& validateHelper.illegalStringLength(0, 148, domain.getActivitySubject())) {
			result.addErrorMessage("活动主题不能大于148个字符");
		}
		if (!validateHelper.emptyString(domain.getOrganizers())
				&& validateHelper.illegalStringLength(0, 248, domain.getOrganizers())) {
			result.addErrorMessage("组织者不能大于248个字符");
		}
		if (!validateHelper.emptyString(domain.getParticipants())
				&& validateHelper.illegalStringLength(0, 248, domain.getParticipants())) {
			result.addErrorMessage("参与者不能大于248个字符");
		}
		// if (!validateHelper.emptyString(domain.getActiveContent())) {
		// if (validateHelper.illegalStringLength(0, 500, domain.getActiveContent())) {
		// result.addErrorMessage("活动内容不能输入大于500字符");
		// }
		// }
		return result;
	}

}
