package com.tianque.baseInfo.base.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;
import org.mongodb.morphia.annotations.NotSaved;
import org.mongodb.morphia.annotations.PostLoad;
import org.mongodb.morphia.annotations.Property;

import com.mongodb.DBObject;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 人
 * 
 * @author tqkaifa
 */
public class People extends BaseDomain {

	/** 关注人员类型 */
	@NotSaved
	private String attentionPopulationType;
	/** 姓名 */
	private String name;
	/** 身份证号 */
	private String idCardNo;
	/** 出生日期 */
	private Date birthday;
	/** 邮箱 */
	private String email;
	/** 所属网格 */
	private Organization organization;
	/** 在mongodb中需要把"."替换成"@"以便正则查询 */
	@Property("orgCode")
	private String mongoOrgCode;
	/** 所属责任区编号 */
	@NotSaved
	private String orgInternalCode;
	/** 手机号码 */
	@Property("mobile")
	private String mobileNumber;
	/** 工作单位 */
	private String workUnit;
	/** 住房id（以房管人使用）不保存 */
	@NotSaved
	private Long houseId;
	/** 住房编号（流口根据编号自动填充房屋信息用） */
	@NotSaved
	private String houseCode;
	/** 常住地址类型 */
	@NotSaved
	private PropertyDict currentAddressType;
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
	/** 常住地址为其他类型 */
	@Property("current")
	private String currentAddress;
	/** 备注 */
	@NotSaved
	private String remark;
	/** 固定电话 */
	@Property("tel")
	private String telephone;
	/** 图像路径 */
	@NotSaved
	private String imgUrl;
	/** 全拼 */
	@Property("qp")
	private String fullPinyin;
	/** 简拼 */
	@Property("jp")
	private String simplePinyin;
	/** 性别 */
	private PropertyDict gender;
	/** 户籍地详址 */
	@Property("native")
	private String nativePlaceAddress;
	/** 身高 */
	private Long stature;
	@NotSaved
	private String uuid;
	/** 是否有房屋 */
	@NotSaved
	private Boolean isHaveHouse;
	/** 无房原因 */
	@NotSaved
	private String noHouseReason;

	public Boolean getIsHaveHouse() {
		return isHaveHouse;
	}

	public void setIsHaveHouse(Boolean isHaveHouse) {
		this.isHaveHouse = isHaveHouse;
	}

	public String getNoHouseReason() {
		return noHouseReason;
	}

	public void setNoHouseReason(String noHouseReason) {
		this.noHouseReason = noHouseReason;
	}

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.setUuid(idCardNo);
		this.idCardNo = idCardNo;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public String getNativePlaceAddress() {
		return nativePlaceAddress;
	}

	public void setNativePlaceAddress(String nativePlaceAddress) {
		this.nativePlaceAddress = nativePlaceAddress;
	}

	public Long getStature() {
		return stature;
	}

	public void setStature(Long stature) {
		this.stature = stature;
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

	public void setCurrentAddressType(PropertyDict currentAddressType) {
		this.currentAddressType = currentAddressType;
	}

	public PropertyDict getCurrentAddressType() {
		return currentAddressType;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setAttentionPopulationType(String attentionPopulationType) {
		this.attentionPopulationType = attentionPopulationType;
	}

	public String getAttentionPopulationType() {
		return attentionPopulationType;
	}

	public String getEncryptId() {
		String orgCode = this.orgInternalCode;
		if (!StringUtil.isStringAvaliable(orgCode) && organization != null) {
			orgCode = this.organization.getOrgInternalCode();
		}
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(), orgCode, null);
	}

	public String getMongoOrgCode() {
		return orgInternalCode == null ? null : orgInternalCode.replace(".",
				"@");
	}

	public void setMongoOrgCode(String mongoOrgCode) {
		this.mongoOrgCode = mongoOrgCode;
	}

	@PostLoad
	public void postLoad(DBObject dbObj) {
		orgInternalCode = mongoOrgCode == null ? null : mongoOrgCode.replace(
				"@", ".");
	}

}
