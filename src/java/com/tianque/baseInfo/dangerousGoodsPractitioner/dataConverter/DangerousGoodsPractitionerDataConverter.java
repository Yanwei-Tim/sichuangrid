package com.tianque.baseInfo.dangerousGoodsPractitioner.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.dangerousGoodsPractitioner.domain.DangerousGoodsPractitioner;
import com.tianque.baseInfo.dangerousGoodsPractitioner.service.DangerousGoodsPractitionerService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("dangerousGoodsPractitionerDataConverter")
@Scope("prototype")
public class DangerousGoodsPractitionerDataConverter extends
		AbstractDataConverter<DangerousGoodsPractitioner> {
	@Autowired
	private DangerousGoodsPractitionerService dangerousGoodsPractitionerService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	@Qualifier("dangerousGoodsPractitionerValidator")
	private AbstractCountrymenValidator<DangerousGoodsPractitioner> dangerousGoodsPractitionerValidator;
	@Autowired
	private GlobalSettingService globalSettingService;

	@Override
	public ValidateResult validate(
			DangerousGoodsPractitioner dangerousGoodsPractitioner, int realRow) {
		return validate(dangerousGoodsPractitionerValidator,
				dangerousGoodsPractitioner, realRow);
	}

	@Override
	public DangerousGoodsPractitioner convertToDomain(String[] cellValues,
			ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		DangerousGoodsPractitioner result = new DangerousGoodsPractitioner();
		String[][] excelColumArray = null;
		if (GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getDangerousGoodsPractitionerImportArrayNap();
		} else if (GlobalSetting.SYNC_ACTUAL_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getDangerousGoodsPractitionerImportArraySap();
		} else {
			excelColumArray = SpecialGroupsContext
					.getDangerousGoodsPractitionerImportArrayNdt();
		}
		ExcelImportHelper.procImportDate(excelColumArray, cellValues,
				getUploadOrganization(), result, vr);
		if (dangerousGoodsPractitionerService.existDangerousGoodsPractitioner(
				result.getOrganization().getId(), result.getIdCardNo(), null)) {
			result.setId(dangerousGoodsPractitionerService
					.getDangerousGoodsPractitionerByIdCardNo(
							result.getIdCardNo(),
							result.getOrganization().getId()).getId());
		}
		if (null != result.getOrganization()) {
			result.setOrgInternalCode(result.getOrganization()
					.getOrgInternalCode());
		}
		return result;
	}

	@Override
	public boolean isRepeatData(DangerousGoodsPractitioner domain) {
		boolean flag = dangerousGoodsPractitionerService
				.existDangerousGoodsPractitioner(domain.getOrganization()
						.getId(), domain.getIdCardNo(), null);
		if (flag) {
			domain.setId(dangerousGoodsPractitionerService
					.getDangerousGoodsPractitionerByIdCardNo(
							domain.getIdCardNo(),
							domain.getOrganization().getId()).getId());
		}
		return flag;
	}

	@Override
	public DangerousGoodsPractitioner persistenceDomain(
			DangerousGoodsPractitioner domain) {
		if (checkDataExitInCache(
				NewBaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		if (dangerousGoodsPractitionerService.existDangerousGoodsPractitioner(
				domain.getOrganization().getId(), domain.getIdCardNo(), null)) {
			domain.setId(dangerousGoodsPractitionerService
					.getDangerousGoodsPractitionerByIdCardNo(
							domain.getIdCardNo(),
							domain.getOrganization().getId()).getId());
		}
		if (null != domain.getOrganization()) {
			domain.setOrgInternalCode(domain.getOrganization()
					.getOrgInternalCode());
		}

		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		dangerousGoodsPractitionerService.addDangerousGoodsPractitioner(domain);
		cleanDataInCache(NewBaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString());
		return domain;
	}

	@Override
	public DangerousGoodsPractitioner updateDomain(
			DangerousGoodsPractitioner domain) {
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		dangerousGoodsPractitionerService
				.updateDangerousGoodsPractitionerByName(domain.getIdCardNo(),
						domain.getOrganization().getId(), domain);
		return domain;
	}

}
