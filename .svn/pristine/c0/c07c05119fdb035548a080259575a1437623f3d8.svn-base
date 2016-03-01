package com.tianque.baseInfo.druggy.dataConverter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.approval.domain.property.SourcesState;
import com.tianque.baseInfo.druggy.dao.DruggyDao;
import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.baseInfo.druggy.service.DruggyService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("druggyDataConverter")
@Scope("prototype")
public class DruggyDataConverter extends AbstractDataConverter<Druggy> {
	@Autowired
	private DruggyService druggyService;
	@Autowired
	@Qualifier("druggyValidator")
	private AbstractCountrymenValidator<Druggy> druggyValidator;

	@Autowired
	private DruggyDao druggyDao;

	@Override
	public Druggy convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		Druggy result = new Druggy();
		String[][] excelColumArray = null;
		if (GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext.getDruggyImportArrayNap();
		} else if (GlobalSetting.SYNC_ACTUAL_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext.getDruggyImportArraySap();
		} else {
			excelColumArray = SpecialGroupsContext.getDruggyImportArrayNdt();
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
	public boolean isRepeatData(Druggy domain) {
		return druggyService.hasDuplicateDruggy(domain.getOrganization()
				.getId(), domain.getIdCardNo(), null);
	}

	@Override
	public ValidateResult validate(Druggy druggy, int realRow) {
		return validate(druggyValidator, druggy, realRow);
	}

	@Override
	public Druggy persistenceDomain(Druggy domain) {
		// checkBusinessLogic(domain);
		if (checkDataExitInCache(NewBaseInfoTables.DRUGGY_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		convertBaseInfoReferencePersistence(domain);
		domain.setSourcesState(SourcesState.BASEINFO);
		domain = druggyService.addDruggy(domain);

		cleanDataInCache(NewBaseInfoTables.DRUGGY_KEY, domain.getIdCardNo(),
				domain.getOrganization().getId().toString());
		return domain;
	}

	@Override
	public Druggy updateDomain(Druggy domain) {
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		druggyService.updateDruggyByIdCardNoAndOrgId(domain.getIdCardNo(),
				domain.getOrganization().getId(), domain);

		return domain;
	}

	public void persistenceDomain(List<Druggy> data) {
		// TODO 新增每个对象id的获取
		for (int i = 0; i < data.size(); i++) {
			// checkBusinessLogic(data.get(i));
			convertBaseInfoReferencePersistence(data.get(i));
		}
		druggyDao.batchInsertDate(data);
	}

	public void updateDomain(List<Druggy> data) {
		// TODO 修改每个对象id的获取
		for (int i = 0; i < data.size(); i++) {
			// checkBusinessLogic(data.get(i));
			convertBaseInfoReferencePersistence(data.get(i));
		}
		druggyDao.batchUpdateDate(data);
	}

}