package com.tianque.baseInfo.scenicManage.scenicEquipment.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.scenicManage.scenicEquipment.domain.ScenicEquipment;
import com.tianque.baseInfo.scenicManage.scenicEquipment.service.ScenicEquipmentService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;

@Component("scenicEquipmentDataConverter")
@Scope("prototype")
public class ScenicEquipmentDataConverter extends
		AbstractDataConverter<ScenicEquipment> {

	@Autowired
	private ScenicEquipmentService scenicEquipmentService;

	@Autowired
	private ValidateHelper validateHelper;

	@Autowired
	@Qualifier("scenicEquipmentValidator")
	private DomainValidator<ScenicEquipment> domainValidator;

	@Override
	public ScenicEquipment convertToDomain(String[] cellValues,
			ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		ScenicEquipment result = new ScenicEquipment();
		String[][] excelColumArray = SpecialGroupsContext
				.getScenicEquipmentImportArray();
		ExcelImportHelper.procImportDate(excelColumArray, cellValues,
				getUploadOrganization(), result, vr);

		return result;
	}

	@Override
	public ValidateResult validate(ScenicEquipment scenicEquipment, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return domainValidator.validate(scenicEquipment);
	}

	@Override
	public ScenicEquipment persistenceDomain(ScenicEquipment domain) {
		return scenicEquipmentService.addScenicEquipment(domain);
	}

	@Override
	public ScenicEquipment updateDomain(ScenicEquipment domain) {
		return scenicEquipmentService.updateScenicEquipmentForImport(domain);
	}

}
