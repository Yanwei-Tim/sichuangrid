package com.tianque.baseInfo.actualCompany.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("actualCompanyValidator")
public class ActualCompanyValidatorImpl implements
		DomainValidator<ActualCompany> {

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

	private boolean orgIsNotNull(Organization org) {
		if (org == null) {
			return false;
		}
		if (org != null && org.getId() == null) {
			return false;
		}
		return true;
	}

	private boolean orgIfExsist(Organization org) {
		Organization organization = organizationDubboService.getSimpleOrgById(org
				.getId());
		if (organization == null) {
			return false;
		}
		return true;
	}

	private boolean orgIsGrid(Organization org) {
		return organizationDubboService.isGridOrganization(org.getId());
	}

	@Override
	public ValidateResult validate(ActualCompany domain) {
		ValidateResult validateResult = new ValidateResult();

		if (!orgIsNotNull(domain.getOrganization())) {
			validateResult.addErrorMessage(getColumNo("organization") + "所属网格必须输入");
		}
		if (orgIsNotNull(domain.getOrganization()) && !orgIfExsist(domain.getOrganization())) {
			validateResult.addErrorMessage(getColumNo("organization") + "找不到指定网格");
		}
		if (orgIsNotNull(domain.getOrganization()) && !orgIsGrid(domain.getOrganization())) {

			validateResult.addErrorMessage(getColumNo("organization") + "所属网格必须为片组片格");
		}
		if (validateHelper.emptyString(domain.getCompanyName())
				|| "".equals(domain.getCompanyName())) {
			validateResult.addErrorMessage(getColumNo("companyName") + "单位名称不能为空");
		}
		if (!validateHelper.emptyString(domain.getCompanyName())
				&& validateHelper.illegalStringLength(0, 50, domain.getCompanyName())) {
			validateResult.addErrorMessage(getColumNo("companyName") + "单位名称不能大于50字符");
		}
		if (!validateHelper.emptyString(domain.getCompanyName())
				&& !validateHelper.isConSpeCharacters(domain.getCompanyName())) {
			validateResult.addErrorMessage(getColumNo("companyName") + "单位名称不能有特殊字符");
		}
		
		if (validateHelper.emptyString(domain.getCompanyAddress())
				|| "".equals(domain.getCompanyAddress())) {
			validateResult.addErrorMessage(getColumNo("companyAddress") + "单位地址不能为空");
		}
		if(!validateHelper.isConSpeCharacters(domain.getCompanyAddress())){
			validateResult.addErrorMessage(getColumNo("companyAddress") + "单位地址不能有特殊字符");
		}

		boolean legalPersonEmpty = validateHelper.emptyString(domain.getCorporateRepresentative());
		if (!legalPersonEmpty
				&& validateHelper.illegalStringLength(2, 20, domain.getCorporateRepresentative())) {
			validateResult
					.addErrorMessage(getColumNo("corporateRepresentative") + "法人代表只能输入2-20个字符");
		}
		if (!legalPersonEmpty
				&& !validateHelper.isConSpeCharacters(domain.getCorporateRepresentative())) {
			validateResult.addErrorMessage(getColumNo("corporateRepresentative") + "法人代表不能包含特殊字符");
		}

		// fateson add
		if (validateHelper.emptyString(domain.getBusinessLicenseNo())
				|| "".equals(domain.getBusinessLicenseNo())) {
			validateResult.addErrorMessage(getColumNo("businessLicenseNo") + "营业执照号码不能为空");
		}
		if (validateHelper.illegalExculdeParticalChar(domain.getBusinessLicenseNo())) {
			validateResult.addErrorMessage(getColumNo("businessLicenseNo") + "营业执照号码不能包含特殊字符");
		}
		if (!validateHelper.emptyString(domain.getSupervisoryDepartment())
				&& validateHelper.illegalExculdeParticalChar(domain.getSupervisoryDepartment())) {
			validateResult.addErrorMessage(getColumNo("supervisoryDepartment") + "管理部门不能包含特殊字符");
		}
		if (!validateHelper.emptyString(domain.getSecurityChief())
				&& validateHelper.illegalExculdeParticalChar(domain.getSecurityChief())) {
			validateResult.addErrorMessage(getColumNo("securityChief") + "治安负责人不能包含特殊字符");
		}
		if (validateHelper.emptyString(domain.getOrgCode()) || "".equals(domain.getOrgCode())) {
			validateResult.addErrorMessage(getColumNo("orgCode") + "组织机构代码不能为空");
		}
		if(!validateHelper.isConSpeCharacters(domain.getOrgCode())){
			validateResult.addErrorMessage(getColumNo("orgCode") + "组织机构代码不合法");
		}
		if (!validateHelper.emptyString(domain.getTelephone())
				&& validateHelper.illegalTelephone(domain.getTelephone())) {
			validateResult.addErrorMessage(getColumNo("telephone") + "固定电话只能输入数字和-");
		}
		if (!validateHelper.emptyString(domain.getBusinessScope()) 
				&& validateHelper.illegalExculdeParticalChar(domain.getBusinessScope())) {
			validateResult.addErrorMessage(getColumNo("businessScope") + "经营范围不能包含特殊字符");
		}
		if (!validateHelper.emptyString(domain.getRegistrationAddress()) 
				&& validateHelper.illegalExculdeParticalChar(domain.getRegistrationAddress())) {
			validateResult.addErrorMessage(getColumNo("registrationAddress") + "注册地址不能包含特殊字符");
		}
		if (!validateHelper.emptyString(domain.getCompetentDepartment()) 
				&& validateHelper.illegalExculdeParticalChar(domain.getCompetentDepartment())) {
			validateResult.addErrorMessage(getColumNo("competentDepartment") + "主管部门不能包含特殊字符");
		}
		if (!validateHelper.emptyString(domain.getRemark())
				&& validateHelper.illegalStringLength(0, 300, domain.getRemark())) {
			validateResult.addErrorMessage(getColumNo("remark") + "备注最多只能输入300个字符");
		}

		Double registeredCapital = domain.getRegisteredCapital();
		if (registeredCapital != null) {
			if (!validateHelper.emptyString(registeredCapital + "")) {
				long registeredCapitalLong = registeredCapital.longValue();
				String registeredCapitalString = String.valueOf(registeredCapitalLong);
				boolean result = validateHelper.illegalStringLength(0, 6, registeredCapitalString);
				if (result) {
					validateResult.addErrorMessage(getColumNo("registeredCapital")
							+ "注册资金最多不能超过6位(万元)");
				}

			}
		}
		return validateResult;
	}

	public String getColumNo(String key) {
		return ExcelImportHelper.getColumNo(key);
	}
}
