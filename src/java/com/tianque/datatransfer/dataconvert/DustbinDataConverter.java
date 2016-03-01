package com.tianque.datatransfer.dataconvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.DataConvertionHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Dustbin;
import com.tianque.service.DustbinService;

@Component("dustbinDataConverter")
@Scope("prototype")
public class DustbinDataConverter extends AbstractDataConverter<Dustbin> {
	@Autowired
	private DataConvertionHelper convertHelper;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private DustbinService dustbinService;

	@Qualifier("dustbinValidator")
	@Autowired
	private DomainValidator dustbinValidator;

	@Override
	public ValidateResult validate(Dustbin dustbin, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return dustbinValidator.validate(dustbin);
	}

	@Override
	@Deprecated
	public Dustbin convertToDomain(String[] cellValues, ValidateResult result) {
		// cellValues = validateHelper.cellValueTrim(cellValues);
		// Dustbin domain = new Dustbin();
		// ExcelImportHelper.procImportDate(SpecialGroupsContext.getActualCompanyImportArray(),
		// cellValues, getUploadOrganization(), domain, result);
		// return domain;
		return null;
	}

	@Override
	public Dustbin persistenceDomain(Dustbin dustbin) {
		return dustbinService.addDustbin(dustbin);
	}

	@Override
	public Dustbin updateDomain(Dustbin domain) {
		Dustbin dustbin = dustbinService.getDustbinByDustbinCode(
				domain.getDustbinCode(), domain.getOrganization().getId());
		domain.setId(dustbin.getId());
		return dustbinService.updateDustbin(domain);
	}

	/**
	 * 在NewExcelDataimportThread.handleCurrectData()中用于判断,
	 * 重复的数据(true)进行updateDomain(Dustbin domain), 否则persistenceDomain(Dustbin
	 * dustbin)
	 */
	@Override
	public boolean isRepeatData(Dustbin dustbin) {
		// return dustbinService.hasDuplicateDustbin(dustbin);//暂不做验证
		return false;
	}

}
