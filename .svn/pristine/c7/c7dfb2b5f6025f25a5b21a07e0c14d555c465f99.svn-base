package com.tianque.validate.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("houseInfoValidator")
public class HouseInfoValidatorImpl implements DomainValidator<HouseInfo> {
	private ValidateHelper validateHelper;
	private OrganizationDubboService organizationDubboService;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	private boolean typeIsNotNull(PropertyDict p) {
		if (p == null || p.getId() == null) {
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

	private boolean validateOrgIfExsist(Organization org) {
		Organization organization = organizationDubboService.getSimpleOrgById(org
				.getId());
		if (organization == null) {
			return false;
		}
		return true;
	}

	private boolean validateOrgIsGrid(Organization org) {
		return organizationDubboService.isGridOrganization(org.getId());
	}

	@Override
	public ValidateResult validate(HouseInfo domain) {
		ValidateResult validateResult = new ValidateResult();
		// if (validateHelper.emptyString(domain.getHouseCode())) {
		// validateResult.addErrorMessage(getColumNo("houseCode") + "住房编号必须输入");
		// }
		if (!validateHelper.emptyString(domain.getHouseCode())
				&& validateHelper.illegalStringLength(0, 50,
						domain.getHouseCode())) {
			validateResult.addErrorMessage(getColumNo("houseCode")
					+ "住房编号最多只能输入50个字符");
		}
		if (!typeIsNotNull(domain.getAddressType())) {
			validateResult.addErrorMessage(getColumNo("addressType")
					+ "常住地址类型必须输入");
		}
		if (validateHelper.emptyString(domain.getAddress())) {
			validateResult.addErrorMessage(getColumNo("address") + "常住地址必须输入");
		}
		if (!validateHelper.emptyString(domain.getAddress())
				&& validateHelper.illegalStringLength(0, 50,
						domain.getAddress())) {
			validateResult.addErrorMessage(getColumNo("addressType")
					+ "常住地址最多只能输入50个字符");
		}
		if (!validateHelper.emptyString(domain.getHouseOwnerIdCardNo())
				&& validateHelper.illegalIdcard(domain.getHouseOwnerIdCardNo())) {
			validateResult.addErrorMessage(getColumNo("houseOwnerIdCardNo")
					+ "房主身份证号码格式输入不正确");
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
		/*
		 * if (!validateHelper.emptyString(domain.getMobileNumber()) &&
		 * validateHelper.illegalMobilePhone(domain.getMobileNumber())){
		 * validateResult.addErrorMessage("手机号码只能输入1开头的11位数字"); } if
		 * (!validateHelper.emptyString(domain.getLegalPersonMobile()) &&
		 * validateHelper.illegalMobilePhone(domain.getLegalPersonMobile())){
		 * validateResult.addErrorMessage("法人代表手机只能输入1开头的11位数字"); } if
		 * (!validateHelper.emptyString(domain.getTelephone()) &&
		 * validateHelper.illegalTelephone(domain.getTelephone())){
		 * validateResult.addErrorMessage("固定电话只能输入数字和-"); } if (null !=
		 * domain.getRoomNumber()&&
		 * (domain.getRoomNumber()<0||domain.getRoomNumber()>999999)){
		 * validateResult.addErrorMessage("房间数只能输入不大于999999的正整数"); } if (null !=
		 * domain.getHouseArea()&&
		 * (domain.getHouseArea()<0||domain.getHouseArea()>99999999)){
		 * validateResult.addErrorMessage("住房面积只能输入不大于99999999的正数"); } if (null
		 * != domain.getRealLiveNumber()&&
		 * (domain.getRealLiveNumber()<=0||domain.getRealLiveNumber()>999999)){
		 * validateResult.addErrorMessage("实住人数只能输入不大于999999的非负整数"); } if
		 * (!validateHelper.emptyString(domain.getHiddenDangerStatus()) &&
		 * validateHelper
		 * .illegalStringLength(0,300,domain.getHiddenDangerStatus())){
		 * validateResult.addErrorMessage("隐患情况最多只能输入50个字符"); }
		 */
		if (!validateHelper.emptyString(domain.getRemark())
				&& validateHelper.illegalStringLength(0, 300,
						domain.getRemark())) {
			validateResult.addErrorMessage("备注最多只能输入300个字符");
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
}
