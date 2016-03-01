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
import com.tianque.domain.School;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.SchoolService;

@Component("schoolDataConverter")
@Scope("prototype")
public class SchoolDataConverter extends AbstractDataConverter<School> {
	@Autowired
	private DataConvertionHelper convertHelper;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private SchoolService schoolService;

	@Qualifier("schoolValidator")
	@Autowired
	private DomainValidator schoolValidator;

	@Override
	public School convertToDomain(String[] cellValues, ValidateResult vr) {
		School school = new School();
		cellValues = validateHelper.cellValueTrim(cellValues);

		ExcelImportHelper.procImportDate(SpecialGroupsContext.getSchoolImportArray(), cellValues,
				getUploadOrganization(), school, vr);

		return school;
	}

	@Override
	public School persistenceDomain(School domain) {
		domain.setIsEmphasis(false); // 现在关注
		return schoolService.addSchool(domain);
	}

	@Override
	public ValidateResult validate(School school, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return schoolValidator.validate(school);
	}

	@Override
	public boolean isRepeatData(School domain) {
		return schoolService.findSchoolByChineseNameAndOrgId(domain.getChineseName(), domain
				.getOrganization().getId()) != null;
	}

	@Override
	public School updateDomain(School domain) {
		return schoolService.updateSchoolByName(domain.getChineseName(), domain.getOrganization()
				.getId(), domain);
	}

}
