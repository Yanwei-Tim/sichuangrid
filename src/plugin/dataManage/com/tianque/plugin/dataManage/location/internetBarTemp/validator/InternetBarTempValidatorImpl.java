package com.tianque.plugin.dataManage.location.internetBarTemp.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.plugin.dataManage.location.internetBarTemp.domain.InternetBarTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 上网服务场所信息的验证
 * 
 * @author xiaofeiyang
 */
@Component("internetBarTempValidator")
public class InternetBarTempValidatorImpl implements
		DomainValidatorTemp<InternetBarTemp> {

	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	@Override
	public ValidateResult validate(InternetBarTemp domain) {
		ValidateResult validateResult = new ValidateResult();
		if (validateHelper.emptyString(domain.getName())) {
			validateResult.addErrorMessage(getColumNo("name") + "单位名称必须输入");
		} else if (validateHelper.illegalStringLength(2, 50, domain.getName())) {
			validateResult.addErrorMessage(getColumNo("name")
					+ "单位名称只能输入2-50个字符");
		}
		if (!validateHelper.emptyString(domain.getName())
				&& !validateHelper.isConSpeCharacters(domain.getName())) {
			validateResult.addErrorMessage(getColumNo("name") + "单位名称不能有特殊字符");
		}
		if (validateOrgIsNotNull(domain.getOrganization())
				&& !validateOrgIfExsist(domain.getOrganization())) {
			validateResult.addErrorMessage(getColumNo("organization")
					+ "找不到指定网格");
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
			validateResult.addErrorMessage(getColumNo("mobile") + "联系方式输入有误");
		}
		if (!validateHelper.emptyString(domain.getMobile())
				&& validateHelper
						.illegalStringLength(0, 20, domain.getMobile())) {
			validateResult
					.addErrorMessage(getColumNo("mobile") + "联系方式最多输入20位");
		}
		if (validateHelper.illegalStringLength(0, 50, domain.getAddress())) {
			validateResult.addErrorMessage(getColumNo("address")
					+ "单位地址最多输入50位");
		}
		if (validateHelper.illegalStringLength(0, 20, domain.getManager())) {
			validateResult
					.addErrorMessage(getColumNo("manager") + "负责人最多输入20位");
		}
		if (validateHelper.illegalStringLength(0, 20, domain.getBarCode())) {
			validateResult.addErrorMessage(getColumNo("barCode")
					+ "网吧编号最多输入20位");
		}
		if (!validateHelper.emptyString(domain.getNetAccessProviders())
				&& !validateHelper.isConSpeCharacters(domain
						.getNetAccessProviders())) {
			validateResult.addErrorMessage(getColumNo("netAccessProviders")
					+ "宽带接入服务商不能有特殊字符");
		}
		if (!validateHelper.emptyString(domain.getNetAccessProviders())
				&& validateHelper.illegalStringLength(0, 50,
						domain.getNetAccessProviders())) {
			validateResult.addErrorMessage(getColumNo("netAccessProviders")
					+ "宽带接入服务商最多输入50个字符");
		}
		if (!validateHelper.emptyString(domain.getUseIP())
				&& !validateHelper.isConSpeCharacters(domain.getUseIP())) {
			validateResult
					.addErrorMessage(getColumNo("useIP") + "现使用IP不能有特殊字符");
		}
		if (!validateHelper.emptyString(domain.getUseIP())
				&& validateHelper.illegalStringLength(0, 50, domain.getUseIP())) {
			validateResult.addErrorMessage(getColumNo("useIP")
					+ "现使用IP最多输入50个字符");
		}
		if (validateHelper.illegalStringLength(0, 20,
				domain.getInternetAccessType())) {
			validateResult.addErrorMessage(getColumNo("internetAccessType")
					+ "宽带接入方式最多输入20位");
		}
		if (validateHelper.illegalStringLength(0, 20,
				domain.getNetCultureLicenceNo())) {
			validateResult.addErrorMessage(getColumNo("netCultureLicenceNo")
					+ "网络文化经营许可证号最多输入20位");
		}
		if (validateHelper.illegalStringLength(0, 20,
				domain.getNetSecurityAuditNo())) {
			validateResult.addErrorMessage(getColumNo("netSecurityAuditNo")
					+ "网络安全审核意见书号最多输入20位");
		}
		if (validateHelper.illegalStringLength(0, 20,
				domain.getFireAuditDocumentNo())) {
			validateResult.addErrorMessage(getColumNo("fireAuditDocumentNo")
					+ "消防审核意见书号最多输入20位");
		}
		if (!validateHelper.nullObj(domain.getTempNetCardNum())
				&& validateHelper.illegalNumberZZ(domain.getTempNetCardNum()
						.toString())
				&& !(domain.getTempNetCardNum().longValue() < 10000000000l))
			validateResult.addErrorMessage(getColumNo("tempNetCardNum")
					+ "临时上网卡数量为1-999999999的正整数");
		if (!validateHelper.nullObj(domain.getTotalComputerNum())
				&& validateHelper.illegalNumberZZ(domain.getTotalComputerNum()
						.toString())
				&& !(domain.getTotalComputerNum().longValue() < 10000000000l))
			validateResult.addErrorMessage(getColumNo("totalComputerNum")
					+ "电脑总数为1-9999999999的正整数");
		if (domain.getOperationArea() != null) {
			if (domain.getOperationArea() < 0
					|| domain.getOperationArea() > 9999999.99) {
				validateResult.addErrorMessage(getColumNo("operationArea")
						+ "营业面积为1-9999999.99的正数");
			}
		}
		if (validateHelper.illegalStringLength(0, 200, domain.getRemark())) {
			validateResult.addErrorMessage(getColumNo("remark") + "备注最多输入200位");
		}
		return validateResult;
	}

	private boolean validateOrgIfExsist(Organization org) {
		Organization organization = organizationDubboService
				.getSimpleOrgById(org.getId());
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
