package com.tianque.baseInfo.qPersonnel.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.qPersonnel.domain.QPersonnel;
import com.tianque.baseInfo.qPersonnel.service.QPersonnelService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("qPersonelDataConverter")
@Scope("prototype")
public class QPersonelDataConverter extends AbstractDataConverter<QPersonnel> {
	@Autowired
	private QPersonnelService qPersonnelService;
	@Autowired
	private ValidateHelper validateHelper;

	@Autowired
	@Qualifier("qPersonnelValidator")
	private AbstractCountrymenValidator<QPersonnel> qPersonnelValidator;

	@Override
	public ValidateResult validate(QPersonnel rectificativePerson, int realRow) {
		return validate(qPersonnelValidator, rectificativePerson, realRow);
	}

	@Override
	public QPersonnel convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		QPersonnel result = new QPersonnel();
		String[][] excelColumArray = null;
		if (GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext.getQPersonnelSlf();
		} else if (GlobalSetting.SYNC_ACTUAL_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getRectificativePersonImportArraySap();
		} else {
			excelColumArray = SpecialGroupsContext.getQPersonnelArrayRla();
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
	public QPersonnel persistenceDomain(QPersonnel domain) {
		if (checkDataExitInCache(NewBaseInfoTables.QPERSONNEL_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		if (qPersonnelService.existQPersonnel(domain.getOrganization().getId(),
				domain.getIdCardNo(), null)) {
			domain.setId(qPersonnelService.getQPersonnelByIdCardNo(
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
		qPersonnelService.addQPersonnel(domain);
		cleanDataInCache(NewBaseInfoTables.QPERSONNEL_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString());
		return domain;
	}

	@Override
	public boolean isRepeatData(QPersonnel domain) {
		boolean flag = qPersonnelService.existQPersonnel(domain
				.getOrganization().getId(), domain.getIdCardNo(), null);
		if (flag) {
			domain.setId(qPersonnelService.getQPersonnelByIdCardNo(
					domain.getIdCardNo(), domain.getOrganization().getId())
					.getId());
		}
		return flag;
	}

	@Override
	public QPersonnel updateDomain(QPersonnel domain) {
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		qPersonnelService.updateQPersonnelByName(domain.getIdCardNo(), domain
				.getOrganization().getId(), domain);
		return domain;
	}

}
