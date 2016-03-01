package com.tianque.baseInfo.internetBar.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.internetBar.domain.InternetBar;
import com.tianque.baseInfo.internetBar.service.InternetBarService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.InternetBarContext;

@Component("internetBarDataConverter")
@Scope("prototype")
public class InternetBarDataConverter extends AbstractDataConverter<InternetBar> {

	@Autowired
	private InternetBarService internetBarService;

	@Autowired
	private ValidateHelper validateHelper;

	@Autowired
	@Qualifier("internetBarValidator")
	private DomainValidator<InternetBar> domainValidator;

	@Override
	public InternetBar convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		InternetBar result = new InternetBar();
		String[][] excelColumArray = InternetBarContext.getInternetBarImportArray();
		ExcelImportHelper.procImportDate(excelColumArray, cellValues, getUploadOrganization(),
				result, vr);

		return result;
	}

	@Override
	public ValidateResult validate(InternetBar internetBar, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return domainValidator.validate(internetBar);
	}

	@Override
	public boolean isRepeatData(InternetBar domain) {
		return internetBarService.hasDuplicateInternetBar(domain.getOrganization().getId(),
				domain.getPlaceName(), domain.getId());
	}

	@Override
	public InternetBar persistenceDomain(InternetBar domain) {
		return internetBarService.addInternetBar(domain);
	}

	@Override
	public InternetBar updateDomain(InternetBar domain) {
		return internetBarService.updateInternetBarForImport(domain);
	}

}
