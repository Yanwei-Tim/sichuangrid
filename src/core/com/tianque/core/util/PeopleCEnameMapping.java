package com.tianque.core.util;

import java.util.HashMap;
import java.util.Map;

public class PeopleCEnameMapping {
	public static Map<String, String> allPeopleCEMap = new HashMap<String, String>();

	public static Map<String, String> baseInfoCEMap = new HashMap<String, String>();

	public static Map<String, String> actualPopulationCEMap = new HashMap<String, String>();
	public static Map<String, String> attentionPopulationCEMap = new HashMap<String, String>();

	public static Map<String, String> houseHoldCEMap = new HashMap<String, String>();
	public static Map<String, String> floatingPopuCEMap = new HashMap<String, String>();
	public static Map<String, String> overseaPopuCEMap = new HashMap<String, String>();

	public static Map<String, String> druggysCEMap = new HashMap<String, String>();
	public static Map<String, String> aidneedPopuCEMap = new HashMap<String, String>();
	public static Map<String, String> aidsPopulationCEMap = new HashMap<String, String>();
	public static Map<String, String> dangerousGoodsPopuCEMap = new HashMap<String, String>();
	public static Map<String, String> elderlyPeopleCEMap = new HashMap<String, String>();
	public static Map<String, String> handicappedsCEMap = new HashMap<String, String>();
	public static Map<String, String> idleYouthCEMap = new HashMap<String, String>();
	public static Map<String, String> mentalpatientsCEMap = new HashMap<String, String>();
	public static Map<String, String> unemployedPeopleCEMap = new HashMap<String, String>();
	public static Map<String, String> superiorVisitsCEMap = new HashMap<String, String>();
	public static Map<String, String> rectificativePersonCEMap = new HashMap<String, String>();
	public static Map<String, String> positiveInfosCEMap = new HashMap<String, String>();
	public static Map<String, String> otherAttentionCEMap = new HashMap<String, String>();
	public static Map<String, String> optimalObjectsCEMap = new HashMap<String, String>();
	public static Map<String, String> nurturesWomenCEMap = new HashMap<String, String>();

	public static Map<String, String> companyPlaceCEMap = new HashMap<String, String>();

	public static Map<String, String> allHouseInfo = new HashMap<String, String>();
	public static Map<String, String> allRentalHouse = new HashMap<String, String>();
	static {
		biuldBaseInfoCEMap();
		buildActualPopulationCEMap();
		buildAttentionPopulationCEMap();
		buildHouseHoldCEMap();
		buildFloatingPopuCEMap();
		buildOverseaPopuCEMap();
		buildDruggysCEMap();
		buildAidneedPopuCEMap();
		buildAidsPopulationCEMap();
		buildDangerousGoodsPopuCEMap();
		buildElderlyPeopleCEMap();
		buildHandicappedsCEMap();
		buildIdleYouthCEMap();
		buildMentalpatientsCEMap();
		buildUnemployedPeopleCEMap();
		buildSuperiorVisitsCEMap();
		buildRectificativePersonCEMap();
		buildPositiveInfosCEMap();
		biuldOtherAttentionCEMap();
		buildOptimalObjectsCEMap();
		buildNurturesWomenCEMap();
		buildAllHouseInfo();
		buildAllRentalHouse();
		buildAllPeopleCEMap();
		buildCompanyPlaceCEMap();
	}

	private static void buildAllRentalHouse() {
		allRentalHouse.put("houseFileNum", "租赁备案证");
		allRentalHouse.put("lessorType", "出租人类型");
		// allRentalHouse.put("rentalPerson", "房主姓名");
		allRentalHouse.put("rentalCertificateType", "证件类型");
		allRentalHouse.put("rentalCertificateNumbe", "证件号码");
		// allRentalHouse.put("rentalTelephone", "联系电话");
		// allRentalHouse.put("lessorAddress", "房主地址");
		allRentalHouse.put("lessorType", "出租房类别");
		// allRentalHouse.put("rentalHouseProperty", "出租房性质");
		// allRentalHouse.put("hiddenDangerLevel", "隐患程度");
		allRentalHouse.put("mangerTypes", "管理类别");
		allRentalHouse.put("registDate", "登记日期");
		allRentalHouse.put("lessorDate", "出租时间");
		allRentalHouse.put("roomNumber", "租用间数");
		allRentalHouse.put("limitPersons", "限住人数");
		allRentalHouse.put("rentMonth", "月租金");
		allRentalHouse.put("hiddenRectification", "隐患情况");
		allRentalHouse.put("isSignGuaranteeString", "是否签订治安责任保证书");
		allRentalHouse.put("isSafetyChannelString", "有无安全通道");
		allRentalHouse.put("isFireChannelsString", "有无消防通道");
	}

	private static void buildAllHouseInfo() {

		allHouseInfo.put("houseCode", "住房编号");
		allHouseInfo.put("isRentalHouse", "是否出租房");
		allHouseInfo.put("address", "房屋准确地址");
		allHouseInfo.put("houseAddress", "房产证地址");
		allHouseInfo.put("buildingName", "建筑物名称");
		allHouseInfo.put("buildingUses", "建筑物用途");
		allHouseInfo.put("propertyName", "物管单位名称");
		allHouseInfo.put("houseOwner", "代表人/业主");
		allHouseInfo.put("houseOwnerIdCardNo", "身份证号码");
		allHouseInfo.put("telephone", "代表人电话");
		allHouseInfo.put("houseDoorModel", "房屋户型");
		allHouseInfo.put("builtYear", "建成年份");
		allHouseInfo.put("houseArea", "本户建筑面积");
		allHouseInfo.put("houseStructure", "房屋结构");
		allHouseInfo.put("houseUses", "房屋用途");
		allHouseInfo.put("houseSource", "房屋来源");
		allHouseInfo.put("housingVouchers", "房屋凭证");
		allHouseInfo.put("houseRightNumber", "房屋权证号");
		allHouseInfo.put("houseRightNumberDate", "发证时间");
		allHouseInfo.put("landDocuments", "土地凭证");
		allHouseInfo.put("landRightsNumbe", "土地权证号");
		allHouseInfo.put("landRightsNumbeDate", "土地权证发证时间");
		allHouseInfo.put("propertyTypes", "产权人类型");
		allHouseInfo.put("name", "产权人姓名");
		allHouseInfo.put("certificateType", "证件类型");
		allHouseInfo.put("certificateNumbe", "证件号码");
		allHouseInfo.put("propertyPersonTel", "产权人电话");
		allHouseInfo.put("propertyPersonMobile", "联系手机");
		allHouseInfo.put("remark", "备注");

		/**
		 * 出租房
		 */
		allHouseInfo.put("usage", "出租房用途");
		allHouseInfo.put("rentalMobileNumber", "房主联系手机");
		allHouseInfo.put("rentalPerson", "房主姓名");
		allHouseInfo.put("rentalTelephone", "联系电话");
		allHouseInfo.put("lessorAddress", "房主地址");
		allHouseInfo.put("hiddenDangerLevel", "隐患程度");
		allHouseInfo.put("rentalType", "出租房类别");
		allHouseInfo.put("rentalHouseProperty", "出租房性质");
	}

	private static void biuldBaseInfoCEMap() {
		baseInfoCEMap.put("attentionPopulationType", "关注人员类型");
		baseInfoCEMap.put("name", "姓名");
		baseInfoCEMap.put("idCardNo", "身份证号");
		baseInfoCEMap.put("birthday", "出生日期");
		baseInfoCEMap.put("email", "邮箱");
		baseInfoCEMap.put("mail", "邮箱");
		baseInfoCEMap.put("unSettedTime", "未落户时间");
		baseInfoCEMap.put("isRemind", "矫正时间是否到期");
		baseInfoCEMap.put("organization", "所属网格");
		baseInfoCEMap.put("orgInternalCode", "所属责任区编号");
		baseInfoCEMap.put("mobileNumber", "手机号码");
		baseInfoCEMap.put("workUnit", "工作单位");
		baseInfoCEMap.put("houseId", "住房id");
		baseInfoCEMap.put("houseCode", "住房编号");
		baseInfoCEMap.put("currentAddressType", "常住地址类型");
		baseInfoCEMap.put("community", "小区");
		baseInfoCEMap.put("block", "幢");
		baseInfoCEMap.put("unit", "单元");
		baseInfoCEMap.put("room", "室");
		baseInfoCEMap.put("currentAddress", "常住地址");
		baseInfoCEMap.put("remark", "备注");
		baseInfoCEMap.put("telephone", "固定电话");
		baseInfoCEMap.put("imgUrl", "图像路径");
		baseInfoCEMap.put("fullPinyin", "全拼");
		baseInfoCEMap.put("simplePinyin", "简拼");
		baseInfoCEMap.put("PropertyDict gender", "性别");
		baseInfoCEMap.put("nativePlaceAddress", "户籍地详址");
		baseInfoCEMap.put("stature", "身高");
		baseInfoCEMap.put("isHaveHouse", "是否有房屋");
		baseInfoCEMap.put("noHouseReason", "无房原因");
		baseInfoCEMap.put("actualPopulationType", "实口类型");
		baseInfoCEMap.put("attentionPopulationTypeCname", "关注人员类型中文名");
		baseInfoCEMap.put("otherAddress", "临时居住");
		baseInfoCEMap.put("career", "职业");
		baseInfoCEMap.put("isDeath", "是否死亡");
		baseInfoCEMap.put("politicalBackground", "政治面貌");
		baseInfoCEMap.put("maritalState", "婚姻状况");
		baseInfoCEMap.put("usedName", "曾用名");
		baseInfoCEMap.put("nation", "民族");
		baseInfoCEMap.put("faith", "宗教信仰");
		baseInfoCEMap.put("schooling", "文化程度");
		baseInfoCEMap.put("bloodType", "血型");
		baseInfoCEMap.put("nativePoliceStation", "户籍地派出所");
		baseInfoCEMap.put("province", "省");
		baseInfoCEMap.put("city", "市");
		baseInfoCEMap.put("district", "区县");
		baseInfoCEMap.put("nativeLocation", "户籍地址");
		baseInfoCEMap.put("groupFamily", "家庭信息");
		baseInfoCEMap.put("hasServiceTeamMember", "是否有服务成员");
		baseInfoCEMap.put("lastRecordTime", "最新服务时间");
		baseInfoCEMap.put("populationType", "业务人员类型");
		baseInfoCEMap.put("baseInfoId", "基础信息ID");
		baseInfoCEMap.put("addressInfoId", "地址信息ID");
		baseInfoCEMap.put("fbusinessRemark", "备注");
		baseInfoCEMap.put("qbusinessRemark", "备注");
		baseInfoCEMap.put("mbusinessRemark", "备注");
	}

	private static void buildActualPopulationCEMap() {
		actualPopulationCEMap.put("workSituation", "工作情况");
		actualPopulationCEMap.put("logOut", "是否注销");
	}

	private static void buildAttentionPopulationCEMap() {
		attentionPopulationCEMap.put("isEmphasis", "是否关注");
		attentionPopulationCEMap.put("attentionExtent", "关注程度");
	}

	private static void buildHouseHoldCEMap() {
		houseHoldCEMap.put("populationtype", "业务类型");
		houseHoldCEMap.put("actualtype", "实口类型");
		houseHoldCEMap.put("relationShipWithHead", "与户主关系");
		houseHoldCEMap.put("relationShipWithHeadText", "户主关系备注");
		houseHoldCEMap.put("censusRegisterFamily", "户籍家庭");
		houseHoldCEMap.put("outGone", "是否外出");
		houseHoldCEMap.put("outReasons", "外出原因");
		houseHoldCEMap.put("reasonsDate", "外出时间");
		houseHoldCEMap.put("outProvince", "外出去向(省)");
		houseHoldCEMap.put("outCity", "外出去向(市)");
		houseHoldCEMap.put("outDistrict", "外出去向(县)");
		houseHoldCEMap.put("goOutDetailedAddress", "外出详址");
		houseHoldCEMap.put("accountNumber", "户口号");
		houseHoldCEMap.put("homePhone", "家庭电话");
		houseHoldCEMap.put("residentStatus", "人户状态");
		houseHoldCEMap.put("houseMarchType", "家庭称呼");
		houseHoldCEMap.put("settleTime", "落户时间");
		houseHoldCEMap.put("residenceType", "户口类别");
	}

	private static void buildFloatingPopuCEMap() {
		floatingPopuCEMap.put("populationtypes", "实口类型");
		floatingPopuCEMap.put("actualtype", "实口类型");
		floatingPopuCEMap.put("isInflowing", "是否流入");
		floatingPopuCEMap.put("inflowingReason", "流入原因");
		floatingPopuCEMap.put("inflowingDate", "流入时间");
		floatingPopuCEMap.put("inflowingProvince", "流入来源省");
		floatingPopuCEMap.put("inflowingCity", "流入来源市");
		floatingPopuCEMap.put("inflowingDistrict", "流入来源县");
		floatingPopuCEMap.put("registrationType", "登记证情况");
		floatingPopuCEMap.put("registerDate", "登记日期");
		floatingPopuCEMap.put("expectedDatedue", "预计到期日期");
		floatingPopuCEMap.put("certificateNumber", "证件编号");
		floatingPopuCEMap.put("stayLocationType", "暂住处所");
		floatingPopuCEMap.put("economySource", "经济来源");
		floatingPopuCEMap.put("stayTimeLimit", "居住时限");
		floatingPopuCEMap.put("preparedStayTimeLimit", "预居住时限");
		floatingPopuCEMap.put("hasMarriedProve", "是否有婚育证明");
		floatingPopuCEMap.put("settleTime", "转为流动人口的时间");
	}

	private static void buildOverseaPopuCEMap() {
		overseaPopuCEMap.put("actualType", "实口类型");
		overseaPopuCEMap.put("nationality", "国籍");
		overseaPopuCEMap.put("certificateType", "证件种类");
		overseaPopuCEMap.put("certificateNo", "证件号码");
		overseaPopuCEMap.put("arrivalTime", "抵达时间");
		overseaPopuCEMap.put("leaveTime", "离开时间");
		overseaPopuCEMap.put("logOut", "是否注销");
		overseaPopuCEMap.put("profession", "职业");
		overseaPopuCEMap.put("englishName", "英文名");
		overseaPopuCEMap.put("certificateStrartDay", "证件有效期开始时间");
		overseaPopuCEMap.put("certificateEndDay", "证件有效期结束时间");
		overseaPopuCEMap.put("inflowReason", "流入原因");
	}

	private static void buildDruggysCEMap() {
		druggysCEMap.put("drugReason", "吸毒原因");
		druggysCEMap.put("drugSource", "毒品来源");
		druggysCEMap.put("detoxicateCase", "戒毒情况");
		druggysCEMap.put("detoxicateCondition", "吸毒状态");
		druggysCEMap.put("isAdanon", "是否服美沙酮戒毒");
		druggysCEMap.put("controlActuality", "管控现状");
		druggysCEMap.put("drugType", "滥用毒品种类");
		druggysCEMap.put("ferretOutDate", "查获日期");
		druggysCEMap.put("drugFristDate", "首吸时间");
		druggysCEMap.put("isUndergoTreatment", "目前是否在接受治疗");
	}

	private static void buildAidneedPopuCEMap() {
		aidneedPopuCEMap.put("aidReason", "救助原因");
		aidneedPopuCEMap.put("isLowHouseholds", "是否低保户");
		aidneedPopuCEMap.put("difficultCardNo", "困难证号");
		aidneedPopuCEMap.put("difficultType", "困难类型");
		aidneedPopuCEMap.put("subsidiesAmount", "领取金额");
		aidneedPopuCEMap.put("subsidiesGetTime", "领取时间");
		aidneedPopuCEMap.put("subsidiesStartTime", "享受起始时间");
		aidneedPopuCEMap.put("subsidiesPopulation", "享受人数");
		aidneedPopuCEMap.put("subsidiesArea", "享受地区");
	}

	private static void buildAidsPopulationCEMap() {
		aidsPopulationCEMap.put("infectway", "感染途径");
		aidsPopulationCEMap.put("violationsofthelaw", "违法情况");
		aidsPopulationCEMap.put("crimetype", "犯罪类型");
		aidsPopulationCEMap.put("receivedlevel", "收治机构所属层级");
		aidsPopulationCEMap.put("helpcircumstances", "帮扶情况");
		aidsPopulationCEMap.put("addressno", "地址编号");
		aidsPopulationCEMap.put("receivedorganization", "收治机构");
	}

	private static void buildDangerousGoodsPopuCEMap() {
		dangerousGoodsPopuCEMap.put("dangerousWorkingType", "危险从业类别");
		dangerousGoodsPopuCEMap.put("legalPerson", "法人");
		dangerousGoodsPopuCEMap.put("legalPersonTelephone", "法人电话");
		dangerousGoodsPopuCEMap.put("legalPersonMobileNumber", "法人手机");
		dangerousGoodsPopuCEMap.put("workingCertificate", "从业资格证");
		dangerousGoodsPopuCEMap.put("workingCertificateNo", "从业资格证号");
		dangerousGoodsPopuCEMap.put("periodOfValidityStart", "有效开始日期");
		dangerousGoodsPopuCEMap.put("periodOfValidityEnd", "有效结束日期");
	}

	private static void buildElderlyPeopleCEMap() {
		elderlyPeopleCEMap.put("elderPeopleId", "老年人证号");
		elderlyPeopleCEMap.put("peopleType", "人员类型");
		elderlyPeopleCEMap.put("currentStatus", "目前情况");
		elderlyPeopleCEMap.put("liveStatus", "居住情况");
		elderlyPeopleCEMap.put("spouseStatus", "配偶情况");
		elderlyPeopleCEMap.put("incomeSource", "经济来源");
		elderlyPeopleCEMap.put("enterWorkDate", "参加工作日期");
		elderlyPeopleCEMap.put("retireUnitAndDuty", "离退休单位");
		elderlyPeopleCEMap.put("retireDate", "离退休日期");
		elderlyPeopleCEMap.put("zhiwu", "原单位职务");
		elderlyPeopleCEMap.put("pension", "退休金");
	}

	private static void buildHandicappedsCEMap() {
		handicappedsCEMap.put("guardianName", "监护人");
		handicappedsCEMap.put("disabilityCardNo", "残疾证号");
		handicappedsCEMap.put("disabilityReason", "残疾原因");
		handicappedsCEMap.put("disability", "残疾等级");
		handicappedsCEMap.put("disabilitys", "残疾等级");
		handicappedsCEMap.put("disabilityType", "残疾类别");
		handicappedsCEMap.put("disabilityTypes", "残疾类别");
		handicappedsCEMap.put("skillProfile", "技能特长");
		handicappedsCEMap.put("workProfile", "劳动能力");
		handicappedsCEMap.put("hadDisabilityCard", "是否有残疾证");
		handicappedsCEMap.put("disabilityDate", "残疾时间");
		handicappedsCEMap.put("disabilityIntellect", "智力等级");
		handicappedsCEMap.put("disabilitysLimbs", "肢体残疾等级");
		handicappedsCEMap.put("disabilitysMental", "精神残疾等级");
		handicappedsCEMap.put("disabilitysHearing", "听力残疾等级");//
		handicappedsCEMap.put("disabilitysSpeech", "言语残疾等级");
		handicappedsCEMap.put("disabilitysVision", "视力残疾等级");
		handicappedsCEMap.put("disabilityTypeIds", "残疾类别");
		handicappedsCEMap.put("isabilityLevelIds", "残疾等级");
	}

	private static void buildIdleYouthCEMap() {
		idleYouthCEMap.put("staffType", "人员类型");
		idleYouthCEMap.put("workCondition", "工作情况");
		idleYouthCEMap.put("isStayInSchool", "是否在校住宿");
		idleYouthCEMap.put("schoolName", "学校名称");
	}

	private static void buildMentalpatientsCEMap() {
		mentalpatientsCEMap.put("psychosisName", "患病名称");
		mentalpatientsCEMap.put("dangerLevel", "发病危险等级");
		mentalpatientsCEMap.put("isTreat", "是否接受过精神病治疗");
		mentalpatientsCEMap.put("cureDepartment", "康复机构");
		mentalpatientsCEMap.put("guardian", "监护人");
		mentalpatientsCEMap.put("guardianTelephone", "监护人固定电话");
		mentalpatientsCEMap.put("guardianMobileNumber", "监护人手机号码");
		mentalpatientsCEMap.put("businessRemark", "业务信息备注");
		mentalpatientsCEMap.put("isUndergoTreatment", "目前是否在接受治疗");
		mentalpatientsCEMap.put("psychosisType", "精神病类型");
		mentalpatientsCEMap.put("treatmentState", "治疗状态");
		mentalpatientsCEMap.put("diseaseTime", "发病时间");
		mentalpatientsCEMap.put("recoveryTime", "康复时间");
	}

	private static void buildUnemployedPeopleCEMap() {
		unemployedPeopleCEMap.put("unemployedPeopleType", "人员类型");
		unemployedPeopleCEMap.put("registerNumber", "就业/失业登记证号");
		unemployedPeopleCEMap.put("upEnterWorkDate", "参加工作时间");
		unemployedPeopleCEMap.put("originalWorkUnit", "原工作单位");
		unemployedPeopleCEMap.put("originalWorkUnitType", "原工作单位类型");
		unemployedPeopleCEMap.put("title", "职称");
		unemployedPeopleCEMap.put("unemploymentDate", "失业时间");
		unemployedPeopleCEMap.put("technologyLevel", "技术等级");
		unemployedPeopleCEMap.put("unemploymentReason", "失业原因");
		unemployedPeopleCEMap.put("specialtySkills", "特长技能");
		unemployedPeopleCEMap.put("employmentIntention", "拟就业意向");
		unemployedPeopleCEMap.put("trainingIntention", "拟参加培训意向");
		unemployedPeopleCEMap.put("participatedPrograms", "参加过的培训项目及时间");
	}

	private static void buildSuperiorVisitsCEMap() {
		superiorVisitsCEMap.put("visitState", "上访状态");
		superiorVisitsCEMap.put("visitType", "上访类型");
		superiorVisitsCEMap.put("visitReason", "上访原因");
		superiorVisitsCEMap.put("visitTypes", "上访类型子类型");
		superiorVisitsCEMap.put("typeName", "上访类型子类型名");
	}

	private static void buildRectificativePersonCEMap() {
		rectificativePersonCEMap.put("executeType", "刑法执行类别");
		rectificativePersonCEMap.put("penaltyTerm", "原判刑期");
		rectificativePersonCEMap.put("recentSituation", "近况描述");
		rectificativePersonCEMap.put("rectifyStartDate", "社区矫正开始时间");
		rectificativePersonCEMap.put("rectifyEndDate", "社区矫正结束时间");
		rectificativePersonCEMap.put("nativePoliceStation", "户籍派出所");
		rectificativePersonCEMap.put("helpEducator", "帮教人员");
		rectificativePersonCEMap.put("educatorTelephone", "帮教人员电话");
		rectificativePersonCEMap.put("educatorMobileNumber", "帮教人员手机号码");
		rectificativePersonCEMap.put("bussinessRemark", "备注");
		rectificativePersonCEMap.put("accusation", "罪名");
	}

	private static void buildPositiveInfosCEMap() {
		positiveInfosCEMap.put("releaseOrBackDate", "解教（刑释）日期");
		positiveInfosCEMap.put("isRepeat", "是否累犯");
		positiveInfosCEMap.put("resettlementDate", "安置时间");
		positiveInfosCEMap.put("householdId", "户号");
		positiveInfosCEMap.put("nativePlace", "籍贯");
		positiveInfosCEMap.put("laborEduAddress", "劳教(服刑)场所");
		positiveInfosCEMap.put("imprisonmentDate", "刑期");
		positiveInfosCEMap.put("caseReason", "原罪(错)");
		positiveInfosCEMap.put("agoProfession", "原职业");
		positiveInfosCEMap.put("noResettlementReason", "未安置原因");
		positiveInfosCEMap.put("helpEducator", "帮教人员");
		positiveInfosCEMap.put("educatorTelephone", "帮教人员电话");
		positiveInfosCEMap.put("educatorMobileNumber", "帮教人员手机号码");
		positiveInfosCEMap.put("positiveInfoType", "人员类型");
		positiveInfosCEMap.put("isExpired", "是否过期");
		positiveInfosCEMap.put("isCrime", "本年度是否重犯");
		positiveInfosCEMap.put("crimeDate", "重犯日期");
		positiveInfosCEMap.put("criminalBehavior", "犯罪行为");
	}

	private static void biuldOtherAttentionCEMap() {
		otherAttentionCEMap.put("attentionReason", "关注原因");
		otherAttentionCEMap.put("workCondition", "工作情况");
		otherAttentionCEMap.put("meRemark", "评论");
	}

	private static void buildOptimalObjectsCEMap() {
		optimalObjectsCEMap.put("optimalCardNo", "优待证号");
		optimalObjectsCEMap.put("optimalCardType", "优抚类型");
		optimalObjectsCEMap.put("laborAbility", "劳动能力");
		optimalObjectsCEMap.put("abilityLiving", "生活能力");
		optimalObjectsCEMap.put("employmentSituation", "就业情况");
		optimalObjectsCEMap.put("supportSituation", "供养情况");
		optimalObjectsCEMap.put("monthLivingExpenses", "月生活费");
	}

	private static void buildNurturesWomenCEMap() {
		nurturesWomenCEMap.put("firstMarriageTime", "初婚时间");
		nurturesWomenCEMap.put("marriageRegistrationTime", "离婚时间");
		nurturesWomenCEMap.put("licenseSituation", "领证情况");
		nurturesWomenCEMap.put("marriageOrDivorceTime", "最近再婚时间");
		nurturesWomenCEMap.put("fertilityServicesNo", "生育服务证号");
		nurturesWomenCEMap.put("licenseTime", "领证时间");
		nurturesWomenCEMap.put("contraceptiveMethod", "避孕方法");
		nurturesWomenCEMap.put("contraceptiveTime", "避孕起始时间");
		nurturesWomenCEMap.put("boyNumber", "男孩数");
		nurturesWomenCEMap.put("girlNumber", "女孩数");
		nurturesWomenCEMap.put("onechildOfCouple", "夫妻双方独生子女情况");
		nurturesWomenCEMap.put("manIdcardNo", "男方身份证号");
		nurturesWomenCEMap.put("manName", "男方姓名");
		nurturesWomenCEMap.put("manMaritalState", "男方婚姻状况");
		nurturesWomenCEMap.put("manFirstMarriageTime", "男方初婚时间");
		nurturesWomenCEMap.put("manMobileNumber", "男方联系手机");
		nurturesWomenCEMap.put("manTelephone", "男方固定电话");
		nurturesWomenCEMap.put("manBirthday", "男方出生日期");
		nurturesWomenCEMap.put("manNation", "男方民族");
		nurturesWomenCEMap.put("manPoliticalBackground", "男方政治面貌");
		nurturesWomenCEMap.put("manSchooling", "男方文化程度");
		nurturesWomenCEMap.put("manResidenceType", "男方户口类型");
		nurturesWomenCEMap.put("manCareer", "男方职业");
		nurturesWomenCEMap.put("manWorkUnit", "男方工作单位");
		nurturesWomenCEMap.put("manProvince", "男方户籍地省");
		nurturesWomenCEMap.put("manCity", "男方户籍地市");
		nurturesWomenCEMap.put("manDistrict", "男方户籍地区县");
		nurturesWomenCEMap.put("manNativeplaceAddress", "男方户籍地详址");
		nurturesWomenCEMap.put("manCurrentAddress", "男方常住地址");
		nurturesWomenCEMap.put("manCurrentAddressType", "男方常住地址类型");
		nurturesWomenCEMap.put("manCommunity", "男方常住地址商品房小区");
		nurturesWomenCEMap.put("manBlock", "男方常住地址商品房幢");
		nurturesWomenCEMap.put("manUnit", "男方常住地址商品房单元");
		nurturesWomenCEMap.put("manRoom", "男方常住地址商品房室");
		nurturesWomenCEMap.put("manRemark", "男方备注");
		nurturesWomenCEMap.put("singleChildCardno", "独生子女证号");
		nurturesWomenCEMap.put("singleChildCDIssueTime", "独生子女证号领取时间");
		nurturesWomenCEMap.put("certifiedUnit", "发证单位生育服务证");
		nurturesWomenCEMap.put("isUnmarriedPregnant", "是否未婚先孕");
		nurturesWomenCEMap.put("isLevied", "是否征收");
		nurturesWomenCEMap.put("isMaternityCard", "是否有婚育证");
		nurturesWomenCEMap.put("maternityCardUnit", "发证单位");
		nurturesWomenCEMap.put("maternityCardNo", "证编号");
		nurturesWomenCEMap.put("maternityCardIssueTime", "发放时间");
		nurturesWomenCEMap.put("maternityCardValidityTime", "有效期");
		nurturesWomenCEMap.put("maternityCardCheckTime", "查验时间");
		nurturesWomenCEMap.put("maternityCardRemark", "查验情况");
	}

	private static void buildAllPeopleCEMap() {
		allPeopleCEMap.putAll(baseInfoCEMap);
		allPeopleCEMap.putAll(actualPopulationCEMap);
		allPeopleCEMap.putAll(attentionPopulationCEMap);
		allPeopleCEMap.putAll(houseHoldCEMap);
		allPeopleCEMap.putAll(floatingPopuCEMap);
		allPeopleCEMap.putAll(overseaPopuCEMap);
		allPeopleCEMap.putAll(druggysCEMap);
		allPeopleCEMap.putAll(aidneedPopuCEMap);
		allPeopleCEMap.putAll(aidsPopulationCEMap);
		allPeopleCEMap.putAll(dangerousGoodsPopuCEMap);
		allPeopleCEMap.putAll(elderlyPeopleCEMap);
		allPeopleCEMap.putAll(handicappedsCEMap);
		allPeopleCEMap.putAll(idleYouthCEMap);
		allPeopleCEMap.putAll(mentalpatientsCEMap);
		allPeopleCEMap.putAll(unemployedPeopleCEMap);
		allPeopleCEMap.putAll(superiorVisitsCEMap);
		allPeopleCEMap.putAll(rectificativePersonCEMap);
		allPeopleCEMap.putAll(positiveInfosCEMap);
		allPeopleCEMap.putAll(otherAttentionCEMap);
		allPeopleCEMap.putAll(optimalObjectsCEMap);
		allPeopleCEMap.putAll(nurturesWomenCEMap);
		allPeopleCEMap.putAll(allHouseInfo);
		allPeopleCEMap.putAll(allRentalHouse);
	}

	private static void buildCompanyPlaceCEMap() {
		companyPlaceCEMap.put("name", "名称");
		companyPlaceCEMap.put("attentionextent", "关注度");
		companyPlaceCEMap.put("address", "地址");
		companyPlaceCEMap.put("centerLon", "经度(三维)");
		companyPlaceCEMap.put("centerLat", "纬度(三维)");
		companyPlaceCEMap.put("centerLon2", "经度(二维)");
		companyPlaceCEMap.put("centerLat2", "纬度(二维)");
		companyPlaceCEMap.put("featureId", "热点");
		companyPlaceCEMap.put("buildDataId", "楼宇");
		companyPlaceCEMap.put("type", "一级类型");
		companyPlaceCEMap.put("classifiCation", "二级类型");
		companyPlaceCEMap.put("category", "三级类型");
		companyPlaceCEMap.put("userName", "负责人");
		companyPlaceCEMap.put("mobileNumber", "联系手机");
		companyPlaceCEMap.put("telePhone", "联系电话");
		companyPlaceCEMap.put("faxNumber", "传真号码");
		companyPlaceCEMap.put("area", "所在区域");
		companyPlaceCEMap.put("coveredArea", "占地面积");
		companyPlaceCEMap.put("remarks", "备注");
		companyPlaceCEMap.put("hasLicense", "是否有执照");
		companyPlaceCEMap.put("businessLicenseNo", "执照号码");
		companyPlaceCEMap.put("orgCode", "组织机构码");
		companyPlaceCEMap.put("cloakRoom", "小件寄存处");
		companyPlaceCEMap.put("scaleType", "规模类型");
		companyPlaceCEMap.put("partyCountNumber", "党员数");
		companyPlaceCEMap.put("networkCardNo", "临时上网卡数");
		companyPlaceCEMap.put("scaleType", "规模类型");
		companyPlaceCEMap.put("registeredCapital", "注册资金");
		companyPlaceCEMap.put("registeredCapitalNo", "注册资本");
		companyPlaceCEMap.put("begintime", "开始时间");
		companyPlaceCEMap.put("endtime", "结束时间");
		companyPlaceCEMap.put("businessArea", "营业面积");
		companyPlaceCEMap.put("registeredCapitalNo", "注册资本");
		companyPlaceCEMap.put("digVolume", "施工挖方量");
		companyPlaceCEMap.put("fillVolume", "施工填方量");
		companyPlaceCEMap.put("email", "电子邮箱");
		companyPlaceCEMap.put("hospitalNature", "医院性质");
		companyPlaceCEMap.put("generalStorage", "存储设备");
		companyPlaceCEMap.put("generalType", "货物类别");
		companyPlaceCEMap.put("faxNo", "传真号码");
		companyPlaceCEMap.put("nowip", "现在使用IP地址");
		companyPlaceCEMap.put("countNo", "从业人数/电脑台数");
		companyPlaceCEMap.put("generalManage", "网络文化经营许可证号/主管部门");
		companyPlaceCEMap.put("generalMente", "网络安全审核意见书号/管理部门");
		companyPlaceCEMap.put("fireAuditOpinionNo", "消防审核意见书号");
		companyPlaceCEMap.put("internetBarNo", "网吧编号");
		companyPlaceCEMap.put("legalVicePrincipal", "法制副校长");
		companyPlaceCEMap.put("schoolNature", "学校性质");
		companyPlaceCEMap.put("schoolNameEn", "学校英文名称");
		companyPlaceCEMap.put("schoolWebSite", "学校网址");
		companyPlaceCEMap.put("economicNature", "经济性质");
		companyPlaceCEMap.put("managementLevel", "管理等级");
		companyPlaceCEMap.put("fireLevel", "消防等级");
		// companyPlaceCEMap.put("isRentalHouse", "有无治安人");
		// companyPlaceCEMap.put("lastRecordTime", "最新巡场时间");
		companyPlaceCEMap.put("hiddangerInfo", "隐患情况");
		companyPlaceCEMap.put("correctionInfo", "隐患整改情况");
		companyPlaceCEMap.put("listedCorrection", "挂牌整治");
		companyPlaceCEMap.put("listedLeve", "挂牌等级");
		companyPlaceCEMap.put("correctionBeiginDate", "整改开始时间");
		companyPlaceCEMap.put("correctionEndDate", "整改结束时间");
		companyPlaceCEMap.put("effectassessment", "效果评估");
		companyPlaceCEMap.put("pollutionType", "污染类型");
		companyPlaceCEMap.put("pollutionInfo", "污染情况 ");
	}
}
