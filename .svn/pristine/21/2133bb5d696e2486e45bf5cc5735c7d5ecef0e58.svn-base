package com.tianque.aidsPopulations.dataConverter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.aidsPopulations.dao.AidspopulationsDao;
import com.tianque.aidsPopulations.domain.Aidspopulations;
import com.tianque.aidsPopulations.service.AidspopulationsService;
import com.tianque.approval.domain.property.SourcesState;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("aidspopulationsDataConverter")
@Scope("prototype")
public class AidspopulationsDataConverter extends
		AbstractDataConverter<Aidspopulations> {
	@Autowired
	private AidspopulationsService aidspopulationService;
	@Autowired
	@Qualifier("aidspopulationsValidator")
	private AbstractCountrymenValidator<Aidspopulations> aidspopulationsValidator;

	@Autowired
	private AidspopulationsDao aidspopulationsDao;

	@Override
	public Aidspopulations convertToDomain(String[] cellValues,
			ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		Aidspopulations result = new Aidspopulations();
		String[][] excelColumArray = null;
		if (GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getAidspopulationsImportArrayNap();
		} else if (GlobalSetting.SYNC_ACTUAL_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getAidspopulationsImportArraySap();
		} else {
			excelColumArray = SpecialGroupsContext
					.getAidspopulationsImportArrayNdt();
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
	public boolean isRepeatData(Aidspopulations domain) {
		return aidspopulationService.hasDuplicateAidspopulations(domain
				.getOrganization().getId(), domain.getIdCardNo(), null);
	}

	@Override
	public ValidateResult validate(Aidspopulations aidspopulations, int realRow) {
		return validate(aidspopulationsValidator, aidspopulations, realRow);
	}

	@Override
	public Aidspopulations persistenceDomain(Aidspopulations domain) {
		// checkBusinessLogic(domain);
		if (checkDataExitInCache(NewBaseInfoTables.AIDSPOPULATIONS_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		convertBaseInfoReferencePersistence(domain);
		domain.setSourcesState(SourcesState.BASEINFO);
		aidspopulationService.addAidspopulations(domain);
		cleanDataInCache(NewBaseInfoTables.AIDSPOPULATIONS_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString());
		return domain;
	}

	@Override
	public Aidspopulations updateDomain(Aidspopulations domain) {
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		aidspopulationService.updateAidspopulationsByIdCardNoAndOrgId(
				domain.getIdCardNo(), domain.getOrganization().getId(), domain);

		return domain;
	}

	public void persistenceDomain(List<Aidspopulations> data) {
		// TODO 新增每个对象id的获取
		for (int i = 0; i < data.size(); i++) {
			// checkBusinessLogic(data.get(i));
			convertBaseInfoReferencePersistence(data.get(i));
		}
		aidspopulationsDao.batchInsertDate(data);
	}

	public void updateDomain(List<Aidspopulations> data) {
		// TODO 修改每个对象id的获取
		for (int i = 0; i < data.size(); i++) {
			// checkBusinessLogic(data.get(i));
			convertBaseInfoReferencePersistence(data.get(i));
		}
		aidspopulationsDao.batchUpdateDate(data);
	}

}