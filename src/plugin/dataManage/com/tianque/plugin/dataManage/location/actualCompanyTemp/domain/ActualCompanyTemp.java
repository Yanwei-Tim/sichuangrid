package com.tianque.plugin.dataManage.location.actualCompanyTemp.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.domain.PropertyDict;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;
import com.tianque.plugin.dataManage.location.allPlaceCommonTemp.domain.AllPlaceCommonTemp;

/**
 * 实有单位
 */
public class ActualCompanyTemp extends AllPlaceCommonTemp {
	private ClaimDetail claimDetail;
	/** 营业执照号码 */
	private String businessLicenseNo;
	/** 组织机构代码 */
	private String orgCode;
	/** 单位类别 */
	private PropertyDict companyType;
	/** 管理级别 */
	private PropertyDict supervisoryLevel;
	/** 管理部门 */
	private String supervisoryDepartment;
	/** 治安负责人 */
	private String securityChief;
	/** 是否重点单位 */
	private Boolean isKey;
	/** 消防等级 */
	private PropertyDict fireFightingLevel;
	/** 单位电话 */
	private String telephone;
	/** 单位传真 */
	private String fax;
	/** 注册资本 */
	private Double registeredCapital;
	/** 经济性质 */
	private PropertyDict economicNature;
	/** 有效期至 */
	private Date expiryDate;
	/** 注册日期 */
	private Date registrationDate;
	/** 经营范围 */
	private String businessScope;
	/** 注册地址 */
	private String registrationAddress;
	/** 从业人数 */
	private Long employeesNum;
	/** 主管部门 */
	private String competentDepartment;
	/** 身份证号 */
	private String idCardNo;

	public String getBusinessLicenseNo() {
		return businessLicenseNo;
	}

	public void setBusinessLicenseNo(String businessLicenseNo) {
		this.businessLicenseNo = businessLicenseNo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public PropertyDict getCompanyType() {
		return companyType;
	}

	public void setCompanyType(PropertyDict companyType) {
		this.companyType = companyType;
	}

	public PropertyDict getSupervisoryLevel() {
		return supervisoryLevel;
	}

	public void setSupervisoryLevel(PropertyDict supervisoryLevel) {
		this.supervisoryLevel = supervisoryLevel;
	}

	public String getSupervisoryDepartment() {
		return supervisoryDepartment;
	}

	public void setSupervisoryDepartment(String supervisoryDepartment) {
		this.supervisoryDepartment = supervisoryDepartment;
	}

	public String getSecurityChief() {
		return securityChief;
	}

	public void setSecurityChief(String securityChief) {
		this.securityChief = securityChief;
	}

	public Boolean getIsKey() {
		return isKey;
	}

	public void setIsKey(Boolean isKey) {
		this.isKey = isKey;
	}

	public PropertyDict getFireFightingLevel() {
		return fireFightingLevel;
	}

	public void setFireFightingLevel(PropertyDict fireFightingLevel) {
		this.fireFightingLevel = fireFightingLevel;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Double getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(Double registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public PropertyDict getEconomicNature() {
		return economicNature;
	}

	public void setEconomicNature(PropertyDict economicNature) {
		this.economicNature = economicNature;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getRegistrationAddress() {
		return registrationAddress;
	}

	public void setRegistrationAddress(String registrationAddress) {
		this.registrationAddress = registrationAddress;
	}

	public Long getEmployeesNum() {
		return employeesNum;
	}

	public void setEmployeesNum(Long employeesNum) {
		this.employeesNum = employeesNum;
	}

	public String getCompetentDepartment() {
		return competentDepartment;
	}

	public void setCompetentDepartment(String competentDepartment) {
		this.competentDepartment = competentDepartment;
	}

	public ClaimDetail getClaimDetail() {
		if (null == claimDetail) {
			claimDetail = new ClaimDetail();
		}
		return claimDetail;
	}

	public void setClaimDetail(ClaimDetail claimDetail) {
		this.claimDetail = claimDetail;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

}
