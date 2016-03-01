package com.tianque.domain.vo;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class SearchHospitalVo extends BaseDomain {
	/** 所属网格 */
	private Organization organization;
	/** 医院等级 */
	private PropertyDict level;
	/** 医院性质 */
	private PropertyDict kind;
	/** 医院类型 */
	private PropertyDict type;
	/** 是否医保 */
	private Boolean medicalInsurance;
	/** 所属网格编号 */
	private String orgInternalCode;
	/** 医院别名 */
	private String anotherName;
	/** 医院名称 */
	private String hospitalName;
	/** 医院地址 */
	private String address;
	/** 医院院长 */
	private String director;
	/** 所属单位 */
	private String affiliatedUnit;
	/** 联系人姓名 */
	private String contactName;
	/** 联系人固定电话 */
	private String telephone;
	/** 联系人手机号码 */
	private String mobileNumber;

	/** 列表页面快速查询的字段 */
	private String unitName;

	/**
	 * 关注程度
	 */
	private Long attentionExtentId;

	public Long getAttentionExtentId() {
		return attentionExtentId;
	}

	public void setAttentionExtentId(Long attentionExtentId) {
		this.attentionExtentId = attentionExtentId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	private Boolean isEmphasis;
	private String importantLocationType;

	public String getAnotherName() {
		return anotherName;
	}

	public String getImportantLocationType() {
		return importantLocationType;
	}

	public void setImportantLocationType(String importantLocationType) {
		this.importantLocationType = importantLocationType;
	}

	public void setAnotherName(String anotherName) {
		this.anotherName = anotherName;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getAffiliatedUnit() {
		return affiliatedUnit;
	}

	public void setAffiliatedUnit(String affiliatedUnit) {
		this.affiliatedUnit = affiliatedUnit;
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
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}

	/** 联系人电子邮件 */
	private String email;
	/** 传真 */
	private String fax;
	/** 备注 */
	private String remark;
	/** 全拼 */
	private String fullPinyin;
	/** 简拼 */
	private String simplePinyin;

	// 是否有治安负责人
	private Long hasServiceTeamMember;
	// 是否有巡场记录
	private Long hasServiceRecord;

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

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public PropertyDict getKind() {
		return kind;
	}

	public void setKind(PropertyDict kind) {
		this.kind = kind;
	}

	public PropertyDict getType() {
		return type;
	}

	public void setType(PropertyDict type) {
		this.type = type;
	}

	public PropertyDict getLevel() {
		return level;
	}

	public void setLevel(PropertyDict level) {
		this.level = level;
	}

	public Boolean getMedicalInsurance() {
		return medicalInsurance;
	}

	public void setMedicalInsurance(Boolean medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}

	public Boolean getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Boolean isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

}
