package com.tianque.gis.domain.vo;

import com.tianque.domain.GisInfo;

public class ActualUnitVo {
	// gis统计类型
	private String gisSearchType;
	private int giscountNum;
	private String typeCatalogName;
	private String displayName;
	private String keyPersonType;
	private GisInfo gisInfo;
	private boolean enableBind;

	private Long unitId;
	private Long orgId;
	private String companyAddress;
	private String companyName;
	private String businessLicenseNo;
	private String companyType;
	private String corporateRepresentative;
	private String telephone;
	private String economicNature;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCorporateRepresentative() {
		return corporateRepresentative;
	}

	public void setCorporateRepresentative(String corporateRepresentative) {
		this.corporateRepresentative = corporateRepresentative;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEconomicNature() {
		return economicNature;
	}

	public void setEconomicNature(String economicNature) {
		this.economicNature = economicNature;
	}

	public boolean isEnableBind() {
		return enableBind;
	}

	public void setEnableBind(boolean enableBind) {
		this.enableBind = enableBind;
	}

	public String getKeyPersonType() {
		return keyPersonType;
	}

	public void setKeyPersonType(String keyPersonType) {
		this.keyPersonType = keyPersonType;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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

	public String getTypeCatalogName() {
		return typeCatalogName;
	}

	public void setTypeCatalogName(String typeCatalogName) {
		this.typeCatalogName = typeCatalogName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBusinessLicenseNo() {
		return businessLicenseNo;
	}

	public void setBusinessLicenseNo(String businessLicenseNo) {
		this.businessLicenseNo = businessLicenseNo;
	}

	public GisInfo getGisInfo() {
		return gisInfo;
	}

	public void setGisInfo(GisInfo gisInfo) {
		this.gisInfo = gisInfo;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

}
