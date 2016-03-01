package com.tianque.baseInfo.newSocietyOrganizations.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.newSocietyOrganizations.domain.NewSocietyOrganizations;
import com.tianque.baseInfo.newSocietyOrganizations.service.NewSocietyOrganitionsService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.domain.Organization;
import com.tianque.excel.definition.SpecialGroupsContext;

@Component("newSocietyOrganizationsDataConverter")
@Scope("prototype")
public class NewSocietyOrganizationsDataConverter extends
		AbstractDataConverter<NewSocietyOrganizations> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private NewSocietyOrganitionsService newSocietyOrganitionsService;
	@Qualifier("newSocietyOrganizationsValidator")
	@Autowired
	private DomainValidator<NewSocietyOrganizations> newSocietyOrganizationsValidator;

	@Override
	public ValidateResult validate(NewSocietyOrganizations newSocietyOrganizations, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return newSocietyOrganizationsValidator.validate(newSocietyOrganizations);
	}

	@Override
	public NewSocietyOrganizations convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		NewSocietyOrganizations result = new NewSocietyOrganizations();
		String[][] excelColumArray = SpecialGroupsContext.getNewSocietyOrganizationsImportArray();
		ExcelImportHelper.procImportDate(excelColumArray, cellValues, getUploadOrganization(),
				result, vr);
		if (null != result.getOrganization()) {
			result.setOrgInternalCode(result.getOrganization().getOrgInternalCode());
		}

		return result;
	}

	@Override
	public NewSocietyOrganizations convertToDomain(String[] cellValues, ValidateResult vr,
			String[][] beanDatas, Organization headerOrg) {
		setUploadOrganization(headerOrg);
		cellValues = validateHelper.cellValueTrim(cellValues);
		NewSocietyOrganizations result = new NewSocietyOrganizations();
		ExcelImportHelper.newProcImportDate(beanDatas, cellValues, getUploadOrganization(), result,
				vr);
		if (null != result.getOrganization()) {
			result.setOrgInternalCode(result.getOrganization().getOrgInternalCode());
		}

		return result;
	}

	@Override
	public NewSocietyOrganizations persistenceDomain(NewSocietyOrganizations domain) {
		return newSocietyOrganitionsService.addNewSocietyOrganizations(domain);
	}

	@Override
	public NewSocietyOrganizations updateDomain(NewSocietyOrganizations domain) {
		return newSocietyOrganitionsService.updateNewSocietyOrganizationsByName(domain.getName(),
				domain.getOrganization().getId(), domain);
	}

	@Override
	public boolean isRepeatData(NewSocietyOrganizations domain) {
		return newSocietyOrganitionsService.hasDuplicateNewSocietyOrganizationsName(domain
				.getOrganization().getId(), domain.getName(), domain.getId());
	}
}
