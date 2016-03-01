package com.tianque.baseInfo.publicComplexPlaces.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.publicComplexPlaces.domain.PublicComplexPlaces;
import com.tianque.baseInfo.publicComplexPlaces.service.PublicComplexPlacesService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.validate.DataConverterAdapter;

@Component("publicComplexPlacesDataConverter")
@Scope("prototype")
public class PublicComplexPlacesDataConverter extends DataConverterAdapter<PublicComplexPlaces> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private PublicComplexPlacesService placeServic;
	@Autowired
	@Qualifier("publicComplexPlacesValidator")
	private DomainValidator<PublicComplexPlaces> PlaceValidator;

	@Override
	public ValidateResult validate(PublicComplexPlaces publicComplexPlaces, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return PlaceValidator.validate(publicComplexPlaces);
	}

	@Override
	public PublicComplexPlaces convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		PublicComplexPlaces result = new PublicComplexPlaces();

		ExcelImportHelper.procImportDate(SpecialGroupsContext.getPublicComplexPlacesImportArray(),
				cellValues, getUploadOrganization(), result, vr);

		return result;
	}

	@Override
	public PublicComplexPlaces convertToDomain(String[] cellValues, ValidateResult vr,
			String[][] beanDatas, Organization headerOrg) {
		setUploadOrganization(headerOrg);
		cellValues = validateHelper.cellValueTrim(cellValues);
		PublicComplexPlaces result = new PublicComplexPlaces();
		ExcelImportHelper.newProcImportDate(beanDatas, cellValues, getUploadOrganization(), result,
				vr);
		return result;
	}

	@Override
	public PublicComplexPlaces persistenceDomain(PublicComplexPlaces domain) {
		domain = placeServic.addPublicComplexPlaces(domain);
		return domain;
	}

	@Override
	public PublicComplexPlaces updateDomain(PublicComplexPlaces domain) {
		return placeServic.updatePlaceByPlaceNameAndOrgId(domain.getPlaceName(), domain
				.getOrganization().getId(), domain);
	}

	public boolean isRepeatData(PublicComplexPlaces domain) {
		return placeServic.hasDuplicatePlace(domain.getOrganization().getId(),
				domain.getPlaceName(), null);
	}

	@Override
	public ValidateResult validate(PublicComplexPlaces domain) {
		return PlaceValidator.validate(domain);
	}

}
