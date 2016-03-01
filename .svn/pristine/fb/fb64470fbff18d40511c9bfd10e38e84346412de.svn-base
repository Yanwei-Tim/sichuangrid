package com.tianque.plugin.dataManage.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.rentalHouse.service.RentalHouseService;
import com.tianque.core.util.DateUtil;
import com.tianque.plugin.dataManage.location.actualHouseTemp.domain.ActualHouseTemp;

public class DataManageBaseInfoForLocationUtil {
	@Autowired
	private RentalHouseService rentalHouseService;

	/**
	 * 实有房屋（房屋信息）对比数据准备
	 * 
	 * @param temp
	 * @param target
	 * @return
	 */
	public static List getActualHouseCommonList(ActualHouseTemp temp,
			HouseInfo target) {
		List compareList = new ArrayList();
		Map map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "房屋编号");
		map.put("tempValue", "" + temp.getHouseCode());
		map.put("realValue", "" + target.getHouseCode());
		map.put("argType", "str");
		map.put("submitName", "houseCode");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "是否出租房");
		map.put("tempValue", "" + temp.getIsRentalHouse());
		map.put("realValue", "" + target.getIsRentalHouse());
		map.put("argType", "boolean");
		map.put("submitName", "isRentalHouse");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "房屋地址");
		map.put("tempValue",
				"" + temp.getAddressType().getId() + temp.getCommunity()
						+ " 小区 " + temp.getBlock() + " 幢 " + temp.getUnit()
						+ " 单元 " + temp.getRoom() + " 室 " + temp.getAddress());
		map.put("realValue",
				"" + target.getAddressType().getId() + target.getCommunity()
						+ " 小区 " + target.getBlock() + " 幢 " + target.getUnit()
						+ " 单元 " + target.getRoom() + " 室 "
						+ target.getAddress());
		map.put("argType", "strs");
		map.put("submitName", new String[] { "addressType.id", "community",
				"block", "unit", "room", "address" });
		map.put("addressType", "" + target.getAddressType().getId());
		map.put("addressTypeTemp", "" + temp.getAddressType().getId());
		map.put("community", "" + target.getCommunity());
		map.put("communityTemp", "" + temp.getCommunity());
		map.put("block", "" + target.getBlock());
		map.put("blockTemp", "" + temp.getBlock());
		map.put("unit", "" + target.getUnit());
		map.put("unitTemp", "" + temp.getUnit());
		map.put("room", "" + target.getRoom());
		map.put("roomTemp", "" + temp.getRoom());
		map.put("address", "" + target.getAddress());
		map.put("addressTemp", "" + temp.getAddress());
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "建筑物名称");
		map.put("tempValue", "" + temp.getBuildingName());
		map.put("realValue", "" + target.getBuildingName());
		map.put("argType", "str");
		map.put("submitName", "buildingName");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "建筑物用途");
		map.put("tempValue", temp.getBuildingUses() == null ? "-1" : ""
				+ temp.getBuildingUses().getId());
		map.put("realValue", target.getBuildingUses() == null ? "-1" : ""
				+ target.getBuildingUses().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@BUILDING_USES");
		map.put("argType", "PropertyDict");
		map.put("submitName", "buildingUses.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "物管单位名称");
		map.put("tempValue", "" + temp.getPropertyName());
		map.put("realValue", "" + target.getPropertyName());
		map.put("argType", "str");
		map.put("submitName", "propertyName");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "代表人/业主");
		map.put("tempValue", "" + temp.getManager());
		map.put("realValue", "" + target.getHouseOwner());
		map.put("argType", "str");
		map.put("submitName", "manager");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "身份证号码");
		map.put("tempValue", "" + temp.getHouseOwnerIdCardNo());
		map.put("realValue", "" + target.getHouseOwnerIdCardNo());
		map.put("argType", "str");
		map.put("submitName", "houseOwnerIdCardNo");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "代表人电话");
		map.put("tempValue", "" + temp.getTelephone());
		map.put("realValue", "" + target.getTelephone());
		map.put("argType", "str");
		map.put("submitName", "telephone");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "房屋户型");
		map.put("tempValue", "" + temp.getHouseDoorModel());
		map.put("realValue", "" + target.getHouseDoorModel());
		map.put("argType", "str");
		map.put("submitName", "houseDoorModel");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "建成年份");
		map.put("tempValue", "" + temp.getBuiltYear());
		map.put("realValue", "" + target.getBuiltYear());
		map.put("argType", "str");
		map.put("submitName", "builtYear");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "本户建筑面积");
		map.put("tempValue", "" + temp.getHouseArea());
		map.put("realValue", "" + target.getHouseArea());
		map.put("argType", "str");
		map.put("submitName", "houseArea");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "房屋结构");
		map.put("tempValue", temp.getHouseStructure() == null ? "-1" : ""
				+ temp.getHouseStructure().getId());
		map.put("realValue", target.getHouseStructure() == null ? "-1" : ""
				+ target.getHouseStructure().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS");
		map.put("argType", "PropertyDict");
		map.put("submitName", "houseStructure.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "房屋用途");
		map.put("tempValue", temp.getHouseUses() == null ? "-1" : ""
				+ temp.getHouseUses().getId());
		map.put("realValue", target.getHouseUses() == null ? "-1" : ""
				+ target.getHouseUses().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@HOUSE_USES");
		map.put("argType", "PropertyDict");
		map.put("submitName", "houseUses.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "房屋来源");
		map.put("tempValue", "" + temp.getHouseSource().getId()
				+ temp.getOwnProperty().getId());
		map.put("realValue", "" + target.getHouseSource().getId()
				+ target.getOwnProperty().getId());
		map.put("argType", "strs");
		map.put("submitName",
				new String[] { "houseSource.id", "ownProperty.id" });
		map.put("houseSource", "" + target.getHouseSource().getId());
		map.put("houseSourceTemp", "" + temp.getHouseSource().getId());
		map.put("ownProperty", "" + target.getOwnProperty().getId());
		map.put("ownPropertyTemp", "" + temp.getOwnProperty().getId());
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "房屋凭证");
		map.put("tempValue", temp.getHousingVouchers() == null ? "-1" : ""
				+ temp.getHousingVouchers().getId());
		map.put("realValue", target.getHousingVouchers() == null ? "-1" : ""
				+ target.getHousingVouchers().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@HOUSING_VOUCHERS");
		map.put("argType", "PropertyDict");
		map.put("submitName", "housingVouchers.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "房屋权证号");
		map.put("tempValue", "" + temp.getHouseRightNumber());
		map.put("realValue", "" + target.getHouseRightNumber());
		map.put("argType", "str");
		map.put("submitName", "houseRightNumber");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "发证时间");
		map.put("tempValue",
				"" + DateUtil.formateYMD(temp.getHouseRightNumberDate()));
		map.put("realValue",
				"" + DateUtil.formateYMD(target.getHouseRightNumberDate()));
		map.put("argType", "strDate");
		map.put("submitName", "houseRightNumberDate");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "土地凭证");
		map.put("tempValue", temp.getLandDocuments() == null ? "-1" : ""
				+ temp.getLandDocuments().getId());
		map.put("realValue", target.getLandDocuments() == null ? "-1" : ""
				+ target.getLandDocuments().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@LAND_DOCUMENTS");
		map.put("argType", "PropertyDict");
		map.put("submitName", "landDocuments.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "土地权证号");
		map.put("tempValue", "" + temp.getLandRightsNumbe());
		map.put("realValue", "" + target.getLandRightsNumbe());
		map.put("argType", "str");
		map.put("submitName", "landRightsNumbe");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "发证时间");
		map.put("tempValue",
				"" + DateUtil.formateYMD(temp.getLandRightsNumbeDate()));
		map.put("realValue",
				"" + DateUtil.formateYMD(target.getLandRightsNumbeDate()));
		map.put("argType", "strDate");
		map.put("submitName", "landRightsNumbeDate");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "产权人类型");
		map.put("tempValue", temp.getPropertyTypes() == null ? "-1" : ""
				+ temp.getPropertyTypes().getId());
		map.put("realValue", target.getPropertyTypes() == null ? "-1" : ""
				+ target.getPropertyTypes().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@PROPERTY_TYPES");
		map.put("argType", "PropertyDict");
		map.put("submitName", "propertyTypes.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "产权人姓名");
		map.put("tempValue", "" + temp.getName());
		map.put("realValue", "" + target.getName());
		map.put("argType", "str");
		map.put("submitName", "name");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "证件类型");
		map.put("tempValue", temp.getCertificateType() == null ? "-1" : ""
				+ temp.getCertificateType().getId());
		map.put("realValue", target.getCertificateType() == null ? "-1" : ""
				+ target.getCertificateType().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@LETTINGCERTIFICATE_TYPE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "certificateType.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "证件号码");
		map.put("tempValue", "" + temp.getCertificateNumbe());
		map.put("realValue", "" + target.getCertificateNumbe());
		map.put("argType", "str");
		map.put("submitName", "certificateNumbe");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "产权人电话");
		map.put("tempValue", "" + temp.getPropertyPersonTel());
		map.put("realValue", "" + target.getPropertyPersonTel());
		map.put("argType", "str");
		map.put("submitName", "propertyPersonTel");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "联系手机");
		map.put("tempValue", "" + temp.getPropertyPersonMobile());
		map.put("realValue", "" + target.getPropertyPersonMobile());
		map.put("argType", "str");
		map.put("submitName", "propertyPersonMobile");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "备注");
		map.put("tempValue", "" + temp.getRemark());
		map.put("realValue", "" + target.getRemark());
		map.put("argType", "str");
		map.put("submitName", "remark");
		compareList.add(map);
		return compareList;

	}

	/**
	 * 出租房对比数据准备
	 * 
	 * @param temp
	 * @param target
	 * @return
	 */
	// public static List getRentalHouseCommonList(ActualHouseTemp temp,
	// HouseInfo target) {
	//
	// List compareList = new ArrayList();
	// Map map = new HashMap();
	// map.put("notNull", "false");
	// map.put("label", "出租用途");
	// map.put("tempValue", temp.getUsage() == null ? "-1" : ""
	// + temp.getUsage().getId());
	// map.put("realValue", target.getUsage() == null ? "-1" : ""
	// + target.getUsage().getId());
	// map
	// .put("PropertyDict",
	// "@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_USAGE");
	// map.put("argType", "PropertyDict");
	// map.put("submitName", "usage.id");
	// compareList.add(map);
	// map = new HashMap();
	// map.put("notNull", "false");
	// map.put("label", "租赁备案证号");
	// map.put("tempValue", "" + temp.getHouseFileNum());
	// map.put("realValue", "" + target.getHouseFileNum());
	// map.put("argType", "str");
	// map.put("submitName", "houseFileNum");
	// compareList.add(map);
	// map = new HashMap();
	// map.put("notNull", "false");
	// map.put("label", "出租人类型");
	// map.put("tempValue", temp.getLessorType() == null ? "-1" : ""
	// + temp.getLessorType().getId());
	// map.put("realValue", target.getLessorType() == null ? "-1" : ""
	// + target.getLessorType().getId());
	// map.put("PropertyDict",
	// "@com.tianque.domain.property.PropertyTypes@LESSOR_TYPE");
	// map.put("argType", "PropertyDict");
	// map.put("submitName", "lessorType.id");
	// compareList.add(map);
	// map = new HashMap();
	// map.put("notNull", "true");
	// map.put("label", "出租人");
	// map.put("tempValue", "" + temp.getRentalPerson());
	// map.put("realValue", "" + target.getRentalPerson());
	// map.put("argType", "str");
	// map.put("submitName", "rentalPerson");
	// compareList.add(map);
	// map = new HashMap();
	// map.put("notNull", "false");
	// map.put("label", "证件类型");
	// map.put("tempValue", temp.getRentalCertificateType() == null ? "-1"
	// : "" + temp.getRentalCertificateType().getId());
	// map.put("realValue", target.getRentalCertificateType() == null ? "-1"
	// : "" + target.getRentalCertificateType().getId());
	// map
	// .put("PropertyDict",
	// "@com.tianque.domain.property.PropertyTypes@LETTINGCERTIFICATE_TYPE");
	// map.put("argType", "PropertyDict");
	// map.put("submitName", "rentalCertificateType.id");
	// compareList.add(map);
	// map = new HashMap();
	// map.put("notNull", "false");
	// map.put("label", "证件号码");
	// map.put("tempValue", "" + temp.getRentalCertificateNumbe());
	// map.put("realValue", "" + target.getRentalCertificateNumbe());
	// map.put("argType", "str");
	// map.put("submitName", "rentalCertificateNumbe");
	// compareList.add(map);
	// map = new HashMap();
	// map.put("notNull", "false");
	// map.put("label", "联系电话");
	// map.put("tempValue", "" + temp.getRentalTelephone());
	// map.put("realValue", "" + target.getRentalTelephone());
	// map.put("argType", "str");
	// map.put("submitName", "rentalTelephone");
	// compareList.add(map);
	// map = new HashMap();
	// map.put("notNull", "false");
	// map.put("label", "联系手机");
	// map.put("tempValue", "" + temp.getRentalMobileNumber());
	// map.put("realValue", "" + target.getRentalMobileNumber());
	// map.put("argType", "str");
	// map.put("submitName", "rentalMobileNumber");
	// compareList.add(map);
	// map = new HashMap();
	// map.put("notNull", "false");
	// map.put("label", "地址");
	// map.put("tempValue", "" + temp.getLessorAddress());
	// map.put("realValue", "" + target.getLessorAddress());
	// map.put("argType", "str");
	// map.put("submitName", "lessorAddress");
	// compareList.add(map);
	// map.put("notNull", "true");
	// map.put("label", "隐患程度");
	// map.put("tempValue", temp.getHiddenDangerLevel() == null ? "-1" : ""
	// + temp.getHiddenDangerLevel().getId());
	// map.put("realValue", target.getHiddenDangerLevel() == null ? "-1" : ""
	// + target.getHiddenDangerLevel().getId());
	// map
	// .put("PropertyDict",
	// "@com.tianque.domain.property.PropertyTypes@HIDDEN_TROUBLE_LEVEL");
	// map.put("argType", "PropertyDict");
	// map.put("submitName", "hiddenDangerLevel.id");
	// compareList.add(map);
	// map.put("notNull", "false");
	// map.put("label", "管理类别");
	// map.put("tempValue", temp.getMangerTypes() == null ? "-1" : ""
	// + temp.getMangerTypes().getId());
	// map.put("realValue", target.getMangerTypes() == null ? "-1" : ""
	// + target.getMangerTypes().getId());
	// map.put("PropertyDict",
	// "@com.tianque.domain.property.PropertyTypes@MANGER_TYPES");
	// map.put("argType", "PropertyDict");
	// map.put("submitName", "mangerTypes.id");
	// compareList.add(map);
	// map = new HashMap();
	// map.put("notNull", "false");
	// map.put("label", "出租间数");
	// map.put("tempValue", "" + temp.getRoomNumber());
	// map.put("realValue", "" + target.getRoomNumber());
	// map.put("argType", "str");
	// map.put("submitName", "roomNumber");
	// compareList.add(map);
	// map = new HashMap();
	// map.put("notNull", "false");
	// map.put("label", "出租时间");
	// map.put("tempValue", "" + DateUtil.formateYMD(temp.getLessorDate()));
	// map.put("realValue", "" + DateUtil.formateYMD(target.getLessorDate()));
	// map.put("argType", "strDate");
	// map.put("submitName", "lessorDate");
	// compareList.add(map);
	// map = new HashMap();
	// map.put("notNull", "false");
	// map.put("label", "月租金");
	// map.put("tempValue", "" + temp.getRentMonth());
	// map.put("realValue", "" + target.getRentMonth());
	// map.put("argType", "str");
	// map.put("submitName", "rentMonth");
	// compareList.add(map);
	// map = new HashMap();
	// map.put("notNull", "false");
	// map.put("label", " 是否签订治安责任保证书");
	// map.put("tempValue", "" + temp.getIsSignGuarantee());
	// map.put("realValue", "" + target.getIsSignGuarantee());
	// map.put("argType", "boolean");
	// map.put("submitName", "isSignGuarantee");
	// compareList.add(map);
	// map = new HashMap();
	// map.put("notNull", "false");
	// map.put("label", "有无安全通道");
	// map.put("tempValue", "" + temp.getIsSafetyChannel());
	// map.put("realValue", "" + target.getIsSafetyChannel());
	// map.put("argType", "boolean");
	// map.put("submitName", "isSafetyChannel");
	// compareList.add(map);
	// map = new HashMap();
	// map.put("notNull", "false");
	// map.put("label", "有无消防通道");
	// map.put("tempValue", "" + temp.getIsFireChannels());
	// map.put("realValue", "" + target.getIsFireChannels());
	// map.put("argType", "boolean");
	// map.put("submitName", "isFireChannels");
	// compareList.add(map);
	// return compareList;
	// }
}
