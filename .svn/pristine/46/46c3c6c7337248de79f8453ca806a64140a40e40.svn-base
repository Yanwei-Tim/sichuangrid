package com.tianque.baseInfo.rectificativePerson.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.baseInfo.rectificativePerson.service.RectificativePersonService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("rectificativePersonDataConverter")
@Scope("prototype")
public class RectificativePersonDataConverter extends
		AbstractDataConverter<RectificativePerson> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private RectificativePersonService rectificativePersonService;
	@Autowired
	@Qualifier("rectificativePersonValidator")
	private AbstractCountrymenValidator<RectificativePerson> rectificativeValidator;
	@Autowired
	private GlobalSettingService globalSettingService;

	@Override
	public ValidateResult validate(RectificativePerson rectificativePerson,
			int realRow) {
		return validate(rectificativeValidator, rectificativePerson, realRow);
	}

	@Override
	public RectificativePerson convertToDomain(String[] cellValues,
			ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		RectificativePerson result = new RectificativePerson();
		String[][] excelColumArray = null;
		if (GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getRectificativePersonImportArrayNap();
		} else if (GlobalSetting.SYNC_ACTUAL_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getRectificativePersonImportArraySap();
		} else {
			excelColumArray = SpecialGroupsContext
					.getRectificativePersonImportArrayNdt();
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
	public boolean isRepeatData(RectificativePerson domain) {
		boolean flag = rectificativePersonService
				.hasDuplicateRectificativePerson(domain.getOrganization()
						.getId(), domain.getIdCardNo(), null);
		if (flag) {
			domain.setId(rectificativePersonService
					.findRectificativePersonByIdCardNoAndOrgId(
							domain.getIdCardNo(),
							domain.getOrganization().getId()).getId());
		}
		return flag;
	}

	@Override
	public RectificativePerson persistenceDomain(RectificativePerson domain) {
		if (checkDataExitInCache(NewBaseInfoTables.RECTIFICATIVEPERSON_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		rectificativePersonService.addRectificativePerson(domain);
		cleanDataInCache(NewBaseInfoTables.RECTIFICATIVEPERSON_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString());
		return domain;
	}

	@Override
	public RectificativePerson updateDomain(RectificativePerson domain) {
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		rectificativePersonService.updateRectificativePersonByName(
				domain.getIdCardNo(), domain.getOrganization().getId(), domain);
		return domain;
	}

}
