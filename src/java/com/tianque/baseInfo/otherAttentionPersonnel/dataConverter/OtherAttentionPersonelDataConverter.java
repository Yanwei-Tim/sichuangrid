package com.tianque.baseInfo.otherAttentionPersonnel.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.otherAttentionPersonnel.domain.OtherAttentionPersonnel;
import com.tianque.baseInfo.otherAttentionPersonnel.service.OtherAttentionPersonnelService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("otherAttentionPersonelDataConverter")
@Scope("prototype")
public class OtherAttentionPersonelDataConverter extends
		AbstractDataConverter<OtherAttentionPersonnel> {
	@Autowired
	private OtherAttentionPersonnelService otherAttentionPersonnelService;
	@Autowired
	private ValidateHelper validateHelper;

	@Autowired
	@Qualifier("otherAttentionPersonnelValidator")
	private AbstractCountrymenValidator<OtherAttentionPersonnel> otherAttentionPersonnelValidator;

	@Override
	public ValidateResult validate(OtherAttentionPersonnel rectificativePerson,
			int realRow) {
		return validate(otherAttentionPersonnelValidator, rectificativePerson,
				realRow);
	}

	@Override
	public OtherAttentionPersonnel convertToDomain(String[] cellValues,
			ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		OtherAttentionPersonnel result = new OtherAttentionPersonnel();
		String[][] excelColumArray = null;
		if (GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getOtherAttentionPersonnelSlf();
		} else if (GlobalSetting.SYNC_ACTUAL_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getRectificativePersonImportArraySap();
		} else {
			excelColumArray = SpecialGroupsContext
					.getOtherAttentionPersonnelArrayRla();
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
	public OtherAttentionPersonnel persistenceDomain(
			OtherAttentionPersonnel domain) {
		if (checkDataExitInCache(NewBaseInfoTables.OTHERATTENTIONPERSONNEL_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		if (otherAttentionPersonnelService.existOtherAttentionPersonnel(domain
				.getOrganization().getId(), domain.getIdCardNo(), null)) {
			domain.setId(otherAttentionPersonnelService
					.getOtherAttentionPersonnelByIdCardNo(domain.getIdCardNo(),
							domain.getOrganization().getId()).getId());
		}
		if (null != domain.getOrganization()) {
			domain.setOrgInternalCode(domain.getOrganization()
					.getOrgInternalCode());
		}
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		otherAttentionPersonnelService.addOtherAttentionPersonnel(domain);
		cleanDataInCache(NewBaseInfoTables.OTHERATTENTIONPERSONNEL_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString());
		return domain;
	}

	@Override
	public boolean isRepeatData(OtherAttentionPersonnel domain) {
		boolean flag = otherAttentionPersonnelService
				.existOtherAttentionPersonnel(domain.getOrganization().getId(),
						domain.getIdCardNo(), null);
		if (flag) {
			domain.setId(otherAttentionPersonnelService
					.getOtherAttentionPersonnelByIdCardNo(domain.getIdCardNo(),
							domain.getOrganization().getId()).getId());
		}
		return flag;
	}

	@Override
	public OtherAttentionPersonnel updateDomain(OtherAttentionPersonnel domain) {
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		otherAttentionPersonnelService.updateOtherAttentionPersonnelByName(
				domain.getIdCardNo(), domain.getOrganization().getId(), domain);
		return domain;
	}

}
