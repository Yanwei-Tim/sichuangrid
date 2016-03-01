package com.tianque.baseInfo.primaryOrg.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.primaryOrg.domain.PrimaryOrgMembers;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 组织成员实体验证类
 * 
 * @author sunyuebin
 */
@Component("primaryOrgMemberValidator")
public class PrimaryOrgMemberValidatorImpl implements DomainValidator<PrimaryOrgMembers> {
	@Autowired
	public ValidateHelper validateHelper;
	/** 名称长度 */
	private final int nameLength = 32;
	/** 简介长度 */
	private final int remarkLength = 600;

	@Override
	public ValidateResult validate(PrimaryOrgMembers domain) {
		ValidateResult result = new ValidateResult();
		if (!validateOrgIsNotNull(domain.getOrg())) {
			result.addErrorMessage("所属区域必须输入");
		}
		if (validateHelper.emptyString(domain.getName())) {
			result.addErrorMessage("名称必须输入");
		} else {
			if (validateHelper.illegalStringLength(0, nameLength, domain.getName())) {
				result.addErrorMessage("名称不能大于" + nameLength + "个字符");
			}
		}

		// 性别验证
		validatorifGender(domain.getGender(), result, null);
		if (domain.getDutyId() != null) {
			if (domain.getDutyId().getId() == null) {
				result.addErrorMessage("职位不能为空");
			}
		}

		// 职位验证
		if (!validateHelper.emptyString(domain.getPosition())
				&& validateHelper.illegalStringLength(0, 10, domain.getPosition())) {
			result.addErrorMessage("职务不能大于10个字符");
		}

		if (!validateHelper.emptyString(domain.getRemark())) {
			if (validateHelper.illegalStringLength(0, remarkLength, domain.getRemark())) {
				result.addErrorMessage("备注不能大于" + remarkLength + "个字符");
			}
		}
		return result;
	}

	public void validatorifGender(PropertyDict gender, ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!typeIsNotNull(gender)) {
			result.addErrorMessage("性别必须输入");
		}
	}

	public boolean typeIsNotNull(PropertyDict p) {
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
}