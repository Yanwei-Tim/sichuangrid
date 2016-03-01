package com.tianque.baseInfo.publicComplexPlaces.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.publicComplexPlaces.domain.PublicComplexPlaces;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("publicComplexPlacesValidator")
public class PublicComplexPlacesValidatorImpl implements
		DomainValidator<PublicComplexPlaces> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(PublicComplexPlaces domain) {
		ValidateResult validateResult = new ValidateResult();
		if (null == domain) {
			validateResult.addErrorMessage("数据为空，校验已停止，请仔细检查！");
		}
		if (validateHelper.emptyString(domain.getPlaceName())) {
			validateResult.addErrorMessage(getColumNo("placeName")
					+ "场所名字不能为空！");
		}
		if (!validateHelper.emptyString(domain.getPlaceName())
				&& !validateHelper.isConSpeCharacters(domain.getPlaceName())) {
			validateResult.addErrorMessage(getColumNo("placeName")
					+ "场所名字不能有特殊字符！");
		}
		if (!validateHelper.emptyString(domain.getPlaceName())
				&& validateHelper.illegalStringLength(2, 30,
						domain.getPlaceName())) {
			validateResult.addErrorMessage(getColumNo("placeName")
					+ "场所名字只能输入2-30个字符");
		}
		if (validateHelper.emptyString(domain.getPlaceAddress())) {
			validateResult.addErrorMessage(getColumNo("placeAddress")
					+ "场所地址不能为空！");
		}
		if (!validateHelper.emptyString(domain.getPlaceAddress())
				&& !validateHelper.isConSpeCharacters(domain.getPlaceAddress())) {
			validateResult.addErrorMessage(getColumNo("placeAddress")
					+ "场所地址不能有特殊字符！");
		}
		if (!validateHelper.emptyString(domain.getPlaceAddress())
				&& validateHelper.illegalStringLength(2, 50,
						domain.getPlaceAddress())) {
			validateResult.addErrorMessage(getColumNo("placeAddress")
					+ "场所地址只能输入2-50个字符");
		}
		if (!orgIsNotNull(domain.getOrganization())) {
			validateResult.addErrorMessage(getColumNo("organization")
					+ "所属网格必须输入");
		}
		if (orgIsNotNull(domain.getOrganization())
				&& !orgIfExsist(domain.getOrganization())) {
			validateResult.addErrorMessage(getColumNo("organization")
					+ "找不到指定网格");
		}
		if (orgIsNotNull(domain.getOrganization())
				&& !orgIsGrid(domain.getOrganization())) {

			validateResult.addErrorMessage(getColumNo("organization")
					+ "所属网格必须为片组片格");
		}
		if (!validateHelper.emptyString(domain.getManager())
				&& validateHelper.illegalStringLength(2, 20,
						domain.getManager())) {
			validateResult.addErrorMessage(getColumNo("manager")
					+ "负责人只能输入2-20个字符");
		}
		if (!validateHelper.emptyString(domain.getManagerTelephone())
				&& validateHelper
						.illegalTelephone(domain.getManagerTelephone())) {
			validateResult.addErrorMessage(getColumNo("personLiableTelephone")
					+ "联系电话输入不正确，格式：0571-88888888");
		}

		if (!validateHelper.emptyString(domain.getManagerMobile())
				&& validateHelper.illegalMobilePhone(domain.getManagerMobile())) {
			validateResult
					.addErrorMessage(getColumNo("personLiableMobileNumber")
							+ "联系手机只能输入1开头的11位数字");
		}
		if (validateHelper.illegalStringLength(0, 100, domain.getHiddenCases())) {
			validateResult.addErrorMessage(getColumNo("hiddenCases")
					+ "隐患情况不能输入大于100字符");
		}
		if (validateHelper.illegalStringLength(0, 100,
				domain.getHiddenRectification())) {
			validateResult.addErrorMessage(getColumNo("hiddenRectification")
					+ "隐患整改情况不能输入大于100字符");
		}
		if (validateHelper.illegalStringLength(0, 300, domain.getRemark())) {
			validateResult.addErrorMessage(getColumNo("备注") + "备注不能输入大于300字符");
		}
		return validateResult;
	}

	public String getColumNo(String key) {
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
}
