package com.tianque.baseInfo.actualHouse.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.mongodb.morphia.annotations.NotSaved;
import org.mongodb.morphia.annotations.PostLoad;
import org.mongodb.morphia.annotations.Property;

import com.mongodb.DBObject;
import com.tianque.baseInfo.buildDatas.domain.Builddatas;
import com.tianque.baseInfo.buildDatas.domain.OpenLayersMapInfo;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.GisInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.qrcode.domain.QrcodeDomain;

/**
 * 住房信息
 */
public class HouseInfo extends BaseDomain {
	@NotSaved
	private String houseType;

	public HouseInfo() {
		setHouseType(BaseInfoTables.ACTUALHOUSE_KEY);
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	@NotSaved
	private GisInfo gisInfo;
	// gis统计类型
	@NotSaved
	private String gisSearchType;
	@NotSaved
	private int giscountNum;

	private Organization organization;

	// @Indexed(value = IndexDirection.DESC, name = "orgCode")
	@NotSaved
	private String orgInternalCode;

	@Property("orgCode")
	private String mongoOrgCode;
	@NotSaved
	private String buildingId;

	/** 住房编号 */
	// @Indexed(value = IndexDirection.DESC, name = "houseCode")
	private String houseCode;

	/** 常住地址类型 */
	@NotSaved
	private PropertyDict addressType;

	/** 是否是出租房 */
	private Boolean isRentalHouse;

	/** 是否出租房中文字符串 非持久化 */
	@NotSaved
	private String rentalHouse;

	/** 居住人数（不保存到数据库） */
	@NotSaved
	private int personNum;

	/** 常住地址 */
	// @Indexed(value = IndexDirection.DESC, name = "index_address")
	private String address;

	/** 小区 */
	@NotSaved
	private String community;

	/** 幢 */
	@NotSaved
	private String block;

	/** 单元 */
	@NotSaved
	private String unit;

	/** 室 */
	@NotSaved
	private String room;

	/** 建筑物名称 */
	@NotSaved
	private String buildingName;

	/** 建筑物用途 */
	@NotSaved
	private PropertyDict buildingUses;

	/** 物业管理单位名称 */
	@NotSaved
	private String propertyName;

	/** 代表人/业主 */
	private String houseOwner;

	/** 业主身份证号 */
	@NotSaved
	private String houseOwnerIdCardNo;

	/** 业主联系电话 */
	@NotSaved
	private String telephone;

	/** 业主联系手机 */
	@NotSaved
	private String mobileNumber;

	/** 房屋户型 */
	@NotSaved
	private String houseDoorModel;

	/** 建成年份 */
	@NotSaved
	private String builtYear;

	/** 房屋结构 */
	private PropertyDict houseStructure;

	/** 本户建筑面积 */
	private Double houseArea;

	/** 房屋用途 */
	private PropertyDict houseUses;

	/** 房屋来源 */
	private PropertyDict houseSource;

	/** 自有产权 */
	@NotSaved
	private PropertyDict ownProperty;

	/** 租赁公房 */
	@NotSaved
	private PropertyDict rentalBuildings;

	/** 房屋凭证 */
	@NotSaved
	private PropertyDict housingVouchers;

	/** 房屋权证号 */
	@NotSaved
	private String houseRightNumber;

	/** 房屋权证发证时间 */
	@NotSaved
	private Date houseRightNumberDate;

	/** 土地凭证 */
	@NotSaved
	private PropertyDict landDocuments;

	/** 土地权证号 */
	@NotSaved
	private String landRightsNumbe;

	/** 土地权证发证时间 */
	@NotSaved
	private Date landRightsNumbeDate;

	/** 产权人类型 */
	@NotSaved
	private PropertyDict propertyTypes;

	/** 产权人姓名 */
	private String name;

	/** 产权人姓名全拼 */
	@NotSaved
	private String fullPinyin;

	/** 产权人姓名简拼 */
	@NotSaved
	private String simplePinyin;

	/** 产权人证件类型 */
	@NotSaved
	private PropertyDict certificateType;

	/** 产权人证件号码 */
	private String certificateNumbe;

	/** 产权人联系电话 */
	@NotSaved
	private String propertyPersonTel;

	/** 产权人联系手机 */
	@NotSaved
	private String propertyPersonMobile;

	/** 图像路径 */
	@NotSaved
	private String imgUrl;

	/** 备注 */
	@NotSaved
	private String remark;

	/** 楼宇 */
	@NotSaved
	private Builddatas builddatas;

	/** 二维地图坐标 */
	@NotSaved
	private OpenLayersMapInfo openLayersMapInfo;

	/** 楼宇编号 */
	@NotSaved
	private Long builddatasId;

	/** 布局模块房屋颜色，非持久化字段 */
	@NotSaved
	private String backGround;

	/** 居住人数 */
	private Integer memberNum;

	// /** 出租人 */
	// private String rentalPerson;
	// /** 出租人联系地址 */
	// private String lessorAddress;
	// /** 出租人联系手机 */
	// private String rentalMobileNumber;
	// /** 出租房用途 */
	// private PropertyDict usage;
	// /** 隐患等级 */
	// private PropertyDict hiddenDangerLevel;
	// /** 出租房类别 */
	// private PropertyDict rentalType;
	// /** 出租人联系电话 */
	// private String rentalTelephone;
	// private PropertyDict rentalHouseProperty;
	@NotSaved
	private String houseAddress;// 房产证地址
	@NotSaved
	private String houseOperateSource;// 添加日志是判断该信息来源

	/** 二维码列表 */
	@NotSaved
	private List<QrcodeDomain> qrcodeList = new ArrayList<QrcodeDomain>();

	public int getPersonNum() {
		return personNum;
	}

	public void setPersonNum(int personNum) {
		this.personNum = personNum;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public GisInfo getGisInfo() {
		return gisInfo;
	}

	public void setGisInfo(GisInfo gisInfo) {
		this.gisInfo = gisInfo;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.mongoOrgCode = orgInternalCode == null ? null : orgInternalCode
				.replace(".", "@");
		this.orgInternalCode = orgInternalCode;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}

	public PropertyDict getAddressType() {
		return addressType;
	}

	public void setAddressType(PropertyDict addressType) {
		this.addressType = addressType;
	}

	public Boolean getIsRentalHouse() {
		return isRentalHouse;
	}

	public void setIsRentalHouse(Boolean isRentalHouse) {
		this.rentalHouse = isRentalHouse == true ? "出租房" : "非出租房";
		this.isRentalHouse = isRentalHouse;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
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

	public String getHouseOwner() {
		return houseOwner;
	}

	public void setHouseOwner(String houseOwner) {
		this.houseOwner = houseOwner;
	}

	public String getHouseOwnerIdCardNo() {
		return houseOwnerIdCardNo;
	}

	public void setHouseOwnerIdCardNo(String houseOwnerIdCardNo) {
		this.houseOwnerIdCardNo = houseOwnerIdCardNo;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getHouseDoorModel() {
		return houseDoorModel;
	}

	public void setHouseDoorModel(String houseDoorModel) {
		this.houseDoorModel = houseDoorModel;
	}

	public String getBuiltYear() {
		return builtYear;
	}

	public void setBuiltYear(String builtYear) {
		this.builtYear = builtYear;
	}

	public PropertyDict getHouseStructure() {
		return houseStructure;
	}

	public void setHouseStructure(PropertyDict houseStructure) {
		this.houseStructure = houseStructure;
	}

	public Double getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(Double houseArea) {
		this.houseArea = houseArea;
	}

	public PropertyDict getHouseUses() {
		return houseUses;
	}

	public void setHouseUses(PropertyDict houseUses) {
		this.houseUses = houseUses;
	}

	public PropertyDict getHouseSource() {
		return houseSource;
	}

	public void setHouseSource(PropertyDict houseSource) {
		this.houseSource = houseSource;
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

	@JSON(format = "yyyy-MM-dd")
	public Date getHouseRightNumberDate() {
		return houseRightNumberDate;
	}

	public void setHouseRightNumberDate(Date houseRightNumberDate) {
		this.houseRightNumberDate = houseRightNumberDate;
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

	@JSON(format = "yyyy-MM-dd")
	public Date getLandRightsNumbeDate() {
		return landRightsNumbeDate;
	}

	public void setLandRightsNumbeDate(Date landRightsNumbeDate) {
		this.landRightsNumbeDate = landRightsNumbeDate;
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
		if (fullPinyin != null && fullPinyin.length() > 30) {
			fullPinyin = fullPinyin.substring(0, 30);
		}
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		if (simplePinyin != null && simplePinyin.length() > 10) {
			simplePinyin = simplePinyin.substring(0, 10);
		}
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

	public String getPropertyPersonTel() {
		return propertyPersonTel;
	}

	public void setPropertyPersonTel(String propertyPersonTel) {
		this.propertyPersonTel = propertyPersonTel;
	}

	public String getPropertyPersonMobile() {
		return propertyPersonMobile;
	}

	public void setPropertyPersonMobile(String propertyPersonMobile) {
		this.propertyPersonMobile = propertyPersonMobile;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGisSearchType() {
		return gisSearchType;
	}

	public void setGisSearchType(String gisSearchType) {
		this.gisSearchType = gisSearchType;
	}

	public int getGiscountNum() {
		return giscountNum;
	}

	public void setGiscountNum(int giscountNum) {
		this.giscountNum = giscountNum;
	}

	public Builddatas getBuilddatas() {
		return builddatas;
	}

	public OpenLayersMapInfo getOpenLayersMapInfo() {
		return openLayersMapInfo;
	}

	public Long getBuilddatasId() {
		return builddatasId;
	}

	public void setBuilddatas(Builddatas builddatas) {
		this.builddatas = builddatas;
	}

	public void setOpenLayersMapInfo(OpenLayersMapInfo openLayersMapInfo) {
		this.openLayersMapInfo = openLayersMapInfo;
	}

	public void setBuilddatasId(Long builddatasId) {
		this.builddatasId = builddatasId;
	}

	public String getBackGround() {
		return backGround;
	}

	public void setBackGround(String backGround) {
		this.backGround = backGround;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
	}

	public String getRentalHouse() {
		return rentalHouse;
	}

	public void setRentalHouse(String rentalHouse) {
		this.rentalHouse = rentalHouse;
	}

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public List<QrcodeDomain> getQrcodeList() {
		return qrcodeList;
	}

	public void setQrcodeList(List<QrcodeDomain> qrcodeList) {
		this.qrcodeList = qrcodeList;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(),
				this.orgInternalCode, null);
	}

	public String getHouseOperateSource() {
		return houseOperateSource;
	}

	public void setHouseOperateSource(String houseOperateSource) {
		this.houseOperateSource = houseOperateSource;
	}

	public void setMongoOrgCode(String mongoOrgCode) {
		this.mongoOrgCode = mongoOrgCode;
	}

	public String getMongoOrgCode() {
		return orgInternalCode == null ? null : orgInternalCode.replace(".",
				"@");
	}

	@PostLoad
	public void postLoad(DBObject dbObj) {
		orgInternalCode = mongoOrgCode == null ? null : mongoOrgCode.replace(
				"@", ".");
	}

}
