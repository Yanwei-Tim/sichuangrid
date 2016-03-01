package com.tianque.issue.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.issue.domain.AdministrativeOrgTimeLimitStandard;

@Component("administrativeOrgTimeLimitStandardValidator")
public class AdministrativeOrgTimeLimitStandardValidatorImpl implements
		DomainValidator<AdministrativeOrgTimeLimitStandard> {

	@Autowired
	public ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(
			AdministrativeOrgTimeLimitStandard administrativeStandard) {
		ValidateResult result = new ValidateResult();
		int mustFillOneItem = 0;
		if (null == administrativeStandard.getYellowLimitAccept()) {
			mustFillOneItem += 1;
		} else if (validateHelper.illegalNumberZZ(administrativeStandard
				.getYellowLimitAccept().toString())
				&& administrativeStandard.getYellowLimitAccept() >= 0) {
			result.addErrorMessage("黄牌受理时限只能填写正整数！");
		} else if (administrativeStandard.getYellowLimitAccept() >= 100) {
			result.addErrorMessage("黄牌受理时限不能大于99天！");
		}
		if (null == administrativeStandard.getYellowLimitHandle()) {
			mustFillOneItem += 1;
		} else if (validateHelper.illegalNumberZZ(administrativeStandard
				.getYellowLimitHandle().toString())
				&& administrativeStandard.getYellowLimitHandle() >= 0) {
			result.addErrorMessage("黄牌办理时限只能填写正整数！");
		}
		if (null == administrativeStandard.getRedLimitAccept()) {
			mustFillOneItem += 1;
		} else if (validateHelper.illegalNumberZZ(administrativeStandard
				.getRedLimitAccept().toString())
				&& administrativeStandard.getRedLimitAccept() >= 0) {
			result.addErrorMessage("红牌受理时限只能填写正整数！");
		} else if (administrativeStandard.getRedLimitAccept() >= 100) {
			result.addErrorMessage("红牌受理时限不能大于99天！");
		}
		if (null == administrativeStandard.getRedLimitHandle()) {
			mustFillOneItem += 1;
		} else if (validateHelper.illegalNumberZZ(administrativeStandard
				.getRedLimitHandle().toString())
				&& administrativeStandard.getRedLimitHandle() >= 0) {
			result.addErrorMessage("红牌办理时限只能填写正整数！");
		}
		if (mustFillOneItem == 4) {
			result.addErrorMessage("办理时限必须填写一项！");
		}
		if (administrativeStandard.getRemark() != null) {
			if (validateHelper.illegalStringLength(0, 200,
					administrativeStandard.getRemark())) {
				result.addErrorMessage("备注不能大于200个字符");
			}
		}
		if (administrativeStandard.getRedLimitAccept() != null
				&& administrativeStandard.getYellowLimitAccept() != null) {
			if (administrativeStandard.getRedLimitAccept() <= administrativeStandard
					.getYellowLimitAccept()) {
				result.addErrorMessage("红牌受理时限需大于黄牌受理时限");
			}
		}
		if (administrativeStandard.getRedLimitHandle() != null
				&& administrativeStandard.getYellowLimitHandle() != null) {
			if (administrativeStandard.getRedLimitHandle() <= administrativeStandard
					.getYellowLimitHandle()) {
				result.addErrorMessage("红牌办理时限需大于黄牌办理时限");
			}
		}
		if (administrativeStandard.getRedLimitVerify() != null
				&& administrativeStandard.getYellowLimitVerify() != null) {
			if (administrativeStandard.getRedLimitVerify() <= administrativeStandard
					.getYellowLimitVerify()) {
				result.addErrorMessage("红牌验证时限需大于黄牌验证时限");
			}
		}
		return result;
	}

}
