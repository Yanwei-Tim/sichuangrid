package com.tianque.newVillage.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.domain.NewVillageLeaderTeam;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("newVillageLeaderTeamValidator")
public class NewVillageLeaderTeamValidator implements
		DomainValidator<NewVillageLeaderTeam> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(NewVillageLeaderTeam domain) {
		if (domain == null) {
			throw new BusinessValidationException("操作失败，请重试");
		}
		ValidateResult result = new ValidateResult();
		//验证姓名
		if (StringUtil.isStringAvaliable(domain.getName())) {
			if (!validateHelper.isConSpeCharacters(domain.getName())) {
				result.addErrorMessage("领导班子姓名不能为空或者包含特殊字符");
			} else if (domain.getName().length() > 10) {
				result.addErrorMessage("领导班子姓名长度不能超过10");
			}
		} else {
			result.addErrorMessage("领导班子姓名不能为空或者包含特殊字符");
		}
		//验证性别
		if (domain.getGender() == null
				|| (domain.getGender() != 1 && domain.getGender() != 0 && domain
						.getGender() != 2)) {
			result.addErrorMessage("性别填写出错");
		}
		//验证职务
		if (StringUtil.isStringAvaliable(domain.getDuty())) {
			if (!validateHelper.isConSpeCharacters(domain.getDuty())) {
				result.addErrorMessage("职务不能包含特殊字符");
			} else if (domain.getDuty().length() > 10) {
				result.addErrorMessage("职务长度不能超过10");
			}
		}
		//验证年龄
		if (domain.getAge() == null || domain.getAge() <= 0
				|| domain.getAge() > 999) {
			result.addErrorMessage("错误的年龄,请输入年龄,最大三位数");
		}
		// 验证联系方式
		if (StringUtil.isStringAvaliable(domain.getContactWay())) {
			if (validateHelper.illegalStringLength(0, 20,
					domain.getContactWay())) {
				result.addErrorMessage("联系电话不能输入大于20位");
			} else if (validateHelper.illegalTelephone(domain.getContactWay())) {
				result.addErrorMessage("联系电话只能输入数字和-");
			}
		}
		return result;
	}
}
