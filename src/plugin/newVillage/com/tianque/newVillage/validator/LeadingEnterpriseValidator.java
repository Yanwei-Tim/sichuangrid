package com.tianque.newVillage.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.domain.LeadingEnterprise;

@Component("leadingEnterpriseValidator")
public class LeadingEnterpriseValidator implements
		DomainValidator<LeadingEnterprise> {
	@Autowired
	private ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(LeadingEnterprise domain) {
		if (domain == null) {
			throw new BusinessValidationException("操作失败，请重试");
		}
		ValidateResult result = new ValidateResult();
		// 验证企业名称
		if (!StringUtil.isStringAvaliable(domain.getEnterpriseName())) {
			result.addErrorMessage("企业名称必须输入");
		} else if (validateHelper.illegalStringLength(0, 20,
				domain.getEnterpriseName())) {
			result.addErrorMessage("企业名称最多输入20个字符");
		}
		// 验证负责人
		if (StringUtil.isStringAvaliable(domain.getChargePerson())
				&& validateHelper.illegalStringLength(0, 10,
						domain.getChargePerson())) {
			result.addErrorMessage("负责人最多输入10个字符");
		}
		// 验证联系电话
		if (StringUtil.isStringAvaliable(domain.getPhoneNumber())) {
			if (validateHelper.illegalStringLength(0, 20,
					domain.getPhoneNumber())) {
				result.addErrorMessage("联系电话不能输入大于20字符");
			} else if (validateHelper.illegalTelephone(domain.getPhoneNumber())) {
				result.addErrorMessage("联系电话只能输入数字和-");
			}
		}
		// 验证地址
		if (StringUtil.isStringAvaliable(domain.getAddress())
				&& validateHelper.illegalStringLength(0, 33,
						domain.getAddress())) {
			result.addErrorMessage("地址最多只能输入33个字符");
		}
		// 验证注册资本
		if (domain.getRegisteredCapital() != null) {
			if (domain.getRegisteredCapital() < 0
					|| domain.getRegisteredCapital() > 9999999999.99D) {
				result.addErrorMessage("注册资金应在0-9999999999.99之间");
			}
		}
		// 验证营业执照号
		if (StringUtil.isStringAvaliable(domain.getLicenseNumber())
				&& validateHelper.illegalStringLength(0, 30,
						domain.getLicenseNumber())) {
			result.addErrorMessage("营业执照号最多输入30个字符");
		}
		// 验证组织机构代码
		if (StringUtil.isStringAvaliable(domain.getBodyCode())
				&& validateHelper.illegalStringLength(0, 12,
						domain.getBodyCode())) {
			result.addErrorMessage("组织机构代码最多输入12个字符");
		}
		// 验证备注
		if (StringUtil.isStringAvaliable(domain.getRemark())
				&& validateHelper.illegalStringLength(0, 200,
						domain.getRemark())) {
			result.addErrorMessage("备注最多输入200个字符");
		}
		return result;
	}
}
