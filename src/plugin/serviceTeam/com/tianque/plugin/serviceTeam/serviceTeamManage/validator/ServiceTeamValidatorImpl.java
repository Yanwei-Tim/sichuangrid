package com.tianque.plugin.serviceTeam.serviceTeamManage.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.plugin.serviceTeam.serviceTeamManage.domain.ServiceTeam;

/**
 * 服务团队实体验证类
 * 
 * @author yangpengdian
 */
@Component("serviceTeamValidator")
public class ServiceTeamValidatorImpl implements DomainValidator<ServiceTeam> {
	@Autowired
	public ValidateHelper validateHelper;
	/** 名称长度 */
	private final int nameLength = 150;
	/** 简介长度 */
	private final int remarkLength = 600;

	@Override
	public ValidateResult validate(ServiceTeam domain) {
		ValidateResult result = new ValidateResult();
		if (!validateOrgIsNotNull(domain.getOrg())) {
			result.addErrorMessage("所属区域必须输入");
		}
		if (validateHelper.emptyString(domain.getTeamName())) {
			result.addErrorMessage("名称必须输入");
		} else {
			if (validateHelper.illegalStringLength(0, nameLength, domain.getTeamName())) {
				result.addErrorMessage("名称不能大于" + nameLength + "个字符");
			}
		}
		if (!validateHelper.emptyString(domain.getRemark())) {
			if (validateHelper.illegalStringLength(0, remarkLength, domain.getRemark())) {
				result.addErrorMessage("简介不能大于" + remarkLength + "个字符");
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