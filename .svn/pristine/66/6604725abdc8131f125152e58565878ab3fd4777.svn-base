package com.tianque.baseInfo.publicPlace.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.publicPlace.domain.PublicPlace;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("publicPlaceValidator")
public class PublicPlaceValidatorImpl implements DomainValidator<PublicPlace> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(PublicPlace domain) {
		ValidateResult validatResult = new ValidateResult();
		if (null == domain) {
			validatResult.addErrorMessage("数据为空，校验已停止，请仔细检查！");
		}
		if (validateHelper.emptyString(domain.getPlaceName())) {
			validatResult.addErrorMessage(getColumNo("placeName") + "场所名字不能为空！");
		}
		if (!validateHelper.emptyString(domain.getPlaceName())
				&& !validateHelper.isConSpeCharacters(domain.getPlaceName())) {
			validatResult.addErrorMessage(getColumNo("placeName") + "场所名字不能有特殊字符！");
		}
		if (!validateHelper.emptyString(domain.getPlaceName())
				&& validateHelper.illegalStringLength(2, 50, domain.getPlaceName())) {
			validatResult.addErrorMessage(getColumNo("placeName") + "场所名字只能输入2-50个字符");
		}
		if (validateHelper.emptyString(domain.getPlaceAddress())) {
			validatResult.addErrorMessage(getColumNo("placeAddress") + "场所地址不能为空！");
		}
		if (!validateHelper.emptyString(domain.getPlaceAddress())
				&& !validateHelper.isConSpeCharacters(domain.getPlaceAddress())) {
			validatResult.addErrorMessage(getColumNo("placeAddress") + "场所地址不能有特殊字符！");
		}
		if (!validateHelper.emptyString(domain.getPlaceAddress())
				&& validateHelper.illegalStringLength(2, 50, domain.getPlaceAddress())) {
			validatResult.addErrorMessage(getColumNo("placeAddress") + "场所地址只能输入2-50个字符");
		}
		if (!orgIsNotNull(domain.getOrganization())) {
			validatResult.addErrorMessage(getColumNo("organization") + "所属网格必须输入");
		}
		if (orgIsNotNull(domain.getOrganization()) && !orgIfExsist(domain.getOrganization())) {
			validatResult.addErrorMessage(getColumNo("organization") + "找不到指定网格");
		}
		if (orgIsNotNull(domain.getOrganization()) && !orgIsGrid(domain.getOrganization())) {

			validatResult.addErrorMessage(getColumNo("organization") + "所属网格必须为片组片格");
		}
		if (!validateHelper.emptyString(domain.getRegistrationNumber())
				&& validateHelper.illegalNumberZZ(domain.getRegistrationNumber())) {
			validatResult.addErrorMessage(getColumNo("registrationNumber") + "备案登记号码只能输入正整数");
		}
		if (validateHelper.illegalStringLength(0, 20, domain.getRegistrationNumber())) {
			validatResult.addErrorMessage(getColumNo("registrationNumber") + "备案登记号码最多输入20个数字");
		}
		if (!validateHelper.emptyString(domain.getManager())
				&& validateHelper.illegalStringLength(2, 20, domain.getManager())) {
			validatResult.addErrorMessage(getColumNo("manager") + "负责人只能输入2-20个字符");
		}
		return validatResult;
	}

	public String getColumNo(String key) {
		StringBuffer bf = new StringBuffer();
		if (!StringUtils.isEmpty(ExcelImportHelper.getDataColumMap(key))) {
			bf.append("第[").append(ExcelImportHelper.realRow.get()).append("]行");
			bf.append("第[").append(Integer.valueOf(ExcelImportHelper.getDataColumMap(key)) + 1)
					.append("]列");
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
		Organization organization = organizationDubboService.getSimpleOrgById(org.getId());
		if (organization == null) {
			return false;
		}
		return true;
	}

	private boolean orgIsGrid(Organization org) {
		return organizationDubboService.isGridOrganization(org.getId());
	}
}
