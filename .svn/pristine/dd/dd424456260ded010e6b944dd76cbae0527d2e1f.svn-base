package com.tianque.baseInfo.scenicManage.scenicTraffic.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.scenicManage.scenicTraffic.domain.ScenicTraffic;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("scenicTrafficValidator")
public class ScenicTrafficValidateImpl
		implements
			DomainValidator<ScenicTraffic> {

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
	public ValidateResult validate(ScenicTraffic domain) {
		ValidateResult validateResult = new ValidateResult();
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
		if (null == domain.getTrafficType()
				|| domain.getTrafficType().getId() == null) {
			validateResult
					.addErrorMessage(getColumNo("trafficType") + "类型必须输入");
		}
		if (validateHelper
				.illegalStringLength(0, 300, domain.getAroundScenic())) {
			validateResult.addErrorMessage(getColumNo("aroundScenic")
					+ "周边景点最多输入300位");
		}
		if (!validateHelper.emptyString(domain.getPhone())
				&& (validateHelper.illegalMobilePhone(domain.getPhone()) && validateHelper
						.illegalTelephone(domain.getPhone()))) {
			validateResult.addErrorMessage(getColumNo("phone") + "联系电话输入有误");
		}
		if (!validateHelper.emptyString(domain.getPhone())
				&& validateHelper.illegalStringLength(0, 20, domain.getPhone())) {
			validateResult.addErrorMessage(getColumNo("phone") + "联系电话最多输入20位");
		}
		if (!validateHelper.emptyString(domain.getManagerPeoplePhone())
				&& (validateHelper.illegalMobilePhone(domain
						.getManagerPeoplePhone()) && validateHelper
						.illegalTelephone(domain.getManagerPeoplePhone()))) {
			validateResult.addErrorMessage(getColumNo("managerPeoplePhone")
					+ "负责人电话输入有误");
		}
		if (!validateHelper.emptyString(domain.getManagerPeoplePhone())
				&& validateHelper.illegalStringLength(0, 20, domain
						.getManagerPeoplePhone())) {
			validateResult.addErrorMessage(getColumNo("managerPeoplePhone")
					+ "负责人电话最多输入20位");
		}
		if (validateHelper.illegalStringLength(0, 30, domain.getStartAddress())) {
			validateResult.addErrorMessage(getColumNo("startAddress")
					+ "起点最多输入30位");
		}
		if (validateHelper.illegalStringLength(0, 30, domain.getEndAddress())) {
			validateResult.addErrorMessage(getColumNo("endAddress")
					+ "终点最多输入30位");
		}
		if (validateHelper.illegalStringLength(0, 100, domain.getTrafficName())) {
			validateResult.addErrorMessage(getColumNo("trafficName")
					+ "线路名称最多输入100位");
		}
		if (validateHelper.illegalStringLength(0, 100, domain.getManagerUnit())) {
			validateResult.addErrorMessage(getColumNo("managerUnit")
					+ "负责单位最多输入100位");
		}
		if (validateHelper
				.illegalStringLength(0, 15, domain.getManagerPeople())) {
			validateResult.addErrorMessage(getColumNo("managerPeople")
					+ "负责人最多输入15位");
		}
		if (validateHelper.illegalStringLength(0, 10, domain.getFirstBusTime())) {
			validateResult.addErrorMessage(getColumNo("firstBusTime")
					+ "最早班车时间最多输入10位");
		}
		if (validateHelper.illegalStringLength(0, 10, domain.getLastBusTime())) {
			validateResult.addErrorMessage(getColumNo("lastBusTime")
					+ "最晚班车时间最多输入10位");
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
					.append(
							Integer.valueOf(ExcelImportHelper
									.getDataColumMap(key)) + 1).append("]列");
		} else {
			bf.append("");
		}
		return bf.toString();
	}

}
