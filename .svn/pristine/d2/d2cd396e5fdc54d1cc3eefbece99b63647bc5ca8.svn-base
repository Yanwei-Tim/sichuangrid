package com.tianque.datatransfer.dataconvert;

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
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.EnterpriseService;

@Component("securityDataConverter")
public class SecurityDataConverter extends AbstractDataConverter<Enterprise> {

	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private DataConvertionHelper dataConverterHelper;
	@Autowired
	private ValidateHelper validateHelper;
	@Qualifier("enterPriseValidator")
	@Autowired
	private DomainValidator enterPriseValidator;

	@Override
	public Enterprise convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		Enterprise enterprise = new Enterprise();

		ExcelImportHelper.procImportDate(SpecialGroupsContext.getSecurityDataImportArray(),
				cellValues, getUploadOrganization(), enterprise, vr);

		enterprise.setKeyType("securityKey");
		return enterprise;
	}

	@Override
	public Enterprise convertToDomain(String[] cellValues, ValidateResult vr, String[][] beanDatas,
			Organization headerOrg) {
		setUploadOrganization(headerOrg);
		cellValues = validateHelper.cellValueTrim(cellValues);
		Enterprise enterprise = new Enterprise();
		ExcelImportHelper.newProcImportDate(beanDatas, cellValues, getUploadOrganization(),
				enterprise, vr);
		enterprise.setKeyType("securityKey");
		return enterprise;
	}

	@Override
	public Enterprise persistenceDomain(Enterprise domain) {
		domain.setKeyType("securityKey");
		domain.setIsEmphasis(false);// 现在关注
		return enterpriseService.addEnterprise(domain);
	}

	@Override
	public ValidateResult validate(Enterprise enterprise, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return enterPriseValidator.validate(enterprise);
	}

	@Override
	public boolean isRepeatData(Enterprise domain) {
		domain.setKeyType("securityKey");
		return enterpriseService.getEnterpriseByNameAndOrgIdAndKeyType(domain.getName(), domain
				.getOrganization().getId(), domain.getKeyType()) != null;
	}

	@Override
	public Enterprise updateDomain(Enterprise domain) {
		domain.setKeyType("securityKey");
		return enterpriseService.updateEnterpriseByName(domain.getName(), domain.getOrganization()
				.getId(), domain);
	}
}
