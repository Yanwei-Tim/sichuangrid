package com.tianque.datatransfer.dataconvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.tenHouseholdsJoint.domain.FamilyInfo;
import com.tianque.tenHouseholdsJoint.service.impl.FamilyInfoServiceImpl;

@Component("tenHouseHoldsFamilyDataConverter")
@Scope("prototype")
public class TenHouseHoldsFamilyDataConverter extends
AbstractDataConverter<FamilyInfo>{
	@Qualifier("tenHouseHoldsFamilyValidatorImpl")
	@Autowired
	DomainValidator<FamilyInfo> tenHouseHoldsFamilyValidator;
	
	@Autowired
	FamilyInfoServiceImpl familyTeamService;
	/***
	 * 验证
	 */
	@Override
	public ValidateResult validate(FamilyInfo domain, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		ValidateResult result = tenHouseHoldsFamilyValidator.validate(domain);
		return result;
	}

	/***
	 * 新增
	 */
	@Override
	public FamilyInfo persistenceDomain(FamilyInfo domain) {
		return familyTeamService.addFamilyInfo(domain);
	}

	@Override
	public FamilyInfo convertToDomain(String[] cellValues, ValidateResult result) {
		return null;
	}

	@Override
	public FamilyInfo updateDomain(FamilyInfo domain) {
		return null;
	}

}
