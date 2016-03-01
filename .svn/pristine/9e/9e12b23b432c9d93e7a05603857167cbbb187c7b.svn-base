package com.tianque.baseInfo.handicapped.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.handicapped.domain.Handicapped;
import com.tianque.baseInfo.handicapped.service.HandicappedService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("handicappedDataConverter")
public class HandicappedDataConverter extends
		AbstractDataConverter<Handicapped> {

	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private HandicappedService handicappedService;
	@Autowired
	@Qualifier("handicappedValidator")
	private AbstractCountrymenValidator<Handicapped> handicappedValidator;

	@Override
	public ValidateResult validate(Handicapped handicapped, int realRow) {
		return validate(handicappedValidator, handicapped, realRow);

	}

	@Override
	public boolean isRepeatData(Handicapped domain) {
		return handicappedService.getHandicappedByIdCardNoAndOrganizationId(
				domain.getIdCardNo(), domain.getOrganization().getId()) != null;
	}

	@Override
	public Handicapped persistenceDomain(Handicapped domain) {
		if (checkDataExitInCache(NewBaseInfoTables.HANDICAPPED_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		handicappedService.addHandicapped(domain);
		cleanDataInCache(NewBaseInfoTables.HANDICAPPED_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString());
		return domain;
	}

	@Override
	public Handicapped updateDomain(Handicapped domain) {
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		handicappedService.updateHandicappedByName(domain.getIdCardNo(), domain
				.getOrganization().getId(), domain);
		return domain;
	}

	@Override
	public Handicapped convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		Handicapped result = new Handicapped();
		String[][] excelColumArray = null;
		if (GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getHandicappedImportArrayNap();
		} else if (GlobalSetting.SYNC_ACTUAL_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getHandicappedImportArraySap();
		} else {
			excelColumArray = SpecialGroupsContext
					.getHandicappedImportArrayNdt();
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
