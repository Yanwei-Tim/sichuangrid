package com.tianque.domain;

import java.util.Date;

import com.tianque.core.util.BaseDomainIdEncryptUtil;

@SuppressWarnings("serial")
public class School extends LocationBaseInfo {
	private String chineseName;
	private String orgInternalCode;
	private String englishName;
	private PropertyDict kind;// 学校性质
	private String address;
	private PropertyDict type;// 学校类型
	private String webSite;
	private String president;
	private String contactName;
	private String telephone;
	private String mobileNumber;
	private String email;
	private String fax;
	private String remark;
	private String fullPinyin;
	private String simplePinyin;
	private Long atSchoolHeadcount;// 在校总人数
	private String personLiable;// 综治负责人
	private String personLiableTelephone;// 综治负责人联系电话
	private String personLiableMobileNumber;// 综治负责人联系手机
	/** 注销原因 */
	private String logOutReason;
	/** 注销时间 */
	private Date logOutTime;
	/** 图像路径 */
	private String imgUrl;

	/** 注销状态 */
	private Long isLogOut;

	// 是否有治安负责人
	private Long hasServiceTeamMember;
	// 是否有巡场记录
	private Long hasServiceRecord;

	private String importantLocationType;

	public School() {
		setImportantLocationType("SCHOOL");
	}

	public Long getAtSchoolHeadcount() {
		return atSchoolHeadcount;
	}

	public void setAtSchoolHeadcount(Long atSchoolHeadcount) {
		this.atSchoolHeadcount = atSchoolHeadcount;
	}

	public String getPersonLiable() {
		return personLiable;
	}

	public void setPersonLiable(String personLiable) {
		this.personLiable = personLiable;
	}

	public String getPersonLiableTelephone() {
		return personLiableTelephone;
	}

	public void setPersonLiableTelephone(String personLiableTelephone) {
		this.personLiableTelephone = personLiableTelephone;
	}

	public String getPersonLiableMobileNumber() {
		return personLiableMobileNumber;
	}

	public void setPersonLiableMobileNumber(String personLiableMobileNumber) {
		this.personLiableMobileNumber = personLiableMobileNumber;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public PropertyDict getKind() {
		return kind;
	}

	public void setKind(PropertyDict kind) {
		this.kind = kind;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public PropertyDict getType() {
		return type;
	}

	public void setType(PropertyDict type) {
		this.type = type;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getPresident() {
		return president;
	}

	public void setPresident(String president) {
		this.president = president;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	private Integer minAtSchoolHeadcount;
	private Integer maxAtSchoolHeadcount;

	public Integer getMinAtSchoolHeadcount() {
		return minAtSchoolHeadcount;
	}

	public void setMinAtSchoolHeadcount(Integer minAtSchoolHeadcount) {
		this.minAtSchoolHeadcount = minAtSchoolHeadcount;
	}

	public Integer getMaxAtSchoolHeadcount() {
		return maxAtSchoolHeadcount;
	}

	public void setMaxAtSchoolHeadcount(Integer maxAtSchoolHeadcount) {
		this.maxAtSchoolHeadcount = maxAtSchoolHeadcount;
	}

	public String getLogOutReason() {
		return logOutReason;
	}

	public void setLogOutReason(String logOutReason) {
		this.logOutReason = logOutReason;
	}

	public Date getLogOutTime() {
		return logOutTime;
	}

	public void setLogOutTime(Date logOutTime) {
		this.logOutTime = logOutTime;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getIsLogOut() {
		return isLogOut;
	}

	public void setIsLogOut(Long isLogOut) {
		this.isLogOut = isLogOut;
	}

	public Long getHasServiceTeamMember() {
		return hasServiceTeamMember;
	}

	public void setHasServiceTeamMember(Long hasServiceTeamMember) {
		this.hasServiceTeamMember = hasServiceTeamMember;
	}

	public Long getHasServiceRecord() {
		return hasServiceRecord;
	}

	public void setHasServiceRecord(Long hasServiceRecord) {
		this.hasServiceRecord = hasServiceRecord;
	}

	public String getImportantLocationType() {
		return importantLocationType;
	}

	public void setImportantLocationType(String importantLocationType) {
		this.importantLocationType = importantLocationType;
	}

	// id orgcode加密
	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(),
				getOrgInternalCode(), null);
	}

}
