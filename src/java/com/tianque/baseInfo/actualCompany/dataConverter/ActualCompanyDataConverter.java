package com.tianque.baseInfo.actualCompany.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.baseInfo.actualCompany.service.ActualCompanyService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;

/**
 * 实有单位导入数据
 */
@Component("actualCompanyDataConverter")
public class ActualCompanyDataConverter extends AbstractDataConverter<ActualCompany> {
	@Autowired
	private ActualCompanyService actualCompanyService;

	@Qualifier("actualCompanyValidator")
	@Autowired
	private DomainValidator<ActualCompany> actualCompanyValidator;

	/** 将Excel数据转为对象 */
	@Override
	public ActualCompany convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		ActualCompany domain = new ActualCompany();

		ExcelImportHelper.procImportDate(SpecialGroupsContext.getActualCompanyImportArray(),
				cellValues, getUploadOrganization(), domain, vr);
		return domain;
	}

	/** 保存数据到数据库 */
	@Override
	public ActualCompany persistenceDomain(ActualCompany domain) {

		return actualCompanyService.addActualCompany(domain);
	}

	/** 修改数据到数据库 */
	@Override
	public ActualCompany updateDomain(ActualCompany domain) {
		ActualCompany actualCompany = actualCompanyService.getActualCompanyByCompanyName(
				domain.getCompanyName(), domain.getOrganization().getId());
		domain.setId(actualCompany.getId());
		return actualCompanyService.updateActualCompany(domain);
	}

	/** 验证Excel数据的正确性 */
	@Override
	public ValidateResult validate(ActualCompany actualCompany, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return actualCompanyValidator.validate(actualCompany);
	}

	@Override
	public boolean isRepeatData(ActualCompany domain) {
		return actualCompanyService.hasDuplicateActualCompany(domain.getOrganization().getId(),
				domain.getIdCardNo(), null);
	}

}
