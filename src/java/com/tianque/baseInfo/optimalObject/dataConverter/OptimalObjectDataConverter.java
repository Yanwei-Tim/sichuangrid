package com.tianque.baseInfo.optimalObject.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.optimalObject.domain.OptimalObject;
import com.tianque.baseInfo.optimalObject.service.OptimalObjectService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("optimalObjectDataConverter")
@Scope("prototype")
public class OptimalObjectDataConverter extends
		AbstractDataConverter<OptimalObject> {
	@Autowired
	private OptimalObjectService optimalObjectService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	@Qualifier("optimalObjectValidator")
	private AbstractCountrymenValidator<OptimalObject> optimalObjectValidator;
	@Autowired
	private GlobalSettingService globalSettingService;

	@Override
	public OptimalObject convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		OptimalObject result = new OptimalObject();
		String[][] excelColumArray = null;
		if (GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getOptimalObjectImportArrayNap();
		} else if (GlobalSetting.SYNC_ACTUAL_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getOptimalObjectImportArraySap();
		} else {
			excelColumArray = SpecialGroupsContext
					.getOptimalObjectImportArrayNdt();
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
	public ValidateResult validate(OptimalObject optimalObject, int realRow) {
		return validate(optimalObjectValidator, optimalObject, realRow);
	}

	@Override
	public boolean isRepeatData(OptimalObject domain) {
		boolean flag = optimalObjectService.hasDuplicateOptimalObject(domain
				.getOrganization().getId(), domain.getIdCardNo(), null);
		if (flag) {
			domain.setId(optimalObjectService.getOptimalObjectIdCardNo(
					domain.getIdCardNo(), domain.getOrganization().getId())
					.getId());
			;
		}
		return flag;
	}

	@Override
	public OptimalObject persistenceDomain(OptimalObject domain) {
		if (checkDataExitInCache(NewBaseInfoTables.OPTIMALOBJECT_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		optimalObjectService.addOptimalObject(domain);
		cleanDataInCache(NewBaseInfoTables.OPTIMALOBJECT_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString());
		return domain;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.datatransfer.DataConvert#updateDomain(java.lang.Object)
	 */
	@Override
	public OptimalObject updateDomain(OptimalObject domain) {
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		domain = optimalObjectService.updateOptimalObjectByIdCardNo(
				domain.getIdCardNo(), domain.getOrganization().getId(), domain);
		return domain;
	}
}
