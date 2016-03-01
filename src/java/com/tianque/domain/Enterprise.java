package com.tianque.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.core.util.BaseInfoTables;

@SuppressWarnings("serial")
public class Enterprise extends LocationBaseInfo {

	public Enterprise() {
		setLocationType(BaseInfoTables.ENTERPRISEKEY_KEY);
	}

	/** 注销原因 */
	private String logOutReason;
	/** 注销时间 */
	private Date logOutTime;
	/** 图像路径 */
	private String imgUrl;
	/**
	 * 员工数量
	 */
	private Long employeeAmount;
	/**
	 * 党员数量
	 */
	private Long partyMemberAmount;
	/**
	 * 注册资金
	 */
	private BigDecimal registeredCapital;
	/**
	 * 面积
	 */
	private BigDecimal area;
	/**
	 * 是否危化企业（默认为否）
	 */
	private boolean isRiskEnterprise;
	/**
	 * 企业类型
	 */
	private PropertyDict type;
	/**
	 * 所属行业
	 */
	private PropertyDict industry;
	/**
	 * 企业名称
	 */
	private String name;
	/**
	 * 工商执照号码
	 */
	private String businessLicense;
	/**
	 * 法人代表
	 */
	private String legalPerson;
	/**
	 * 隐患情况
	 */
	private String hiddenCases;
	/**
	 * 隐患整改情况
	 */
	private String hiddenRectification;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 企业名称全拼
	 */
	private String fullPinyin;
	/**
	 * 企业名称简拼
	 */
	private String simplePinyin;
	/**
	 * 所属网格内部编码
	 */
	private String orgInternalCode;
	/**
	 * 企业联系电话
	 */
	private String enterpriseTelephone;
	/**
	 * 传真
	 */
	private String fax;
	/**
	 * 法人联系电话
	 */
	private String telephone;
	/**
	 * 法人手机号码
	 */
	private String mobileNumber;
	/**
	 * 地址
	 */
	private String address;

	private String areaString;

	private String registeredCapitalString;

	private Integer renovateType;

	/**
	 * 综治工作室成员
	 */
	private List<ComprehensiveManageMember> comprehensiveManageMembers;

	/**
	 * 重点类型(重点保障，安全生产重点，消防安全重点)
	 */
	private String keyType;

	public final static String KEYTYPE_ENTERPRISEKEY = "enterpriseKey";// 规上企业
	public final static String KEYTYPE_PROTECTIONKEY = "enterpriseKey";// 重点保障
	public final static String KEYTYPE_SAFETYPRODUCTIONKEY = "safetyProductionKey";// 安全生产重点
	public final static String KEYTYPE_FIRESAFETYKEY = "fireSafetyKey";// 消防安全重点
	public final static String KEYTYPE_SECURITYKEY = "securityKey";// 治安重点
	public final static String KEYTYPE_ENTERPRISEDOWNKEY = "enterpriseDownKey";// 规下企业

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public boolean isRiskEnterprise() {
		return isRiskEnterprise;
	}

	public void setRiskEnterprise(boolean isRiskEnterprise) {
		this.isRiskEnterprise = isRiskEnterprise;
	}

	public PropertyDict getType() {
		return type;
	}

	public void setType(PropertyDict type) {
		this.type = type;
	}

	public PropertyDict getIndustry() {
		return industry;
	}

	public void setIndustry(PropertyDict industry) {
		this.industry = industry;
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

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getEnterpriseTelephone() {
		return enterpriseTelephone;
	}

	public void setEnterpriseTelephone(String enterpriseTelephone) {
		this.enterpriseTelephone = enterpriseTelephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<ComprehensiveManageMember> getComprehensiveManageMembers() {
		return comprehensiveManageMembers;
	}

	public void setComprehensiveManageMembers(
			List<ComprehensiveManageMember> comprehensiveManageMembers) {
		this.comprehensiveManageMembers = comprehensiveManageMembers;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getAreaString() {
		return areaString;
	}

	public void setAreaString(String areaString) {
		this.areaString = areaString;
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

	public BigDecimal getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(BigDecimal registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public Long getEmployeeAmount() {
		return employeeAmount;
	}

	public void setEmployeeAmount(Long employeeAmount) {
		this.employeeAmount = employeeAmount;
	}

	public Long getPartyMemberAmount() {
		return partyMemberAmount;
	}

	public void setPartyMemberAmount(Long partyMemberAmount) {
		this.partyMemberAmount = partyMemberAmount;
	}

	public String getRegisteredCapitalString() {
		return registeredCapitalString;
	}

	public void setRegisteredCapitalString(String registeredCapitalString) {
		this.registeredCapitalString = registeredCapitalString;
	}

	public Integer getRenovateType() {
		return renovateType;
	}

	public void setRenovateType(Integer renovateType) {
		this.renovateType = renovateType;
	}

	// id orgcode加密
	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(),
				getOrgInternalCode(), null);
	}
}
