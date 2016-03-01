package com.tianque.validate.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Hospital;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("hospitalValidator")
public class HospitalValidatorImpl implements DomainValidator<Hospital> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
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
	public ValidateResult validate(Hospital domain) {
		ValidateResult validateResult = new ValidateResult();
		boolean orgIsNotNull = validateOrgIsNotNull(domain.getOrganization());
		if (!orgIsNotNull) {
			validateResult.addErrorMessage(getColumNo("organization") + "所属网格必须输入");
		}
		boolean orgIsNotGrid = (orgIsNotNull && !validateOrgIsGrid(domain
				.getOrganization()));
		if (orgIsNotGrid) {
			validateResult.addErrorMessage(getColumNo("organization") + "所属网格必须为片组片格");
		}
		boolean nameEmpty = validateHelper
				.emptyString(domain.getHospitalName());
		if (nameEmpty) {
			validateResult.addErrorMessage(getColumNo("hospitalName") + "医院名称必须输入");
		}
		if (!nameEmpty
				&& validateHelper.illegalStringLength(2, 30,
						domain.getHospitalName())) {
			validateResult.addErrorMessage(getColumNo("hospitalName") + "医院名称只能输入2-30个字符");
		}
		boolean addressEmpty = validateHelper.emptyString(domain.getAddress());
		if (addressEmpty) {
			validateResult.addErrorMessage(getColumNo("address") + "医院地址必须输入");
		}
		if (!addressEmpty
				&& validateHelper.illegalStringLength(2, 50,
						domain.getAddress())) {
			validateResult.addErrorMessage(getColumNo("address") + "医院地址只能输入2-50个字符");
		}
		if (!validateHelper.emptyString(domain.getAffiliatedUnit())
				&& validateHelper.illegalStringLength(2, 50,
						domain.getAffiliatedUnit())) {
			validateResult.addErrorMessage(getColumNo("affiliatedUnit") + "所属单位只能输入2-50个字符");
		}
		if (!validateHelper.emptyString(domain.getDirector())
				&& validateHelper.illegalStringLength(2, 20,
						domain.getDirector())) {
			validateResult.addErrorMessage(getColumNo("director") + "医院院长只能输入2-20个字符");
		}
		if (!validateHelper.emptyString(domain.getTelephone())
				&& validateHelper.illegalTelephone(domain.getTelephone())) {
			validateResult.addErrorMessage(getColumNo("telephone") + "联系电话只能输入数字和-");
		}
		if (validateHelper.illegalStringLength(0, 15, domain.getTelephone())) {
			validateResult.addErrorMessage(getColumNo("telephone") + "联系人电话不能大于15个字符");
		}
		if (!validateHelper.emptyString(domain.getMobileNumber())
				&& validateHelper.illegalMobilePhone(domain.getMobileNumber())) {
			validateResult.addErrorMessage(getColumNo("mobileNumber") + "联系手机只能输入1开头的11位数字");
		}
		if (validateHelper.illegalStringLength(0, 50, domain.getEmail())) {
			validateResult.addErrorMessage(getColumNo("mobileNumber") + "联系人E-mail不能大于50个字符");
		}
		if (!validateHelper.emptyString(domain.getFax())
				&& validateHelper.illegalTelephone(domain.getFax())) {
			validateResult.addErrorMessage(getColumNo("fax") + "联系人传真只能输入数字和-");
		}
		if (validateHelper.illegalStringLength(0, 15, domain.getFax())) {
			validateResult.addErrorMessage(getColumNo("fax") + "联系人传真不能大于15个字符");
		}
		if (!validateHelper.emptyString(domain.getEmail())
				&& validateHelper.illegalEmail(domain.getEmail())) {
			validateResult.addErrorMessage(getColumNo("email")
					+ "电子邮箱输入不正确,例如：youname@youcompany.com");
		}
		if (validateHelper.illegalStringLength(0, 200, domain.getRemark())) {
			validateResult.addErrorMessage(getColumNo("remark") + "备注不能输入大于200字符");
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
