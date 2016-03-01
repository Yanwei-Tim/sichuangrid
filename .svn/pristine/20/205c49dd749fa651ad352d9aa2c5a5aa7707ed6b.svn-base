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
import com.tianque.domain.PartyMemberInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.CurrentAddressType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.service.PartyMemberInfoService;
import com.tianque.service.PartyOrgInfoService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("partyMemberInfoDataConverter")
@Scope("prototype")
public class PartyMemberInfoDataConverter extends AbstractDataConverter<PartyMemberInfo> {

	@Autowired
	private PartyMemberInfoService partyMemberInfoService;

	@Autowired
	private PartyOrgInfoService partyOrgInfoService;

	@Autowired
	DataConvertionHelper convertHelper;
	@Autowired
	OrganizationDubboService organizationDubboService;

	@Autowired
	private ValidateHelper validateHelper;

	@Qualifier("partyMemberInfoValidator")
	@Autowired
	private DomainValidator<PartyMemberInfo> partyMemberInfoValidator;

	@Override
	public PartyMemberInfo convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		PartyMemberInfo result = new PartyMemberInfo();
		result.setName(cellValues[0]);
		result.setOrganization(organizationDubboService.getOrganizationsByParentAndOrgName(
				getUploadOrganization().getId(), cellValues[1]));
		/*
		 * Organization organization = new Organization();
		 * organization.setId(6L); organization.setOrgName("太和居委会");
		 * result.setOrganization(organization);
		 */

		result.setGender(convertHelper.convertToPropertyDict(PropertyTypes.GENDER, cellValues[2]));
		result.setBirthday(convertHelper.convertToDate(cellValues[3]));
		result.setIdCardNo(cellValues[4]);
		result.setPartyOrgInfo(this.partyOrgInfoService.getPartyOrgInfoByNameAndOrgId(
				cellValues[5], result.getOrganization().getId()));
		result.setJoinPartyBranchDate(convertHelper.convertToDate(cellValues[6]));
		result.setPartyMemberType(convertHelper.convertToPropertyDict(
				PropertyTypes.PARTYMEMBER_TYPE, cellValues[7]));
		result.setJoinPartyDate(convertHelper.convertToDate(cellValues[8]));
		result.setJoinPartyBranch(cellValues[9]);
		result.setBecomesDate(convertHelper.convertToDate(cellValues[10]));
		result.setBecomesState(convertHelper.convertToPropertyDict(PropertyTypes.BECOMES_STATE,
				cellValues[11]));
		result.setPartyPosition(cellValues[12]);
		result.setOfficeDate(convertHelper.convertToDate(cellValues[13]));
		result.setTrainingState(convertHelper.convertToPropertyDict(PropertyTypes.TRAINING_STATE,
				cellValues[14]));
		result.setRewardsPunishment(cellValues[15]);
		result.setExpertise(cellValues[16]);
		if ("是".equals(cellValues[17])) {
			result.setIsFlowPartyCard(1L);
		} else {
			result.setIsFlowPartyCard(0L);
		}
		result.setFlowPartyBranch(cellValues[18]);
		result.setFlowPartyBranchDate(convertHelper.convertToDate(cellValues[19]));
		result.setPartyBranchContacts(cellValues[20]);
		result.setBranchTelephone(cellValues[21]);
		result.setBranchMobileNumber(cellValues[22]);
		result.setProvince(cellValues[23]);
		result.setCity(cellValues[24]);
		result.setDistrict(cellValues[25]);
		result.setNativePlaceAddress(cellValues[26]);

		PropertyDict propertyDict = convertHelper.convertToPropertyDict(
				PropertyTypes.CURRENT_ADDRESS_TYPE, cellValues[27]);
		result.setCurrentAddressType(convertHelper.convertToPropertyDict(
				PropertyTypes.CURRENT_ADDRESS_TYPE, cellValues[27]));
		if (CurrentAddressType.BUSINESS_HOUSE == propertyDict.getInternalId()) {

			result.setCommunity(cellValues[28]);
			result.setBlock(cellValues[29]);
			result.setUnit(cellValues[30]);
			result.setRoom(cellValues[31]);
			String str = null;
			str = cellValues[28] + "小区"
					+ ("".equals(cellValues[29]) ? cellValues[29] : cellValues[29] + "幢")
					+ ("".equals(cellValues[30]) ? cellValues[30] : cellValues[30] + "单元")
					+ ("".equals(cellValues[31]) ? cellValues[31] : cellValues[31] + "室");
			result.setCurrentAddress(str);
		}
		if (CurrentAddressType.OTHER == propertyDict.getInternalId()) {
			result.setCurrentAddress(cellValues[28]);
		}
		result.setOtherAddress(cellValues[32]);
		result.setUsedName(cellValues[33]);
		result.setWorkUnit(cellValues[34]);
		result.setTelephone(cellValues[35]);
		result.setMobileNumber(cellValues[36]);
		result.setEmail(cellValues[37]);
		result.setNation(convertHelper.convertToPropertyDict(PropertyTypes.NATION, cellValues[38]));
		result.setPoliticalBackground(convertHelper.convertToPropertyDict(
				PropertyTypes.POLITICAL_BACKGROUND, cellValues[39]));
		result.setSchooling(convertHelper.convertToPropertyDict(PropertyTypes.SCHOOLING,
				cellValues[40]));
		result.setCareer(convertHelper.convertToPropertyDict(PropertyTypes.CAREER, cellValues[41]));
		result.setMaritalState(convertHelper.convertToPropertyDict(PropertyTypes.MARITAL_STATUS,
				cellValues[42]));
		result.setBloodType(convertHelper.convertToPropertyDict(PropertyTypes.BLOOD_TYPE,
				cellValues[43]));
		result.setFaith(convertHelper.convertToPropertyDict(PropertyTypes.FAITH, cellValues[44]));
		result.setStature((cellValues[45] != null && cellValues[46].trim().length() > 0) ? Long
				.parseLong(cellValues[45]) : null);
		result.setRemark(cellValues[46]);
		return result;
	}

	@Override
	public ValidateResult validate(PartyMemberInfo partyMemberInfo, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return partyMemberInfoValidator.validate(partyMemberInfo);
	}

	/*
	 * (non-Javadoc)
	 * @see com.tianque.datatransfer.DataConvert#isRepeatData(java.lang.Object)
	 */
	public boolean isRepeatData(PartyMemberInfo domain) {
		return this.partyMemberInfoService.hasDuplicatePartyMemberInfo(domain.getOrganization()
				.getId(), domain.getIdCardNo(), null);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.tianque.datatransfer.dataconvert.AbstractDataConverter#persistenceDomain
	 * (java.lang.Object)
	 */
	@Override
	public PartyMemberInfo persistenceDomain(PartyMemberInfo domain) {
		return this.partyMemberInfoService.addPartyMemberInfo(domain);
	}

	/*
	 * (non-Javadoc)
	 * @see com.tianque.datatransfer.DataConvert#updateDomain(java.lang.Object)
	 */
	@Override
	public PartyMemberInfo updateDomain(PartyMemberInfo domain) {
		return this.partyMemberInfoService.updatePartyMemberInfoByIdCardNo(domain.getIdCardNo(),
				domain.getOrganization().getId(), domain);
	}
}
