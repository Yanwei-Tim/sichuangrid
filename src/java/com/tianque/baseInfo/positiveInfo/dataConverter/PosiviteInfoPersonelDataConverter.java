package com.tianque.baseInfo.positiveInfo.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.baseInfo.positiveInfo.service.PositiveInfoService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("posiviteInfoPersonelDataConverter")
public class PosiviteInfoPersonelDataConverter extends
		AbstractDataConverter<PositiveInfo> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private PositiveInfoService positiveInfoService;
	@Autowired
	@Qualifier("positiveInfoValidator")
	private AbstractCountrymenValidator<PositiveInfo> positiveInfoValidator;
	@Autowired
	private GlobalSettingService globalSettingService;

	@Override
	public ValidateResult validate(PositiveInfo positiveInfo, int realRow) {
		return validate(positiveInfoValidator, positiveInfo, realRow);
	}

	@Override
	public PositiveInfo convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		PositiveInfo result = new PositiveInfo();
		String[][] excelColumArray = null;
		if (GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getPositiveInfoImportArrayNap();
		} else if (GlobalSetting.SYNC_ACTUAL_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getPositiveInfoImportArraySap();
		} else {
			excelColumArray = SpecialGroupsContext
					.getPositiveInfoImportArrayNdt();
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
	public boolean isRepeatData(PositiveInfo domain) {
		boolean flag = positiveInfoService.hasDuplicatePosiviteInfos(domain
				.getOrganization().getId(), domain.getIdCardNo(), null);
		if (flag) {
			domain.setId(positiveInfoService
					.findPositiveInfoByIdCardNoAndOrgId(domain.getIdCardNo(),
							domain.getOrganization().getId()).getId());
		}
		return flag;
	}

	@Override
	public PositiveInfo persistenceDomain(PositiveInfo domain) {
		if (null != domain.getOrganization()) {
			domain.setOrgInternalCode(domain.getOrganization()
					.getOrgInternalCode());
		}
		if (checkDataExitInCache(NewBaseInfoTables.POSITIVEINFO_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		positiveInfoService.addPositiveInfo(domain);
		cleanDataInCache(NewBaseInfoTables.POSITIVEINFO_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString());
		return domain;
	}

	@Override
	public PositiveInfo updateDomain(PositiveInfo domain) {
		if (null != domain.getOrganization()) {
			domain.setOrgInternalCode(domain.getOrganization()
					.getOrgInternalCode());
		}
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		domain = positiveInfoService.updatePositiveInfoByName(
				domain.getIdCardNo(), domain.getOrganization().getId(), domain);
		return domain;
	}
}
