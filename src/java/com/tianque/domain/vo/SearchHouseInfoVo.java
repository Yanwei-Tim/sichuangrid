package com.tianque.domain.vo;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class SearchHouseInfoVo extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private String orgInternalCode;

	/** 住房编号 */
	private String houseCode;

	/** 常住地址 */
	private String address;

	/** 产权人类型 */
	private PropertyDict propertyTypes;

	/** 产权人姓名 */
	private String name;

	/** 产权人姓名全拼 */
	private String fullPinyin;

	/** 产权人姓名简拼 */
	private String simplePinyin;

	/** 产权人证件类型 */
	private PropertyDict certificateType;

	/** 产权人证件号码 */
	private String certificateNumbe;

	/** 房屋用途 */
	private PropertyDict houseUses;

	/** 房屋结构 */
	private PropertyDict houseStructure;

	/** 建成开始年份 */
	private String builtYearFrom;

	/** 建成结束年份 */
	private String builtYearEnd;

	/** 本户建筑面积从 */
	private Double houseAreaFrom;

	/** 本户建筑面积到 */
	private Double houseAreaEnd;

	/** 是否是出租房 */
	private Long isRentalHouse;
	private Long houseId;

	/** 建筑物名称 */
	private String buildingName;

	/** 建筑物用途 */
	private PropertyDict buildingUses;

	/** 物业管理单位名称 */
	private String propertyName;

	/** 房屋来源 */
	private PropertyDict houseSource;

	/** 自有产权 */
	private PropertyDict ownProperty;

	/** 租赁公房 */
	private PropertyDict rentalBuildings;

	/** 房屋凭证 */
	private PropertyDict housingVouchers;

	/** 房屋权证号 */
	private String houseRightNumber;

	/** 房屋权证发证时间从 */
	private Date houseRightNumberDateFrom;

	/** 房屋权证发证时间到 */
	private Date houseRightNumberDateEnd;

	/** 土地凭证 */
	private PropertyDict landDocuments;

	/** 土地权证号 */
	private String landRightsNumbe;

	/** 土地权证发证时间从 */
	private Date landRightsNumbeDateFrom;

	/** 土地权证发证时间到 */
	private Date landRightsNumbeDateEnd;

	/** 隐患等级 */
	private PropertyDict hiddenDangerLevel;

	/** 出租房用途 */
	private PropertyDict usage;

	/** 出租人类型 */
	private PropertyDict lessorType;

	/** 出租人 */
	private String rentalPerson;

	/** 租赁备案证号 */
	private String houseFileNum;

	/** 管理类别 */
	private PropertyDict mangerTypes;

	/** 出租时间从 */
	private Date lessorDateFrom;

	/** 出租时间到 */
	private Date lessorDateEnd;

	/** 出租间数从 */
	private Long roomNumberFrom;

	/** 出租间数到 */
	private Long roomNumberEnd;

	/** 月租金从 */
	private Long rentMonthFrom;

	/** 月租金到 */
	private Long rentMonthEnd;

	/** 有无消防通道 */
	private Long isFireChannels;

	/** 有无安全通道 */
	private Long isSafetyChannel;

	/** 是否签订治安责任保证书 */
	private Long isSignGuarantee;

	private Organization organization;

	private String sortField;
	private String order;
	/** 是否注销 */
	private Long isEmphasis;
	/** 房屋编号和地址 */
	private String houseCodeAndAddress;
	/** 房屋居住人数 */
	private Integer memberNum;
	/** 是否有服务成员 */
	private Long hasServiceTeamMember;
	/** 是否有服务记录 */
	private Long hasServiceTeamRecord;

	/** 房间数（出租间数） */
	private Long roomNumber;

	/** 出租房性质 */
	// 由于前面的开发人员往数据库里插入数据时，“出租房性质”的值是保存在rentalProperty（出租房属性）字段中而非lettingHouseProperty（出租房性质）
	// 所以增加此查询条件时，此处只能用rentalProperty
	private PropertyDict rentalProperty;

	/** 出租房类别 */
	private PropertyDict type;

	/** 限制人数从 */
	private Long limitPersonsFrom;

	/** 限制人数到 */
	private Long limitPersonsTo;

	/** 实住人数 从 */
	private Long realityPersonsFrom;

	/** 实住人数 到 */
	private Long realityPersonsTo;

	/** 登记日期 */
	private String registerDate;

	/** 区分是否快速搜索 */
	private Boolean isFastSearch = false;

	public Long getRentMonthFrom() {
		return rentMonthFrom;
	}

	public void setRentMonthFrom(Long rentMonthFrom) {
		this.rentMonthFrom = rentMonthFrom;
	}

	public Long getRentMonthEnd() {
		return rentMonthEnd;
	}

	public void setRentMonthEnd(Long rentMonthEnd) {
		this.rentMonthEnd = rentMonthEnd;
	}

	public PropertyDict getHiddenDangerLevel() {
		return hiddenDangerLevel;
	}

	public void setHiddenDangerLevel(PropertyDict hiddenDangerLevel) {
		this.hiddenDangerLevel = hiddenDangerLevel;
	}

	public PropertyDict getUsage() {
		return usage;
	}

	public void setUsage(PropertyDict usage) {
		this.usage = usage;
	}

	public PropertyDict getLessorType() {
		return lessorType;
	}

	public void setLessorType(PropertyDict lessorType) {
		this.lessorType = lessorType;
	}

	public String getRentalPerson() {
		return rentalPerson;
	}

	public void setRentalPerson(String rentalPerson) {
		this.rentalPerson = rentalPerson;
	}

	public String getHouseFileNum() {
		return houseFileNum;
	}

	public void setHouseFileNum(String houseFileNum) {
		this.houseFileNum = houseFileNum;
	}

	public PropertyDict getMangerTypes() {
		return mangerTypes;
	}

	public void setMangerTypes(PropertyDict mangerTypes) {
		this.mangerTypes = mangerTypes;
	}

	public Date getLessorDateFrom() {
		return lessorDateFrom;
	}

	public void setLessorDateFrom(Date lessorDateFrom) {
		this.lessorDateFrom = lessorDateFrom;
	}

	public Date getLessorDateEnd() {
		return lessorDateEnd;
	}

	public void setLessorDateEnd(Date lessorDateEnd) {
		this.lessorDateEnd = lessorDateEnd;
	}

	public Long getRoomNumberFrom() {
		return roomNumberFrom;
	}

	public void setRoomNumberFrom(Long roomNumberFrom) {
		this.roomNumberFrom = roomNumberFrom;
	}

	public Long getRoomNumberEnd() {
		return roomNumberEnd;
	}

	public void setRoomNumberEnd(Long roomNumberEnd) {
		this.roomNumberEnd = roomNumberEnd;
	}

	public Long getIsFireChannels() {
		return isFireChannels;
	}

	public void setIsFireChannels(Long isFireChannels) {
		this.isFireChannels = isFireChannels;
	}

	public Long getIsSafetyChannel() {
		return isSafetyChannel;
	}

	public void setIsSafetyChannel(Long isSafetyChannel) {
		this.isSafetyChannel = isSafetyChannel;
	}

	public Long getIsSignGuarantee() {
		return isSignGuarantee;
	}

	public void setIsSignGuarantee(Long isSignGuarantee) {
		this.isSignGuarantee = isSignGuarantee;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public PropertyDict getPropertyTypes() {
		return propertyTypes;
	}

	public void setPropertyTypes(PropertyDict propertyTypes) {
		this.propertyTypes = propertyTypes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}

	public PropertyDict getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(PropertyDict certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNumbe() {
		return certificateNumbe;
	}

	public void setCertificateNumbe(String certificateNumbe) {
		this.certificateNumbe = certificateNumbe;
	}

	public PropertyDict getHouseUses() {
		return houseUses;
	}

	public void setHouseUses(PropertyDict houseUses) {
		this.houseUses = houseUses;
	}

	public PropertyDict getHouseStructure() {
		return houseStructure;
	}

	public void setHouseStructure(PropertyDict houseStructure) {
		this.houseStructure = houseStructure;
	}

	public String getBuiltYearFrom() {
		return builtYearFrom;
	}

	public void setBuiltYearFrom(String builtYearFrom) {
		this.builtYearFrom = builtYearFrom;
	}

	public String getBuiltYearEnd() {
		return builtYearEnd;
	}

	public void setBuiltYearEnd(String builtYearEnd) {
		this.builtYearEnd = builtYearEnd;
	}

	public Double getHouseAreaFrom() {
		return houseAreaFrom;
	}

	public void setHouseAreaFrom(Double houseAreaFrom) {
		this.houseAreaFrom = houseAreaFrom;
	}

	public Double getHouseAreaEnd() {
		return houseAreaEnd;
	}

	public void setHouseAreaEnd(Double houseAreaEnd) {
		this.houseAreaEnd = houseAreaEnd;
	}

	public Long getIsRentalHouse() {
		return isRentalHouse;
	}

	public void setIsRentalHouse(Long isRentalHouse) {
		this.isRentalHouse = isRentalHouse;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public PropertyDict getBuildingUses() {
		return buildingUses;
	}

	public void setBuildingUses(PropertyDict buildingUses) {
		this.buildingUses = buildingUses;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public PropertyDict getOwnProperty() {
		return ownProperty;
	}

	public void setOwnProperty(PropertyDict ownProperty) {
		this.ownProperty = ownProperty;
	}

	public PropertyDict getRentalBuildings() {
		return rentalBuildings;
	}

	public void setRentalBuildings(PropertyDict rentalBuildings) {
		this.rentalBuildings = rentalBuildings;
	}

	public PropertyDict getHousingVouchers() {
		return housingVouchers;
	}

	public void setHousingVouchers(PropertyDict housingVouchers) {
		this.housingVouchers = housingVouchers;
	}

	public String getHouseRightNumber() {
		return houseRightNumber;
	}

	public void setHouseRightNumber(String houseRightNumber) {
		this.houseRightNumber = houseRightNumber;
	}

	public Date getHouseRightNumberDateFrom() {
		return houseRightNumberDateFrom;
	}

	public void setHouseRightNumberDateFrom(Date houseRightNumberDateFrom) {
		this.houseRightNumberDateFrom = houseRightNumberDateFrom;
	}

	public Date getHouseRightNumberDateEnd() {
		return houseRightNumberDateEnd;
	}

	public void setHouseRightNumberDateEnd(Date houseRightNumberDateEnd) {
		this.houseRightNumberDateEnd = houseRightNumberDateEnd;
	}

	public PropertyDict getLandDocuments() {
		return landDocuments;
	}

	public void setLandDocuments(PropertyDict landDocuments) {
		this.landDocuments = landDocuments;
	}

	public String getLandRightsNumbe() {
		return landRightsNumbe;
	}

	public void setLandRightsNumbe(String landRightsNumbe) {
		this.landRightsNumbe = landRightsNumbe;
	}

	public Date getLandRightsNumbeDateFrom() {
		return landRightsNumbeDateFrom;
	}

	public void setLandRightsNumbeDateFrom(Date landRightsNumbeDateFrom) {
		this.landRightsNumbeDateFrom = landRightsNumbeDateFrom;
	}

	public Date getLandRightsNumbeDateEnd() {
		return landRightsNumbeDateEnd;
	}

	public void setLandRightsNumbeDateEnd(Date landRightsNumbeDateEnd) {
		this.landRightsNumbeDateEnd = landRightsNumbeDateEnd;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public PropertyDict getHouseSource() {
		return houseSource;
	}

	public void setHouseSource(PropertyDict houseSource) {
		this.houseSource = houseSource;
	}

	public String getHouseCodeAndAddress() {
		return houseCodeAndAddress;
	}

	public void setHouseCodeAndAddress(String houseCodeAndAddress) {
		this.houseCodeAndAddress = houseCodeAndAddress;
	}

	public Integer getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
	}

	public Long getHasServiceTeamMember() {
		return hasServiceTeamMember;
	}

	public void setHasServiceTeamMember(Long hasServiceTeamMember) {
		this.hasServiceTeamMember = hasServiceTeamMember;
	}

	public Long getHasServiceTeamRecord() {
		return hasServiceTeamRecord;
	}

	public void setHasServiceTeamRecord(Long hasServiceTeamRecord) {
		this.hasServiceTeamRecord = hasServiceTeamRecord;
	}

	public String getLocationType() {
		return BaseInfoTables.RENTALHOUSE_KEY;
	}

	public Long getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Long roomNumber) {
		this.roomNumber = roomNumber;
	}

	public PropertyDict getType() {
		return type;
	}

	public void setType(PropertyDict type) {
		this.type = type;
	}

	public Long getLimitPersonsFrom() {
		return limitPersonsFrom;
	}

	public void setLimitPersonsFrom(Long limitPersonsFrom) {
		this.limitPersonsFrom = limitPersonsFrom;
	}

	public Long getLimitPersonsTo() {
		return limitPersonsTo;
	}

	public void setLimitPersonsTo(Long limitPersonsTo) {
		this.limitPersonsTo = limitPersonsTo;
	}

	public Long getRealityPersonsFrom() {
		return realityPersonsFrom;
	}

	public void setRealityPersonsFrom(Long realityPersonsFrom) {
		this.realityPersonsFrom = realityPersonsFrom;
	}

	public Long getRealityPersonsTo() {
		return realityPersonsTo;
	}

	public void setRealityPersonsTo(Long realityPersonsTo) {
		this.realityPersonsTo = realityPersonsTo;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public PropertyDict getRentalProperty() {
		return rentalProperty;
	}

	public void setRentalProperty(PropertyDict rentalProperty) {
		this.rentalProperty = rentalProperty;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public void setIsFastSearch(Boolean isFastSearch) {
		this.isFastSearch = isFastSearch;
	}

	public Boolean getIsFastSearch() {
		return isFastSearch;
	}

}
