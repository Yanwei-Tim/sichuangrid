package com.tianque.baseInfo.idleYouth.dataConverter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.baseInfo.idleYouth.service.IdleYouthService;
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

@Component("idleYouthDataConverter")
public class IdleYouthDataConverter extends AbstractDataConverter<IdleYouth> {
	@Autowired
	private IdleYouthService idleYouthService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	@Qualifier("idleYouthValidator")
	private AbstractCountrymenValidator<IdleYouth> idleYouthValidator;
	@Autowired
	private GlobalSettingService globalSettingService;

	@Override
	public ValidateResult validate(IdleYouth idleYouth, int realRow) {
		return validate(idleYouthValidator, idleYouth, realRow);
	}

	@Override
	public IdleYouth convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		IdleYouth result = new IdleYouth();
		String[][] excelColumArray = null;
		if (GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext.getIdleYouthImportArrayNap();
		} else if (GlobalSetting.SYNC_ACTUAL_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext.getIdleYouthImportArraySap();
		} else {
			excelColumArray = SpecialGroupsContext.getIdleYouthImportArrayNdt();
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
	public boolean isRepeatData(IdleYouth domain) {
		IdleYouth exists = idleYouthService
				.getIdleYouthByIdCardNoAndOrganizationId(domain.getIdCardNo(),
						domain.getOrganization().getId());
		if (null != exists) {
			domain.setId(exists.getId());
			return true;
		} else
			return false;
	}

	@Override
	public IdleYouth persistenceDomain(IdleYouth domain) {
		if (null != domain.getOrganization()) {
			domain.setOrgInternalCode(domain.getOrganization()
					.getOrgInternalCode());
		}
		if (checkDataExitInCache(NewBaseInfoTables.IDLEYOUTH_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		checkStayInSchool(domain);
		idleYouthService.addIdleYouth(domain);
		cleanDataInCache(NewBaseInfoTables.IDLEYOUTH_KEY, domain.getIdCardNo(),
				domain.getOrganization().getId().toString());

		/*
		 * BUG单 #21934 重点青少年中导入1000条数据时导入成功后每次只显示5条 fateson add
		 * idleYouthService.addIdleYouth(domain); 中已经包含该操作 for (PropertyDict
		 * dict : domain.getStaffType()) {
		 * idleYouthService.addStaffType(domain.getId(), dict.getId()); }
		 */

		return domain;
	}

	@Override
	public IdleYouth updateDomain(IdleYouth domain) {
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		checkStayInSchool(domain);
		idleYouthService.updateIdleYouthByIdCardNoAndOrgId(
				domain.getIdCardNo(), domain.getOrganization().getId(), domain);
		idleYouthService.regrantStaffTypeIds(domain.getId(),
				convertFromProperyDicts(domain.getStaffType()));
		return domain;
	}

	private List<Long> convertFromProperyDicts(List<PropertyDict> dicts) {
		List<Long> lgs = null;
		if (null != dicts && dicts.size() > 0) {
			lgs = new ArrayList<Long>();
			for (PropertyDict dict : dicts) {
				lgs.add(dict.getId());
			}
		}
		return lgs;
	}

	private void checkStayInSchool(IdleYouth domain) {
		if (true != domain.isStayInSchool()) {
			domain.setSchoolName(null);
		}
	}

}
