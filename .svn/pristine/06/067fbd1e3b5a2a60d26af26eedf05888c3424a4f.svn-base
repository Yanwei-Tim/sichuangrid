package com.tianque.baseInfo.fPersonnel.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.fPersonnel.domain.FPersonnel;
import com.tianque.baseInfo.fPersonnel.service.FPersonnelService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("fPersonelDataConverter")
@Scope("prototype")
public class FPersonelDataConverter extends AbstractDataConverter<FPersonnel> {
	@Autowired
	private FPersonnelService fPersonnelService;
	@Autowired
	private ValidateHelper validateHelper;

	@Autowired
	@Qualifier("fPersonnelValidator")
	private AbstractCountrymenValidator<FPersonnel> fPersonnelValidator;

	@Override
	public ValidateResult validate(FPersonnel rectificativePerson, int realRow) {
		return validate(fPersonnelValidator, rectificativePerson, realRow);
	}

	@Override
	public FPersonnel convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		FPersonnel result = new FPersonnel();
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
	public FPersonnel persistenceDomain(FPersonnel domain) {
		if (checkDataExitInCache(NewBaseInfoTables.FPERSONNEL_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		if (fPersonnelService.existFPersonnel(domain.getOrganization().getId(),
				domain.getIdCardNo(), null)) {
			domain.setId(fPersonnelService.getFPersonnelByIdCardNo(
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
		fPersonnelService.addFPersonnel(domain);
		cleanDataInCache(NewBaseInfoTables.FPERSONNEL_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString());
		return domain;
	}

	@Override
	public boolean isRepeatData(FPersonnel domain) {
		boolean flag = fPersonnelService.existFPersonnel(domain
				.getOrganization().getId(), domain.getIdCardNo(), null);
		if (flag) {
			domain.setId(fPersonnelService.getFPersonnelByIdCardNo(
					domain.getIdCardNo(), domain.getOrganization().getId())
					.getId());
		}
		return flag;
	}

	@Override
	public FPersonnel updateDomain(FPersonnel domain) {
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		fPersonnelService.updateFPersonnelByName(domain.getIdCardNo(), domain
				.getOrganization().getId(), domain);
		return domain;
	}

}
