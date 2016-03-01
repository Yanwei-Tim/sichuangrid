package com.tianque.plugin.dataManage.location.publicPlaceTemp.validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.location.publicPlaceTemp.domain.PublicPlaceTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("publicPlaceTempValidator")
public class PublicPlaceTempValidatorImpl implements
		DomainValidatorTemp<PublicPlaceTemp> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(PublicPlaceTemp domain) {
		ValidateResult validatResult = new ValidateResult();
		if (null == domain) {
			validatResult.addErrorMessage("数据为空，校验已停止，请仔细检查！");
		}
		if (validateHelper.emptyString(domain.getName())) {
			validatResult.addErrorMessage(getColumNo("name") + "场所名字不能为空！");
		}
		if (!validateHelper.emptyString(domain.getName())
				&& !validateHelper.isConSpeCharacters(domain.getName())) {
			validatResult.addErrorMessage(getColumNo("name") + "场所名字不能有特殊字符！");
		}
		if (!validateHelper.emptyString(domain.getName())
				&& validateHelper.illegalStringLength(2, 50, domain.getName())) {
			validatResult.addErrorMessage(getColumNo("name")
					+ "场所名字只能输入2-50个字符");
		}
		if (validateHelper.emptyString(domain.getAddress())) {
			validatResult.addErrorMessage(getColumNo("address") + "场所地址不能为空！");
		}
		if (!validateHelper.emptyString(domain.getAddress())
				&& !validateHelper.isConSpeCharacters(domain.getAddress())) {
			validatResult.addErrorMessage(getColumNo("address")
					+ "场所地址不能有特殊字符！");
		}
		if (!validateHelper.emptyString(domain.getRegistrationNumber())
				&& validateHelper.illegalNumberZZ(domain
						.getRegistrationNumber())) {
			validatResult.addErrorMessage(getColumNo("registrationNumber")
					+ "备案登记号码只能输入正整数");
		}
		if (validateHelper.illegalStringLength(0, 20,
				domain.getRegistrationNumber())) {
			validatResult.addErrorMessage(getColumNo("registrationNumber")
					+ "备案登记号码最多输入20个数字");
		}
		if (!validateHelper.emptyString(domain.getManager())
				&& validateHelper.illegalStringLength(2, 20,
						domain.getManager())) {
			validatResult.addErrorMessage(getColumNo("manager")
					+ "负责人只能输入2-20个字符");
		}

		if (null != domain.getLicenseDate()
				&& !isDateStr(domain.getLicenseDate())) {
			validatResult.addErrorMessage(getColumNo("licenseDate")
					+ "领取执照时间输入格式有问题");
		}
		if (validateHelper.illegalStringLength(0, 20, domain.getPlaceLevel())) {
			validatResult.addErrorMessage(getColumNo("placeLevel")
					+ "场所等级最多输入20个字符");
		}
		if (validateHelper.illegalStringLength(0, 20, domain.getCategory())) {
			validatResult.addErrorMessage(getColumNo("category")
					+ "公共场所类别最多输入20个字符");
		}
		if (domain.getTotalArea() != null) {
			if (domain.getTotalArea().doubleValue() < 1
					|| domain.getTotalArea().doubleValue() > 9999999.99) {
				validatResult.addErrorMessage(getColumNo("totalArea")
						+ "总面积（平方米）只能是1-9999999.99之间的正数");
			}
		}
		if (domain.getOperationArea() != null) {
			if (domain.getOperationArea().doubleValue() < 1
					|| domain.getOperationArea().doubleValue() > 9999999.99) {
				validatResult.addErrorMessage(getColumNo("operationArea")
						+ "营业面积（平方米）只能是1-9999999.99之间的正数");
			}
		}
		if (validateHelper.illegalStringLength(0, 20,
				domain.getBuildingStructure())) {
			validatResult.addErrorMessage(getColumNo("buildingStructure")
					+ "建筑物结构最多输入20个字符");
		}
		if (validateHelper.illegalStringLength(0, 150, domain.getSurrounding())) {
			validatResult.addErrorMessage(getColumNo("surrounding")
					+ "周围环境最多输入150个字符");
		}
		if (validateHelper.illegalStringLength(0, 20, domain.getPassageway())) {
			validatResult.addErrorMessage(getColumNo("passageway")
					+ "通道口最多输入20个字符");
		}
		if (validateHelper.illegalStringLength(0, 20,
				domain.getInnerStructure())) {
			validatResult.addErrorMessage(getColumNo("innerStructure")
					+ "内部结构最多输入20个字符");
		}
		if (validateHelper.illegalStringLength(0, 200, domain.getRemark())) {
			validatResult
					.addErrorMessage(getColumNo("remark") + "备注最多输入200个字符");
		}
		if (!validateHelper.nullObj(domain.getLicenseDate())
				&& domain.getLicenseDate().after(new Date())) {
			validatResult.addErrorMessage(getColumNo("licenseDate")
					+ "领取执照时间不能晚于今天");
		}
		return validatResult;
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

	private boolean isDateStr(Date date) {
		if (date == null) {
			return false;
		}
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.format(date);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
