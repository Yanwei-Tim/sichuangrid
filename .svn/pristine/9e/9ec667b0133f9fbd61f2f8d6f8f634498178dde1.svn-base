package com.tianque.baseInfo.primaryOrg.primaryMembers.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMembers;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 成员实体验证类
 * 
 */
@Component("primaryMembersValidator")
public class PrimaryMembersValidator implements DomainValidator<PrimaryMembers> {
	@Autowired
	public ValidateHelper validateHelper;
	/** 名称长度 */
	private final int nameLength = 20;
	/** 简介长度 */
	private final int remarkLength = 300;

	@Override
	public ValidateResult validate(PrimaryMembers domain) {
		ValidateResult result = new ValidateResult();
		if (!validateOrgIsNotNull(domain.getOrg())) {
			result.addErrorMessage("所属区域必须输入");
		}
		if (validateHelper.emptyString(domain.getName())) {
			result.addErrorMessage("名称必须输入");
		} else {
			if (validateHelper.illegalStringLength(0, nameLength, domain
					.getName())) {
				result.addErrorMessage("名称不能大于" + nameLength + "个字符");
			}
		}
		// 身份证验证
		if (!validateHelper.emptyString(domain.getIdcardNo())) {
			if (validateHelper.illegalIdcard(domain.getIdcardNo())) {
				result.addErrorMessage("请输入一个合法的身份证号码");
			}
		}
		// 性别验证
		validatorifGender(domain.getGender(), result, null);

		// 职位验证
		if (domain.getDutyId() != null) {
			if (domain.getDutyId().getId() == null) {
				result.addErrorMessage("职位不能为空");
			}
		}
		// 级别验证
		if (domain.getDepartmentPartyLevel() != null) {
			if (domain.getDepartmentPartyLevel().getId() == null) {
				result.addErrorMessage("级别不能为空");
			}
		}

		// 出生日期验证
		if (validateHelper.emptyString(domain.getBirthday().toString())) {
			result.addErrorMessage("出生日期不能为空");
		}
		// 民族验证
		validatorifNation(domain.getNation(), result, null);
		// 政治面貌验证
		validatorifPoliticalBackground(domain.getPoliticalBackground(), result,
				null);
		// 文化程度验证
		validatorifSchooling(domain.getSchooling(), result, null);
		// 联系手机验证
		if (!validateHelper.emptyString(domain.getMobile())) {
			if (validateHelper.illegalMobilePhone(domain.getMobile())) {
				result.addErrorMessage("手机号码输入只能是以1开头的11位数字");
			}

		}
		// 联系电话验证
		if (!validateHelper.emptyString(domain.getTelephone())) {
			if (validateHelper.illegalTelephone(domain.getTelephone())) {
				result.addErrorMessage("固定电话不合法，只能输数字和横杠(-)");
			}
		}
		// 备注验证
		if (!validateHelper.emptyString(domain.getRemark())) {
			if (validateHelper.illegalStringLength(0, remarkLength, domain
					.getRemark())) {
				result.addErrorMessage("备注不能大于" + remarkLength + "个字符");
			}
		}
		return result;
	}

	public void validatorifGender(PropertyDict gender, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!typeIsNotNull(gender)) {
			result.addErrorMessage("性别必须输入");
		}
	}

	public void validatorifNation(PropertyDict gender, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!typeIsNotNull(gender)) {
			result.addErrorMessage("民族必须输入");
		}
	}

	public void validatorifPoliticalBackground(PropertyDict gender,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!typeIsNotNull(gender)) {
			result.addErrorMessage("政治面貌必须输入");
		}
	}

	public void validatorifSchooling(PropertyDict gender,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!typeIsNotNull(gender)) {
			result.addErrorMessage("文化程度必须输入");
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