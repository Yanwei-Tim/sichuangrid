package com.tianque.baseInfo.dangerousChemicalsUnit.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.dangerousChemicalsUnit.domain.DangerousChemicalsUnit;
import com.tianque.baseInfo.dangerousChemicalsUnit.service.DangerousChemicalsUnitService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;

@Component("dangerousChemicalsUnitConvert")
public class DangerousChemicalsUnitConvert extends AbstractDataConverter<DangerousChemicalsUnit> {

	@Autowired
	private DangerousChemicalsUnitService dangerousChemicalsUnitService;

	@Autowired
	private ValidateHelper validateHelper;

	@Autowired
	@Qualifier("dangerousChemicalsUnitValidator")
	private DomainValidator<DangerousChemicalsUnit> dangerousChemicalsUnitValidator;

	/** 将Excel数据转为对象 */
	@Override
	public DangerousChemicalsUnit convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		DangerousChemicalsUnit result = new DangerousChemicalsUnit();
		// String[][] excelColumArray = DangerousChemicalUnitContext
		// .getDangerousChemicalsImportArray();
		ExcelImportHelper.procImportDate(SpecialGroupsContext.getDangerousChemicalsImportArray(),
				cellValues, getUploadOrganization(), result, vr);
		if (null != result.getOrganization()) {
			result.setOrgInternalCode(result.getOrganization().getOrgInternalCode());
		}
		return result;
	}

	@Override
	public ValidateResult validate(DangerousChemicalsUnit dangerousChemicalsUnit, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return dangerousChemicalsUnitValidator.validate(dangerousChemicalsUnit);
	}

	@Override
	public DangerousChemicalsUnit persistenceDomain(DangerousChemicalsUnit domain) {
		return dangerousChemicalsUnitService.addDangerousChemicalsUnit(domain);
	}

	@Override
	public boolean isRepeatData(DangerousChemicalsUnit domain) {
		return dangerousChemicalsUnitService.existDangerousChemicalsUnit(domain.getUnitName(),
				domain.getOrganization().getId()) != null;
	}

	@Override
	public DangerousChemicalsUnit updateDomain(DangerousChemicalsUnit domain) {
		if (null == domain.getIsEmphasis() || false == domain.getIsEmphasis()) {
			domain.setIsEmphasis(true);
		} else {
			domain.setIsEmphasis(false);
		}
		return dangerousChemicalsUnitService.updateDangerousChemicalsUnitByUnitNameAndOrgId(
				domain.getUnitName(), domain.getOrganization().getId(), domain);
	}

}
