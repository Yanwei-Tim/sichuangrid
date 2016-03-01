package com.tianque.datatransfer.dataconvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.DataConvertionHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Hospital;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.service.HospitalService;

@Component("hospitalDataConverter")
public class HospitalDataConverter extends AbstractDataConverter<Hospital> {
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private DataConvertionHelper convertHelper;
	@Autowired
	private ValidateHelper validateHelper;
	@Qualifier("hospitalValidator")
	@Autowired
	private DomainValidator<Hospital> hospitalValidator;

	@Override
	public Hospital convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		Hospital result = new Hospital();
		result.setHospitalName(cellValues[0]);
		result.setOrganization(convertHelper.convertToOrganization(
				getUploadOrganization(), cellValues[1]));
		result.setOrgInternalCode(result.getOrganization().getOrgInternalCode());
		result.setKind(convertHelper.convertToPropertyDict(
				PropertyTypes.HOSPITALS_KIND, cellValues[2]));
		result.setAddress(cellValues[3]);
		result.setType(convertHelper.convertToPropertyDict(
				PropertyTypes.HOSPITALS_TYPE, cellValues[4]));
		result.setDirector(cellValues[5]);
		result.setAffiliatedUnit(cellValues[6]);
		result.setTelephone(cellValues[7]);
		result.setMobileNumber(cellValues[8]);
		result.setEmail(cellValues[9]);
		result.setFax(cellValues[10]);
		result.setRemark(cellValues[11]);
		return result;
	}

	@Override
	public Hospital persistenceDomain(Hospital domain) {
		return hospitalService.addHospital(domain);
	}

	@Override
	public ValidateResult validate(Hospital hospital, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return hospitalValidator.validate(hospital);
	}

	@Override
	public boolean isRepeatData(Hospital domain) {
		 return hospitalService.hasDuplicateHospital(
				 domain.getOrganization().getId(),domain.getHospitalName()) != null;
	}

	@Override
	public Hospital updateDomain(Hospital domain) {
		 return hospitalService.updateHospitalByName(domain.getHospitalName(),
		 domain.getOrganization().getId(), domain);
	}

}
