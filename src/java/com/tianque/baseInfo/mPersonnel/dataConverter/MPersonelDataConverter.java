package com.tianque.baseInfo.mPersonnel.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.mPersonnel.domain.MPersonnel;
import com.tianque.baseInfo.mPersonnel.service.MPersonnelService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("mPersonelDataConverter")
@Scope("prototype")
public class MPersonelDataConverter extends AbstractDataConverter<MPersonnel> {
	@Autowired
	private MPersonnelService mPersonnelService;
	@Autowired
	private ValidateHelper validateHelper;

	@Autowired
	@Qualifier("mPersonnelValidator")
	private AbstractCountrymenValidator<MPersonnel> mPersonnelValidator;

	@Override
	public ValidateResult validate(MPersonnel rectificativePerson, int realRow) {
		return validate(mPersonnelValidator, rectificativePerson, realRow);
	}

	@Override
	public MPersonnel convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		MPersonnel result = new MPersonnel();
		String[][] excelColumArray = null;
		if (GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext.getMPersonnelSlf();
		} else if (GlobalSetting.SYNC_ACTUAL_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getRectificativePersonImportArraySap();
		} else {
			excelColumArray = SpecialGroupsContext.getMPersonnelArrayRla();
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
	public MPersonnel persistenceDomain(MPersonnel domain) {
		if (checkDataExitInCache(NewBaseInfoTables.MPERSONNEL_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		if (mPersonnelService.existMPersonnel(domain.getOrganization().getId(),
				domain.getIdCardNo(), null)) {
			domain.setId(mPersonnelService.getMPersonnelByIdCardNo(
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
		mPersonnelService.addMPersonnel(domain);
		cleanDataInCache(NewBaseInfoTables.MPERSONNEL_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString());
		return domain;
	}

	@Override
	public boolean isRepeatData(MPersonnel domain) {
		boolean flag = mPersonnelService.existMPersonnel(domain
				.getOrganization().getId(), domain.getIdCardNo(), null);
		if (flag) {
			domain.setId(mPersonnelService.getMPersonnelByIdCardNo(
					domain.getIdCardNo(), domain.getOrganization().getId())
					.getId());
		}
		return flag;
	}

	@Override
	public MPersonnel updateDomain(MPersonnel domain) {
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		mPersonnelService.updateMPersonnelByName(domain.getIdCardNo(), domain
				.getOrganization().getId(), domain);
		return domain;
	}

}
