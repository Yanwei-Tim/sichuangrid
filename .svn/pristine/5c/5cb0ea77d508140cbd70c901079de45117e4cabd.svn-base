package com.tianque.datatransfer.dataconvert;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.DataConvertionHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Enterprise;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.service.EnterpriseService;
import com.tianque.sysadmin.service.PropertyDictService;

@Component("enterpriseDownDataConverter")
public class EnterpriseDownDataConverter extends AbstractDataConverter<Enterprise> {

	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private DataConvertionHelper dataConverterHelper;
	@Autowired
	private ValidateHelper validateHelper;
	@Qualifier("enterPriseValidator")
	@Autowired
	private DomainValidator<Enterprise> enterpriseValidator;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public Enterprise convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		Enterprise enterprise = new Enterprise();
		enterprise.setName(cellValues[0]);
		enterprise.setType(dataConverterHelper.convertToPropertyDict(PropertyTypes.ENTERPRISE_TYPE,
				"规下企业"));
		enterprise.setBusinessLicense(cellValues[1]);
		enterprise.setOrganization(dataConverterHelper.convertToOrganization(
				getUploadOrganization(), cellValues[2]));
		enterprise.setLegalPerson(cellValues[3]);
		enterprise.setIndustry(dataConverterHelper.convertToPropertyDict(PropertyTypes.CAREER,
				cellValues[4]));
		enterprise.setAddress(cellValues[5]);
		if (validateHelper.emptyString(cellValues[6])) {
			enterprise.setArea(null);
		} else {
			enterprise.setArea(dataConverterHelper.convertToBigDecima(cellValues[6]));
		}
		if (validateHelper.emptyString(cellValues[7])) {
			enterprise.setRegisteredCapital(null);
		} else {
			enterprise.setRegisteredCapital(dataConverterHelper.convertToBigDecima(cellValues[7]));
		}
		enterprise.setEmployeeAmount(dataConverterHelper.convertToLong(cellValues[8]));
		enterprise.setPartyMemberAmount(dataConverterHelper.convertToLong(cellValues[9]));
		enterprise.setEnterpriseTelephone(cellValues[10]);
		enterprise.setFax(cellValues[11]);
		enterprise.setTelephone(cellValues[12]);
		enterprise.setMobileNumber(cellValues[13]);
		enterprise.setAttentionExtent(dataConverterHelper.convertToPropertyDict(
				PropertyTypes.ATTENTION_EXTENT, cellValues[14]));
		enterprise.setRiskEnterprise(dataConverterHelper.convertToBoolean(cellValues[15]));
		enterprise.setHiddenCases(cellValues[16]);
		enterprise.setHiddenRectification(cellValues[17]);
		enterprise.setRemark(cellValues[18]);
		enterprise.setKeyType(cellValues[19]);
		return enterprise;
	}

	@Override
	public Enterprise persistenceDomain(Enterprise domain) {
		if (domain.getType() == null) {
			if (domain.getKeyType() != null && domain.getKeyType().equals("enterpriseDownKey")) {
				List<PropertyDict> propertyDictList = propertyDictService
						.findPropertyDictByDomainName(PropertyTypes.ENTERPRISE_TYPE);
				for (int i = 0; i < propertyDictList.size(); i++) {
					PropertyDict domain1 = propertyDictList.get(i);
					if (domain1.getDisplayName().equals("规下企业")) {
						domain.setType(domain1);
					}
				}
			}
		}
		return enterpriseService.addEnterprise(domain);
	}

	// fateson add
	@Override
	public Enterprise convertToDomain(String[] cellValues, ValidateResult result,
			String[][] beanDatas, Organization headerOrg) {
		setUploadOrganization(headerOrg);
		cellValues = validateHelper.cellValueTrim(cellValues);
		Enterprise enterprise = new Enterprise();
		ExcelImportHelper.newProcImportDate(beanDatas, cellValues, getUploadOrganization(),
				enterprise, result);
		enterprise.setKeyType("enterpriseDownKey");
		return enterprise;
	}

	@Override
	public ValidateResult validate(Enterprise enterprise, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return enterpriseValidator.validate(enterprise);

	}

	@Override
	public boolean isRepeatData(Enterprise domain) {
		return enterpriseService.getEnterpriseByNameAndOrgIdAndKeyType(domain.getName(), domain
				.getOrganization().getId(), domain.getKeyType()) != null;
	}

	@Override
	public Enterprise updateDomain(Enterprise domain) {
		return enterpriseService.updateEnterpriseByName(domain.getName(), domain.getOrganization()
				.getId(), domain);
	}
}
