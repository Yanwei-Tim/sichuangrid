package com.tianque.datatransfer.dataconvert;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.NewEconomicOrganizations;
import com.tianque.domain.Organization;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.NewEconomicOrganizationsService;

@Component("newEconomicOrganizations")
public class NewEconomicOrganizationsDataConverter extends
		AbstractDataConverter<NewEconomicOrganizations> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private NewEconomicOrganizationsService newEconomicOrganizationsService;
	@Qualifier("newEconomicOrganizationsValidator")
	@Autowired
	private DomainValidator newEconomicOrganizationsValidator;

	@Override
	public ValidateResult validate(NewEconomicOrganizations newEconomicOrganizations, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return newEconomicOrganizationsValidator.validate(newEconomicOrganizations);
	}

	@Override
	public NewEconomicOrganizations convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		NewEconomicOrganizations result = new NewEconomicOrganizations();
		String[][] excelColumArray = SpecialGroupsContext.getNewEconomicOrganizationsImportArray();
		ExcelImportHelper.procImportDate(excelColumArray, cellValues, getUploadOrganization(),
				result, vr);
		if (null != result.getOrganization()) {
			result.setOrgInternalCode(result.getOrganization().getOrgInternalCode());
		}
		return result;
	}

	@Override
	public NewEconomicOrganizations convertToDomain(String[] cellValues, ValidateResult vr,
			String[][] beanDatas, Organization headerOrg) {
		setUploadOrganization(headerOrg);
		cellValues = validateHelper.cellValueTrim(cellValues);
		NewEconomicOrganizations result = new NewEconomicOrganizations();
		ExcelImportHelper.newProcImportDate(beanDatas, cellValues, getUploadOrganization(), result,
				vr);
		if (null != result.getOrganization()) {
			result.setOrgInternalCode(result.getOrganization().getOrgInternalCode());
		}

		return result;
	}

	@Override
	public NewEconomicOrganizations persistenceDomain(NewEconomicOrganizations domain) {
		return newEconomicOrganizationsService.addNewEconomicOrganizations(domain);
	}

	@Override
	public boolean isRepeatData(NewEconomicOrganizations domain) {
		return newEconomicOrganizationsService
				.getNewEconomicOrganizationsByNameAndLicenseNumberAndOrgId(domain.getName(),
						domain.getLicenseNumber(), domain.getOrganization().getId()).size() > 0;
	}

	@Override
	public NewEconomicOrganizations updateDomain(NewEconomicOrganizations domain) {
		return newEconomicOrganizationsService
				.updateNewEconomicOrganizationsByNameAndLicenseNumber(domain.getName(),
						domain.getLicenseNumber(), domain.getOrganization().getId(), domain);
	}

	public Date convertToDate(String dateText) {
		if (!StringUtil.isStringAvaliable(dateText)) {
			return null;
		}
		Date result = null;
		result = CalendarUtil.parseDate(dateText, "yyyy/MM/dd");
		if (result == null) {
			result = CalendarUtil.parseDate(dateText, "yyyy-MM-dd");
		}
		if (result == null) {
			result = CalendarUtil.parseDate(dateText, "MM/dd/yy");
		}
		return result;
	}

}
