package com.tianque.baseInfo.unemployedPeople.dataConverter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.unemployedPeople.domain.UnemployedPeople;
import com.tianque.baseInfo.unemployedPeople.service.UnemployedPeopleService;
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

@Component("unemployedPeopleDataConverter")
public class UnemployedPeopleDataConverter extends
		AbstractDataConverter<UnemployedPeople> {
	@Autowired
	private UnemployedPeopleService unemployedPeopleService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	@Qualifier("unemployedPeopleValidator")
	private AbstractCountrymenValidator<UnemployedPeople> unemployedPeopleValidator;
	@Autowired
	private GlobalSettingService globalSettingService;

	@Override
	public ValidateResult validate(UnemployedPeople unemployedPeople,
			int realRow) {
		return validate(unemployedPeopleValidator, unemployedPeople, realRow);
	}

	@Override
	public UnemployedPeople convertToDomain(String[] cellValues,
			ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		UnemployedPeople result = new UnemployedPeople();
		String[][] excelColumArray = null;
		if (GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getUnemployedPeopleImportArrayNap();
		} else if (GlobalSetting.SYNC_ACTUAL_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getUnemployedPeopleImportArraySap();
		} else {
			excelColumArray = SpecialGroupsContext
					.getUnemployedPeopleImportArrayNdt();
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
	public boolean isRepeatData(UnemployedPeople domain) {
		boolean flag = unemployedPeopleService.hasDuplicateUnemployedPeople(
				domain.getOrganization().getId(), domain.getIdCardNo(), null);
		if (flag) {
			domain.setId(unemployedPeopleService
					.getUnemployedPeopleByIdCardNoAndOrganizationId(
							domain.getIdCardNo(),
							domain.getOrganization().getId()).getId());
		}
		return flag;
	}

	@Override
	public UnemployedPeople persistenceDomain(UnemployedPeople domain) {
		if (checkDataExitInCache(NewBaseInfoTables.UNEMPLOYEDPEOPLE_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		unemployedPeopleService.addUnemployedPeople(domain);
		cleanDataInCache(NewBaseInfoTables.UNEMPLOYEDPEOPLE_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString());
		/*
		 * fateson add 上面的unemployedPeopleService.addUnemployedPeople(domain)方法中
		 * 包含了下面的两个调用 if (null != domain.getEmploymentIntention() &&
		 * domain.getEmploymentIntention().size() > 0) { for (PropertyDict p :
		 * domain.getEmploymentIntention()) {
		 * unemployedPeopleService.addEmploymentIntention(domain.getId(),
		 * p.getId()); } } if (null != domain.getTrainingIntention() &&
		 * domain.getTrainingIntention().size() > 0) { for (PropertyDict p :
		 * domain.getTrainingIntention()) {
		 * unemployedPeopleService.addTrainingIntention(domain.getId(),
		 * p.getId()); } }
		 */

		return domain;
	}

	@Override
	public UnemployedPeople updateDomain(UnemployedPeople domain) {
		fillDefaultBirthday(domain);
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		updateUnemployedPeople(domain);
		return domain;
	}

	private void updateUnemployedPeople(UnemployedPeople domain) {
		unemployedPeopleService.updateUnemployedPeopleByIdCardNo(
				domain.getIdCardNo(), domain.getOrganization().getId(), domain);
		if (null != domain.getEmploymentIntention()
				&& domain.getEmploymentIntention().size() > 0) {
			List<Long> employmentIntentionIds = new ArrayList<Long>();
			for (PropertyDict p : domain.getEmploymentIntention()) {
				employmentIntentionIds.add(p.getId());
			}
			unemployedPeopleService.regrantEmploymentIntentionIds(
					domain.getId(), employmentIntentionIds);
		}
		if (null != domain.getTrainingIntention()
				&& domain.getTrainingIntention().size() > 0) {
			List<Long> trainingIntentionIds = new ArrayList<Long>();
			for (PropertyDict p : domain.getTrainingIntention()) {
				trainingIntentionIds.add(p.getId());
			}
			unemployedPeopleService.regrantTrainingIntentionIds(domain.getId(),
					trainingIntentionIds);
		}
	}

}
