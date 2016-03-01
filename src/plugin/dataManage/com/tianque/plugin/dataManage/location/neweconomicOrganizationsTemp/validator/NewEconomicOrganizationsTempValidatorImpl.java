package com.tianque.plugin.dataManage.location.neweconomicOrganizationsTemp.validator;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.dataManage.location.neweconomicOrganizationsTemp.domain.NewEconomicOrganizationsTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("newEconomicOrganizationsTempValidator")
public class NewEconomicOrganizationsTempValidatorImpl implements
		DomainValidatorTemp<NewEconomicOrganizationsTemp> {

	@Autowired
	private ValidateHelper validateHelper;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	private boolean typeIsNotNull(PropertyDict p) {
		if (p == null || p.getId() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ValidateResult validate(NewEconomicOrganizationsTemp domain) {
		ValidateResult result = new ValidateResult();
		if (validateHelper.emptyString(domain.getName())) {
			result.addErrorMessage(getColumNo("name") + "名称必须输入");
		} else if (!validateHelper.isConSpeCharacters(domain.getName())) {
			result.addErrorMessage(getColumNo("name") + "名称不能包含特殊字符");
		} else if (validateHelper.illegalStringLength(2, 50, domain.getName())) {
			result.addErrorMessage(getColumNo("name") + "名称只能输入2-50个字符");
		}
		if (validateHelper.emptyString(domain.getLicenseNumber())) {
			result.addErrorMessage(getColumNo("licenseNumber") + "营业执照号码必须输入");
		} else if (validateHelper.illegalStringLength(0, 20,
				domain.getLicenseNumber())) {
			result.addErrorMessage(getColumNo("licenseNumber")
					+ "营业执照号码最多输入20个字符");
		}
		if (validateHelper.emptyString(domain.getAddress())) {
			result.addErrorMessage(getColumNo("address") + "住所必须输入");
		} else if (validateHelper.illegalStringLength(0, 50,
				domain.getAddress())) {
			result.addErrorMessage(getColumNo("address") + "住所最多输入50个字符");
		}
		if (validateHelper.nullObj(domain.getValidityStartDate())) {
			result.addErrorMessage(getColumNo("validityStartDate")
					+ "有效期开始日期不能为空");
		} else if (domain.getValidityStartDate().after(new Date())) {
			result.addErrorMessage(getColumNo("validityStartDate")
					+ "有效期开始日期不能大于今天");
		}
		if (validateHelper.nullObj(domain.getValidityEndDate())) {
			result.addErrorMessage(getColumNo("validityEndDate")
					+ "有效期结束日期不能为空");
		} else if (validateHelper.endDateIsSameorBigForStartDate(
				domain.getValidityStartDate(), domain.getValidityEndDate())) {
			result.addErrorMessage(getColumNo("validityEndDate")
					+ "有效期结束日期不能小于有效期开始日期");
		}
		if (!validateHelper.emptyString(domain.getMobileNumber())
				&& validateHelper.illegalMobilePhone(domain.getMobileNumber())) {
			result.addErrorMessage(getColumNo("mobileNumber")
					+ "联系手机只能输入1开头的11位数字");
		}
		if (!validateHelper.emptyString(domain.getTelephone())) {
			if (validateHelper
					.illegalStringLength(0, 20, domain.getTelephone())) {
				result.addErrorMessage(getColumNo("telephone")
						+ "固定电话不能输入大于20字符");
			} else if (validateHelper.illegalTelephone(domain.getTelephone())) {
				result.addErrorMessage(getColumNo("telephone") + "固定电话只能输入数字和-");
			}
		}
		if (!validateHelper.emptyString(domain.getFoxNumber())) {
			if (validateHelper
					.illegalStringLength(0, 20, domain.getFoxNumber())) {
				result.addErrorMessage(getColumNo("foxNumber")
						+ "传真号码不能输入大于20字符");
			} else if (validateHelper.illegalTelephone(domain.getFoxNumber())) {
				result.addErrorMessage(getColumNo("foxNumber") + "传真号码只能输入数字和-");
			}
		}
		if (domain.getFoxNumber() != null
				&& validateHelper.illegalStringLength(0, 20,
						domain.getFoxNumber())) {
			result.addErrorMessage(getColumNo("foxNumber") + "传真长度不能大于20");
		}
		if (!typeIsNotNull(domain.getType())) {
			result.addErrorMessage(getColumNo("type") + "类别必须输入");
		}

		if (validateHelper.emptyString(domain.getManager())) {
			result.addErrorMessage(getColumNo("manager") + "负责人必须输入");
		} else if (!validateHelper.isConSpeCharacters(domain.getManager())) {
			result.addErrorMessage(getColumNo("manager") + "负责人不能包含特殊字符");
		} else if (validateHelper.illegalStringLength(2, 20,
				domain.getManager())) {
			result.addErrorMessage(getColumNo("manager") + "负责人只能输入2-20个字符");
		}

		if (domain.getArea() != null && domain.getArea() > 999999999) {
			result.addErrorMessage(getColumNo("area") + "面积不能大于999999999平方米");
		}
		if (domain.getEmployeeNumber() != null
				&& domain.getEmployeeNumber() > 999999999) {
			result.addErrorMessage(getColumNo("employeeNumber")
					+ "从业人数不能大于999999999人");
		}

		if (domain.getPartyNum() != null) {
			if (domain.getPartyNum() > 999999999) {
				result.addErrorMessage(getColumNo("PartyNum")
						+ "党员人数不能大于999999999人");
			} else if (domain.getEmployeeNumber() != null
					&& domain.getPartyNum() > domain.getEmployeeNumber()) {
				result.addErrorMessage(getColumNo("PartyNum") + "党员人数不能大于从业人数");
			}

		}
		if (!validateHelper.emptyString(domain.getIntroduction())) {
			if (validateHelper.illegalStringLength(0, 300,
					domain.getIntroduction())) {
				result.addErrorMessage(getColumNo("introduction")
						+ "简介不能输入大于300字符");
			}
		}
		if (!validateHelper.emptyString(domain.getHonor())) {
			if (validateHelper.illegalStringLength(0, 300, domain.getHonor())) {
				result.addErrorMessage(getColumNo("honor") + "荣誉不能输入大于300字符");
			}
		}
		if (!validateHelper.emptyString(domain.getRemark())) {
			if (validateHelper.illegalStringLength(0, 300, domain.getRemark())) {
				result.addErrorMessage(getColumNo("remark") + "备注不能输入大于300字符");
			}
		}
		return result;
	}

	public String getColumNo(String key) {
		return ExcelImportHelper.getColumNo(key);
	}
}
