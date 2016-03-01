package com.tianque.baseInfo.scenicManage.scenicTraffic.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.scenicManage.scenicTraffic.domain.ScenicTraffic;
import com.tianque.baseInfo.scenicManage.scenicTraffic.service.ScenicTrafficService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;

/**
 * @ClassName: ScenicTrafficDataConverter
 * @Description: TODO
 * @author wangxiaohu wsmalltiger@163.com
 * @date Oct 31, 2013 1:56:01 PM
 */
@Component("scenicTrafficDataConverter")
@Scope("prototype")
public class ScenicTrafficDataConverter
		extends
			AbstractDataConverter<ScenicTraffic> {

	@Autowired
	private ScenicTrafficService scenicEquipmentService;

	@Autowired
	private ValidateHelper validateHelper;

	@Autowired
	@Qualifier("scenicEquipmentValidator")
	private DomainValidator<ScenicTraffic> domainValidator;

	@Override
	public ScenicTraffic convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		ScenicTraffic result = new ScenicTraffic();
		String[][] excelColumArray = SpecialGroupsContext
				.getScenicTrafficImportArray();
		ExcelImportHelper.procImportDate(excelColumArray, cellValues,
				getUploadOrganization(), result, vr);

		return result;
	}

	@Override
	public ValidateResult validate(ScenicTraffic scenicEquipment, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return domainValidator.validate(scenicEquipment);
	}

	@Override
	public ScenicTraffic persistenceDomain(ScenicTraffic domain) {
		return scenicEquipmentService.addScenicTraffic(domain);
	}

	@Override
	public ScenicTraffic updateDomain(ScenicTraffic domain) {
		return scenicEquipmentService.updateScenicTrafficForImport(domain);
	}

}
