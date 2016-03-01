package com.tianque.plugin.dataManage.location.otherLocaleTemp.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.dataManage.location.otherLocaleTemp.domain.OtherLocaleTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("otherLocaleTempValidator")
public class OtherLocaleTempValidatorImpl implements
		DomainValidatorTemp<OtherLocaleTemp> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	private boolean typeIsNotNull(PropertyDict p) {
		if (p == null || p.getId() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ValidateResult validate(OtherLocaleTemp domain) {
		ValidateResult validateResult = new ValidateResult();
		boolean nameEmpty = validateHelper.emptyString(domain.getName());
		if (nameEmpty) {
			validateResult.addErrorMessage(getColumNo("name") + "场所名称必须输入");
		}
		boolean nameIllage = !nameEmpty
				&& validateHelper.illegalStringLength(2, 20, domain.getName());
		if (nameIllage) {
			validateResult.addErrorMessage(getColumNo("name")
					+ "场所名称只能输入2-20个字符");
		}
		if (!typeIsNotNull(domain.getType())) {
			validateResult.addErrorMessage(getColumNo("type") + "场所类型必须输入");
		}
		if (!validateHelper.emptyString(domain.getManager())
				&& validateHelper.illegalStringLength(2, 20,
						domain.getManager())) {
			validateResult.addErrorMessage(getColumNo("manager")
					+ "联系人只能输入2-30个字符");
		}
		if (!validateHelper.emptyString(domain.getAddress())
				&& validateHelper.illegalStringLength(0, 50,
						domain.getAddress())) {
			validateResult.addErrorMessage(getColumNo("address")
					+ "场所地址最多输入50个字符");
		}
		if (!validateHelper.emptyString(domain.getTelephone())) {
			if (validateHelper.illegalTelephone(domain.getTelephone())) {
				validateResult.addErrorMessage(getColumNo("telephone")
						+ "联系电话只能输入数字和-");
			}
		}
		if (!validateHelper.emptyString(domain.getMobileNumber())) {
			if (validateHelper.illegalMobilePhone(domain.getMobileNumber())) {
				validateResult.addErrorMessage(getColumNo("mobileNumber")
						+ "联系手机只能输入1开头的11位数字");
			}
		}
		if (validateHelper.illegalStringLength(0, 300, domain.getRemark())) {
			validateResult.addErrorMessage(getColumNo("remark")
					+ "备注不能输入大于300字符");
		}
		return validateResult;
	}

	private String getColumNo(String key) {
		StringBuffer bf = new StringBuffer();
		if (!StringUtils.isEmpty(ExcelImportHelper.getDataColumMap(key))) {
			bf.append("第[").append(ExcelImportHelper.realRow.get())
					.append("]行");
			bf.append("第[")
					.append(Integer.valueOf(ExcelImportHelper
							.getDataColumMap(key)) + 1).append("]列");
		} else {
			bf.append("");
		}
		return bf.toString();
	}

}
