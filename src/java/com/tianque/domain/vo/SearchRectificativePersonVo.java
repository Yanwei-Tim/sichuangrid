package com.tianque.domain.vo;

import java.io.Serializable;
import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.PropertyDict;

@SuppressWarnings("serial")
public class SearchRectificativePersonVo extends BaseDomain implements Serializable {
	/** 性别 */
	private Long genderId;
	/** 刑法执行类别 */
	private Long executeTypeId;
	/** 家庭住址 */
	private String familyAddress;
	/** 原判刑期 */
	private String penaltyTerm;
	/** 近况描述 */
	private String recentSituation;
	/** 帮教人员 */
	private String helpEducator;
	/** 帮教人员电话 */
	private String educatorTelephone;
	/** 帮教人员手机号码 */
	private String educatorMobileNumber;
	/** 户籍地详址 */
	private String nativePlaceAddress;
	/** 户籍派出所 */
	private String nativePoliceStation;
	/** 现在居住地 */
	private String currentAddress;

	/** 省 */
	private String province;
	/** 市 */
	private String city;
	/** 区县 */
	private String district;
	/** 姓名 */
	private String name;
	/** 身份证号 */
	private String idCardNo;
	/** 固定电话 */
	private String telephone;
	/** 手机号码 */
	private String mobileNumber;
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 开始出生日期 */
	private Date startBirthday;
	/** 结束出生日期 */
	private Date endBirthday;
	/** 社区矫正开始时间 */
	private Date rectifyStartDate;
	/** 社区矫正结束时间 */
	private Date rectifyEndDate;
	private Long isEmphasis;
	private String fastSearchKeyWords;// 姓名、身份证
	private Long isDeath;// 死亡状态
	/** 是否有服务成员 */
	private Long hasServiceTeamMember;
	/** 是否有服务记录 */
	private Long hasServiceTeamRecord;
	/** 学历 */
	private PropertyDict schooling;

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public Long getGenderId() {
		return genderId;
	}

	public void setGenderId(Long genderId) {
		this.genderId = genderId;
	}

	public Long getExecuteTypeId() {
		return executeTypeId;
	}

	public void setExecuteTypeId(Long executeTypeId) {
		this.executeTypeId = executeTypeId;
	}

	public String getFamilyAddress() {
		return familyAddress;
	}

	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}

	public String getPenaltyTerm() {
		return penaltyTerm;
	}

	public void setPenaltyTerm(String penaltyTerm) {
		this.penaltyTerm = penaltyTerm;
	}

	public String getRecentSituation() {
		return recentSituation;
	}

	public void setRecentSituation(String recentSituation) {
		this.recentSituation = recentSituation;
	}

	public String getHelpEducator() {
		return helpEducator;
	}

	public void setHelpEducator(String helpEducator) {
		this.helpEducator = helpEducator;
	}

	public String getEducatorTelephone() {
		return educatorTelephone;
	}

	public void setEducatorTelephone(String educatorTelephone) {
		this.educatorTelephone = educatorTelephone;
	}

	public String getEducatorMobileNumber() {
		return educatorMobileNumber;
	}

	public void setEducatorMobileNumber(String educatorMobileNumber) {
		this.educatorMobileNumber = educatorMobileNumber;
	}

	public String getNativePlaceAddress() {
		return nativePlaceAddress;
	}

	public void setNativePlaceAddress(String nativePlaceAddress) {
		this.nativePlaceAddress = nativePlaceAddress;
	}

	public String getNativePoliceStation() {
		return nativePoliceStation;
	}

	public void setNativePoliceStation(String nativePoliceStation) {
		this.nativePoliceStation = nativePoliceStation;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
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
		this.idCardNo = idCardNo;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Date getStartBirthday() {
		return startBirthday;
	}

	public void setStartBirthday(Date startBirthday) {
		this.startBirthday = startBirthday;
	}

	public Date getEndBirthday() {
		return endBirthday;
	}

	public void setEndBirthday(Date endBirthday) {
		this.endBirthday = endBirthday;
	}

	public Date getRectifyStartDate() {
		return rectifyStartDate;
	}

	public void setRectifyStartDate(Date rectifyStartDate) {
		this.rectifyStartDate = rectifyStartDate;
	}

	public Date getRectifyEndDate() {
		return rectifyEndDate;
	}

	public void setRectifyEndDate(Date rectifyEndDate) {
		this.rectifyEndDate = rectifyEndDate;
	}

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public String getFastSearchKeyWords() {
		return fastSearchKeyWords;
	}

	public void setFastSearchKeyWords(String fastSearchKeyWords) {
		this.fastSearchKeyWords = fastSearchKeyWords;
	}

	public Long getIsDeath() {
		return isDeath;
	}

	public void setIsDeath(Long isDeath) {
		this.isDeath = isDeath;
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

	public PropertyDict getSchooling() {
		return schooling;
	}

	public void setSchooling(PropertyDict schooling) {
		this.schooling = schooling;
	}

	public String getAttentionPopulationType() {
		return BaseInfoTables.RECTIFICATIVEPERSON_KEY;
	}

}
