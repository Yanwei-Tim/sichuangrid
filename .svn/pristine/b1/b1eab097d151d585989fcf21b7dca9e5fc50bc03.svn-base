package com.tianque.baseInfo.aidNeedPopulation.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.aidNeedPopulation.domain.AidNeedPopulation;
import com.tianque.baseInfo.aidNeedPopulation.service.AidNeedPopulationService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("aidNeedPopulationDataConverter")
public class AidNeedPopulationDataConverter extends
		AbstractDataConverter<AidNeedPopulation> {
	@Autowired
	private AidNeedPopulationService aidNeedPopulationService;
	@Autowired
	@Qualifier("aidNeedPopulationValidator")
	private AbstractCountrymenValidator<AidNeedPopulation> aidNeedPopulationValidator;
	@Autowired
	private GlobalSettingService globalSettingService;

	@Override
	public ValidateResult validate(AidNeedPopulation aidNeedPopulation,
			int realRow) {
		return validate(aidNeedPopulationValidator, aidNeedPopulation, realRow);
	}

	@Override
	public boolean isRepeatData(AidNeedPopulation domain) {
		AidNeedPopulation existsObj = aidNeedPopulationService
				.getAidNeedPopulationByIdCardNoAndOrganizationId(
						domain.getIdCardNo(), domain.getOrganization().getId());
		if (null != existsObj) {
			domain.setId(existsObj.getId());
			return true;
		} else
			return false;
	}

	@Override
	public AidNeedPopulation persistenceDomain(AidNeedPopulation domain) {
		if (null != domain.getOrganization()) {
			domain.setOrgInternalCode(domain.getOrganization()
					.getOrgInternalCode());
		}
		if (checkDataExitInCache(NewBaseInfoTables.AIDNEEDPOPULATION_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		initalAidNeedPopulation(domain);
		aidNeedPopulationService.addAidNeedPopulation(domain);
		cleanDataInCache(NewBaseInfoTables.AIDNEEDPOPULATION_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString());
		return domain;
	}

	@Override
	public AidNeedPopulation updateDomain(AidNeedPopulation domain) {
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		initalAidNeedPopulation(domain);
		aidNeedPopulationService.updateAidNeedPopulationByIdCardNoAndOrgId(
				domain.getIdCardNo(), domain.getOrganization().getId(), domain);
		return domain;
	}

	private void initalAidNeedPopulation(AidNeedPopulation domain) {
		if (!domain.getIsLowHouseholds()) {
			domain.setDifficultCardNo(null);
			domain.setDifficultType(null);
			domain.setSubsidiesArea(null);
			domain.setSubsidiesAmount(null);
			domain.setSubsidiesGetTime(null);
			domain.setSubsidiesPopulation(null);
			domain.setSubsidiesStartTime(null);
		}
	}

	@Override
	public AidNeedPopulation convertToDomain(String[] cellValues,
			ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		AidNeedPopulation result = new AidNeedPopulation();
		String[][] excelColumArray = null;
		if (GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getAidNeedPopulationImportArrayNap();
		} else if (GlobalSetting.SYNC_ACTUAL_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getAidNeedPopulationImportArraySap();
		} else {
			excelColumArray = SpecialGroupsContext
					.getAidNeedPopulationImportArrayNdt();
		}
		ExcelImportHelper.procImportDate(excelColumArray, cellValues,
				getUploadOrganization(), result, vr);
		if (null != result.getOrganization()) {
			result.setOrgInternalCode(result.getOrganization()
					.getOrgInternalCode());
		}
		return result;
	}

}
