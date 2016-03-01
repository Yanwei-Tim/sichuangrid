package com.tianque.domain.vo;

import java.io.Serializable;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.ComprehensiveManageMember;

@SuppressWarnings("serial")
public class SearchEnterpriseVo extends BaseDomain implements Serializable {
	private String name;
	private String businessLicense;
	private String legalPerson;
	private Long industryId;
	private Long typeId;
	private String orgInternalCode;
	private String keyType;
	private Long attentionExtentId;
	// 类型
	private String importantLocationType;
	// 面积区间
	private Double minArea;
	private Double maxArea;
	// 是危险企业（默认为否）
	private Boolean isRiskEnterprise;
	// 企业联系电话
	private String enterpriseTelephone;
	// 党员数量区间
	private Long minPartyMemberAmount;
	private Long maxPartyMemberAmount;
	// 企业传真
	private String fax;
	// 员工数区间
	private Long minEmployeeAmount;
	private Long maxEmployeeAmount;
	// 法人手机号码
	private String mobileNumber;
	// 注册资金区间
	private Double minRegisteredCapital;
	private Double maxRegisteredCapital;
	// 法人联系电话
	private String telephone;
	// 综治负责人
	private ComprehensiveManageMember comprehensiveManageMember1;
	// 综治专干
	private ComprehensiveManageMember comprehensiveManageMember2;
	// 企业地址
	private String address;
	// 隐患情况
	private String hiddenCases;
	// 整改情况
	private String hiddenRectification;
	private Long isEmphasis;
	// 是否有治安负责人
	private Long hasServiceTeamMember;
	// 是否有巡场记录
	private Long hasServiceRecord;

	private Integer renovateType;

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public Double getMinArea() {
		return minArea;
	}

	public void setMinArea(Double minArea) {
		this.minArea = minArea;
	}

	public Double getMaxArea() {
		return maxArea;
	}

	public void setMaxArea(Double maxArea) {
		this.maxArea = maxArea;
	}

	public Boolean getIsRiskEnterprise() {
		return isRiskEnterprise;
	}

	public void setIsRiskEnterprise(Boolean isRiskEnterprise) {
		this.isRiskEnterprise = isRiskEnterprise;
	}

	public String getEnterpriseTelephone() {
		return enterpriseTelephone;
	}

	public void setEnterpriseTelephone(String enterpriseTelephone) {
		this.enterpriseTelephone = enterpriseTelephone;
	}

	public Long getMinPartyMemberAmount() {
		return minPartyMemberAmount;
	}

	public void setMinPartyMemberAmount(Long minPartyMemberAmount) {
		this.minPartyMemberAmount = minPartyMemberAmount;
	}

	public Long getMaxPartyMemberAmount() {
		return maxPartyMemberAmount;
	}

	public void setMaxPartyMemberAmount(Long maxPartyMemberAmount) {
		this.maxPartyMemberAmount = maxPartyMemberAmount;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Long getMinEmployeeAmount() {
		return minEmployeeAmount;
	}

	public void setMinEmployeeAmount(Long minEmployeeAmount) {
		this.minEmployeeAmount = minEmployeeAmount;
	}

	public Long getMaxEmployeeAmount() {
		return maxEmployeeAmount;
	}

	public void setMaxEmployeeAmount(Long maxEmployeeAmount) {
		this.maxEmployeeAmount = maxEmployeeAmount;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Double getMinRegisteredCapital() {
		return minRegisteredCapital;
	}

	public void setMinRegisteredCapital(Double minRegisteredCapital) {
		this.minRegisteredCapital = minRegisteredCapital;
	}

	public Double getMaxRegisteredCapital() {
		return maxRegisteredCapital;
	}

	public void setMaxRegisteredCapital(Double maxRegisteredCapital) {
		this.maxRegisteredCapital = maxRegisteredCapital;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public ComprehensiveManageMember getComprehensiveManageMember1() {
		return comprehensiveManageMember1;
	}

	public void setComprehensiveManageMember1(
			ComprehensiveManageMember comprehensiveManageMember1) {
		this.comprehensiveManageMember1 = comprehensiveManageMember1;
	}

	public ComprehensiveManageMember getComprehensiveManageMember2() {
		return comprehensiveManageMember2;
	}

	public void setComprehensiveManageMember2(
			ComprehensiveManageMember comprehensiveManageMember2) {
		this.comprehensiveManageMember2 = comprehensiveManageMember2;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHiddenCases() {
		return hiddenCases;
	}

	public void setHiddenCases(String hiddenCases) {
		this.hiddenCases = hiddenCases;
	}

	public String getHiddenRectification() {
		return hiddenRectification;
	}

	public void setHiddenRectification(String hiddenRectification) {
		this.hiddenRectification = hiddenRectification;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public Long getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Long industryId) {
		this.industryId = industryId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getAttentionExtentId() {
		return attentionExtentId;
	}

	public void setAttentionExtentId(Long attentionExtentId) {
		this.attentionExtentId = attentionExtentId;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
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

	public Integer getRenovateType() {
		return renovateType;
	}

	public void setRenovateType(Integer renovateType) {
		this.renovateType = renovateType;
	}

}
