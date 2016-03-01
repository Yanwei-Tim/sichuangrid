package com.tianque.baseInfo.elderlyPeople.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.baseInfo.elderlyPeople.service.ElderlyPeopleService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("elderlyPeopleDataConverter")
@Scope("prototype")
public class ElderlyPeopleDataConverter extends
		AbstractDataConverter<ElderlyPeople> {
	@Autowired
	private ElderlyPeopleService elderlyPeopleService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	@Qualifier("elderlyPeopleValidator")
	private AbstractCountrymenValidator<ElderlyPeople> elderlyPeopleValidator;
	@Autowired
	private GlobalSettingService globalSettingService;

	@Override
	public ValidateResult validate(ElderlyPeople elderlyPeople, int realRow) {
		return validate(elderlyPeopleValidator, elderlyPeople, realRow);
	}

	@Override
	public ElderlyPeople convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		ElderlyPeople result = new ElderlyPeople();
		String[][] excelColumArray = null;
		if (GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getElderlyPeopleImportArrayNap();
		} else if (GlobalSetting.SYNC_ACTUAL_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getElderlyPeopleImportArraySap();
		} else {
			excelColumArray = SpecialGroupsContext
					.getElderlyPeopleImportArrayNdt();
		}
		ExcelImportHelper.procImportDate(excelColumArray, cellValues,
				getUploadOrganization(), result, vr);
		if (null != result.getOrganization()) {
			result.setOrgInternalCode(result.getOrganization()
					.getOrgInternalCode());
		}
		return result;
	}

	@Override
	public boolean isRepeatData(ElderlyPeople domain) {
		ElderlyPeople exists = elderlyPeopleService
				.getElderlyPeopleByIdCardNoAndOrganizationId(
						domain.getIdCardNo(), domain.getOrganization().getId());
		if (null != exists) {
			domain.setId(exists.getId());
			return true;
		} else
			return false;
	}

	@Override
	public ElderlyPeople persistenceDomain(ElderlyPeople domain) {
		if (checkDataExitInCache(NewBaseInfoTables.ELDERLYPEOPLE_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		elderlyPeopleService.addElderlyPeople(domain);
		cleanDataInCache(NewBaseInfoTables.ELDERLYPEOPLE_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString());
		return domain;
	}

	@Override
	public ElderlyPeople updateDomain(ElderlyPeople domain) {
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		elderlyPeopleService.updateElderlyPeopleByIdCardNo(
				domain.getIdCardNo(), domain.getOrganization().getId(), domain);
		return domain;
	}

}
