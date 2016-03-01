package com.tianque.baseInfo.scenicManage.scenicSpot.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.scenicManage.scenicSpot.domain.ScenicSpot;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("scenicSpotValidator")
public class ScenicSpotValidatorImpl implements DomainValidator<ScenicSpot> {
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
	public ValidateResult validate(ScenicSpot domain) {
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

		boolean nameEmpty = validateHelper.emptyString(domain.getName());
		if (nameEmpty) {
			validateResult.addErrorMessage(getColumNo("name") + "景点名称必须输入");
		}
		if (!nameEmpty
				&& validateHelper.illegalStringLength(2, 30, domain.getName())) {
			validateResult.addErrorMessage(getColumNo("name")
					+ "景点名称只能输入2-30个字符");
		} else if (!nameEmpty
				&& !validateHelper.isConSpeCharacters(domain.getName())) {
			validateResult.addErrorMessage(getColumNo("name") + "景点名不能输入特殊字符");
		}

		if (!validateHelper.emptyString(domain.getAddress())) {
			if (validateHelper.illegalStringLength(0, 50, domain.getAddress())) {
				validateResult.addErrorMessage(getColumNo("address")
						+ "景点地址不能输入大于50个字符");
			} else if (!validateHelper.isConSpeCharacters(domain.getAddress())) {
				validateResult.addErrorMessage(getColumNo("address")
						+ "景点地址不能输入特殊字符");
			}
		}

		if (!validateHelper.emptyString(domain.getTelephone())
				&& validateHelper.illegalTelephone(domain.getTelephone())) {
			validateResult.addErrorMessage(getColumNo("telephone")
					+ "景点电话输入不正确，格式：0571-88888888");
		}

		if (!validateHelper.emptyString(domain.getPersonLiableTelephone())
				&& validateHelper.illegalTelephone(domain
						.getPersonLiableTelephone())) {
			validateResult.addErrorMessage(getColumNo("personLiableTelephone")
					+ "景点负责人电话输入不正确，格式：0571-88888888");
		}

		if (validateHelper.illegalStringLength(0, 300, domain.getRemark())) {
			validateResult.addErrorMessage(getColumNo("remark")
					+ "备注不能输大于300个字符");
		}

		if (validateHelper
				.illegalStringLength(0, 900, domain.getIntroduction())) {
			validateResult.addErrorMessage(getColumNo("introduction")
					+ "景点介绍不能输大于900个字符");
		}

		return validateResult;
	}

	public String getColumNo(String key) {
		return ExcelImportHelper.getColumNo(key);
	}
}
