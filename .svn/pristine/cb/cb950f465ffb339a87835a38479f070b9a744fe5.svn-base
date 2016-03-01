package com.tianque.baseInfo.primaryOrg.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.primaryOrg.domain.PrimaryOrganizations;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;

/**
 * 综合组织实体验证类
 * 
 * @author sunyuebin
 */
@Component("primaryOrgValidator")
public class PrimaryOrgValidatorImpl implements
		DomainValidator<PrimaryOrganizations> {
	@Autowired
	public ValidateHelper validateHelper;
	/** 名称长度 */
	private final int nameLength = 16;
	/** 详细名称长度 */
	private final int detailNameLength = 30;
	/** 简介长度 */
	private final int remarkLength = 300;

	@Override
	public ValidateResult validate(PrimaryOrganizations domain) {
		ValidateResult result = new ValidateResult();
		if (!validateOrgIsNotNull(domain.getOrg())) {
			result.addErrorMessage("所属区域必须输入");
		}
		if (validateHelper.emptyString(domain.getDetailName())) {
			result.addErrorMessage("名称必须输入");
		} else {
			if (validateHelper.illegalStringLength(0, detailNameLength,
					domain.getDetailName())) {
				result.addErrorMessage("名称不能大于" + detailNameLength + "个字符");
			}
		}
		/*
		 * if (validateHelper.emptyString(domain.getDetailName())) {
		 * result.addErrorMessage("详细名称必须输入"); } else { if
		 * (validateHelper.illegalStringLength(0, detailNameLength,
		 * domain.getDetailName())) { result.addErrorMessage("详细名称不能大于" +
		 * detailNameLength + "个字符"); } }
		 */
		if (!validateHelper.emptyString(domain.getRemark())) {
			if (validateHelper.illegalStringLength(0, remarkLength,
					domain.getRemark())) {
				result.addErrorMessage("备注不能大于" + remarkLength + "个字符");
			}
		}
		return result;
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