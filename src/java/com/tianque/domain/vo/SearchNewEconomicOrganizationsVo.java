package com.tianque.domain.vo;

import java.io.Serializable;
import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseInfoTables;

public class SearchNewEconomicOrganizationsVo extends BaseDomain implements Serializable {

	private String name;
	private String residence;
	private String licenseNumber;
	private Date validityStartDate;
	private Date validityEndDate;
	private String style;
	private String chief;
	private String foxNumber;
	private String orgInternalCode;
	private String areaStart;
	private String areaEnd;
	private String employeeNumberStart;
	private String employeeNumberEnd;
	private String partyMemberNumberStart;
	private String partyMemberNumberEnd;

	private String telephone;
	private String mobileNumber;

	/** 是否注销 */
	private Long isEmphasis;
	/** 查询时排序字段 */
	private String sortField;
	/** 查询排序 */
	private String order;
	/** 快速搜索条件 */
	private String fastSearch;
	/* 是否有治安负责人 */
	private Long hasServiceTeamMember;
	/* 是否有巡场记录 */
	private Long hasServiceRecord;
	/* 对象类型 */
	private String objectType;

	public SearchNewEconomicOrganizationsVo() {
		setObjectType(BaseInfoTables.NEWECONOMICORGANIZATIONS_KEY);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public Date getValidityStartDate() {
		return validityStartDate;
	}

	public void setValidityStartDate(Date validityStartDate) {
		this.validityStartDate = validityStartDate;
	}

	public Date getValidityEndDate() {
		return validityEndDate;
	}

	public void setValidityEndDate(Date validityEndDate) {
		this.validityEndDate = validityEndDate;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getChief() {
		return chief;
	}

	public void setChief(String chief) {
		this.chief = chief;
	}

	public String getFoxNumber() {
		return foxNumber;
	}

	public void setFoxNumber(String foxNumber) {
		this.foxNumber = foxNumber;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getAreaStart() {
		return areaStart;
	}

	public void setAreaStart(String areaStart) {
		this.areaStart = areaStart;
	}

	public String getAreaEnd() {
		return areaEnd;
	}

	public void setAreaEnd(String areaEnd) {
		this.areaEnd = areaEnd;
	}

	public String getEmployeeNumberStart() {
		return employeeNumberStart;
	}

	public void setEmployeeNumberStart(String employeeNumberStart) {
		this.employeeNumberStart = employeeNumberStart;
	}

	public String getEmployeeNumberEnd() {
		return employeeNumberEnd;
	}

	public void setEmployeeNumberEnd(String employeeNumberEnd) {
		this.employeeNumberEnd = employeeNumberEnd;
	}

	public String getPartyMemberNumberStart() {
		return partyMemberNumberStart;
	}

	public void setPartyMemberNumberStart(String partyMemberNumberStart) {
		this.partyMemberNumberStart = partyMemberNumberStart;
	}

	public String getPartyMemberNumberEnd() {
		return partyMemberNumberEnd;
	}

	public void setPartyMemberNumberEnd(String partyMemberNumberEnd) {
		this.partyMemberNumberEnd = partyMemberNumberEnd;
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

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
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

	public String getFastSearch() {
		return fastSearch;
	}

	public void setFastSearch(String fastSearch) {
		this.fastSearch = fastSearch;
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

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

}
