package com.tianque.baseInfo.buildDatas.dataConverter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.buildDatas.domain.Builddatas;
import com.tianque.baseInfo.buildDatas.service.BuilddatasService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.domain.Organization;
import com.tianque.excel.definition.HouseContext;
import com.tianque.validate.impl.BuildingValidatorImpl;

/**
 * @author hxpwangyi@163.com
 * @date 2013-5-15
 */
@Component("buildDatasDataConverter")
public class BuildDatasDataConverter extends AbstractDataConverter<Builddatas> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private BuildingValidatorImpl buildingValidator;
	@Autowired
	private BuilddatasService builddataService;

	@Override
	public ValidateResult validate(Builddatas domain, int realRow) {
		ValidateResult result = new ValidateResult();
		ExcelImportHelper.realRow.set(realRow);
		result = buildingValidator.validate(domain);
		return result;
	}

	@Override
	public Builddatas convertToDomain(String[] cellValues, ValidateResult result) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		Builddatas builddatas = new Builddatas();
		String[][] excelColumArray = HouseContext.getBuildDatasImportArray();
		ExcelImportHelper.procImportDate(excelColumArray, cellValues,
				getUploadOrganization(), builddatas, result);
		if (null != builddatas.getOrganization()) {
			builddatas.setOrginternalcode(builddatas.getOrganization()
					.getOrgInternalCode());
		}

		builddatas.setCreateDate(new Date());
		return builddatas;
	}

	@Override
	public Builddatas convertToDomain(String[] cellValues,
			ValidateResult result, String[][] beanDatas, Organization headerOrg) {
		setUploadOrganization(headerOrg);
		cellValues = validateHelper.cellValueTrim(cellValues);
		Builddatas returnObject = new Builddatas();
		ExcelImportHelper.newProcImportDate(beanDatas, cellValues,
				getUploadOrganization(), returnObject, result);

		if (null != returnObject.getOrganization()) {
			returnObject.setOrginternalcode(returnObject.getOrganization()
					.getOrgInternalCode());
		}

		return returnObject;
	}

	@Override
	public Builddatas persistenceDomain(Builddatas domain) {
		builddataService.addBuilddatas(domain);
		return domain;
	}

	@Override
	public Builddatas updateDomain(Builddatas domain) {
		builddataService.updateBuilddatas(domain);
		return domain;
	}

}
