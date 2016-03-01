package com.tianque.plugin.dataManage.location.schoolTemp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.location.schoolTemp.domain.SchoolTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("schoolTempValidator")
public class SchoolTempValidatorImpl implements DomainValidatorTemp<SchoolTemp> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	@Override
	public ValidateResult validate(SchoolTemp domain) {
		ValidateResult validateResult = new ValidateResult();
		boolean nameEmpty = validateHelper.emptyString(domain.getName());
		if (nameEmpty) {
			validateResult.addErrorMessage(getColumNo("name") + "学校名称必须输入");
		}
		if (!nameEmpty
				&& validateHelper.illegalStringLength(2, 30, domain.getName())) {
			validateResult.addErrorMessage(getColumNo("name")
					+ "学校名称只能输入2-30个字符");
		}

		if (domain.getKind() == null) {
			validateResult.addErrorMessage(getColumNo("kind") + "请选择学校性质");
		}
		if (validateHelper.emptyString(domain.getAddress())) {
			validateResult.addErrorMessage(getColumNo("address") + "学校地址必须输入");
		} else if (validateHelper.illegalStringLength(0, 50,
				domain.getAddress())) {
			validateResult.addErrorMessage(getColumNo("address")
					+ "学校地址不能输入大于50个字符");
		}
		if (domain.getType() == null) {
			validateResult.addErrorMessage(getColumNo("type") + "请选择学校类型");
		}
		if (validateHelper.emptyString(domain.getManager())) {
			validateResult.addErrorMessage(getColumNo("manager") + "校长必须输入");
		} else if (validateHelper.illegalStringLength(2, 20,
				domain.getManager())) {
			validateResult.addErrorMessage(getColumNo("manager")
					+ "校长姓名只能输入2-20个字符");
		}

		if (!validateHelper.emptyString(domain.getTelephone())
				&& validateHelper.illegalTelephone(domain.getTelephone())) {
			validateResult.addErrorMessage(getColumNo("telephone")
					+ "联系电话输入不正确，格式：0571-88888888");
		}
		if (!validateHelper.emptyString(domain.getTelephone())
				&& validateHelper.illegalStringLength(0, 20,
						domain.getTelephone())) {
			validateResult.addErrorMessage(getColumNo("telephone")
					+ "联系电话最多输入20位");
		}

		if (!validateHelper.emptyString(domain.getMobileNumber())
				&& validateHelper.illegalMobilePhone(domain.getMobileNumber())) {
			validateResult.addErrorMessage(getColumNo("mobileNumber")
					+ "联系手机只能输入1开头的11位数字");
		}
		if (domain.getAtSchoolHeadcount() != null) {
			if (domain.getAtSchoolHeadcount() < 0
					|| domain.getAtSchoolHeadcount() > 999999) {
				validateResult.addErrorMessage(getColumNo("atSchoolHeadcount")
						+ "在校人数只能输入0到999999数字");
			}
		}
		if (!validateHelper.emptyString(domain.getEnglishName())
				&& validateHelper.illegalEnglishName(domain.getEnglishName())) {
			validateResult.addErrorMessage(getColumNo("englishName")
					+ "英文名称不能输入中文");
		}
		if (validateHelper.illegalStringLength(0, 30, domain.getEnglishName())) {
			validateResult.addErrorMessage(getColumNo("englishName")
					+ "英文名称不能输入大于30个字符");
		}
		if (!validateHelper.emptyString(domain.getFax())) {
			if (validateHelper.illegalTelephone(domain.getFax())) {
				validateResult
						.addErrorMessage(getColumNo("fax") + "传真只能输入数字和-");
			}
		}
		if (!validateHelper.emptyString(domain.getFax())) {
			if (validateHelper.illegalStringLength(0, 20, domain.getFax())) {
				validateResult.addErrorMessage(getColumNo("fax") + "传真最多输入20位");
			}
		}

		if (!validateHelper.emptyString(domain.getEmail())
				&& validateHelper.illegalEmail(domain.getEmail())) {
			validateResult.addErrorMessage(getColumNo("email")
					+ "电子邮箱输入不正确,例如：youname@youcompany.com");
		}
		if (!validateHelper.emptyString(domain.getEmail())
				&& validateHelper.illegalStringLength(1, 30, domain.getEmail())) {
			validateResult.addErrorMessage(getColumNo("email") + "电子邮箱最多输入30位");
		}
		if (!validateHelper.emptyString(domain.getWebSite())
				&& validateHelper.illegalWebSite(domain.getWebSite())) {
			validateResult.addErrorMessage(getColumNo("webSite")
					+ "请输入以http://开头的学校网址!例:http://www.xxx.com");
		}
		if (!validateHelper.emptyString(domain.getWebSite())
				&& validateHelper.illegalStringLength(1, 30,
						domain.getWebSite())) {
			validateResult.addErrorMessage(getColumNo("webSite") + "网址最多输入30位");
		}
		if (validateHelper.illegalStringLength(0, 200, domain.getRemark())) {
			validateResult.addErrorMessage(getColumNo("remark")
					+ "备注不能输大于200个字符");
		}
		return validateResult;
	}

	public String getColumNo(String key) {
		return ExcelImportHelper.getColumNo(key);
	}
}
