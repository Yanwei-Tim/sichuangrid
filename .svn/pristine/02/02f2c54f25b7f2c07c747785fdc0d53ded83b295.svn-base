package com.tianque.datatransfer.dataconvert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.DataConvertionHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.CurrentAddressType;
import com.tianque.domain.property.Infrastructure;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.service.HouseInfoService;

@Component("houseInfoDataConverter")
public class HouseInfoDataConverter extends AbstractDataConverter<HouseInfo> {

	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private DataConvertionHelper convertHelper;
	@Autowired
	private HouseInfoService houseInfoService;
	@Qualifier("houseInfoValidator")
	@Autowired
	private DomainValidator houseInfoValidator;

	@Override
	public HouseInfo convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		HouseInfo houseInfo = new HouseInfo();
		houseInfo.setHouseCode(cellValues[0]);
		houseInfo.setOrganization(convertHelper.convertToOrganization(getUploadOrganization(),
				cellValues[1]));
		PropertyDict addressType = convertHelper.convertToPropertyDict(
				PropertyTypes.CURRENT_ADDRESS_TYPE, cellValues[2]);
		houseInfo.setAddressType(addressType);
		if (addressType.getInternalId() != CurrentAddressType.OTHER) {
			houseInfo.setCommunity(cellValues[3]);
		}
		houseInfo.setAddress(cellValues[3]);
		houseInfo.setBlock(cellValues[4]);
		houseInfo.setUnit(cellValues[5]);
		houseInfo.setRoom(cellValues[6]);
		/*
		 * PropertyDict houseType = convertHelper.convertToPropertyDict(
		 * PropertyTypes.HOUSEING_INFO_TYPE, cellValues[7]);
		 * houseInfo.setHouseType(houseType); if (houseType.getInternalId() ==
		 * HouseInfoType.RENTAL_HOUSE) {
		 * houseInfo.setHouseFileNum(cellValues[8]);
		 * houseInfo.setHiddenDangerStatus(cellValues[9]);
		 * houseInfo.setHiddenDangerLevel(convertHelper.convertToPropertyDict(
		 * PropertyTypes.HOUSEING_INFO_TYPE, cellValues[10])); } if
		 * (houseType.getInternalId() == HouseInfoType.COMPANY_HOUSE) {
		 * houseInfo.setHouseOwner(cellValues[11]);
		 * houseInfo.setLegalPerson(cellValues[12]);
		 * houseInfo.setLegalPersonIdCardNo(cellValues[13]);
		 * houseInfo.setLegalPersonMobile(cellValues[14]); } else {
		 * houseInfo.setHouseOwner(cellValues[12]);
		 * houseInfo.setHouseOwnerIdCardNo(cellValues[13]);
		 * houseInfo.setMobileNumber(cellValues[14]); }
		 * houseInfo.setTelephone(cellValues[15]);
		 * houseInfo.setRoomNumber(convertHelper.convertToLong(cellValues[16]));
		 */
		houseInfo.setHouseArea(convertHelper.convertToDouble(cellValues[17]));
		houseInfo.setHouseStructure(convertHelper.convertToPropertyDict(
				PropertyTypes.LETTINGHOUSE_STRUTS, cellValues[18]));

		List<PropertyDict> infrastructure = new ArrayList<PropertyDict>();
		if ("有".equals(cellValues[19])) {
			infrastructure.addAll(convertHelper.convertToPropertyDicts(
					PropertyTypes.INFRASTRUCTURE, new int[] { Infrastructure.WATER }));
		}
		if ("有".equals(cellValues[20])) {
			infrastructure.addAll(convertHelper.convertToPropertyDicts(
					PropertyTypes.INFRASTRUCTURE, new int[] { Infrastructure.ELECTRICITY }));
		}
		if ("有".equals(cellValues[21])) {
			infrastructure.addAll(convertHelper.convertToPropertyDicts(
					PropertyTypes.INFRASTRUCTURE, new int[] { Infrastructure.GAS }));
		}
		if ("有".equals(cellValues[22])) {
			infrastructure.addAll(convertHelper.convertToPropertyDicts(
					PropertyTypes.INFRASTRUCTURE, new int[] { Infrastructure.WARM }));
		}
		/*
		 * houseInfo.setInfrastructure(infrastructure);
		 * houseInfo
		 * .setRealLiveNumber(convertHelper.convertToLong(cellValues[23]));
		 */
		houseInfo.setRemark(cellValues[24]);
		return houseInfo;
	}

	@Override
	public HouseInfo persistenceDomain(HouseInfo domain) {
		return houseInfoService.addHouseInfo(domain);
	}

	@Override
	public HouseInfo updateDomain(HouseInfo domain) {
		return houseInfoService.updateHouseInfo(domain);
	}

	@Override
	public ValidateResult validate(HouseInfo HouseInfo, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return houseInfoValidator.validate(HouseInfo);
	}

}
