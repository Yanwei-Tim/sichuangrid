package com.tianque.baseInfo.publicPlace.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.publicPlace.domain.PublicPlace;
import com.tianque.baseInfo.publicPlace.service.PublicPlaceService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.validate.DataConverterAdapter;

@Component("publicPlaceDataConverter")
@Scope("prototype")
public class PublicPlaceDataConverter extends DataConverterAdapter<PublicPlace> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private PublicPlaceService placeServic;
	@Autowired
	@Qualifier("publicPlaceValidator")
	private DomainValidator<PublicPlace> PlaceValidator;

	@Override
	public ValidateResult validate(PublicPlace publicPlace, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return PlaceValidator.validate(publicPlace);
	}

	@Override
	public PublicPlace convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		PublicPlace result = new PublicPlace();

		ExcelImportHelper.procImportDate(SpecialGroupsContext.getPublicPlaceImportArray(),
				cellValues, getUploadOrganization(), result, vr);

		return result;
	}

	@Override
	public PublicPlace convertToDomain(String[] cellValues, ValidateResult vr,
			String[][] beanDatas, Organization headerOrg) {
		setUploadOrganization(headerOrg);
		cellValues = validateHelper.cellValueTrim(cellValues);
		PublicPlace result = new PublicPlace();
		ExcelImportHelper.newProcImportDate(beanDatas, cellValues, getUploadOrganization(), result,
				vr);
		return result;
	}

	@Override
	public PublicPlace persistenceDomain(PublicPlace domain) {
		domain = placeServic.addPublicPlace(domain);
		return domain;
	}

	@Override
	public PublicPlace updateDomain(PublicPlace domain) {
		return placeServic.updatePlaceByPlaceNameAndOrgId(domain.getPlaceName(), domain
				.getOrganization().getId(), domain);
	}

	public boolean isRepeatData(PublicPlace domain) {
		return placeServic.hasDuplicatePlace(domain.getOrganization().getId(),
				domain.getPlaceName(), null);
	}

	@Override
	public ValidateResult validate(PublicPlace domain) {
		return PlaceValidator.validate(domain);
	}

}
