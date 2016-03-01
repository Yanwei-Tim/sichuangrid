package com.tianque.datatransfer.dataconvert;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.OtherLocale;
import com.tianque.domain.PropertyDict;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.OtherLocaleService;

@Component("otherLocaleDataConverter")
public class OtherLocaleDataConverter extends AbstractDataConverter<OtherLocale> {

	@Autowired
	private OtherLocaleService otherLocaleService;
	@Autowired
	private ValidateHelper validateHelper;

	// @Autowired
	// private OrganizationDubboService organizationDubboService;

	@Override
	public OtherLocale convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		OtherLocale result = new OtherLocale();
		ExcelImportHelper.procImportDate(SpecialGroupsContext.getOtherLocaleImportArray(),
				cellValues, getUploadOrganization(), result, vr);
		return result;
	}

	@Override
	public OtherLocale persistenceDomain(OtherLocale domain) {
		return otherLocaleService.addOtherLocale(domain);
	}

	@Override
	public ValidateResult validate(OtherLocale otherLocale, int realRow) {
		ValidateResult result = new ValidateResult();
		if (validateHelper.emptyString(otherLocale.getName())) {
			result.addErrorMessage(getColumNo("name") + "其他场所名称不能为空");
		} else if (validateHelper.illegalStringLength(2, 30, otherLocale.getName())) {
			result.addErrorMessage(getColumNo("name") + "其他场所名称只能输入2-30个字符");
		}
		if (!validateHelper.emptyString(otherLocale.getName())
				&& !validateHelper.isConSpeCharacters(otherLocale.getName())) {
			result.addErrorMessage(getColumNo("name") + "其他场所不能输入特殊字符");
		}
		// String orgCode = organizationDubboService.getSimpleOrgById(
		// ThreadVariable.getUser().getOrganization().getId())
		// .getOrgInternalCode();
		// if (validateHelper.emptyString(cellValues[1])) {
		// result.addErrorMessage(realRow, 2, "片组片格必须输入");
		// } else if (!validateHelper.emptyString(cellValues[1])) {
		// if (orgCode.length() == 13
		// && !cellValues[1].equals(organizationDubboService
		// .getSimpleOrgById(
		// ThreadVariable.getUser().getOrganization()
		// .getId()).getOrgName())) {
		// result.addErrorMessage(realRow, 2, "不正确,权限越界，不能将数据导入到该网格下！");
		// }
		//
		// if (validateHelper.illegalOrganizationName(getUploadOrganization(),
		// cellValues[1])) {
		// result.addErrorMessage(realRow, 2,
		// "所属网格输入不正确，请确保有该网格存在并且已经添加到系统中");
		// }
		// }

		if (validateHelper.illegalStringLength(0, 50, otherLocale.getAddress())) {
			result.addErrorMessage(getColumNo("address") + "地址不能输入大于50字符");
		}
		if (!validateType(otherLocale.getType())) {
			result.addErrorMessage(getColumNo("type") + "场所类型不能为空");
		}
		// if (!validateHelper.emptyString(otherLocale.getLocationType())) {
		// if (validateHelper.illegalPropertyDictDisplayName(
		// PropertyTypes.OTHER_LOCALE_TYPE, otherLocale
		// .getLocationType())) {
		// result.addErrorMessage(getColumNo("type") + "场所类型输入不正确");
		// }
		// }
		if (!validateHelper.emptyString(otherLocale.getContactPerson())
				&& validateHelper.illegalStringLength(2, 20, otherLocale.getContactPerson())) {
			result.addErrorMessage(getColumNo("contactPerson") + "联系人只能输入2-30个字符");
		}
		if (!validateHelper.emptyString(otherLocale.getContactPerson())
				&& !validateHelper.isConSpeCharacters(otherLocale.getContactPerson())) {
			result.addErrorMessage(getColumNo("contactPerson") + "联系人不能输入特殊字符");
		}
		if (!validateHelper.emptyString(otherLocale.getTelephone())) {
			if (validateHelper.illegalTelephone(otherLocale.getTelephone())) {
				result.addErrorMessage(getColumNo("telephone") + "联系电话只能输入数字和-");
			}
			if (validateHelper.illegalStringLength(0, 15, otherLocale.getTelephone())) {
				result.addErrorMessage(getColumNo("telephone") + "联系电话不能输入大于15字符");
			}
		}
		
		if (otherLocale.getPractitionerNumber() != null) {
			if (validateHelper.illegalNumberZZ(""
					+ otherLocale.getPractitionerNumber())) {
				result.addErrorMessage(getColumNo("practitionerNumber")+"从业人数只能输入正整数");
			} else if (Double.valueOf(otherLocale.getPractitionerNumber())
					.longValue() > 999999999) {
				result.addErrorMessage(getColumNo("practitionerNumber")+"从业人数不能超过999999999");
			}
		}
		
		if (!validateHelper.emptyString(otherLocale.getMobileNumber())) {
			if (validateHelper.illegalMobilePhone(otherLocale.getMobileNumber())) {
				result.addErrorMessage(getColumNo("mobileNumber") + "联系手机只能输入1开头的11位数字");
			}
		}
		if (validateHelper.illegalStringLength(0, 200, otherLocale.getRemark())) {
			result.addErrorMessage(getColumNo("remark") + "备注不能输入大于200字符");
		}
		return result;
	}

	private boolean validateType(PropertyDict dict) {
		if (dict == null || dict.getId() == null) {
			return false;
		}
		return true;
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

	@Override
	public boolean isRepeatData(OtherLocale domain) {
		return otherLocaleService.countExsistedOtherLocale(domain.getName(), domain
				.getOrganization().getId(), null) > 0;
	}

	@Override
	public OtherLocale updateDomain(OtherLocale domain) {
		return otherLocaleService.updateOtherLocaleByName(domain.getName(), domain
				.getOrganization().getId(), domain);
	}
}
