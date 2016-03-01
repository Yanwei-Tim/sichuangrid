package com.tianque.baseInfo.goodSamaritan.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.goodSamaritan.domain.GoodSamaritan;
import com.tianque.baseInfo.goodSamaritan.service.GoodSamaritanService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("goodSamaritanDataConverter")
@Scope("prototype")
public class GoodSamaritanDataConverter extends
		AbstractDataConverter<GoodSamaritan> {
	@Autowired
	private GoodSamaritanService goodSamaritanService;
	@Autowired
	private ValidateHelper validateHelper;

	@Autowired
	@Qualifier("goodSamaritanValidator")
	private AbstractCountrymenValidator<GoodSamaritan> goodSamaritanValidator;

	@Override
	public ValidateResult validate(GoodSamaritan rectificativePerson,
			int realRow) {
		return validate(goodSamaritanValidator, rectificativePerson, realRow);
	}

	@Override
	public GoodSamaritan convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		GoodSamaritan result = new GoodSamaritan();
		String[][] excelColumArray = null;
		if (GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext.getFPersonnelSlf();
		} else if (GlobalSetting.SYNC_ACTUAL_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getRectificativePersonImportArraySap();
		} else {
			excelColumArray = SpecialGroupsContext.getFPersonnelArrayRla();
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
	public GoodSamaritan persistenceDomain(GoodSamaritan domain) {
		if (checkDataExitInCache(NewBaseInfoTables.GOOD_SAMARITAN_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		if (goodSamaritanService.existGoodSamaritan(domain.getOrganization()
				.getId(), domain.getIdCardNo(), null)) {
			domain.setId(goodSamaritanService.getGoodSamaritanByIdCardNo(
					domain.getIdCardNo(), domain.getOrganization().getId())
					.getId());
		}
		if (null != domain.getOrganization()) {
			domain.setOrgInternalCode(domain.getOrganization()
					.getOrgInternalCode());
		}
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		goodSamaritanService.addGoodSamaritan(domain);
		cleanDataInCache(NewBaseInfoTables.GOOD_SAMARITAN_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString());
		return domain;
	}

	@Override
	public boolean isRepeatData(GoodSamaritan domain) {
		boolean flag = goodSamaritanService.existGoodSamaritan(domain
				.getOrganization().getId(), domain.getIdCardNo(), null);
		if (flag) {
			domain.setId(goodSamaritanService.getGoodSamaritanByIdCardNo(
					domain.getIdCardNo(), domain.getOrganization().getId())
					.getId());
		}
		return flag;
	}

	@Override
	public GoodSamaritan updateDomain(GoodSamaritan domain) {
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		goodSamaritanService.updateGoodSamaritanByName(domain.getIdCardNo(),
				domain.getOrganization().getId(), domain);
		return domain;
	}
}
