package com.tianque.validate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.School;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("schoolValidator")
public class SchoolValidatorImpl implements DomainValidator<School> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
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

	private boolean validateOrgIsGrid(Organization org) {
		return organizationDubboService.isGridOrganization(org.getId());
	}

	@Override
	public ValidateResult validate(School domain) {
		ValidateResult validateResult = new ValidateResult();
		boolean orgIsNotNull = validateOrgIsNotNull(domain.getOrganization());
		if (!orgIsNotNull) {
			validateResult.addErrorMessage(getColumNo("organization")
					+ "所属网格必须输入");
		}
		boolean orgIsNotGrid = (orgIsNotNull && !validateOrgIsGrid(domain
				.getOrganization()));
		if (orgIsNotGrid) {
			validateResult.addErrorMessage(getColumNo("organization")
					+ "所属网格必须为片组片格");
		}
		boolean nameEmpty = validateHelper.emptyString(domain.getChineseName());
		if (nameEmpty) {
			validateResult.addErrorMessage(getColumNo("chineseName")
					+ "中文名称必须输入");
		}else{
			if (validateHelper.illegalStringLength(2, 30,
							domain.getChineseName())) {
				validateResult.addErrorMessage(getColumNo("chineseName")
						+ "中文名称只能输入2-30个字符");
			} else if (!validateHelper.isConSpeCharacters(domain.getChineseName())) {
				validateResult.addErrorMessage(getColumNo("chineseName")
						+ "学校名不能输入特殊字符");
			}
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
		} else if (!validateHelper.isConSpeCharacters(domain.getAddress())) {
			validateResult.addErrorMessage(getColumNo("address")
					+ "学校地址不能输入特殊字符");
		}
		if (domain.getType() == null) {
			validateResult.addErrorMessage(getColumNo("type") + "请选择学校类型");
		}
		if (validateHelper.emptyString(domain.getPresident())) {
			validateResult.addErrorMessage(getColumNo("president") + "校长必须输入");
		} else if (validateHelper.illegalStringLength(2, 20,
				domain.getPresident())) {
			validateResult.addErrorMessage(getColumNo("president")
					+ "校长姓名只能输入2-20个字符");
		} else if (!validateHelper.isConSpeCharacters(domain.getPresident())) {
			validateResult.addErrorMessage(getColumNo("president")
					+ "校长姓名不能输入特殊字符");
		}

		if (!validateHelper.emptyString(domain.getPersonLiableTelephone())
				&& validateHelper.illegalTelephone(domain
						.getPersonLiableTelephone())) {
			validateResult.addErrorMessage(getColumNo("personLiableTelephone")
					+ "联系电话输入不正确，格式：0571-88888888");
		}

		if (!validateHelper.emptyString(domain.getPersonLiableMobileNumber())
				&& validateHelper.illegalMobilePhone(domain
						.getPersonLiableMobileNumber())) {
			validateResult
					.addErrorMessage(getColumNo("personLiableMobileNumber")
							+ "联系手机只能输入1开头的11位数字");
		}
		if (domain.getAtSchoolHeadcount() != null) {
			if (domain.getAtSchoolHeadcount() < 0
					|| domain.getAtSchoolHeadcount() > 999999) {
				validateResult.addErrorMessage(getColumNo("atSchoolHeadcount")
						+ "在校人数只能输入0到999999数字");
			}
		}
		if (!validateHelper.emptyString(domain.getEnglishName())) {
			if (validateHelper.illegalEnglishName(domain.getEnglishName())) {
				validateResult.addErrorMessage(getColumNo("englishName")
						+ "英文名称不能输入中文");
			}
			if (validateHelper.illegalExculdeParticalChar(domain
					.getEnglishName())) {
				validateResult.addErrorMessage(getColumNo("englishName")
						+ "英文名称不能输入特殊字符");
			}
			if (validateHelper.illegalStringLength(0, 30,
					domain.getEnglishName())) {
				validateResult.addErrorMessage(getColumNo("englishName")
						+ "英文名称不能输入大于30个字符");
			}
		}

		if (!validateHelper.emptyString(domain.getFax())) {
			if (validateHelper.illegalTelephone(domain.getFax())) {
				validateResult
						.addErrorMessage(getColumNo("fax") + "传真只能输入数字和-");
			}
		}

		if (!validateHelper.emptyString(domain.getEmail())
				&& validateHelper.illegalEmail(domain.getEmail())) {
			validateResult.addErrorMessage(getColumNo("email")
					+ "电子邮箱输入不正确,例如：youname@youcompany.com");
		}

		if (!validateHelper.emptyString(domain.getWebSite())
				&& validateHelper.illegalWebSite(domain.getWebSite())) {
			validateResult.addErrorMessage(getColumNo("webSite")
					+ "请输入以http://开头的学校网址!例:http://www.xxx.com");
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
