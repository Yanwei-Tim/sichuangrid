package com.tianque.fourTeams.fourTeamsManage.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.fourTeams.fourTeamsManage.domain.FourTeamsOrg;

@Component("fourTeamsOrgValidator")
public class FourTeamsOrgValidatorImpl implements DomainValidator<FourTeamsOrg> {

	@Autowired
	private ValidateHelper validateHelper;

	private final int MIN_LEN = 2;
	private final int MAX_TITLE_LEN = 16;
	private final int MAX_NAME_LEN = 6;
	private final int MAX_POSITION_LEN = 20;

	@Override
	public ValidateResult validate(FourTeamsOrg domain) {
		ValidateResult result = new ValidateResult();

		if (validateHelper.emptyString(domain.getTeamTitle())) {
			result.addErrorMessage("队伍名称不能为空");
		} else {
			if (validateHelper.illegalScript(domain.getTeamTitle())) {
				result.addErrorMessage("队伍名称包含非法脚本");
			}
			if (validateHelper.illegalStringLength(MIN_LEN, MAX_TITLE_LEN,
					domain.getTeamTitle())) {
				result.addErrorMessage("队伍名不能小于" + MIN_LEN + "个字符且不能大于"
						+ MAX_TITLE_LEN + "个字符");
			}
			if (validateHelper
					.illegalExculdeParticalChar(domain.getTeamTitle())) {
				result.addErrorMessage("队伍名称包含非法字符");
			}
		}

		if (validateHelper.emptyString(domain.getUserName())) {
			result.addErrorMessage("姓名不能为空");
		} else {
			if (validateHelper.illegalScript(domain.getUserName())) {
				result.addErrorMessage("姓名包含非法脚本");
			}
			if (validateHelper.illegalStringLength(MIN_LEN, MAX_NAME_LEN,
					domain.getUserName())) {
				result.addErrorMessage("姓名不能小于" + MIN_LEN + "个字符且不能大于"
						+ MAX_NAME_LEN + "个字符");
			}
			if (validateHelper.illegalExculdeParticalChar(domain.getUserName())) {
				result.addErrorMessage("姓名包含非法字符");
			}
		}

		if (validateHelper.emptyString(domain.getPosition())) {
			result.addErrorMessage("职务不能为空");
		} else {
			if (validateHelper.illegalScript(domain.getPosition())) {
				result.addErrorMessage("职务包含非法脚本");
			}
			if (validateHelper.illegalStringLength(MIN_LEN, MAX_POSITION_LEN,
					domain.getPosition())) {
				result.addErrorMessage("职务不能小于" + MIN_LEN + "个字符且不能大于"
						+ MAX_POSITION_LEN + "个字符");
			}
			if (validateHelper.illegalExculdeParticalChar(domain.getPosition())) {
				result.addErrorMessage("职务包含非法字符");
			}
		}

		if (!validateHelper.emptyString(domain.getPhoneNumber())) {
			if (validateHelper.illegalMobilePhone(domain.getPhoneNumber())) {
				result.addErrorMessage("手机号码输入错误");
			}
		}

		return result;
	}

}
