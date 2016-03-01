package com.tianque.baseInfo.superiorVisit.dataConverter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisit;
import com.tianque.baseInfo.superiorVisit.service.SuperiorVisitService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.domain.PropertyDict;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("superiorVisitPersonelDataConverter")
public class SuperiorVisitPersonelDataConverter extends
		AbstractDataConverter<SuperiorVisit> {
	@Autowired
	private SuperiorVisitService superiorVisitService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	@Qualifier("superiorVisitValidator")
	private AbstractCountrymenValidator<SuperiorVisit> superiorVisitValidator;
	@Autowired
	private GlobalSettingService globalSettingService;

	@Override
	public ValidateResult validate(SuperiorVisit superiorVisit, int realRow) {
		return validate(superiorVisitValidator, superiorVisit, realRow);
	}

	@Override
	public SuperiorVisit convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		SuperiorVisit result = new SuperiorVisit();
		String[][] excelColumArray = null;
		if (GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getSuperiorVisitImportArrayNap();
		} else if (GlobalSetting.SYNC_ACTUAL_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getSuperiorVisitImportArraySap();
		} else {
			excelColumArray = SpecialGroupsContext
					.getSuperiorVisitImportArrayNdt();
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
	public boolean isRepeatData(SuperiorVisit domain) {
		boolean flag = superiorVisitService.hasDuplicateSuperiorVisit(domain
				.getOrganization().getId(), domain.getIdCardNo(), null);
		if (flag) {
			domain.setId(superiorVisitService
					.getSuperiorVistByIdCardNoAndOrgId(domain.getIdCardNo(),
							domain.getOrganization().getId()).getId());
		}
		return flag;
	}

	@Override
	public SuperiorVisit persistenceDomain(SuperiorVisit domain) {
		if (checkDataExitInCache(NewBaseInfoTables.SUPERIORVISIT_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		superiorVisitService.addSuperiorVisit(domain,
				parkageFromPropertyDictList(domain.getVisitTypes()));
		cleanDataInCache(NewBaseInfoTables.SUPERIORVISIT_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString());
		return domain;
	}

	@Override
	public SuperiorVisit updateDomain(SuperiorVisit domain) {
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		superiorVisitService.updateSuperiorVisit(domain,
				parkageFromPropertyDictList(domain.getVisitTypes()));
		return domain;
	}

	private List<Long> parkageFromPropertyDictList(List<PropertyDict> dicts) {
		List<Long> list = null;
		if (null != dicts && dicts.size() > 0) {
			list = new ArrayList<Long>();
			for (PropertyDict dict : dicts) {
				list.add(dict.getId());
			}
		}
		return list;
	}

}
