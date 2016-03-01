package com.tianque.baseInfo.optimalObject.validator;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.optimalObject.domain.OptimalObject;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("optimalObjectValidator")
public class OptimalObjectValidatorImpl extends
		AbstractCountrymenValidator<OptimalObject> {

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
	public ValidateResult validateSpecializedInfo(OptimalObject domain) {

		ValidateResult result = new ValidateResult();

		if (!validateHelper.emptyString(domain.getOptimalCardNo())
				&& validateHelper.illegalStringLength(0, 30,
						domain.getOptimalCardNo())) {
			result.addErrorMessage(getColumNo("optimalCardNo")
					+ "优待证号不能大于30个字符");
		}

		if (!validateHelper.emptyString(domain.getOptimalCardNo())
				&& !validateHelper
						.isConSpeCharacters(domain.getOptimalCardNo())) {
			result.addErrorMessage(getColumNo("optimalCardNo") + "优待证号不能含有特殊字符");
		}

		if (null != domain.getMonthLivingExpenses()) {
			if(domain.getMonthLivingExpenses() <= 0 || domain.getMonthLivingExpenses()>99999999 || domain.getMonthLivingExpenses().toString().length()>12){
				result.addErrorMessage(getColumNo("monthLivingExpenses")
						+ "月生活费必须大于0或者小于999999999");
			}
		}
		return result;
	}
}
