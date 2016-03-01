package com.tianque.baseInfo.mentalPatient.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.mentalPatient.domain.MentalPatient;
import com.tianque.baseInfo.mentalPatient.service.MentalPatientService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("mentalPatientDataConverter")
@Scope("prototype")
public class MentalPatientDataConverter extends
		AbstractDataConverter<MentalPatient> {
	@Autowired
	private MentalPatientService mentalPatientService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	@Qualifier("mentalPatientValidator")
	private AbstractCountrymenValidator<MentalPatient> mentalPatientValidator;
	@Autowired
	private GlobalSettingService globalSettingService;

	@Override
	public ValidateResult validate(MentalPatient mentalPatient, int realRow) {
		return validate(mentalPatientValidator, mentalPatient, realRow);
	}

	@Override
	public MentalPatient convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		MentalPatient result = new MentalPatient();
		String[][] excelColumArray = null;
		if (GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getMentalPatientImportArrayNap();
		} else if (GlobalSetting.SYNC_ACTUAL_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getMentalPatientImportArraySap();
		} else {
			excelColumArray = SpecialGroupsContext
					.getMentalPatientImportArrayNdt();
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
	public boolean isRepeatData(MentalPatient domain) {
		boolean flag = mentalPatientService.hasDuplicateMentalPatient(domain
				.getOrganization().getId(), domain.getIdCardNo(), null);
		if (flag) {
			domain.setId(mentalPatientService
					.findMentalPatientByIdCardNoAndOrgId(domain.getIdCardNo(),
							domain.getOrganization().getId()).getId());
		}
		return flag;
	}

	@Override
	public MentalPatient persistenceDomain(MentalPatient domain) {
		if (checkDataExitInCache(NewBaseInfoTables.MENTALPATIENT_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		mentalPatientService.addMentalPatient(domain);
		cleanDataInCache(NewBaseInfoTables.MENTALPATIENT_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString());
		return domain;
	}

	@Override
	public MentalPatient updateDomain(MentalPatient domain) {
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		mentalPatientService.updateMentalPatientByName(domain.getIdCardNo(),
				domain.getOrganization().getId(), domain);
		return domain;
	}

}
