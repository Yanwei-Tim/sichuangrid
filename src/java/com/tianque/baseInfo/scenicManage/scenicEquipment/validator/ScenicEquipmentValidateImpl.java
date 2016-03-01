package com.tianque.baseInfo.scenicManage.scenicEquipment.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.scenicManage.scenicEquipment.domain.ScenicEquipment;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("scenicEquipmentValidator")
public class ScenicEquipmentValidateImpl implements
		DomainValidator<ScenicEquipment> {

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

	@Override
	public ValidateResult validate(ScenicEquipment domain) {
		ValidateResult validateResult = new ValidateResult();
		if (validateHelper.emptyString(domain.getEquipName())) {
			validateResult.addErrorMessage(getColumNo("equipName") + "名称必须输入");
		} else if (validateHelper.illegalStringLength(2, 50,
				domain.getEquipName())) {
			validateResult.addErrorMessage(getColumNo("equipName")
					+ "名称只能输入2-50个字符");
		}
		if (!validateHelper.emptyString(domain.getEquipName())
				&& !validateHelper.isConSpeCharacters(domain.getEquipName())) {
			validateResult.addErrorMessage(getColumNo("equipName")
					+ "名称不能有特殊字符");
		}
		if (!validateOrgIsNotNull(domain.getOrganization())) {
			validateResult.addErrorMessage(getColumNo("organization")
					+ "所属网格必须输入");
		}
		if (validateOrgIsNotNull(domain.getOrganization())
				&& !validateOrgIfExsist(domain.getOrganization())) {
			validateResult.addErrorMessage(getColumNo("organization")
					+ "找不到指定网格");
		}
		if (validateOrgIsNotNull(domain.getOrganization())
				&& !validateOrgIsGrid(domain.getOrganization())) {
			validateResult.addErrorMessage(getColumNo("organization")
					+ "所属网格必须为片组片格");
		}
		boolean orgIsNotNull = validateOrgIsNotNull(domain.getOrganization());
		String userOrgInternalCode = organizationDubboService.getSimpleOrgById(
				ThreadVariable.getUser().getOrganization().getId())
				.getOrgInternalCode();
		String userOrgName = organizationDubboService.getSimpleOrgById(
				ThreadVariable.getUser().getOrganization().getId())
				.getOrgName();
		if (userOrgInternalCode.length() == 13 && orgIsNotNull
				&& domain.getOrganization().getOrgName() != null) {
			if (!domain.getOrganization().getOrgName().equals(userOrgName)) {
				validateResult.addErrorMessage(getColumNo("organization")
						+ "权限越界，不能将数据导入到该网格下！");
			}
		}
		if (!validateHelper.emptyString(domain.getMobile())
				&& (validateHelper.illegalMobilePhone(domain.getMobile()) && validateHelper
						.illegalTelephone(domain.getMobile()))) {
			validateResult.addErrorMessage(getColumNo("mobile") + "联系电话输入有误");
		}
		if (!validateHelper.emptyString(domain.getMobile())
				&& validateHelper
						.illegalStringLength(0, 20, domain.getMobile())) {
			validateResult
					.addErrorMessage(getColumNo("mobile") + "联系方式最多输入20位");
		}
		if (!validateHelper.emptyString(domain.getManagerMobile())
				&& (validateHelper
						.illegalMobilePhone(domain.getManagerMobile()) && validateHelper
						.illegalTelephone(domain.getManagerMobile()))) {
			validateResult.addErrorMessage(getColumNo("manageMobile")
					+ "负责人电话输入有误");
		}
		if (!validateHelper.emptyString(domain.getManagerMobile())
				&& validateHelper.illegalStringLength(0, 20,
						domain.getManagerMobile())) {
			validateResult.addErrorMessage(getColumNo("manageMobile")
					+ "负责人电话最多输入20位");
		}
		if (validateHelper.illegalStringLength(0, 50, domain.getEquipAddress())) {
			validateResult.addErrorMessage(getColumNo("equipAddress")
					+ "地址最多输入50位");
		}
		if (!validateHelper.emptyString(domain.getEquipAddress())
				&& !validateHelper.isConSpeCharacters(domain.getEquipAddress())) {
			validateResult.addErrorMessage(getColumNo("equipAddress")
					+ "地址不能有特殊字符");
		}
		if (validateHelper.illegalStringLength(0, 20, domain.getManager())) {
			validateResult
					.addErrorMessage(getColumNo("manager") + "负责人最多输入20位");
		}
		if (null == domain.getEquipType()) {
			validateResult.addErrorMessage(getColumNo("equipType") + "类型必须输入");
		}
		if (validateHelper
				.illegalStringLength(0, 300, domain.getAroundScenic())) {
			validateResult.addErrorMessage(getColumNo("aroundScenic")
					+ "周边景点最多输入300位");
		}
		if (validateHelper.illegalStringLength(0, 300, domain.getRemark())) {
			validateResult.addErrorMessage(getColumNo("remark") + "备注最多输入300位");
		}
		return validateResult;
	}

	private boolean validateOrgIsGrid(Organization org) {
		return organizationDubboService.isGridOrganization(org.getId());
	}

	private boolean validateOrgIfExsist(Organization org) {
		Organization organization = organizationDubboService.getSimpleOrgById(org
				.getId());
		if (organization == null) {
			return false;
		}
		return true;
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
